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
	
    request.setCharacterEncoding("UTF-8");
    
    final int PAGE_SIZE = 5*3;
	int pageNo = 1;
	String strPageNo = request.getParameter("pageNo");
	int totalRecords = 0;
	int no = 1;//必须更改
   
	if (strPageNo != null && !strPageNo.trim().equals("")) {
		try {
	pageNo = Integer.parseInt(strPageNo);
		} catch (NumberFormatException e) {
	pageNo = 1;
		}
	}
    
	if (pageNo <= 0)
		pageNo = 1;
		
	ProjectBrowseSearchVO searchvo = (ProjectBrowseSearchVO) session
			.getAttribute("searchvo");
			List<ProjectVO> projectvos = new ArrayList<ProjectVO>();
   
	if(searchvo != null){
	
	totalRecords = ProjectMgr.getInstance().getProjects(projectvos, pageNo, PAGE_SIZE, searchvo);
	
	}else{
	  totalRecords = ProjectMgr.getInstance().getStartedProjects(projectvos, pageNo, PAGE_SIZE);
	}
	
	int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;

	if (pageNo > totalPages)
		pageNo = totalPages;
	List<ProjectCatagoryVO> projectcatagoryvos = ProjectCatagoryMgr
	.getInstance().getProjectCatagorys();			
			
			
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐筹网-浏览项目</title>
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

<!--search panel-->
<link rel="stylesheet" type="text/css" href="css/search.css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/script.js"></script>

