/*package top.icoding.security.social.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

*//**
 * @author 我是金角大王
 * @date 2018年6月29日 上午11:28:09
 *//*
public class IcodingSpringSocialConfigurer extends SpringSocialConfigurer {
	
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setSignupUrl("/socialRegister");
		return (T) filter;
	}
	
}
*/