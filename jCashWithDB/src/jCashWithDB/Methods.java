package jCashWithDB;

import java.sql.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

//DATABASE INFO
//DB NAME:jcashnewdb
//TABLE NAME: userandpass
//COLUMNS: username(20),password(20),balance
//LOCALHOST URL:"jdbc:mysql://localhost:3306/jcashnewdb
//ONLINE URLHost:
/*
 sql12.freesqldatabase.com
Database name: sql12620100
Database user: sql12620100
Database password: hM5CjeARjz
Port number: 3306
*/
//jdbc;mysql;//sql6freesqldatabase.com:3306/sql12620100
//DB INFOS
/*
private static final String DB_URL = "jdbc:mysql://localhost:3306/jcashnewdb";
private static final String DB_USERNAME = "root";
private static final String DB_PASSWORD = "";
*/
public class Methods {

	private static final String DB_URL = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12620100";
    private static final String DB_USERNAME = "sql12620100";
    private static final String DB_PASSWORD = "hM5CjeARjz";
    private static int balance = 0;
    public static int getBalance() {return balance;	}	
	public static void setBalance(int balance) {Methods.balance = balance;}
	private static String CurrentUser;
	public static String getCurrentUser() {	return CurrentUser;	}
	public static void setCurrentUser(String currentUser) {CurrentUser = currentUser;}
     
    Scanner scn = new Scanner(System.in);  
	public boolean loginToDB(String username, String password) 
	{
		  try 
		{

			Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
			PreparedStatement statement = connection.prepareStatement("SELECT * FROM userandpass WHERE username = ?");
	        statement.setString(1, username);	
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) 
	        {
                String DBPassword = resultSet.getString("password");

                if (password.equals(DBPassword)) {
                    JOptionPane.showMessageDialog(null, "SUCCESS");
                    setCurrentUser(username);
                    connection.close();
                    return true;
                } else 
                {
                	 JOptionPane.showMessageDialog(null, "Incorrect password!");
                	 return false;
                }
            } else
            {
            	 JOptionPane.showMessageDialog(null, "User does not exist in database ");
            	 return false;
            }

		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
    
    }
	    public boolean register(String username, String password) {
	        

	        try {
	        	Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
	            PreparedStatement statement = connection.prepareStatement("INSERT INTO userandpass (username, password, balance) VALUES (?, ?, 0)");
	            statement.setString(1, username);
	            statement.setString(2, password);

	            int registeredAcc = statement.executeUpdate();

	            statement.close();
	            connection.close();

	            return registeredAcc > 0;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
	    
	    public void onCLickCashIn() {
	    	String moneyToCashInSTR = JOptionPane.showInputDialog("ENTER MONEY TO CASH IN");
	    	if (moneyToCashInSTR != null) {
	    	    try {
	    	        int moneyToCashIn = Integer.parseInt(moneyToCashInSTR);

	    	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	    	            PreparedStatement statement = connection.prepareStatement("UPDATE userandpass SET balance = balance + ? WHERE username = ?");
	    	            statement.setInt(1, moneyToCashIn);
	    	            statement.setString(2, getCurrentUser());

	    	            int rowsUpdated = statement.executeUpdate();

	    	            if (rowsUpdated > 0) {
	    	                JOptionPane.showMessageDialog(null, "Cash in successful!");
	    	                setBalance(getBalance() + moneyToCashIn);
	    	                ReceiptFrame rcp = new ReceiptFrame(getCurrentUser(), " ", "SUCCESSFULLY CASH IN:", moneyToCashIn);
	    	                rcp.setVisible(true);
	    	                connection.close();
	    	            } else {
	    	                JOptionPane.showMessageDialog(null, "ERROR");
	    	            }
	    	        } catch (SQLException e) {
	    	            e.printStackTrace();
	    	        }
	    	    } catch (NumberFormatException e) {
	    	        JOptionPane.showMessageDialog(null, "ERROR: Invalid input. Please enter a valid number.");
	    	    }
	    	}
	    }
	    public void onCLickCashOut() {
	        String moneyToCashOutSTR = JOptionPane.showInputDialog("ENTER MONEY TO CASH OUT");
	        if (moneyToCashOutSTR != null) {
	            try {
	                int moneyToCashOut = Integer.parseInt(moneyToCashOutSTR);
	                if (moneyToCashOut <= getBalance()) {
	                    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	                        PreparedStatement statement = connection.prepareStatement("UPDATE userandpass SET balance = balance - ? WHERE username = ?");
	                        statement.setInt(1, moneyToCashOut);
	                        statement.setString(2, getCurrentUser());

	                        int rowsUpdated = statement.executeUpdate();

	                        if (rowsUpdated > 0) {
	                            JOptionPane.showMessageDialog(null, "Cash out successful!");
	                            setBalance(getBalance() - moneyToCashOut);
	                            ReceiptFrame rcp = new ReceiptFrame(getCurrentUser(), " ", "SUCCESSFULLY CASH OUT:", moneyToCashOut);
	                            rcp.setVisible(true);
	                            connection.close();
	                        } else {
	                            JOptionPane.showMessageDialog(null, "ERROR: INSUFFICIENT BALANCE");
	                        }
	                    } catch (SQLException e) {
	                        e.printStackTrace();
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null, "ERROR: NOT ENOUGH BALANCE");
	                }
	            } catch (NumberFormatException e) {
	                JOptionPane.showMessageDialog(null, "ERROR: Invalid input.  Please enter a valid number.");
	            }
	        }
	    }
	    
