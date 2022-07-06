package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Password extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPanel contentPane;
	private JTextField tf_nhapsdt;
	private JTextField tf_maxn;
	private JPasswordField tf_mkmoi;
	private JPasswordField tf_mkmoi2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Password dialog = new Password();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public <Image> Password() {
		setTitle("Password");
		URL url = Password.class.getResource("/Icon/pass.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		setLocationRelativeTo(null);
		setBounds(390, 100, 872, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lb_quenmk = new JLabel("Qu\u00EAn M\u1EADt Kh\u1EA9u");
		lb_quenmk.setForeground(Color.RED);
		lb_quenmk.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 40));
		lb_quenmk.setBounds(260, 21, 323, 44);
		contentPane.add(lb_quenmk);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 75, 838, 2);
		contentPane.add(separator);
		
		JLabel lb_nhapsdt = new JLabel("Vui L\u00F2ng Nh\u1EADp S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i:");
		lb_nhapsdt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_nhapsdt.setBounds(41, 121, 269, 31);
		contentPane.add(lb_nhapsdt);
		
		tf_nhapsdt = new JTextField();
		tf_nhapsdt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_nhapsdt.setBounds(347, 123, 198, 28);
		contentPane.add(tf_nhapsdt);
		tf_nhapsdt.setColumns(10);
		
		JLabel lb_maxn = new JLabel("Nh\u1EADp M\u00E3 X\u00E1c Nh\u1EADn:");
		lb_maxn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_maxn.setBounds(41, 171, 177, 28);
		contentPane.add(lb_maxn);
		
		tf_maxn = new JTextField();
		tf_maxn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_maxn.setBounds(347, 172, 198, 28);
		contentPane.add(tf_maxn);
		tf_maxn.setColumns(10);
		
		JButton bt_guima = new JButton("G\u1EEDi M\u00E3 X\u00E1c Nh\u1EADn");
		bt_guima.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(Password.this,"Mã Đang Về, Vui Lòng Đợi Giây Lát");
			}
			
		});
		bt_guima.setBackground(Color.GRAY);
		bt_guima.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_guima.setBounds(587, 143, 227, 33);
		contentPane.add(bt_guima);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 244, 838, 2);
		contentPane.add(separator_1);
		
		JLabel lb_doimk = new JLabel("\u0110\u1ED5i M\u1EADt Kh\u1EA9u");
		lb_doimk.setForeground(Color.BLACK);
		lb_doimk.setFont(new Font("Tahoma", Font.BOLD, 22));
		lb_doimk.setBounds(20, 256, 198, 28);
		contentPane.add(lb_doimk);
		
		JLabel lb_mkmoi = new JLabel("M\u1EADt Kh\u1EA9u M\u1EDBi:");
		lb_mkmoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_mkmoi.setBounds(236, 301, 150, 31);
		contentPane.add(lb_mkmoi);
		
		tf_mkmoi = new JPasswordField();
		tf_mkmoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_mkmoi.setBounds(460, 303, 177, 29);
		contentPane.add(tf_mkmoi);
		tf_mkmoi.setColumns(10);
		
		JLabel lb_mkmoi_2 = new JLabel("Nh\u1EADp L\u1EA1i M\u1EADt Kh\u1EA9u M\u1EDBi:");
		lb_mkmoi_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_mkmoi_2.setBounds(235, 367, 227, 31);
		contentPane.add(lb_mkmoi_2);
		
		tf_mkmoi2 = new JPasswordField();
		tf_mkmoi2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tf_mkmoi2.setColumns(10);
		tf_mkmoi2.setBounds(460, 369, 177, 29);
		contentPane.add(tf_mkmoi2);
		
		JButton bt_quaylai = new JButton("Quay L\u1EA1i");
		bt_quaylai.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Login();
			}
			
		});
		bt_quaylai.setBackground(Color.WHITE);
		bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_quaylai.setBounds(544, 461, 132, 31);
		contentPane.add(bt_quaylai);
		
		JButton bt_hoanthnanh = new JButton("Ho\u00E0n Th\u00E0nh");
		bt_hoanthnanh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(Password.this,"Cập Nhật Mật Khẩu Thành Công");
				dispose();
				new Login();
			}
			
		});
		bt_hoanthnanh.setBackground(Color.WHITE);
		bt_hoanthnanh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_hoanthnanh.setBounds(698, 461, 150, 30);
		contentPane.add(bt_hoanthnanh);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 443, 838, 8);
		contentPane.add(separator_2);
		setVisible(true);
	}

	


}
