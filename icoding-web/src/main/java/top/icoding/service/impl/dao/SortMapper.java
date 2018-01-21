package top.icoding.service.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import top.icoding.vo.SortVo;

/**
 * @author 我是金角大王
 * @date 2017年11月20日 下午3:00:04
 */
@Mapper
public interface SortMapper {

	@Results(id = "selectSortByType", value = { @Result(property = "sortId", column = "sort_id", id = true),
			@Result(property = "sortType", column = "sort_type"),
			@Result(property = "sortValue", column = "sort_value"),
			@Result(property = "sortName", column = "sort_name")})
	@Select("select sort_id,sort_type,sort_value,sort_name from sort where sort_type = #{sortType}")
	List<SortVo> selectSortByType(String sortType);
}