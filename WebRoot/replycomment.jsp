<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.lechou.project.*,com.lechou.project.comment.*'%>
<%@include file="sessioncheck.jsp"%>
<%
	String name = "";
	
	if(currentUserVO != null)
	{
	   name = currentUserVO.getUsername();
	}
	if(name == null || name.trim().equals(""))
    {
    response.getWriter().print(
			"<script>alert('亲，您还没有登录额!');window.location.href='login.jsp'</script>");
   return;
    }
    String pk_projectstr = request.getParameter("pk_project");
    int pk_project = 0;
	if (pk_projectstr != null && !pk_projectstr.trim().equals("")) {
		pk_project = Integer.parseInt(pk_projectstr);
	}
    String requesturl = request.getParameter("requesturl");
    String content = request.getParameter("content");
	
	if(ProjectCommentMgr.getInstance().replyComments(pk_project, currentUserVO.getPk_user(), content) ==0)
	{

	response.getWriter().print(
					"<script>alert('发布成功!');window.location.href='"+requesturl+"?pk_project="+pk_project+"'</script>");
	}
	%>
	