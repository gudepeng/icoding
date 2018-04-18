package top.icoding.util;
/**
* @author 我是金角大王
* @date 2018年4月18日 下午4:45:30
*/
public enum ReturnMessageType {
	SUCCESS("success"),FAILUER("failuer");
	
	private String msg;
	
	private ReturnMessageType(String msg) {
		this.msg = msg;
	}
	
	public String msg(){
		return msg;
	}
	
}
