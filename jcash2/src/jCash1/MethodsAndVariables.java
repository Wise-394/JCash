package jCash1;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MethodsAndVariables {
	private float balance = 0;
	private String userName;
	public String balanceFile = "DB/bal.txt";
	public String passwordFile = "DB/pw.txt";
	private String password = "1111";
	
	//get and set methods
	public float getBalance() { return this.balance; }
	public void setBalance(float balanceToSet) { this.balance = balanceToSet; }
	public String getUserName() { return this.userName; }
	public void setUsername(String userNameToSet) { this.userName = userNameToSet; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	//cash in/out method
	public void cashOut(float moneyToCashOut) { this.balance -= moneyToCashOut; 	saveBalance();}
	public void cashIn(float moneyToCashIn) { this.balance += moneyToCashIn; 	saveBalance();}
	
	//method when user clicked buttons
	public void onClickCashOut() 
	{
		String moneyStr = JOptionPane.showInputDialog("Enter money to cash out: ");
		if(moneyStr != null)
		{
			float money = Float.parseFloat(moneyStr);
			if(money <= getBalance()) 
			{
				cashOut(money);
				
			}
			else 
			{
				JOptionPane.showMessageDialog(null, "Error cannot be more than your balance");
			}
		}
		
	}
	
	public void onClickCashIn() 
	{
		String moneyStr = JOptionPane.showInputDialog("Enter money to cash in: ");
		if(moneyStr != null) 
		{			
			float money = Float.parseFloat(moneyStr);
			cashIn(money);
		
		}
	}
	
	public void onClickReset() 
	{
		setBalance(0);
		saveBalance();
		setPassword("1111");
		savePassword();
	}
	
	
	//save and load file
	public void saveBalance() 
	{
		try {
		      FileWriter fileWriter = new FileWriter(balanceFile);
		      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		      bufferedWriter.write(Float.toString(getBalance()));
		      bufferedWriter.close();
		    } catch (IOException e)
			{
		      e.printStackTrace();
		    }
	}
	public float loadBalance()
	{
		float balance = 0;
		 try {
		      FileReader fileReader = new FileReader(balanceFile);
		      BufferedReader bufferedReader = new BufferedReader(fileReader);
		      String floatString = bufferedReader.readLine();
		      balance = Float.parseFloat(floatString);
		      bufferedReader.close();
		      return balance;
		    } catch (IOException e) {
		    
		      e.printStackTrace();
		      return balance;
		    } catch (NumberFormatException e) {
		      e.printStackTrace();
		      return balance;
		    }
		
		
		
	}
	public void savePassword() 
	{
		try {
		      FileWriter fileWriter = new FileWriter(passwordFile);
		      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		      bufferedWriter.write(getPassword());
		      bufferedWriter.close();
		    } catch (IOException e)
			{
		      e.printStackTrace();
		    }
	}
	public String loadPassword()
	{
		String passwordToReturn = null;
		 try {
		      FileReader fileReader = new FileReader(passwordFile);
		      BufferedReader bufferedReader = new BufferedReader(fileReader);
		      passwordToReturn = bufferedReader.readLine();
		      bufferedReader.close();
		      return passwordToReturn;
		    } catch (IOException e) {
		    
		      e.printStackTrace();
		      return passwordToReturn;
		    } catch (NumberFormatException e) {
		      e.printStackTrace();
		      return passwordToReturn;
		    }
	
		}
	 public void register() 
	 {
		 try 
		 {
		String askPassword = JOptionPane.showInputDialog("welcome to jCash \nENTER YOUR NEW PASSWORD");
		if(!askPassword.equals(null))
		{
		setPassword(askPassword);
		savePassword();
		}
		}
		 catch(Exception e)
		{
			 JOptionPane.showMessageDialog(null,"You didnt enter a password \nExiting...");
			 System.exit(0);
		}
	 }
	 
	 public boolean login() 
	 {
		 String askPassword = JOptionPane.showInputDialog("welcome back! \nENTER YOUR PASSWORD");
		 if(askPassword.equals(loadPassword()))
		 {
			 return true;
		 }
		 else 
		 {
			 return false;
		 }
	 }
	
}	












