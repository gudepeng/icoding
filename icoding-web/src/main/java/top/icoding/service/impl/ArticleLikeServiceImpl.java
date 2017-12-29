package top.icoding.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import top.icoding.exception.ArticleException;
import top.icoding.service.ArticleLikeService;
import top.icoding.service.impl.dao.ArticleLikeMapper;
import top.icoding.service.impl.dao.ArticleMapper;
import top.icoding.vo.ArticleLikeVo;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
@Service
public class ArticleLikeServiceImpl implements ArticleLikeService{
	@Autowired
	private ArticleMapper articlemapper;
	
	@Autowired
	private ArticleLikeMapper articlelikemapper;

	@Override
	@Transactional
	public int like(int articleId, int userId) {
		int a = articlemapper.addArticleLikeByPrimaryKey(articleId);
		if(a!=1){
			throw new ArticleException();
		}
		ArticleLikeVo articlelike = new ArticleLikeVo(articleId,userId,new Date());
		int b = articlelikemapper.insertArticleLike(articlelike);
		if(b!=1){
			throw new ArticleException();
		}
		return articlelike.getArticleId();
	}

	@Override
	@Transactional
	public void unlike(int articleId,int likeId) {
		int a = articlemapper.cutArticleLikeByPrimaryKey(articleId);
		if(a!=1){
			throw new ArticleException();
		}
		int b = articlelikemapper.deleteArticleLike(likeId);
		if(b!=1){
			throw new ArticleException();
		}
	}
	

}
