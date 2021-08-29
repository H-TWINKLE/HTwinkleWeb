package cn.htwinkle.web.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseUser<M extends BaseUser<M>> extends Model<M> implements IBean {

	public M setUserId(java.lang.Integer userId) {
		set("userId", userId);
		return (M)this;
	}
	
	public java.lang.Integer getUserId() {
		return getInt("userId");
	}
	
	public M setUserAdmin(java.lang.String userAdmin) {
		set("userAdmin", userAdmin);
		return (M)this;
	}
	
	public java.lang.String getUserAdmin() {
		return getStr("userAdmin");
	}
	
	public M setUserPass(java.lang.String userPass) {
		set("userPass", userPass);
		return (M)this;
	}
	
	public java.lang.String getUserPass() {
		return getStr("userPass");
	}
	
	public M setUserSalt(java.lang.String userSalt) {
		set("userSalt", userSalt);
		return (M)this;
	}
	
	public java.lang.String getUserSalt() {
		return getStr("userSalt");
	}
	
	public M setUserNickname(java.lang.String userNickname) {
		set("userNickname", userNickname);
		return (M)this;
	}
	
	public java.lang.String getUserNickname() {
		return getStr("userNickname");
	}
	
	public M setUserSex(java.lang.String userSex) {
		set("userSex", userSex);
		return (M)this;
	}
	
	public java.lang.String getUserSex() {
		return getStr("userSex");
	}
	
	public M setUserYear(java.time.LocalDateTime userYear) {
		set("userYear", userYear);
		return (M)this;
	}
	
	public java.time.LocalDateTime getUserYear() {
		return getLocalDateTime("userYear");
	}
	
	public M setUserAuto(java.lang.String userAuto) {
		set("userAuto", userAuto);
		return (M)this;
	}
	
	public java.lang.String getUserAuto() {
		return getStr("userAuto");
	}
	
	public M setUserHeadPic(java.lang.String userHeadPic) {
		set("userHeadPic", userHeadPic);
		return (M)this;
	}
	
	public java.lang.String getUserHeadPic() {
		return getStr("userHeadPic");
	}
	
	public M setUserTel(java.lang.String userTel) {
		set("userTel", userTel);
		return (M)this;
	}
	
	public java.lang.String getUserTel() {
		return getStr("userTel");
	}
	
	public M setUserUpdate(java.time.LocalDateTime userUpdate) {
		set("userUpdate", userUpdate);
		return (M)this;
	}
	
	public java.time.LocalDateTime getUserUpdate() {
		return getLocalDateTime("userUpdate");
	}
	
	public M setUsersaveDate(java.time.LocalDateTime usersaveDate) {
		set("usersaveDate", usersaveDate);
		return (M)this;
	}
	
	public java.time.LocalDateTime getUsersaveDate() {
		return getLocalDateTime("usersaveDate");
	}
	
}

