package top.icoding.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SpringSocialConfigurer icodingSpringSocialConfigurer;
	
	@Autowired
	private AuthenticationSuccessHandler icodingAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler icodingAuthenticationFailureHandler;
	/**
	 * 设置 HTTP验证规则
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf验证
		http.formLogin().loginPage("/login/authority")
				.loginProcessingUrl("/login/form")
				.successHandler(icodingAuthenticationSuccessHandler)
				.failureHandler(icodingAuthenticationFailureHandler)
				.and().apply(icodingSpringSocialConfigurer)
				.and().csrf().disable()
				// 对请求进行认证
				.authorizeRequests()
				// 权限检查
				.antMatchers("/like").hasAuthority("AUTH_LOGIN")
				// 所有 / 的所有请求 都放行
				.antMatchers("/**/*").permitAll()
				// 所有请求需要身份认证
				.anyRequest().authenticated();
	}

	/**
	 * 修改security配置 修改userDetails的实现
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 设置UserDetailsService
		auth.userDetailsService(this.userDetailsService);

	}
}
