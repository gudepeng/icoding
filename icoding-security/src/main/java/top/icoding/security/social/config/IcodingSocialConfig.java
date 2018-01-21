package top.icoding.security.social.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 我是金角大王
 * @date 2018年1月19日 下午4:22:12
 */
@Component
@ConfigurationProperties(prefix = "social")
public class IcodingSocialConfig {
	private String qqAppId;
	
	private String qqAppSecret;
	
	private String githubAppId;
	
	private String githubAppSecret;

	public String getQqAppId() {
		return qqAppId;
	}

	public void setQqAppId(String qqAppId) {
		this.qqAppId = qqAppId;
	}

	public String getQqAppSecret() {
		return qqAppSecret;
	}

	public void setQqAppSecret(String qqAppSecret) {
		this.qqAppSecret = qqAppSecret;
	}

	public String getGithubAppId() {
		return githubAppId;
	}

	public void setGithubAppId(String githubAppId) {
		this.githubAppId = githubAppId;
	}

	public String getGithubAppSecret() {
		return githubAppSecret;
	}

	public void setGithubAppSecret(String githubAppSecret) {
		this.githubAppSecret = githubAppSecret;
	}
	
}
