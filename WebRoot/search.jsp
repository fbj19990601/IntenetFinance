<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.lechou.user.*,com.lechou.project.*,java.util.*,com.lechou.project.approvelist.*,com.lechou.project.comment.*,
com.lechou.project.catagory.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	String catagoryidstr = request.getParameter("catagoryid");
	String status = request.getParameter("status");
	String other = request.getParameter("other");
	int catagoryid = 0;

	ProjectBrowseSearchVO searchvo = (ProjectBrowseSearchVO) session
			.getAttribute("searchvo");
	if (searchvo == null) {
		ProjectBrowseSearchVO searchvo1 = new ProjectBrowseSearchVO();

		if (catagoryidstr != null && !catagoryidstr.trim().equals("")) {
			catagoryid = Integer.parseInt(catagoryidstr);
			searchvo1.setCatagoryid(catagoryid);
		}
		if (status != null && !status.trim().equals("")) {
			searchvo1.setStatus(status);
		}
		if (other != null) {
			searchvo1.setOther(other);
		}

		session.setAttribute("searchvo", searchvo1);
	} else {
		if (action != null && action.trim().equals("delete")) {
			if (catagoryidstr != null
					&& !catagoryidstr.trim().equals("")) {
				catagoryid = Integer.parseInt(catagoryidstr);
				searchvo.setCatagoryid(0);
			}
			if (status != null && !status.trim().equals("")) {
				searchvo.setStatus("");
			}
			if (other != null) {
				searchvo.setOther("");
			}

			session.setAttribute("searchvo", searchvo);
		} else {
			if (catagoryidstr != null
					&& !catagoryidstr.trim().equals("")) {
				catagoryid = Integer.parseInt(catagoryidstr);
				searchvo.setCatagoryid(catagoryid);
			}
			if (status != null && !status.trim().equals("")) {
				searchvo.setStatus(status);
			}
			if (other != null) {
				searchvo.setOther(other);
			}

			session.setAttribute("searchvo", searchvo);
		}
	}

   	
	response.getWriter()
			.print("<script>window.location.href='browseproject.jsp'</script>");
%>