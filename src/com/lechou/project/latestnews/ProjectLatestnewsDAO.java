package com.lechou.project.latestnews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class ProjectLatestnewsDAO {
	private static Connection conn = DatabaseOperator.getConn();
	/*
	 * ��ѯ����������е�VO����
	 */
	public List<ProjectLatestnewsVO> getVOs() {
		
	
		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectLatestnewsVO> vos = new ArrayList<ProjectLatestnewsVO>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_latestnews  order by publishtime desc ";
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			ProjectLatestnewsVO vo = null;
			while (rs.next()) {
				vo = new ProjectLatestnewsVO();
				ProjectLatestnewsMgr.getInstance().initFromResultSet(vo, rs);
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
	 * ��������ҳ��ѯ����
	 * 
	 * @return ���VO��������
	 */
	public int getVOs(List<ProjectLatestnewsVO> vos, int pageNo, int pageSize,
			String queryStr) {
		int totalRecords = -1;

		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_latestnews " + queryStr
					+ " order by publishtime desc limit " + (pageNo - 1)
					* pageSize + "," + pageSize;
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			ProjectLatestnewsVO vo = null;
			while (rs.next()) {
				vo = new ProjectLatestnewsVO();
				ProjectLatestnewsMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			String sql1 = "select count(*) from lechou_project_latestnews " + queryStr;
			rsCount = DatabaseOperator.executeQuery(stmtCount,sql1
					);
			System.out.println("��ʼִ�е���select���" + sql1);
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
	public ProjectLatestnewsVO getVOById(int id) {
		ProjectLatestnewsVO vo = new ProjectLatestnewsVO();
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_latestnews where pk_project_latestnews="
				+ id;
		System.out.println("��ʼִ�е���select���" + sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo = ProjectLatestnewsMgr.getInstance().getProjectProgressVOFromRs(rs);
		return vo;
	
	}
	public int getVOCountByProjectId(int id) {
	    int sum = 0;
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select count(*) from lechou_project_latestnews where pk_project="
				+ id;
		System.out.println("��ʼִ�е���select���" + sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
			sum = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sum;
	
	}
	
	public int getVOsByProjectId(List<ProjectLatestnewsVO> vos,int pk_project) {
		int totalRecords = -1;
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_latestnews where pk_project = " + pk_project	+ " order by publishtime desc";
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			ProjectLatestnewsVO vo = null;
			while (rs.next()) {
				vo = new ProjectLatestnewsVO();
				ProjectLatestnewsMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			String sql1 = "select count(*) from lechou_project_latestnews  where pk_project = " + pk_project;
			rsCount = DatabaseOperator.executeQuery(stmtCount,sql1
					);
			System.out.println("��ʼִ�е���select���" + sql1);
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
	 * ����id������ѯVO����
	 */
	public List<ProjectLatestnewsVO> getVOById(int[] ids) {
		return null;

	}
	

	/*
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(ProjectLatestnewsVO vo) {

		
		String sql = "insert into lechou_project_latestnews values(null,?,?,?,?,?,?,?)";
		System.out.println("��ʼִ�е���insert��䣺" + sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {			
			pstmt.setInt(1, vo.getPk_project());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImagesurl());
			pstmt.setString(4, vo.getMoviesurl());
			pstmt.setTimestamp(5, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(6, vo.getModifier());
			pstmt.setTimestamp(7, new Timestamp(vo.getModifytime().getTime()));
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
	public int addByVO(List<ProjectLatestnewsVO> vos) {
		return 0;

	}

	/*
	 * ����VO����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteByVO(ProjectLatestnewsVO vo) {
		return 0;

	}

	/*
	 * ����idɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteById(int id) {
	

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_project_latestnews where pk_project_latestnews ="
				+ id;
		System.out.println("��ʼִ�е���delete���");
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
	public int batchDelete(List<ProjectLatestnewsVO> vos) {
		return 0;

	}

	/*
	 * ����id����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int batchDelete(int[] idArray) {

		Statement stmt = DatabaseOperator.createStatement(conn);
		System.out.println("��ʼִ������delete���");
		for (int i = 0; i < idArray.length; i++) {
			String sql = "delete from lechou_project_latestnews where pk_project_latestnews ="
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
	public int update(ProjectLatestnewsVO vo) {
	
		
		PreparedStatement pstmt = null;
		String sql = "update lechou_project_latestnews set pk_project=?,content=?,imagesurl=?,"
				+ "moviesurl=?,publishtime=?,modifier=?,modifytime=?  where pk_project_latestnews = ?";
		System.out.println("��ʼִ�е���update��䣺" + sql);

		try {
			pstmt = DatabaseOperator.prepareStatement(conn, sql);
			pstmt.setInt(1, vo.getPk_project());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getImagesurl());
			pstmt.setString(4, vo.getMoviesurl());
			pstmt.setTimestamp(5, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(6, vo.getModifier());
			pstmt.setTimestamp(7, new Timestamp(vo.getModifytime().getTime()));
			pstmt.setInt(10, vo.getPk_project_latestnews());
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
	public int update(List<ProjectLatestnewsVO> vos) {
		return 0;
	}
}
