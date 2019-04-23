package com.lechou.project.latestnews;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjectLatestnewsMgr {
	private static ProjectLatestnewsMgr mgr = null;
	private static ProjectLatestnewsDAO dao = new ProjectLatestnewsDAO();

	private ProjectLatestnewsMgr() {
	}

	public static ProjectLatestnewsMgr getInstance() {
		if (mgr == null) {
			mgr = new ProjectLatestnewsMgr();
		}
		return mgr;
	}

	public ProjectLatestnewsVO getProjectProgressVOFromRs(ResultSet rs) {
		ProjectLatestnewsVO vo = new ProjectLatestnewsVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(ProjectLatestnewsVO vo, ResultSet rs) {
		try {
	
			vo.setPk_project_latestnews(rs.getInt("pk_project_latestnews"));
			vo.setPk_project(rs.getInt("pk_project"));
			vo.setContent(rs.getString("content"));
			vo.setImagesurl(rs.getString("imagesurl"));
			vo.setMoviesurl(rs.getString("moviesurl"));
			vo.setPublishtime(rs.getTimestamp("publishtime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));


		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getLatestnewsByProjectId(List<ProjectLatestnewsVO> vos,int pk_project){
		return dao.getVOsByProjectId(vos, pk_project);
	}
	public int getLatestnewSCountByProjectId(int pk_project){
	
		return dao.getVOCountByProjectId(pk_project);
		
	}
	
}
