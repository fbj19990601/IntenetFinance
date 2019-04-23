<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.lechou.user.*,com.lechou.project.*,java.util.*,com.lechou.project.approvelist.*,com.lechou.project.comment.*,
com.lechou.project.catagory.*"%>
<%
	request.setCharacterEncoding("UTF-8");
	String action = request.getParameter("action");
	String catagoryidstr = request.getParameter("catagoryid");
	String other = request.getParameter("other");
	int catagoryid = 0;

	ProjectBrowseSearchVO searchvo = (ProjectBrowseSearchVO) session
			.getAttribute("approvesearchvo");
	if (searchvo == null) {
		ProjectBrowseSearchVO searchvo1 = new ProjectBrowseSearchVO();

		if (catagoryidstr != null && !catagoryidstr.trim().equals("")) {
			catagoryid = Integer.parseInt(catagoryidstr);
			searchvo1.setCatagoryid(catagoryid);
		}
		if (other != null) {
			searchvo1.setOther(other);
		}

		session.setAttribute("approvesearchvo", searchvo1);
	} else {
		if (action != null && action.trim().equals("delete")) {
			if (catagoryidstr != null
					&& !catagoryidstr.trim().equals("")) {
				catagoryid = Integer.parseInt(catagoryidstr);
				searchvo.setCatagoryid(0);
			}
			
			if (other != null) {
				searchvo.setOther("");
			}

			session.setAttribute("approvesearchvo", searchvo);
		} else {
			if (catagoryidstr != null
					&& !catagoryidstr.trim().equals("")) {
				catagoryid = Integer.parseInt(catagoryidstr);
				searchvo.setCatagoryid(catagoryid);
			}
			
			if (other != null) {
				searchvo.setOther(other);
			}

			session.setAttribute("approvesearchvo", searchvo);
		}
	}

   	
	response.getWriter()
			.print("<script>window.location.href='approveproject1.jsp'</script>");
%>