package top.icoding.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import top.icoding.service.ResourcesService;
import top.icoding.util.ReturnMessage;
import top.icoding.util.ReturnMessageType;
import top.icoding.vo.ResourcesVo;

/**
* @author 我是金角大王
* @date 2017年12月31日 下午1:28:24
*/
@RestController
@RequestMapping(value="/resources", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ResourcesController {
	@Autowired
	private ResourcesService resourcesservice;
	
	@PutMapping
	public ReturnMessage insertReesources(@RequestBody ResourcesVo resourceVo){
		try{
			resourcesservice.insertReesources(resourceVo);
		}catch (Exception e) {
			return new ReturnMessage("false");
		}
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), null);
	}
	
	@GetMapping(value="{resourcesId:\\d}")
	public ReturnMessage getResourcesInfo(@PathVariable int resourcesId){
		ResourcesVo resourceVo = resourcesservice.getResourcesInfo(resourcesId);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), resourceVo);
	}
	@GetMapping
	public ReturnMessage getResourcess(@RequestParam(defaultValue = "1") Integer currentPage, Integer pageNumber,Integer sortId){
		Map<String,Object> resourceVos=resourcesservice.getResourcess(currentPage,pageNumber,sortId);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(), resourceVos);
	}
	
	
}
