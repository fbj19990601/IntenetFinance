package com.lechou.project.approvelist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.lechou.user.UserMgr;

public class ProjectApprovelistMgr {
	private static ProjectApprovelistMgr mgr = null;
	private static ProjectApprovelistDAO dao = new ProjectApprovelistDAO();

	private ProjectApprovelistMgr() {
	}

	public static ProjectApprovelistMgr getInstance() {
		if (mgr == null) {
			mgr = new ProjectApprovelistMgr();
		}
		return mgr;
	}
	
	public ProjectApprovelistVO getProjectApprovelistVOFromRs(ResultSet rs) {
		ProjectApprovelistVO vo = new ProjectApprovelistVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(ProjectApprovelistVO vo, ResultSet rs) {
		try {
			vo.setPk_project_approvelist(rs.getInt("pk_project_approvelist"));
			vo.setPk_project(rs.getInt("pk_project"));
			vo.setPk_user(rs.getInt("pk_user"));
			vo.setApproveresult(rs.getInt("approveresult"));
			vo.setPublishtime(rs.getTimestamp("publishtime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));	
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getResultCount(int pk_project,int result){
		return dao.getVOsByApproveResult(pk_project,result);	
	}
	
	public List<Integer> getVOs(int no) {
		return dao.getVOs(no);
	}
	
	public int addApprove(int pk_user ,int pk_project,int result){
		ProjectApprovelistVO vo = new ProjectApprovelistVO();
		vo.setPk_project(pk_project);
		vo.setPk_user(pk_user);
		vo.setApproveresult(result);
		vo.setPublishtime(new Date());
		vo.setModifier(UserMgr.getInstance().getUserVOById(pk_user).getUsername());
		vo.setModifytime(new Date());
		return dao.addByVO(vo);
		
	}
	
	public int isUserApproveProject(int pk_user,int pk_project){
		List<ProjectApprovelistVO> vos = dao.getVOByProjectId(pk_project);
		for(Iterator<ProjectApprovelistVO> it = vos.iterator();it.hasNext();){
			ProjectApprovelistVO vo = it.next();
			if(vo.getPk_user() == pk_user){
				return 1;
			}
		
		}
		return 0;
		
	}
	
}
