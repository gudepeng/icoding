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
	Map<String,Object> getArticles(int currentPage,Integer pageNumber,Integer sortId,Integer userId);
	
	/**
	 * 根据id获取文章
	 * 
	 * @param articleId    文章主键
	 * @return 返回文章信息
	 * */
	ArticleVo getArticleById(int articleId);
}
