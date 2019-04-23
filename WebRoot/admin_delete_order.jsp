<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.order.*'%>

<%
	request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");//是否为search方式
	String action1 = request.getParameter("action1");//删除方式
	String requesturl = request.getParameter("requesturl");//
	
	if (action1 != null && action1.trim().equals("singledelete")) {

		String pk_order = request.getParameter("pk_order");

		if (pk_order == null || pk_order.trim().equals("")) {
			return;
		}
		if (OrderMgr.getInstance().deleteOrder(Integer.parseInt(pk_order)) == 0) {
			response.getWriter().print(
					"<script>alert('删除成功!');window.location.href='"+requesturl+"?action="+action+"'</script>");
		}
	}
	 if(action1 != null && action1.trim().equals("batchdelete")) {

		try {
			String[] strIds = request.getParameterValues("id");
			int[] ids = new int[strIds.length];
			for (int i = 0; i < strIds.length; i++) {
				ids[i] = Integer.parseInt(strIds[i]);
			}
			if (OrderMgr.getInstance().deleteOrder(ids) == 0) {
				response.getWriter().print(
						"<script>alert('批量删除成功!');window.location.href='"+requesturl+"?action="+action+"'</script>");
			}
		} catch (Exception e) {
			response.getWriter().print(
					"<script>alert('请勾选记录!');window.location.href='"+requesturl+"?action="+action+"'</script>");

		}

	}
%>