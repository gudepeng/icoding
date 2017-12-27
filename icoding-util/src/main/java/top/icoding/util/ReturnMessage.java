package top.icoding.util;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
public class ReturnMessage {
	
	/**
	 * 是否成功
	 */
	private int status;
	/**
	 * 提示语
	 */
	private String message;
	/**
	 * 返回数据
	 */
	private Object result;

	public ReturnMessage(String message) {
		this.status = 0;
		this.message = message;
	}

	public ReturnMessage(String message, Object result) {
		this.status = 1;
		this.message = message;
		this.result = result;
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

}
