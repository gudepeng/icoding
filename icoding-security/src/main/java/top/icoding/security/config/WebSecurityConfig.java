package top.icoding.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	/**
	 * 设置 HTTP验证规则
	 */	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf验证
		http.csrf().disable()
				// 对请求进行认证
				.authorizeRequests()
				// 权限检查
				/*.antMatchers("/like").hasAuthority("AUTH_LOGIN")*/
				// 所有 / 的所有请求 都放行
				.antMatchers("/**/*").permitAll()
				// 所有请求需要身份认证
				.anyRequest().authenticated().and()
				// 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				// 添加一个过滤器验证其他请求的Token是否合法
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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
