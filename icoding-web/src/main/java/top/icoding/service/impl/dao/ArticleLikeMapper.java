package top.icoding.service.impl.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

import top.icoding.vo.ArticleLikeVo;

@Mapper
public interface ArticleLikeMapper {
	
	@Insert("insert into article_like (article_id,user_id,like_time) values(#{articleId},#{userId},#{likeTime})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "likeId", keyColumn = "like_id", resultType = Integer.class, before = false)
    int insertArticleLike(ArticleLikeVo articlelike);
	
	@Delete("delete from article_like where like_id = #{likeId}")
	int deleteArticleLike(int likeId);
	
	@Delete("delete from article_like where article_id = #{articleId}")
	int deleteArticleLikeByArticleId(int articleId);
}