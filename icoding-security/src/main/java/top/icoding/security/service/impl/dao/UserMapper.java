package top.icoding.security.service.impl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import top.icoding.vo.UserVo;


/**
* @author 我是金角大王
* @date 2017年11月22日 上午9:52:42
*/
@Mapper
public interface UserMapper {
	/**
	 * 根据username获取user
	 * 
	 * @param userName 用户名
	 * @return 返回用户集合
	 * */
	@Results(id = "selectUserByUsername", value = {
			@Result(property = "userId", column = "user_id", id = true),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "userPwd", column = "user_pwd"), 
			@Result(property = "userPhone", column = "user_phone"),
			@Result(property = "userSex", column = "user_sex"),
			@Result(property = "userQq", column = "user_qq"),
			@Result(property = "userWx", column = "user_wx"),
			@Result(property = "userEmail", column = "user_email"),
			@Result(property = "userGithub", column = "user_github"),
			@Result(property = "userAddress", column = "user_address"),
			@Result(property = "userDescription", column = "user_description"),
			@Result(property = "userImageUrl", column = "user_image_url"),
			@Result(property = "userRankId", column = "user_rank_id"),
			@Result(property = "userLock", column = "user_lock")})
	@Select("select user_id, user_name, user_pwd, user_phone, user_sex, user_qq, user_wx, user_email,"
			+ "user_github, user_address, user_description, user_image_url, user_rank_id, user_lock "
			+ "from user where user_name = #{userName}")
	List<UserVo> selectUserByUsername(String userName);
	
	/**
	 * 根据username获取user
	 * 
	 * @param userName 用户名
	 * @return 返回用户集合
	 * */
	@Results(id = "selectUserByUsergithub", value = {
			@Result(property = "userId", column = "user_id", id = true),
			@Result(property = "userName", column = "user_name"),
			@Result(property = "userPwd", column = "user_pwd"), 
			@Result(property = "userPhone", column = "user_phone"),
			@Result(property = "userSex", column = "user_sex"),
			@Result(property = "userQq", column = "user_qq"),
			@Result(property = "userWx", column = "user_wx"),
			@Result(property = "userEmail", column = "user_email"),
			@Result(property = "userGithub", column = "user_github"),
			@Result(property = "userAddress", column = "user_address"),
			@Result(property = "userDescription", column = "user_description"),
			@Result(property = "userImageUrl", column = "user_image_url"),
			@Result(property = "userRankId", column = "user_rank_id"),
			@Result(property = "userLock", column = "user_lock")})
	@Select("select user_id, user_name, user_pwd, user_phone, user_sex, user_qq, user_wx, user_email,"
			+ "user_github, user_address, user_description, user_image_url, user_rank_id, user_lock "
			+ "from user where user_github = #{usergithub}")
	List<UserVo> selectUserByUsergithub(String usergithub);
	
	/**
	 * 创建用户
	 * 
	 * @param user 用户类
	 * @return 返回判断码：0：失败，1：成功
	 * */
	@Insert("insert into user (user_name,user_pwd,user_phone,user_email,user_address,user_description,user_rank_id,user_lock)"
			+ " values(#{userName},#{userPwd},#{userPhone},#{userEmail},#{userAddress},#{userDescription},#{userRankId},#{userLock})")
	@SelectKey(statement = "select LAST_INSERT_ID()", keyProperty = "userId", keyColumn = "user_id", resultType = Integer.class, before = false)
	int insertUser(UserVo user);

}