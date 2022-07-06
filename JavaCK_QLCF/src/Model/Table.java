package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.management.loading.PrivateClassLoader;

public class Table {
     private String maHD;
     private int maBan;
     DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
     private String maSp;
     public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public String getDate3() {
		return date3;
	}
	public void setDate3(String date3) {
		this.date3 = date3;
	}
	private String tenSp;
     private int soLuong;
     private int tongTien;
     private Date date2;
     private String date3;
	public Table(String maHD, int maBan, DateFormat date, String maSp, int soLuong, int tongTien) {
		super();
		this.maHD = maHD;
		this.maBan = maBan;
		this.date = date;
		this.maSp = maSp;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}
	public Table( int maBan, String maSp, String tenSp,  int soLuong, int tongTien) {
		super();
		this.maBan = maBan;
		this.maSp = maSp;
		this.tenSp = tenSp;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}
	public Table(String maHD,int maBan, DateFormat date, int tongTien) {
		this.maHD = maHD;
		this.maBan = maBan;
		this.date = date;
		this.tongTien = tongTien;
	}
	public Table(String maHD,int maBan, Date date2, int tongTien) {
		this.maHD = maHD;
		this.maBan = maBan;
		this.date2 = date2;
		this.tongTien = tongTien;
	}
	public Table(String maHD,int maBan, String date3, int tongTien) {
		this.maHD = maHD;
		this.maBan = maBan;
		this.date3 = date3;
		this.tongTien = tongTien;
	}
	
	public Table() {
		super();
		this.maHD = "";
		this.maBan = 0;
		this.date = null;
		this.maSp = "";
		this.soLuong = 0;
		this.tongTien = 0;
	}
	public Table(String maSP, int tongTien, int soLuong) {
		// TODO Auto-generated constructor stub
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public int getMaBan() {
		return maBan;
	}

	public void setMaBan(int maBan) {
		this.maBan = maBan;
	}

	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	public String getMaSp() {
		return maSp;
	}

	public void setMaSp(String maSp) {
		this.maSp = maSp;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	public String getTenSp() {
		return tenSp;
	}
	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}
     
     
	
}
