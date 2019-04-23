<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.project.*'%>
<%@include file="sessioncheck.jsp"%>
<%
	request.setCharacterEncoding("GBK");
	String requesturl = request.getParameter("requesturl");//请求页面的url
	
	
	if (requesturl != null && !requesturl.trim().equals("")) {

		String pk_project = request.getParameter("pk_project");

		if (pk_project == null || pk_project.trim().equals("")) {
			return;
		}
		if (ProjectMgr.getInstance().deleteProject(
				Integer.parseInt(pk_project)) == 0) {
			response.getWriter().print(
					"<script>alert('删除成功!');window.location.href='"+requesturl+"'</script>");
		}
	}

%>
