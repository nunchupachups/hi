package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.chitiethoadonbean;
import bean.khachhangbean;

public class chitiethoadondao {
	public ArrayList<chitiethoadonbean> getchitiethoadon(Long mahd) throws Exception{
		ArrayList<chitiethoadonbean> ds=new ArrayList<chitiethoadonbean>();
		DungChung dc=new DungChung();
		dc.KetNoi();
		String sql="select * from ChiTietHoaDon where MaHoaDon=?";
		PreparedStatement cmd=dc.cn.prepareStatement(sql);
		cmd.setLong(1, mahd);
		ResultSet rs=cmd.executeQuery();
		while (rs.next()) {
			Long machitiethd=rs.getLong(1);
			String masach=rs.getNString(2);
			int soluongmua=rs.getInt(3);
			ds.add(new chitiethoadonbean(machitiethd, masach, soluongmua, mahd));
		}
		rs.close();
		dc.cn.close();
		return ds;
	}
	public int addchitiethd(String masach, int soluongmua, Long mahd) throws Exception{
		DungChung dc= new DungChung();
		dc.KetNoi();
		String sql="insert into ChiTietHoaDon (MaSach, SoLuongMua, MaHoaDon) values (?,?,?)";
		PreparedStatement cmd=dc.cn.prepareStatement(sql);
		cmd.setString(1, masach);
		cmd.setInt(2, soluongmua);
		cmd.setLong(3, mahd);
		int kq=cmd.executeUpdate();
		dc.cn.close();
		return kq;
	}
}
