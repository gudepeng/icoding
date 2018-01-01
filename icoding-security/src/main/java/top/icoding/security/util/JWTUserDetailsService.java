package top.icoding.security.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import top.icoding.security.service.UserService;
import top.icoding.security.vo.UserVo;

@Service
public class JWTUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserVo> listuser=userservice.getUserByUsername(username);
		if(listuser.size()==0){
			throw new UsernameNotFoundException("not find user:"+username);
		}
		UserVo user=listuser.get(0);
		return new User(user.getUserName(),user.getUserPwd(), new ArrayList<GrantedAuthority>());
	}

}
