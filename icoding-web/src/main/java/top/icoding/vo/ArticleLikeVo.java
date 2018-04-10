package top.icoding.vo;

import java.util.Date;

public class ArticleLikeVo {
    private Integer likeId;

    private Integer articleId;

    private String userId;

    private Date likeTime;

    public ArticleLikeVo(Integer articleId, String userId, Date likeTime) {
		super();
		this.articleId = articleId;
		this.userId = userId;
		this.likeTime = likeTime;
	}

	public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}