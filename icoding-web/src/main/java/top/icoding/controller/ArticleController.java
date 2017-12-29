package top.icoding.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@ApiOperation(value = "获取文章列表", notes = "根据文章分类和用户主键获取分页文章列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage", value = "当前第几页", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "每页多少条", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sortId", value = "文章分类", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户主键", required = false, dataType = "int", paramType = "query") })
	@GetMapping
	public ReturnMessage getArticles(@RequestParam(value = "currentPage", required = true) int currentPage,
			@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
			@RequestParam(value = "sortId", required = false) Integer sortId,
			@RequestParam(value = "userId", required = false) Integer userId) {
		Map<String, Object> articles = articleservice.getArticles(currentPage, pageNumber, sortId, userId);
		return new ReturnMessage("true", articles);
	}

	@ApiOperation(value = "获取文章", notes = "根据id获取文章", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "int", paramType = "path")
	@GetMapping("/{articleId:\\d}")
	public ReturnMessage getArticle(@PathVariable("articleId") int articleId) {
		ArticleVo articles = articleservice.getArticleById(articleId);
		return new ReturnMessage("true", articles);
	}
	
	@ApiOperation(value = "获取文章", notes = "根据id删除文章", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "int", paramType = "path")
	@DeleteMapping("/{articleId:\\d}")
	public ReturnMessage delArticles(@PathVariable("articleId") int articleId) {
		try{
			articleservice.delArticleById(articleId);
		}catch(ArticleException e){
			return new ReturnMessage("false");
		}
		
		return new ReturnMessage("true", null);
	}
}
