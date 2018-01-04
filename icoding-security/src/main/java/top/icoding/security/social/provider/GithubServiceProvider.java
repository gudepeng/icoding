package top.icoding.security.social.provider;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

import top.icoding.security.social.service.GithubService;
import top.icoding.security.social.service.impl.GithubServiceImpl;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午10:45:20
*/
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<GithubService> {
	
	private static final String URL_AUTHORIZE = "https://github.com/login/oauth/authorize";
	
	private static final String URL_ACCESS_TOKEN = "https://github.com/login/oauth/access_token";

	public GithubServiceProvider(String clientId,String clientSecret) {
		super(new OAuth2Template(clientId, clientSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public GithubService getApi(String accessToken) {
		return new GithubServiceImpl(accessToken);
	}

}
