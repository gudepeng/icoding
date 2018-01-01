package top.icoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.icoding.service.ResourcesService;
import top.icoding.service.impl.dao.ResourcesMapper;
import top.icoding.vo.ResourcesVo;

/**
* @author 我是金角大王
* @date 2017年12月31日 下午5:19:36
*/
@Service
public class ResourcesServiceImpl implements ResourcesService {
	@Autowired
	private ResourcesMapper resourcesmapper;
	
	@Override
	public void insertReesources(ResourcesVo resourceVo) {
		resourcesmapper.insertResources(resourceVo);
	}

	@Override
	public ResourcesVo getResourcesInfo(int resourcesId) {
		return resourcesmapper.selectResourcesInfo(resourcesId);
	}

	@Override
	public List<ResourcesVo> getResourcess(Integer sortId) {
		return resourcesmapper.selectResourcess(sortId);
	}

}
