package com.lechou.project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.lechou.order.OrderMgr;
import com.lechou.project.approvelist.ProjectApprovelistMgr;
import com.lechou.project.approvelist.ProjectApprovelistVO;
import com.lechou.project.comment.ProjectCommentMgr;

public class ProjectMgr {
	private static ProjectMgr mgr = null;
	private static ProjectDAO dao = new ProjectDAO();

	private ProjectMgr() {
	}

	public static ProjectMgr getInstance() {
		if (mgr == null) {
			mgr = new ProjectMgr();
		}
		return mgr;
	}

	public ProjectVO getProjectVOFromRs(ResultSet rs) {
		ProjectVO vo = new ProjectVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(ProjectVO vo, ResultSet rs) {
		try {
			vo.setPk_project(rs.getInt("pk_project"));
			vo.setTitle(rs.getString("title"));
			vo.setSelectableprice1(rs.getInt("selectableprice1"));
			vo.setSelectableprice2(rs.getInt("selectableprice2"));
			vo.setSelectableprice3(rs.getInt("selectableprice3"));
			vo.setFinalprice(rs.getInt("finalprice"));
			vo.setInvestreward(rs.getString("investreward"));
			vo.setDescription(rs.getString("description"));
			vo.setDetaildescription(rs.getString("detaildescription"));
			vo.setTitleimageurl(rs.getString("titleimageurl"));
			vo.setDescriptionimages(rs.getString("descriptionimages"));
			vo.setMovieurl(rs.getString("movieurl"));
			vo.setInitiatorid(rs.getInt("initiatorid"));
			vo.setCatagoryid(rs.getInt("catagoryid"));
			vo.setProjectaddress(rs.getString("projectaddress"));
			vo.setStatus(rs.getString("status"));
			vo.setFundamount(rs.getInt("fundamount"));
			vo.setFunddate(rs.getInt("funddate"));
			vo.setFundstarttime(rs.getTimestamp("fundstarttime"));
			vo.setFundendtime(rs.getTimestamp("fundendtime"));
			vo.setApprovestarttime(rs.getTimestamp("approvestarttime"));
			vo.setApproveendtime(rs.getTimestamp("approveendtime"));
			vo.setFocuscount(rs.getInt("focuscount"));
			vo.setIssuccesse(rs.getInt("issuccesse"));
			vo.setSuccessetime(rs.getTimestamp("successetime"));
			vo.setSuccessedchoose(rs.getInt("successedchoose"));
			vo.setPublishtime(rs.getTimestamp("modifytime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getCompletefundamount(int pk_project) {
		System.out.print(OrderMgr.getInstance().getProjectCompleteFund(
				pk_project));
		return OrderMgr.getInstance().getProjectCompleteFund(pk_project);
	}

	public int getCompletedegree(int pk_project) {
	
		return (this.getCompletefundamount(pk_project) * 10000)
				/ this.getProjectVOById(pk_project).getFundamount();
	}

	public int getProjects(List<ProjectVO> Projects, int pageNo, int pageSize) {
		return dao.getVOs(Projects, pageNo, pageSize);
	}

	public List<ProjectVO> getProjects() {
		return dao.getVOs();
	}

	public int getOwnProjects(List<ProjectVO> Projects, int pk_user) {
		return dao.getVOsByUserId(Projects, pk_user);
	}
	
	

	public int getProjects(List<ProjectVO> Projects, int pageNo, int pageSize,
			ProjectSearchVO projectsearchvo) {

		String queryStr = " where 1=1 ";

		if (projectsearchvo.getCatagoryid() != -1) {
			queryStr += " and lechou_project.catagoryid ="
					+ projectsearchvo.getCatagoryid() + "";
		}

		if (projectsearchvo.getProjectaddress() != null
				&& !projectsearchvo.getProjectaddress().trim().equals("")) {
			queryStr += " and lechou_project.projectaddress like '%"
					+ projectsearchvo.getProjectaddress() + "%'";
		}
		if (projectsearchvo.getStatus() != null
				&& !projectsearchvo.getStatus().trim().equals("全部项目")) {
			queryStr += " and lechou_project.status = '"
					+ projectsearchvo.getStatus() + "'";
		}
		if (projectsearchvo.getTitle() != null
				&& !projectsearchvo.getTitle().trim().equals("")) {
			queryStr += " and lechou_project.title like '%"
					+ projectsearchvo.getTitle() + "%'";
		}
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}

	public int getProjects(List<ProjectVO> Projects, int pageNo, int pageSize,
			ProjectBrowseSearchVO searchvo) {

		String queryStr = " where 1=1 ";
		if(searchvo.getCatagoryid() == 0 &&searchvo.getStatus()==null&& searchvo.getOther() == null)
		{
			queryStr = " where 1=1 and status in ('即将开始','众筹中','即将结束' ,'已成功' )";
			return dao.getVOs(Projects, pageNo, pageSize, queryStr);
		}

		if (searchvo.getStatus() != null
				&& !searchvo.getStatus().trim().equals("")) {

			if (searchvo.getCatagoryid() != 0) {
				queryStr += " and lechou_project.catagoryid = "
						+ searchvo.getCatagoryid() + "";
			}
			queryStr += "  and lechou_project.status = '" + searchvo.getStatus()
					+ "'";
		}else{
			if (searchvo.getCatagoryid() != 0) {
				queryStr += " and lechou_project.catagoryid = "
						+ searchvo.getCatagoryid() + " and status in ('即将开始','众筹中','即将结束' ,'已成功' ) ";
			}
			
		}
		
		
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}

	public int getProjectsFilterByStatus(List<ProjectVO> Projects, int pageNo,
			int pageSize, String status) {
		String queryStr = " where 1=1 ";
		if (status != null && !status.trim().equals("全部项目")) {
			queryStr += " and lechou_project.status = '" + status + "'";
		}

		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}

	public int getStartedProjects(List<ProjectVO> Projects, int pageNo,
			int pageSize) {
		String queryStr = "where 1=1  and status in ('即将开始','众筹中','即将结束' ,'已成功' )  ";
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}
	public int getApproveProjects(List<ProjectVO> Projects, int pageNo,
			int pageSize) {
		String queryStr = "where 1=1  and status ='未审核'";
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}

	public int getApproveProjects(List<ProjectVO> Projects, int pageNo, int pageSize,
			ProjectBrowseSearchVO searchvo) {

		String queryStr = " where 1=1 and status ='未审核'";

			if (searchvo.getCatagoryid() != 0) {
				queryStr += " and lechou_project.catagoryid = "
						+ searchvo.getCatagoryid() + " ";
			}
	
		
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}
	
	public int getSuccessProjects(List<ProjectVO> Projects, int pageNo,
			int pageSize) {
		String queryStr = "where 1=1  and status ='已成功'";
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}

	public int getSuccessProjects(List<ProjectVO> Projects, int pageNo, int pageSize,
			ProjectBrowseSearchVO searchvo) {

		String queryStr = " where 1=1 and status ='已成功'";

			if (searchvo.getCatagoryid() != 0) {
				queryStr += " and lechou_project.catagoryid = "
						+ searchvo.getCatagoryid() ;
			}
	
		
		System.out.println("构建用户查询语句：" + queryStr);
		return dao.getVOs(Projects, pageNo, pageSize, queryStr);
	}
	public int updateProject(ProjectVO vo) {
		return dao.update(vo);
	}

	
	public int deleteProject(int pk_project) {
		return dao.deleteById(pk_project);
	}

	public int deleteProject(int[] pk_project) {
		return dao.batchDelete(pk_project);
	}

	public ProjectVO getProjectVOById(int id) {
		return dao.getVOById(id);
	}

	public int initialproject(ProjectVO projectvo) {

		projectvo.setStatus("未审核");
		projectvo.setApprovestarttime(new Date());
		projectvo.setPublishtime(new Date());
		projectvo.setModifytime(new Date());
		return dao.addByVO(projectvo);
	}

	public int temporarysave(ProjectVO projectvo) {
		projectvo.setStatus("草稿");
		projectvo.setPublishtime(new Date());
		projectvo.setModifytime(new Date());
		return dao.addByVO(projectvo);
	}

	public void getLatestProjectsBystatus(List<ProjectVO> Projects, int count,
			String status) {
		getProjectsFilterByStatus(Projects, 1, count, status);
	}

	public void gethotestProjectsBystatus(List<ProjectVO> Projects, int count) {
		List<Integer> pk_projects = ProjectApprovelistMgr.getInstance().getVOs(
				count);
		for (Iterator<Integer> it = pk_projects.iterator(); it.hasNext();) {
			Integer i = it.next();
			Projects.add(dao.getVOById(i));
		}
	}

	// 1,2,3分别代表备选定价，4代表不支持
	public int getApproveResult(ProjectVO project) {

		int supportcount = 0;
		int insupportcount = 0;
		int totalcount = 1;
		int rate = 0;
		supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
				project.getPk_project(), 1);
		supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
				project.getPk_project(), 2);
		supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
				project.getPk_project(), 3);
		insupportcount = ProjectApprovelistMgr.getInstance().getResultCount(
				project.getPk_project(), 4);
		if (supportcount + insupportcount != 0) {
			totalcount = supportcount + insupportcount;
			rate = (supportcount * 100) / totalcount;
			if (rate >= ProjectVO.APPROVEPASSRATE
					&& totalcount > ProjectVO.APPROVEPASSCOUNT) {
				return 1;
			}
		}
		return 0;

	}
	
	// 1,2,3分别代表备选定价，4代表不支持
		public int getSurpportCount(ProjectVO project) {

			int supportcount = 0;			
			supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 1);
			supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 2);
			supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 3);
		
			return supportcount;

		}
		
		public int getInsurpportCount(ProjectVO project) {
        	int insupportcount = 0;
		insupportcount = ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 4);
				return insupportcount;

		}
		

		public int getSurpportRate(ProjectVO project) {
			int supportcount = 0;
			int insupportcount = 0;
			int totalcount = 1;
			int rate = 0;
			supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 1);
			supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 2);
			supportcount += ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 3);
			insupportcount = ProjectApprovelistMgr.getInstance().getResultCount(
					project.getPk_project(), 4);
			if(totalcount<ProjectApprovelistVO.APPROVECOUNT)
			{				
				rate = 0;
			}else{
				totalcount = supportcount + insupportcount;
				rate = (supportcount * 100) / totalcount;
			
			}
			return rate;

		}

	public int getActualExtractRate(ProjectVO project) {

		int supportcount = 0;
		int insupportcount = 0;
		int totalcount = 1;
		int rate = 0;
		supportcount = ProjectApprovelistMgr.getInstance().getResultCount(
				project.getPk_project(), 1);
		insupportcount = ProjectApprovelistMgr.getInstance().getResultCount(
				project.getPk_project(), 0);
		if (supportcount + insupportcount != 0) {
			totalcount = supportcount + insupportcount;
			rate = (supportcount * 100) / totalcount;
			if (rate >= ProjectVO.APPROVEPASSRATE) {
				return 1;
			}
		}
		return 0;

	}
	
	 public int getFinallyPrice(int pk_project){
		ProjectVO vo = getProjectVOById(pk_project);
		int count1 =  ProjectApprovelistMgr.getInstance().getResultCount(pk_project, 1);
		int count2 =  ProjectApprovelistMgr.getInstance().getResultCount(pk_project, 2);
		int count3 =  ProjectApprovelistMgr.getInstance().getResultCount(pk_project, 3);
		int price = (count1*vo.getSelectableprice1()+ count2*vo.getSelectableprice2()+count2*vo.getSelectableprice2())/(count1+count2+count3);
		return price;
	 }

	public int updateVOStatus(ProjectVO project) {
		return dao.update(project);

	}

	public int WhenApprovePass(ProjectVO projectvo) {
		projectvo.setStatus("即将开始");
		projectvo.setFinalprice(getFinallyPrice(projectvo.getPk_project()));
		projectvo.setApproveendtime(new Date());
		projectvo.setModifier("system");
		projectvo.setModifytime(new Date());
		return this.updateVOStatus(projectvo);

	}

	public int WhenApproveNoPass(ProjectVO projectvo) {
		projectvo.setStatus("审核未通过");
		projectvo.setApproveendtime(new Date());
		projectvo.setModifier("system");
		projectvo.setModifytime(new Date());

		return this.updateVOStatus(projectvo);

	}
 
	public int WhenFundWillStart(ProjectVO projectvo) {
		projectvo.setStatus("众筹中");
		projectvo.setFundstarttime(new Date());
		projectvo.setModifier("system");
		projectvo.setModifytime(new Date());

		return this.updateVOStatus(projectvo);

	}

	public int WhenFundWillEnd(ProjectVO projectvo) {
		projectvo.setStatus("即将结束");
		projectvo.setModifier("system");
		projectvo.setModifytime(new Date());

		return this.updateVOStatus(projectvo);

	}

	public int WhenFundEndedWithSuccess(ProjectVO projectvo) {
		projectvo.setStatus("已成功");
		projectvo.setFundendtime(new Date());
		projectvo.setIssuccesse(1);
		projectvo.setSuccessetime(new Date());
		projectvo.setSuccessedchoose(0);
		projectvo.setModifier("system");
		projectvo.setModifytime(new Date());

		return this.updateVOStatus(projectvo);

	}

	public int WhenFundEndedWithFail(ProjectVO projectvo) {
		projectvo.setStatus("已失败");
		projectvo.setFundendtime(new Date());
		projectvo.setIssuccesse(0);
		projectvo.setModifier("system");
		projectvo.setModifytime(new Date());

		return this.updateVOStatus(projectvo);

	}

	public int getFocusCount(int pk_project) {
		return dao.getVOById(pk_project).getFocuscount();
	}

	public int getProjectHotDegree(int pk_project) {
		int total = ProjectMgr.getInstance().getFocusCount(pk_project)
				+ ProjectCommentMgr.getInstance().getCommentsCount(pk_project);
		return total;

	}

	// 通过评论数与关注数之和进行查询热度
	public List<ProjectVO> getProjectWillStart(int requestcount) {

		List<ProjectVO> projects = dao.getVOs();//
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {

			ProjectVO project = it.next();
			if (project.getStatus() != null) {
				if (project.getStatus().trim().equals("即将开始")) {
					map.put(project.getPk_project(), ProjectMgr.getInstance()
							.getProjectHotDegree(project.getPk_project()));
				}
			}

		}
		List<Map.Entry<Integer, Integer>> result = sortMethod(map);
		List<ProjectVO> hotprojects = new ArrayList<ProjectVO>();

		// 排序后
		for (int i = 0; i < (result.size() > requestcount ? requestcount
				: result.size()); i++) {
			hotprojects.add(ProjectMgr.getInstance().getProjectVOById(
					result.get(i).getKey()));
		}
		return hotprojects;

	}
	// 通过评论数与关注数之和进行查询热度
	public List<ProjectVO> getHotApproveProject(int requestcount) {

		List<ProjectVO> projects = dao.getVOs();//
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {

			ProjectVO project = it.next();
			if (project.getStatus() != null) {
				if (project.getStatus().trim().equals("未审核")) {
					map.put(project.getPk_project(), ProjectMgr.getInstance()
							.getProjectHotDegree(project.getPk_project()));
				}
			}

		}
		List<Map.Entry<Integer, Integer>> result = sortMethod(map);
		List<ProjectVO> hotprojects = new ArrayList<ProjectVO>();

		// 排序后
		for (int i = 0; i < (result.size() > requestcount ? requestcount
				: result.size()); i++) {
			hotprojects.add(ProjectMgr.getInstance().getProjectVOById(
					result.get(i).getKey()));
		}
		return hotprojects;

	}
	public List<ProjectVO> getHotApproveProjectByCatagoryid(int requestcount,int catagoryid) {

		List<ProjectVO> projects = new ArrayList<ProjectVO>();
		dao.getVOsByCatagoryId(projects, catagoryid);
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {

			ProjectVO project = it.next();
			if (project.getStatus() != null) {
				if (project.getStatus().trim().equals("未审核")) {
					map.put(project.getPk_project(), ProjectMgr.getInstance()
							.getProjectHotDegree(project.getPk_project()));
				}
			}

		}
		List<Map.Entry<Integer, Integer>> result = sortMethod(map);
		List<ProjectVO> hotprojects = new ArrayList<ProjectVO>();

		// 排序后
		for (int i = 0; i < (result.size() > requestcount ? requestcount
				: result.size()); i++) {
			hotprojects.add(ProjectMgr.getInstance().getProjectVOById(
					result.get(i).getKey()));
		}
		return hotprojects;

	}
	
	// 通过评论数与关注数之和进行查询热度
		public List<ProjectVO> getHostestProject(int requestcount) {

			List<ProjectVO> projects = 	dao.getVOs();
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

			for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
				ProjectVO project = it.next();
				if (project.getStatus() != null) {
					if (project.getStatus().trim().equals("众筹中")
							|| project.getStatus().trim().equals("即将结束")
							|| project.getStatus().trim().equals("已成功")) {
						map.put(project.getPk_project(),this.getCompletefundamount(project.getPk_project()));
					}
				}
			}
			List<Map.Entry<Integer, Integer>> result = sortMethod(map);
			List<ProjectVO> hotprojects = new ArrayList<ProjectVO>();
			// 排序后
			for (int i = 0; i < (result.size() > requestcount ? requestcount
					: result.size()); i++) {
				hotprojects.add(ProjectMgr.getInstance().getProjectVOById(
						result.get(i).getKey()));
			}
			Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
			for (Iterator<ProjectVO> it = hotprojects.iterator(); it.hasNext();) {
				ProjectVO project = it.next();
				if (project.getStatus() != null) {
					map1.put(project.getPk_project(),getProjectHotDegree(project.getPk_project()));
				
				}
			}
			List<Map.Entry<Integer, Integer>> result1 = sortMethod(map1);
			List<ProjectVO> hotprojects1 = new ArrayList<ProjectVO>();
			for (int i = 0; i < result1.size(); i++) {
				hotprojects1.add(ProjectMgr.getInstance().getProjectVOById(
						result1.get(i).getKey()));
			}
			return hotprojects1;

		}

	// 根据完成度，再根据热度排行
	public List<ProjectVO> getProjectHotRankByCatagoryId(int requestcount,
			int catagoryid) {

		List<ProjectVO> projects = new ArrayList<ProjectVO>();
		dao.getVOsByCatagoryId(projects, catagoryid);
		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();

		for (Iterator<ProjectVO> it = projects.iterator(); it.hasNext();) {
			ProjectVO project = it.next();
			if (project.getStatus() != null) {
				if (project.getStatus().trim().equals("众筹中")
						|| project.getStatus().trim().equals("即将结束")
						|| project.getStatus().trim().equals("已成功")) {
					/*map.put(project.getPk_project(),this.getCompletefundamount(project.getPk_project()));*/
					map.put(project.getPk_project(),getProjectHotDegree(project.getPk_project()));
				}
			}
		}
		List<Map.Entry<Integer, Integer>> result = sortMethod(map);
		List<ProjectVO> hotprojects = new ArrayList<ProjectVO>();
		// 排序后
		for (int i = 0; i < (result.size() > requestcount ? requestcount
				: result.size()); i++) {
			hotprojects.add(ProjectMgr.getInstance().getProjectVOById(
					result.get(i).getKey()));
		}
		Map<Integer, Integer> map1 = new TreeMap<Integer, Integer>();
		for (Iterator<ProjectVO> it = hotprojects.iterator(); it.hasNext();) {
			ProjectVO project = it.next();
			if (project.getStatus() != null) {
/*				map1.put(project.getPk_project(),getProjectHotDegree(project.getPk_project()));
*/			map1.put(project.getPk_project(),this.getCompletefundamount(project.getPk_project()));
			}
		}
		List<Map.Entry<Integer, Integer>> result1 = sortMethod(map1);
		List<ProjectVO> hotprojects1 = new ArrayList<ProjectVO>();
		for (int i = 0; i < result1.size(); i++) {
			hotprojects1.add(ProjectMgr.getInstance().getProjectVOById(
					result1.get(i).getKey()));
		}
		return hotprojects1;

	}

	public List<Map.Entry<Integer, Integer>> sortMethod(
			Map<Integer, Integer> map) {
		List<Map.Entry<Integer, Integer>> infoIds = new ArrayList<Map.Entry<Integer, Integer>>(
				map.entrySet());
		// 排序
		Collections.sort(infoIds,
				new Comparator<Map.Entry<Integer, Integer>>() {
					public int compare(Map.Entry<Integer, Integer> o1,
							Map.Entry<Integer, Integer> o2) {
						return (o2.getValue() - o1.getValue());
					}
				});
		return infoIds;
	}

}
