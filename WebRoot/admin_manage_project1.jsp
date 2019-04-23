<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import='com.lechou.project.*,com.lechou.project.catagory.*,com.lechou.user.*,java.util.*,java.text.*'%>
<%@include file="admin_sessioncheck.jsp"%>
<%
	request.setCharacterEncoding("UTF-8");
   
	final int PAGE_SIZE = 20;
	int pageNo = 1;
	String strPageNo = request.getParameter("pageNo");
	String action = request.getParameter("action");
	String requesturl = "admin_manage_project1.jsp";
	
	String status = "全部项目";//复制网页必须修改
	List<ProjectVO> projectvos = new ArrayList<ProjectVO>();
	int totalRecords = 0;
	int no = 1;//必须更改
   
	if (strPageNo != null && !strPageNo.trim().equals("")) {
		try {
	pageNo = Integer.parseInt(strPageNo);
		} catch (NumberFormatException e) {
	pageNo = 1;
		}
	}
    
	if (pageNo <= 0)
		pageNo = 1;

	
   	if(action!=null &&action.trim().equals("search"))
	{	
	    ProjectSearchVO projectsearchvo = (ProjectSearchVO)session.getAttribute("projectsearchvo");
	    if(projectsearchvo == null)
	    {
	    ProjectSearchVO projectsearchvo1 = new ProjectSearchVO();
	   
		String catagoryidstr = request.getParameter("catagoryidstr");
	    int catagoryid = -1;
	    if(catagoryidstr != null&&!catagoryidstr.trim().equals("all"))
	    {
	    catagoryid = Integer.parseInt(catagoryidstr);
     	}
	    String title = request.getParameter("title");
	    String projectaddress = request.getParameter("address");
	    
	    projectsearchvo1.setTitle(title);
	    projectsearchvo1.setProjectaddress(projectaddress);
	    projectsearchvo1.setCatagoryid(catagoryid);
	    projectsearchvo1.setStatus(status);
	    session.setAttribute("projectsearchvo",projectsearchvo1);
	    totalRecords = ProjectMgr.getInstance().getProjects(projectvos, pageNo, PAGE_SIZE, projectsearchvo1);
	   
	   }else{
	   String catagoryidstr = request.getParameter("catagoryidstr");
	    int catagoryid = -1;
	    if(catagoryidstr != null&&!catagoryidstr.trim().equals("all"))
	    {
	    catagoryid = Integer.parseInt(catagoryidstr);
     	}
	    String title = request.getParameter("title");
	    String projectaddress = request.getParameter("address");
	    String status1 = request.getParameter("status1");//全部项目要需要根据状态查询，所以需要获得status。
	    
	    if(title != null)
	    {
	    projectsearchvo.setTitle(title);
	    }
	    if(projectaddress != null)
	    {
	    projectsearchvo.setProjectaddress(projectaddress);
	    
	    }
	    if(catagoryidstr != null)
	    {
	    projectsearchvo.setCatagoryid(catagoryid);
	    
	    }
	   
        if(status1 != null){
        projectsearchvo.setStatus(status1);	    
	    
	   }
	      	  
	   totalRecords = ProjectMgr.getInstance().getProjects(projectvos, pageNo, PAGE_SIZE, projectsearchvo);
	   }

    }else
    {
          totalRecords = ProjectMgr.getInstance().getProjectsFilterByStatus(projectvos,
		pageNo, PAGE_SIZE,status);
	}
	int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;

	if (pageNo > totalPages)
		pageNo = totalPages;
	List<ProjectCatagoryVO> projectcatagoryvos = ProjectCatagoryMgr
	.getInstance().getProjectCatagorys();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>乐筹网-后台管理-项目管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="expires" content="0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<meta name="respire" content="0">
<link rel="shortcut icon" type="image/png"
	href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css"
	href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<script type="text/javascript" async="" src="js/dc.js"></script>
<script type="text/javascript" async="" src="js/ag.js"></script>
<script type="text/javascript" async="" src="js/selectall.js"></script>
<script type="text/javascript"
	src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>
<script>
$(".meun-01").live("click",function(){
	location.href = $(this).data("link");
})
</script>

