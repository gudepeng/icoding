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
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();  
        GrantedAuthority au = new SimpleGrantedAuthority("AUTH_LOGIN"); 
        list.add(au);
        
        SessionUser sessionuser=new SessionUser(user.getUserName(),user.getUserPwd(), list);
        sessionuser.setUserId(user.getUserId());
        sessionuser.setDisplayName(user.getUserName());
        sessionuser.setUserPhone(user.getUserPhone());
        sessionuser.setUserSex(user.getUserSex());
        sessionuser.setUserEmail(user.getUserEmail());
        sessionuser.setUserAddress(user.getUserAddress());
        sessionuser.setUserDescription(user.getUserDescription());
        sessionuser.setUserImageUrl(user.getUserImageUrl());
        
		return sessionuser;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userid) throws UsernameNotFoundException {
		List<UserVo> listuser=userservice.getUserBySocialUserid(userid);
		if(listuser.size()==0){
			throw new UsernameNotFoundException("not find userId:"+userid);
		}
		UserVo user=listuser.get(0);
		
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();  
        GrantedAuthority au = new SimpleGrantedAuthority("AUTH_LOGIN"); 
        list.add(au);
        SessionUser sessionuser=new SessionUser(userid,"password", list);
        sessionuser.setUserId(userid);
        sessionuser.setDisplayName(user.getUserName());
        sessionuser.setUserImageUrl(user.getUserImageUrl());
		return sessionuser;
	}

}
