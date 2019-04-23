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
	 * ��ѯ����������е�VO����
	 */
	public List<UserVO> getVOs() {
		return null;

	}

	/*
	 * ��ҳ��ѯ����
	 * 
	 * @return ���VO��������
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
			System.out.println("��ʼִ�е���select���"+sql);
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
			System.out.println("��ʼִ�е���select���"+sql);
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
			System.out.println("��ʼִ�е���select���"+sql);
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
			System.out.println("��ʼִ�е���select���"+sql);
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
	public UserVO getVOById(int id) {
		UserVO user = new UserVO();

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_user where pk_user=" + id;
		System.out.println("��ʼִ�е���select���"+sql);
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
	 * ����id������ѯVO����
	 */
	public List<UserVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(UserVO vo) {

	
		String sql = "insert into lechou_user values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("��ʼִ�е���insert��䣺"+sql);
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
	 * ����VO������������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int addByVO(List<UserVO> vos) {
		return 0;

	}

	/*
	 * ����VO����ɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteByVO(UserVO vo) {
		return 0;

	}

	/*
	 * ����idɾ��
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int deleteById(int id) {


		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_user where pk_user =" + id;
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
	public int batchDelete(List<UserVO> vos) {
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
	public int update(UserVO vo) {

	
		PreparedStatement pstmt = null;
			
		String sql = "update lechou_user set username=?,userpassword=?,sex=?,tel=?,email=?,iconurl=?,resume=?,"
				+ "modifier=?,modifytime=?,register=?,registetime=?,firstlogintime=?,"
				+ "lastlogintime=? ,isadmin=? where pk_user = ?";
		System.out.println("��ʼִ�е���update��䣺"+sql);

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
	 * ����VO��������
	 * 
	 * @return ��ӳɹ�����0�����ʧ�ܷ���-1
	 */
	public int update(List<UserVO> vos) {
		return 0;
	}

	public Boolean isPasswordRight(List<UserVO> vos) {
		return true;
	}
	
	/*
	 * �ж��û����Ƿ����
	 */
	public Boolean isNameExist(String name) {
		
		
		
			Statement stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_user where username='" + name+"'";
			System.out.println("ִ�е��в�ѯ���"+sql);
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
	 * �ж��û��������Ƿ����
	 */
	public Boolean isUserEmailExist(String email) {
		
		
			
			Statement stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_user where email='" + email+"'";
			System.out.println("ִ�е��в�ѯ���"+sql);
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
	 * �ж��ֻ����Ƿ����
	 */
	public Boolean isUserTelExist(String tel) {
		
		
	
			Statement stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_user where tel='" + tel+"'";
			System.out.println("ִ�е��в�ѯ���"+sql);
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
	
		System.out.println("��ʼִ�е���select���"+sql);
		Statement stmt = DatabaseOperator.createStatement(conn);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			if (!rs.next()) {
				throw new UserNotFoundException("�û�������:" + username);		
			} else if (!PasswordEncryptor.GetMD5Code(password).equals(rs.getString("userpassword"))){
				throw new PasswordNotCorrectException("���벻��ȷ!");
			}
			else if(rs.getInt("isadmin") != isadmin)
				{
				throw new UsertypeNotCorrectException("�û����ʹ���!");
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
