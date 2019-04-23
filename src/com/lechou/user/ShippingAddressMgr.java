package com.lechou.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShippingAddressMgr {
	private static ShippingAddressMgr mgr = null;
	private static ShippingAddressDAO dao = new ShippingAddressDAO();

	private ShippingAddressMgr() {
	}

	public static ShippingAddressMgr getInstance() {
		if (mgr == null) {
			mgr = new ShippingAddressMgr();
		}
		return mgr;
	}
	
	public ShippingAddressVO getShippingAddressVOFromRs(ResultSet rs) {
		ShippingAddressVO vo = new ShippingAddressVO();
		this.initFromResultSet(vo, rs);
		return vo;
	}

	public void initFromResultSet(ShippingAddressVO vo, ResultSet rs) {
		try {
			
			vo.setPk_shipping_address(rs.getInt("pk_shipping_address"));
			vo.setPk_user(rs.getInt("pk_user"));
			vo.setReceiver(rs.getString("receiver"));
			vo.setTel(rs.getString("tel"));
			vo.setAddress(rs.getString("address"));
			vo.setPostcode(rs.getString("postcode"));
			vo.setPublishtime(rs.getTimestamp("publishtime"));
			vo.setModifier(rs.getString("modifier"));
			vo.setModifytime(rs.getTimestamp("modifytime"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int addShippingAddress(ShippingAddressVO vo){
		return dao.addByVO(vo);
	}
	
	public List<ShippingAddressVO> getOwnShippingAddresss(int id) {
		return dao.getVOsByUserId(id);
	}

	public int deleteShippingAddress(int id) {
		return dao.deleteById(id);
	}
}
