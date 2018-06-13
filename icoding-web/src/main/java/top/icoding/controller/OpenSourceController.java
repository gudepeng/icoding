package top.icoding.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import top.icoding.service.OpenSourceService;
import top.icoding.util.ReturnMessage;
import top.icoding.util.ReturnMessageType;

/**
* @author 我是金角大王
* @date 2018年6月13日 下午1:42:45
*/
@RestController
@RequestMapping(value = "/opensource", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class OpenSourceController {
	@Autowired
	private OpenSourceService opensourceservice;
	
	@ApiOperation(value = "开源项目", notes = "获取分页开源项目列表", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "currentPage", value = "当前第几页", required = false, dataType = "int", paramType = "query"),
			@ApiImplicitParam(name = "pageNumber", value = "每页多少条", required = false, dataType = "int", paramType = "query")})
	@GetMapping
	public ReturnMessage getOpenSourceList(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageNumber) {
		Map<String, Object> opensources = opensourceservice.getOpenSourceList(currentPage, pageNumber);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), opensources);
	}
}
