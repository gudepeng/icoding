package top.icoding.security.social.service.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.databind.ObjectMapper;

import top.icoding.security.social.service.GithubService;
import top.icoding.security.social.vo.GithubUser;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午10:30:07
*/
public class GithubServiceImpl extends AbstractOAuth2ApiBinding implements GithubService {
	
	private static final String URL_GET_USERINFO = "https://api.github.com/user";
	private ObjectMapper objectmapper = new ObjectMapper();
	
	public GithubServiceImpl(String accessToken){
		super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
	}
	
	@Override
	public GithubUser getGithubUserInfo(){
		String result = getRestTemplate().getForObject(URL_GET_USERINFO, String.class);
		GithubUser userinfo = null;
		try {
			userinfo = objectmapper.readValue(result, GithubUser.class);
			return userinfo;
		} catch (Exception e) {
			throw new RuntimeException("获取个人信息失败");
		}
	}

}
