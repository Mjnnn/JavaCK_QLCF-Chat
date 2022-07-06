package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import DBConnection.DBConnection;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.*;


public class Bill<Image> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_mahd;
	static int c=0;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	String a="0";
	private JLabel lb_thanhtien;
	private JComboBox comboBox;
	private JTextField tf_thoigian;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Bill dialog = new Bill(c);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Bill(int c) {
		setTitle("Thanh Toán");
		
		URL url = Bill.class.getResource("/Icon/Bill24.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setBounds(340, 120, 938, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Thanh Toán Bàn "+c);
			lblNewLabel.setBackground(Color.WHITE);
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
			lblNewLabel.setBounds(305, 10, 369, 42);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lb_mahd = new JLabel("Mã Hóa Đơn:");
			lb_mahd.setBackground(Color.WHITE);
			lb_mahd.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_mahd.setBounds(305, 135, 128, 31);
			contentPanel.add(lb_mahd);
		}
		{
			tf_mahd = new JTextField();
			tf_mahd.setBackground(Color.WHITE);
			tf_mahd.setFont(new Font("Tahoma", Font.PLAIN, 18));
			tf_mahd.setBounds(449, 136, 180, 31);
			contentPanel.add(tf_mahd);
			tf_mahd.setColumns(10);
		}
		{
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 77, 904, 16);
			contentPanel.add(separator);
		}
		{
			JLabel lb_thoigian = new JLabel("Thời Gian:");
			lb_thoigian.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_thoigian.setBackground(Color.WHITE);
			lb_thoigian.setBounds(305, 193, 106, 31);
			contentPanel.add(lb_thoigian);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_quaylai = new JButton("Quay Lại");
				bt_quaylai.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Bill.this.setVisible(false);
						
					}
					
				});
				bt_quaylai.setBackground(Color.WHITE);
				bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_quaylai.setActionCommand("OK");
				buttonPane.add(bt_quaylai);
				getRootPane().setDefaultButton(bt_quaylai);
			}
			{
				JButton bt_thanhtoan = new JButton("Thanh Toán");
				bt_thanhtoan.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String hoadon = tf_mahd.getText();
						String thoigian= tf_thoigian.getText();
						if(hoadon.equals("")==false && thoigian.equals("")==false) {
							int d=0;
							// TODO Auto-generated method stub
							int abc= (int) comboBox.getItemAt(comboBox.getSelectedIndex());
							DBConnection cnn = new DBConnection();
							
							String sql = "SELECT  Sum(ThanhTien) as[Tongtien] FROM DetailBill WHERE MaBan='"+c+"'";
							conn = cnn.connectSQL();
							try {
								ps = conn.prepareStatement(sql);
								rs = ps.executeQuery();
								
								if(rs != null) {
									while(rs.next()) {
									a=rs.getString("Tongtien");
									 d = Integer.parseInt((String) a);
									if(abc!=0) {
										d= d-(d*abc)/100;
									  }
									}
								}else {
									JOptionPane.showMessageDialog(Bill.this,"Bàn Này Chưa Được Đặt");
								}
							} catch (Exception e2) {
								// TODO: handle exception
							}
							try {
								int record = cnn.executeDB(
										"INSERT INTO Bill VALUES (N'" + tf_mahd.getText() + "','"+c+"','" +tf_thoigian.getText() + "' ,'" +d+ "')");
								if(record >0 ) {
									
									String sql2 = "DELETE FROM DetailBill  WHERE MaBan='"+c+"'";
									conn = cnn.connectSQL();
									try {
										ps = conn.prepareStatement(sql2);
										int record2 = ps.executeUpdate();
										if(record2>0) {
											JOptionPane.showMessageDialog(Bill.this,"Thanh Toán Thành Công");
											lb_thanhtien.setText(d+"d");
										}
									} catch (Exception e2) {
										// TODO: handle exception
										e2.printStackTrace();
									}
								}else {
									JOptionPane.showMessageDialog(Bill.this,"Thanh Toán Không Thành Công");
								}
							} catch (Exception e2) {
								// TODO: handle exception
								e2.printStackTrace();
							}
					}else {
						JOptionPane.showMessageDialog(Bill.this, "Thiếu Thông Tin");
					  }
					}
				});
				bt_thanhtoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_thanhtoan.setBackground(Color.WHITE);
				bt_thanhtoan.setActionCommand("Cancel");
				buttonPane.add(bt_thanhtoan);
			}
		}
			{
				JLabel lb_giamgia = new JLabel("Giảm Giá:");
				lb_giamgia.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lb_giamgia.setBackground(Color.WHITE);
				lb_giamgia.setBounds(305, 271, 118, 25);
				contentPanel.add(lb_giamgia);
			}
			String[] sale = {"No","10","25","50"};
		    comboBox = new JComboBox();
		    comboBox.addItem(0);
		    comboBox.addItem(10);
		    comboBox.addItem(25);
		    comboBox.addItem(50);
			comboBox.setBackground(Color.WHITE);
			comboBox.setBounds(503, 269, 67, 27);
			contentPanel.add(comboBox);
			
			JLabel lb_tongtien = new JLabel("Tổng Tiền: ");
			lb_tongtien.setBackground(Color.WHITE);
			lb_tongtien.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lb_tongtien.setBounds(305, 317, 118, 25);
			contentPanel.add(lb_tongtien);
			
		    lb_thanhtien = new JLabel(a+"d");
			lb_thanhtien.setFont(new Font("Tahoma", Font.BOLD, 20));
			lb_thanhtien.setBounds(449, 315, 180, 31);
			contentPanel.add(lb_thanhtien);
			
			JSeparator separator = new JSeparator();
			separator.setBounds(10, 379, 904, 2);
			contentPanel.add(separator);
			
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(10, 367, 904, 2);
			contentPanel.add(separator_1);
			{
				tf_thoigian = new JTextField();
				tf_thoigian.setFont(new Font("Tahoma", Font.PLAIN, 18));
				tf_thoigian.setBounds(449, 198, 180, 29);
				contentPanel.add(tf_thoigian);
				tf_thoigian.setColumns(10);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("(YYYY/MM/DD)");
				lblNewLabel_1.setForeground(Color.RED);
				lblNewLabel_1.setBackground(Color.WHITE);
				lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 10));
				lblNewLabel_1.setBounds(498, 237, 87, 13);
				contentPanel.add(lblNewLabel_1);
			}
		setVisible(true);
	}
}
