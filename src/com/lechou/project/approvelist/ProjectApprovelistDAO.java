package com.lechou.project.approvelist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class ProjectApprovelistDAO {
	private static Connection conn = DatabaseOperator.getConn();
	/*
	 * ȡ�øñ��м�¼������ǰ����
	 */
	public List<Integer> getVOs(int no) {
		
		
		Statement stmt = null;
		ResultSet rs = null;
	    List<Integer>pk_projects =new ArrayList<Integer>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select  pk_project,count(pk_project)  from  lechou_project_approvelist  group by pk_project order by  count(pk_project) desc limit 0, "+ no;
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pk_projects.add(rs.getInt("pk_project"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {		
			DatabaseOperator.close(stmt);
			DatabaseOperator.close(rs);
		
		}

		return pk_projects;

	}
	

	/*
	 * ��ѯ����������е�VO����
	 */
	public List<ProjectApprovelistVO> getVOs() {
		
		
		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectApprovelistVO> vos = new ArrayList<ProjectApprovelistVO>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_approvelist  order by publishtime desc ";
			System.out
					.println("com.lechou.project.ProjectApprovelistDAO.getVOs()��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectApprovelistVO vo = null;
			while (rs.next()) {
				vo = new ProjectApprovelistVO();
				ProjectApprovelistMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseOperator.close(stmt);
			DatabaseOperator.close(rs);
	
		}

		return vos;
	}

	
	/*
	 * ��ѯ����������е�VO����
	 */
	public int getVOsByApproveResult(int pk_project,int result) {
		
		
		Statement stmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_project_approvelist  where pk_project ="+pk_project+" and approveresult ="+result;
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			
			rs.next();
			totalcount = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseOperator.close(stmt);
			DatabaseOperator.close(rs);
		
		}

		return totalcount;
	}
	
	

	/*
	 * ����id��ѯVO����
	 */
	public ProjectApprovelistVO getVOById(int id) {
		ProjectApprovelistVO vo = new ProjectApprovelistVO();
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_approvelist where pk_project_approvelist=" + id;
		System.out
				.println("��ʼִ�е���select���"
						+ sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo = ProjectApprovelistMgr.getInstance().getProjectApprovelistVOFromRs(rs);
		return vo;

	}


	/*
	 * ����id��ѯVO����
	 */
	public List<ProjectApprovelistVO> getVOByProjectId(int id) {
		List<ProjectApprovelistVO> vos = new ArrayList<ProjectApprovelistVO>();
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_approvelist where pk_project=" + id;
		System.out
				.println("��ʼִ�е���select���"
						+ sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		ProjectApprovelistVO vo = new ProjectApprovelistVO();
		try {
		while(rs.next()){
			vo = ProjectApprovelistMgr.getInstance().getProjectApprovelistVOFromRs(rs);
			vos.add(vo);
		} 
		}catch (SQLException e) {
			e.printStackTrace();
		}
	
		return vos;

	}
	/*
	 * ����id������ѯVO����
	 */
	public List<ProjectApprovelistVO> getVOById(int[] ids) {
		return null;

	}
	
	/*
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(ProjectApprovelistVO vo) {


		String sql = "insert into lechou_project_approvelist values(null,?,?,?,?,?,?)";
		System.out
				.println("��ʼִ�е���insert��䣺"
						+ sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {
			pstmt.setInt(1, vo.getPk_project());
			pstmt.setInt(2, vo.getPk_user());
			pstmt.setInt(3, vo.getApproveresult());
			pstmt.setTimestamp(4, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(5, vo.getModifier());
			pstmt.setTimestamp(6, new Timestamp(vo.getModifytime().getTime()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			DatabaseOperator.close(pstmt);
		
		}
		return 0;

	}

	/*
	 * ����VO������������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(List<ProjectApprovelistVO> vos) {
		return 0;

	}
}
