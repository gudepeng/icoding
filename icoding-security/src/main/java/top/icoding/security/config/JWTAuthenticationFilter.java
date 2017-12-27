package top.icoding.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.ExpiredJwtException;
import top.icoding.security.util.JWTTokenMessage;
import top.icoding.security.util.TokenAuthenticationService;

public class JWTAuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		Authentication authentication =null;
		try{
			authentication = TokenAuthenticationService.getAuthentication((HttpServletRequest) request);
		}catch(ExpiredJwtException e){
			e.printStackTrace();
			HttpServletResponse rep=((HttpServletResponse) response);
			rep.setStatus(HttpServletResponse.SC_FORBIDDEN);
			rep.setContentType("application/json");
			rep.getOutputStream().println(JWTTokenMessage.tojson(403,"timeout",""));
			return;
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		filterChain.doFilter(request, response);
	}
}
