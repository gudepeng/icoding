package top.icoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import top.icoding.service.SortService;
import top.icoding.util.ReturnMessage;
import top.icoding.util.ReturnMessageType;
import top.icoding.vo.SortVo;

/**
* @author 我是金角大王
* @date 2018年1月6日 下午9:20:29
*/
@RestController
@RequestMapping(value = "/sort", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SortController {
	@Autowired
	public SortService sortservice;
	
	@GetMapping("/{sortType}")
	public ReturnMessage getSortByType(@PathVariable("sortType") String sortType){
		List<SortVo> sortVos = sortservice.getSortByType(sortType);
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(),sortVos);
	}
}
