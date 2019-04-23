<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.lechou.user.*,com.lechou.project.*,java.util.*,com.lechou.project.approvelist.*,com.lechou.project.comment.*,
com.lechou.project.catagory.*"%>
<%@include file="sessioncheck.jsp"%>
<%
	String name = "";
	if(currentUserVO != null)
	{
	   name = currentUserVO.getUsername();
	}
	
	List<ProjectVO> willstartprojectvos = new ArrayList<ProjectVO>();
	int willstartprojectcount = 5;
	willstartprojectvos = ProjectMgr.getInstance().getProjectWillStart(willstartprojectcount);
	
	List<ProjectCatagoryVO> projectcatagoryvos = ProjectCatagoryMgr.getInstance().getProjectCatagorys();
	int hotprojectbycatacount = 5;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="expires" content="0">
<title>乐筹网-主页</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/png"
	href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css"
	href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<!--slider css-->
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
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/main.js"></script>
</head>
<body>
	<!--header static-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="m-left">
				<h2>
					<a id="nav_logo" alt="众筹网" class="img-logo " title="众筹网"href="index.jsp"><img
						src="images/logo.png" alt="" />
					</a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a  href="index.jsp"><span><i
								class="s-01"></i>
						</span>首页</a>
					</li>
					<li><a href="browseproject.jsp" target="_blank"><i class="s-02"></i>浏览项目</a>
					</li>
					<li><a href="approveproject1.jsp" target="_blank"><i
							class="s-30"></i>项目审核</a>
					</li>
					<li><a href="initialproject.jsp" target="_blank"><i class="s-03"></i>发起项目</a>
					</li>
					<li><a href="successeproject.jsp" target="_blank"><i class="s-phone"></i>成功项目</a>
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
					class="Js-showLogin" href="manageproject.jsp"><%=name%></a> <a id="user_register"
					class="z-register Js-showRegister" href="exit.jsp?operate=exit">退出</a>
				</span>
				<%
					}
				%>
				<form action="#" name="search" method="get" wx-validator=""
					autocomplete="off" id="search">
					<span> <input name="keywords2"
						wx-validator-placeholder="输入项目名称" wx-validator-rule="required"
						type="text" wx-validator-notip="" placeholder="输入项目名称"> <a
						href="javascript:document.search.submit()">搜索</a> </span>
				</form>
			</div>
		</div>
	</div>
	<!--header end-->
	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">
	<div class="slide-main" id="touchMain">
		<a class="prev" href="javascript:;" stat="prev1001"><img
			src="images/l-btn.png" />
		</a>
		<div class="slide-box" id="slideContent">
			<div class="slide" id="bgstylec">
				<a stat="sslink-3" href="" target="_blank">
					<div class="obj-e">
						<img src="images/baomi-t-1.png" />
					</div>
					<!---->
					<div class="obj-f">
						<img src="images/baomi-kl.jpg" />
					</div> </a>
			</div>
			<div class="slide" id="bgstylea">
				<a stat="sslink-1" href="" target="_blank">
					<div class="obj-a">
						<img src="images/bao.png" />
					</div>
					<div class="obj-b">
						<img src="images/nt-1.png" />
					</div>
					<!----> </a>
			</div>
			<div class="slide" id="bgstyleb">
				<a stat="sslink-2" href="" target="_blank">
					<div class="obj-c">
						<img src="images/bao-2.png" />
					</div>
					<div class="obj-d">
						<img src="images/st-2.png" />
						<!---->
						<p>全新chrome内核，内存占用最低，轻巧炫酷皮肤</p>
					</div> </a>
			</div>
		</div>
		<a class="next" href="javascript:;" stat="next1002"><img
			src="images/r-btn.png" />
		</a>
		<div class="item">
			<a class="cur" stat="item1001" href="javascript:;"></a><a
				href="javascript:;" stat="item1002"></a><a href="javascript:;"
				stat="item1003"></a>
		</div>
	</div>
	<link rel="stylesheet" type="text/css"
		href="css/54a58051ccf1833f8e04a32cf144a640.css">

	<!--start of hot project-->
	<div class="wrap m-box03">
