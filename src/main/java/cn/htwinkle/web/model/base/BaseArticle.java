package cn.htwinkle.web.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseArticle<M extends BaseArticle<M>> extends Model<M> implements IBean {

	public M setArticleId(java.lang.Integer articleId) {
		set("articleId", articleId);
		return (M)this;
	}
	
	public java.lang.Integer getArticleId() {
		return getInt("articleId");
	}
	
	public M setArticleTitle(java.lang.String articleTitle) {
		set("articleTitle", articleTitle);
		return (M)this;
	}
	
	public java.lang.String getArticleTitle() {
		return getStr("articleTitle");
	}
	
	public M setArticleAuthor(java.lang.String articleAuthor) {
		set("articleAuthor", articleAuthor);
		return (M)this;
	}
	
	public java.lang.String getArticleAuthor() {
		return getStr("articleAuthor");
	}
	
	public M setArticleContent(java.lang.String articleContent) {
		set("articleContent", articleContent);
		return (M)this;
	}
	
	public java.lang.String getArticleContent() {
		return getStr("articleContent");
	}
	
	public M setArticleDate(java.time.LocalDateTime articleDate) {
		set("articleDate", articleDate);
		return (M)this;
	}
	
	public java.time.LocalDateTime getArticleDate() {
		return getLocalDateTime("articleDate");
	}
	
}

