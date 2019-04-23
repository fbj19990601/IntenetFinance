package com.lechou.project.comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.lechou.user.UserMgr;

public class ProjectCommentMgr {
	private static ProjectCommentMgr mgr = null;
	private static ProjectCommentDAO dao = new ProjectCommentDAO();

	private ProjectCommentMgr() {
	}

	public static ProjectCommentMgr getInstance() {
		if (mgr == null) {
			mgr = new ProjectCommentMgr();
		}
		return mgr;
	}
	
	public ProjectCommentVO getProjectCommentVOFromRs(ResultSet rs) {
		ProjectCommentVO vo = new ProjectCommentVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(ProjectCommentVO vo, ResultSet rs) {
		try {
			vo.setPk_project_comment(rs.getInt("pk_project_comment"));
			vo.setPk_project(rs.getInt("pk_project"));
			vo.setPk_user(rs.getInt("pk_user"));
			vo.setCommentcontent(rs.getString("commentcontent"));
			vo.setPublishtime(rs.getTimestamp("publishtime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public int getCommentsCount(int pk_project){
		return dao.getVOCountByProjectid(pk_project);	
	}
	public int replyComments(int pk_project,int pk_user,String content){
		ProjectCommentVO vo = new ProjectCommentVO();
        vo.setPk_project(pk_project);
        vo.setPk_user(pk_user);
        vo.setCommentcontent(content);
        vo.setPublishtime(new Date());
        vo.setModifier(UserMgr.getInstance().getUserVOById(pk_user).getUsername());
        vo.setModifytime(new Date());
		
		return dao.addByVO(vo);	
	}
	
	public int getProjectComment( List <ProjectCommentVO> vos,int pageNo,int pageSize,int pk_project){
		String queryStr = " where 1=1 ";

		if (pk_project != 0) {
			queryStr += "  and lechou_project_comment.pk_project = " + pk_project;
		}
		return dao.getVOs(vos, pageNo, pageSize, queryStr);
		
	}
	


}
