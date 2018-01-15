package top.icoding.security.social;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

/**
* @author 我是金角大王
* @date 2018年1月8日 下午10:07:36
*/

public class IcodingConnectView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		if(model.get("connection")==null){
			response.getWriter().write("<h3>解绑成功</h3>");
		}else{
			response.getWriter().write("<h3>绑定成功</h3>");
		}
		
	}

}
