<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import='com.lechou.order.*,java.util.*'%>
<%@ page
	import='com.lechou.project.*,com.lechou.project.catagory.*,com.lechou.user.*,java.util.*,java.text.*'%>
	<%@include file="admin_sessioncheck.jsp"%>
<%
	request.setCharacterEncoding("GBk");

	final int PAGE_SIZE = 20;
	int pageNo = 1;
	String strPageNo = request.getParameter("pageNo");
	String action = request.getParameter("action");
	String requesturl = "admin_manage_order.jsp";
	
	List<OrderVO> ordervos = new ArrayList<OrderVO>();
	int totalRecords = 0;

   
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
	    OrderSearchVO ordersearchvo = (OrderSearchVO)session.getAttribute("ordersearchvo");
	    if(ordersearchvo == null)
	    {
	    OrderSearchVO ordersearchvo1 = new OrderSearchVO();
	   
		String ispaystr = request.getParameter("ispaystr");
		String projectname = request.getParameter("projectname");
		String username = request.getParameter("username");
		
	    int ispay = -1;
	    if(ispaystr != null&&!ispaystr.trim().equals("all"))
	    {
	    ispay = Integer.parseInt(ispaystr);
     	}
     	
     	ordersearchvo1.setIspay(ispay);
     	ordersearchvo1.setProjectname(projectname);
     	ordersearchvo1.setUsername(username);
     	
	    session.setAttribute("ordersearchvo",ordersearchvo1);
	    totalRecords = OrderMgr.getInstance().getorders(ordervos, pageNo, PAGE_SIZE, ordersearchvo1);
	   
	   }else{
	    String catagoryidstr = request.getParameter("catagoryidstr");
	    String ispaystr = request.getParameter("ispaystr");
		String projectname = request.getParameter("projectname");
		String username = request.getParameter("username");
		
	    int ispay = -1;
	    if(ispaystr != null&&!ispaystr.trim().equals("all"))
	    {
	    ispay = Integer.parseInt(ispaystr);
     	}
	    
	    if(ispaystr != null)
	    {
	   ordersearchvo.setIspay(ispay);
	    }
	    if(projectname != null)
	    {
	    ordersearchvo.setProjectname(projectname);
	    
	    }
	    if(username != null)
	    {
	   ordersearchvo.setUsername(username);
	    
	    } 	  
	   totalRecords = OrderMgr.getInstance().getorders(ordervos, pageNo, PAGE_SIZE, ordersearchvo);
	   }

    }else
    {
          totalRecords = OrderMgr.getInstance().getorders(ordervos, pageNo, PAGE_SIZE);
	}
	int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;

	if (pageNo > totalPages)
		pageNo = totalPages;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐筹网-后台管理-订单管理</title>
<meta http-equiv="expires" content="0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
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
	$(".meun-01").live("click", function() {
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
				</ul>			</div>
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
			<li ><a href="admin_manage_project1.jsp" class="like-project"><i></i>项目管理</a>
			</li>
				<li class="child select"><a href="admin_manage_order.jsp" class="launch-project"><i></i>订单管理</a>
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
				<form id="Js-search" name="searchForm" action="admin_manage_order.jsp"
					class="project-search" wx-validator-single-error="error"
					autocomplete="off">
					<div id="error" class="error-text"
						style="position: absolute;left: 12px;top: 0;"></div>
					<input type="hidden" name="action" value="search"> 
						
					<span>
						<select	class="pd-type" name="ispaystr">		
						<option value="all">是否支付</option>
						<option value="0">未支付</option>
						<option value="1">已支付</option>
						</select> 
					项目名称: <input type="text" name="projectname" value=""> </span> <span>用户名称: <input type="text"
						
						name="username" value=""> </span> <a
						href="javascript:document.searchForm.submit()" type="submit" class="search-btn">查询</a>
				</form>
				<form name="selectallform" method="post"
					action="admin_delete_order.jsp">
						<input type="hidden" value="<%=action%>" name="action">
							<input type="hidden" value="<%=requesturl%>" name="requesturl">
					<input type="hidden" value="batchdelete" name="action1">
				<div class="promoters-pro-con">
					<div class="promoters-pro-tit">
					<a id="batchDel"
								href="javascript:document.selectallform.submit()">批量删除</a>
							<ul>
							
                                <li class="wp10"><input value="全选" type="checkbox"
									onclick="selectControl(this);">全选</li>
								<li class="wp20">项目名称</li>
								<li class="wp14">下单人</li>
								<li class="wp14">数量</li>
								<li class="wp10">总金额</li>
								<li class="wp10">是否支付</li>
								<li class="wp10">下单时间</li>
								<li class="wp12">操作</li>
						</ul>
					</div>

					<table>
						<tbody>
						<%
									for (Iterator<OrderVO> it = ordervos.iterator(); it.hasNext();) {
										OrderVO ordervo = it.next();
								%>
							<tr>
							  <td style="width:6%">&nbsp;&nbsp;&nbsp;<input name="id"
										value="<%=ordervo.getPk_order() %>" type="checkbox">
									</td>
								<td style="width:24%"><span class="con"><%=ProjectMgr.getInstance().getProjectVOById(ordervo.getPk_project()).getTitle()%>
								</span>
								</td>
								<td style="width:14%"><span class="con"><%=UserMgr.getInstance().getUserVOById(ordervo.getPk_user()).getUsername() %></span>
								</td>
								<td style="width:14%"><span class="con"><%=ordervo.getAmount() %></span>
								</td>
								<td style="width:10%"><span class="con"><%=ordervo.getTotalsum() %></span>
								</td>
								<td style="width:10%;position:relative;" class="Js-tipwrap"><span
									class="con"><%=ordervo.getIspay()==0?"否":"是" %></span></td>
								<td style="width:10"><span class="con"> <span
										style="color:#CCCCCC"><%=new SimpleDateFormat("yyyy-MM-dd").format(ordervo.getPublishtime())%></span> </span>
								</td>
								<td style="width:12%"><span class="con"> <!--<a href="/corp-count/id-<?/*=$val['id']*/?>">查看</a>-->
										<a href="#">编辑</a> <a href="admin_delete_order.jsp?action1=singledelete&action=<%=action %>&pk_order=<%=ordervo.getPk_order()%>&requesturl=<%=requesturl %>" >删除</a> </span>
								</td>
							</tr>
							<%
									}
								%>
						</tbody>
						
					</table>
				
						<div class="pages">
											
			<a class="prev" href="admin_manage_order.jsp?pageNo=1&action=<%=action%>">首页</a>
			 <a class="prev" href="admin_manage_order.jsp?pageNo=<%=pageNo==1 ? 1:pageNo - 1%>&action=<%=action%>">&lt;</a> &nbsp;
			 <%for(int i=1;i<=totalPages;i++) {
			 %>
			 <a class="<%=(i==pageNo) ? "selected": "common-sprite"%>" href="admin_manage_order.jsp?pageNo=<%=i%>&action=<%=action%>"><%=i %></a>&nbsp;
			 <%} %>
			 <a class="next" href="admin_manage_order.jsp?pageNo=<%=pageNo==totalPages?totalPages:pageNo + 1%>&action=<%=action%>">&gt;</a>
			<a class="common-sprite" href="admin_manage_order.jsp?pageNo=<%=totalPages%>&action=<%=action%>">尾页</a>

						</div>
				</form>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$('.Js-tipwrap').hover(function() {
				$(this).find('.tip').show();
			}, function() {
				$(this).find('.tip').hide();
			});
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
