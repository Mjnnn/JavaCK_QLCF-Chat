package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DBConnection.DBConnection;
import Model.Product;
import Model.Table;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JSeparator;
import java.awt.event.*;
import java.sql.Date;
public class DetailBill<Image> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private List<Table> tablelist = new ArrayList<Table>();
	DefaultTableModel tableModel;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	private JTextField tf_ngay;
	private JTextField tf_thang;
	private JTextField tf_nam;
	private JTextField tf_mahd;
//	 String value;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetailBill dialog = new DetailBill();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetailBill() {
		setTitle("Hồ Sơ Thanh Toán");
		
		URL url = DetailBill.class.getResource("/Icon/File.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setBounds(360, 118, 910, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lb_timkiemtheongay = new JLabel("Tìm Kiếm Theo Ngày/Tháng/Năm:");
		lb_timkiemtheongay.setForeground(Color.RED);
		lb_timkiemtheongay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_timkiemtheongay.setBounds(25, 33, 312, 25);
		contentPanel.add(lb_timkiemtheongay);
		
		JButton bt_timkiem1 = new JButton("Tìm Kiếm");
		bt_timkiem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
                   Search();
                   showAll2();
			}
			
		});
		bt_timkiem1.setBackground(Color.GRAY);
		bt_timkiem1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_timkiem1.setBounds(201, 68, 113, 31);
		contentPanel.add(bt_timkiem1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_quaylai = new JButton("Quay Lại");
				bt_quaylai.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						DetailBill.this.setVisible(false);
					}
					
				});
				bt_quaylai.setBackground(Color.WHITE);
				bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_quaylai.setActionCommand("Cancel");
				buttonPane.add(bt_quaylai);
			}
		}
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Mã Hóa Đơn", "Mã Bàn", "Thời Gian", "Tổng Tiền"
				}
			));
			table.setFont(new Font("Times New Roman", Font.PLAIN, 18));
			
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(118, 142, 656, 191);
			contentPanel.add(scrollPane);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 552, 852, 2);
			contentPanel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(10, 495, 876, 2);
			contentPanel.add(separator_1);
			
			tf_ngay = new JTextField();
			tf_ngay.setBackground(Color.WHITE);
			tf_ngay.setFont(new Font("Tahoma", Font.PLAIN, 18));
			tf_ngay.setBounds(49, 68, 31, 31);
			contentPanel.add(tf_ngay);
			tf_ngay.setColumns(10);
			
			tf_thang = new JTextField();
			tf_thang.setBackground(Color.WHITE);
			tf_thang.setFont(new Font("Tahoma", Font.PLAIN, 18));
			tf_thang.setBounds(90, 68, 31, 31);
			contentPanel.add(tf_thang);
			tf_thang.setColumns(10);
			
			tf_nam = new JTextField();
			tf_nam.setBackground(Color.WHITE);
			tf_nam.setFont(new Font("Tahoma", Font.PLAIN, 18));
			tf_nam.setBounds(131, 68, 50, 31);
			contentPanel.add(tf_nam);
			tf_nam.setColumns(10);
			
			JLabel lb_mahd = new JLabel("Tìm Kiếm Theo Mã Hóa Đơn:");
			lb_mahd.setForeground(Color.RED);
			lb_mahd.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_mahd.setBounds(574, 33, 258, 25);
			contentPanel.add(lb_mahd);
			
			tf_mahd = new JTextField();
			tf_mahd.setFont(new Font("Tahoma", Font.PLAIN, 18));
			tf_mahd.setBounds(584, 68, 127, 28);
			contentPanel.add(tf_mahd);
			tf_mahd.setColumns(10);
			
			JButton bt_timkiem2 = new JButton("Tìm Kiếm");
			bt_timkiem2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					Search2();
					showAll2();
				}
				
			});
			bt_timkiem2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			bt_timkiem2.setBackground(Color.GRAY);
			bt_timkiem2.setBounds(730, 68, 113, 31);
			contentPanel.add(bt_timkiem2);
			
			JSeparator separator_2 = new JSeparator();
			separator_2.setBounds(10, 400, 876, 2);
			contentPanel.add(separator_2);
			
			JLabel lb_day = new JLabel("(DD)");
			lb_day.setForeground(Color.RED);
			lb_day.setFont(new Font("Tahoma", Font.ITALIC, 10));
			lb_day.setBounds(49, 109, 31, 13);
			contentPanel.add(lb_day);
			
			JLabel lb_month = new JLabel("(MM)");
			lb_month.setForeground(Color.RED);
			lb_month.setFont(new Font("Tahoma", Font.ITALIC, 10));
			lb_month.setBounds(90, 109, 31, 13);
			contentPanel.add(lb_month);
			
			JLabel lb_year = new JLabel("(YYYY)");
			lb_year.setForeground(Color.RED);
			lb_year.setFont(new Font("Tahoma", Font.ITALIC, 10));
			lb_year.setBounds(141, 109, 39, 13);
			contentPanel.add(lb_year);
			
			JButton bt_xoa = new JButton("Xóa");
			bt_xoa.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					xoaDon();
					
