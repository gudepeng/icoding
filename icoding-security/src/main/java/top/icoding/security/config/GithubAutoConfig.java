package top.icoding.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import top.icoding.security.social.connet.GithubConnectionFactory;

/**
 * @author 我是金角大王
 * @date 2018年1月2日 上午9:02:22
 */
@Configuration
public class GithubAutoConfig extends SocialAutoConfigurerAdapter {

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new GithubConnectionFactory("github", "8347be17a991df62767f",
				"9f502ba54524fe6f7977db90b8ed38d887adf7d0");
	}

}
