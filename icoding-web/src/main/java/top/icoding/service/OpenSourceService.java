package top.icoding.service;

import java.util.Map;

/**
 * @author 我是金角大王
 * @date 2018年6月13日 下午1:46:36
 */
public interface OpenSourceService {
	/**
	 * 获取开源项目列表
	 * 
	 * @param currentPage 第几页
	 * @param pageNumber  每也多少条
	 * @return 返回开源项目列表和分页信息
	 * */
	public Map<String, Object> getOpenSourceList(Integer currentPage, Integer pageNumber);
}
