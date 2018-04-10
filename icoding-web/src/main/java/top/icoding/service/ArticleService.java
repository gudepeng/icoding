package top.icoding.service;

import java.util.Map;

import top.icoding.vo.ArticleVo;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
public interface ArticleService {
	/**
	 * 获取文章列表
	 * 
	 * @param currentPage 第几页
	 * @param pageNumber  每也多少条
	 * @param sortId     文章分类
	 * @return 返回文章列表和分页信息
	 * */
	Map<String,Object> getArticles(Integer currentPage,Integer pageNumber,Integer sortId,String userId);
	
	/**
	 * 根据id获取文章
	 * 
	 * @param articleId    文章主键
	 * @return 返回文章信息
	 * */
	ArticleVo getArticleInfoById(int articleId);
	
	/**
	 * 根据id删除文章
	 * 
	 * @param articleId    文章主键
	 * @return 返回删除状态
	 * */
	void delArticleById(int articleId);
	
	/**
	 * 插入或者更新文章
	 * 
	 * @param articleVo    文章类
	 * */
	void insertAndUpdateArticle(ArticleVo articleVo);
}
