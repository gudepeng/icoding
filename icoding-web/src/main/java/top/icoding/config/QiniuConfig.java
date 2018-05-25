package top.icoding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 我是金角大王
 * @date 2018年5月15日 下午3:36:37
 */

@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiniuConfig {
	private String accessKey;
	private String secretKey;
	private String bucket;

	public QiniuConfig() {
		super();
	}

	public QiniuConfig(String accessKey, String secretKey, String bucket) {
		super();
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.bucket = bucket;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

}
