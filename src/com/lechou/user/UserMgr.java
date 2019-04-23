package com.lechou.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserMgr {
	private static UserMgr mgr = null;

	private static UserDAO dao = new UserDAO();

	private UserMgr() {
	}

	public static UserMgr getInstance() {
		if (mgr == null) {
			mgr = new UserMgr();
		}
		return mgr;
	}
	
	public UserVO getUserVOFromRs(ResultSet rs) {
		UserVO vo = new UserVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(UserVO vo, ResultSet rs) {
		try {
			vo.setPk_user(rs.getInt("pk_user"));
			vo.setUsername(rs.getString("username"));
			vo.setUserpassword(rs.getString("userpassword"));
			vo.setSex(rs.getString("sex"));
			vo.setTel(rs.getString("tel"));
			vo.setEmail(rs.getString("email"));
			vo.setIconurl(rs.getString("iconurl"));
			vo.setResume(rs.getString("resume"));
			vo.setRegister(rs.getString("register"));
			vo.setRegistetime(rs.getTimestamp("registetime"));
			vo.setFirstlogintime(rs.getTimestamp("firstlogintime"));
			vo.setLastlogintime(rs.getTimestamp("lastlogintime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));
			vo.setIsadmin(rs.getInt("isadmin"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<UserVO> getUsers() {
		return dao.getVOs();
	}

	public int getUsers(List<UserVO> Users, int pageNo, int pageSize) {
		return dao.getVOs(Users, pageNo, pageSize);
	}

	public int getUsers(List<UserVO> Users, int pageNo, int pageSize,
			UserSearchVO usersearchvo) {

		String queryStr = " where 1=1 ";

		if (usersearchvo.getUsername() != null
				&& !usersearchvo.getUsername().trim().equals("")) {
			queryStr += " and lechou_user.username like '%"
					+ usersearchvo.getUsername() + "%'";
		}
	
		if (usersearchvo.getSex() != null
				&& !usersearchvo.getSex().trim().equals("")) {
			queryStr += " and lechou_user.sex = '" + usersearchvo.getSex()
					+ "'";
		}
		if (usersearchvo.getEmail() != null	&& !usersearchvo.getEmail().trim().equals("")&&usersearchvo.getTel() != null && !usersearchvo.getTel().trim().equals("")) {
			queryStr += " and (lechou_user.email like '%"
					+ usersearchvo.getEmail() + "%'"+"or lechou_user.tel like '%" + usersearchvo.getTel()
					+ "%')";
		}
		if (usersearchvo.getIsadmin() !=-1) {
			queryStr += " and lechou_user.isadmin = " + usersearchvo.getIsadmin();
		}
		System.out.println("构建用户查询语句："+queryStr);
		return dao.getVOs(Users, pageNo, pageSize, queryStr);
	}

	public int addUser(UserVO vo) throws UserNameExistException{

		if (dao.isNameExist(vo.getUsername()))
		{
			throw new UserNameExistException("用户名称重复:" + vo.getUsername());
		}
		return dao.addByVO(vo);
	}

	public int updateUser(UserVO vo)  throws UserNameExistException{
		
		return dao.update(vo);
	}

	public int initialPassword(int id) {
		UserVO vo = new UserVO();
		vo = UserMgr.getInstance().getUserVOById(id);
		vo.setUserpassword("1");
		return dao.update(vo);
	}

	public int deleteUser(int id) {
		return dao.deleteById(id);
	}

	public int deleteUser(int[] id) {
		return dao.batchDelete(id);
	}

	public UserVO getUserVOById(int id) {
		return dao.getVOById(id);
	}

	public Boolean check(String username, String password, UserVO vo,int isadmin) {
		return dao.check(username, password, vo,isadmin);

	}

	

}
