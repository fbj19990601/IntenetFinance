package com.lechou.order;

import java.util.Date;

public class OrderVO {
	
	private int pk_order;
	private int pk_project;
	private int pk_user;
	private int amount;
	private int totalsum;
	private int ispay;
	private String payway;
	private int isshiping;
	private String ordertype;
	private Date publishtime;
	private String modifier;
	private Date modifytime; 
	
	public int getPk_order() {
		return pk_order;
	}
	public void setPk_order(int pk_order) {
		this.pk_order = pk_order;
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
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotalsum() {
		return totalsum;
	}
	public void setTotalsum(int totalsum) {
		this.totalsum = totalsum;
	}
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public String getPayway() {
		return payway;
	}
	public void setPayway(String payway) {
		this.payway = payway;
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
	public int getIsshiping() {
		return isshiping;
	}
	public void setIsshiping(int isshiping) {
		this.isshiping = isshiping;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	
}
