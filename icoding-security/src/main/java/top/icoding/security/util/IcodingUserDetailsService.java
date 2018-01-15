package top.icoding.security.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

import top.icoding.security.service.UserService;
import top.icoding.security.vo.UserVo;

@Service
public class IcodingUserDetailsService implements UserDetailsService,SocialUserDetailsService {
	@Autowired
	private UserService userservice;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<UserVo> listuser=userservice.getUserByUsername(username);
		if(listuser.size()==0){
			throw new UsernameNotFoundException("not find user:"+username);
		}
		UserVo user=listuser.get(0);
		return new SocialUser(user.getUserName(),user.getUserPwd(), new ArrayList<GrantedAuthority>());
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userid) throws UsernameNotFoundException {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();  
        GrantedAuthority au = new SimpleGrantedAuthority("AUTH_LOGIN"); 
        list.add(au);
		return new SocialUser(userid,"password", list);
	}

}
