package com.lechou.project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectVO {
	public static final int APPROVEDATE = 2;//审核日期
	public static final int APPROVEPASSRATE = 60;//审核通过率
	public static final int APPROVEPASSCOUNT = 0;//审核通过量
	public static final int FUNDWILLSTARTDATE = 1;//众筹即将开始时间
	public static final int FUNDWILLENDTDATE = 1;//众筹即将结束时间
	public static final int EXTRACTRATE = 1;
  
	private int pk_project;
	private String title;
	private int selectableprice1;
	private int selectableprice2;
	private int selectableprice3;
	private int finalprice;
	private String investreward;
	private String description;
	private String detaildescription;
	private String titleimageurl;
	private String descriptionimages;
	private String movieurl;
	private int initiatorid;
	private int catagoryid;
	private String projectaddress;
	private String status;
	private int fundamount;     
	private int funddate;
	private Date fundstarttime;
	private Date fundendtime;
	private Date approvestarttime;
	private Date approveendtime;
	private int focuscount;
	private int issuccesse;
	private Date successetime;
	private int successedchoose;
	private Date publishtime;
	private String modifier;
	private Date modifytime;
	


	public int getPk_project() {
		return pk_project;
	}

	public void setPk_project(int pk_project) {
		this.pk_project = pk_project;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getInvestreward() {
		return investreward;
	}

	public void setInvestreward(String investreward) {
		this.investreward = investreward;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitleimageurl() {
		return titleimageurl;
	}

	public void setTitleimageurl(String titleimageurl) {
		this.titleimageurl = titleimageurl;
	}

	public String getDescriptionimages() {
		return descriptionimages;
	}

	public void setDescriptionimages(String descriptionimages) {
		this.descriptionimages = descriptionimages;
	}

	public String getMovieurl() {
		return movieurl;
	}

	public void setMovieurl(String movieurl) {
		this.movieurl = movieurl;
	}

	public int getInitiatorid() {
		return initiatorid;
	}

	public void setInitiatorid(int initiatorid) {
		this.initiatorid = initiatorid;
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

	public int getFundamount() {
		return fundamount;
	}

	public void setFundamount(int fundamount) {
		this.fundamount = fundamount;
	}


	
	public int getFunddate() {
		return funddate;
	}

	public void setFunddate(int funddate) {
		this.funddate = funddate;
	}

	// 返回正数说明没有超期，返回负数表示超期。
	public long getFundlastdate() {
		return funddate
				+ this.dateDiff(
						new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
						fundstarttime.toString(), "yyyy-MM-dd");
	}

	

	public Date getFundstarttime() {
		return fundstarttime;
	}

	public void setFundstarttime(Date fundstarttime) {
		this.fundstarttime = fundstarttime;
	}

	public Date getFundendtime() {
		return fundendtime;
	}

	public void setFundendtime(Date fundendtime) {
		this.fundendtime = fundendtime;
	}

	// 返回正数说明没有超期，返回负数表示超期。
	public long getApprovelastdate() {
	return APPROVEDATE+this.dateDiff(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				approvestarttime.toString(), "yyyy-MM-dd");
	}
	public Date getFundStartdate() {
		return fundstarttime ;
		}
	public long getFundWillStartLastDate(){
		return FUNDWILLSTARTDATE+this.dateDiff(
				new SimpleDateFormat("yyyy-MM-dd").format(new Date()),
				approveendtime.toString(), "yyyy-MM-dd");
	}

	public Date getApprovestarttime() {
		return approvestarttime;
	}

	public int getSelectableprice1() {
		return selectableprice1;
	}

	public void setSelectableprice1(int selectableprice1) {
		this.selectableprice1 = selectableprice1;
	}

	public int getSelectableprice2() {
		return selectableprice2;
	}

	public void setSelectableprice2(int selectableprice2) {
		this.selectableprice2 = selectableprice2;
	}

	public int getSelectableprice3() {
		return selectableprice3;
	}

	public void setSelectableprice3(int selectableprice3) {
		this.selectableprice3 = selectableprice3;
	}

	public int getFinalprice() {
		return finalprice;
	}

	public void setFinalprice(int finalprice) {
		this.finalprice = finalprice;
	}

	public String getDetaildescription() {
		return detaildescription;
	}

	public void setDetaildescription(String detaildescription) {
		this.detaildescription = detaildescription;
	}

	public void setApprovestarttime(Date approvestarttime) {
		this.approvestarttime = approvestarttime;
	}

	public Date getApproveendtime() {
		return approveendtime;
	}

	public void setApproveendtime(Date approveendtime) {
		this.approveendtime = approveendtime;
	}

	public int getFocuscount() {
		return focuscount;
	}

	public void setFocuscount(int focuscount) {
		this.focuscount = focuscount;
	}

	
	public int getIssuccesse() {
		return issuccesse;
	}

	public void setIssuccesse(int issuccesse) {
		this.issuccesse = issuccesse;
	}

	public Date getSuccessetime() {
		return successetime;
	}

	public void setSuccessetime(Date successetime) {
		this.successetime = successetime;
	}


	public int getSuccessedchoose() {
		return successedchoose;
	}

	public void setSuccessedchoose(int successedchoose) {
		this.successedchoose = successedchoose;
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
