package top.icoding.security.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @author 我是金角大王
* @date 2018年1月3日 下午1:59:14
*/
@Component
public class IcodingAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setStatus(500);
		response.setContentType("application/json");  
	    response.setCharacterEncoding("utf-8");  
	    PrintWriter writer = response.getWriter();  
        writer.write(objectMapper.writeValueAsString(authentication));  
        writer.flush();  
        writer.close();  
	}

}
