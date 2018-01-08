package top.icoding.service;

import java.util.List;

import top.icoding.vo.SortVo;

/**
* @author 我是金角大王
* @date 2018年1月6日 下午9:25:18
*/
public interface SortService {
	List<SortVo> getSortByType(String sortType);
}
