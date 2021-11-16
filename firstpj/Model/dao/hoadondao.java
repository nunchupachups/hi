package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.hoadonbean;

public class hoadondao {
	public ArrayList<hoadonbean> gethoadon(Long makh) throws Exception{
		ArrayList<hoadonbean> ds= new ArrayList<hoadonbean>();
		DungChung dc = new DungChung();
		dc.KetNoi();
		String sql="select * from hoadon where makh=? and damua='false'";
		PreparedStatement cmd= dc.cn.prepareStatement(sql);
		cmd.setLong(1, makh);
		ResultSet rs=cmd.executeQuery();
		while (rs.next()) {
			Long mahoadon=rs.getLong(1);
			Date ngaymua=rs.getDate(3);
			ds.add(new hoadonbean(mahoadon, makh, ngaymua, false));
		}
		rs.close();
		dc.cn.close();
		return ds;
	}
	public ArrayList<hoadonbean> getlshoadon(Long makh) throws Exception{
		ArrayList<hoadonbean> ds= new ArrayList<hoadonbean>();
		DungChung dc = new DungChung();
		dc.KetNoi();
		String sql="select * from hoadon where makh=? and damua='true'";
		PreparedStatement cmd= dc.cn.prepareStatement(sql);
		cmd.setLong(1, makh);
		ResultSet rs=cmd.executeQuery();
		while (rs.next()) {
			Long mahoadon=rs.getLong(1);
			Date ngaymua=rs.getDate(3);
			ds.add(new hoadonbean(mahoadon, makh, ngaymua, true));
		}
		rs.close();
		dc.cn.close();
		return ds;
	}
	public ArrayList<hoadonbean> getdshoadon() throws Exception{
		ArrayList<hoadonbean> ds= new ArrayList<hoadonbean>();
		DungChung dc = new DungChung();
		dc.KetNoi();
		String sql="select * from hoadon where damua='false'";
		PreparedStatement cmd= dc.cn.prepareStatement(sql);
		ResultSet rs=cmd.executeQuery();
		while (rs.next()) {
			Long mahoadon=rs.getLong(1);
			Long makh =rs.getLong(2);
			Date ngaymua=rs.getDate(3);
			ds.add(new hoadonbean(mahoadon, makh, ngaymua, false));
		}
		rs.close();
		dc.cn.close();
		return ds;
	}
	public ArrayList<hoadonbean> getdslshoadon() throws Exception{
		ArrayList<hoadonbean> ds= new ArrayList<hoadonbean>();
		DungChung dc = new DungChung();
		dc.KetNoi();
		String sql="select * from hoadon where damua='true'";
		PreparedStatement cmd= dc.cn.prepareStatement(sql);
		ResultSet rs=cmd.executeQuery();
		while (rs.next()) {
			Long mahoadon=rs.getLong(1);
			Long makh =rs.getLong(2);
			Date ngaymua=rs.getDate(3);
			ds.add(new hoadonbean(mahoadon, makh, ngaymua, true));
		}
		rs.close();
		dc.cn.close();
		return ds;
	}
	public Long addhoadon(Long makh, Date ngaymua) throws Exception{
		DungChung dc=new DungChung();
		dc.KetNoi();
		String sql="insert into hoadon (makh, NgayMua, damua) values (?,?,?)";
		PreparedStatement cmd=dc.cn.prepareStatement(sql);
		cmd.setLong(1, makh);
		cmd.setDate(2, ngaymua);
		cmd.setBoolean(3, false);
		int kq=cmd.executeUpdate();
		String sql1="select MaHoaDon from hoadon order by MaHoaDon desc";
		PreparedStatement cmd1=dc.cn.prepareStatement(sql1);
		ResultSet rs=cmd1.executeQuery();
		rs.next();
		Long mahd=rs.getLong("MaHoaDon");
		rs.close();
		dc.cn.close();
		return mahd;
	}
	public hoadonbean timhoadon(long mahd) throws Exception{
		hoadonbean hd= new hoadonbean();
		DungChung dc = new DungChung();
		dc.KetNoi();
		String sql="select * from hoadon where MaHoaDon=?";
		PreparedStatement cmd= dc.cn.prepareStatement(sql);
		cmd.setLong(1, mahd);
		ResultSet rs=cmd.executeQuery();
		if (rs.next()) {
			Long makh=rs.getLong(2);
			Date ngaymua=rs.getDate(3);
			boolean damua=rs.getBoolean(4);
			hd.setMakhachhang(makh);
			hd.setMahoadon(mahd);
			hd.setNgaymua(ngaymua);
			hd.setDamua(damua);
		}
		rs.close();
		dc.cn.close();
		return hd;
	}
	public Long tongtien(long mahd) throws Exception{
		Long tong=(long)0;
		DungChung dc = new DungChung();
		dc.KetNoi();
		String sql="select c.MaHoaDon, sum(gia*SoLuongMua) as tong\r\n"
				+ "from \r\n"
				+ "(select hd.MaHoaDon,MaSach, SoLuongMua\r\n"
				+ "from hoadon as hd inner join ChiTietHoaDon as ct on hd.MaHoaDon=ct.MaHoaDon \r\n"
				+ "where hd.MaHoaDon=?\r\n"
				+ ") as c inner join sach on sach.masach=c.MaSach \r\n"
				+ "group by c.MaHoaDon";
		PreparedStatement cmd= dc.cn.prepareStatement(sql);
		cmd.setLong(1, mahd);
		ResultSet rs=cmd.executeQuery();
		if (rs.next()) {
			tong=rs.getLong("tong");
		}
		rs.close();
		dc.cn.close();
		return tong;
	}
	
	
}
