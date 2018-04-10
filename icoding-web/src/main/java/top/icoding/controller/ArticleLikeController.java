package top.icoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.icoding.exception.ArticleException;
import top.icoding.security.util.SessionUser;
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

	@ApiOperation(value = "点赞功能", notes = "添加文章点赞", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章主键", required = true, dataType = "int", paramType = "query")
	@PutMapping("/{articleId:^\\d+$}")
	public ReturnMessage like(@PathVariable("articleId") int articleId) {
		SessionUser userDetails = (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		try {
			articlelikeservice.like(articleId, userDetails.getUserId());
			return new ReturnMessage("点赞成功");
		} catch (ArticleException e) {
			return new ReturnMessage("点赞失败");
		}catch (Exception e) {
			return new ReturnMessage("点赞失败");
		}
	}

	@ApiOperation(value = "点赞功能", notes = "删除文章点赞", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章主键", required = true, dataType = "int", paramType = "query")
	@DeleteMapping("/{articleId:^\\d+$}")
	public ReturnMessage unlike(@PathVariable("articleId") int articleId) {
		SessionUser userDetails = (SessionUser) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		try {
			articlelikeservice.unlike(articleId,userDetails.getUserId());
			return new ReturnMessage("取消点赞成功", null);
		} catch (ArticleException e) {
			return new ReturnMessage("取消点赞失败");
		}
	}
	
}
