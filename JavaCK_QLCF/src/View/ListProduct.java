package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import DBConnection.DBConnection;
import View.Home;
import java.awt.event.*;

import Model.Product;

import javax.swing.JSeparator;

public class ListProduct<Image> extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField tf_timkiem;
	private JTable table;
	private JTextField tf_masp;
	private JTextField tf_tensp;
	private JTextField tf_giatien;
	private List<Product> productlist = new ArrayList<Product>();
	DefaultTableModel tableModel;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
    String value;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListProduct dialog = new ListProduct();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListProduct() {
		setTitle("Danh Sách Đồ Uống");
	
		URL url = ListProduct.class.getResource("/Icon/listproduct.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setBounds(430, 155, 804, 465);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			tf_timkiem = new JTextField();
			tf_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
			tf_timkiem.setBounds(58, 26, 175, 32);
			contentPanel.add(tf_timkiem);
			tf_timkiem.setColumns(10);
		}
		
		JButton bt_timkiem = new JButton("Tìm Kiếm");
		bt_timkiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Search();
				showAll();
				
			}
			
		});
		bt_timkiem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(ListProduct.class.getResource("/Icon/search1.png"))));
		bt_timkiem.setBackground(Color.GRAY);
		bt_timkiem.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_timkiem.setBounds(267, 25, 142, 32);
		contentPanel.add(bt_timkiem);
		
		JLabel lb_danhsach = new JLabel("Danh Sách Đồ Uống");
		lb_danhsach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_danhsach.setBounds(122, 87, 190, 32);
		contentPanel.add(lb_danhsach);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DBConnection cnn = new DBConnection();
				String sql = "SELECT * from Product where MaSP= N'"+maSP()+"'";
				conn = cnn.connectSQL();
				try {
					ps = conn.prepareStatement(sql);
					ResultSet rs = ps.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							 tf_masp.setText(rs.getString("MaSP"));
							 tf_tensp.setText(rs.getString("TenSp"));
							 tf_giatien.setText(rs.getInt("Gia")+"");
						}
					}
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
			}
			
		});
		
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã Sản Phẩm", "Tên Sản Phẩm", "Giá Tiền"
			}
		));
		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(42, 129, 367, 236);
		contentPanel.add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("Thông Tin Đồ Uống");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(528, 90, 216, 27);
		contentPanel.add(lblNewLabel);
		
		JLabel lb_masp = new JLabel("Mã Sản Phẩm:");
		lb_masp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_masp.setBounds(440, 140, 133, 22);
		contentPanel.add(lb_masp);
		
		tf_masp = new JTextField();
		tf_masp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tf_masp.setBounds(583, 140, 182, 27);
		contentPanel.add(tf_masp);
		tf_masp.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Sản Phẩm:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(440, 187, 133, 22);
		contentPanel.add(lblNewLabel_1);
		
		tf_tensp = new JTextField();
		tf_tensp.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		tf_tensp.setBounds(583, 187, 182, 27);
		contentPanel.add(tf_tensp);
		tf_tensp.setColumns(10);
		
		JLabel lb_giatien = new JLabel("Giá Tiền:");
		lb_giatien.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_giatien.setBounds(445, 234, 91, 22);
		contentPanel.add(lb_giatien);
		
		tf_giatien = new JTextField();
		tf_giatien.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tf_giatien.setBounds(583, 235, 182, 26);
		contentPanel.add(tf_giatien);
		tf_giatien.setColumns(10);
		
		JButton bt_them = new JButton("Thêm");
		bt_them.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBConnection cnn = new DBConnection();
				try {
					int record = cnn.executeDB(
							"INSERT INTO Product VALUES (N'" + tf_masp.getText() + "',N'" + tf_tensp.getText() + "' ,'" + tf_giatien.getText() + "')");
					if (record > 0) {
						JOptionPane.showMessageDialog(null, "Thêm Thành Công");
						loadData();
						showAll();
					} else
						JOptionPane.showMessageDialog(null, "Thất Bại, Vui Lòng Xem Lại");

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
			
		});
		bt_them.setBackground(Color.GRAY);
		bt_them.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_them.setBounds(440, 281, 85, 32);
		contentPanel.add(bt_them);
		
		JButton bt_xoa = new JButton("Xóa");
