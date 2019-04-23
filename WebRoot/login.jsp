<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lechou.user.*"%>

<%
	String action = request.getParameter("action");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String remember = request.getParameter("remember");
	UserVO currentUserVO = new UserVO();
    int isadmin = 0;
	if (action != null && action.trim().equals("login")) {
		Boolean b = null;
		try {
			b = UserMgr.getInstance().check(name, password, currentUserVO, isadmin);
		} catch (UserNotFoundException e) {
		out.println(e.getMessage());
		response.getWriter().print(
					"<script>alert('账户名不存在!');window.location.href='login.jsp'</script>");
		} catch (PasswordNotCorrectException e) {
			out.println(e.getMessage());
				response.getWriter().print(
					"<script>alert('密码错误!');window.location.href='login.jsp'</script>");
		}
		catch(UsertypeNotCorrectException e){
		   out.println(e.getMessage());
		   response.getWriter().print(
					"<script>alert('用户类型不匹配，无法登陆!');window.location.href='login.jsp'</script>");
		}
		if (b !=null&& b == true) {
			session.setAttribute("currentUserVO", currentUserVO);
			response.sendRedirect("index.jsp");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐筹网-用户登录</title>
<meta http-equiv="expires" content="0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/png" href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css" href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<script type="text/javascript" async="" src="js/dc.js"></script>
<script type="text/javascript" async="" src="js/ag.js"></script>
<script type="text/javascript" src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>
<script>
$(".meun-01").live("click",function(){
	location.href = $(this).data("link");
})
</script>

<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>
<script src="js/AC_ActiveX.js" type="text/javascript"></script>
<script src="js/AC_RunActiveContent.js" type="text/javascript"></script>


</head>
<body>
<!--header static-->
<div class="header">
  <div class="wrap clearfix">
    <div class="m-left">
      <h2><a id="nav_logo" alt="众筹网" class="img-logo " title="众筹网" href#"><img src="images/logo.png" alt=""/></a>
     </h2>
      <ul class="m-nav clearfix">
        <li><a  href="index.jsp"><span><i class="s-01"></i></span>首页</a></li>
        <li><a  href="browseproject.jsp" target="_blank"><i class="s-02"></i>浏览项目</a></li>
        <li><a  href="approveproject1.jsp" target="_blank"><i class="s-30"></i>新项目审核</a></li>
        <li><a  href="initialproject.jsp" target="_blank"><i class="s-03"></i>发起项目</a></li>
        <li><a href="successeproject.jsp" target="_blank"><i class="s-phone"></i>成功项目</a></li>
      </ul>
    </div>
    <div class="m-right clearfix">
    
        	<span class="z-Login">
			<a id="user_register" class="z-register Js-showRegister" href="register.jsp">注册</a>
            <a id="user_login" class="Js-showLogin" href="login.jsp">登录</a>
          </span>
        
       <!--login in-->
        <!-- <span class="z-Login">
           <a id="user_login" class="Js-showLogin" href="javascript:;">用户</a>
			<a id="user_register" class="z-register Js-showRegister" href="javascript:;">退出</a>
          </span> -->
  <form action="#" name="search" method="get" wx-validator="" autocomplete="off">

 <span>
  <input name="keywords2" wx-validator-placeholder="输入项目名称" wx-validator-rule="required" type="text" wx-validator-notip="" placeholder="输入项目名称">  
   <a href="javascript:document.search.submit()" >搜索</a>

   </span>
    
  </form>
    </div>
  </div>
</div>
<!--header end-->
<link rel="stylesheet" type="text/css" href="css/5de86140d87bd2dbed2f7d202594aff3.css">


<link rel="stylesheet" type="text/css" href="css/login_register.css">
<div class="wrap">
	<div class="logo-content">
	  <p class="login_logo">
                            <img src="images/login-logo.png">
                      </p>
	  <div class="logo_form">
	  	<h5 class="clearfix"><span>登录</span> <em>没有账号，<a href="register.jsp">马上注册</a></em></h5>
	  	<form action="login.jsp" name="loginForm" >
	  		
	  		<input type="text" name="name" wx-validator-placeholder="用户名" placeholder="用户名" >
	  		<input type="password" name="password"  wx-validator-placeholder="密码" placeholder="密码">
            <p>
                <input type="checkbox" name="remember" value="1">记住用户
            </p>
            <input type="hidden" name="action" value="login">
        
	  		<p class="p-login"><a href="javascript:document.loginForm.submit();" type="submit" class="a-login-btn">立即登录</a><a href="#" class="forget-password">忘记密码</a></p>
	  	</form>
	  </div>
    </div>
</div>
<script src="js/login.js"></script> 

<!--start of footer-->
<div class="footer">
  <div class="footer-nav"> <a href="http://www.zhongchou.cn/help-index/id-1" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a href="http://www.zhongchou.cn/help-contact" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a>
    <!-- <a href="/help-team" rel="nofollow">团队介绍 <i>｜</i> </a> -->
    <a href="http://www.zhongchou.cn/help-term" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a href="http://www.zhongchou.cn/help-specification" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a href="http://www.zhongchou.cn/special-xinshou" target="_blank">新手帮助 <i>｜</i> </a> <a href="http://www.zhongchou.cn/help-center" target="_blank">帮助中心 </a></div>
  <div class="footer-icon"> <span>copyright  ©2015  by lechou  all  right  reserved</span> </div>
</div>
<!--end of footer-->
<script type="text/javascript" src="js/fe3974b84fb6b50da0f329242078614c.js"></script>
</body>
</html>
