package top.icoding.security.social.service.impl;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import top.icoding.security.social.service.QQService;
import top.icoding.security.social.vo.QQUser;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;

/**
 * @author 我是金角大王
 * @date 2018年1月1日 下午6:45:30
 */
public class QQServiceImpl extends AbstractOAuth2ApiBinding implements QQService {
	private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?&oauth_consumer_key=%s&openid=%s";

	private String appId;

	private String openId;

	private ObjectMapper objectMapper = new ObjectMapper();

	public QQServiceImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);

		this.appId = appId;
		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
	}

	@Override
	public QQUser getQQUserInfo() {
		String url = String.format(URL_GET_USERINFO, appId, openId);
		String result = getRestTemplate().getForObject(url, String.class);
		QQUser qquser=null;
		try {
			qquser = objectMapper.readValue(result, QQUser.class);
			qquser.setOpenId(openId);
			return qquser;
		} catch (Exception e) {
			throw new RuntimeException("获取个人信息失败");
		}
	}

}
