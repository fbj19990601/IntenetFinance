package com.lechou.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderMgr {

	private static OrderMgr mgr = null;
	private static OrderDAO dao = new OrderDAO();

	private OrderMgr() {
	}

	public static OrderMgr getInstance() {
		if (mgr == null) {
			mgr = new OrderMgr();
		}
		return mgr;
	}
	
	public OrderVO getOrderVOFromRs(ResultSet rs) {
		OrderVO vo = new OrderVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(OrderVO vo, ResultSet rs) {
		try {
			vo.setPk_order(rs.getInt("pk_order"));
			vo.setPk_project(rs.getInt("pk_project"));
			vo.setPk_user(rs.getInt("pk_user"));
			vo.setAmount(rs.getInt("amount"));
			vo.setTotalsum(rs.getInt("totalsum"));
			vo.setIspay(rs.getInt("ispay"));
			vo.setPayway(rs.getString("payway"));
			vo.setIsshiping(rs.getInt("isshiping")); 
			vo.setOrdertype(rs.getString("ordertype"));
			vo.setPublishtime(rs.getTimestamp("publishtime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getOwnOrder(List<OrderVO> orders, int pk_user) {
		return dao.getVOsByUserId(orders, pk_user);
	}
	
	public int getorders(List<OrderVO> orders, int pageNo, int pageSize) {
		return dao.getVOs(orders, pageNo, pageSize);
	}
	
	public int getorders(List<OrderVO> orders, int pageNo, int pageSize,
			OrderSearchVO ordersearchvo) {

		String queryStr = " where 1=1 ";
		
		if (ordersearchvo.getIspay() != -1) {
			queryStr += " and lechou_order.ispay ="
					+ ordersearchvo.getIspay() + "";
		}
		if (ordersearchvo.getProjectname() != null
				&& !ordersearchvo.getProjectname().trim().equals("")) {
			queryStr += " and lechou_order.pk_project in (select lechou_project.pk_project from lechou_project where  lechou_project.title like '%" + ordersearchvo.getProjectname()
					+ "%')";
		}
		if (ordersearchvo.getUsername() != null
				&& !ordersearchvo.getUsername().trim().equals("")) {
			queryStr += " and lechou_order.pk_user in (select lechou_user.pk_user from lechou_user where  lechou_user.username like '%" + ordersearchvo.getUsername()
					+ "%')";
		}
		System.out.println("构建用户查询语句："+queryStr);
		return dao.getVOs(orders, pageNo, pageSize, queryStr);
	}
	
	public int getVOsByProjectId(List<OrderVO> vos, int pageNo, int pageSize,int pk_project) {
		String querystr = " where 1 = 1" ;
		if(pk_project != 0){
			querystr +=" and pk_project = "+pk_project;
		}
	
		return dao.getVOs(vos, pageNo, pageSize, querystr);
	
	}
	public OrderVO getOrderVOById(int id) {
		return dao.getVOById(id);
	}
	public int getOrderCountByProjectId(int id) {
		return dao.getVOCountByProjectId(id);
	}
	
	public int deleteOrder(int  id){
		return dao.deleteById(id);
		
	}
	public int deleteOrder(int[]ids){
		return dao.batchDelete(ids);
		
	}
	
	public int getProjectCompleteFund(int pk_project){
		return dao.getVOsByProjectId(pk_project);
	}
	public int addOrder(OrderVO vo){
		return dao.addByVO(vo);
	}
	

	}

