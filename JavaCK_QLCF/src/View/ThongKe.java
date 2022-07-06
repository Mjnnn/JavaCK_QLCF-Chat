package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.*;
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

import DBConnection.DBConnection;
import java.awt.Color;


public class ThongKe<Image> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	private JComboBox cbb_thongke;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ThongKe dialog = new ThongKe();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ThongKe() {
		setTitle("Thống Kê");
		this.setLocationRelativeTo(null);
		URL url = ThongKe.class.getResource("/Icon/thongke.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setBounds(600, 300, 403, 185);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lb_thongke = new JLabel("Chọn Năm Cần Thống Kê:");
		lb_thongke.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lb_thongke.setBounds(30, 37, 249, 25);
		contentPanel.add(lb_thongke);
		
		cbb_thongke = new JComboBox();
		for(int i=2018; i<=2030;i++){
			cbb_thongke.addItem(i+"");
		}
		cbb_thongke.setBackground(Color.WHITE);
		cbb_thongke.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cbb_thongke.setBounds(287, 37, 80, 27);
		contentPanel.add(cbb_thongke);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_quaylai = new JButton("Quay Lại");
				bt_quaylai.setBackground(Color.GRAY);
				bt_quaylai.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ThongKe.this.setVisible(false);
					}
					
				});
				bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_quaylai.setActionCommand("OK");
				buttonPane.add(bt_quaylai);
				getRootPane().setDefaultButton(bt_quaylai);
			}
			{
				JButton bt_thongke = new JButton("Thống Kê");
				bt_thongke.setBackground(Color.GRAY);
				bt_thongke.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DefaultCategoryDataset bdcot = new DefaultCategoryDataset();
						int a= Integer.parseInt((String) cbb_thongke.getItemAt(cbb_thongke.getSelectedIndex()));
						DBConnection cnn  = new DBConnection();
						// THÁNG 1
						String sql = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+1+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 1");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						// THÁNG 2
						String sql2 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+2+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql2);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 2");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						// THÁNG 3 
						String sql3 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+3+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql3);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 3");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 4
						String sql4 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+4+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql4);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 4");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 5
						String sql5 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+5+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql5);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 5");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 6
						String sql6 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+6+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql6);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 6");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 7
						String sql7 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+7+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql7);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 7");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 8
						String sql8 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+8+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql8);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 8");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						//THÁNG 9
						String sql9 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+9+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql9);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 9");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						//THÁNG 10
						String sql10 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+10+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql10);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 10");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 11
						String sql11 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+11+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql11);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 11");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						//THÁNG 12
						String sql12 = "SELECT SUM(TongTien) as [TongTien] FROM Bill where YEAR(ThoiGian) ='"+a+"' AND MONTH(ThoiGian)= '"+12+"'";
						conn = cnn.connectSQL();
						try {
							ps = conn.prepareStatement(sql12);
						    rs = ps.executeQuery();
							if (rs != null){
								while (rs.next()) {
									bdcot.setValue(rs.getInt("TongTien"), "Doanh Thu", "Tháng 12");
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						
						JFreeChart jChart = ChartFactory.createBarChart("Biểu Đồ Doanh Thu Trong Năm","Theo Các Tháng Trong Năm", "Tổng Doang Thu Hằng Tháng (Đơn Vị: Vnd)", bdcot, PlotOrientation.VERTICAL,true,true,false);
			        	CategoryPlot plot = jChart.getCategoryPlot();
//			        	plot.setRangeGridlinePaint(Color.black);
			        	ChartFrame chartFrame = new ChartFrame("Biểu Đồ Cột Doanh Thu Hằng Tháng", jChart,true);
			        	chartFrame.setVisible(true);
			        	chartFrame.setSize(1200,800);
			        	chartFrame.setLocationRelativeTo(null);
						
					}
					
				});
				bt_thongke.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_thongke.setActionCommand("Cancel");
				buttonPane.add(bt_thongke);
			}
		}
		setVisible(true);
	}
}
