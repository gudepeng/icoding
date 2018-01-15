package top.icoding.security.social;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
* @author 我是金角大王
* @date 2018年1月8日 下午8:02:14
*/
@Component("connect/status")
public class IcodingConnectionStatusView extends AbstractView {
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String,List<Connection<?>>> connection =(Map<String,List<Connection<?>>>) model.get("connectionMap");
		Map<String,Boolean> result = new HashMap<>();
		for(String key:connection.keySet()){
			result.put(key, CollectionUtils.isNotEmpty(connection.get(key)));
		}
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}

}
