<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.user.*'%>

<%
	request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");//是否为search方式
	String action1 = request.getParameter("action1");//删除方式
	
	
	if (action1 != null && action1.trim().equals("singledelete")) {

		String pk_user = request.getParameter("pk_user");

		if (pk_user == null || pk_user.trim().equals("")) {
			return;
		}
		if (UserMgr.getInstance().deleteUser(Integer.parseInt(pk_user)) == 0) {
			response.getWriter().print(
					"<script>alert('删除成功!');window.location.href='admin_manage_user.jsp?action="+action+"'</script>");
		}
	}
	 if(action1 != null && action1.trim().equals("batchdelete")) {

		try {
			String[] strIds = request.getParameterValues("id");
			int[] ids = new int[strIds.length];
			for (int i = 0; i < strIds.length; i++) {
				ids[i] = Integer.parseInt(strIds[i]);
			}
			if (UserMgr.getInstance().deleteUser(ids) == 0) {
				response.getWriter().print(
						"<script>alert('批量删除成功!');window.location.href='admin_manage_user.jsp?action="+action+"'</script>");
			}
		} catch (Exception e) {
			response.getWriter().print(
					"<script>alert('请勾选记录!');window.location.href='admin_manage_user.jsp?action="+action+"'</script>");

		}

	}
%>