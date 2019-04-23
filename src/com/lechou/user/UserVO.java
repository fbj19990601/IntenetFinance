package com.lechou.user;

import java.util.Date;

public class UserVO  {
	private int pk_user;
	private String username;
	private String userpassword;
	private String sex;
	private String tel;
	private String email;
	private String iconurl;
	private String resume;//个人简介
	private String modifier;
	private Date modifytime;
	private String register;
	private Date registetime;
	private Date firstlogintime;
	private Date lastlogintime;//最后登录时间
	private int isadmin;	
	
	
	public int getPk_user() {
		
		return pk_user;
	}
	public void setPk_user(int pk_user) {
		this.pk_user = pk_user;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIconurl() {
		return iconurl;
	}
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public String getRegister() {
		return register;
	}
	public void setRegister(String register) {
		this.register = register;
	}
	public Date getRegistetime() {
		return registetime;
	}
	public void setRegistetime(Date registetime) {
		this.registetime = registetime;
	}
	public Date getFirstlogintime() {
		return firstlogintime;
	}
	public void setFirstlogintime(Date firstlogintime) {
		this.firstlogintime = firstlogintime;
	}
	public Date getLastlogintime() {
		return lastlogintime;
	}
	public void setLastlogintime(Date lastlogintime) {
		this.lastlogintime = lastlogintime;
	}
	public int getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(int isadmin) {
		this.isadmin = isadmin;
	}
}
