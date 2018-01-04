package top.icoding.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
* @author 我是金角大王
* @date 2018年1月3日 上午11:42:16
*/
@RestController
public class LoginController {
	
	@RequestMapping("/login/authority")
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String LoginAuthority(){
		return "{message:'没有权限'}";
	}
}
