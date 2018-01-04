package top.icoding.security.social.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import top.icoding.security.social.service.QQService;
import top.icoding.security.social.vo.QQUser;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午10:56:16
*/
public class QQAdapter implements ApiAdapter<QQService> {

	@Override
	public boolean test(QQService api) {
		return true;
	}

	@Override
	public void setConnectionValues(QQService api, ConnectionValues values) {
		QQUser userInfo = api.getQQUserInfo();
		
		values.setDisplayName(userInfo.getNickname());
		values.setProviderUserId(userInfo.getOpenId());
		values.setImageUrl(userInfo.getFigureurl_1());
		values.setProfileUrl(null);
	}

	@Override
	public UserProfile fetchUserProfile(QQService api) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStatus(QQService api, String message) {
		// TODO Auto-generated method stub
		
	}

}
