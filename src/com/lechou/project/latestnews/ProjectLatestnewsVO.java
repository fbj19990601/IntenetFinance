package com.lechou.project.latestnews;

import java.util.Date;

public class ProjectLatestnewsVO {
		
	private int pk_project_latestnews;
	private int pk_project;
	private String content;
	private String imagesurl;
	private String moviesurl;
	
	private Date publishtime;
	private String modifier;
	private Date modifytime;
	public int getPk_project_latestnews() {
		return pk_project_latestnews;
	}
	public void setPk_project_latestnews(int pk_project_latestnews) {
		this.pk_project_latestnews = pk_project_latestnews;
	}
	public int getPk_project() {
		return pk_project;
	}
	public void setPk_project(int pk_project) {
		this.pk_project = pk_project;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagesurl() {
		return imagesurl;
	}
	public void setImagesurl(String imagesurl) {
		this.imagesurl = imagesurl;
	}
	public String getMoviesurl() {
		return moviesurl;
	}
	public void setMoviesurl(String moviesurl) {
		this.moviesurl = moviesurl;
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
	
	
}
