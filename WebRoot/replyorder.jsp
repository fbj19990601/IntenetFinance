<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.lechou.project.*,com.lechou.project.approvelist.*,com.lechou.order.*,java.util.*'%>
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
    String action = request.getParameter("action");
	int pk_project = 0;

	if (pk_projectstr != null && !pk_projectstr.trim().equals("")) {
		pk_project = Integer.parseInt(pk_projectstr);
	}
   ProjectVO vo = ProjectMgr.getInstance().getProjectVOById(pk_project);
	if(action != null && action.trim().equals("reply"))
	{
	    OrderVO ordervo = new OrderVO();

	    ordervo.setPk_project(pk_project);
	    ordervo.setPk_user(currentUserVO.getPk_user());
	    ordervo.setAmount(1);
	    ordervo.setTotalsum(vo.getFinalprice());
	    ordervo.setIspay(1);
	    ordervo.setPayway("支付宝");
	    ordervo.setOrdertype("投资");
	    ordervo.setIsshiping(0);
	    ordervo.setPublishtime(new Date());
	    ordervo.setModifier(currentUserVO.getUsername());
	    ordervo.setModifytime(new Date());
		if(OrderMgr.getInstance().addOrder(ordervo) == 0){
		 response.getWriter().print(
					"<script>alert('下单成功');window.location.href='"+requesturl+"?pk_project="+pk_project+"'</script>");
		}
	
	}
	if(action != null && action.trim().equals("focus")){
    Cookie[] cookies = request.getCookies();
     String focusname = "";
    String focusproject = "";
    String[] focusnames;
     String[] focusprojects;
    for(int i=0;i<cookies.length;i++){
    Cookie cookie = cookies[i];
  
    if(cookie.getName().equals("focusname")){
    focusname = cookie.getValue();
     }
     if(cookie.getName().equals("focusproject")){
    focusproject = cookie.getValue();
     }
    focusnames = focusname.split("&");
    focusprojects = focusproject.split("&");
    for(int k=0;k<focusnames.length;k++)
    {
    for(int j=0;j<focusprojects.length;j++){
    if(currentUserVO.getUsername().trim().equals(focusnames[k])&&pk_projectstr.trim().equals(focusprojects[j]))
     response.getWriter().print(
					"<script>alert('你已经关注过了，不能重复关注!');window.location.href='"+requesturl+"?pk_project="+pk_project+"'</script>");
    }
    
    }
    }
	vo.setFocuscount(vo.getFocuscount()+1);
	if(ProjectMgr.getInstance().updateProject(vo) == 0){
	
    response.addCookie(new Cookie("focusname",focusname+"&"+currentUserVO.getUsername()));   
    response.addCookie(new Cookie("focusproject",focusproject+"&"+pk_projectstr));   
	response.getWriter().print(
					"<script>alert('谢谢参与!');window.location.href='"+requesturl+"?pk_project="+pk_project+"'</script>");
	}    
	}
	%>
	