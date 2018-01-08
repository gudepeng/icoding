package top.icoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.icoding.service.SortService;
import top.icoding.service.impl.dao.SortMapper;
import top.icoding.vo.SortVo;

/**
* @author 我是金角大王
* @date 2018年1月6日 下午9:29:32
*/
@Service
public class SortServiceImpl implements SortService {
	@Autowired
	public SortMapper sortmapper;
	
	@Override
	public List<SortVo> getSortByType(String sortType) {
		return sortmapper.selectSortByType(sortType);
	}

}
