package top.icoding.security.social;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.web.servlet.View;

/**
 * @author 我是金角大王
 * @date 2018年1月1日 下午6:38:17
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private ConnectionSignUp connectionSignUp;


	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
				connectionFactoryLocator, Encryptors.noOpText());
		repository.setConnectionSignUp(connectionSignUp);
		return repository;
	}

	@Bean
	public SpringSocialConfigurer icodingSpringSocialConfigurer() {
		return new SpringSocialConfigurer();
	}
	
	@Bean({"connect/githubConnected","connect/githubConnect"})
	public View IcodingGithubConnectView(){
		return new IcodingConnectView();
	}
	
	@Bean({"connect/qqConnected","connect/qqConnect"})
	public View IcodingQqConnectView(){
		return new IcodingConnectView();
	}
}
