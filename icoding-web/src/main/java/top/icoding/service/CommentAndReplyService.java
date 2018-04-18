package top.icoding.service;

import java.util.List;

import top.icoding.vo.CommentVo;
import top.icoding.vo.ReplyVo;

/**
* @author 我是金角大王
* @date 2018年4月16日 下午5:32:03
*/
public interface CommentAndReplyService {
	/**
	 * 获取评论列表
	 * 
	 * @param topicType 评论类别
	 * @param topicId 评论类别的主键
	 * @return 评论列表
	 */
	List<CommentVo> getCommentsById(String topicType,Integer topicId);
	
	/**
	 * 插入或者更新评论
	 * 
	 * @param commentVo 评论实体
	 */
	void insertOrUpdateComment(CommentVo commentVo);
	
	/**
	 * 插入或者更新回复并且更新评论上的数量
	 * 
	 * @param replyVo 回复实体
	 */
	void insertOrUpdateReply(ReplyVo replyVo);
	
	/**
	 * 删除评论
	 * 
	 * @param commentId 评论主键
	 */
	void deleteComment(int commentId);
	
	/**
	 * 删除回复
	 * 
	 * @param replyId 回复主键
	 */
	void deleteReply(int replyId);
	
	/**
	 * 获取回复列表
	 * 
	 * @param commentId 评论类别
	 * @return 回复列表
	 */
	List<ReplyVo> getReplysByCommentId(int commentId);
}
