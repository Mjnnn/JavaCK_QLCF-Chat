package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;
import View.Home;
import View.Login;
import Model.Account;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tf_username;
	private JPasswordField tf_pass;
	private ArrayList<Account> list = new ArrayList<Account>();
	private Connection conn;
	 PreparedStatement ps;
	 ResultSet rs ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param <Image>
	 */
	public <Image> Login() {
		setTitle("Đăng Nhập");
		
		 try {
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=JavaCK;"
	                + "username=sa;password=sab");
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(525, 100, 577, 586);
		
		URL url = Login.class.getResource("/Icon/icondangnhap.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_username = new JLabel("Tài Khoản:");
//		lb_username.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("icontaikhoan.png"))));
		lb_username.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_username.setBounds(86, 262, 177, 67);
		contentPane.add(lb_username);
		
		tf_username = new JTextField();
		tf_username.setBackground(Color.WHITE);
		tf_username.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_username.setBounds(246, 282, 177, 28);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		JLabel lb_password = new JLabel("Mật Khẩu:");
//		lb_password.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("iconmatkhau.png"))));
		lb_password.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_password.setBounds(86, 332, 188, 48);
		contentPane.add(lb_password);
		
		tf_pass = new JPasswordField();
		tf_pass.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_pass.setBackground(Color.WHITE);
		tf_pass.setBounds(246, 343, 177, 28);
		contentPane.add(tf_pass);
		tf_pass.setColumns(10);
		
		JLabel lb_background = new JLabel("");
		lb_background.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Login.class.getResource("/Icon/logincf.jpg"))));
		lb_background.setBounds(167, -1, 267, 233);
		contentPane.add(lb_background);
		
		JButton bt_dangnhap = new JButton("Đăng Nhập");
		bt_dangnhap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DBConnection cnn = new DBConnection();
				conn = cnn.connectSQL();
				String sql = "SELECT *FROM Account WHERE TaiKhoan=? AND MatKhau=?";
				try {
					ps = conn.prepareStatement(sql);
					ps.setString(1, tf_username.getText());
					ps.setString(2, tf_pass.getText().toLowerCase());
					ResultSet rs = ps.executeQuery();
					if (tf_username.getText().equals("") || tf_pass.getText().equals("")) {
						JOptionPane.showMessageDialog(Login.this, "Chưa Nhập Tài Khoản Hoạc Mật Khẩu");
					} else if (rs.next()) {
						int n = JOptionPane.showConfirmDialog(Login.this, "Đăng Nhập Thành Công, Tiếp Tục?", "Đăng Nhập", JOptionPane.YES_NO_OPTION);
						if (n==JOptionPane.YES_OPTION) {
							Home cf = new Home();
							cf.setVisible(true);
							cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							Login.this.dispose();
							return;
						};
						
					}
						else {
							JOptionPane.showMessageDialog(Login.this, "Đăng Nhập Thất Bại, Vui Lòng Kiểm Tra Lại Tài Khoản Hoặc Mật Khẩu");
						}
					}
				

				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			
		});
		bt_dangnhap.setBackground(Color.GRAY);
		bt_dangnhap.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_dangnhap.setBounds(332, 418, 153, 38);
		contentPane.add(bt_dangnhap);
		
		JLabel lb_quenmk = new JLabel("Bạn Quên Mậtt Khẩu...?");
		lb_quenmk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				new Password();
			}
		});
		lb_quenmk.setBackground(Color.WHITE);
		lb_quenmk.setForeground(Color.RED);
		lb_quenmk.setFont(new Font("Tahoma", Font.ITALIC, 12));
		lb_quenmk.setBounds(209, 388, 135, 13);
		contentPane.add(lb_quenmk);
		
		JButton bt_signup = new JButton("Đăng Kí");
		bt_signup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new SignUp();	
			}
		});
		bt_signup.setBackground(Color.GRAY);
		bt_signup.setFont(new Font("Tahoma", Font.BOLD, 20));
		bt_signup.setBounds(153, 420, 147, 34);
		contentPane.add(bt_signup);
		setVisible(true);
	}
}
