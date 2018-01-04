package top.icoding.security.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
* @author 我是金角大王
* @date 2018年1月3日 下午9:23:27
*/
public class QQProperties extends SocialProperties {
	private String providerId="qq";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