//		bt_xoa.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				xoaHang();
//				xoaDon();
//			}
//		});
		bt_xoa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBConnection cnn = new DBConnection();
				String sql = "DELETE FROM Product WHERE MaSP=?";
				conn = cnn.connectSQL();
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, tf_masp.getText());
					int record = ps.executeUpdate();
					if (record > 0) {
						loadData();
						showAll();
						JOptionPane.showMessageDialog(null, "Xóa Thành Công");
					}

					else
						JOptionPane.showMessageDialog(null, "Xóa Thất Bại, Vui Lòng Xem Lại");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
							
		});
		bt_xoa.setBackground(Color.GRAY);
		bt_xoa.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_xoa.setBounds(564, 281, 85, 32);
		contentPanel.add(bt_xoa);
		
		JButton bt_capnhat = new JButton("Cập Nhật");
		bt_capnhat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DBConnection cnn  = new DBConnection();
				String sql = "UPDATE Product SET Gia =?  WHERE MaSP=?";
				conn = cnn.connectSQL();
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, tf_giatien.getText());
					ps.setString(2, tf_masp.getText());
					int record = ps.executeUpdate();
					if (record > 0) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Thành Công");
						loadData();
						showAll();
					} else
						JOptionPane.showMessageDialog(null, "Cập Nhật Thất Bại, Vui Lòng Thử Lại");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		bt_capnhat.setBackground(Color.GRAY);
		bt_capnhat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_capnhat.setBounds(674, 281, 106, 32);
		contentPanel.add(bt_capnhat);
		
		JButton bt_reset = new JButton("Menu");
		bt_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadData();
				showAll();
				tf_timkiem.setText("");
			}
			
		});
		bt_reset.setBackground(Color.GRAY);
		bt_reset.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bt_reset.setBounds(633, 333, 85, 32);
		contentPanel.add(bt_reset);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 373, 754, 2);
		contentPanel.add(separator);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xoatt();
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(510, 333, 85, 32);
		contentPanel.add(btnNewButton);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_quaylai = new JButton("Quay L\u1EA1i");
				bt_quaylai.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ListProduct.this.setVisible(false);
					}
					
				});
				bt_quaylai.setBackground(Color.WHITE);
				bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 20));
				bt_quaylai.setActionCommand("Cancel");
				buttonPane.add(bt_quaylai);
				
				JSeparator separator_1 = new JSeparator();
				buttonPane.add(separator_1);
			}
		}
		tableModel = (DefaultTableModel) table.getModel();
		loadData();
		showAll();
		setVisible(true);
	}
	
	public void Search() {
		productlist.clear();
		DBConnection cnn = new DBConnection();
		String sql = "SELECT * FROM PRODUCT WHERE MaSP='"+tf_timkiem.getText()+"' OR TenSp=N'"+tf_timkiem.getText()+"'" ;
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
//			ps.setString(1, tf_timkiem.getText());
			ResultSet rs = ps.executeQuery();
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
			ResultSet rs = ps.executeQuery();
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
	public void xoaHang() {
		tableModel.removeRow(table.getSelectedRow());
	}
	public String maSP() {
		int column = 0;
		int row = table.getSelectedRow();
		value = table.getModel().getValueAt(row, column).toString();
		return value;
	}
	public void xoaDon() {
		DBConnection cnn = new DBConnection();
		String sql ="Delete  from Product where MaSP=N'"+maSP()+"'";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				JOptionPane.showMessageDialog(ListProduct.this, "Xóa Thành Công ");
			}else {
				JOptionPane.showMessageDialog(ListProduct.this, "Xóa Không Thành Công ");
			}
	         }catch (Exception e) {
				// TODO: handle exception
	        	 e.printStackTrace(); 
			}
   }
	public void xoatt() {
		tf_giatien.setText("");
		tf_tensp.setText("");
		tf_masp.setText("");
	}
}
