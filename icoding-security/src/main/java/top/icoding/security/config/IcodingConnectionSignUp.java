package top.icoding.security.config;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
* @author 我是金角大王
* @date 2018年1月4日 下午8:22:10
*/
@Component
public class IcodingConnectionSignUp implements ConnectionSignUp {

	@Override
	public String execute(Connection<?> connection) {
		
		return connection.getDisplayName();
	}

}
