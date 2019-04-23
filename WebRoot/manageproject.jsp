<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.project.*,java.util.*'%>
<%@include file="sessioncheck.jsp"%>
<%
	String name = "";
	if(currentUserVO != null)
	{
	   name = currentUserVO.getUsername();
	}
	if(name == null || name.trim().equals(""))
    {
    response.getWriter().print(
			"<script>alert('亲，您还没有登录额!');window.location.href='login.jsp'</script>");
return;
    }
	request.setCharacterEncoding("GBk");
	String requesturl ="manageproject.jsp";
	
    
	List<ProjectVO> projectvos = new ArrayList<ProjectVO>();
	int totalRecords = 0;
    totalRecords = ProjectMgr.getInstance().getOwnProjects(projectvos,currentUserVO.getPk_user());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>乐筹网-项目管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</head>
<body>
	<!--header static-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="m-left">
				<h2>
					<a  id="nav_logo" alt="乐筹网" class="img-logo " title="乐筹网"href="index.jsp"><img
						src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a  href="index.jsp"><span><i
								class="s-01"></i> </span>返回首页</a>
					</li>

					<li><a  href="manageproject.jsp" target="_blank" ><i class="s-30"></i>项目管理</a>
					</li>
					<li><a href="manageorder.jsp" target="_blank"><i class="s-03"></i>订单管理</a>
					</li>
					<li><a href="manageinfo.jsp" target="_blank"><i class="s-phone"></i>个人设置</a>
					</li>
				</ul>
			</div>
			<div class="m-right clearfix">
		
				
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="exit.jsp?operate=exit">退出</i> </span>
				</div>
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="manageproject.jsp"><%=name%></i> </span>
				</div>
			</div>
		</div>
	</div>


	<!--header end-->
	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">
	<div class="backstage-wrap clearfix">
		<ul class="backstage-left">
			<li class="first">菜单</li>
			
			<li class="child select"><a href="manageproject.jsp" class="like-project"><i></i>项目管理</a>
			</li>
			<li ><a href="manageorder.jsp" class="launch-project"><i></i>订单管理</a>
			</li>
			
			<li><a href="manageinfo.jsp" class="set"><i></i>个人设置</a>
			</li>
		</ul>
		<div class="backstage-right" style="min-height:500px;">
			<div class="backstage-right-con fill-2 clearfix">

				<div class="promoters-pro-con">
					<div class="promoters-pro-tit">
						<ul>
							<li class="wp10">项目名称</li>
							<li class="wp14">描述</li>
							<li class="wp14">单笔投资额</li>
							<li class="wp20">筹集总金额</li>
							<li class="wp10">当前状态</li>
							<li class="wp10">发起时间</li>
							<li class="wp22">操作</li>

						</ul>
					</div>

					<table>
						<tbody>

							<%
								for(Iterator<ProjectVO> it = projectvos.iterator();it.hasNext();)
								{
											ProjectVO projectvo = it.next();
							%>
							<tr>
								<td style="width:10%"><span class="con"> <%=(projectvo.getTitle()).length()>13?((projectvo.getTitle()).substring(0, 13)+"..."):projectvo.getTitle()%> </span>
								</td>
								<td style="width:14%"><span class="con"> <%=(projectvo.getDescription()).length()>23?((projectvo.getDescription()).substring(0, 23)+"..."):projectvo.getDescription()%></span>
								</td>
								<td style="width:14%"><span class="con"><%=projectvo.getFinalprice()%></span>
								</td>
								<td style="width:20%"><span class="con"><%=projectvo.getFundamount()%></span>
								</td>
								<td style="width:10%;position:relative;" class="Js-tipwrap"><span
									class="con"><%=projectvo.getStatus()%></span></td>
								<td style="width:10"><span class="con"> <span
										style="color:#CCCCCC"><%=projectvo.getPublishtime()%>
									</span> </span>
								</td>
								<td style="width:22%"><span class="con"> <!--<a href="/corp-count/id-<?/*=$val['id']*/?>">查看</a>-->
										<a href="#">编辑</a> <a
										href="deleteproject.jsp?requesturl=<%=requesturl%>&pk_project=<%=projectvo.getPk_project()%>">删除</a>
								</span>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>
					<div class="pages">
						截止到今天，您一共发起了<%=totalRecords%>个项目
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$('.Js-tipwrap').hover(function() {
				$(this).find('.tip').show();
			}, function() {
				$(this).find('.tip').hide();
			});
		});
	</script>
	<!--start of footer-->
	<div class="footer">
		<div class="footer-nav">
			<a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a
				href="#" target="_blank">新手帮助 <i>｜</i> </a> <a href="#"
				target="_blank">帮助中心 <i>｜</i> </a>
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
