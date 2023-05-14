package jCash1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		JLabel textBalance = new JLabel("");
		MethodsAndVariables jCash = new MethodsAndVariables();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				textBalance.setText(jCash.loadBalance()+"");
			}
		});
		
	
		
		setTitle("JCash");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 360);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel textGreetUser = new JLabel("Hello, User!");
		textGreetUser.setForeground(new Color(0, 0, 205));
		textGreetUser.setFont(new Font("Tahoma", Font.BOLD, 15));
		textGreetUser.setBounds(10, 11, 147, 28);
		contentPane.add(textGreetUser);
		
		JButton btnReset = new JButton("RESET");
		btnReset.setBackground(new Color(0, 0, 255));
		btnReset.setForeground(new Color(255, 255, 255));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jCash.onClickReset();
				float moneyF = jCash.getBalance();
				textBalance.setText(moneyF +"");
				
			}
		});
		btnReset.setBounds(160, 11, 84, 28);
		contentPane.add(btnReset);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(65, 105, 225));
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
		
		textBalance.setForeground(new Color(255, 255, 255));
		textBalance.setFont(new Font("Tahoma", Font.BOLD, 20));
		textBalance.setHorizontalAlignment(SwingConstants.LEFT);
		textBalance.setBounds(45, 25, 179, 26);
		panel.add(textBalance);
		
		JButton btnCashIn = new JButton("Cash In");
		btnCashIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			jCash.onClickCashIn();
			float moneyF = jCash.getBalance();
			textBalance.setText(moneyF +"");
			}
		});
		btnCashIn.setForeground(new Color(255, 255, 255));
		btnCashIn.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnCashIn.setBackground(new Color(0, 0, 255));
		btnCashIn.setBounds(10, 123, 234, 38);
		contentPane.add(btnCashIn);
		
		JButton btnCashOut = new JButton("Cash Out");
		btnCashOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jCash.onClickCashOut();
				float moneyF = jCash.getBalance();
				textBalance.setText(moneyF +"");
			}
		});
		btnCashOut.setForeground(new Color(255, 255, 255));
		btnCashOut.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnCashOut.setBackground(Color.BLUE);
		btnCashOut.setBounds(10, 172, 234, 38);
		contentPane.add(btnCashOut);
		
		JButton btnHelp = new JButton("HELP");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"1.Cash in - click the cash in button and enter ammount to cash in"
						+ "\n2.Cash out - click the cash out button and enter ammount to cash out"
						+ "\n3.Reset button - clicking the reset button will empty your balance");
			}
		});
		btnHelp.setForeground(new Color(255, 255, 255));
		btnHelp.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnHelp.setBackground(Color.BLUE);
		btnHelp.setBounds(10, 221, 234, 38);
		contentPane.add(btnHelp);
		
		JButton btnAboutUs = new JButton("ABOUT US");
		btnAboutUs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"JCash is a convenient and lightweight money management system that allows you to easily\r\n"
						+ "handle your financial transactions. With JCash, you can effortlessly cash in and cash out,\r\n"
						+ "enabling you to securely manage your money on the go.\n\n DEVELOPERS:\n "
						+"Jhenn Rod Dumlao \nJoshua Forbile\nRochelle Ann De leon \nPrince Nikko Payopay \nDonniel Cruz \nAngelo Gabat ");
			}
		});
		btnAboutUs.setForeground(new Color(255, 255, 255));
		btnAboutUs.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnAboutUs.setBackground(Color.BLUE);
		btnAboutUs.setBounds(10, 270, 234, 38);
		contentPane.add(btnAboutUs);
	}
}
