package top.icoding.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.icoding.security.service.UserService;
import top.icoding.security.service.impl.dao.UserMapper;
import top.icoding.security.social.vo.GithubUser;
import top.icoding.security.vo.UserVo;
import top.icoding.util.Const;

/**
* @author 我是金角大王
* @date 2017年11月22日 上午9:52:42
*/
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper usermapper;
	
	@Override
	public List<UserVo> getUserByUsername(String userName) {
		return usermapper.selectUserByUsername(userName);
	}
	
	
	@Override
	public int insertUser(UserVo user) {
		return usermapper.insertUser(user);
	}

	@Override
	public UserVo getUserByUserid(int userId) {
		List<UserVo> users = usermapper.selectUserByUserid(userId);
		if(users.size()==0){
			return null;
		}
		return users.get(0);
	}


	@Override
	public List<UserVo> getUserBySocialUserid(String userId) {
		return usermapper.selectSocialUserByUserId(userId);
	}

}
