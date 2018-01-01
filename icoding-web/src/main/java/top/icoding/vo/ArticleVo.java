package top.icoding.vo;

import java.util.Date;

/**
* @author 我是金角大王
* @date 2017年11月20日 下午3:00:04
*/
public class ArticleVo {
    private Integer articleId;

    private String articleTitle;

    private String articleTag;

    private Integer sortId;

    private String articleSummary;

    private String articleTitleimage;

    private Integer userId;

    private Date articleTime;

    private Integer articleClick;
    
    private Integer articleLike;
    
    private Integer articleComment;

    private Byte articleUp;

    private String articleContent;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag == null ? null : articleTag.trim();
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary == null ? null : articleSummary.trim();
    }

    public String getArticleTitleimage() {
        return articleTitleimage;
    }

    public void setArticleTitleimage(String articleTitleimage) {
        this.articleTitleimage = articleTitleimage == null ? null : articleTitleimage.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(Date articleTime) {
        this.articleTime = articleTime;
    }
    
    public Integer getArticleLike() {
		return articleLike;
	}

	public void setArticleLike(Integer articleLike) {
		this.articleLike = articleLike;
	}

	public Integer getArticleComment() {
		return articleComment;
	}

	public void setArticleComment(Integer articleComment) {
		this.articleComment = articleComment;
	}

	public Integer getArticleClick() {
        return articleClick;
    }

    public void setArticleClick(Integer articleClick) {
        this.articleClick = articleClick;
    }

    public Byte getArticleUp() {
        return articleUp;
    }

    public void setArticleUp(Byte articleUp) {
        this.articleUp = articleUp;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}