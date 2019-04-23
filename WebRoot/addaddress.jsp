<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <%@ page import='com.lechou.user.*,java.util.*'%>
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
	request.setCharacterEncoding("GBK");
	String action = request.getParameter("action");
    List<ShippingAddressVO> shippaddressvos = new ArrayList<ShippingAddressVO>();
    shippaddressvos = ShippingAddressMgr.getInstance().getOwnShippingAddresss(currentUserVO.getPk_user());
    
    
	 if (action != null && action.trim().equals("addaddress")) {
	  String receiver = request.getParameter("receiver");
     String tel = request.getParameter("tel");
     String address = request.getParameter("address");
	 String postcode = request.getParameter("postcode");

	 ShippingAddressVO shippingaddressvo = new ShippingAddressVO();
	 shippingaddressvo.setPk_user(currentUserVO.getPk_user());
	 shippingaddressvo.setReceiver(receiver);
	 shippingaddressvo.setTel(tel);
	 shippingaddressvo.setAddress(address);
	 shippingaddressvo.setPostcode(postcode);
	 shippingaddressvo.setPublishtime(new Date());
     shippingaddressvo.setModifier(currentUserVO.getUsername());
     shippingaddressvo.setModifytime(new Date());

	 if (ShippingAddressMgr.getInstance().addShippingAddress(shippingaddressvo) == 0)
	 {
	 response.getWriter()
	 .print("<script>alert('添加成功!');window.location.href='addaddress.jsp'</script>");

	 }
	
	 }
	 
	if(action != null && action.trim().equals("delete"))
	{
	String pk_shipping_address = request.getParameter("pk_shipping_address");
	if(ShippingAddressMgr.getInstance().deleteShippingAddress(Integer.parseInt(pk_shipping_address)) == 0)
	{
	} response.getWriter()
	 .print("<script>alert('删除成功!');window.location.href='addaddress.jsp'</script>");
	
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>乐筹网-收件地址管理</title>
<meta http-equiv="expires" content="0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/png" href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css" href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<script type="text/javascript" async="" src="js/dc.js"></script>
<script type="text/javascript" async="" src="js/ag.js"></script>
<script type="text/javascript" src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>
<script>
$(".meun-01").live("click",function(){
	location.href = $(this).data("link");
})
</script>
<script async="" src="js/ncfpb.1.1.min.js"></script>
<script async="" src="js/zcpb.1.0.min.js"></script>
<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>

<script type="text/javascript" async="" src="js/dc.js"></script><script type="text/javascript" async="" src="js/ag.js"></script><script type="text/javascript" src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>  
<script>
$(".meun-01").live("click",function(){
	location.href = $(this).data("link");
})
</script>

</head>
<body>
<!--header static-->
<div class="header">
  <div class="wrap clearfix">
    <div class="m-left">
      <h2><a id="nav_logo" alt="乐筹网" class="img-logo " title="乐筹网" href="index.jsp"><img src="images/logo.png" alt=""/></a> </h2>
   <ul class="m-nav clearfix">
					<li><a  href="index.jsp"><span><i
								class="s-01"></i> </span>返回首页</a>
					</li>
					
					<li><a href="manageproject.jsp" target="_blank"><i class="s-30"></i>项目管理</a>
					</li>
					<li><a href="manageorder.jsp" target="_blank"><i class="s-03"></i>订单管理</a>
					</li>
					<li><a href="manageinfo.jsp" target="_blank"><i class="s-phone"></i>个人设置</a>
					</li>
				</ul>
    </div>
    <div class="m-right clearfix">
     <div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="exit.jsp?operate=exit#">退出</i> </span>
				</div>
				<div id="Js-header-loginBtn">
					<span class="pull-down clearfix"><i class="meun-02"></i><i
						class="meun-01" data-link="manageproject.jsp"><%=name%></i> </span>
				</div>
    </div>
  </div>
</div>
<!--header end-->
<link rel="stylesheet" type="text/css" href="css/7ffb95504c3dc3e41f80429d1d3004ea.css">

<link rel="stylesheet" type="text/css" href="css/5de86140d87bd2dbed2f7d202594aff3.css">

<div class="wrap pb116 clearfix">
  <h2 class="personal-set">
			<a href="manageproject.jsp" class="mm-blue ml-pm a-personal-set  ">返回后台主页</a>个人设置
		</h2>
		<!--left side static-->
		<div class="personal-set-left">
			<ul>
				<li ><a href="manageinfo.jsp">资料修改</a>
				</li>
				<li><a href="updatepassword.jsp">密码修改</a>
				</li>
				<li><a href="updateheadicon.jsp">头像修改</a>
				</li>
				<li class="selected"><a href="addaddress.jsp">收件地址管理</a>
				</li>

			</ul>
		</div>
        <!--left side end-->
        
        <!--right side static-->
         <div class="personal-set-right">
               <div class=" receive-address clearfix">
                <h3>收件地址管理</h3>
                <p>可添加管理多个收件地址</p>
                <a onClick="add_newaddr()" class="Js-addList btn-address" href="javascript:;">添加地址</a>
                
               </div>
               
                               <table cellpadding="0" cellspacing="0" class="Js-addList address-list">
                   <colgroup>
                        <col width="8%">
                        <col width="25%">
                        <col width="25%">
                        <col width="10%">
                        <col width="17%">
                        <col width="15%">
                    </colgroup>
                      <thead>
                            <tr>
                                <th>收件人</th>
                                <th>详细地址</th>
                                <th>邮编</th>
                                <th>电话/手机</th>
                                <th>生成时间</th>
                                <th>操作</th>
			                 </tr>
                      </thead>
                      <tbody>
                              <%
				for (Iterator<ShippingAddressVO> it = shippaddressvos.iterator(); it.hasNext();)
				{
				ShippingAddressVO shippaddressvo = it.next();
								%>
	          					<tr>
						<th><%=shippaddressvo.getReceiver()%></th>
						<td><%=shippaddressvo.getAddress() %></td>
						<td><%=shippaddressvo.getPostcode()%></td>
						<td><%=shippaddressvo.getTel()%></td>
						<td><%=shippaddressvo.getPublishtime()%></td>
						<td data-id="358699"><a class="editor Js-addr-modify" href="javascript:;">编辑</a><a class="editor Js-addr-del" href="addaddress.jsp?action=delete&pk_shipping_address=<%=shippaddressvo.getPk_shipping_address()%>">删除</a></td>
				    </tr>
                    <%} %>
                 </tbody>
            </table>            
                            <form id="add_addr" class="address-form" name="addaddress" action="addaddress.jsp" wx-validator="" wx-validator-error-class="error-text" wx-validator-ajax="reload" style="display:none;" autocomplete="off">
                   <div class="form-item clearfix">
                           <p class="return clearfix"><a onClick="addr_back()" class="btn-address">返回</a></p>
                           <div class="row clearfix">
                               <label>收件人：</label>
                               <input type="text" class="input-wid" name="receiver" wx-validator-rule="required">
                               <input type="hidden" name="action" value="addaddress">
                          </div>
                          <div class="row clearfix">
                               <label>手机：</label>
                               <input type="text" class="input-wid" name="tel" wx-validator-rule="required|mobile">
                          </div>
                         
                          <div class="row clearfix">
                               <label>详细地址：</label><input class="ads-ipt" type="text" name="address" wx-validator-rule="required">
                          </div>
                          <div class="row clearfix">
                               <label>邮编：</label>
                               <input type="text" class="input-wid" name="postcode" wx-validator-rule="post">
                          </div>
                          <p class="p-btn"><a type="submit" class=" a-btn-blue btn-effect-blue" onclick="avascript:document.addaddress.submit()">保存</a></p>
                 </div>
            </form>
        </div>
        <!--right side end-->
        
</div>

<!--start of footer-->
<div id="foottop1"></div>
<div class="footer">
  <div class="footer-nav"> <a href="#" rel="nofollow" target="_blank">关于我们 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">联系我们 <i>｜</i> </a>
        <a href="#" rel="nofollow" target="_blank">服务协议 <i>｜</i> </a> <a href="#" rel="nofollow" target="_blank">项目发起规范 <i>｜</i> </a> <a href="#" target="_blank">新手帮助 <i>｜</i> </a> <a href="#" target="_blank">帮助中心 <i>｜</i> </a> </div>
  <div class="footer-icon"> <span>copyright  ©2015  by lechou  all  right  reserved</span> </div>
</div>
<!--end of footer-->
<script type="text/javascript" src="js/fe3974b84fb6b50da0f329242078614c.js"></script>
</body>
</html>
<script type="text/javascript" src="js/fe3974b84fb6b50da0f329242078614c.js"></script>                   
<div id="gotop" class="go-top"></div>
<!--footer end-->
<!--footer end-->

<script type="text/javascript">
	function add_newaddr(){
		$(".Js-addList").hide();
		$("#add_addr").show();
		$("input[name='consignee']").val("");
		$("input[name='mobile']").val("");
		$("input[name='address']").val("");
		$("input[name='zip']").val("");
		$("select[name='province']").find("option:first").prop("selected",true);
		$("select[name='city']").find("option:first").prop("selected",true);
	}
	function addr_back(){
		$(".Js-addList").show();
		$("#add_addr").hide();
	}
	$(".Js-addr-del").click(function(){
		var id = $(this).parent().attr("data-id");
		wx.confirm("是否要删除此地址",function(){
			wx.sendData("/settings-del_consignee","id="+id,wx.RELOAD);
		});
	});
	$(".Js-addr-modify").click(function(){
		var id = $(this).parent().attr("data-id");
		wx.sendData("/settings-get_consignee","id="+id,function(data){
			add_newaddr();
			$(".address-form").append('<input name="id" value="'+id+'" style="display:none"/>');
			$("input[name='consignee']").val(data[0].consignee);
			$("input[name='mobile']").val(data[0].mobile);
			$("input[name='address']").val(data[0].address);
			$("input[name='zip']").val(data[0].zip);
      getRegion({provinceName:data[0].province,cityName:data[0].city})
		});
	});
</script>

