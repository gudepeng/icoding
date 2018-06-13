package top.icoding.service.impl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import top.icoding.vo.OpenSourceVo;

/**
* @author 我是金角大王
* @date 2018年6月13日 下午1:50:46
*/
@Mapper
public interface OpenSourceMapper {
	
	/**
	 * 获取分页开源项目列表
	 * 
	 * @param map
	 *            分页需要参数
	 * @return 分页列表
	 */
	@Results(id = "selectOpenSourcesByPage", value = {
			@Result(property = "osId", column = "os_id", id = true),
			@Result(property = "osTitle", column = "os_title"),
			@Result(property = "osSummary", column = "os_summary"), 
			@Result(property = "osUrl", column = "os_url")})
	@Select("<script> " + "select os_id,os_title,os_summary,os_url from opensource order by os_id"
			+ "</script> ")
	List<OpenSourceVo> selectOpenSourcesByPage(Map map);
}
