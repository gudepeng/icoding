package top.icoding.service;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
public interface ArticleLikeService {
	/**
	 * 点赞文章
	 * 
	 * @param articleId 文章id
	 * @param userId  用户id
	 * @return 是否点赞成功
	 * @throws Exception 
	 * */
	void like(int articleId,int userId) throws Exception;
	
	/**
	 * 取消点赞文章
	 * 
	 * @param articleId 文章id
	 * @param likeId  点赞主键
	 * @return 是否取消点赞成功
	 * */
	void unlike(int articleId,int userId);
}
