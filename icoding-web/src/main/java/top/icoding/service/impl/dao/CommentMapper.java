package top.icoding.service.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import top.icoding.vo.CommentVo;

@Mapper
public interface CommentMapper {

	/**
	 * 获取评论列表
	 * 
	 * @param topicType
	 *            评论类别
	 * @param topicId
	 *            评论类别的主键
	 * @return 评论列表
	 */
	@Results(id = "selectCommentsById", value = { @Result(property = "commentId", column = "comment_id", id = true),
			@Result(property = "topicType", column = "topic_type"), @Result(property = "topicId", column = "topic_id"),
			@Result(property = "commentReply", column = "comment_reply"),
			@Result(property = "fromUserId", column = "from_user_id"),
			@Result(property = "commentTime", column = "comment_time"),
			@Result(property = "commentContent", column = "comment_content") })
	@Select("<script> "
			+ "select comment_id,topic_type,topic_id,comment_reply,from_user_id,comment_time,comment_content "
			+ "from comment where topic_type = #{topicType} and topic_id = #{topicId}" + "</script> ")
	List<CommentVo> selectCommentsById(@Param("topicType")String topicType,@Param("topicId")Integer topicId);
	
	/**
	 * 插入新评论
	 * 
	 * @param commentVo 评论实体
	 */
	@Insert("insert into comment (comment_id,topic_type,topic_id,comment_reply,from_user_id"
			+ ",comment_time,comment_content) values(#{commentId},#{topicType},#{topicId},#{commentReply},#{fromUserId}"
			+ ",#{commentTime},#{commentContent})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "commentId", keyColumn = "comment_id", resultType = Integer.class, before = false)
	int insertComment(CommentVo commentVo);
	
	/**
	 * 更新新评论
	 * 
	 * @param commentVo 评论实体
	 */
	@Update("<script> update comment <set >" + " <if test='topicType != null' >topic_type = #{topicType},</if>"
			+ " <if test='topicId != null' >topic_id = #{topicId},</if>"
			+ " <if test='commentReply != null' >comment_reply = #{commentReply},</if>"
			+ " <if test='fromUserId != null' >from_user_id = #{fromUserId},</if>"
			+ " <if test='commentTime != null' >comment_time = #{commentTime},</if>"
			+ " <if test='commentContent != null' >comment_content = #{commentContent},</if>"
			+ " </set> where comment_id = #{commentId}</script>")
	int updateComment(CommentVo commentVo);
	
	@Update("update comment set comment_reply=comment_reply+1 where comment_id=#{commentId}")
	int addCommentReplyByCommentId(int commentId);

	@Update("update comment set comment_reply=comment_reply+1 where comment_id=#{commentId}")
	int cutCommentReplyByCommentId(int commentId);
	
	@Delete("delete from comment where comment_id=#{commentId}")
	int delCommentById(int commentId);
}