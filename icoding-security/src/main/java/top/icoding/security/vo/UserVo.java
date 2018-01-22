package top.icoding.security.vo;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import top.icoding.util.Const;

/**
* @author 我是金角大王
* @date 2017年11月22日 上午9:52:42
*/
@ApiModel(description = "用户信息")
public class UserVo {
	@ApiModelProperty(value = "用户主键", required = false)
    private Integer userId;
    
    @ApiModelProperty(value = "用户名", required = true)
    @NotEmpty
    private String userName;
    
    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty
    private String userPwd;
    
    @ApiModelProperty(value = "手机号", required = false)
    private String userPhone;
    
    @ApiModelProperty(value = "用户性别", required = false)
    private String userSex;
    
    @ApiModelProperty(value = "用户邮箱地址", required = false)
    private String userEmail;
    
    @ApiModelProperty(value = "用户地址", required = false)
    private String userAddress;
    
    @ApiModelProperty(value = "用户自我描述", required = false)
    private String userDescription;
    
    @ApiModelProperty(value = "用户头像路径", required = false)
    private String userImageUrl;
    
    @ApiModelProperty(value = "用户等级", required = false)
    private Byte userRankId = Const.DEFAULT_USERRANKID;
    
    @ApiModelProperty(value = "用户锁定状态", required = false)
    private Byte userLock = Const.DEFAULT_USERLOCK;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public String getUserDescription() {
        return userDescription;
    }

    public void setUserDescription(String userDescription) {
        this.userDescription = userDescription == null ? null : userDescription.trim();
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl == null ? null : userImageUrl.trim();
    }

    public Byte getUserRankId() {
        return userRankId;
    }

    public void setUserRankId(Byte userRankId) {
        this.userRankId = userRankId;
    }

    public Byte getUserLock() {
        return userLock;
    }

    public void setUserLock(Byte userLock) {
        this.userLock = userLock;
    }
}