//					System.out.println(value);
					
				}
				
			});
			bt_xoa.setBackground(Color.GRAY);
			bt_xoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			bt_xoa.setBounds(730, 359, 113, 31);
			contentPanel.add(bt_xoa);
			
			JButton btnNewButton = new JButton("Menu");
			btnNewButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					loadData2();
					showAll2();
					
				}
				
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setBackground(Color.GRAY);
			btnNewButton.setBounds(595, 359, 85, 31);
			contentPanel.add(btnNewButton);
			tableModel = (DefaultTableModel) table.getModel();
			loadData2();
			showAll2();
			setVisible(true);
	}
	public void Search() {
		tablelist.clear();
		DBConnection cnn = new DBConnection();
 	    String sql = null; // = "SELECT * FROM Bill ";
		conn = cnn.connectSQL();
		if(tf_ngay.getText().length()==0 && tf_thang.getText().length()==0) {
			 sql="SELECT * FROM Bill where Year(ThoiGian)= '"+tf_nam.getText()+"'";
		}
		else if(tf_ngay.getText().length()==0 && tf_nam.getText().length()==0) {
			sql="SELECT * FROM Bill where MONTH(ThoiGian)= '"+tf_thang.getText()+"'";
		}
		else if(tf_thang.getText().length()==0 && tf_nam.getText().length()==0) {
			sql="SELECT * FROM Bill where DAY(ThoiGian)= '"+tf_ngay.getText()+"'";
		}
		else if(tf_ngay.getText().length()==0) {
			sql="SELECT * FROM Bill where YEAR(ThoiGian)= '"+tf_nam.getText()+"' AND MONTH(ThoiGian)= '"+tf_thang.getText()+"'";
		}
		else if(tf_thang.getText().length()==0) {
			sql="SELECT * FROM Bill where DAY(ThoiGian)= '"+tf_ngay.getText()+"' AND YEAR(ThoiGian)= '"+tf_nam.getText()+"'";
		}
		else if(tf_nam.getText().length()==0) {
			sql= "SELECT * FROM Bill where DAY(ThoiGian)= '"+tf_ngay.getText()+"' AND MONTH(ThoiGian)= '"+tf_thang.getText()+"'";
		}
		else if(tf_ngay.getText().length()!=0 && tf_thang.getText().length()!=0 && tf_nam.getText().length()!=0) {
			sql="SELECT * FROM Bill where DAY(ThoiGian)= '"+tf_ngay.getText()+"' AND MONTH(ThoiGian)= '"+tf_thang.getText()+" AND YEAR(ThoiGian)= '"+tf_nam.getText()+"'";
		}
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Table table = new Table(rs.getString("MaHD"), rs.getInt("MaBan"), rs.getString("ThoiGian"), rs.getInt("TongTien"));
					tablelist.add(table);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql=null;
	
	}
	public void loadData2() {
		tablelist.clear();
		DBConnection cnn  = new DBConnection();
		String sql = "SELECT * FROM Bill ";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
				Table table = new Table(rs.getString("MaHD"),rs.getInt("MaBan"),rs.getString("ThoiGian"), rs.getInt("TongTien"));
					tablelist.add(table);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showAll2() {
		tableModel.setRowCount(0);
		for (int i = 0; i < tablelist.size(); i++) {
			tableModel.addRow(new Object[] { tablelist.get(i).getMaHD(),tablelist.get(i).getMaBan(),tablelist.get(i).getDate3(),
					String.valueOf(tablelist.get(i).getTongTien())
       
			});

		}
	}
	
	public void Search2() {
		tablelist.clear();
		DBConnection cnn = new DBConnection();
		String sql = "SELECT * FROM Bill WHERE MaHD=?";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, tf_mahd.getText());
		    rs = ps.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					Table table = new Table(rs.getString("MaHD"),rs.getInt("MaBan"),rs.getString("ThoiGian"), rs.getInt("TongTien"));
					tablelist.add(table);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void xoaDon() {
		DBConnection cnn = new DBConnection();
		int row = table.getSelectedRow();
		String value = table.getModel().getValueAt(row, 0).toString();
		String sql ="Delete  from Bill where MaHD='"+value+"'";
		conn = cnn.connectSQL();
		int n = JOptionPane.showConfirmDialog(DetailBill.this, "Bạn Có Chắc Chắn Muốn Xóa Hóa Đơn Này ?", "Hóa Đơn", JOptionPane.YES_NO_OPTION);
		if (n==JOptionPane.YES_OPTION) {
		try {
			int record = cnn.executeDB("Delete  from Bill where MaHD='"+value+"'");
			if (record >0) {
				JOptionPane.showMessageDialog(DetailBill.this, "Xóa Thành Công ");
				xoaHang();
			}else {
				JOptionPane.showMessageDialog(DetailBill.this, "Xóa Không Thành Công ");
	           }
			}catch (Exception e3) {
		// TODO: handle exception
		  e3.printStackTrace();
	   }
	}
   }
	public void xoaHang() {
		tableModel.removeRow(table.getSelectedRow());
	}
}
