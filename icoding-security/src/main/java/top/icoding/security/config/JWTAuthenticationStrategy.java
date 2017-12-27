package top.icoding.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

/**
* @author 我是金角大王
* @date 2017年11月24日 上午9:13:00
*/
public class JWTAuthenticationStrategy implements SessionAuthenticationStrategy{

	@Override
	public void onAuthentication(Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) throws SessionAuthenticationException {
		System.out.println(111222);
	}

}
