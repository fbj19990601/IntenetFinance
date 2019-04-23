<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.user.*,com.lechou.util.*'%>
<%@include file="admin_sessioncheck.jsp"%>
<%
	request.setCharacterEncoding("GBK");
	 String action = request.getParameter("action");

  
	 if (action != null && action.trim().equals("adduser")) {
	  String name = request.getParameter("name");
     String paw = request.getParameter("paw");
     String repaw = request.getParameter("repaw");
	 String sex = request.getParameter("sex");
	 String tel = request.getParameter("tel");
	 String email = request.getParameter("email");
	 String resume = request.getParameter("resume");
	 int isadmin = Integer.parseInt( request.getParameter("isadmin"));
	 
	 if(!paw.equals(repaw)){
	 	 response.getWriter()
	 .print("<script>alert('两次密码不一致，请重新输入!');window.location.href='admin_add_user.jsp'</script>");
	 
	 }else{
	 UserVO uservo = new UserVO();
	 uservo.setUsername(name);
	 uservo.setUserpassword(paw);
	 uservo.setSex(sex);
	 uservo.setTel(tel);
	 uservo.setEmail(email);
	 uservo.setResume(resume);
     uservo.setModifier(currentUserVO.getUsername());
     uservo.setIsadmin(isadmin);
     uservo.setRegister(currentUserVO.getUsername());
     uservo.setFirstlogintime(new Date());
     uservo.setLastlogintime(new Date());
     uservo.setRegistetime(new Date());
     uservo.setModifytime(new Date());
	try{
	 if (UserMgr.getInstance().addUser(uservo) == 0)
	 {
	 response.getWriter()
	 .print("<script>alert('添加成功!');window.location.href='admin_manage_user.jsp'</script>");

	 }
	 }catch(UserNameExistException e){
	 e.printStackTrace();
	  response.getWriter()
	 .print("<script>alert('用户名重复!');window.location.href='admin_add_user.jsp'</script>");
	 }
	 }
	 }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>乐筹网-后台管理-添加用户</title>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>乐筹网-个人中心</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<meta http-equiv="expires" content="0">
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
					<a id="nav_logo" alt="乐筹网" class="img-logo " title="乐筹网"href="index.jsp"><img
						src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a href="index.jsp"><span><i
								class="s-01"></i> </span>返回首页</a>
					</li>
					<li><a href="admin_manage_user.jsp" target="_blank"><i class="s-02"></i>用户管理</a>
					</li>
					<li><a href="admin_manage_project1.jsp" target="_blank"><i class="s-30"></i>项目管理</a>
					</li>
					<li><a href="admin_manage_order.jsp" target="_blank"><i class="s-03"></i>订单管理</a>
					</li>
					<li><a href="admin_update_info.jsp" target="_blank"><i class="s-phone"></i>个人设置</a>
					</li>
				</ul>
			</div>
			<div class="m-right clearfix">
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="admin_exit.jsp?operate=exit">退出</i> </span>
				</div>
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="admin_update_info.jsp"><%=currentUserVO.getUsername() %></i> </span>
				</div>
			
			</div>
		</div>
	</div>
	<!--header end-->
	<link rel="stylesheet" type="text/css"
		href="css/7ffb95504c3dc3e41f80429d1d3004ea.css">
	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">
	<div class="backstage-wrap clearfix">
	
		<ul class="backstage-left">
			<li class="first">菜单</li>
			<li><a href="admin_manage_user.jsp" class="support-project"><i></i>用户管理</a>
			</li>
			<li class="child select"><a href="admin_manage_project1.jsp" class="like-project"><i></i>项目管理</a>
			</li>
				<li><a href="admin_manage_order.jsp" class="launch-project"><i></i>订单管理</a>
			</li>
			<li><a href="admin_add_catagory.jsp" class="launch-project"><i></i>项目类别</a>
			</li>
		
			<li><a href="admin_manage_advertisement.jsp" class="launch-project"><i></i>广告营销</a>
			</li>
			<li>
				<ul>
					<li><a href="#">运营情况</a>
					</li>
				</ul></li>
			<li><a href="admin_update_info.jsp" class="set"><i></i>个人设置</a>
			</li>
		</ul>
	
			
		
		<div class="backstage-right" style="min-height:650px;" id="backcolor">
		
			<div class="personal-set-right">
				<form class="setting-form" name="addForm" action="admin_add_user.jsp">
				
					<input type="hidden" name="action" value="adduser">
					<div class="form-item clearfix">
					<div class="row clearfix">
							<label>管理员：</label> <span class="span-style"> <label
								class="label-style"> <input type="radio" name="isadmin"
									value="0" checked="checked"> 否</label> <label class="label-style"> <input
									type="radio"  name="isadmin" value="1"> 是</label>
							</span>
						</div>
						<div class="row clearfix">
							<label>用户名：</label> <input name="name" type="text"
								class="input-wid"  value="">
						</div>
						<div class="row clearfix">
							<label>密码：</label> <input name="paw" type="password"
								class="input-wid" >
						</div>
						
						<div class="row clearfix">
							<label>确认密码：</label> <input name="repaw" type="password"
								class="input-wid" >
						</div>

						<div class="row clearfix">
							<label>性别：</label> <span class="span-style"> <label
								class="label-style"> <input type="radio" name="sex"
									value="男"> 男</label> <label class="label-style"> <input
									type="radio" checked="checked" name="sex" value="女"> 女</label>
							</span>
						</div>
						<div class="row clearfix">
							<label>手机号：</label> <input name="tel" type="text"
								class="input-wid" value="" >
						</div>
						<div class="row clearfix">
							<label>邮箱：</label> <input type="text" 	name="email"  >
						</div>

						<div class="row clearfix">
							<label>个人说明：</label>
							<textarea name="resume" ></textarea>
						</div>
						
						<p class="p-btn">
							<a type="submit" href="javascript:document.addForm.submit()"
								class=" a-btn-blue btn-effect-blue">保存</a>
							
						</p>
						
					
					</div>


				</form>
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
</body>
</html>