<%	if(willstartprojectvos.size() != 0){ %>
		<div class="m-headline clearfix">
			<div class="project-title">
				<a href="browseproject.jsp?status=willstart" target="_blank">即将开始</a>
			</div>
			<a class="z-browse" href="browseproject.jsp?status=willstart">浏览全部</a>
		</div>

		<!--focus static-->
		<div class="focus-box">
			<ul class="focus-con clearfix">


				<%
				
					for (Iterator<ProjectVO> it =willstartprojectvos.iterator(); it
							.hasNext();) {
						ProjectVO projectvo = it.next();
						if(projectvo != null){
				%>
				<li>
					<div class="list-item" style="background: #FFF">
						<a target="_blank" class="item-figure"
							href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>">
							<img src="uploadFile/<%=projectvo.getTitleimageurl()%>"
							alt="<%=projectvo.getTitle()%>" title="<%=projectvo.getTitle()%>">
						</a>
						<div class="z-lump ">
							<h2>
								<a target="_blank"
									href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=projectvo.getTitle()%></a>
							</h2>
							<!-- 众筹中 -->
							<p class="z-raising">
								筹资<em>￥</em><i><%=projectvo.getFundamount()%></i>
							
							</p>
							
							<div>
							
									审核通过时间：<%=projectvo.getApproveendtime()%>
							</div>
							<div class="item-rate clearfix">
								<span class="rate1">单笔投资额：<%=projectvo.getFinalprice()%></span><em>￥</em>
							</div>
							<!-- 已成功 -->
						</div>
						<p class="z-assist clearfix">
							支持数：<a class="assist1" target="_blank" title="关注"
								href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=projectvo.getFocuscount()%></a>
							评论数：<a class="assist2" target="_blank" title="评论数"
								href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=ProjectCommentMgr.getInstance().getCommentsCount(
						projectvo.getPk_project())%></a>
						</p>
					</div></li>
				<%
					}
					}
					
				%>
			</ul>
		</div>
		<%} %>
		<!--focus end-->
	</div>
	<!--start of the catagoryhotproject-->
	
	<%
	
	
	 for(Iterator<ProjectCatagoryVO> it = projectcatagoryvos.iterator();it.hasNext();)
    {
    
        ProjectCatagoryVO projectcatagoryvo = it.next();
        List<ProjectVO> projectvos = new ArrayList<ProjectVO>();
       projectvos = ProjectMgr.getInstance().getProjectHotRankByCatagoryId(hotprojectbycatacount, projectcatagoryvo.getCatagoryid());
       if(projectvos.size() != 0){
     %>
    
	<div class="wrap m-box03">
		<div class="m-headline clearfix">
			<div class="project-title">
				<a href="browseproject.jsp?catagoryid=<%=projectcatagoryvo.getCatagoryid() %>" target="_blank"><%=projectcatagoryvo.getCatagoryname() %></a>
			</div>
			<a class="z-browse" href="browseproject.jsp?catagoryid=<%=projectcatagoryvo.getCatagoryid() %>">浏览全部</a>
		</div>
		<!--focus static-->
		<div class="focus-box">
			<ul class="focus-con clearfix">
			<%
					for (Iterator<ProjectVO> it1 = projectvos.iterator(); it1
							.hasNext();) {
						ProjectVO projectvo = it1.next();
				%>
					<li>
					<div class="list-item" style="background: #FFF">
						<a target="_blank" class="item-figure"
							href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>">
							<img src="uploadFile/<%=projectvo.getTitleimageurl()%>"
							alt="<%=projectvo.getTitle()%>" title="<%=projectvo.getTitle()%>">
						</a>
						<div class="z-lump ">
							<h2>
								<a target="_blank"
									href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=projectvo.getTitle()%></a>
							</h2>
							<!-- 众筹中 -->
							<p class="z-raising">
							筹资：<i><%=projectvo.getFundamount()%></i><em>￥</em>
								已筹资：<i><%=ProjectMgr.getInstance().getCompletefundamount(projectvo.getPk_project())%></i><em>￥</em>
								
							</p>
							<div class="progress-bar">
								<span class="progress bg-yellow"
									style="width:<%=ProjectMgr.getInstance().getCompletedegree(projectvo.getPk_project())/100%>%;"></span>
							</div>
							<div class="item-rate clearfix">
								<span class="rate1">完成<%=ProjectMgr.getInstance().getCompletedegree(projectvo.getPk_project())/100%>% </span> 
								<%if(projectvo.getFundlastdate()>0) {%><span
									class="rate2">剩余<%=projectvo.getFundlastdate()%>天</span><%} %>
							</div>
							<!-- 已成功 -->
						</div>
						<p class="z-assist clearfix">
							支持数：<a class="assist1" target="_blank" title="支持"
								href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=projectvo.getFocuscount()%></a>
							评论数：<a class="assist2" target="_blank" title="评论数"
								href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=ProjectCommentMgr.getInstance().getCommentsCount(
						projectvo.getPk_project())%></a>
						</p>
					</div></li>
					<%}
					} %>
			</ul>
		</div>
		<!--focus end-->
	</div>
		<%} %>
	<!--start of footer-->
	<div class="footer">
		<div class="footer-nav">
			<a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a>
			<!-- <a href="/help-team" rel="nofollow">团队介绍 <i>｜</i> </a> -->
			<a href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a
				href="#" target="_blank">新手帮助 </a>
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


