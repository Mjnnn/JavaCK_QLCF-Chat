package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.event.*;
import com.toedter.calendar.JDateChooser;

import DBConnection.DBConnection;
import View.Login;
import Model.Account;

public class SignUp extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tf_dangkitk;
	private JPasswordField tf_dkimk;
	private JTextField tf_hovaten;
	private ButtonGroup gioitinh;
	private JTextField tf_diachi;
	private Connection conn;
	PreparedStatement ps;
	ResultSet rs;
	private JPasswordField tf_nhaplaimk;
	private JDateChooser dtCBTD;
	private ButtonGroup bt_group;
	private ArrayList<Account> account = new ArrayList<Account>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SignUp dialog = new SignUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param <Image>
	 */
	public <Image> SignUp() {
		setTitle("Đăng Kí");

//		try {
//	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//	         conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=JavaCK;"
//	                + "username=sa;password=sa");
//	     } catch (Exception e) {
//	         e.printStackTrace();
//	     }	

		setBounds(390, 90, 830, 470);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		URL url = SignUp.class.getResource("/Icon/dki.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		{
			JLabel lb_dangki = new JLabel("Đăng Kí");
			lb_dangki.setForeground(Color.RED);
			lb_dangki.setBounds(315, 23, 207, 49);
			lb_dangki.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
			contentPanel.add(lb_dangki);
		}

		JSeparator separator = new JSeparator();
		separator.setBounds(37, 82, 769, -10);
		contentPanel.add(separator);
		{
			JSeparator separator_1 = new JSeparator();
			separator_1.setBounds(10, 76, 796, 13);
			contentPanel.add(separator_1);
		}
		{
			JLabel lb_thongtin = new JLabel("Thông Tin");
			lb_thongtin.setFont(new Font("Tahoma", Font.BOLD, 25));
			lb_thongtin.setBounds(20, 82, 142, 31);
			contentPanel.add(lb_thongtin);
		}
		{
			JLabel lb_dktaikhoan = new JLabel("Tài Khoản:");
			lb_dktaikhoan.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lb_dktaikhoan.setBounds(75, 146, 113, 31);
			contentPanel.add(lb_dktaikhoan);
		}

		tf_dangkitk = new JTextField();
		tf_dangkitk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_dangkitk.setBounds(198, 147, 181, 28);
		contentPanel.add(tf_dangkitk);
		tf_dangkitk.setColumns(10);

		JLabel lb_dkmatkhau = new JLabel("Mật Khẩu:");
		lb_dkmatkhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_dkmatkhau.setBounds(404, 146, 113, 31);
		contentPanel.add(lb_dkmatkhau);

		tf_dkimk = new JPasswordField();
		tf_dkimk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_dkimk.setColumns(10);
		tf_dkimk.setBounds(580, 147, 181, 30);
		contentPanel.add(tf_dkimk);

		JLabel lb_hovaten = new JLabel("Họ Và Tên:");
		lb_hovaten.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_hovaten.setBounds(75, 202, 99, 22);
		contentPanel.add(lb_hovaten);

		tf_hovaten = new JTextField();
		tf_hovaten.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_hovaten.setBounds(198, 198, 181, 31);
		contentPanel.add(tf_hovaten);
		tf_hovaten.setColumns(10);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 389, 796, 13);
		contentPanel.add(separator_1);

		gioitinh = new ButtonGroup();

		JLabel lb_diachi = new JLabel("Địa Chỉ: ");
		lb_diachi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_diachi.setBounds(75, 253, 85, 21);
		contentPanel.add(lb_diachi);

		tf_diachi = new JTextField();
		tf_diachi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_diachi.setColumns(10);
		tf_diachi.setBounds(198, 249, 181, 28);
		contentPanel.add(tf_diachi);

		JButton bt_xoatt = new JButton("Xóa Thông Tin");
		bt_xoatt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = JOptionPane.showConfirmDialog(SignUp.this, "Bạn Có Chắc Chắn Muốn Xóa Thông Tin?", "Cảnh Báo",
						JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					xoatt();
				}
			}

		});
		bt_xoatt.setBackground(Color.GRAY);
		bt_xoatt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		bt_xoatt.setBounds(638, 340, 168, 39);
		contentPanel.add(bt_xoatt);

		JLabel lb_nhaplaimk = new JLabel("Nhập Lại Mật Khẩu:");
		lb_nhaplaimk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_nhaplaimk.setBounds(404, 202, 166, 22);
		contentPanel.add(lb_nhaplaimk);

		JLabel lb_gioitinh_1 = new JLabel("Giới Tính:");
		lb_gioitinh_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_gioitinh_1.setBounds(297, 304, 85, 29);
		contentPanel.add(lb_gioitinh_1);

		JRadioButton jrbt_nam_1 = new JRadioButton("Nam");
		jrbt_nam_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jrbt_nam_1.setBounds(395, 308, 63, 21);
		contentPanel.add(jrbt_nam_1);

		JRadioButton jrbt_nu_1 = new JRadioButton("Nữ");
		jrbt_nu_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		jrbt_nu_1.setBounds(477, 308, 63, 21);
		contentPanel.add(jrbt_nu_1);

		bt_group = new ButtonGroup();
		bt_group.add(jrbt_nam_1);
		bt_group.add(jrbt_nu_1);

		JLabel lb_ngaysinh = new JLabel("Ngày Sinh:");
		lb_ngaysinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_ngaysinh.setBounds(404, 252, 99, 22);
		contentPanel.add(lb_ngaysinh);

		dtCBTD = new JDateChooser();
		dtCBTD.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 18));
		dtCBTD.getCalendarButton().setBackground(Color.WHITE);
		dtCBTD.setBackground(Color.WHITE);
		dtCBTD.setBounds(580, 244, 181, 30);
		contentPanel.add(dtCBTD);

		tf_nhaplaimk = new JPasswordField();
		tf_nhaplaimk.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_nhaplaimk.setBounds(580, 199, 181, 31);
		contentPanel.add(tf_nhaplaimk);
		tf_nhaplaimk.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_quaylai = new JButton("Quay Lại");
				bt_quaylai.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						new Login();

					}

				});
				bt_quaylai.setBackground(Color.WHITE);
				bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_quaylai.setActionCommand("Quay Lại");
				buttonPane.add(bt_quaylai);
			}
			{
				JButton bt_hoanthanh = new JButton("Hoàn Thành");
				bt_hoanthanh.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						String gt = null;
						if (jrbt_nam_1.isSelected()) {
							gt = jrbt_nam_1.getText();
							System.out.println(gt);
						} else if (jrbt_nu_1.isSelected()) {
							gt = jrbt_nu_1.getText();
							System.out.println(gt);
						}
						String s1 = tf_dangkitk.getText().toLowerCase();
						String s = tf_hovaten.getText();
						String s2 = tf_dkimk.getText().toLowerCase();
						String s3 = tf_nhaplaimk.getText().toLowerCase();
						String s4 = tf_diachi.getText();
						if (s1.equals("") == false && s4.equals("") == false && s2.equals("") == false
								&& s.equals("") == false && gt != null) {
							if (tf_dkimk.getText().equalsIgnoreCase(tf_nhaplaimk.getText()) == false)
								JOptionPane.showMessageDialog(SignUp.this, "Mật Khẩu Không Khớp");
							else {
								try {
									DBConnection conn = new DBConnection();
									int record = conn.executeDB("INSERT INTO Account VALUES(N'" + tf_dangkitk.getText()
											+ "',N'" + tf_dkimk.getText() + "',N'" + tf_hovaten.getText() + "','"
											+ df.format(dtCBTD.getDate()) + "',N'" + tf_diachi.getText() + "',N'" + gt
											+ "')");
									if (record > 0) {
										JOptionPane.showMessageDialog(null, "Đăng Kí Thành Công");
										dispose();
										Login lg = new Login();
										lg.setVisible(true);
									} else
										JOptionPane.showMessageDialog(null, "Đăng Ký Thất Bại Vui Lòng Thử Lại");
								} catch (Exception e2) {
									// TODO: handle exception
//									JOptionPane.showMessageDialog(SignUp.this, "Không Thành Công, Vui Lòng Thử Lại");
									e2.printStackTrace();

								}

							}

						} else {
							JOptionPane.showMessageDialog(SignUp.this, "Thiếu Thông Tin");
						}
					}

				});
				bt_hoanthanh.setBackground(Color.WHITE);
				bt_hoanthanh.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_hoanthanh.setActionCommand("OK");
				buttonPane.add(bt_hoanthanh);
				getRootPane().setDefaultButton(bt_hoanthanh);
			}
		}
		setVisible(true);
	}

	private void xoatt() {
		tf_dangkitk.setText("");
		tf_dkimk.setText("");
		tf_hovaten.setText("");
		dtCBTD.setDate(null);
		tf_diachi.setText("");
		gioitinh.clearSelection();
		tf_nhaplaimk.setText("");
		bt_group.clearSelection();

	}
}
