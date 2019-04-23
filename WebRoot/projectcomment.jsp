<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import='com.lechou.project.*,com.lechou.user.*,com.lechou.project.approvelist.*,
	com.lechou.project.catagory.*,com.lechou.project.comment.*,java.util.*,java.text.*,
	com.lechou.project.latestnews.*,com.lechou.order.*'%>
<%@include file="sessioncheck.jsp"%>
<%
	final int PAGE_SIZE = 3;
	int pageNo = 1;
	String strPageNo = request.getParameter("pageNo");
	int totalRecords = 0;

   
	if (strPageNo != null && !strPageNo.trim().equals("")) {
		try {
	pageNo = Integer.parseInt(strPageNo);
		} catch (NumberFormatException e) {
	pageNo = 1;
		}
	}
    
	if (pageNo <= 0)
		pageNo = 1;

		
	String name = "";
	if (currentUserVO != null) {
		name = currentUserVO.getUsername();
	}
	request.setCharacterEncoding("GBk");
	String requesturl = "projectcomment.jsp";
	String pk_projectstr = request.getParameter("pk_project");
	int pk_project = 0;
	if (pk_projectstr != null && !pk_projectstr.trim().equals("")) {
		pk_project = Integer.parseInt(pk_projectstr);
	}
	ProjectVO projectvo = ProjectMgr.getInstance().getProjectVOById(
	pk_project);
   List <ProjectCommentVO> commentvos = new ArrayList<ProjectCommentVO>();
   totalRecords = ProjectCommentMgr.getInstance().getProjectComment(commentvos, pageNo, PAGE_SIZE, pk_project);
   
    List<ProjectVO> hotprojectvos = new ArrayList<ProjectVO>();
    int hotprojectbycatacount = 4;
    hotprojectvos = ProjectMgr.getInstance().getProjectHotRankByCatagoryId(hotprojectbycatacount, projectvo.getCatagoryid());
    
    List<ProjectVO> hotestprojectvos = new ArrayList<ProjectVO>();
    int hotestprojectbycatacount = 4;
    hotestprojectvos = ProjectMgr.getInstance().getHostestProject(hotestprojectbycatacount);
   	
	int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;

	if (pageNo > totalPages)
		pageNo = totalPages;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐筹网-项目详情</title>
<meta http-equiv="expires" content="0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/png"
	href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css"
	href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<script type="text/javascript" async="" src="js/dc.js"></script>
<script type="text/javascript" async="" src="js/ag.js"></script>
<script type="text/javascript"
	src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>
<script>
	$(".meun-01").live("click", function() {
		location.href = $(this).data("link");
	})
</script>
<script async="" src="js/ncfpb.1.1.min.js"></script>
<script async="" src="js/zcpb.1.0.min.js"></script>
<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>
<script src="js/AC_ActiveX.js" type="text/javascript"></script>
<script src="js/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body>
	<!--header static-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="m-left">
				<h2>
					<a id="nav_logo" alt="众筹网" class="img-logo " title="众筹网"
						href=index.jsp"><img src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a href="index.jsp"><span><i class="s-01"></i>
						</span>首页</a></li>
					<li><a href="browseproject.jsp" target="_blank"><i
							class="s-02"></i>浏览项目</a></li>
					<li><a href="approveproject1.jsp" target="_blank"><i
							class="s-30"></i>新项目审核</a>
					</li>
					<li><a href="initialproject.jsp" target="_blank"><i
							class="s-03"></i>发起项目</a></li>
					<li><a href="successeproject.jsp" target="_blank"><i
							class="s-phone"></i>成功项目</a>
					</li>
				</ul>
			</div>
			<div class="m-right clearfix">
				<%
					if (currentUserVO == null) {
				%>
				<span class="z-Login"> <a id="user_register"
					class="z-register Js-showRegister" href="register.jsp">注册</a> <a
					id="user_login" class="Js-showLogin" href="login.jsp">登录</a> </span>
				<%
					} else {
				%>

				<!--login in-->
				<span class="z-Login"> <a id="user_login"
					class="Js-showLogin" href="manageproject.jsp"><%=name%></a> <a
					id="user_register" class="z-register Js-showRegister"
					href="exit.jsp?operate=exit">退出</a> </span>
				<%
					}
				%>
				<form action="#" name="search" method="get" wx-validator=""
					autocomplete="off">
					<span> <input name="keywords2"
						wx-validator-placeholder="输入项目名称" wx-validator-rule="required"
						type="text" wx-validator-notip="" placeholder="输入项目名称"> <a
						href="javascript:document.search.submit()">搜索</a> </span>
				</form>
			</div>
		</div>
	</div>
	<!--project info-->
	<link rel="stylesheet" type="text/css"
		href="css/1db826e5ef2ef3191131490e43a8940b.css">
	<!--main static-->
	<!--particulars static-->
