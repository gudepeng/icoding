package top.icoding.security.service;

import java.util.List;

import top.icoding.security.social.vo.GithubUser;
import top.icoding.security.vo.UserVo;


/**
* @author 我是金角大王
* @date 2017年11月22日 上午9:51:45
*/
public interface UserService {
	/**
	 * 根据username获取user
	 * 
	 * @param userName 用户名
	 * @return 返回用户集合
	 * */
	List<UserVo> getUserByUsername(String userName);
	/**
	 * 根据usergithub获取user
	 * 
	 * @param userName 用户名
	 * @return 返回用户集合
	 * */
	UserVo getUserByUsergithub(GithubUser usergithub);
	/**
	 * 根据usergithub获取user
	 * 
	 * @param userName 用户名
	 * @return 返回用户集合
	 * */
	UserVo getUserByUserid(int userId);
	/**
	 * 创建用户
	 * 
	 * @param user 用户类
	 * @return 返回判断码：0：失败，1：成功
	 * */
	int insertUser(UserVo user);
}
