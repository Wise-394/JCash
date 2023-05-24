package jCashWithDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.sun.jdi.Method;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {
	Methods method = new Methods();

	private JPanel contentPane;

	public MainFrame() 
	{
		
		
		
				
		setTitle("JCash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textGreetUser = new JLabel("Hello, User!");
		textGreetUser.setForeground(new Color(0, 153, 255));
		textGreetUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		textGreetUser.setBounds(10, 11, 147, 28);
		contentPane.add(textGreetUser);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 51, 255));
		panel.setBounds(10, 50, 234, 62);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel textAvailBalance = new JLabel("  Available Balance");
		textAvailBalance.setForeground(new Color(255, 255, 255));
		textAvailBalance.setFont(new Font("Tahoma", Font.BOLD, 11));
		textAvailBalance.setBounds(0, 0, 105, 14);
		panel.add(textAvailBalance);
		
		JLabel textPesoSign = new JLabel("₱");
		textPesoSign.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textPesoSign.setForeground(new Color(255, 255, 255));
		textPesoSign.setHorizontalAlignment(SwingConstants.CENTER);
		textPesoSign.setBounds(10, 25, 46, 26);
		panel.add(textPesoSign);
		
		JLabel textBalance = new JLabel("0");
		textBalance.setHorizontalAlignment(SwingConstants.LEFT);
		textBalance.setForeground(Color.WHITE);
		textBalance.setFont(new Font("Tahoma", Font.BOLD, 20));
		textBalance.setBounds(45, 25, 179, 26);
		panel.add(textBalance);
		
	
		
		JButton btnCashIn = new JButton("Cash In");
		btnCashIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			method.onCLickCashIn();
			String balance = Integer.toString(method.geteBalanceFromDB());
			textBalance.setText(balance);
			}
		});
		btnCashIn.setForeground(new Color(255, 255, 255));
		btnCashIn.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnCashIn.setBackground(new Color(0, 51, 255));
		btnCashIn.setBounds(10, 123, 234, 38);
		contentPane.add(btnCashIn);
		
		JButton btnCashOut = new JButton("Cash Out");
		btnCashOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 method.onCLickCashOut();
				 String balance = Integer.toString(method.geteBalanceFromDB());
				textBalance.setText(balance);
			
			}
		});
		btnCashOut.setForeground(new Color(255, 255, 255));
		btnCashOut.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnCashOut.setBackground(new Color(0, 51, 255));
		btnCashOut.setBounds(10, 172, 234, 38);
		contentPane.add(btnCashOut);
		
		JButton btnSendMoney = new JButton("Send Money");
		btnSendMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 method.onClickSendMoney();
			}
		});
		btnSendMoney.setForeground(new Color(255, 255, 255));
		btnSendMoney.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnSendMoney.setBackground(new Color(0, 51, 255));
		btnSendMoney.setBounds(10, 221, 234, 38);
		contentPane.add(btnSendMoney);
		
		JButton btnAboutUs = new JButton("ABOUT US");
		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"JCash is a convenient and lightweight money management system that allows you to easily\r\n"
						+ "handle your financial transactions. With JCash, you can effortlessly cash in and cash out,\r\n"
						+"\n1.Cash in - click the cash in button and enter ammount to cash in\"\r\n"
						+ "\n2.Cash out - click the cash out button and enter ammount to cash out\r\n"
						+ "	\n3.Reset button - clicking the reset button will empty your balance" + "enabling you to securely manage your money on the go.\n\n DEVELOPERS:\n "
						+"Jhenn Rod Dumlao \nJoshua Forbile\nRochelle Ann De leon \nPrince Nikko Payopay \nDonniel Cruz \nAngelo Gabat ");
			}
		});
		btnAboutUs.setForeground(new Color(255, 255, 255));
		btnAboutUs.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnAboutUs.setBackground(new Color(0, 51, 255));
		btnAboutUs.setBounds(10, 270, 234, 38);
		contentPane.add(btnAboutUs);
	
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				int balance = method.geteBalanceFromDB();
                textBalance.setText(String.valueOf(balance));
                textGreetUser.setText("Hello," + method.getCurrentUser());
			}
		});
		
	}
}