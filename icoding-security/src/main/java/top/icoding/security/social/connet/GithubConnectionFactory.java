package top.icoding.security.social.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import top.icoding.security.social.provider.GithubServiceProvider;
import top.icoding.security.social.service.GithubService;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午11:05:40
*/
public class GithubConnectionFactory extends OAuth2ConnectionFactory<GithubService> {

	public GithubConnectionFactory(String providerId,String appId,String appSecret) {
		super(providerId, new GithubServiceProvider(appId,appSecret), new GithubAdapter());
	}

}
