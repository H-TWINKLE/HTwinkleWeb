package cn.htwinkle.web.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseProject<M extends BaseProject<M>> extends Model<M> implements IBean {

	/**
	 * 背景ID
	 */
	public M setProjectId(java.lang.Integer projectId) {
		set("projectId", projectId);
		return (M)this;
	}
	
	/**
	 * 背景ID
	 */
	public java.lang.Integer getProjectId() {
		return getInt("projectId");
	}

	/**
	 * 项目名称
	 */
	public M setProjectName(java.lang.String projectName) {
		set("projectName", projectName);
		return (M)this;
	}
	
	/**
	 * 项目名称
	 */
	public java.lang.String getProjectName() {
		return getStr("projectName");
	}

	/**
	 * 项目url地址
	 */
	public M setProjectUrl(java.lang.String projectUrl) {
		set("projectUrl", projectUrl);
		return (M)this;
	}
	
	/**
	 * 项目url地址
	 */
	public java.lang.String getProjectUrl() {
		return getStr("projectUrl");
	}

	/**
	 * 项目整体样式
	 */
	public M setProjectStyle(java.lang.String projectStyle) {
		set("projectStyle", projectStyle);
		return (M)this;
	}
	
	/**
	 * 项目整体样式
	 */
	public java.lang.String getProjectStyle() {
		return getStr("projectStyle");
	}

	/**
	 * 项目排序，从0开始
	 */
	public M setProjectIndex(java.lang.Integer projectIndex) {
		set("projectIndex", projectIndex);
		return (M)this;
	}
	
	/**
	 * 项目排序，从0开始
	 */
	public java.lang.Integer getProjectIndex() {
		return getInt("projectIndex");
	}

	/**
	 * 项目标题
	 */
	public M setProjectTitle(java.lang.String projectTitle) {
		set("projectTitle", projectTitle);
		return (M)this;
	}
	
	/**
	 * 项目标题
	 */
	public java.lang.String getProjectTitle() {
		return getStr("projectTitle");
	}

	/**
	 * 项目标题样式
	 */
	public M setProjectTitleStyle(java.lang.String projectTitleStyle) {
		set("projectTitleStyle", projectTitleStyle);
		return (M)this;
	}
	
	/**
	 * 项目标题样式
	 */
	public java.lang.String getProjectTitleStyle() {
		return getStr("projectTitleStyle");
	}

	/**
	 * 项目简介
	 */
	public M setProjectBriefIntroduction(java.lang.String projectBriefIntroduction) {
		set("projectBriefIntroduction", projectBriefIntroduction);
		return (M)this;
	}
	
	/**
	 * 项目简介
	 */
	public java.lang.String getProjectBriefIntroduction() {
		return getStr("projectBriefIntroduction");
	}

	/**
	 * 项目简介样式
	 */
	public M setProjectBriefIntroductionStyle(java.lang.String projectBriefIntroductionStyle) {
		set("projectBriefIntroductionStyle", projectBriefIntroductionStyle);
		return (M)this;
	}
	
	/**
	 * 项目简介样式
	 */
	public java.lang.String getProjectBriefIntroductionStyle() {
		return getStr("projectBriefIntroductionStyle");
	}

	/**
	 * 背景图片地址
	 */
	public M setProjectPicUrl(java.lang.String projectPicUrl) {
		set("projectPicUrl", projectPicUrl);
		return (M)this;
	}
	
	/**
	 * 背景图片地址
	 */
	public java.lang.String getProjectPicUrl() {
		return getStr("projectPicUrl");
	}

	/**
	 * 背景图片样式
	 */
	public M setProjectPicStyle(java.lang.String projectPicStyle) {
		set("projectPicStyle", projectPicStyle);
		return (M)this;
	}
	
	/**
	 * 背景图片样式
	 */
	public java.lang.String getProjectPicStyle() {
		return getStr("projectPicStyle");
	}

	/**
	 * 背景图片位置
	 */
	public M setProjectPicPosition(java.lang.String projectPicPosition) {
		set("projectPicPosition", projectPicPosition);
		return (M)this;
	}
	
	/**
	 * 背景图片位置
	 */
	public java.lang.String getProjectPicPosition() {
		return getStr("projectPicPosition");
	}

	public M setProjectDate(java.util.Date projectDate) {
		set("projectDate", projectDate);
		return (M)this;
	}
	
	public java.util.Date getProjectDate() {
		return get("projectDate");
	}

}