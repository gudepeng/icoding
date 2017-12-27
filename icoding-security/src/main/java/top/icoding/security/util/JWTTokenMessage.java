package top.icoding.security.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

public class JWTTokenMessage {

	// 返回状态码 0 代表正常返回，其他都是错误
	private int status;
	// 一般显示错误信息
	private String message;
	// 结果集
	private Object result;

	public JWTTokenMessage(int status, String message, Object result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public JWTTokenMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "{\"status\": "+status+",\"message\": "+message+",\"result\": "+result+"}";
	}
	
	public static String tojson(int status,String message,Object result){
		JSONObject jsonObject = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", status);
			map.put("message", message);
			map.put("result", result);
			jsonObject = new JSONObject(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return jsonObject.toString();
	}
}
