package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.text.DefaultCaret;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import com.orsoncharts.legend.ColorScaleElement;
import com.orsoncharts.plot.PiePlot3D;

import DBConnection.DBConnection;
import demo.orsoncharts.swing.PieChart3DDemo1;

import java.awt.Color;

public class ThongKeTr<Image> extends JDialog {

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
			ThongKeTr dialog = new ThongKeTr();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ThongKeTr() {
        setTitle("Thống Kê");
		setLocationRelativeTo(null);
		URL url = ThongKe.class.getResource("/Icon/thongketr.png");
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
						ThongKeTr.this.setVisible(false);
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
						DefaultPieDataset dataset = new DefaultPieDataset();
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
									dataset.setValue("Tháng 1",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 2",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 3",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 4",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 5",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 6",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 7",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 8",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 9",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 10",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 11",new Integer(rs.getInt("TongTien")));
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
									dataset.setValue("Tháng 12",new Integer(rs.getInt("TongTien")));
								}
							}			
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
						
						JFreeChart jChart = ChartFactory.createPieChart("Biểu Đồ Doanh Thu Trong Năm", dataset,true,true,false);
					    PiePlot p = (PiePlot) jChart.getPlot();
//			        	CategoryPlot plot = jChart.getCategoryPlot();
//			        	plot.setRangeGridlinePaint(Color.black);
			        	ChartFrame chartFrame = new ChartFrame("Biểu Đồ Tròn Doanh Thu Hằng Tháng", jChart,true);
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
