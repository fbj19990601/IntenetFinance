<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import='com.lechou.project.*,com.lechou.project.catagory.*,com.lechou.util.*,java.util.*'%>
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
					"<script>alert('亲，您还没有登录额，无法发起项目!');window.location.href='login.jsp'</script>");
return;
    }	
    String result = request.getParameter("result");
    if(result != null && !result.trim().equals("")){
     response.getWriter().print(
					"<script>alert('发起成功');</script>");
    }
    String requesturl =request.getParameter("requesturl");
    if(requesturl == null){
    requesturl = "initialproject.jsp";
    }
	List<ProjectCatagoryVO> projectcatagoryvos = ProjectCatagoryMgr
	.getInstance().getProjectCatagorys();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>乐筹网-发起项目</title>
<meta name="keywords" content="">
<meta http-equiv="expires" content="0">
<meta name="description" content="">
<meta name="renderer" content="webkit">
<link rel="shortcut icon" type="image/png"
	href="http://zcs6.ncfstatic.com/v3/static/images/icon/title.png?v=">
<link rel="stylesheet" type="text/css"
	href="css/14c4c0d24fba6ed25d8b6758064ef7b8.css">
<script type="text/javascript" async="" src="js/dc.js"></script>
<script type="text/javascript" async="" src="js/ag.js"></script>
<script type="text/javascript"
	src="js/2cc0475e1160ea574d0a9b5ebea60136.js"></script>
<script type="text/javascript" src="js/ProvinceAndCity.js"></script>
<script>
	$(".meun-01").live("click", function() {
		location.href = $(this).data("link");
	})
</script>
<script async="" src="js/ncfpb.1.1.min.js"></script>
<script async="" src="js/zcpb.1.0.min.js"></script>
<script type="text/javascript" id="b5mmain" src="js/b5m.main.js"></script>
<script src="js/AC_ActiveX.js" type="text/javascript"></script>
<script src="js/AC_RunActiveContent.js" type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="ckeditor/ckeditor.js"></script>
<script src="ckeditor/adapters/jquery.js"></script>
	
<script>

		CKEDITOR.disableAutoInline = true;


		$( document ).ready( function() {
			$( '#editor1' ).ckeditor(); // Use CKEDITOR.replace() if element is <textarea>.
			$( '#editable' ).ckeditor(); // Use CKEDITOR.inline().
		} );

		function setValue() {
			$( '#editor1' ).val( $( 'input#val' ).val() );
		}
		
	</script>
	

