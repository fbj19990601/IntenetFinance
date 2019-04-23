<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.project.*'%>
<%@include file="sessioncheck.jsp"%>
<%
	request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");//是否为search方式
	String action1 = request.getParameter("action1");//删除方式
	int no = Integer.parseInt(request.getParameter("no"));
	
	if (action1 != null && action1.trim().equals("singledelete")) {

		String pk_project = request.getParameter("pk_project");

		if (pk_project == null || pk_project.trim().equals("")) {
			return;
		}
		if (ProjectMgr.getInstance().deleteProject(
				Integer.parseInt(pk_project)) == 0) {
			response.getWriter().print(
					"<script>alert('删除成功!');window.location.href='admin_manage_project"
							+ no + ".jsp?action="+action+"'</script>");
		}
	}
	 if(action1 != null && action1.trim().equals("batchdelete")) {

		try {
			String[] strIds = request.getParameterValues("id");
			int[] ids = new int[strIds.length];
			for (int i = 0; i < strIds.length; i++) {
				ids[i] = Integer.parseInt(strIds[i]);
			}
			if (ProjectMgr.getInstance().deleteProject(ids) == 0) {
				response.getWriter().print(
						"<script>alert('批量删除成功!');window.location.href='admin_manage_project"
								+ no + ".jsp?action="+action+"'</script>");
			}
		} catch (Exception e) {
			response.getWriter().print(
					"<script>alert('请勾选记录!');window.location.href='admin_manage_project"
							+ no + ".jsp?action="+action+"'</script>");

		}

	}
%>