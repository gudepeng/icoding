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

import top.icoding.vo.ReplyVo;

@Mapper
public interface ReplyMapper {
	/**
	 * 获取回复列表
	 * 
	 * @param commentId
	 *            评论主键
	 * @return 回复列表
	 */
	@Results(id = "selectReplysByCpmmentId", value = { @Result(property = "replyId", column = "reply_id", id = true),
			@Result(property = "replyType", column = "reply_type"),
			@Result(property = "commentId", column = "comment_id"),
			@Result(property = "replyReplayId", column = "reply_replay_id"),
			@Result(property = "replyContent", column = "reply_content"),
			@Result(property = "fromUserId", column = "from_user_id"),
			@Result(property = "toUserId", column = "to_user_id"),
			@Result(property = "replyTime", column = "reply_time") })
	@Select("<script> "
			+ "select reply_id,reply_type,comment_id,reply_replay_id,reply_content,from_user_id,to_user_id,reply_time "
			+ "from reply where comment_id = #{commentId}" + "</script> ")
	List<ReplyVo> selectReplysByCommentId(@Param("commentId") int commentId);

	/**
	 * 插入回复
	 * 
	 * @param replyVo
	 *            回复实体
	 */
	@Insert("insert into reply (reply_type,comment_id,reply_replay_id,reply_content,from_user_id,to_user_id,reply_time)"
			+ " values(#{replyType},#{commentId},#{replyReplayId},#{replyContent},#{fromUserId},#{toUserId},#{replyTime})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "replyId", keyColumn = "reply_id", resultType = Integer.class, before = false)
	int insertReply(ReplyVo replyVo);

	/**
	 * 更新新回复
	 * 
	 * @param replyVo
	 *            回复实体
	 */
	@Update("<script> update reply <set >" + " <if test='replyType != null' >reply_type = #{replyType},</if>"
			+ " <if test='commentId != null' >comment_id = #{commentId},</if>"
			+ " <if test='replyReplayId != null' >reply_replay_id = #{replyReplayId},</if>"
			+ " <if test='replyContent != null' >reply_content = #{replyContent},</if>"
			+ " <if test='fromUserId != null' >from_user_id = #{fromUserId},</if>"
			+ " <if test='toUserId != null' >to_user_id = #{toUserId},</if>"
			+ " <if test='replyTime != null' >reply_time = #{replyTime},</if>"
			+ " </set> where reply_id = #{replyId}</script>")
	int updateReply(ReplyVo replyVo);
	
	@Delete("delete from reply where reply_id=#{replyId}")
	int delReplyById(int replyId);
	
	@Delete("delete from reply where comment_id=#{commentId}")
	int delReplyByCommentId(int commentId);
}