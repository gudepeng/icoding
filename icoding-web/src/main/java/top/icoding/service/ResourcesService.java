package top.icoding.service;

import java.util.List;

import top.icoding.vo.ResourcesVo;

/**
* @author 我是金角大王
* @date 2017年12月31日 下午4:50:57
*/
public interface ResourcesService {
	
	void insertReesources(ResourcesVo resourceVo);
	
	ResourcesVo getResourcesInfo(int resourcesId);
	
	List<ResourcesVo> getResourcess(Integer sortId);
}
