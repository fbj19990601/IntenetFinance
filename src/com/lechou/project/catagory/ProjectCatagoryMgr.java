package com.lechou.project.catagory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectCatagoryMgr {
	private static ProjectCatagoryMgr mgr = null;
	private static ProjectCatagoryDAO dao = new ProjectCatagoryDAO();

	private ProjectCatagoryMgr() {
	}

	public static ProjectCatagoryMgr getInstance() {
		if (mgr == null) {
			mgr = new ProjectCatagoryMgr();
		}
		return mgr;
	}
	
	public ProjectCatagoryVO getProjectCatagoryVOFromRs(ResultSet rs) {
		ProjectCatagoryVO vo = new ProjectCatagoryVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(ProjectCatagoryVO vo, ResultSet rs) {
		try {
			vo.setCatagoryid(rs.getInt("catagoryid"));
			vo.setCatagoryname(rs.getString("catagoryname"));
			vo.setDescr(rs.getString("descr"));
			vo.setPublishtime(rs.getTimestamp("publishtime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ProjectCatagoryVO getProjectCatagoryVOById(int id) {
		return dao.getVOById(id);
	}
	
	public List<ProjectCatagoryVO> getProjectCatagorys() {
		return dao.getVOs();
	}
	
}
