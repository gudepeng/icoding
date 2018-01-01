package top.icoding.security.controller;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import top.icoding.security.service.UserService;
import top.icoding.security.util.HttpClientUtils;
import top.icoding.security.util.TokenAuthenticationService;
import top.icoding.security.vo.GithubUser;
import top.icoding.security.vo.UserVo;

/**
 * @author 我是金角大王
 * @date 2017年11月27日 上午10:24:41
 */
@RestController
@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {
	@Autowired
	private UserService userservice;

	@GetMapping("/github")
	public void githubUser(String code,HttpServletResponse response) {
		String resultInfo = null;
		try {
			Map<String, String> map = new HashMap<>();
			map.put("client_id", "8347be17a991df62767f");
			map.put("client_secret", "9f502ba54524fe6f7977db90b8ed38d887adf7d0");
			map.put("code", code);
			String result = HttpClientUtils.postParameters("https://github.com/login/oauth/access_token", map);

			String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(result, "&");

			String accessToken = StringUtils.substringAfterLast(items[0], "=");

			resultInfo = HttpClientUtils.get("https://api.github.com/user?" + items[0]);
			Object json = JSON.parseObject(resultInfo, GithubUser.class);
			GithubUser githubUser = (GithubUser) json;
			UserVo user = userservice.getUserByUsergithub(githubUser);
			TokenAuthenticationService.addAuthentication(response, "github:"+user.getUserGithub());
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
