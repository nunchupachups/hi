package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.khachhangbean;
import bean.sachbean;

public class sachdao {
	public ArrayList<sachbean> getsach() throws Exception{
		ArrayList<sachbean> ds=new ArrayList<sachbean>();
		DungChung dc=new DungChung();
		dc.KetNoi();
		String sql="select * from sach";
		PreparedStatement cmd=dc.cn.prepareStatement(sql);
		ResultSet rs=cmd.executeQuery();
		while(rs.next()) {
			String masach=rs.getString("masach"); 
			String tensach=rs.getString("tensach");
			String tacgia=rs.getString("tacgia"); 
			Long soluong=rs.getLong("soluong");
			Long gia=rs.getLong("gia");
			String anh=rs.getString("anh");
			String maloai=rs.getString("maloai");
			ds.add(new sachbean(masach, tensach, tacgia, soluong, gia, anh, maloai));
		}
		//b6:Dong
		rs.close();
		dc.cn.close();
		return ds;
	}

	
}
