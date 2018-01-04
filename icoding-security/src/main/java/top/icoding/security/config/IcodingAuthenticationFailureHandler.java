package top.icoding.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @author 我是金角大王
* @date 2018年1月3日 下午2:01:13
*/
@Component
public class IcodingAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(500);
		response.setContentType("application/json");  
	    response.setCharacterEncoding("utf-8");  
	    PrintWriter writer = response.getWriter();  
        writer.write(objectMapper.writeValueAsString(exception));  
        writer.flush();  
        writer.close();  
	}

}
