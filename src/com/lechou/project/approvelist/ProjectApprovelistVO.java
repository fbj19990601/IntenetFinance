package com.lechou.project.approvelist;

import java.util.Date;

public class ProjectApprovelistVO {
	public static final int APPROVECOUNT = 2;//…Û∫À»’∆⁄
	private int pk_project_approvelist;
	private int pk_project;
	private int pk_user;
	private int approveresult;
	private Date publishtime;
	private String modifier;
	private Date modifytime;
	
	

	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
	public int getPk_project_approvelist() {
		return pk_project_approvelist;
	}
	public void setPk_project_approvelist(int pk_project_approvelist) {
		this.pk_project_approvelist = pk_project_approvelist;
	}
	public int getPk_project() {
		return pk_project;
	}
	public void setPk_project(int pk_project) {
		this.pk_project = pk_project;
	}
	public int getPk_user() {
		return pk_user;
	}
	public void setPk_user(int pk_user) {
		this.pk_user = pk_user;
	}
	public int getApproveresult() {
		return approveresult;
	}
	public void setApproveresult(int approveresult) {
		this.approveresult = approveresult;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
}