<script async="" src="js/ncfpb.1.1.min.js"></script>
<script async="" src="js/zcpb.1.0.min.js"></script>
<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>
</head>
<body>
	<!--header static-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="m-left">
				<h2>
					<a id="nav_logo" alt="乐筹网" class="img-logo " title="乐筹网"href="index.jsp"><img
						src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a href="index.jsp"><span><i
								class="s-01"></i> </span>返回首页</a>
					</li>
					<li><a href="admin_manage_user.jsp" target="_blank"><i class="s-02"></i>用户管理</a>
					</li>
					<li><a href="admin_manage_project1.jsp" target="_blank"><i class="s-30"></i>项目管理</a>
					</li>
					<li><a href="admin_manage_order.jsp" target="_blank"><i class="s-03"></i>订单管理</a>
					</li>
					<li><a href="admin_update_info.jsp" target="_blank"><i class="s-phone"></i>个人设置</a>
					</li>
				</ul>
			</div>
			<div class="m-right clearfix">
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="admin_exit.jsp?operate=exit">退出</i> </span>
				</div>
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="admin_update_info.jsp"><%=currentUserVO.getUsername() %></i> </span>
				</div>
			
			</div>
		</div>
	</div>


	<!--header end-->
	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">
	<div class="backstage-wrap clearfix">
		<ul class="backstage-left">
			<li class="first">菜单</li>
			<li><a href="admin_manage_user.jsp" class="support-project"><i></i>用户管理</a>
			</li>
			<li class="child select"><a href="admin_manage_project1.jsp" class="like-project"><i></i>项目管理</a>
			</li>
				<li><a href="admin_manage_order.jsp" class="launch-project"><i></i>订单管理</a>
			</li>
			<li><a href="#" class="launch-project"><i></i>项目类别</a>
			</li>
		
			<li><a href="#" class="launch-project"><i></i>广告营销</a>
			</li>
			<li>
				<ul>
					<li><a href="#">运营情况</a>
					</li>
				</ul></li>
			<li><a href="admin_update_info.jsp" class="set"><i></i>个人设置</a>
			</li>
		</ul>
		<div class="backstage-right" style="min-height:500px;">
			<div class="backstage-right-con fill-2 clearfix">
			
				<form id="Js-search" name="searchForm"
					action="admin_manage_project<%=no%>.jsp" class="project-search"
					method="post" autocomplete="off">
					<div id="error" class="error-text"
						style="position: absolute;left: 12px;top: 0;"></div>
					<input type="hidden" name="action" value="search"> 
						<%
							if(status.equals("全部项目")){
						%>
					<select class="pd-type" name="status1">
							<option value="全部项目">项目状态</option>
							<option value="未审核">未审核</option>
							<option value="审核未通过">审核未通过</option>
							<option value="即将开始">即将开始</option>
							<option value="众筹中">众筹中</option>
							<option value="即将结束">即将结束</option>
							<option value="已成功">已成功</option>
							<option value="已失败">已失败</option>
					</select>
				
						<%
							}
						%>
					<select	class="pd-type" name="catagoryidstr">
									
						<option value="all">所属类别</option>
						<%
							for(Iterator<ProjectCatagoryVO> it =projectcatagoryvos.iterator();it.hasNext();){
							    ProjectCatagoryVO  projectcatagoryvo = it.next();
						%>
						<option value="<%=projectcatagoryvo.getCatagoryid()%>"><%=projectcatagoryvo.getCatagoryname()%></option>
						<%
							}
						%>

					</select> <span> 项目名称：<input type="text" name="title" value=""> </span> <span>项目地点： <input
						type="text" name="address" value=""> </span> <a
						href="javascript:document.searchForm.submit()" type="submit"
						class="search-btn">查询</a>
				</form>
				<form name="selectallform" method="post"
					action="admin_delete_project.jsp">
					<input type="hidden" value="<%=no%>" name="no">
					<input type="hidden" value="<%=action%>" name="action">
					<input type="hidden" value="batchdelete" name="action1">
					<div class="promoters-pro-con">
						<div class="promoters-pro-tit">
							<a id="batchDel"
								href="initialproject.jsp">发起项目</a>
								<a id="batchDel"
								href="javascript:document.selectallform.submit()">批量删除</a>
							<ul>
								<li class="wp10"><input value="全选" type="checkbox"
									onclick="selectControl(this);">全选</li>
								<li class="wp20">项目名称</li>
								<li class="wp14">发起人</li>
								<li class="wp14">所属类别</li>
								<li class="wp10">项目状态</li>
								<li class="wp10">项目地点</li>
								<li class="wp10">发布日期</li>
								<li class="wp12">操作</li>
							</ul>
						</div>
						<table>
							<tbody>
								<%
									for (Iterator<ProjectVO> it = projectvos.iterator(); it.hasNext();) {
																																	ProjectVO projectvo = it.next();
								%>
								<tr>
									<td style="width:6%">&nbsp;&nbsp;&nbsp;<input name="id"
										value="<%=projectvo.getPk_project()%>" type="checkbox">
									</td>
									<td style="width:24%"><span class="con"><%=projectvo.getTitle()%></span>
									</td>
									<td style="width:14%"><span class="con"><%=UserMgr.getInstance().getUserVOById(projectvo.getInitiatorid()).getUsername()%></span>
									</td>
									<td style="width:14%"><span class="con"><%=ProjectCatagoryMgr.getInstance().getProjectCatagoryVOById(projectvo.getCatagoryid()).getCatagoryname()%></span>
									</td>
									<td style="width:10%"><span class="con"><%=projectvo.getStatus()%></em>
									</span>
									</td>
									<td style="width:10%;position:relative;" class="Js-tipwrap"><span
										class="con"><%=projectvo.getProjectaddress()%></span></td>
									<td style="width:10%"><span class="con"> <span
											style="color:#CCCCCC"> <%=new SimpleDateFormat("yyyy-MM-dd").format(projectvo.getPublishtime())%>
										</span> </span>
									</td>
									<td style="width:12%"><span class="con"> <!--<a href="/corp-count/id-<?/*=$val['id']*/?>">查看</a>-->

											<a href="admin_update_project.jsp">编辑</a> <a
											href="admin_delete_project.jsp?action1=singledelete&action=<%=action %>&pk_project=<%=projectvo.getPk_project()%>&no=<%=no%>"
											onclick="return confirm('确定将此记录删除?')">删除</a> </span>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table>

						<div class="pages">
											
			<a class="prev" href="admin_manage_project1.jsp?pageNo=1&action=<%=action%>">首页</a>
			 <a class="prev" href="admin_manage_project1.jsp?pageNo=<%=pageNo==1 ? 1:pageNo - 1%>&action=<%=action%>">&lt;</a> &nbsp;
			 <%for(int i=1;i<=totalPages;i++) {
			 %>
			 <a class="<%=(i==pageNo) ? "selected": "common-sprite"%>" href="admin_manage_project1.jsp?pageNo=<%=i%>&action=<%=action%>"><%=i %></a>&nbsp;
			 <%} %>
			 <a class="next" href="admin_manage_project1.jsp?pageNo=<%=pageNo==totalPages?totalPages:pageNo + 1%>&action=<%=action%>">&gt;</a>
			<a class="common-sprite" href="admin_manage_project1.jsp?pageNo=<%=totalPages%>&action=<%=action%>">尾页</a>

						</div>
				</form>
			</div>
		</div>

	</div>
	<script>
    $(function(){
        $('.Js-tipwrap').hover(
            function(){
                $(this).find('.tip').show();
            },
            function(){
                $(this).find('.tip').hide();
            }
        );
    });
</script>
	<!--start of footer-->
	<div class="footer">
		<div class="footer-nav">
			<a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a
				href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a
				href="#" target="_blank">新手帮助 <i>｜</i> </a> <a href="#"
				target="_blank">帮助中心 <i>｜</i> </a>
		</div>
		<div class="footer-icon">
			<span>copyright ©2015 by lechou all right reserved</span>
		</div>
	</div>
	<!--end of footer-->
	<script type="text/javascript"
		src="js/fe3974b84fb6b50da0f329242078614c.js"></script>
</body>
</html>