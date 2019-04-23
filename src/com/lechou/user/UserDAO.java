package com.lechou.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.lechou.util.*;

public class UserDAO {
	private static Connection	conn = DatabaseOperator.getConn();
	/*
	 * 查询处理，获得所有的VO对象
	 */
	public List<UserVO> getVOs() {
		return null;

	}

	/*
	 * 分页查询处理
	 * 
	 * @return 获得VO对象总数
	 */
	public int getVOs(List<UserVO> vos, int pageNo, int pageSize) {
		int totalRecords = -1;

		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_user  order by registetime desc limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			System.out.println("开始执行单行select语句"+sql);
			rs = stmt.executeQuery(sql);
			UserVO vo = null;
			while (rs.next()) {
				vo = new UserVO();
				UserMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_user");
			System.out.println("开始执行单行select语句"+sql);
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
	 * 按条件分页查询处理
	 * 
	 * @return 获得VO对象总数
	 */
	public int getVOs(List<UserVO> vos, int pageNo, int pageSize,
			String queryStr) {
		int totalRecords = -1;
	
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_user " + queryStr
					+ " order by registetime desc limit " + (pageNo - 1)
					* pageSize + "," + pageSize;
			System.out.println("开始执行单行select语句"+sql);
			rs = stmt.executeQuery(sql);
			UserVO vo = null;
			while (rs.next()) {
				vo = new UserVO();
				UserMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_user" + queryStr);
			System.out.println("开始执行单行select语句"+sql);
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
	 * 根据id查询VO对象
	 */
	public UserVO getVOById(int id) {
		UserVO user = new UserVO();

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_user where pk_user=" + id;
		System.out.println("开始执行单行select语句"+sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		user = UserMgr.getInstance().getUserVOFromRs(rs);
		return user;

	}

	

	/*
	 * 根据id批量查询VO对象
	 */
	public List<UserVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * 根据VO对象增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(UserVO vo) {

	
		String sql = "insert into lechou_user values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("开始执行单行insert语句："+sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {
			pstmt.setString(1, vo.getUsername());
			pstmt.setString(2, PasswordEncryptor.GetMD5Code(vo.getUserpassword()));
			pstmt.setString(3, vo.getSex());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getIconurl());
			pstmt.setString(7, vo.getResume());
			pstmt.setString(8, vo.getModifier());
			pstmt.setTimestamp(9, new Timestamp(vo.getModifytime().getTime()));
			pstmt.setString(10, vo.getRegister());
			pstmt.setTimestamp(11, new Timestamp(vo.getRegistetime().getTime()));
			pstmt.setTimestamp(12, new Timestamp(vo.getFirstlogintime().getTime()));
			pstmt.setTimestamp(13, new Timestamp(vo.getLastlogintime().getTime()));
			pstmt.setInt(14, vo.getIsadmin());
			 
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
	public int addByVO(List<UserVO> vos) {
		return 0;

	}

	/*
	 * 根据VO对象删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteByVO(UserVO vo) {
		return 0;

	}

	/*
	 * 根据id删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteById(int id) {


		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_user where pk_user =" + id;
		System.out.println("开始执行单行delete语句");
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
	public int batchDelete(List<UserVO> vos) {
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
			String sql = "delete from lechou_user where pk_user =" + idArray[i];
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
	public int update(UserVO vo) {

	
		PreparedStatement pstmt = null;
			
		String sql = "update lechou_user set username=?,userpassword=?,sex=?,tel=?,email=?,iconurl=?,resume=?,"
				+ "modifier=?,modifytime=?,register=?,registetime=?,firstlogintime=?,"
				+ "lastlogintime=? ,isadmin=? where pk_user = ?";
		System.out.println("开始执行单行update语句："+sql);

		try {
			pstmt = DatabaseOperator.prepareStatement(conn, sql);
			pstmt.setString(1, vo.getUsername());
			pstmt.setString(2, vo.getUserpassword());
			pstmt.setString(3, vo.getSex());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getEmail());
			pstmt.setString(6, vo.getIconurl());
			pstmt.setString(7, vo.getResume());
			pstmt.setString(8, vo.getModifier());
			pstmt.setTimestamp(9, new Timestamp(vo.getModifytime().getTime()));
			pstmt.setString(10, vo.getModifier());
			pstmt.setTimestamp(11, new Timestamp(vo.getRegistetime().getTime()));
			pstmt.setTimestamp(12, new Timestamp(vo.getFirstlogintime().getTime()));
			pstmt.setTimestamp(13, new Timestamp(vo.getLastlogintime().getTime()));
			pstmt.setInt(14, vo.getIsadmin());
			pstmt.setInt(15, vo.getPk_user());
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
	public int update(List<UserVO> vos) {
		return 0;
	}

	public Boolean isPasswordRight(List<UserVO> vos) {
		return true;
	}
	
	/*
	 * 判断用户名是否存在
	 */
	public Boolean isNameExist(String name) {
		
		
		
			Statement stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_user where username='" + name+"'";
			System.out.println("执行单行查询语句"+sql);
			ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
			int i = 0;
			try {
				rs.next();
				i = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseOperator.close(rs);
				DatabaseOperator.close(stmt);
			}
			if (i == 0)
				return false;
			else
				return true;
	}
	

	/*
	 * 判断用户邮箱名是否存在
	 */
	public Boolean isUserEmailExist(String email) {
		
		
			
			Statement stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_user where email='" + email+"'";
			System.out.println("执行单行查询语句"+sql);
			ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
			int i = 0;
			try {
				rs.next();
				i = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseOperator.close(rs);
				DatabaseOperator.close(stmt);
			}
			if (i == 0)
				return false;
			else
				return true;
	}



	/*
	 * 判断手机号是否存在
	 */
	public Boolean isUserTelExist(String tel) {
		
		
	
			Statement stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_user where tel='" + tel+"'";
			System.out.println("执行单行查询语句"+sql);
			ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
			int i = 0;
			try {
				rs.next();
				i = rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseOperator.close(rs);
				DatabaseOperator.close(stmt);
			
			}
			if (i == 0)
				return false;
			else
				return true;
	}

	public Boolean check(String username, String password, UserVO vo,int isadmin)
			throws UserNotFoundException, PasswordNotCorrectException {

		conn = DatabaseOperator.getConn();
		String sql = "select * from lechou_user where username='" + username
				+ "'";
	
		System.out.println("开始执行单行select语句"+sql);
		Statement stmt = DatabaseOperator.createStatement(conn);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			if (!rs.next()) {
				throw new UserNotFoundException("用户不存在:" + username);		
			} else if (!PasswordEncryptor.GetMD5Code(password).equals(rs.getString("userpassword"))){
				throw new PasswordNotCorrectException("密码不正确!");
			}
			else if(rs.getInt("isadmin") != isadmin)
				{
				throw new UsertypeNotCorrectException("用户类型错误!");
				}
			else{
				UserMgr.getInstance().initFromResultSet(vo, rs);	
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseOperator.close(rs);
			DatabaseOperator.close(stmt);
			
		}

		return true;

	}

}
