package top.icoding.service.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import top.icoding.vo.ArticleCommentVo;
import top.icoding.vo.ArticleLikeVo;

@Mapper
public interface ArticleCommentMapper {
	
	@Insert("insert into article_comment (article_id,user_id,comment_content,comment_time) values(#{articleId},#{userId},#{commentContent},#{commentTime})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "commentId", keyColumn = "comment_id", resultType = Integer.class, before = false)
    int insertArticleComment(ArticleLikeVo articlelike);
	
	@Delete("delete from article_comment where like_id = #{likeId}")
	int deleteArticleComment(int likeId);
	
	List<ArticleCommentVo> selectArticleCommentsByArticleIdByPage();
}