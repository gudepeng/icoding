package top.icoding.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.icoding.exception.ArticleException;
import top.icoding.security.util.SessionUser;
import top.icoding.service.ArticleService;
import top.icoding.service.impl.dao.ArticleLikeMapper;
import top.icoding.service.impl.dao.ArticleMapper;
import top.icoding.util.Page;
import top.icoding.vo.ArticleVo;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
@Service
public class ArticleServiceImpl implements ArticleService{
	@Autowired
	private ArticleMapper articlemapper;
	@Autowired
	private ArticleLikeMapper articlelikemapper;
	
	@Override
	public Map<String,Object> getArticles(Integer currentPage, Integer pageNumber,Integer sortId,String userId) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		if(pageNumber!=null){
			page.setPageNumber(pageNumber);
		}
		Map<String,Object> map = new HashMap<>(3);
		map.put("page", page);
		map.put("sortId", sortId);
		map.put("userId", userId);
		Object userDetails = SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		if(!userDetails.equals("anonymousUser")){
			map.put("likeUserId", ((SessionUser)userDetails).getUserId());
		}else{
			map.put("likeUserId", null);
		}
		map.put("data", articlemapper.selectArticlesBySortIdByPage(map));
		return map;
	}
	
	@Override
	public Map<String, Object> getSelfOrLikeArticles(Integer currentPage, Integer pageNumber, String type) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		if(pageNumber!=null){
			page.setPageNumber(pageNumber);
		}
		Map<String,Object> map = new HashMap<>(3);
		map.put("page", page);
		SessionUser userDetails = (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		if(type.equals("SELF")){
			map.put("likeUserId", null);
			map.put("userId", userDetails.getUserId());
			map.put("data", articlemapper.selectArticlesBySortIdByPage(map));
			return map;
		}else{
			map.put("likeUserId", userDetails.getUserId());
			map.put("data", articlemapper.selectLikeArticles(map));
			return map;
		}
	}

	@Override
	public ArticleVo getArticleInfoById(int articleId) {
		articlemapper.addArticleClickByPrimaryKey(articleId);
		return articlemapper.selectByPrimaryKey(articleId);
	}

	@Override
	public void delArticleById(int articleId) {
		if(articlemapper.delArticleById(articleId)==0){
			throw new ArticleException();
		}
		articlelikemapper.deleteArticleLikeByArticleId(articleId);
	}

	@Override
	public void insertAndUpdateArticle(ArticleVo articleVo) {
		articleVo.setArticleTime(new Date());
		if(articleVo.getArticleId()==null){
			articleVo.setArticleClick(0);
			articleVo.setArticleLike(0);
			articleVo.setArticleComment(0);
			articleVo.setArticleUp((byte) 0);
			articlemapper.insertArticle(articleVo);
		}else{
			articlemapper.updateArticle(articleVo);
		}
	}

}
