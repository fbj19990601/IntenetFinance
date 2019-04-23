package com.lechou.user;

import java.util.Date;

public class ShippingAddressVO {
	private int pk_shipping_address;
	private int pk_user;
	private String receiver;
	private String tel;
	private String address;
	private String postcode;
	private Date publishtime;
	private String modifier;
	private Date  modifytime;
	
	public int getPk_shipping_address() {
		return pk_shipping_address;
	}
	public void setPk_shipping_address(int pk_shipping_address) {
		this.pk_shipping_address = pk_shipping_address;
	}
	public int getPk_user() {
		return pk_user;
	}
	public void setPk_user(int pk_user) {
		this.pk_user = pk_user;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
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
	public Date getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}
}
