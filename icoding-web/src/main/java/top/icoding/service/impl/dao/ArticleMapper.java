package top.icoding.service.impl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import top.icoding.vo.ArticleVo;

/**
 * @author 我是金角大王
 * @date 2017年11月20日 下午3:00:04
 */
@Mapper
public interface ArticleMapper {
	/**
	 * 获取分页文章列表
	 * 
	 * @param map
	 *            分页需要参数
	 * @return 分页列表
	 */
	@Results(id = "selectArticlesBySortIdByPage", value = {
			@Result(property = "articleId", column = "article_id", id = true),
			@Result(property = "articleTitle", column = "article_title"),
			@Result(property = "articleTag", column = "article_tag"), @Result(property = "sortId", column = "sort_id"),
			@Result(property = "articleSummary", column = "article_summary"),
			@Result(property = "articleTitleimage", column = "article_titleimage"),
			@Result(property = "userId", column = "user_id"), 
			@Result(property = "articleTime", column = "article_time"),
			@Result(property = "articleClick", column = "article_click"),
			@Result(property = "articleLike", column = "article_like"),
			@Result(property = "articleComment", column = "article_comment"),
			@Result(property = "articleUp", column = "article_up"),
			@Result(property = "likeId", column = "like_id")})
	@Select("<script> " + "select like_id,a1.article_id,article_title,article_tag,sort_id,article_summary,article_titleimage"
			+ ",a1.user_id,article_time,article_click,article_like,article_comment,article_up "
			+ "from article a1 LEFT JOIN (select * from article_like where <choose>"
			+ "<when test='likeUserId != null'> user_id=#{likeUserId} </when><otherwise> user_id='' </otherwise></choose>) "
			+ "a2 ON a1.article_id = a2.article_id "
			+ " where 1 = 1 " + "	<if test='sortId != null'> " + "		and sort_id = #{sortId} "
			+ " </if> " + "	<if test='userId != null'> " + " and a1.user_id = #{userId} " + " </if> "
			+ " order by article_time desc" + "</script> ")
	List<ArticleVo> selectArticlesBySortIdByPage(Map map);

	/**
	 * 获取文章信息
	 * 
	 * @param articleId
	 *            文章主键
	 * @return 文章信息
	 */
	@Results(id = "selectByPrimaryKey", value = { @Result(property = "articleId", column = "article_id", id = true),
			@Result(property = "articleTitle", column = "article_title"),
			@Result(property = "articleTag", column = "article_tag"), @Result(property = "sortId", column = "sort_id"),
			@Result(property = "articleSummary", column = "article_summary"),
			@Result(property = "articleTitleimage", column = "article_titleimage"),
			@Result(property = "articleContent", column = "article_content"),
			@Result(property = "userId", column = "user_id"), 
			@Result(property = "articleTime", column = "article_time"),
			@Result(property = "articleClick", column = "article_click"),
			@Result(property = "articleLike", column = "article_like"),
			@Result(property = "articleComment", column = "article_comment"),
			@Result(property = "articleUp", column = "article_up") })
	@Select("<script> " + "select article_id,article_title,article_tag,sort_id,article_summary,article_titleimage"
			+ ",article_content,user_id,article_time,article_click,article_like,article_comment,article_up "
			+ "from article where article_id = #{articleId} " + "</script> ")
	ArticleVo selectByPrimaryKey(int articleId);

	@Update("update article set article_click=article_click+1 where article_id=#{articleId}")
	int addArticleClickByPrimaryKey(int articleId);

	@Update("update article set article_like=article_like+1 where article_id=#{articleId}")
	int addArticleLikeByPrimaryKey(int articleId);

	@Update("update article set article_like=article_like-1 where article_id=#{articleId}")
	int cutArticleLikeByPrimaryKey(int articleId);

	@Delete("delete from article where article_id=#{articleId}")
	int delArticleById(int articleId);

	@Insert("insert into article (article_title,article_tag,sort_id,article_summary,article_titleimage"
			+ ",user_id,article_time,article_click,article_like,article_comment,article_up,article_content) "
			+ "values(#{articleTitle},#{articleTag},#{sortId},#{articleSummary},#{articleTitleimage}"
			+ ",#{userId},#{articleTime},#{articleClick},#{articleLike},#{articleComment},#{articleUp},#{articleContent})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "articleId", keyColumn = "article_id", resultType = Integer.class, before = false)
	int insertArticle(ArticleVo articleVo);

	@Update("<script> update article <set >"
			+ " <if test='articleTitle != null' >article_title = #{articleTitle},</if>"
			+ " <if test='articleTag != null' >article_tag = #{articleTag},</if>"
			+ " <if test='sortId != null' >sort_id = #{sortId},</if>"
			+ " <if test='articleSummary != null' >article_summary = #{articleSummary},</if>"
			+ " <if test='articleTitleimage != null' >article_titleimage = #{articleTitleimage},</if>"
			+ " <if test='articleTime != null' >article_time = #{articleTime},</if>"
			+ " <if test='articleUp != null' >article_up = #{articleUp},</if>"
			+ " <if test='articleContent != null' >article_content = #{articleContent},</if>"
			+ " </set> where article_id = #{articleId}</script>")
	int updateArticle(ArticleVo articleVo);
}