package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DBConnection.DBConnection;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import java.awt.event.*;

public class TransferTable<Image> extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox cbb_chuyenban;
	static int v=0;
	PreparedStatement ps;
	Connection conn;
	ResultSet rs;
	int x=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TransferTable dialog = new TransferTable(v);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TransferTable(int v) {
		setTitle("Chuyển Bàn");
		

		URL url = TransferTable.class.getResource("/Icon/transfertable.png");
		Image img = (Image) Toolkit.getDefaultToolkit().createImage(url);
		this.setIconImage((java.awt.Image) img);
		
		setBounds(570, 230, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lb_chuyenban = new JLabel("Chuyển Bàn");
		lb_chuyenban.setBackground(Color.WHITE);
		lb_chuyenban.setForeground(Color.RED);
		lb_chuyenban.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lb_chuyenban.setBounds(149, 10, 136, 35);
		contentPanel.add(lb_chuyenban);
		
		JLabel lb_ban = new JLabel("Đang Ở Bàn: ");
		lb_ban.setBackground(Color.WHITE);
		lb_ban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_ban.setBounds(68, 78, 111, 22);
		contentPanel.add(lb_ban);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 55, 416, 2);
		contentPanel.add(separator);
		
		JLabel lb_doiban = new JLabel("Chuyển Sang Bàn:");
		lb_doiban.setBackground(Color.WHITE);
		lb_doiban.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lb_doiban.setBounds(68, 136, 161, 22);
		contentPanel.add(lb_doiban);
		
		JLabel lblNewLabel = new JLabel(v+"");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(234, 78, 97, 21);
		contentPanel.add(lblNewLabel);
		
	    cbb_chuyenban = new JComboBox();
	    cbb_chuyenban.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	    cbb_chuyenban.addItem(1);
//	    cbb_chuyenban.addItem(2);
//	    cbb_chuyenban.addItem(3);
//	    cbb_chuyenban.addItem(4);
//	    cbb_chuyenban.addItem(5);
//	    cbb_chuyenban.addItem(6);
		cbb_chuyenban.setModel(new DefaultComboBoxModel(new String[] {"1", "2","3","4","5","6"}));
	   
		cbb_chuyenban.setBackground(Color.WHITE);
		cbb_chuyenban.setBounds(254, 132, 63, 30);
		contentPanel.add(cbb_chuyenban);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton bt_quaylai = new JButton("Quay Lại");
				bt_quaylai.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						TransferTable.this.setVisible(false);
					}
					
				});
				bt_quaylai.setBackground(Color.WHITE);
				bt_quaylai.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_quaylai.setActionCommand("OK");
				buttonPane.add(bt_quaylai);
				getRootPane().setDefaultButton(bt_quaylai);
			}
			{
				JButton bt_xacnhan = new JButton("Xác Nhận");
				bt_xacnhan.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DBConnection cnn = new DBConnection();				    
					    String b= (String) cbb_chuyenban.getItemAt(cbb_chuyenban.getSelectedIndex());
					    x = Integer.parseInt((String) b);
						String sql = "select * from detailbill Where Maban= '"+x+"'";
						conn = cnn.connectSQL();
					    try {						
					    	ps = conn.prepareStatement(sql);
							ResultSet rs = ps.executeQuery();
							if (rs != null) {
								int i=0;
								while(rs.next()) {
									i++;
								}
								if(i>0) {
									JOptionPane.showMessageDialog(TransferTable.this,"Bàn Này Đã Được Đặt");
								
							}else {
								int n = JOptionPane.showConfirmDialog(TransferTable.this, "Bạn Có Chắc Chắn Muốn Đổi Bàn ?", "Chuyển Bàn", JOptionPane.YES_NO_OPTION);
								if (n==JOptionPane.YES_OPTION) {
									
									String sql2= "Update DetailBill set Maban= '"+x+"' where MaBan='"+v+"'";
									conn = cnn.connectSQL();
									try {
										int record = cnn.executeDB("Update DetailBill set Maban= '"+x+"' where MaBan='"+v+"'");
										if (record >0) {
											JOptionPane.showMessageDialog(TransferTable.this, "Chuyển Bàn Thành Công");
											TransferTable.this.setVisible(false);
										}else {
											JOptionPane.showMessageDialog(TransferTable.this, "Chuyển Bàn Thất Bại ");
										}
								  }catch (Exception e3) {
									// TODO: handle exception
									  e3.printStackTrace();
								 }
								}
							}
					    }
					    
					}
					    catch (Exception e3) {
							// TODO: handle exception
					    	e3.printStackTrace();
						}
					}
				});
				bt_xacnhan.setFont(new Font("Tahoma", Font.PLAIN, 18));
				bt_xacnhan.setBackground(Color.WHITE);
				bt_xacnhan.setActionCommand("Cancel");
				buttonPane.add(bt_xacnhan);
			}
		}
		setVisible(true);
	}
}
