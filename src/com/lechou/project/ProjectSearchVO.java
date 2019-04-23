package com.lechou.project;

public class ProjectSearchVO {

	private String title;
	private int catagoryid;
	private String status;
	private String projectaddress;
	private int issearch;
	private int isFirstLogin;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCatagoryid() {
		return catagoryid;
	}

	public void setCatagoryid(int catagoryid) {
		this.catagoryid = catagoryid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProjectaddress() {
		return projectaddress;
	}

	public void setProjectaddress(String projectaddress) {
		this.projectaddress = projectaddress;
	}

	public int getIssearch() {
		return issearch;
	}

	public void setIssearch(int issearch) {
		this.issearch = issearch;
	}

	public int getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(int isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

}
