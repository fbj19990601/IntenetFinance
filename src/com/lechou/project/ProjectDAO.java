package com.lechou.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lechou.util.DatabaseOperator;

public class ProjectDAO {
	public static Connection conn = DatabaseOperator.getConn();;
	/*
	 * 查询处理，获得所有的VO对象
	 */
	public List<ProjectVO> getVOs() {

		Statement stmt = null;
		ResultSet rs = null;
		List<ProjectVO> vos = new ArrayList<ProjectVO>();
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project  order by publishtime desc ";
			System.out.println("开始执行单行select语句" + sql);
			rs = stmt.executeQuery(sql);
			ProjectVO vo = null;
			while (rs.next()) {
				vo = new ProjectVO();
				ProjectMgr.getInstance().initFromResultSet(vo, rs);
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
	 * 分页查询处理
	 * 
	 * @return 获得VO对象总数
	 */
	public int getVOsByUserId(List<ProjectVO> vos,int pk_user) {
		int totalRecords = -1;
	
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project where initiatorid="+pk_user+" order by publishtime desc ";
			System.out.println("开始执行单行select语句" + sql);
			rs = stmt.executeQuery(sql);
			ProjectVO vo = null;
			while (rs.next()) {
				vo = new ProjectVO();
				ProjectMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project");
			System.out.println("开始执行单行select语句" + sql);
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
	 * 分页查询处理
	 * 
	 * @return 获得VO对象总数
	 */
	public int getVOsByCatagoryId(List<ProjectVO> vos,int id) {
		int totalRecords = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project where catagoryid="+id+" order by publishtime desc ";
			System.out.println("开始执行单行select语句" + sql);
			rs = stmt.executeQuery(sql);
			ProjectVO vo = null;
			while (rs.next()) {
				vo = new ProjectVO();
				ProjectMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project");
			System.out.println("开始执行单行select语句" + sql);
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
	 * 分页查询处理
	 * 
	 * @return 获得VO对象总数
	 */
	public int getVOsByStatus(List<ProjectVO> vos,String status) {
		int totalRecords = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project where status='"+status+"' order by publishtime desc ";
			System.out.println("开始执行单行select语句" + sql);
			rs = stmt.executeQuery(sql);
			ProjectVO vo = null;
			while (rs.next()) {
				vo = new ProjectVO();
				ProjectMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project");
			System.out.println("开始执行单行select语句" + sql);
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
	 * 分页查询处理
	 * 
	 * @return 获得VO对象总数
	 */
	public int getVOs(List<ProjectVO> vos, int pageNo, int pageSize) {
		int totalRecords = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
		
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project  order by publishtime desc limit "
					+ (pageNo - 1) * pageSize + "," + pageSize;
			System.out.println("开始执行单行select语句" + sql);
			rs = stmt.executeQuery(sql);
			ProjectVO vo = null;
			while (rs.next()) {
				vo = new ProjectVO();
				ProjectMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			rsCount = DatabaseOperator.executeQuery(stmtCount,
					"select count(*) from lechou_project");
			System.out.println("开始执行单行select语句" + sql);
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
	public int getVOs(List<ProjectVO> vos, int pageNo, int pageSize,
			String queryStr) {
		int totalRecords = -1;
		
		Statement stmt = null;
		ResultSet rs = null;
		Statement stmtCount = null;
		ResultSet rsCount = null;
		try {
	
			stmt = DatabaseOperator.createStatement(conn);
			String sql = "select * from lechou_project " + queryStr
					+ " order by publishtime desc limit " + (pageNo - 1)
					* pageSize + "," + pageSize;
			System.out.println("开始执行单行select语句" + sql);
			rs = stmt.executeQuery(sql);
			ProjectVO vo = null;
			while (rs.next()) {
				vo = new ProjectVO();
				ProjectMgr.getInstance().initFromResultSet(vo, rs);
				vos.add(vo);
			}
			stmtCount = DatabaseOperator.createStatement(conn);
			String sql1 = "select count(*) from lechou_project " + queryStr;
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
	public ProjectVO getVOById(int id) {
		ProjectVO vo = new ProjectVO();

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "select * from lechou_project where pk_project=" + id;
		System.out.println("开始执行单行select语句" + sql);
		ResultSet rs = DatabaseOperator.executeQuery(stmt, sql);
		try {
			rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		vo = ProjectMgr.getInstance().getProjectVOFromRs(rs);
		return vo;

	}

	/*
	 * 根据id批量查询VO对象
	 */
	public List<ProjectVO> getVOById(int[] ids) {
		return null;

	}

	/*
	 * 根据VO对象增加
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int addByVO(ProjectVO vo) {

	
		String sql = "insert into lechou_project values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		System.out.println("开始执行单行insert语句：" + sql);
		PreparedStatement pstmt = DatabaseOperator.prepareStatement(conn, sql);
		try {
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getSelectableprice1());
			pstmt.setInt(3, vo.getSelectableprice2());
			pstmt.setInt(4, vo.getSelectableprice3());
			pstmt.setInt(5, vo.getFinalprice());
			pstmt.setString(6, vo.getInvestreward());
			pstmt.setString(7, vo.getDescription());
			pstmt.setString(8, vo.getDetaildescription());
			pstmt.setString(9, vo.getTitleimageurl());
			pstmt.setString(10, vo.getDescriptionimages());
			pstmt.setString(11, vo.getMovieurl());
			pstmt.setInt(12, vo.getInitiatorid());
			pstmt.setInt(13, vo.getCatagoryid());
			pstmt.setString(14, vo.getProjectaddress());
			pstmt.setString(15, vo.getStatus());
			pstmt.setInt(16, vo.getFundamount());
			pstmt.setInt(17, vo.getFunddate());
			if(vo.getFundstarttime() != null)
			{
			pstmt.setTimestamp(18, new Timestamp(vo.getFundstarttime()
					.getTime()));
			}
			else{
				pstmt.setTimestamp(18,null);
			}
			if(vo.getFundendtime() != null)
			{
				pstmt.setTimestamp(19, new Timestamp(vo.getFundendtime().getTime()));
			}
			else{
				pstmt.setTimestamp(19,null);
			}
			if(vo.getApprovestarttime() != null)
			{
				pstmt.setTimestamp(20, new Timestamp(vo.getApprovestarttime()
						.getTime()));
			}
			else{
				pstmt.setTimestamp(20,null);
			}
			if(vo.getApproveendtime() != null)
			{
				pstmt.setTimestamp(21, new Timestamp(vo.getApproveendtime()
						.getTime()));
			}
			else{
				pstmt.setTimestamp(21,null);
			}
			pstmt.setInt(22, vo.getFocuscount());
			pstmt.setInt(23, vo.getIssuccesse());
			if(vo.getSuccessetime() != null)
			{		
				pstmt.setTimestamp(24,
					new Timestamp(vo.getSuccessetime().getTime()));
			}
			else{
				pstmt.setTimestamp(24,null);
			}
			pstmt.setInt(25, vo.getSuccessedchoose());
			if(vo.getPublishtime() != null)
			{		
				pstmt.setTimestamp(26, new Timestamp(vo.getPublishtime().getTime()));
			}
			else{
				pstmt.setTimestamp(26,null);
			}
			
			pstmt.setString(27, vo.getModifier());
			if(vo.getModifytime() != null)
			{		
				pstmt.setTimestamp(28, new Timestamp(vo.getModifytime().getTime()));
			}
			else{
				pstmt.setTimestamp(28,null);
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
	public int addByVO(List<ProjectVO> vos) {
		return 0;

	}

	/*
	 * 根据VO对象删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteByVO(ProjectVO vo) {
		return 0;

	}

	/*
	 * 根据id删除
	 * 
	 * @return 添加成功返回0，添加失败返回-1
	 */
	public int deleteById(int id) {
	

		Statement stmt = DatabaseOperator.createStatement(conn);
		String sql = "delete from lechou_project where pk_project =" + id;
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
	public int batchDelete(List<ProjectVO> vos) {
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
			String sql = "delete from lechou_project where pk_project ="
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
	public int update(ProjectVO vo) {
		
		PreparedStatement pstmt = null;
		String sql = "update lechou_project set title=?,selectableprice1=?,selectableprice2=?,selectableprice3=?,finalprice=?,investreward=?," +
				"description=?,detaildescription=?,titleimageurl=?,descriptionimages=?,movieurl=?,initiatorid=?,catagoryid=?,projectaddress=?,status=?," +
				"fundamount=?,funddate=?,fundstarttime=?,fundendtime=?,approvestarttime=?,approveendtime=?,focuscount=?,issuccesse=?," +
				"successetime=?,successedchoose=?,publishtime=?,modifier=?,modifytime=? where pk_project = ?";
		System.out.println("开始执行单行update语句：" + sql);
	
		try {
			pstmt = DatabaseOperator.prepareStatement(conn, sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setInt(2, vo.getSelectableprice1());
			pstmt.setInt(3, vo.getSelectableprice2());
			pstmt.setInt(4, vo.getSelectableprice3());
			pstmt.setInt(5, vo.getFinalprice());
			pstmt.setString(6, vo.getInvestreward());
			pstmt.setString(7, vo.getDescription());
			pstmt.setString(8, vo.getDetaildescription());
			pstmt.setString(9, vo.getTitleimageurl());
			pstmt.setString(10, vo.getDescriptionimages());
			pstmt.setString(11, vo.getMovieurl());
			pstmt.setInt(12, vo.getInitiatorid());
			pstmt.setInt(13, vo.getCatagoryid());
			pstmt.setString(14, vo.getProjectaddress());
			pstmt.setString(15, vo.getStatus());
			pstmt.setInt(16, vo.getFundamount());
			pstmt.setInt(17, vo.getFunddate());
			if(vo.getFundstarttime() != null)
			{
			pstmt.setTimestamp(18, new Timestamp(vo.getFundstarttime()
					.getTime()));
			}
			else{
				pstmt.setTimestamp(18,null);
			}
			if(vo.getFundendtime() != null)
			{
				pstmt.setTimestamp(19, new Timestamp(vo.getFundendtime().getTime()));
			}
			else{
				pstmt.setTimestamp(19,null);
			}
			if(vo.getApprovestarttime() != null)
			{
				pstmt.setTimestamp(20, new Timestamp(vo.getApprovestarttime()
						.getTime()));
			}
			else{
				pstmt.setTimestamp(20,null);
			}
			if(vo.getApproveendtime() != null)
			{
				pstmt.setTimestamp(21, new Timestamp(vo.getApproveendtime()
						.getTime()));
			}
			else{
				pstmt.setTimestamp(21,null);
			}
			pstmt.setInt(22, vo.getFocuscount());
			pstmt.setInt(23, vo.getIssuccesse());
			if(vo.getSuccessetime() != null)
			{		
				pstmt.setTimestamp(24,
					new Timestamp(vo.getSuccessetime().getTime()));
			}
			else{
				pstmt.setTimestamp(24,null);
			}
			pstmt.setInt(25, vo.getSuccessedchoose());
			if(vo.getPublishtime() != null)
			{		
				pstmt.setTimestamp(26, new Timestamp(vo.getPublishtime().getTime()));
			}
			else{
				pstmt.setTimestamp(26,null);
			}
			
			pstmt.setString(27, vo.getModifier());
			if(vo.getModifytime() != null)
			{		
				pstmt.setTimestamp(28, new Timestamp(vo.getModifytime().getTime()));
			}
			else{
				pstmt.setTimestamp(28,null);
			}
			pstmt.setInt(29, vo.getPk_project());
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
	public int update(List<ProjectVO> vos) {
		return 0;
	}

}
