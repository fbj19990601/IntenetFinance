package com.lechou.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class OrderDAO {
	private static Connection conn = DatabaseOperator.getConn();
	/*
	 * ��ѯ����������е�VO����
	 */
	public List<OrderVO> getVOs() {

		
		Statement stmt = null;
		ResultSet rs = null;
		List<OrderVO> vos = new ArrayList<OrderVO>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_order  order by publishtime desc ";
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			OrderVO vo = null;
			while (rs.next()) {
				vo = new OrderVO();
				OrderMgr.getInstance().initFromResultSet(vo, rs);
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
	public int getVOs(List<OrderVO> vos, int pageNo, int pageSize) {
		int totalRecords = -1;
	
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
	
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_order  order by publishtime desc limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			OrderVO vo = null;
			while (rs.next()) {
				vo = new OrderVO();
				OrderMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_order");
			System.out.println("��ʼִ�е���select���" + sql);
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
	public int getVOs(List<OrderVO> vos, int pageNo, int pageSize,
			String queryStr) {
		int totalRecords = -1;
	
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_order " + queryStr
					+ " order by publishtime desc limit " + (pageNo - 1)
					* pageSize + "," + pageSize;
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			OrderVO vo = null;
			while (rs.next()) {
				vo = new OrderVO();
				OrderMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_order" + queryStr);
			System.out.println("��ʼִ�е���select���" + sql);
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
	public OrderVO getVOById(int id) {
		OrderVO vo = new OrderVO();
		
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_order where pk_order=" + id;
		System.out.println("��ʼִ�е���select���" + sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo = OrderMgr.getInstance().getOrderVOFromRs(rs);
		return vo;

	}

	public int getVOsByProjectId(int id) {
		int sum = 0;
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select totalsum from lechou_order where pk_project=" + id;
		System.out.println("��ʼִ�е���select���" + sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			while (rs.next()) {
				sum += rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return sum;

	}
	public int getVOCountByProjectId(int id) {
		int sum = 0;
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select count(*) from lechou_order where pk_project=" + id;
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
	
	
	
	public int getVOsByUserId(List<OrderVO> vos,int id) {
		int totalRecords = -1;

		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_order where pk_user= "+id;
			System.out.println("��ʼִ�е���select���" + sql);
			rs = stmt.executeQuery(sql);
			OrderVO vo = null;
			while (rs.next()) {
				vo = new OrderVO();
				OrderMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_order where pk_user =" + id);
			System.out.println("��ʼִ�е���select���" + sql);
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
	public List<OrderVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(OrderVO vo) {


		String sql = "insert into lechou_order values(null,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("��ʼִ�е���insert��䣺" + sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {

			pstmt.setInt(1, vo.getPk_project());
			pstmt.setInt(2, vo.getPk_user());
			pstmt.setInt(3, vo.getAmount());
			pstmt.setInt(4, vo.getTotalsum());
			pstmt.setInt(5, vo.getIspay());
			pstmt.setString(6, vo.getPayway());
			pstmt.setInt(7, vo.getIsshiping());
			pstmt.setString(8, vo.getOrdertype());
			pstmt.setTimestamp(9, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(10, vo.getModifier());
			pstmt.setTimestamp(11, new Timestamp(vo.getModifytime().getTime()));
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
	public int addByVO(List<OrderVO> vos) {
		return 0;

	}

	/*
	 * ����VO����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteByVO(OrderVO vo) {
		return 0;

	}

	/*
	 * ����idɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteById(int id) {


		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_order where pk_order =" + id;
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
	public int batchDelete(List<OrderVO> vos) {
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
			String sql = "delete from lechou_order where pk_order ="
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
	public int update(OrderVO vo) {

	

		PreparedStatement pstmt = null;
		String sql = "update lechou_order set pk_project=?,pk_user=?,amount=?,totalsum=?,"
				+ "ispay=?,payway=?,publishtime=?,modifier=?,"
				+ "modifytime=?  where pk_order = ?";
		System.out.println("��ʼִ�е���update��䣺" + sql);
		try {
			pstmt = DatabaseOperator.prepareStatement(conn, sql);

			pstmt.setInt(1, vo.getPk_project());
			pstmt.setInt(2, vo.getPk_user());
			pstmt.setInt(3, vo.getAmount());
			pstmt.setInt(4, vo.getTotalsum());
			pstmt.setInt(5, vo.getIspay());
			pstmt.setString(6, vo.getPayway());
			pstmt.setInt(7, vo.getIsshiping());
			pstmt.setString(8, vo.getOrdertype());
			pstmt.setTimestamp(9, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(10, vo.getModifier());
			pstmt.setTimestamp(11, new Timestamp(vo.getModifytime().getTime()));
			pstmt.setInt(12, vo.getPk_order());
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
	public int update(List<OrderVO> vos) {
		return 0;
	}
}
