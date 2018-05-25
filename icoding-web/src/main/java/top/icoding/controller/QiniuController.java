package top.icoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qiniu.util.Auth;

import top.icoding.config.QiniuConfig;
import top.icoding.util.ReturnMessage;
import top.icoding.util.ReturnMessageType;

/**
* @author 我是金角大王
* @date 2018年5月15日 下午3:31:44
*/
@RestController
@RequestMapping(value="/qiniu", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class QiniuController {
	@Autowired
	public QiniuConfig qiniuconfig;
	
	@GetMapping("/uptoken")
	public ReturnMessage getUpToken(){
		Auth auth = Auth.create(qiniuconfig.getAccessKey(),qiniuconfig.getSecretKey());
		String upToken = auth.uploadToken(qiniuconfig.getBucket());
		return new ReturnMessage(ReturnMessageType.SUCCESS.msg(),upToken);
	}
}
