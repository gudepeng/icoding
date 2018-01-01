package top.icoding.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.icoding.exception.ArticleException;
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
	public Map<String,Object> getArticles(Integer currentPage, Integer pageNumber,Integer sortId,Integer userId) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		if(pageNumber!=null){
			page.setPageNumber(pageNumber);
		}
		Map<String,Object> map = new HashMap<>(3);
		map.put("page", page);
		map.put("sortId", sortId);
		map.put("userId", userId);
		map.put("data", articlemapper.selectArticlesBySortIdByPage(map));
		return map;
	}

	@Override
	public ArticleVo getArticleInfoById(int articleId) {
		articlemapper.addArticleClickByPrimaryKey(articleId);
		return articlemapper.selectByPrimaryKey(articleId);
	}

	@Override
	@Transactional
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
			articlemapper.insertArticle(articleVo);
		}else{
			articlemapper.updateArticle(articleVo);
		}
	}

}
