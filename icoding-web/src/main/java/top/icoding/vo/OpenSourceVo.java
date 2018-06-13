package top.icoding.vo;

/**
 * @author 我是金角大王
 * @date 2018年6月13日 下午1:55:07
 */
public class OpenSourceVo {
	private Integer osId;
	
	private String osTitle;
	
	private String osSummary;
	
	private String osUrl;

	public OpenSourceVo() {
		super();
	}

	public OpenSourceVo(int osId, String osTitle, String osSummary, String osUrl) {
		super();
		this.osId = osId;
		this.osTitle = osTitle;
		this.osSummary = osSummary;
		this.osUrl = osUrl;
	}

	public int getOsId() {
		return osId;
	}

	public void setOsId(int osId) {
		this.osId = osId;
	}

	public String getOsTitle() {
		return osTitle;
	}

	public void setOsTitle(String osTitle) {
		this.osTitle = osTitle;
	}

	public String getOsSummary() {
		return osSummary;
	}

	public void setOsSummary(String osSummary) {
		this.osSummary = osSummary;
	}

	public String getOsUrl() {
		return osUrl;
	}

	public void setOsUrl(String osUrl) {
		this.osUrl = osUrl;
	}

}
