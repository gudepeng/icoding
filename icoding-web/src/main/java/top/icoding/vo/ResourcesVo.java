package top.icoding.vo;

import java.util.Date;

public class ResourcesVo {
    private Integer resourcesId;

    private String resourcesName;

    private Integer sortId;

    private String resourcesUrl;

    private String resourcesPassword;
    
    private Date createTime;

	public Integer getResourcesId() {
		return resourcesId;
	}

	public void setResourcesId(Integer resourcesId) {
		this.resourcesId = resourcesId;
	}

	public String getResourcesName() {
		return resourcesName;
	}

	public void setResourcesName(String resourcesName) {
		this.resourcesName = resourcesName;
	}

	public Integer getSortId() {
		return sortId;
	}

	public void setSortId(Integer sortId) {
		this.sortId = sortId;
	}

	public String getResourcesUrl() {
		return resourcesUrl;
	}

	public void setResourcesUrl(String resourcesUrl) {
		this.resourcesUrl = resourcesUrl;
	}

	public String getResourcesPassword() {
		return resourcesPassword;
	}

	public void setResourcesPassword(String resourcesPassword) {
		this.resourcesPassword = resourcesPassword;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}