	        public void onClickSendMoney()
	        {
	            String recipientUsername = JOptionPane.showInputDialog("Enter recipient's username:");
	            if (recipientUsername != null) 
	            {
	                String amountToSendStr = JOptionPane.showInputDialog("Enter amount to send:");
	                if (amountToSendStr != null) 
	                {
	                    try
	                    {
	                        int amountToSend = Integer.parseInt(amountToSendStr);
	                        if (amountToSend <= getBalance()) 
	                        {
	                            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	                                connection.setAutoCommit(false);  
	                                PreparedStatement senderStatement = connection.prepareStatement("UPDATE userandpass SET balance = balance - ? WHERE username = ?");
	                                senderStatement.setInt(1, amountToSend);
	                                senderStatement.setString(2, getCurrentUser());
	                                int rowsUpdatedSender = senderStatement.executeUpdate();
	                                PreparedStatement recipientStatement = connection.prepareStatement("UPDATE userandpass SET balance = balance + ? WHERE username = ?");
	                                recipientStatement.setInt(1, amountToSend);
	                                recipientStatement.setString(2, recipientUsername);
	                                int rowsUpdatedRecipient = recipientStatement.executeUpdate();
	                                if (rowsUpdatedSender > 0 && rowsUpdatedRecipient > 0) {
	                                    connection.commit(); 
	                                    setBalance(getBalance() - amountToSend);
	                                    JOptionPane.showMessageDialog(null, "Money sent successfully!");
	                                    ReceiptFrame rcp = new ReceiptFrame(getCurrentUser(),recipientUsername, "SUCCESFULLY SENT AMMOUNT:", amountToSend);
	            		                rcp.setVisible(true);
	            		                connection.close();
	                                } else {
	                                    connection.rollback(); 
	                                    JOptionPane.showMessageDialog(null, "ERROR: Failed to send money.");
	                                }
	                            } catch (SQLException e) {
	                                e.printStackTrace();
	                                JOptionPane.showMessageDialog(null, "ERROR: Database error occurred.");
	                            }
	                        } else {
	                            JOptionPane.showMessageDialog(null, "ERROR: Insufficient balance");
	                        }
	                    } catch (NumberFormatException e) {
	                        JOptionPane.showMessageDialog(null, "ERROR: Invalid amount");
	                    }
	                }
	            }
	        }
	    public int geteBalanceFromDB() {
	        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
	            PreparedStatement statement = connection.prepareStatement("SELECT balance FROM userandpass WHERE username = ?");
	            statement.setString(1, getCurrentUser());

	            ResultSet resultSet = statement.executeQuery();
	            if (resultSet.next()) {
	                balance = resultSet.getInt("balance");
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return balance;
	    }
	    public void loadBalance() 
	    {
	    	setBalance(geteBalanceFromDB());
	    }
}
