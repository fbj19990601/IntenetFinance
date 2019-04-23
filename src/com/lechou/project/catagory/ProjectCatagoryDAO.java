package com.lechou.project.catagory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class ProjectCatagoryDAO {
	private static Connection 	conn = DatabaseOperator.getConn();
	/*
	 * ��ѯ������������е�VO����
	 */
	public List<ProjectCatagoryVO> getVOs() {


		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectCatagoryVO> vos = new ArrayList<ProjectCatagoryVO>();
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_catagory  order by publishtime desc ";
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectCatagoryVO vo = null;
			while (rs.next()) {
				vo = new ProjectCatagoryVO();
				ProjectCatagoryMgr.getInstance().initFromResultSet(vo, rs);
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
	 * ��ҳ��ѯ����
	 * 
	 * @return ���VO��������
	 */
	public int getVOs(List<ProjectCatagoryVO> vos, int pageNo, int pageSize) {
		int totalRecords = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_catagory  order by publishtime desc limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectCatagoryVO vo = null;
			while (rs.next()) {
				vo = new ProjectCatagoryVO();
				ProjectCatagoryMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project_catagory");
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
	public int getVOs(List<ProjectCatagoryVO> vos, int pageNo, int pageSize,
			String queryStr) {
		int totalRecords = -1;
	
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_catagory " + queryStr
					+ " order by publishtime desc limit " + (pageNo - 1)
					* pageSize + "," + pageSize;
			System.out
					.println("��ʼִ�е���select���"
							+ sql);
			rs = stmt.executeQuery(sql);
			ProjectCatagoryVO vo = null;
			while (rs.next()) {
				vo = new ProjectCatagoryVO();
				ProjectCatagoryMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project_catagory" + queryStr);
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
	public ProjectCatagoryVO getVOById(int id) {
		ProjectCatagoryVO vo = new ProjectCatagoryVO();
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_catagory where catagoryid=" + id;
		System.out
				.println("��ʼִ�е���select���"
						+ sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo = ProjectCatagoryMgr.getInstance().getProjectCatagoryVOFromRs(rs);
		return vo;

	}

	/*
	 * ����id������ѯVO����
	 */
	public List<ProjectCatagoryVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * ����VO��������
	 * 
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int addByVO(ProjectCatagoryVO vo) {

	
		String sql = "insert into lechou_project_catagory values(null,?,?,?,?,?)";
		System.out
				.println("��ʼִ�е���insert��䣺"
						+ sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {
			
			pstmt.setString(1, vo.getCatagoryname());
			pstmt.setString(2, vo.getDescr());
			pstmt.setTimestamp(3, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(4, vo.getModifier());
			pstmt.setTimestamp(5, new Timestamp(vo.getModifytime().getTime()));
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
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int addByVO(List<ProjectCatagoryVO> vos) {
		return 0;

	}

	/*
	 * ����VO����ɾ��
	 * 
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int deleteByVO(ProjectCatagoryVO vo) {
		return 0;

	}

	/*
	 * ����idɾ��
	 * 
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int deleteById(int id) {
	

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_project_catagory where catagoryid =" + id;
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
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int batchDelete(List<ProjectCatagoryVO> vos) {
		return 0;

	}

	/*
	 * ����id����ɾ��
	 * 
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int batchDelete(int[] idArray) {
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		System.out
				.println("��ʼִ������delete���");
		for (int i = 0; i < idArray.length; i++) {
			String sql = "delete from lechou_project_catagory where catagoryid ="
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
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int update(ProjectCatagoryVO vo) {

	
		PreparedStatement pstmt = null;
		
		String sql = "update lechou_project_catagory set catagoryname=?,descr=?,publishtime=?,modifier=?,"
				+ "modifytime=?  where catagoryid = ?";
		System.out
				.println("��ʼִ�е���update��䣺"
						+ sql);

		try {
			pstmt = DatabaseOperator.prepareStatement(conn, sql);
			pstmt.setString(1, vo.getCatagoryname());
			pstmt.setString(2, vo.getDescr());
			pstmt.setTimestamp(3, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(4, vo.getModifier());
			pstmt.setTimestamp(5, new Timestamp(vo.getModifytime().getTime()));
			pstmt.setInt(6, vo.getCatagoryid());
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
	 * @return ���ӳɹ�����0������ʧ�ܷ���-1
	 */
	public int update(List<ProjectCatagoryVO> vos) {
		return 0;
	}

}