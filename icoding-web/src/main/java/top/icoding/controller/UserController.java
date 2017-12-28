package top.icoding.controller;

import javax.validation.Valid;
import javax.ws.rs.QueryParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import top.icoding.security.service.UserService;
import top.icoding.util.ReturnMessage;
import top.icoding.vo.UserVo;

/**
 * @author 我是金角大王
 * @date 2017年11月27日 上午10:24:41
 */
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {
	
	@Autowired
	private UserService userservice;

	@ApiOperation(value = "创建用户", notes = "创建用户", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PostMapping
	public ReturnMessage insertUser(@RequestBody @Valid UserVo user) {
		int message = userservice.insertUser(user);
		if (message == 0) {
			return new ReturnMessage("注册失败");
		}
		return new ReturnMessage("注册成功", user);
	}
	
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiImplicitParam(name = "userId", value = "用户主键", required = true, dataType = "int", paramType = "query")
	@GetMapping
	public ReturnMessage getUser(@QueryParam("userId") Integer userId) {
		UserVo userVo = userservice.getUserByUserid(userId);
		return new ReturnMessage("成功", userVo);
	}
}
