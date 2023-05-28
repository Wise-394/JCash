package jCashWithDB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPanel extends JFrame {

	private JPanel contentPane;
	private JTextField tfUser;
	private JTextField tfPass;
	Methods method = new Methods();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPanel frame = new LoginPanel();
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
	public LoginPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 276, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(68, 174, 171));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel txtJcashLogo = new JLabel("WELCOME TO JCASH");
		txtJcashLogo.setHorizontalAlignment(SwingConstants.CENTER);
		txtJcashLogo.setFont(new Font("Arial Black", Font.BOLD, 16));
		txtJcashLogo.setForeground(Color.BLACK);
		txtJcashLogo.setBounds(10, 11, 240, 43);
		contentPane.add(txtJcashLogo);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(24, 77, 75));
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(tfUser.getText() != null  && tfPass.getText() != null) 
			{
				
				if(method.loginToDB(tfUser.getText(),tfPass.getText())) 
				{
					 MainFrame mf = new MainFrame();
				     mf.setVisible(true);
				     
				     dispose(); 
				}
				else 
				{
					System.exit(0);
				}
				 
			}	
			}
		});
		btnLogin.setBounds(84, 148, 89, 23);
		contentPane.add(btnLogin);
		
		tfUser = new JTextField();
		tfUser.setBounds(20, 65, 210, 20);
		contentPane.add(tfUser);
		tfUser.setColumns(10);
		
		tfPass = new JTextField();
		tfPass.setColumns(10);
		tfPass.setBounds(20, 106, 210, 20);
		contentPane.add(tfPass);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUser.setBounds(20, 50, 89, 14);
		contentPane.add(lblUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(20, 85, 89, 23);
		contentPane.add(lblPassword);
		
		JLabel lblnewToJcash = new JLabel("New to Jcash?");
		lblnewToJcash.setFont(new Font("Arial", Font.PLAIN, 12));
		lblnewToJcash.setHorizontalAlignment(SwingConstants.RIGHT);
		lblnewToJcash.setBounds(10, 227, 127, 34);
		contentPane.add(lblnewToJcash);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user = JOptionPane.showInputDialog("REGISTER \nENTER YOUR USERNAME");
				String pass = JOptionPane.showInputDialog("REGISTER \nENTER YOUR PASS");
				if(method.register(user, pass)) 
				{
					JOptionPane.showMessageDialog(null,"SUCCESS");
				}
				else 
				{
					JOptionPane.showMessageDialog(null,"ERROR");
				}
			}
		});
		btnRegister.setForeground(Color.WHITE);
		btnRegister.setFont(new Font("Arial", Font.BOLD, 15));
		btnRegister.setBackground(new Color(24, 77, 75));
		btnRegister.setBounds(147, 227, 103, 23);
		contentPane.add(btnRegister);
	}

	
}
