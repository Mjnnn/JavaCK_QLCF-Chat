package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import Model.SynchList;
import Model.SocketConnection;
import Model.Transaction;
import Model.Message;

public class Server extends JFrame {

	private JPanel contentPane;
	private JTextField tf_ip;
	private JLabel lb_port;
	private JTextField tf_port;
    int port_number = 0;
	private ServerSocket serverSocket;
	private Transaction transactions;
	private SynchList outputs;
	private static ArrayList<String> online_users;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
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
	public Server() {
		setTitle("Server");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 400, 400);
		URL url = Server.class.getResource("/Icon/Hserver.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		JLabel bgr = new JLabel("");
//		bgr.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Start.class.getResource("/Icon/server2.png"))));
//		bgr.setBounds(10, 0, 386, 363);
//		contentPane.add(bgr);

		tf_ip = new JTextField();
		tf_ip.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_ip.setEditable(false);
		tf_ip.setBackground(Color.LIGHT_GRAY);
		tf_ip.setBounds(167, 105, 195, 31);
		contentPane.add(tf_ip);
		tf_ip.setColumns(10);
		tf_ip.setText("192.165.1.5");

		JLabel lb_ip = new JLabel("Địa Chỉ IP:");
		lb_ip.setBackground(Color.WHITE);
		lb_ip.setFont(new Font("Tahoma", Font.BOLD, 20));
		lb_ip.setForeground(Color.BLACK);
		lb_ip.setBounds(33, 105, 124, 31);
		contentPane.add(lb_ip);

		lb_port = new JLabel("Vui Lòng Nhập Cổng:");
		lb_port.setForeground(Color.BLACK);
		lb_port.setFont(new Font("Tahoma", Font.BOLD, 15));
		lb_port.setBounds(116, 172, 176, 24);
		contentPane.add(lb_port);

		tf_port = new JTextField();
		tf_port.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tf_port.setBounds(38, 214, 324, 31);
		contentPane.add(tf_port);
		tf_port.setColumns(10);

		JButton btn_run = new JButton("Khởi Động");
		btn_run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int a = tf_port.getText().length();
				if (a < 1) {
					JOptionPane.showMessageDialog(null, "Vui lòng Kiểm Tra lại cổng!");
				} else if (a > 0) {
					port_number = Integer.parseInt(tf_port.getText());
					System.out.println(tf_port.getText());
					JOptionPane.showMessageDialog(null, "Khởi động Server thành công!");
					runServer();
//					setState(Frame.ICONIFIED);
				}
			}
		});
		btn_run.setBackground(Color.LIGHT_GRAY);
		btn_run.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btn_run.setBounds(116, 295, 165, 31);
		contentPane.add(btn_run);
		
		JLabel lb_bgr = new JLabel("");
		lb_bgr.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(Start.class.getResource("/Icon/server2.png"))));
		lb_bgr.setBounds(0, 0, 386, 363);
		contentPane.add(lb_bgr);
	}

	public void runServer() {
		try {
			System.out.println("Port: " + port_number);
			if (port_number > 0) {
				serverSocket = new ServerSocket(port_number);
                outputs = new SynchList();
                online_users = new ArrayList<String>();
                online_users.add("Everyone");

			}
			 while (true) {
	                transactions = new Transaction(outputs.size(), outputs,online_users ,serverSocket.accept());
	                System.out.println("Máy Chủ hiện đang chạy trên cổng "+port_number +".....");
	                transactions.start();
	                System.out.println("Khách Hàng Đã Tham Gia...");
	            }

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Thực hiện không thành công: " + e);
		}
	}
}
