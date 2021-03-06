package top.icoding.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.icoding.exception.ArticleException;
import top.icoding.security.util.SessionUser;
import top.icoding.service.ArticleService;
import top.icoding.service.impl.RedisServiceImpl;
import top.icoding.util.ReturnMessage;
import top.icoding.util.ReturnMessageType;
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
	@Autowired
	private RedisServiceImpl redisserviceimpl;

	@ApiOperation(value = "文章模块", notes = "根据文章分类和用户主键获取分页文章列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage", value = "当前第几页", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "每页多少条", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "sortId", value = "文章分类", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "userId", value = "用户主键", required = false, dataType = "string", paramType = "query") })
	@GetMapping
	public ReturnMessage getArticleList(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageNumber,
			Integer sortId, String userId) {
		Map<String, Object> articles = articleservice.getArticles(currentPage, pageNumber, sortId, userId);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), articles);
	}

	@ApiOperation(value = "文章模块", notes = "获取我的文章和我的喜欢", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage", value = "当前第几页", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "每页多少条", required = false, dataType = "int", paramType = "query") })
	@GetMapping("/self/{type}")
	public ReturnMessage getSelfOrLikeArticleList(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageNumber,
			@PathVariable("type") String type) {
		Map<String, Object> articles = articleservice.getSelfOrLikeArticles(currentPage, pageNumber, type);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), articles);
	}

	@ApiOperation(value = "文章模块", notes = "根据id获取文章", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "int", paramType = "path")
	@GetMapping("/{articleId:^\\d+$}")
	public ReturnMessage getArticleInfo(@PathVariable("articleId") int articleId) {
		ArticleVo articles = articleservice.getArticleInfoById(articleId);
		Map<String, Object> map = new HashMap<>();
		map.put("id", articles.getArticleId());
		map.put("name", articles.getArticleTitle());
		getHotArticle();
		redisserviceimpl.IncrementScore(LocalDate.now().toString(), map, -1);
		redisserviceimpl.IncrementScore(LocalDate.now().minusDays(6) + "-" + LocalDate.now(), map, -1);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), articles);
	}

	@ApiOperation(value = "文章模块", notes = "根据id删除文章", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "int", paramType = "path")
	@DeleteMapping("/{articleId:^\\d+$}")
	public ReturnMessage delArticle(@PathVariable("articleId") int articleId) {
		try {
			articleservice.delArticleById(articleId);
		} catch (ArticleException e) {
			return new ReturnMessage(ReturnMessageType.FAILUER.msg());
		}

		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), null);
	}

	@ApiOperation(value = "文章模块", notes = "添加或修改文章", httpMethod = "PUT", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping
	public ReturnMessage insertAndUpdateArticle(@RequestBody ArticleVo articleVo, HttpServletRequest request) {
		SessionUser userDetails = (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		articleVo.setUserId(userDetails.getUserId());
		articleservice.insertAndUpdateArticle(articleVo);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), articleVo.getArticleId());
	}

	@ApiOperation(value = "文章模块", notes = "获取热门文章", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@GetMapping("/hot")
	public ReturnMessage getHotArticle() {
		String hotsName = LocalDate.now().minusDays(6) + "-" + LocalDate.now();
		if (redisserviceimpl.exists(hotsName)) {
			Set<Object> hotList = redisserviceimpl.zRange(hotsName, 0, 9);
			return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), hotList);
		} else {
			List<String> hosts = new ArrayList<>();
			for (int i = 0; i < 7; i++) {
				hosts.add(LocalDate.now().minusDays(i).toString());
			}
			redisserviceimpl.unionStore(hosts, hotsName);
			return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), redisserviceimpl.zRange(hotsName, 0, 9));

		}
	}
}
