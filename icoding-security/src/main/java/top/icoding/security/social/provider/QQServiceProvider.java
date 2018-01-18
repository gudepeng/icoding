package top.icoding.security.social.provider;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import top.icoding.security.social.connet.QQOAuth2Template;
import top.icoding.security.social.service.QQService;
import top.icoding.security.social.service.impl.QQServiceImpl;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午10:45:20
*/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQService> {
	String appId;
	
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

	public QQServiceProvider(String appId,String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId= appId;
	}
	
	@Override
	public QQService getApi(String accessToken) {
		return new QQServiceImpl(accessToken, appId);
	}

}
