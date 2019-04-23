<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.lechou.user.*"%>

<%
    
	String action = request.getParameter("action");
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	UserVO currentUserVO = new UserVO();
    int isadmin = 1;
	if (action != null && action.trim().equals("login")) {
		Boolean b = null;
		try {
			b = UserMgr.getInstance().check(name, password, currentUserVO, isadmin);
		} catch (UserNotFoundException e) {
		out.println(e.getMessage());
		response.getWriter().print(
					"<script>alert('账户名不存在!');window.location.href='admin_login.jsp'</script>");
		} catch (PasswordNotCorrectException e) {
			out.println(e.getMessage());
				response.getWriter().print(
					"<script>alert('密码错误!');window.location.href='admin_login.jsp'</script>");
		}catch(UsertypeNotCorrectException e){
		   out.println(e.getMessage());
		   response.getWriter().print(
					"<script>alert('用户类型不匹配，无法登陆!');window.location.href='admin_login.jsp'</script>");
		}
		if (b !=null&& b == true) {
			session.setAttribute("currentUserVO", currentUserVO);
			response.sendRedirect("admin_manage_user.jsp");
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="expires" content="0">
<title>后台管理-登录</title>
<link href="css/admin_login.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="admin_login_wrap">
    <h1>后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form action="admin_login.jsp" method="post">
                <ul class="admin_items">
                    <li>
                        <label for="name">用户名：</label>
                         <input	type="hidden" name="action" value="login" />
                        <input type="text" name="name" id="user" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="password">密码：</label>
                        <input type="password" name="password"  id="pwd" size="40" class="admin_input_style" />
                    </li>
                    <li>
                        <input type="submit" tabindex="3" value="提交" class="btn btn-primary" />
                    </li>
                </ul>
            </form>
        </div>
    </div><br><br><br>
        <p class="admin_copyright"><a tabindex="5" href="index.html" target="_blank">返回首页</a> &copy; 2015 designed by lechou.cn</p>
</div>
</body>
</html>