</head>
<body>
	<!--header static-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="m-left">
				<h2>
					<a id="nav_logo" alt="众筹网" class="img-logo " title="众筹网"href#"><img
						src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a  href="index.jsp"><span><i
								class="s-01"></i> </span>首页</a>
					</li>
					<li><a href="browseproject.jsp" target="_blank" ><i class="s-02"></i>浏览项目</a>
					</li>
					<li><a href="approveproject1.jsp" target="_blank"><i class="s-30"></i>新项目审核</a>
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
					autocomplete="off">

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
	<link rel="stylesheet" type="text/css"
		href="css/26238307520063c1bed12d636680a99c.css">
	<div class="wrap m-box ">

		<div class="type-list clearfix">
			<ul>
				<li class="li-font">分类排序：</li>
					<%
							for(Iterator<ProjectCatagoryVO> it =projectcatagoryvos.iterator();it.hasNext();)
							{
							    ProjectCatagoryVO  projectcatagoryvo = it.next();
						%>
				<li><a href="search.jsp?catagoryid=<%=projectcatagoryvo.getCatagoryid()%>"><%=projectcatagoryvo.getCatagoryname()%></a></li>
				
				<%} %>
			</ul>
		</div>
		<div class="type-list clearfix">
			<ul>
				<li class="li-font">项目进程：</li>
				
				<li><a href="search.jsp?status=众筹中">众筹中</a>
				</li>
				<li><a href="search.jsp?status=已成功">已成功</a>
				</li>
				<li><a href="search.jsp?status=即将开始">即将开始</a>
				</li>
				<li><a href="search.jsp?status=将要结束">将要结束</a>
				</li>

			</ul>
		</div>
	<!-- 	<div class="type-list clearfix">
			<ul>
				<li class="li-font">其他类别：</li>
				<li><a href="search.jsp?other=默认排序">默认排序</a>
				</li>
				<li><a href="search.jsp?other=最新上线">最新上线</a>
				</li>
				<li><a href="search.jsp?other=最高目标金额">最高目标金额</a>
				</li>
				<li><a href="search.jsp?other=最多关注人数">最多关注人数</a>
				</li>
				<li><a href="search.jsp?other=最多完成金额">最多完成金额</a>
				</li>

			</ul>
		</div> -->
		<div class="type-list clearfix">
			<ul>
				<li class="li-font">已选条件：</li>
				<%
					if (searchvo != null) {
						if (searchvo.getCatagoryid() != 0) {
				%>
				<li><a
					href="search.jsp?catagoryid=<%=searchvo.getCatagoryid()%>&action=delete"><%=ProjectCatagoryMgr.getInstance()
							.getProjectCatagoryVOById(searchvo.getCatagoryid())
							.getCatagoryname()%>x</a>
				</li>
				<%
					}  if (searchvo.getStatus() != null
								&& !searchvo.getStatus().trim().equals("")) {
				%>
				<li><a href="search.jsp?status=<%=searchvo.getStatus()%>&action=delete"><%=searchvo.getStatus()%>x</a></li>
				<%
					}if (searchvo.getOther() != null
								&& !searchvo.getOther().trim().equals("")) {
				%>
				<li><a href="search.jsp?other=<%=searchvo.getOther()%>&action=delete"><%=searchvo.getOther()%>x</a></li>
				<%
					}
					if(searchvo.getCatagoryid() == 0 && searchvo.getStatus() == null
								&& searchvo.getOther() == null) {
				%>
				<li><a href="#">暂时还没有选择条件</a></li>
				<%
					}
					} else {
				%>
				<li><a href="#">暂时还没有选择条件</a></li>
				<%
					}
				%>
			</ul>
		</div>

		<div class="focus-box">
			<ul class="focus-con clearfix">
				<!-- deal_card - widget -->


				<%
					for (Iterator<ProjectVO> it = projectvos.iterator(); it
							.hasNext();) {
						ProjectVO projectvo = it.next();
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
								<span class="rate1">完成<%=ProjectMgr.getInstance().getCompletedegree(projectvo.getPk_project())/100%>% </span> <span
									class="rate2">剩余<%=projectvo.getFundlastdate()%>天</span>
							</div>
							<!-- 已成功 -->
						</div>
						<p class="z-assist clearfix">
							关注数：<a class="assist1" target="_blank" title="关注数"
								href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=projectvo.getFocuscount()%></a>
							评论数：<a class="assist2" target="_blank" title="评论数"
								href="projectdetail.jsp?pk_project=<%=projectvo.getPk_project()%>"><%=ProjectCommentMgr.getInstance().getCommentsCount(
						projectvo.getPk_project())%></a>
						</p>
					</div></li>
				<%
					}
				%>

			</ul>
		</div>

		<div class="pager">
	<%-- 		<a href="admin_manage_order.jsp?pageNo=1&action=<%=action%>">首页</a> <a
								href="admin_manage_order.jsp?pageNo=<%=pageNo - 1%>&action=<%=action%>">上一页</a>
							<a href="admin_manage_order.jsp?pageNo=<%=pageNo + 1%>&action=<%=action%>">下一页</a>
							<a href="admin_manage_order.jsp?pageNo=<%=totalPages%>&action=<%=action%>">末页</a> --%>
							
			<a class="prev" href="browseproject.jsp?pageNo=1">首页</a>
			 <a class="prev" href="browseproject.jsp?pageNo=<%=pageNo==1 ? 1:pageNo - 1%>">&lt;</a> &nbsp;
			 <%for(int i=1;i<=totalPages;i++) {
			 %>
			 <a class="<%=(i==pageNo) ? "selected": "common-sprite"%>" href="browseproject.jsp?pageNo=<%=i%>"><%=i %></a>&nbsp;
			 <%} %>
			 <a class="next" href="browseproject.jsp?pageNo=<%=pageNo==totalPages?totalPages:pageNo + 1%>">&gt;</a>
			<a class="common-sprite" href="browseproject.jsp?pageNo=<%=totalPages%>">尾页</a>
		</div>

	</div>
<br>
<br>
	<!--start of footer-->
	<div class="footer">
		<div class="footer-nav">
			<a href="http://www.zhongchou.cn/help-index/id-1" rel="nofollow"
				target="_blank">关于我们 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/help-contact" rel="nofollow"
				target="_blank">联系我们 <i>｜</i> </a>
			<!-- <a href="/help-team" rel="nofollow">团队介绍 <i>｜</i> </a> -->
			<a href="http://www.zhongchou.cn/help-term" rel="nofollow"
				target="_blank">服务协议 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/help-specification" rel="nofollow"
				target="_blank">项目发起规范 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/special-xinshou" target="_blank">新手帮助
				<i>｜</i> </a> <a href="http://www.zhongchou.cn/help-center"
				target="_blank">帮助中心</a>
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
