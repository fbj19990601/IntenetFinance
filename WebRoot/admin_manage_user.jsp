<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='com.lechou.user.*,java.util.*,java.text.*'%>
<%@include file="admin_sessioncheck.jsp"%>
    <%
	request.setCharacterEncoding("GBk");

	final int PAGE_SIZE = 10;
	int pageNo = 1;
	String strPageNo = request.getParameter("pageNo");
	String action = request.getParameter("action");
	
    List<UserVO> uservos = new ArrayList<UserVO>();
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
	    UserSearchVO usersearchvo = (UserSearchVO)session.getAttribute("usersearchvo");
	    if(usersearchvo == null)
	    {
	    UserSearchVO usersearchvo1 = new UserSearchVO(); 
		String usercatagorystr = request.getParameter("usercatagorystr");
		String sex = request.getParameter("sex");
		String teloremail = request.getParameter("teloremail");
		String name = request.getParameter("name");
		int usercatagory = -1;
		if(usercatagorystr != null&&!usercatagorystr.trim().equals("all"))
	    {
	    usercatagory = Integer.parseInt(usercatagorystr);
     	}
	    usersearchvo1.setIsadmin(usercatagory);
	    usersearchvo1.setUsername(name);
	    usersearchvo1.setTel(teloremail);
	    usersearchvo1.setEmail(teloremail);
	    usersearchvo1.setSex(sex);
	    
	    session.setAttribute("usersearchvo",usersearchvo1);
	    totalRecords = UserMgr.getInstance().getUsers(uservos, pageNo, PAGE_SIZE, usersearchvo1);
	   
	   }else{
	    String usercatagorystr = request.getParameter("usercatagorystr");
		String sex = request.getParameter("sex");
		String teloremail = request.getParameter("teloremail");
		String name = request.getParameter("name");
		int usercatagory = -1;
		if(usercatagorystr != null&&!usercatagorystr.trim().equals("all"))
	    {
	    usercatagory = Integer.parseInt(usercatagorystr);
     	}
	    
	     if(usercatagorystr != null)
	    {
	    usersearchvo.setIsadmin(usercatagory);
	    }
	    if(sex != null)
	    {
	    usersearchvo.setSex(sex);
	    
	    }
	    if(teloremail != null)
	    {
	    usersearchvo.setEmail(teloremail);
	    usersearchvo.setTel(teloremail);   
	    }
	   
        if(name != null){
        usersearchvo.setUsername(name);	    
	    
	   }
	   
	   totalRecords = UserMgr.getInstance().getUsers(uservos, pageNo, PAGE_SIZE, usersearchvo);
	   }

    }else
    {
          totalRecords = UserMgr.getInstance().getUsers(uservos,
		pageNo, PAGE_SIZE);
	}
	int totalPages = (totalRecords + PAGE_SIZE - 1) / PAGE_SIZE;

	if (pageNo > totalPages)
		pageNo = totalPages;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>乐筹网-后台管理-用户管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="expires" content="0">
