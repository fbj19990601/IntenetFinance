<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.lechou.user.*'%>
<%@include file="sessioncheck.jsp"%>
<%
    String operate = request.getParameter("operate");
	
	if (operate != null && operate.trim().equals("exit")) {
		session.removeAttribute("currentUserVO");
	
			response.getWriter().print("<script>window.location.href='index.jsp'</script>");

	}

 %>
