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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JEditorPane;
import javax.swing.JInternalFrame;
import javax.swing.JSlider;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import DBConnection.DBConnection;
import Model.Table;
import login.Loginnn;

import java.awt.SystemColor;
import java.awt.event.*;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.orsoncharts.legend.ColorScaleElement;

public class Home<Image> extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lb_clock;
	private JLabel lb_thongtinban;
	private JButton bt_ban1;
	private JButton bt_ban2;
	private JButton bt_ban3;
	private JButton bt_ban4;
	private JButton bt_ban5;
	private JButton bt_ban6;
	int ban=0;
	private List<Table> tablelist = new ArrayList<Table>();
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	private DefaultTableModel tableModel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home frame = new Home();
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
	public Home() {
		setTitle("Home");

		URL url = Login.class.getResource("/Icon/symbolmain.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(330, 50, 960, 700);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.inactiveCaption);
		setJMenuBar(menuBar);
		
		JMenu jmenu_menu = new JMenu("Menu");
		jmenu_menu.setBackground(Color.WHITE);
		jmenu_menu.setIcon(new ImageIcon(Home.class.getResource("/icon/menu.png")));
		jmenu_menu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(jmenu_menu);
		
		JMenuItem jtem_danhsachdouong = new JMenuItem("Danh Sách Đồ Uống");
		jtem_danhsachdouong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ListProduct();
			}
			
		});
		jtem_danhsachdouong.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jmenu_menu.add(jtem_danhsachdouong);
		
		JMenuItem item_hosothanhtoan = new JMenuItem("Hồ Sơ Thanh Toán");
		item_hosothanhtoan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new DetailBill();
				
			}
			
		});
		item_hosothanhtoan.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jmenu_menu.add(item_hosothanhtoan);
		
		JMenu jmenu_bieudo = new JMenu("Biểu Đồ Thống Kê");
		jmenu_bieudo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jmenu_menu.add(jmenu_bieudo);
		
		JMenuItem item_bdcot = new JMenuItem("Biểu Đồ Cột");
		item_bdcot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThongKe();
