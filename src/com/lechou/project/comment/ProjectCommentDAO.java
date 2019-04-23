package com.lechou.project.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class ProjectCommentDAO {
	private static Connection conn = DatabaseOperator.getConn();
	/*
	 * ��ѯ����������е�VO����
	 */
	public List<ProjectCommentVO> getVOs() {
	
		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectCommentVO> vos = new ArrayList<ProjectCommentVO>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_comment  order by publishtime desc ";
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectCommentVO vo = null;
			while (rs.next()) {
				vo = new ProjectCommentVO();
				ProjectCommentMgr.getInstance().initFromResultSet(vo, rs);
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
	public int getVOCountByProjectid(int pk_project) {
	
		Statement stmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_project_comment  where pk_project = "+pk_project;
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
	 * ��ҳ��ѯ����
	 * 
	 * @return ���VO��������
	 */
	public int getVOs(List<ProjectCommentVO> vos, int pageNo, int pageSize) {
		int totalRecords = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_comment  order by publishtime desc limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectCommentVO vo = null;
			while (rs.next()) {
				vo = new ProjectCommentVO();
				ProjectCommentMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project_comment");
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rsCount.next();
			totalRecords = rsCount.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseOperator.close(rsCount);
			DatabaseOperator.close(stmtCount);
			DatabaseOperator.close(stmt);
			DatabaseOperator.close(rs);

		}

		return totalRecords;
	}

	/*
	 * ��������ҳ��ѯ����
	 * 
	 * @return ���VO��������
	 */
	public int getVOs(List<ProjectCommentVO> vos, int pageNo, int pageSize,
			String queryStr) {
		int totalRecords = -1;
	
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_comment " + queryStr
					+ " order by publishtime desc limit " + (pageNo - 1)
					* pageSize + "," + pageSize;
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectCommentVO vo = null;
			while (rs.next()) {
				vo = new ProjectCommentVO();
				ProjectCommentMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project_comment" + queryStr);
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rsCount.next();
			totalRecords = rsCount.getInt(1);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseOperator.close(rsCount);
			DatabaseOperator.close(stmtCount);
			DatabaseOperator.close(stmt);
			DatabaseOperator.close(rs);
		
		}

		return totalRecords;

	}

	/*
	 * ����id��ѯVO����
	 */
	public ProjectCommentVO getVOById(int id) {
		ProjectCommentVO vo = new ProjectCommentVO();
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_comment where pk_project_comment=" + id;
		System.out
				.println("��ʼִ�е���select���"
						+ sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo = ProjectCommentMgr.getInstance().getProjectCommentVOFromRs(rs);
		return vo;

	}

	/*
	 * ����id������ѯVO����
	 */
	public List<ProjectCommentVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(ProjectCommentVO vo) {

		String sql = "insert into lechou_project_comment values(null,?,?,?,?,?,?)";
		System.out
				.println("��ʼִ�е���insert��䣺"
						+ sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {
			pstmt.setInt(1, vo.getPk_project());
			pstmt.setInt(2, vo.getPk_user());
			pstmt.setString(3, vo.getCommentcontent());
			if(vo.getPublishtime() != null)
			{
				pstmt.setTimestamp(4, new Timestamp(vo.getPublishtime().getTime()));
			}
			else{
				pstmt.setTimestamp(4,null);
			}
		
			pstmt.setString(5, vo.getModifier());
			if(vo.getModifytime() != null)
			{		
				pstmt.setTimestamp(6, new Timestamp(vo.getModifytime().getTime()));
			}
			else{
				pstmt.setTimestamp(6,null);
			}
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
	public int addByVO(List<ProjectCommentVO> vos) {
		return 0;

	}

	/*
	 * ����VO����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteByVO(ProjectCommentVO vo) {
		return 0;

	}

	/*
	 * ����idɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteById(int id) {

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_project_comment where pk_project_comment =" + id;
		System.out
				.println("��ʼִ�е���delete���");
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			return -1;
		} finally {
			DatabaseOperator.close(stmt);
		
		}
		return 0;

	}

	/*
	 * ����VO��������ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int batchDelete(List<ProjectCommentVO> vos) {
		return 0;

	}

	/*
	 * ����id����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int batchDelete(int[] idArray) {
		Statement stmt = DatabaseOperator.createStatement(conn);
		System.out
				.println("��ʼִ������delete���");
		for (int i = 0; i < idArray.length; i++) {
			String sql = "delete from lechou_project_comment where pk_project_comment ="
					+ idArray[i];
			System.out.println(sql);
			try {
				stmt.addBatch(sql);

			} catch (SQLException e) {
				return -1;
			}
		}
		try {
			stmt.executeBatch();
		} catch (SQLException e) {
			return -1;
		} finally {
			DatabaseOperator.close(stmt);
	
		}
		return 0;
	}

	/*
	 * ����VO���� java.util.date
	 * ����ʱ��,java.sql.date����,java.sql.stamptime����ʱ��,���������ݿ���д��ʱҪʹ��timestampt:
	 * �洢��preparedStatement.setTimestamp(1, new java.sql.Timestamp(new
	 * java.util.Date().getTime())); ��ȡ��java.util.Date d =
	 * resultSet.getTimestamp(1)����java.util.Date d = new
	 * java.util.Date(resultSet.getTimestamp(1).getTime())�� java.util.Date d =
	 * new java.util.Date(resultSet.getTimestamp(1).getTime()); new
	 * SimpleTimeFormat("yyyyy-MM-dd HH:mm:ss").format(d);
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int update(ProjectCommentVO vo) {

		PreparedStatement pstmt = null;
		String sql = "update lechou_project_comment set pk_project=?,pk_user=?,commentcontent=?,publishtime=?,"
				+ "modifier=?,modifytime=? where pk_project_comment = ?";
		System.out
				.println("��ʼִ�е���update��䣺"
						+ sql);

		try {
			pstmt = DatabaseOperator.prepareStatement(conn, sql);
			pstmt.setInt(1, vo.getPk_project());
			pstmt.setInt(2, vo.getPk_user());
			pstmt.setString(3, vo.getCommentcontent());
			if(vo.getPublishtime() != null)
			{
				pstmt.setTimestamp(4, new Timestamp(vo.getPublishtime().getTime()));
			}
			else{
				pstmt.setTimestamp(4,null);
			}
		
			pstmt.setString(5, vo.getModifier());
			if(vo.getModifytime() != null)
			{		
				pstmt.setTimestamp(6, new Timestamp(vo.getModifytime().getTime()));
			}
			else{
				pstmt.setTimestamp(6,null);
			}
			pstmt.setInt(7, vo.getPk_project_comment());
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
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int update(List<ProjectCommentVO> vos) {
		return 0;
	}

}
