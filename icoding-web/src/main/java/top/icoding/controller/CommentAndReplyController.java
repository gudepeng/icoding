package top.icoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.icoding.exception.CommentAndReplyExcption;
import top.icoding.security.util.SessionUser;
import top.icoding.service.CommentAndReplyService;
import top.icoding.util.ReturnMessage;
import top.icoding.util.ReturnMessageType;
import top.icoding.vo.CommentVo;
import top.icoding.vo.ReplyVo;

/**
 * @author 我是金角大王
 * @date 2018年4月16日 下午5:25:51
 */

@RestController
public class CommentAndReplyController {
	@Autowired
	private CommentAndReplyService commentandreplyservice;

	@ApiOperation(value = "评论回复功能", notes = "获取评论列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "topicType", value = "评论类别", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "topicId", value = "评论主键", required = true, dataType = "int", paramType = "path") })
	@GetMapping("/comment/{topicType}/{topicId:^\\d+$}")
	public ReturnMessage getComments(@PathVariable("topicType") String topicType, @PathVariable("topicId") int topicId) {
		List<CommentVo> comments = commentandreplyservice.getCommentsById(topicType, topicId);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), comments);
	}

	@ApiOperation(value = "评论回复功能", notes = "删除评论", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@DeleteMapping("/comment/{commentId:^\\d+$}")
	public ReturnMessage deleteComment(@PathVariable("commentId") int commentId) {
		try{
			commentandreplyservice.deleteComment(commentId);
			return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), null);
		}catch(CommentAndReplyExcption e){
			return new ReturnMessage(ReturnMessageType.FAILUER.msg());
		}
		
	}

	@ApiOperation(value = "评论回复功能", notes = "添加评论", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PutMapping("/comment")
	public ReturnMessage insertOrUpdateComment(@RequestBody CommentVo commentVo) {
		SessionUser userDetails = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		commentVo.setFromUserId(userDetails.getUserId());
		commentandreplyservice.insertOrUpdateComment(commentVo);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), commentVo);
	}
	
	@ApiOperation(value = "评论回复功能", notes = "添加回复", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PutMapping("/reply")
	public ReturnMessage insertOrUpdateReply(@RequestBody ReplyVo replyVo) {
		SessionUser userDetails = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		replyVo.setFromUserId(userDetails.getUserId());
		try{
			commentandreplyservice.insertOrUpdateReply(replyVo);
			return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), null);
		}catch(CommentAndReplyExcption e){
			return new ReturnMessage(ReturnMessageType.FAILUER.msg());
		}
	}

	@ApiOperation(value = "评论回复功能", notes = "删除回复", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@DeleteMapping("/reply/{replyId:^\\d+$}")
	public ReturnMessage deleteReply(@PathVariable("replyId") int replyId) {
		commentandreplyservice.deleteReply(replyId);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), null);
	}
	
	@ApiOperation(value = "评论回复功能", notes = "获取回复列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "commentId", value = "评论主键", required = true, dataType = "int", paramType = "query")
	@GetMapping("/reply")
	public ReturnMessage getReplys(int commentId) {
		List<ReplyVo> replys = commentandreplyservice.getReplysByCommentId(commentId);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), replys);
	}
}