//				DefaultCategoryDataset bdcot = new DefaultCategoryDataset();
//				DBConnection cnn  = new DBConnection();
//				// THÁNG 1
//				String sql = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+1+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 1");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				// THÁNG 2
//				String sql2 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+2+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql2);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 2");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				// THÁNG 3 
//				String sql3 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+3+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql3);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 3");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 4
//				String sql4 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+4+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql4);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 4");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 5
//				String sql5 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+5+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql5);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 5");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 6
//				String sql6 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+6+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql6);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 6");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 7
//				String sql7 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+7+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql7);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 7");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 8
//				String sql8 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+8+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql8);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 8");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				
//				//THÁNG 9
//				String sql9 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+9+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql9);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 9");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				
//				//THÁNG 10
//				String sql10 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+10+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql10);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 10");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 11
//				String sql11 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+11+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql11);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 11");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				//THÁNG 12
//				String sql12 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+2021+"' AND MONTH(ThoiGian)= '"+12+"'";
//				conn = cnn.connectSQL();
//				try {
//					ps = conn.prepareStatement(sql12);
//				    rs = ps.executeQuery();
//					if (rs != null){
//						while (rs.next()) {
//							bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 12");
//						}
//					}			
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//				
//				
//				JFreeChart jChart = ChartFactory.createBarChart("Biểu Đồ Doanh Thu Hằng Năm","Theo Các Tháng Trong Năm", "Tổng Doang Thu Hằng Tháng", bdcot, PlotOrientation.VERTICAL,true,true,false);
//	        	CategoryPlot plot = jChart.getCategoryPlot();
////	        	plot.setRangeGridlinePaint(Color.black);
//	        	ChartFrame chartFrame = new ChartFrame("Biểu Đồ Cột Doanh Thu Hằng Tháng", jChart,true);
//	        	chartFrame.setVisible(true);
//	        	chartFrame.setSize(1000,800);
				
			}
		});
		item_bdcot.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		jmenu_bieudo.add(item_bdcot);
		
		JMenuItem item_bdtron = new JMenuItem("Biểu Đồ Tròn");
		item_bdtron.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThongKeTr();
			}
		});
		
		item_bdtron.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		jmenu_bieudo.add(item_bdtron);
		
		JMenuItem jtem_chat = new JMenuItem("Chat");
		jtem_chat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Loginnn();
			}
		});
		jtem_chat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jmenu_menu.add(jtem_chat);
		
		JMenu jmenu_taikhoan = new JMenu("Tài Khoản");
		jmenu_taikhoan.setBackground(Color.WHITE);
		jmenu_taikhoan.setIcon(new ImageIcon(Home.class.getResource("/icon/man.png")));
		jmenu_taikhoan.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(jmenu_taikhoan);
		
		JMenuItem jitem_thongtin = new JMenuItem("C\u1EADp Nh\u1EADt L\u1EA1i Th\u00F4ng Tin");
		jitem_thongtin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jmenu_taikhoan.add(jitem_thongtin);
		
		JMenuItem jitem_dangxuat = new JMenuItem("Đăng Xuất");
		jitem_dangxuat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				new Login();
			}
			
		});
		jitem_dangxuat.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		jmenu_taikhoan.add(jitem_dangxuat);
		
		JMenu mnNewMenu = new JMenu("Trợ Giúp");
		mnNewMenu.setBackground(Color.WHITE);
		mnNewMenu.setIcon(new ImageIcon(Home.class.getResource("/icon/help2.png")));
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem item_lienhequanli = new JMenuItem("Liên Hệ Quản Lí");
		item_lienhequanli.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(Home.this,"Chi Tiết Xin Liên Hệ : 0931368433");
			}
			
		});
		item_lienhequanli.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(item_lienhequanli);
		
		JMenuItem item_thontinkhac = new JMenuItem("Thông Tin Khác");
		item_thontinkhac.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(Home.this,"Truy Cập: https://www.facebook.com/Cafe4mua.ThanhDaNang/");
			}
			
		});
		item_thontinkhac.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		mnNewMenu.add(item_thontinkhac);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.info);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Danh Sách Bàn");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(85, 65, 215, 21);
		contentPane.add(lblNewLabel);
		
		bt_ban1 = new JButton("  1");
		bt_ban1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lb_thongtinban.setText("Thông Tin Bàn 1");
				bt_ban1.setBackground(Color.YELLOW);
				bt_ban2.setBackground(Color.GRAY);
				bt_ban3.setBackground(Color.GRAY);
				bt_ban4.setBackground(Color.GRAY);
				bt_ban5.setBackground(Color.GRAY);
				bt_ban6.setBackground(Color.GRAY);
				ban=1;
				if(loadData2(ban)!=1) {
					JOptionPane.showMessageDialog(Home.this, "Bàn Chưa Được Đặt");
				}
				showAll2();
				
			}
			
		});
		bt_ban1.setBackground(Color.GRAY);
		bt_ban1.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_ban1.setIcon(new ImageIcon(Home.class.getResource("/icon/table.png")));
		bt_ban1.setBounds(37, 133, 119, 57);
		contentPane.add(bt_ban1);
		
	    bt_ban2 = new JButton(" 2");
		bt_ban2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				lb_thongtinban.setText("Thông Tin Bàn 2");
				bt_ban2.setBackground(Color.YELLOW);
				bt_ban1.setBackground(Color.GRAY);
				bt_ban3.setBackground(Color.GRAY);
				bt_ban4.setBackground(Color.GRAY);
				bt_ban5.setBackground(Color.GRAY);
				bt_ban6.setBackground(Color.GRAY);
				ban=2;
				if(loadData2(ban)!=1) {
					JOptionPane.showMessageDialog(Home.this, "Bàn Chưa Được Đặt");
				}
				showAll2();
			}
			
		});
		bt_ban2.setBackground(Color.GRAY);
		bt_ban2.setIcon(new ImageIcon(Home.class.getResource("/icon/table.png")));
		bt_ban2.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_ban2.setBounds(214, 133, 119, 57);
		contentPane.add(bt_ban2);
		
	    bt_ban3 = new JButton(" 3");
		bt_ban3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lb_thongtinban.setText("Thông Tin Bàn 3");
				bt_ban3.setBackground(Color.YELLOW);
				bt_ban2.setBackground(Color.GRAY);
				bt_ban1.setBackground(Color.GRAY);
				bt_ban4.setBackground(Color.GRAY);
				bt_ban5.setBackground(Color.GRAY);
				bt_ban6.setBackground(Color.GRAY);
				ban=3;
				if(loadData2(ban)!=1) {
					JOptionPane.showMessageDialog(Home.this, "Bàn Chưa Được Đặt");
				}
				showAll2();
			}
			
		});
		bt_ban3.setBackground(Color.GRAY);
		bt_ban3.setIcon(new ImageIcon(Home.class.getResource("/icon/table.png")));
		bt_ban3.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_ban3.setBounds(37, 247, 119, 57);
		contentPane.add(bt_ban3);
		
	    bt_ban4 = new JButton(" 4");
		bt_ban4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lb_thongtinban.setText("Thông Tin Bàn 4");
				bt_ban4.setBackground(Color.YELLOW);
				bt_ban2.setBackground(Color.GRAY);
				bt_ban3.setBackground(Color.GRAY);
				bt_ban1.setBackground(Color.GRAY);
				bt_ban5.setBackground(Color.GRAY);
				bt_ban6.setBackground(Color.GRAY);
				ban=4;
				if(loadData2(ban)!=1) {
					JOptionPane.showMessageDialog(Home.this, "Bàn Chưa Được Đặt");
				}
				showAll2();
				
			}
			
		});
		bt_ban4.setBackground(Color.GRAY);
		bt_ban4.setIcon(new ImageIcon(Home.class.getResource("/icon/table.png")));
		bt_ban4.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_ban4.setBounds(214, 247, 119, 57);
		contentPane.add(bt_ban4);
		
		bt_ban5 = new JButton(" 5");
		bt_ban5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lb_thongtinban.setText("Thông Tin Bàn 5");
				bt_ban5.setBackground(Color.YELLOW);
				bt_ban2.setBackground(Color.GRAY);
				bt_ban3.setBackground(Color.GRAY);
				bt_ban4.setBackground(Color.GRAY);
				bt_ban1.setBackground(Color.GRAY);
				bt_ban6.setBackground(Color.GRAY);
				ban=5;
				if(loadData2(ban)!=1) {
					JOptionPane.showMessageDialog(Home.this, "Bàn Chưa Được Đặt");
				}
				showAll2();
			}
			
		});
		bt_ban5.setBackground(Color.GRAY);
		bt_ban5.setIcon(new ImageIcon(Home.class.getResource("/icon/table.png")));
		bt_ban5.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_ban5.setBounds(37, 357, 119, 57);
		contentPane.add(bt_ban5);
		
		bt_ban6 = new JButton("  6");
		bt_ban6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lb_thongtinban.setText("Thông Tin Bàn 6");
				bt_ban6.setBackground(Color.YELLOW);
				bt_ban2.setBackground(Color.GRAY);
				bt_ban3.setBackground(Color.GRAY);
				bt_ban4.setBackground(Color.GRAY);
				bt_ban5.setBackground(Color.GRAY);
				bt_ban1.setBackground(Color.GRAY);
				ban=6;
				if(loadData2(ban)!=1) {
					JOptionPane.showMessageDialog(Home.this, "Bàn Chưa Được Đặt");
				}
				showAll2();
			}
			
		});
		bt_ban6.setBackground(Color.GRAY);
		bt_ban6.setIcon(new ImageIcon(Home.class.getResource("/icon/table.png")));
		bt_ban6.setFont(new Font("Tahoma", Font.BOLD, 18));
		bt_ban6.setBounds(214, 357, 119, 57);
		contentPane.add(bt_ban6);
		
		JButton bt_order = new JButton("Đặt Bàn Ngay !!!");
		bt_order.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
			       new Order();
				
			}
			
		});
		bt_order.setBackground(Color.CYAN);
		bt_order.setIcon(new ImageIcon(Home.class.getResource("/icon/order.png")));
		bt_order.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		bt_order.setBounds(50, 480, 276, 84);
		contentPane.add(bt_order);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã Bàn", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Thành Tiền"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(448, 133, 478, 281);
		contentPane.add(scrollPane);
		
	    lb_thongtinban = new JLabel("Thông Tin Bàn");
		lb_thongtinban.setForeground(Color.RED);
		lb_thongtinban.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lb_thongtinban.setBounds(589, 60, 238, 31);
		contentPane.add(lb_thongtinban);
		
		JButton bt_doiban = new JButton("Đổi Bàn");
		bt_doiban.setIcon(new ImageIcon(Home.class.getResource("/icon/transfertable48.png")));
		bt_doiban.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                   new TransferTable(ban);
				
			}
			
		});
		bt_doiban.setBackground(Color.WHITE);
		bt_doiban.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_doiban.setBounds(498, 485, 176, 79);
		contentPane.add(bt_doiban);
		
		String[] doiban = {"","Bàn 1","Bàn 2","Bàn 3","Bàn 4","Bàn 5","Bàn 6"};
		
		String[] sale = {"No","10%","25%","50%"};
		
		JButton bt_tinhtien = new JButton("Tính Tiền");
		bt_tinhtien.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Bill(ban);
				
			}
			
		});
		bt_tinhtien.setBackground(Color.WHITE);
		bt_tinhtien.setIcon(new ImageIcon(Home.class.getResource("/icon/bill.png")));
		bt_tinhtien.setFont(new Font("Tahoma", Font.PLAIN, 20));
		bt_tinhtien.setBounds(724, 480, 176, 88);
		contentPane.add(bt_tinhtien);
		
		Font font= new Font("Arial", Font.BOLD, 20);
		
		lb_clock = new JLabel();
		lb_clock.setFont(font);	
		lb_clock.setBackground(SystemColor.info);
		lb_clock.setBounds(826, 10, 100, 31);
		contentPane.add(lb_clock);
		tableModel2 = (DefaultTableModel) table.getModel();
		setVisible(true);
		clock();
	}
	public void clock() {
	    Thread clock = new Thread() {
	        public void run() {
	            try {
	                while (true) {
	                    Calendar cal = new GregorianCalendar();
	                    int second = cal.get(Calendar.SECOND);
	                    int minute = cal.get(Calendar.MINUTE);
	                    int hour = cal.get(Calendar.HOUR_OF_DAY);
	                    

	                   String time= hour + ":" + minute + ":" + second;
	                   lb_clock.setText(time);
	                    sleep(1000);
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        };
	        clock.start(); 
	}
	public int loadData2(int c) {
		int a=0;
		tablelist.clear();
		DBConnection cnn  = new DBConnection();
		String sql = "SELECT * FROM DetailBill where MaBan='"+c+"' ";
		conn = cnn.connectSQL();
		try {
			ps = conn.prepareStatement(sql);
		    rs = ps.executeQuery();
			if (rs != null){
				while (rs.next()) {
				Table table = new Table(rs.getInt("MaBan"),rs.getString("MaSP"),rs.getString("TenSp"),rs.getInt("SoLuong"),rs.getInt("ThanhTien"));
					tablelist.add(table);
					a=1;
				}
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}
	public void showAll2() {
		tableModel2.setRowCount(0);
		for (int i = 0; i < tablelist.size(); i++) {
			tableModel2.addRow(new Object[] { tablelist.get(i).getMaBan(),tablelist.get(i).getMaSp(),tablelist.get(i).getTenSp(),
					String.valueOf(tablelist.get(i).getSoLuong()),	String.valueOf(tablelist.get(i).getTongTien())
       
			});

		}
	}
}
