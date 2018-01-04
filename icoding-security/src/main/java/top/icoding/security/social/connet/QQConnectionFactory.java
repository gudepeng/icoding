package top.icoding.security.social.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import top.icoding.security.social.provider.QQServiceProvider;
import top.icoding.security.social.service.QQService;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午11:05:40
*/
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQService> {

	public QQConnectionFactory(String providerId,String appId,String appSecret) {
		super(providerId, new QQServiceProvider(appId,appSecret), new QQAdapter());
	}

}
