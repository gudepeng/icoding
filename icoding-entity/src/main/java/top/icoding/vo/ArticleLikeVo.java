package top.icoding.vo;

import java.util.Date;

public class ArticleLikeVo {
    private Integer likeId;

    private Integer articleId;

    private Integer userId;

    private Date likeTime;

    public ArticleLikeVo(Integer articleId, Integer userId, Date likeTime) {
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Date likeTime) {
        this.likeTime = likeTime;
    }
}