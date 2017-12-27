package top.icoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.icoding.exception.ArticleException;
import top.icoding.service.ArticleLikeService;
import top.icoding.util.ReturnMessage;

/**
 * @author 我是金角大王
 * @date 2017年11月28日 上午9:43:10
 */
@RestController
@RequestMapping(value = "/like", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ArticleLikeController {
	@Autowired
	private ArticleLikeService articlelikeservice;
	
	@ApiOperation(value = "点赞功能", notes = "文章添加点赞", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "文章主键", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户主键", required = true, dataType = "int", paramType = "query") })
	@PutMapping
	public ReturnMessage like(@RequestParam(value = "articleId", required = true) Integer articleId,
			@RequestParam(value = "userId", required = true) Integer userId){
		try{
			int id = articlelikeservice.like(articleId, userId);
			return new ReturnMessage("点赞成功",id);
		}catch(ArticleException e){
			return new ReturnMessage("点赞失败");
		}
	}
	
	@ApiOperation(value = "点赞功能", notes = "文章删除点赞", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "articleId", value = "文章主键", required = true, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "likeId", value = "点赞主键", required = true, dataType = "int", paramType = "query") })
	@DeleteMapping
	public ReturnMessage unlike(@RequestParam(value = "articleId", required = true) Integer articleId,
			@RequestParam(value = "likeId", required = true) Integer likeId) {
		try{
			articlelikeservice.unlike(articleId,likeId);
			return new ReturnMessage("取消点赞成功",null);
		}catch(ArticleException e){
			return new ReturnMessage("取消点赞失败");
		}
	}
}
