package jCashWithDB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.Random;

public class ReceiptFrame extends JFrame {

	private JPanel contentPane;
	public ReceiptFrame(String username,String username1,String transaction,int Ammount) {
	
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 270, 339);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(68, 174, 171));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("OFFICIAL RECEIPT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewLabel.setBounds(10, 11, 234, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRef = new JLabel("REFERENCE NO: ");
		lblRef.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRef.setForeground(new Color(255, 255, 255));
		lblRef.setBounds(10, 286, 114, 14);
		contentPane.add(lblRef);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 234, 234);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblUser = new JLabel("USERNAME");
		lblUser.setFont(new Font("Arial", Font.BOLD, 14));
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setBounds(10, 5, 214, 41);
		panel.add(lblUser);
		
		JLabel lblTransaction = new JLabel("TRANSACTION");
		lblTransaction.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransaction.setBounds(0, 42, 234, 46);
		panel.add(lblTransaction);
		
		JLabel lblAmmount = new JLabel("AMMOUNT");
		lblAmmount.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAmmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmmount.setBounds(0, 88, 234, 17);
		panel.add(lblAmmount);
		
		JLabel lblUsername1 = new JLabel("USERNAME");
		lblUsername1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUsername1.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername1.setBounds(0, 129, 234, 29);
		panel.add(lblUsername1);
		
		JLabel lblTo = new JLabel("TO:");
		lblTo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTo.setBounds(0, 115, 234, 14);
		panel.add(lblTo);
		
		JLabel lblDate = new JLabel("DATE");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDate.setBounds(0, 220, 234, 14);
		panel.add(lblDate);
		
		JLabel lblRef1 = new JLabel("New label");
		lblRef1.setHorizontalAlignment(SwingConstants.LEFT);
		lblRef1.setForeground(new Color(255, 255, 255));
		lblRef1.setBounds(134, 286, 110, 14);
		contentPane.add(lblRef1);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				LocalDate date = LocalDate.now();
				Random rnd = new Random();
				int randomNumber = rnd.nextInt(10000000);
				lblUser.setText(username);
				lblTransaction.setText(transaction);
				lblAmmount.setText("₱"+Ammount);
				lblDate.setText(""+date);
				lblRef1.setText(""+randomNumber);
				if(transaction.equals("SUCCESFULLY SENT AMMOUNT:")) 
				{
					lblTo.setText("TO:");
					lblUsername1.setText(username1);
				}
				else 
				{
					lblTo.setText("");
					lblUsername1.setText("");
				}
				
			}
		});
		
	}
}
