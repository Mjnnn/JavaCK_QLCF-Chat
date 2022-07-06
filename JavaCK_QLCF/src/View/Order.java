package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import DBConnection.DBConnection;
import Model.Product;
import Model.Table;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.*;
import javax.swing.JSpinner;

public class Order<Image> extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tf_timkiem;
	private JTextField tf_masp;
	private JTextField tf_tensanpham;
	private JTable table_1;
	private JSpinner spinner;
	private JComboBox cbb_chonban;
	private List<Product> productlist = new ArrayList<Product>();
	private List<Table> tablelist = new ArrayList<Table>();
	DefaultTableModel tableModel;
	DefaultTableModel tableModel2;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	String value;
	String valuee;
	int abc=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order frame = new Order();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Order() {
		setTitle("Đặt Bàn");
		
		URL url = Order.class.getResource("/Icon/order24.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(320, 30, 970, 787);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(26, 155, 505, 313);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lb_chonban = new JLabel("Chọn Bàn:");
		lb_chonban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_chonban.setBounds(258, 172, 122, 22);
		panel.add(lb_chonban);
		
	    cbb_chonban = new JComboBox();
		cbb_chonban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbb_chonban.setModel(new DefaultComboBoxModel(new String[] {"","1", "2","3","4","5","6"}));
		cbb_chonban.setBounds(346, 172, 59, 25);
		panel.add(cbb_chonban);
		
		JLabel lb_masp = new JLabel("Mã Sản Phẩm:");
		lb_masp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_masp.setBounds(61, 52, 122, 32);
		panel.add(lb_masp);
		
		tf_masp = new JTextField();
		tf_masp.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				DBConnection cnn = new DBConnection();
				String sql = "SELECT TenSp from Product where MaSP= N'"+tf_masp.getText()+"'";
				conn = cnn.connectSQL();
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							 tf_tensanpham.setText(rs.getString("TenSp"));
						}
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		tf_masp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_masp.setBounds(211, 52, 194, 32);
		panel.add(tf_masp);
		tf_masp.setColumns(10);
		
		JLabel lb_tensp = new JLabel("Tên Sản Phẩm:");
		lb_tensp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_tensp.setBounds(62, 114, 144, 22);
		panel.add(lb_tensp);
		
		tf_tensanpham = new JTextField();
		tf_tensanpham.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				DBConnection cnn = new DBConnection();
				String sql = "SELECT MaSP from Product where TenSp= N'"+tf_tensanpham.getText()+"'";
				conn = cnn.connectSQL();
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							 tf_masp.setText(rs.getString("MaSP"));
						}
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
		});
		tf_tensanpham.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_tensanpham.setBounds(211, 109, 194, 32);
		panel.add(tf_tensanpham);
		tf_tensanpham.setColumns(10);
		
		JLabel lb_soluong = new JLabel("Số Lượng:");
		lb_soluong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_soluong.setBounds(62, 173, 100, 21);
		panel.add(lb_soluong);
		
		    spinner = new JSpinner();
			spinner.setModel(new SpinnerListModel(new String[] {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
			spinner.setFont(new Font("Tahoma", Font.PLAIN, 18));
			spinner.setBounds(172, 168, 59, 30);
			panel.add(spinner);
			
		
		JButton bt_dat = new JButton("Đặt");
		bt_dat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection cnn = new DBConnection();	
				int a=Integer.parseInt((String) spinner.getValue ());
				String s =tf_tensanpham.getText();
				String c= tf_masp.getText();
				String b=(String) cbb_chonban.getItemAt(cbb_chonban.getSelectedIndex());
				if(c.equals("")== true || s.equals("")== true || b.equals("") ||  a==0 ) {
					JOptionPane.showMessageDialog(Order.this, "Nhập Thiếu Thông Tin");
				}else {
				
					try {    
						int record = cnn.executeDB("INSERT INTO DetailBill VALUES('"+Integer.parseInt((String) cbb_chonban.getItemAt(cbb_chonban.getSelectedIndex()))+"',N'"+tf_masp.getText()+"',N'"+tf_tensanpham.getText()+"','"+Integer.parseInt((String) spinner.getValue ())+"','"+thanhtien()+"')");
						if(record>0) {
							JOptionPane.showMessageDialog(Order.this,"Đặt Thành Công");
						}else {
							JOptionPane.showMessageDialog(Order.this,"Đặt Không Thành Công");
						}
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
				loadData2(Integer.parseInt((String) cbb_chonban.getItemAt(cbb_chonban.getSelectedIndex())));
				showAll2();
			}
				
			
		});
		bt_dat.setBackground(Color.GRAY);
		bt_dat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_dat.setBounds(361, 243, 100, 32);
		panel.add(bt_dat);
		
		JButton bt_datlai = new JButton("Đặt Lại");
		bt_datlai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				datlai();
			}
			
		});
		bt_datlai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_datlai.setBackground(Color.GRAY);
		bt_datlai.setBounds(229, 243, 100, 32);
		panel.add(bt_datlai);
		
	   
		JLabel lb_datban = new JLabel("Quản Lí Đặt Bàn");
		lb_datban.setForeground(Color.RED);
		lb_datban.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lb_datban.setBounds(325, 26, 366, 57);
		contentPane.add(lb_datban);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 95, 936, 2);
		contentPane.add(separator);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Tiền"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(562, 155, 366, 260);
		contentPane.add(scrollPane);
		
		tf_timkiem = new JTextField();
		tf_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tf_timkiem.setBounds(562, 441, 156, 27);
		contentPane.add(tf_timkiem);
		tf_timkiem.setColumns(10);
		
		JLabel lb_menu = new JLabel("Menu");
		lb_menu.setForeground(Color.RED);
		lb_menu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_menu.setBounds(713, 118, 75, 27);
		contentPane.add(lb_menu);
		
		JButton bt_timkiem = new JButton("Tìm Kiếm");
		bt_timkiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Search();
				showAll();
			}
			
		});
		bt_timkiem.setBackground(Color.GRAY);
		bt_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_timkiem.setBounds(728, 441, 118, 27);
		contentPane.add(bt_timkiem);
		
		JButton bt_quaylai = new JButton("Quay Lại");
		bt_quaylai.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Order.this.setVisible(false);
				
			}
			
		});
		bt_quaylai.setBackground(Color.WHITE);
		bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_quaylai.setBounds(810, 709, 118, 31);
		contentPane.add(bt_quaylai);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(26, 696, 908, 2);
		contentPane.add(separator_1);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"Mã Bàn","Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền"
			}
		));
		
		
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		scrollPane_1.setBounds(124, 490, 716, 157);
		contentPane.add(scrollPane_1);
		
		JButton bt_menu = new JButton("Menu");
		bt_menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadData();
				showAll();
				tf_timkiem.setText("");
			}
			
		});
		bt_menu.setBackground(Color.GRAY);
		bt_menu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bt_menu.setBounds(856, 441, 75, 26);
		contentPane.add(bt_menu);
		
		JButton bt_Xoa = new JButton("Xóa");
		bt_Xoa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					xoaDon();
					xoaHang();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(Order.this,"Vui Lòng Chọn Hàng Cần Xóa");
				}
			}
		});
		bt_Xoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		bt_Xoa.setBackground(Color.GRAY);
		bt_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_Xoa.setBounds(810, 657, 118, 29);
		contentPane.add(bt_Xoa);
		tableModel = (DefaultTableModel) table.getModel();
		tableModel2 = (DefaultTableModel) table_1.getModel();
		loadData();
		showAll();
		setVisible(true);
	}
	private void datlai() {
		tf_masp.setText("");
		tf_tensanpham.setText("");
		cbb_chonban.setSelectedIndex(0);
//		spinner.setValue(0); 
		spinner.setModel(new SpinnerListModel(new String[] {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
	}
	public void Search() {
		productlist.clear();
		DBConnection cnn = new DBConnection();
		String sql = "SELECT * FROM PRODUCT WHERE MaSP='"+tf_timkiem.getText()+"' OR TenSp=N'"+tf_timkiem.getText()+"'" ;
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
//			ps.setString(1, tf_timkiem.getText());
		    rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Product product = new Product(rs.getString("MaSP"),rs.getString("TenSp"),rs.getInt("Gia"));
					productlist.add(product);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void loadData() {
		productlist.clear();
		DBConnection cnn  = new DBConnection();
		String sql = "SELECT * FROM Product";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			 rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Product product = new  Product(rs.getString("MaSP"),rs.getString("TenSp"),rs.getInt("Gia"));
					productlist.add(product);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showAll() {
		tableModel.setRowCount(0);
		for (int i = 0; i < productlist.size(); i++) {
			tableModel.addRow(new Object[] { productlist.get(i).getMaSP(),productlist.get(i).getTenSp(),
					String.valueOf(productlist.get(i).getGia())
       
			});

		}
	}
	private int thanhtien() {
		DBConnection cnn = new DBConnection();
		String sql = "select * from product where MaSP=?";
		int c=1;
		int b=1;
		int a=1;
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tf_masp.getText());
			 rs = ps.executeQuery();
			if(rs!=null) {
				while(rs.next()) {
//				a= rs.getInt("Gia");
			    b = Integer.parseInt((String) spinner.getValue ());
				c =b*rs.getInt("Gia");
				}
			}else {
				JOptionPane.showMessageDialog(Order.this,"Không Tìm Thấy Mã Sản Phẩm");
			}
			
		
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		return c;
	}
	public void loadData2(int c) {
		tablelist.clear();
		DBConnection cnn  = new DBConnection();
		String sql = "SELECT * FROM DetailBill where MaBan='"+c+"' ";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
				Table table = new Table(rs.getInt("MaBan"),rs.getString("MaSP"),rs.getString("TenSp"),rs.getInt("SoLuong"),rs.getInt("ThanhTien"));
					tablelist.add(table);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showAll2() {
		tableModel2.setRowCount(0);
		for (int i = 0; i < tablelist.size(); i++) {
			tableModel2.addRow(new Object[] { tablelist.get(i).getMaBan(),tablelist.get(i).getMaSp(),tablelist.get(i).getTenSp(),
					String.valueOf(tablelist.get(i).getSoLuong()),	String.valueOf(tablelist.get(i).getTongTien())
       
			});

		}
	}
	public void xoaHang() {
		tableModel2.removeRow(table_1.getSelectedRow());
	}
	public String maSP() {
		int column = 1;
		int row = table_1.getSelectedRow();
		value = table_1.getModel().getValueAt(row, column).toString();
//		System.out.println(value);
		return value;
	}
	public int maBan() {
		int column = 0;
		int row = table_1.getSelectedRow();
		valuee = table_1.getModel().getValueAt(row, column).toString();
		abc = Integer.parseInt((String) valuee);
		return abc;
	}
	public void xoaDon() {
		DBConnection cnn = new DBConnection();
		String sql ="Delete  from DetailBill where MaSP='"+maSP()+"' AND MaBan='"+maBan()+"'";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				JOptionPane.showMessageDialog(Order.this, "Xóa Thành Công ");
			}else {
				JOptionPane.showMessageDialog(Order.this, "Xóa Không Thành Công ");
			}
	         }catch (Exception e) {
				// TODO: handle exception
	        	 e.printStackTrace(); 
			}
   }
}
