package bo;

import java.util.ArrayList;

import bean.chitiethoadonbean;
import dao.chitiethoadondao;

public class chitiethoadonbo {
	chitiethoadondao cthddao=new chitiethoadondao();
	public ArrayList<chitiethoadonbean> getchitiethoadon(Long mahd) throws Exception{
		return cthddao.getchitiethoadon(mahd);
	}
	public int addchitiethd(String masach, int soluongmua, Long mahd) throws Exception{
		return cthddao.addchitiethd(masach, soluongmua, mahd);
	}
}
