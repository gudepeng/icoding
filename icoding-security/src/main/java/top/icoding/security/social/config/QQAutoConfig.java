package top.icoding.security.social.config;

import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import top.icoding.security.social.connet.QQConnectionFactory;

/**
 * @date 2018年1月2日 上午9:02:22
 */
@Configuration
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new QQConnectionFactory("qq", "8347be17a991df62767f",
				"9f502ba54524fe6f7977db90b8ed38d887adf7d0");
	}

}
