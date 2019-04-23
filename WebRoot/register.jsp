<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.lechou.user.*,java.util.*'%>
<%
     request.setCharacterEncoding("GBK");
	 String action = request.getParameter("action");

   
	 if (action != null && action.trim().equals("register")) {
	  String name = request.getParameter("name");
     String paw = request.getParameter("paw");
     String repaw = request.getParameter("repaw");
	 String sex = request.getParameter("sex");
	 String tel = request.getParameter("tel");
	 String email = request.getParameter("email");
	 
	 if(!paw.equals(repaw)){
	 	 response.getWriter()
	 .print("<script>alert('两次密码不一致，请重新输入!');window.location.href='register.jsp'</script>");
	 
	 }else{
	 UserVO uservo = new UserVO();
	 uservo.setUsername(name);
	 uservo.setUserpassword(paw);
	 uservo.setSex(sex);
	 uservo.setTel(tel);
	 uservo.setEmail(email);
	 uservo.setModifier(name);
     uservo.setIsadmin(0);
     uservo.setRegister(name);
     uservo.setFirstlogintime(new Date());
     uservo.setLastlogintime(new Date());
     uservo.setRegistetime(new Date());
     uservo.setModifytime(new Date());
	try{
	 if (UserMgr.getInstance().addUser(uservo) == 0)
	 {
	 response.getWriter()
	 .print("<script>alert('恭喜你注册成功,赶快去登录吧!');window.location.href='login.jsp'</script>");

	 }
	 }catch(UserNameExistException e){
	 e.printStackTrace();
	  response.getWriter()
	 .print("<script>alert('用户名重复!');window.location.href='register.jsp'</script>");
	 }
	 }
	 }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐筹网-用户注册</title>
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
<script async="" src="js/ncfpb.1.1.min.js"></script>
<script async="" src="js/zcpb.1.0.min.js"></script>
<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>
<script src="js/AC_ActiveX.js" type="text/javascript"></script>
<script src="js/AC_RunActiveContent.js" type="text/javascript"></script>

<script src="js/jquery-1.8.2.js"></script>
<script src="js/wx.js"></script>
<script src="js/wx.config.js"></script>
<script src="js/common.js"></script>
<script src="js/json2.js"></script>

<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>

</head>
<body>
<!--header static-->
<div class="header">
  <div class="wrap clearfix">
    <div class="m-left">
      <h2><a id="nav_logo" alt="众筹网" class="img-logo " title="众筹网" href#"><img src="images/logo.png" alt=""/></a> </h2>
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
<!--       <span class="z-Login"> <a id="user_login" class="Js-showLogin" href="javascript:;">用户</a> <a id="user_register" class="z-register Js-showRegister" href="javascript:;">退出</a> </span>
 -->      <form action="#" name="search" method="get" wx-validator="" autocomplete="off">
        <span>
        <input name="keywords2" wx-validator-placeholder="输入项目名称" wx-validator-rule="required" type="text" wx-validator-notip="" placeholder="输入项目名称">
        <a href="javascript:document.search.submit()" >搜索</a> </span>
      </form>
    </div>
  </div>
</div>
<!--header end-->
<link rel="stylesheet" type="text/css" href="css/5de86140d87bd2dbed2f7d202594aff3.css">
<link rel="stylesheet" type="text/css" href="css/login_register.css">
<div class="wrap">
  <div class="register-content">
    <div class="logo_form register_form">
      <h5 class="clearfix"><span>注册</span> <em>已有账号，<a href="login.jsp">立即登录</a></em></h5>
      <form action="register.jsp" name="registerForm" >
        <div id="error" class="error-text"></div>
                <input type="hidden" name="action" value="register">
        <input type="text" name="name" wx-validator-user_name-required="用户名不能为空" wx-validator-placeholder="用户名" placeholder="用户名">
        <span id="wx-validator-code-required" class="error-text hidden">不能为空</span>
        <p>
          <input type="password" name="paw"  wx-validator-placeholder="密码" wx-validator-password-required="密码不能为空" placeholder="密码">
        </p>
        <span id="wx-validator-code-required" class="error-text hidden">不能为空</span>
        <p>
          <input type="password" name="repaw" wx-validator-placeholder="确认密码" wx-validator-password-required="密码不能为空" placeholder="确认密码">
        </p>
        <p><br>
          <input type="radio" name="sex" value="男" checked>男&nbsp;&nbsp;
           <input type="radio" name="sex" value="女" >女
        </p><br>
        <p> <span id="wx-validator-verify-required" class="error-text hidden">不能为空</span>
          <input type="text" name="tel"  wx-validator-placeholder="手机号" wx-validator-mobile-required="手机号不能为空" wx-validator-mobile-mobile="手机号填写不正确" placeholder="手机号">
        </p>
        <span id="wx-validator-verify-required" class="error-text hidden">不能为空</span>
        <p>
          <input type="text" name="email"  wx-validator-user_name-required="邮箱不能为空" wx-validator-placeholder="邮箱" placeholder="邮箱">
        </p>
        <span id="wx-validator-code-required" class="error-text hidden">不能为空</span>
        <p>
          <input type="checkbox" name="article" wx-validator-article-nocheck="阅读并同意">
          阅读并同意乐筹网的<a class="agreement" href="#" target="_blank">《服务协议》</a> </p>
        <span id="wx-validator-article-nocheck" class="error-text hidden">请阅读协议</span> <a href="javascript:document.registerForm.submit();" type="submit" class="a-register-btn">立即注册</a>
      </form>
    </div> 
  </div>
</div>
<!--start of footer-->
<div class="footer">
  <div class="footer-nav"> <a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a>
    <!-- <a href="/help-team" rel="nofollow">团队介绍 <i>｜</i> </a> -->
    <a href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a href="#" target="_blank">新手帮助 </a></div>
  <div class="footer-icon"> <span>copyright  ©2015  by lechou  all  right  reserved</span> </div>
</div>
<!--end of footer-->
<script type="text/javascript" src="js/fe3974b84fb6b50da0f329242078614c.js"></script>
</body>
</html>
