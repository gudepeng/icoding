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
public class ArticleLikeServiceImpl implements ArticleLikeService {
	@Autowired
	private ArticleMapper articlemapper;

	@Autowired
	private ArticleLikeMapper articlelikemapper;

	@Override
	@Transactional
	public void like(int articleId, String userId) throws Exception {
		if (articlemapper.addArticleLikeByPrimaryKey(articleId) == 0) {
			throw new ArticleException();
		}
		ArticleLikeVo articlelike = new ArticleLikeVo(articleId, userId, new Date());
		if (articlelikemapper.insertArticleLike(articlelike) == 0) {
			throw new ArticleException();
		}
	}

	@Override
	@Transactional
	public void unlike(int articleId, String userId) {
		if (articlemapper.cutArticleLikeByPrimaryKey(articleId) == 0) {
			throw new ArticleException();
		}
		if (articlelikemapper.deleteArticleLike(articleId, userId) == 0) {
			throw new ArticleException();
		}
	}

}
