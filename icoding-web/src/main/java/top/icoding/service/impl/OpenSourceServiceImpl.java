package top.icoding.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.icoding.service.OpenSourceService;
import top.icoding.service.impl.dao.OpenSourceMapper;
import top.icoding.util.Page;

/**
* @author 我是金角大王
* @date 2018年6月13日 下午1:48:36
*/
@Service
public class OpenSourceServiceImpl implements OpenSourceService {
	@Autowired
	private OpenSourceMapper opensourcemapper;
	
	@Override
	public Map<String, Object> getOpenSourceList(Integer currentPage, Integer pageNumber) {
		Page page = new Page();
		page.setCurrentPage(currentPage);
		if(pageNumber!=null){
			page.setPageNumber(pageNumber);
		}
		Map<String,Object> map = new HashMap<>();
		map.put("page", page);
		map.put("data", opensourcemapper.selectOpenSourcesByPage(map));
		return map;
	}

}
