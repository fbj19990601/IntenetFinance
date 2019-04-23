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
	 * 查询处理，获得所有的VO对象
	 */
	public List<ProjectLatestnewsVO> getVOs() {
		
	
		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectLatestnewsVO> vos = new ArrayList<ProjectLatestnewsVO>();
		try {
			
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project_latestnews  order by publishtime desc ";
			System.out.println("开始执行单行select语句" + sql);
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
	 * 按条件分页查询处理
	 * 
	 * @return 获得VO对象总数
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
			System.out.println("开始执行单行select语句" + sql);
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
			System.out.println("开始执行单行select语句" + sql1);
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
	public ProjectLatestnewsVO getVOById(int id) {
		ProjectLatestnewsVO vo = new ProjectLatestnewsVO();
	
		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project_latestnews where pk_project_latestnews="
				+ id;
		System.out.println("开始执行单行select语句" + sql);
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
		System.out.println("开始执行单行select语句" + sql);
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
			System.out.println("开始执行单行select语句" + sql);
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
			System.out.println("开始执行单行select语句" + sql1);
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
	 * 根据id批量查询VO对象
	 */
	public List<ProjectLatestnewsVO> getVOById(int[] ids) {
		return null;

	}
	

	/*
	 * 根据VO对象增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(ProjectLatestnewsVO vo) {

		
		String sql = "insert into lechou_project_latestnews values(null,?,?,?,?,?,?,?)";
		System.out.println("开始执行单行insert语句：" + sql);
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
	 * 根据VO对象批量增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(List<ProjectLatestnewsVO> vos) {
		return 0;

	}

	/*
	 * 根据VO对象删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteByVO(ProjectLatestnewsVO vo) {
		return 0;

	}

	/*
	 * 根据id删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteById(int id) {
	

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_project_latestnews where pk_project_latestnews ="
				+ id;
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
	public int batchDelete(List<ProjectLatestnewsVO> vos) {
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
	public int update(ProjectLatestnewsVO vo) {
	
		
		PreparedStatement pstmt = null;
		String sql = "update lechou_project_latestnews set pk_project=?,content=?,imagesurl=?,"
				+ "moviesurl=?,publishtime=?,modifier=?,modifytime=?  where pk_project_latestnews = ?";
		System.out.println("开始执行单行update语句：" + sql);

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
	 * 根据VO批量更新
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int update(List<ProjectLatestnewsVO> vos) {
		return 0;
	}
}
