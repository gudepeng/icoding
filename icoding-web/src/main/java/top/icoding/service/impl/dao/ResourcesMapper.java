package top.icoding.service.impl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import top.icoding.vo.ResourcesVo;

/**
 * @author 我是金角大王
 * @date 2017年11月20日 下午3:00:04
 */
@Mapper
public interface ResourcesMapper {
	@Insert("insert into resources (resources_name,resource_description,sort_id,resource_integral,resources_url,resources_password,create_time"
			+ "values(#{resourcesName},#{resourceDescription},#{sortId},#{resourceIntegral},#{resourcesUrl},#{resourcesPassword},#{createTime})")
	int insertResources(ResourcesVo resourceVo);

	@Results(id = "selectResourcesInfo", value = { @Result(property = "resourcesId", column = "resources_id", id = true),
			@Result(property = "resourcesName", column = "resources_name"),
			@Result(property = "resourceDescription", column = "resource_description"),
			@Result(property = "sortId", column = "sort_id"),
			@Result(property = "resourceIntegral", column = "resource_integral"),
			@Result(property = "resourcesUrl", column = "resources_url"),
			@Result(property = "resourcesPassword", column = "resources_password"),
			@Result(property = "createTime", column = "create_time") })
	@Select("select resources_id,resources_name,resource_description,sort_id,resource_integral,resources_url,resources_password,create_time"
			+ "from resources where resources_id = #{resourcesId} ")
	ResourcesVo selectResourcesInfo(int resourcesId);

	@Results(id = "selectResourcess", value = { @Result(property = "resourcesId", column = "resources_id", id = true),
			@Result(property = "resourcesName", column = "resources_name"),
			@Result(property = "resourceDescription", column = "resource_description"),
			@Result(property = "sortId", column = "sort_id"),
			@Result(property = "resourceIntegral", column = "resource_integral"),
			@Result(property = "resourcesUrl", column = "resources_url"),
			@Result(property = "resourcesPassword", column = "resources_password"),
			@Result(property = "createTime", column = "create_time") })
	@Select("<script> " + "select resources_id,resources_name,resource_description,sort_id,resource_integral,resources_url,resources_password,create_time"
			+ " from share_resource  where 1 = 1 " + "	<if test='sortId != null'> " 
			+ " and sort_id = #{sortId} "+"</if>" + " order by create_time desc</script>")
	List<ResourcesVo> selectResourcessBySortIdByPage(Map map);
}