<div class="bg-white clearfix">
		<div class="wrap">
			<div class="project-left">
				<a class="a-img"><img
					src="uploadFile/<%=projectvo.getTitleimageurl()%>"> </a>
				<div class="clearfix">
				<%
							if (projectvo.getStatus() != null
									&& projectvo.getStatus().trim().equals("未审核")) {
						%> 
					<span> <a id="deal_detail_like" href="javascript:;"
						data-id="120412" class="z-gf Js-like_top bn"><i class="z-like"></i>支持<%=projectvo.getSelectableprice1()%></a>
						<span style="display:inline;float:none;">(<%=ProjectApprovelistMgr.getInstance().getResultCount(
					projectvo.getPk_project(), 1)%></span>) </span> &nbsp; <span> &nbsp; 支持<%=projectvo.getSelectableprice2()%></a>
						<span style="display:inline;float:none;">(<%=ProjectApprovelistMgr.getInstance().getResultCount(
					projectvo.getPk_project(), 2)%></span>) </span> &nbsp; <span> &nbsp; 支持<%=projectvo.getSelectableprice3()%></a>
						<span style="display:inline;float:none;">(<%=ProjectApprovelistMgr.getInstance().getResultCount(
					projectvo.getPk_project(), 3)%></span>) </span> &nbsp; <span> &nbsp; <a
						id="deal_detail_like" href="javascript:;" data-id="120412"
						class="z-gf Js-like_top bn"> <i class="z-like"></i>反对数</a>(<span
						style="display:inline;float:none;"><%=ProjectApprovelistMgr.getInstance().getResultCount(
					projectvo.getPk_project(), 4)%></span>) </span>
					<%}else{
					 %>
					  <span> &nbsp; <a
						id="deal_detail_like" href="javascript:;" data-id="120412"
						class="z-gf Js-like_top bn"> <i class="z-like"></i>关注数</a>(<span
						style="display:inline;float:none;"><%=projectvo.getFocuscount()%></span>) </span>
					
					 <span> &nbsp; <a
						id="deal_detail_like" href="javascript:;" data-id="120412"
						class="z-gf Js-like_top bn"> <i class="z-like"></i>评论数</a>(<span
						style="display:inline;float:none;"><%=ProjectCommentMgr.getInstance().getCommentsCount(
						projectvo.getPk_project())%></span>) </span>
					 <%} %>
					<div class="share">
						<div class="fenxiang"></div>
					</div>
				</div>
			</div>
			<div class="project-right">
				<h2>
					<a><%=projectvo.getTitle()%></a>
				</h2>
				<p class="p-color"><%=projectvo.getDescription()%></p>
				<div class="support-d ">
					<ul>
								<%
							if (projectvo.getStatus() != null
									&& projectvo.getStatus().trim().equals("未审核")) {
						%>
						<li class="first-li" style="z-index:4"><br>
							<p class="description" ><span style="line-height: 1.5;">
								乐筹网会对筹资人发起的项目公开审核2天,您可以登录网址参与投票，为你中意的项目投上一票，你还可以选择您满意的投资额，以便筹资人最终定价，小伙伴们赶快投上你们宝贵的一票吧！</span>
								<br> 项目集资总额：<%=projectvo.getFundamount()%>元&nbsp;&nbsp;
								筹集天数：<%=projectvo.getFunddate()%>天<br> <br> 审核开始时间：<%=projectvo.getApprovestarttime()%>&nbsp;&nbsp;
								审核剩余天数：<%=projectvo.getApprovelastdate()%>天 <br> <br>已有<%=ProjectMgr.getInstance().getSurpportCount(projectvo)
						+ ProjectMgr.getInstance()
								.getInsurpportCount(projectvo)%>
								位网友参与了投票<br> <a
									href="approvelist.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&result=1"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">支持定价 <%=projectvo.getSelectableprice1()%>元</span>
								</a> <a
									href="approvelist.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&result=2"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">支持定价 <%=projectvo.getSelectableprice2()%>元
								</span> </a> <a
									href="approvelist.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&result=4"
									data-jump="" class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">觉得没新意 </span> </a> <a
									href="approvelist.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&result=3"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">支持定价 <%=projectvo.getSelectableprice3()%>元
								</span> </a>
						</li>
						<%
							} else if(projectvo.getStatus() != null
									&& (projectvo.getStatus().trim().equals("众筹中")||projectvo.getStatus().trim().equals("将要结束"))) {
						%>

						<li class="first-li" style="z-index:4"><br>
							<p class="description"><span style="line-height: 1.5;">
								在众筹期限内，必须达到项目集资总额，否则项目就将宣布失败！</span>单笔投资额：<%=projectvo.getFinalprice()%>元<br>
								<br> 项目集资总额：<%=projectvo.getFundamount()%>元&nbsp;&nbsp;<br>
								<br> 筹集天数：<%=projectvo.getFunddate()%>天<br> <br>已筹集：<%=ProjectMgr.getInstance().getCompletefundamount(
						pk_project)%>元&nbsp;&nbsp;
								单笔投资额：<%=projectvo.getFinalprice()%>元<br>
								<br>完成度：<%=ProjectMgr.getInstance()
						.getCompletedegree(pk_project)/100%>%&nbsp;&nbsp;
								<br>
								<br>剩余天数：<%=projectvo.getApprovelastdate()%>天 &nbsp;&nbsp;已有<%=projectvo.getFocuscount()%>
								位网友关注数<br> <a
									href="replyorder.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&action=focus"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">添加关注 </span> </a> <a
									href="replyorder.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&action=reply"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">投一笔</span> </a>
						</li>
						<%
							}else if(projectvo.getStatus() != null
									&& projectvo.getStatus().trim().equals("即将开始")){
							
						%>
						<li class="first-li" style="z-index:4;line-height: 1.5;"><br>
							<p class="description">
								审核通过后需等待一天才能正式上线，在此期间，发起人可以确定最终投资额，如果不修改，将由系统自动确定最终投资额,请各位筹资人相互转告。<br>单笔投资额：<%=projectvo.getFinalprice()==0?"尚未确定":projectvo.getFinalprice()+"元"%><br>
								 项目集资总额：<%=projectvo.getFundamount()%>元&nbsp;&nbsp;
								<br> 筹集天数：<%=projectvo.getFunddate()%>天<br> 
						 审核开始时间：<%=projectvo.getApprovestarttime()%>&nbsp;&nbsp;
							<br>审核通过时间：<%=projectvo.getApproveendtime()%>&nbsp;&nbsp;
								<br>您还可以：
								<a	href="replyorder.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&action=focus"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">添加关注 </span> </a>
						</li>
						<%} if(projectvo.getStatus() != null
									&& projectvo.getStatus().trim().equals("已成功")){
							
						%>
						<li class="first-li" style="z-index:4;line-height: 1.5;"><br>
							<p class="description">
								审核成功后，筹资人会兑现事前的承诺的投资回报，请各位投资人关注项目最新动态。<br>单笔投资额：<%=projectvo.getFinalprice()==0?"尚未确定":projectvo.getFinalprice()+"元"%><br>
								 项目集资总额：<%=projectvo.getFundamount()%>元&nbsp;&nbsp;
								<br> 筹集天数：<%=projectvo.getFunddate()%>天<br> 
						 已筹资：<%=ProjectMgr.getInstance().getCompletefundamount(projectvo.getPk_project())%>&nbsp;&nbsp;
							<br>完成率：<%=ProjectMgr.getInstance().getCompletedegree(projectvo.getPk_project())/100%>%&nbsp;&nbsp;
							<br>成功时间：<%=projectvo.getSuccessetime()%>&nbsp;&nbsp;
								<br>您还可以：
								<a	href="replyorder.jsp?pk_project=<%=projectvo.getPk_project()%>&requesturl=<%=requesturl%>&action=focus"
									class=" a-btn Js-support Js-support"> <span
									id="support_price_100132027">添加关注 </span> </a>
						</li>
						<%} %>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!--header end-->
	<link rel="stylesheet" type="text/css"
		href="css/1db826e5ef2ef3191131490e43a8940b.css">
	<!--detail of the project-->
	<div class="particulars clearfix">
		<a name="maodian"></a>
		<div class="particulars-left">
			<div id="Js-detail" class="m-matter white">
				<ul class="particulars-list-nav clearfix Js-topbill">
					<li ><a id="deal_detail_home"
						href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>">项目主页</a>
					</li>
					<li class="selected"><a id="deal_detail_home"
						href="projectcomment.jsp?pk_project=<%=projectvo.getPk_project()%>">评论(<%=ProjectCommentMgr.getInstance().getCommentsCount(
					projectvo.getPk_project())%>)</a></li>
					<li >
					<%if(projectvo.getStatus() != null
									&& !projectvo.getStatus().trim().equals("未审核")){ %><a id="deal_detail_home"
						href="projectlatestnews.jsp?pk_project=<%=projectvo.getPk_project()%>">项目进展(<%=ProjectLatestnewsMgr.getInstance().getLatestnewSCountByProjectId(pk_project)%>)</a>
					</li>
					<li><a id="deal_detail_home"
						href="projectinvestor.jsp?pk_project=<%=projectvo.getPk_project()%>">投资人(<%=OrderMgr.getInstance().getOrderCountByProjectId(pk_project)%>)</a></li><%} %>
				</ul>


				<div class="m-topics">
					<form name="commentBlock" action="replycomment.jsp"
						autocomplete="off">
						<input type="hidden" value="<%=requesturl%>" name="requesturl">
						<input type="hidden" value="<%=projectvo.getPk_project()%>"
							name="pk_project">
						<textarea name="content" cols="" rows=""></textarea>
						<div class="hom-gn Js-img_insert">
							<div class="use-width"></div>
							<a href="javascript:document.commentBlock.submit();"
								type="submit" class="mm-blue ml-pm">发表回复</a>
						</div>
					</form>
					<div class="m-entire">
						<a href="http://www.zhongchou.cn/deal-topic/id-135328"
							class="no-01">全部</a>
					</div>
					<ul>
						<%
							for (Iterator<ProjectCommentVO> it = commentvos.iterator(); it
									.hasNext();) {
								ProjectCommentVO commentvo = it.next();
						%>
						<li id="Js-logId162682" class="Js-talkList">
							<div class="f-outs">
								<div class="f-outs-updow clearfix">
									<a href="#" class="cyclo-img01"><img
										src="<%=(UserMgr.getInstance()
						.getUserVOById(commentvo.getPk_user()).getIconurl() == null || UserMgr
						.getInstance().getUserVOById(commentvo.getPk_user())
						.getIconurl().trim().equals("")) == true ? "images/defaulticon.png"
						: "uploadFile/"
								+ UserMgr.getInstance()
										.getUserVOById(commentvo.getPk_user())
										.getIconurl()%>">
									</a>
									<div class="fl">
										<p>
									
											<a href="#"><%=UserMgr.getInstance().getUserVOById(commentvo.getPk_user()).getUsername()%> </a>&nbsp;<span><%=commentvo.getpublishToNow()%>天前</span>
										</p>
										<p><%=commentvo.getCommentcontent()%></p>
									</div>
								</div>
								<p class="lp-hn">

									<a href="javascript:;"
										onclick="document.getElementById('comment').style.display='';"
										class="Js-showComment"><%=new SimpleDateFormat("yyyy-MM-dd").format(commentvo
						.getPublishtime())%></a>
								</p>


							</div></li>
						<%
							}
						%>

					</ul>
					<div class="pager">

						<a class="prev" href="projectcomment.jsp?pageNo=1&pk_project=<%=pk_project%>">首页</a> <a
							class="prev"
							href="projectcomment.jsp?pageNo=<%=pageNo == 1 ? 1 : pageNo - 1%>&pk_project=<%=pk_project%>">&lt;</a>
						&nbsp;
						<%
							for (int i = 1; i <= totalPages; i++) {
						%>
						<a class="<%=(i == pageNo) ? "selected" : "common-sprite"%>"
							href="projectcomment.jsp?pageNo=<%=i%>&pk_project=<%=pk_project%>"><%=i%></a>&nbsp;
						<%
							}
						%>
						<a class="next"
							href="projectcomment.jsp?pageNo=<%=pageNo == totalPages ? totalPages : pageNo + 1%>&pk_project=<%=pk_project%>">&gt;</a>
						<a class="common-sprite"
							href="projectcomment.jsp?pageNo=<%=totalPages%>&pk_project=<%=pk_project%>">尾页</a>
					</div>
				</div>


			</div>
			<div class="m-portpe">
				<h4>热门项目</h4>
				<ul>
				
				<%
				for (Iterator<ProjectVO> it1 = hotestprojectvos.iterator(); it1
							.hasNext();) {
						ProjectVO hotestprojectvo = it1.next();
				 %>
					<li>
						<div class="m-recommend img-scale">
							<a href="projectdetail.jsp?pk_project=<%=hotestprojectvo.getPk_project()%>" target="_blank" ><img
								src="uploadFile/<%=hotestprojectvo.getTitleimageurl() %>">
							</a>
							<h5>
								<a href="projectdetail.jsp?pk_project=<%=hotestprojectvo.getPk_project()%>"><%=hotestprojectvo.getTitle()%></a>
							</h5>
							<p>
								已筹资：<em>¥<%=ProjectMgr.getInstance().getCompletefundamount(hotestprojectvo.getPk_project()) %>(<%=ProjectMgr.getInstance().getCompletedegree(hotestprojectvo.getPk_project())/100%>%)</em>
							</p><br>
							<%if(hotestprojectvo.getFundlastdate()>0) {%>
							<p>
							&nbsp;&nbsp;剩余时间：<em><%=hotestprojectvo.getFundlastdate() %>天</em>
							</p>
							<%} %>
						</div>
					</li>
					<%} %>
				
				</ul>
			</div>
		</div>
		<div class="particulars-right particulars-right-white">
		<div class="particulars-right-top">
				<p class="p-c">
					发起人： <span><%=UserMgr.getInstance()
					.getUserVOById(projectvo.getInitiatorid()).getUsername()%></span>
				</p>
				<p class="p-c">
					项目地点 ：<span><%=projectvo.getProjectaddress()%></span>
				</p>
				<p class="p-c p-pb">
					个人简介 ：<span  style=" line-height: 2;"><%=UserMgr.getInstance()
					.getUserVOById(projectvo.getInitiatorid()).getResume()%></span>
				</p>
				<p class="bfb_ts clearfix">
					<span>状态：</span><%if(projectvo.getStatus().equals("未审核")) {%><span><%=projectvo.getApprovelastdate()%>天<em>/<%=ProjectVO.APPROVEDATE%>天</em>
					</span>
					<%}else if(projectvo.getStatus().equals("即将开始")){ %>
					<span>即将开始
					</span><%}else if(projectvo.getStatus().equals("已成功")){ %>
					<span>已成功
					</span><%}
					else{ %>
					<span><%=projectvo.getFundlastdate()%>天<em>/<%=projectvo.getFunddate()%>天</em>
					</span>
					<%} %>
				</p>

			</div>
			<style>