</head>
<body onload="loadProvince('selProvince');">
	<!--header static-->
	<div class="header">
		<div class="wrap clearfix">
			<div class="m-left">
				<h2>
					<a id="nav_logo" alt="众筹网" class="img-logo " title="众筹网"href#"><img
						src="images/logo.png" alt="" /> </a>
				</h2>
				<ul class="m-nav clearfix">
					<li><a href="index.jsp"><span><i class="s-01"></i>
						</span>首页</a>
					</li>
					<li><a href="browseproject.jsp" target="_blank"><i
							class="s-02"></i>浏览项目</a>
					</li>
					<li><a href="approveproject1.jsp" target="_blank"><i
							class="s-30"></i>新项目审核</a></li>
					<li><a href="initialproject.jsp" target="_blank"
						><i class="s-03"></i>发起项目</a>
					</li>
					<li><a href="successeproject.jsp" target="_blank"><i
							class="s-phone"></i>成功项目</a></li>
				</ul>
			</div>
			<div class="m-right clearfix">
			<%
					if (currentUserVO == null) {
				%>
				<span class="z-Login"> <a id="user_register"
					class="z-register Js-showRegister" href="register.jsp">注册</a> <a
					id="user_login" class="Js-showLogin" href="login.jsp">登录</a> </span>
				<%
					} else {
				%>

				<!--login in-->
				<span class="z-Login"> <a id="user_login"
					class="Js-showLogin" href="manageproject.jsp"><%=name%></a> <a
					id="user_register" class="z-register Js-showRegister"
					href="exit.jsp?operate=exit">退出</a> </span>
				<%
					}
				%>
				<form action="#" name="search" method="get" wx-validator=""
					autocomplete="off">

					<span> <input name="keywords2"
						wx-validator-placeholder="输入关键词" wx-validator-rule="required"
						type="text" wx-validator-notip="" placeholder="输入关键词"> <a
						href="javascript:document.search.submit()">搜索</a> </span>

				</form>
			</div>
		</div>
	</div>
	<!--header end-->
	<link rel="stylesheet" type="text/css"
		href="css/5de86140d87bd2dbed2f7d202594aff3.css">

	<!--header end-->
	<!--header static-->
	<script type="text/javascript">
		var ROOT = '/keupload.php';
	</script>
	<link rel="stylesheet" type="text/css"
		href="css/82f8b8a65f382c57ae8ef8aeee541802.css">
	<!--header-end-->

	<div class="main clearfix">
		<div class="process-wrap clearfix">
			<form action="initialprojectupload" method="post" name="projectForm"
				enctype="MULTIPART/FORM-DATA">
				<input type="hidden" name="pk_user"
					value="<%=currentUserVO.getPk_user()%>" /> <input type="hidden"
					name="requesturl" value="<%=requesturl%>" />
				<div class="tit-wrap">
					<h3>项目信息</h3>
				</div>
				<!--project start-->
				<div class="project-box">
					<div class="project-con fl w835">
						<div class="project-edit">
							<div class="form-item clearfix">

								<label>项目名称：</label> <input type="text" name="title"
									class="w655" value="" maxlength="40" placeholder="项目名称不能超过40个字">
							</div>

							<div class="form-item clearfix">
								<label>需筹资总金额：</label> <input type="text" name="fundamount"
									class="w408" value="" placeholder="不少于500元"> <em>元</em>
							</div>
							<div class="form-item clearfix">
								<label>募集天数：</label> <input type="text" name="funddate" value=""
									placeholder="10~90天"> <em>天</em>
							</div>
							<div class="form-item clearfix">
								<label>项目回报：</label> <input type="text" name="investreward"
									class="w655" value="" maxlength="40" placeholder="项目回报不能超过40个字">
							</div>
							<div class="form-item clearfix" id="single">
								<label>备选投资金额一：</label> <input type="text"
									name="selectableprice1" class="w408" value=""
									placeholder="用于投资人对项目进行审核"> <em>元</em>
							</div>
							<div class="form-item clearfix" id="single">
								<label>备选投资金额二：</label> <input type="text"
									name="selectableprice2" class="w408" value=""
									placeholder="用于投资人对项目进行审核"> <em>元</em>
							</div>
							<div class="form-item clearfix">
								<label>备选投资金额三：</label> <input type="text"
									name="selectableprice3" class="w408" value=""
									placeholder="用于投资人对项目进行审核"> <em>元</em>
							</div>
							<div class="form-item clearfix">
								<label>所属类别：</label>
								<div class="option-box">
									<select name="catagoryid" data-class="select-1"
										data-placeholder="选择省份" data-default="">
										<option value="all">所属类别</option>
										<%
											for (Iterator<ProjectCatagoryVO> it = projectcatagoryvos.iterator(); it
													.hasNext();) {
												ProjectCatagoryVO projectcatagoryvo = it.next();
										%>
										<option value="<%=projectcatagoryvo.getCatagoryid()%>"><%=projectcatagoryvo.getCatagoryname()%></option>
										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="form-item clearfix">
								<label>项目地点：</label>
								<div class="option-box">
							
								 <select name="province" id="selProvince" onchange="loadCity(this,'selCity');"></select> 
                                 <select name="city" id="selCity"></select> 
                          
							<!-- 		<select name="projectaddress" data-class="select-1"
										data-placeholder="选择省份" data-default="">
										<option value="all">请选择省份</option>
										<option value="安徽">安徽</option>
										<option value="澳门">澳门</option>
										<option value="北京">北京</option>

									</select> -->

								</div>
							</div>
							<div class="form-item clearfix">
								<label>项目封面：</label> <input type="file" name="titleimageurl">

								<div class="up-btn fs14">

									<span class="gray">支持jpg、jpeg、png，大小不超过5M。建议尺寸：640 x
										480px</span>
								</div>
							</div>
							<div class="form-item clearfix">
								<label>项目视频：</label> <input type="file" name="movieurl">
								<div class="up-btn fs14">

									<span class="gray">支持flv，大小不超过100M</span>
								</div>
							</div>

							<div class="form-item clearfix">
								<label>项目简介：</label>
								<textarea name="description" placeholder="不超过75个字"></textarea>

							</div>
						
							<div class="form-item clearfix">
							<label>项目详细描述：</label>
								<textarea name="detaildescription" placeholder="不超过1000个字 " ></textarea>
                    <!--    <textarea cols="80" id="editor1" name="detaildescription" rows="10" >
		
	            	   </textarea> -->
 
							</div>
							<div class="form-item clearfix">
								<label>项目详情图片：</label> <input type="file"
									name="descriptionimage1"> <input type="file"
									name="descriptionimage2"> <input type="file"
									name="descriptionimage3"> <input type="file"
									name="descriptionimage4">
								<div class="up-btn fs14">

									<span class="gray">支持jpg、jpeg、png，大小不超过5M。建议尺寸：640 x
										480px,最多上传4张</span>
								</div>
							</div>

							<div class="form-item clearfix fs14">
								<label></label> <input id="Js-agr" name="agr" type="checkbox"
									class="inp-w310"> <span id="wx-validator-agr-nocheck">请阅读协议并勾选同意</span><br>
								&nbsp;&nbsp;<span class="gray">
									项目成功后,筹资总金额的1.5%将被扣除，作为平台的手续费，详情查询 </span><a href="# class="
									green"
									target="_blank">《乐筹网项目管理章程》</a>

							</div>
							<input type="hidden" name="action" value="initailnow" />
							<center>
								<a class="btn-effect-blue btn-sub w170" type="submit"
									href="javascript:document.projectForm.submit();">立即发起</a>&nbsp;&nbsp;&nbsp;&nbsp;
							</center>
						</div>
					</div>
				</div>
			</form>
			<!--project end-->
		</div>

	</div>



	<div id="foottop"></div>
	<!--start of footer-->
	<div class="footer">
		<div class="footer-nav">
			<a href="http://www.zhongchou.cn/help-index/id-1" rel="nofollow"
				target="_blank">关于我们 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/help-contact" rel="nofollow"
				target="_blank">联系我们 <i>｜</i> </a>
			<!-- <a href="/help-team" rel="nofollow">团队介绍 <i>｜</i> </a> -->
			<a href="http://www.zhongchou.cn/help-term" rel="nofollow"
				target="_blank">服务协议 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/help-specification" rel="nofollow"
				target="_blank">项目发起规范 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/special-xinshou" target="_blank">新手帮助
				<i>｜</i> </a> <a href="http://www.zhongchou.cn/help-center"
				target="_blank">帮助中心 <i>｜</i> </a> <a
				href="http://www.zhongchou.cn/help-tourongzi" target="_blank">投融资服务<i>｜</i>
			</a> <a href="http://news.zhongchou.cn/" target="_blank">众筹资讯</a>
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
