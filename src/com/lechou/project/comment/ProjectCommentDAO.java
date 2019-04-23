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
	 * 查询处理，获得所有的VO对象
	 */
	public List<ProjectCommentVO> getVOs() {
	
		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectCommentVO> vos = new ArrayList<ProjectCommentVO>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_comment  order by publishtime desc ";
			System.out
					.println("开始执行单行select语句"
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
	 * 查询处理，获得所有的VO对象
	 */
	public int getVOCountByProjectid(int pk_project) {
	
		Statement stmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select count(*) from lechou_project_comment  where pk_project = "+pk_project;
			System.out
					.println("开始执行单行select语句"
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
	 * 分页查询处理
	 * 
	 * @return 获得VO对象总数
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
					.println("开始执行单行select语句"
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
					.println("开始执行单行select语句"
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
	 * 按条件分页查询处理
	 * 
	 * @return 获得VO对象总数
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
					.println("开始执行单行select语句"
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
					.println("开始执行单行select语句"
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
	 * 根据id查询VO对象
	 */
	public ProjectCommentVO getVOById(int id) {
		ProjectCommentVO vo = new ProjectCommentVO();
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_comment where pk_project_comment=" + id;
		System.out
				.println("开始执行单行select语句"
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
	 * 根据id批量查询VO对象
	 */
	public List<ProjectCommentVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * 根据VO对象增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(ProjectCommentVO vo) {

		String sql = "insert into lechou_project_comment values(null,?,?,?,?,?,?)";
		System.out
				.println("开始执行单行insert语句："
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
	 * 根据VO对象批量增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(List<ProjectCommentVO> vos) {
		return 0;

	}

	/*
	 * 根据VO对象删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteByVO(ProjectCommentVO vo) {
		return 0;

	}

	/*
	 * 根据id删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteById(int id) {

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_project_comment where pk_project_comment =" + id;
		System.out
				.println("开始执行单行delete语句");
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
	public int batchDelete(List<ProjectCommentVO> vos) {
		return 0;

	}

	/*
	 * 根据id批量删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int batchDelete(int[] idArray) {
		Statement stmt = DatabaseOperator.createStatement(conn);
		System.out
				.println("开始执行批量delete语句");
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
	public int update(ProjectCommentVO vo) {

		PreparedStatement pstmt = null;
		String sql = "update lechou_project_comment set pk_project=?,pk_user=?,commentcontent=?,publishtime=?,"
				+ "modifier=?,modifytime=? where pk_project_comment = ?";
		System.out
				.println("开始执行单行update语句："
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
	 * 根据VO批量更新
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int update(List<ProjectCommentVO> vos) {
		return 0;
	}

}