<meta charset="utf-8">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/png" href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css" href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<script type="text/javascript" async="" src="js/dc.js"></script>
<script type="text/javascript" async="" src="js/ag.js"></script>
<script type="text/javascript" src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>
<script type="text/javascript" async="" src="js/selectall.js"></script>

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
<link rel="stylesheet" type="text/css" href="css/5de86140d87bd2dbed2f7d202594aff3.css">
<div class="backstage-wrap clearfix">
	<ul class="backstage-left">
			<li class="first">菜单</li>
			<li class="child select"><a href="admin_manage_user.jsp" class="support-project"><i></i>用户管理</a>
			</li>
			<li ><a href="admin_manage_project1.jsp" class="like-project"><i></i>项目管理</a>
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
        <form id="Js-search" name="searchForm" action="admin_manage_user.jsp" class="project-search"  autocomplete="off">
        <input type="hidden" name="action" value="search"> 
        <div id="error" class="error-text" style="position: absolute;left: 12px;top: 0;"></div>
         <select class="pd-type" name="usercatagorystr">
          <option value="-1">用户类别</option>
          <option value="0">普通用户</option>
          <option value="1">管理员</option>
        </select>
        <select class="pd-type" name="sex">
          <option value="">性别</option>
          <option value="男">男性</option>
          <option value="女">女性</option>
        </select>
        <span>

        用户名称:<input type="text"  name="name" value="">
        </span> <span>
      手机号或邮箱:  <input type="text"  name="teloremail" value="">
        </span> <a href="javascript:document.searchForm.submit()" type="submit" class="search-btn">查询</a>
      </form>
      
      <form name="selectallform" method="post"		action="admin_delete_user.jsp">
		<input type="hidden" value="<%=action%>" name="action">
		<input type="hidden" value="batchdelete" name="action1">
      <div class="promoters-pro-con">
        <div class="promoters-pro-tit">
           <a  href="admin_add_user.jsp">  添加用户</a>
        <a id="batchDel" href="javascript:document.selectallform.submit()">
                      批量删除</a>
                    
          <ul>
        <li class="wp10">
          <input value="全选" type="checkbox"						onclick="selectControl(this);">全选</li>
								<li class="wp10">用户名</li>
								<li class="wp10">是否管理员</li>
								<li class="wp10">性别</li>
								<li class="wp14">手机号</li>
								<li class="wp20">邮箱</li>
								<li class="wp14">注册日期</li>
								<li class="wp12">操作</li>

          </ul>
        </div>
        <table>
          <tbody>
          <%
			for (Iterator<UserVO> it = uservos.iterator(); it.hasNext();) {
			UserVO uservo = it.next();
		%>
            <tr>
            <td style="width:6%">&nbsp;&nbsp;&nbsp;<input name="id"
										value="<%=uservo.getPk_user()%>" type="checkbox">
									</td>
              <td style="width:14%"><span class="con"><%=uservo.getUsername() %></span></td>
              <td style="width:7%"><span class="con"><%=uservo.getIsadmin()==0?"否":"是" %></span></td>
              <td style="width:5%"><span class="con"><%=uservo.getSex() %></span></td>
              <td style="width:14%"><span class="con"><%=uservo.getTel() %></span></td>
              <td style="width:20%;position:relative;" class="Js-tipwrap"><span class="con"><%=uservo.getEmail() %></span> </td>
              <td style="width:17%"><span class="con"> <span style="color:#CCCCCC"><%=new SimpleDateFormat("yyyy-MM-dd").format(uservo.getRegistetime()) %> </span> </span></td>
              <td style="width:17%"><span class="con">
                <!--<a href="/corp-count/id-<?/*=$val['id']*/?>">查看</a>-->
                <a href="admin_update_user.jsp?pk_user=<%=uservo.getPk_user()%>">编辑</a> <a href="admin_delete_user.jsp?action1=singledelete&action=<%=action %>&pk_user=<%=uservo.getPk_user()%>" >删除</a> </span></td>
            </tr>
          <%} %>
          </tbody>
        </table>
    		<div class="pages">
											
			<a class="prev" href="admin_manage_user.jsp?pageNo=1&action=<%=action%>">首页</a>
			 <a class="prev" href="admin_manage_user.jsp?pageNo=<%=pageNo==1 ? 1:pageNo - 1%>&action=<%=action%>">&lt;</a> &nbsp;
			 <%for(int i=1;i<=totalPages;i++) {
			 %>
			 <a class="<%=(i==pageNo) ? "selected": "common-sprite"%>" href="admin_manage_user.jsp?pageNo=<%=i%>&action=<%=action%>"><%=i %></a>&nbsp;
			 <%} %>
			 <a class="next" href="admin_manage_user.jsp?pageNo=<%=pageNo==totalPages?totalPages:pageNo + 1%>&action=<%=action%>">&gt;</a>
			<a class="common-sprite" href="admin_manage_user.jsp?pageNo=<%=totalPages%>&action=<%=action%>">尾页</a>

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
  <div class="footer-nav"> <a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a href="#" target="_blank">新手帮助 <i>｜</i> </a> <a href="#" target="_blank">帮助中心 <i>｜</i> </a> </div>
  <div class="footer-icon"> <span>copyright  ©2015  by lechou  all  right  reserved</span> </div>
</div>
<!--end of footer-->
<script type="text/javascript" src="js/fe3974b84fb6b50da0f329242078614c.js"></script>
</body>
</html>
