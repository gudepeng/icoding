package top.icoding.security.social.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

import top.icoding.security.social.connet.QQConnectionFactory;

/**
 * @date 2018年1月2日 上午9:02:22
 */
@Configuration
public class QQAutoConfig extends SocialAutoConfigurerAdapter {
	
	@Autowired
	private IcodingSocialConfig icodingSocialConfig;
	
	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		return new QQConnectionFactory("qq", icodingSocialConfig.getQqAppId(),icodingSocialConfig.getQqAppSecret());
	}
	
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

}
