package com.lechou.project.comment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectCommentVO {
	private int pk_project_comment;
	private int pk_project;
	private int pk_user;
	private String commentcontent;
	private Date publishtime;
	private String modifier;
	private Date modifytime;
	

	public int getPk_project_comment() {
		return pk_project_comment;
	}

	public void setPk_project_comment(int pk_project_comment) {
		this.pk_project_comment = pk_project_comment;
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

	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public Date getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
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
	//距离现在多少天
	public long getpublishToNow(){
		return -this.dateDiff(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				getPublishtime().toString(), "yyyy-MM-dd");
	}
	
	
	public long dateDiff(String startTime, String endTime, String format) {

		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;
		long diff = 0;
		long day = 0;

		try {

			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			day = diff / nd;

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}


}
