package top.icoding.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.icoding.exception.ArticleException;
import top.icoding.service.ArticleService;
import top.icoding.util.ReturnMessage;
import top.icoding.vo.ArticleVo;

/**
 * @author 我是金角大王
 * @date 2017年11月20日 下午3:00:04
 */
@RestController
@RequestMapping(value = "/article", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleController {
	
	@Autowired
	private ArticleService articleservice;

	@ApiOperation(value = "文章模块", notes = "根据文章分类和用户主键获取分页文章列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage", value = "当前第几页", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "每页多少条", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sortId", value = "文章分类", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户主键", required = false, dataType = "int", paramType = "query")})
	@GetMapping
	public ReturnMessage getArticleList(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageNumber,
			Integer sortId,Integer userId) {
		Map<String, Object> articles = articleservice.getArticles(currentPage, pageNumber, sortId, userId);
		return new ReturnMessage("true", articles);
	}

	@ApiOperation(value = "文章模块", notes = "根据id获取文章", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "int", paramType = "path")
	@GetMapping("/{articleId:\\d}")
	public ReturnMessage getArticleInfo(@PathVariable("articleId") int articleId) {
			ArticleVo articles = articleservice.getArticleInfoById(articleId);
			return new ReturnMessage("true", articles);
	}

	@ApiOperation(value = "文章模块", notes = "根据id删除文章", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "int", paramType = "path")
	@DeleteMapping("/{articleId:\\d}")
	public ReturnMessage delArticle(@PathVariable("articleId") int articleId) {
		try {
			articleservice.delArticleById(articleId);
		} catch (ArticleException e) {
			return new ReturnMessage("false");
		}

		return new ReturnMessage("true", null);
	}

	@ApiOperation(value = "文章模块", notes = "添加或修改文章", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PutMapping
	public ReturnMessage insertAndUpdateArticle(@RequestBody ArticleVo articleVo) {
		SocialUserDetails userDetails = (SocialUserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		//articleVo.setUserId(userDetails.getUserId());
		articleservice.insertAndUpdateArticle(articleVo);
		return new ReturnMessage("true", null);
	}
}
