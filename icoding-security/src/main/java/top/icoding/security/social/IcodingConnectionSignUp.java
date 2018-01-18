package top.icoding.security.social;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 使用第三方登录直接根据key注册
 * 
* @author 我是金角大王
* @date 2018年1月4日 下午8:22:10
*/
@Component
public class IcodingConnectionSignUp implements ConnectionSignUp {

	@Override
	public String execute(Connection<?> connection) {
		
		return connection.getKey().toString();
	}

}
