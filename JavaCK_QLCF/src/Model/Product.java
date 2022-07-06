package Model;

public class Product {
	private String maSP;
	private String tenSp;
	private int gia;
	public Product() {
		super();
		this.maSP = maSP;
		this.tenSp = tenSp;
		this.gia = gia;
	}
	
	public Product(String maSP, String tenSp, int gia) {
		super();
		this.maSP = maSP;
		this.tenSp = tenSp;
		this.gia = gia;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSp() {
		return tenSp;
	}

	public void setTenSp(String tenSp) {
		this.tenSp = tenSp;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}
	
	

}
