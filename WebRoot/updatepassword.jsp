<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ page import='com.lechou.user.*,com.lechou.util.*'%>
<%@include file="sessioncheck.jsp"%>
<%
	String name = "";
	if (currentUserVO != null) {
		name = currentUserVO.getUsername();
	}
	if (name == null || name.trim().equals("")) {
		response.getWriter()
				.print("<script>alert('亲，您还没有登录额!');window.location.href='login.jsp'</script>");
		return;
	}
	request.setCharacterEncoding("GBK");

	String oldpwd = request.getParameter("oldpwd");
	String newpwd = request.getParameter("newpwd");
	String repwd = request.getParameter("repwd");
	String action = request.getParameter("action");

	if (action != null && action.trim().equals("updatepassword")) {
		if (!currentUserVO.getUserpassword().trim()
				.equals(PasswordEncryptor.GetMD5Code(oldpwd))) {
			response.getWriter()
					.print("<script>alert('原密码输入不正确!');window.location.href='updatepassword.jsp'</script>");
		} else {

			currentUserVO.setUserpassword(PasswordEncryptor
					.GetMD5Code(newpwd));
			if (!newpwd.trim().equals(repwd)) {
				response.getWriter()
						.print("<script>alert('新密码与确认密码不一致!');window.location.href='updatepassword.jsp'</script>");
			} else {
				if (UserMgr.getInstance().updateUser(currentUserVO) == 0) {
					response.getWriter()
							.print("<script>alert('保存成功!');window.location.href='updatepassword.jsp'</script>");

				}
			}

		}

	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">

<title>乐筹网-修改密码</title>
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
					<a id="nav_logo" alt="乐筹网" class="img-logo " title="乐筹网"
						href="index.jsp"><img src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a href="index.jsp"><span><i class="s-01"></i>
						</span>返回首页</a></li>

					<li><a href="manageproject.jsp" target="_blank"><i
							class="s-30"></i>项目管理</a></li>
					<li><a href="manageorder.jsp" target="_blank"><i
							class="s-03"></i>订单管理</a></li>
					<li><a href="manageinfo.jsp" target="_blank"><i
							class="s-phone"></i>个人设置</a></li>
				</ul>
			</div>
			<div class="m-right clearfix">
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="exit.jsp?operate=exit#">退出</i> </span>
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
		href="css/7ffb95504c3dc3e41f80429d1d3004ea.css">

	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">
	<div class="wrap pb116 clearfix">
		<h2 class="personal-set">
			<a href="manageproject.jsp" class="mm-blue ml-pm a-personal-set  ">返回后台主页</a>个人设置
		</h2>
		<!--left side static-->
		<div class="personal-set-left">
			<ul>
				<li><a href="manageinfo.jsp">资料修改</a></li>
				<li class="selected"><a href="updatepassword.jsp">密码修改</a></li>
				<li><a href="updateheadicon.jsp">头像修改</a></li>
				<li><a href="addaddress.jsp">收件地址管理</a></li>

			</ul>
		</div>
		<!--left side end-->

		<!--right side static-->

		<div class="personal-set-right">
			<form class="setting-form" name="modify" action="updatepassword.jsp"
				method="get">
				<input type="hidden" name="action" value="updatepassword">
				<div class="form-item clearfix">

					<div class="row clearfix">
						<label>旧密码：</label> <input name="oldpwd" type="password"
							class="input-wid" value="">
					</div>
					<div class="row clearfix">
						<label>新密码：</label> <input name="newpwd" type="password"
							class="input-wid" value="">
					</div>
					<div class="row clearfix">
						<label>确认新密码：</label> <input name="repwd" type="password"
							class="input-wid" value="">
					</div>

					<p class="p-btn">
						<a type="submit" href="javascript:document.modify.submit();"
							class=" a-btn-blue btn-effect-blue">保存</a>
					</p>
				</div>
			</form>
		</div>
		<!--right side end-->
	</div>
	<div id="foottop"></div>
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
