package top.icoding.security.social.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import top.icoding.security.social.service.GithubService;
import top.icoding.security.social.vo.GithubUser;

/**
* @author 我是金角大王
* @date 2018年1月1日 下午10:56:16
*/
public class GithubAdapter implements ApiAdapter<GithubService> {

	@Override
	public boolean test(GithubService api) {
		return true;
	}

	@Override
	public void setConnectionValues(GithubService api, ConnectionValues values) {
		GithubUser userInfo = api.getGithubUserInfo();
		
		values.setDisplayName(userInfo.getName());
		values.setProviderUserId(userInfo.getId());
		values.setImageUrl(userInfo.getAvatar_url());
		values.setProfileUrl(userInfo.getHtml_url());
	}

	@Override
	public UserProfile fetchUserProfile(GithubService api) {
		return null;
	}

	@Override
	public void updateStatus(GithubService api, String message) {		
	}

}
