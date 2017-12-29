package top.icoding.service.impl;

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
	public Map<String,Object> getArticles(int currentPage, Integer pageNumber,Integer sortId,Integer userId) {
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
	public ArticleVo getArticleById(int articleId) {
		ArticleVo article = articlemapper.selectByPrimaryKey(articleId);
		articlemapper.addArticleClickByPrimaryKey(articleId);
		return article;
	}

	@Override
	@Transactional
	public void delArticleById(int articleId) {
		int a=articlemapper.delArticleById(articleId);
		if(a!=1){
			throw new ArticleException();
		}
		int b =articlelikemapper.deleteArticleLikeByArticleId(articleId);
		if(b!=1){
			throw new ArticleException();
		}
	}

}
