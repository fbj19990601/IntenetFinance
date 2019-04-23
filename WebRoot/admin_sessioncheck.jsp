<%@ page import="com.lechou.user.*"%>
<%
  UserVO currentUserVO =(UserVO) session.getAttribute("currentUserVO");
  if(currentUserVO== null)
  {
  response.sendRedirect("admin_login.jsp");
  }
   if(currentUserVO.getIsadmin()== 0)
  {
  response.sendRedirect("admin_login.jsp");
  }

%>