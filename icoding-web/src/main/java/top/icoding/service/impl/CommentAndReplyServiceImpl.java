package top.icoding.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.icoding.exception.CommentAndReplyExcption;
import top.icoding.service.CommentAndReplyService;
import top.icoding.service.impl.dao.CommentMapper;
import top.icoding.service.impl.dao.ReplyMapper;
import top.icoding.vo.CommentVo;
import top.icoding.vo.ReplyVo;

/**
* @author 我是金角大王
* @date 2018年4月16日 下午5:32:31
*/
@Service
public class CommentAndReplyServiceImpl implements CommentAndReplyService {
	@Autowired
	private CommentMapper commentmapper;
	
	@Autowired
	private ReplyMapper replymapper;
	
	@Override
	public List<CommentVo> getCommentsById(String topicType,Integer topicId) {
		return commentmapper.selectCommentsById(topicType, topicId);
	}

	@Override
	public void insertOrUpdateComment(CommentVo commentVo) {
		commentVo.setCommentTime(new Date());
		if(commentVo.getCommentId()==null){
			commentVo.setCommentReply(0);
			commentmapper.insertComment(commentVo);
		}else{
			commentmapper.updateComment(commentVo);
		}
	}

	@Override
	public void insertOrUpdateReply(ReplyVo replyVo) {
		replyVo.setReplyTime(new Date());
		if(replyVo.getReplyId()==null){
			if(commentmapper.addCommentReplyByCommentId(replyVo.getCommentId()) == 0){
				throw new CommentAndReplyExcption();
			}
			replymapper.insertReply(replyVo);
		}else{
			replymapper.updateReply(replyVo);
		}
	}

	@Override
	public void deleteComment(int commentId) {
		if(commentmapper.delCommentById(commentId) == 0){
			throw new CommentAndReplyExcption();
		}
		replymapper.delReplyByCommentId(commentId);
	}

	@Override
	public void deleteReply(int replyId) {
		replymapper.delReplyById(replyId);
	}

	@Override
	public List<ReplyVo> getReplysByCommentId(int commentId) {
		return replymapper.selectReplysByCommentId(commentId);
	}

}
