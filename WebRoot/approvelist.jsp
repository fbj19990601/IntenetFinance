<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.lechou.project.*,com.lechou.project.approvelist.*'%>
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
    String requesturl = request.getParameter("requesturl");
   
    
    String pk_projectstr = request.getParameter("pk_project");
    String resultstr = request.getParameter("result");
	int pk_project = 0;
	int result = 0;
	if (pk_projectstr != null && !pk_projectstr.trim().equals("")) {
		pk_project = Integer.parseInt(pk_projectstr);
	}
	if(ProjectApprovelistMgr.getInstance().isUserApproveProject(currentUserVO.getPk_user(), pk_project) == 1){
    response.getWriter().print(
					"<script>alert('一个账号只能参与一次!');window.location.href='"+requesturl+"?pk_project="+pk_project+"'</script>");
    }else{
    if (resultstr != null && !resultstr.trim().equals("")) {
		result = Integer.parseInt(resultstr);
	}
	if(ProjectApprovelistMgr.getInstance().addApprove(currentUserVO.getPk_user(), pk_project, result) ==0)
	{

	response.getWriter().print(
					"<script>alert('谢谢参与!');window.location.href='"+requesturl+"?pk_project="+pk_project+"'</script>");
	}
	}
	%>
	