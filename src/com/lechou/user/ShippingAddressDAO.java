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
	 * 查询处理，获得所有的VO对象
	 */
	public List<ShippingAddressVO> getVOs() {
		List<ShippingAddressVO> vos = new ArrayList<ShippingAddressVO>();
		

		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_shipping_address  order by publishtime desc";
			System.out.println("开始执行单行select语句"+sql);
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
	 * 查询处理，获得当前登录人的VO对象
	 */
	public List<ShippingAddressVO> getVOsByUserId(int userid) {
		List<ShippingAddressVO> vos = new ArrayList<ShippingAddressVO>();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_shipping_address where pk_user="+userid+"  order by publishtime desc";
			System.out.println("开始执行单行select语句"+sql);
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
	 * 根据id查询VO对象
	 */
	public ShippingAddressVO getVOById(int id) {
		ShippingAddressVO vo = new ShippingAddressVO();
	 
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_shipping_address where pk_shipping_address=" + id;
		System.out.println("开始执行单行select语句" + sql);
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
	 * 根据id批量查询VO对象
	 */
	public List<ShippingAddressVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * 根据VO对象增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(ShippingAddressVO vo) {

		String sql = "insert into lechou_shipping_address values(null,?,?,?,?,?,?,?,?)";
		System.out.println("开始执行单行insert语句：" + sql);
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
	 * 根据VO对象批量增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(List<ShippingAddressVO> vos) {
		return 0;

	}

	/*
	 * 根据VO对象删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteByVO(ShippingAddressVO vo) {
		return 0;

	}

	/*
	 * 根据id删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteById(int id) {

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_shipping_address where pk_shipping_address =" + id;
		System.out.println("开始执行单行delete语句"+sql);
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
	 * 根据VO对象批量删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int batchDelete(List<ShippingAddressVO> vos) {
		return 0;

	}

	/*
	 * 根据id批量删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int batchDelete(int[] idArray) {
		Statement stmt = DatabaseOperator.createStatement(conn);
		System.out.println("开始执行批量delete语句");
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
	 * 根据VO更新 java.util.date
	 * 日期时间,java.sql.date日期,java.sql.stamptime日期时间,所以向数据库中写入时要使用timestampt:
	 * 存储：preparedStatement.setTimestamp(1, new java.sql.Timestamp(new
	 * java.util.Date().getTime())); 读取：java.util.Date d =
	 * resultSet.getTimestamp(1)或者java.util.Date d = new
	 * java.util.Date(resultSet.getTimestamp(1).getTime())； java.util.Date d =
	 * new java.util.Date(resultSet.getTimestamp(1).getTime()); new
	 * SimpleTimeFormat("yyyyy-MM-dd HH:mm:ss").format(d);
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int update(ShippingAddressVO vo) {

		PreparedStatement pstmt = null;

		String sql = "update lechou_shipping_address set pk_user=?,receiver=?,tel=?,address=?,postcode=?,publishtime=?,modifier=?,"
				+ "modifytime=? where pk_shipping_address = ?";
		System.out.println("开始执行单行update语句：" + sql);

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
	 * 根据VO批量更新
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int update(List<ShippingAddressVO> vos) {
		return 0;
	}

}