#image {
	height: 200px;
	width: 260px;
}
</style>
			<div class="support-project-list">
				<h4>同类推荐</h4>
				<ul>
				
				<%
					for (Iterator<ProjectVO> it1 = hotprojectvos.iterator(); it1
							.hasNext();) {
						ProjectVO hotprojectvo = it1.next();
				%>
					<li>
						<div class="s-c"><a target="_blank" class="item-figure" href="projectdetail.jsp?pk_project=<%=hotprojectvo.getPk_project()%>"> 
							<img src="uploadFile/<%=hotprojectvo.getTitleimageurl() %>" id="image"></a>
						</div>
						<p class="p-f">
							已筹资：<em>¥<%=ProjectMgr.getInstance().getCompletefundamount(hotprojectvo.getPk_project()) %>(<%=ProjectMgr.getInstance().getCompletedegree(hotprojectvo.getPk_project())/100%>%)</em></span>
						</p><br>
							<%if(hotprojectvo.getFundlastdate()>0) {%>
							<p>
							&nbsp;&nbsp;	剩余时间：<em><%=hotprojectvo.getFundlastdate() %>天</em>
							</p>
							<%} %>
					</li>
					<%} %>
					
				</ul>
			</div>
				</div>
	</div>
	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">
	<!--start of footer-->
	<div class="footer">
		<div class="footer-nav">
			<a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a>
			<!-- <a href="/help-team" rel="nofollow">团队介绍 <i>｜</i> </a> -->
			<a href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a
				href="#" target="_blank">新手帮助 <i>｜</i> </a>
		</div>
		<div class="footer-icon">
			<span>copyright ©2015 by lechou all right reserved</span>
		</div>
	</div>
	<!--end of footer-->
	<script type="text/javascript"
		src="js/fe3974b84fb6b50da0f329242078614c.js"></script>
</body>
</html>
