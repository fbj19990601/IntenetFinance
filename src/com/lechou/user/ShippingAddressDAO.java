package com.lechou.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class ShippingAddressDAO {
	private static Connection conn = DatabaseOperator.getConn();
	/*
	 * ��ѯ����������е�VO����
	 */
	public List<ShippingAddressVO> getVOs() {
		List<ShippingAddressVO> vos = new ArrayList<ShippingAddressVO>();
		

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_shipping_address  order by publishtime desc";
			System.out.println("��ʼִ�е���select���"+sql);
			rs = stmt.executeQuery(sql);
			ShippingAddressVO vo = null;
			while (rs.next()) {
				vo = new ShippingAddressVO();
				ShippingAddressMgr.getInstance().initFromResultSet(vo, rs);
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
	 * ��ѯ������õ�ǰ��¼�˵�VO����
	 */
	public List<ShippingAddressVO> getVOsByUserId(int userid) {
		List<ShippingAddressVO> vos = new ArrayList<ShippingAddressVO>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_shipping_address where pk_user="+userid+"  order by publishtime desc";
			System.out.println("��ʼִ�е���select���"+sql);
			rs = stmt.executeQuery(sql);
			ShippingAddressVO vo = null;
			while (rs.next()) {
				vo = new ShippingAddressVO();
				ShippingAddressMgr.getInstance().initFromResultSet(vo, rs);
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
	 * ����id��ѯVO����
	 */
	public ShippingAddressVO getVOById(int id) {
		ShippingAddressVO vo = new ShippingAddressVO();
	 
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_shipping_address where pk_shipping_address=" + id;
		System.out.println("��ʼִ�е���select���" + sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vo = ShippingAddressMgr.getInstance().getShippingAddressVOFromRs(rs);
		return vo;

	}

	/*
	 * ����id������ѯVO����
	 */
	public List<ShippingAddressVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(ShippingAddressVO vo) {

		String sql = "insert into lechou_shipping_address values(null,?,?,?,?,?,?,?,?)";
		System.out.println("��ʼִ�е���insert��䣺" + sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {
			pstmt.setInt(1, vo.getPk_user());
			pstmt.setString(2, vo.getReceiver());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getPostcode());
			pstmt.setTimestamp(6, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(7, vo.getModifier());
			pstmt.setTimestamp(8, new Timestamp(vo.getModifytime().getTime()));

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
	public int addByVO(List<ShippingAddressVO> vos) {
		return 0;

	}

	/*
	 * ����VO����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteByVO(ShippingAddressVO vo) {
		return 0;

	}

	/*
	 * ����idɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteById(int id) {

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_shipping_address where pk_shipping_address =" + id;
		System.out.println("��ʼִ�е���delete���"+sql);
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
	public int batchDelete(List<ShippingAddressVO> vos) {
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
			String sql = "delete from lechou_shipping_address where pk_shipping_address =" + idArray[i];
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
	public int update(ShippingAddressVO vo) {

		PreparedStatement pstmt = null;

		String sql = "update lechou_shipping_address set pk_user=?,receiver=?,tel=?,address=?,postcode=?,publishtime=?,modifier=?,"
				+ "modifytime=? where pk_shipping_address = ?";
		System.out.println("��ʼִ�е���update��䣺" + sql);

		try {
			
			pstmt = DatabaseOperator.prepareStatement(conn, sql);
			pstmt.setInt(1, vo.getPk_user());
			pstmt.setString(2, vo.getReceiver());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getPostcode());
			pstmt.setTimestamp(6, new Timestamp(vo.getPublishtime().getTime()));
			pstmt.setString(7, vo.getModifier());
			pstmt.setTimestamp(8, new Timestamp(vo.getModifytime().getTime()));
			pstmt.setInt(9, vo.getPk_shipping_address());
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
	public int update(List<ShippingAddressVO> vos) {
		return 0;
	}

}
