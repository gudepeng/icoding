package top.icoding.security.po;
/**
* @author 我是金角大王
* @date 2017年12月27日 上午11:45:43
*/
public class GithubUser {
	/**
	 * 登陆账号
	 * */
	private String login;
	/**
	 * id
	 * */
	private String id;
	/**
	 * 头像地址
	 * */
	private String avatar_url;
	
	private String gravatar_id;
	/**
	 * 个人信息地址
	 * */
	private String url;
	/**
	 * github主页地址
	 * */
	private String html_url;
	/**
	 * 被关注的地址
	 * */
	private String followers_url;
	/**
	 * 关注的地址
	 * */
	private String following_url;
	/**
	 * 名称
	 * */
	private String name;
	/**
	 * 邮箱
	 * */
	private String email;
	/**
	 * 简介
	 * */
	private String bio;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public String getGravatar_id() {
		return gravatar_id;
	}
	public void setGravatar_id(String gravatar_id) {
		this.gravatar_id = gravatar_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getFollowers_url() {
		return followers_url;
	}
	public void setFollowers_url(String followers_url) {
		this.followers_url = followers_url;
	}
	public String getFollowing_url() {
		return following_url;
	}
	public void setFollowing_url(String following_url) {
		this.following_url = following_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	
}