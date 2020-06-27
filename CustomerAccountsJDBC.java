import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Scanner;
public class CustomerAccountsJDBC 
{
	private static Statement stmt = null;
	private static Connection conn = null;
	
	public CustomerAccountsJDBC()  throws SQLException, ClassNotFoundException
	{
		Class.forName(DBInfo.JDBC_DRIVER);
		conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
		stmt = conn.createStatement();
	}
	
	public boolean createCustomerAccount(CustomerAccount newCustomerAccount) throws SQLException
	{
		String sql = "INSERT INTO CustomerAccount(CUSTOMER_FNAME, CUSTOMER_LNAME, CUSTOMER_USERNAME, CUSTOMER_PASSWORD, CUSTOMER_ADDRESS, "
					+ "CUSTOMER_CITY, CUSTOMER_STATE, CUSTOMER_ZIP, CUSTOMER_EMAIL, CUSTOMER_CCNUM)"
					+ "VALUES ("
					+ "'" + newCustomerAccount.getCustomerFirstName() + "', " 
					+ "'" + newCustomerAccount.getCustomerLastName() +  "', "
					+ "'"+ newCustomerAccount.getCustomerUsername() + "', "
					+ "'"+ newCustomerAccount.getCustomerPassword() + "', " 
					+ "'"+ newCustomerAccount.getCustomerAddress() + "', "
					+ "'"+ newCustomerAccount.getCustomerCity() + "', "
					+ "'"+ newCustomerAccount.getCustomerState() + "', "
					+  newCustomerAccount.getCustomerZipCode() + ", "
					+ "'"+ newCustomerAccount.getCustomerEmail()+ "'" + ", "
					+ "'"+ newCustomerAccount.getCustomerCCNum()+"' );";
			
			stmt.executeUpdate(sql);
		return true;
	}
	
	public CustomerAccount logIn(String username, String password) throws SQLException
	{
		boolean isLoggedIn = false;
		String usernameSQL = "";
		String passwordSQL = "";
		CustomerAccount account = null;
		String sql = "SELECT * FROM CustomerAccount WHERE CUSTOMER_USERNAME = '" + username + "';";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
	   {
		   usernameSQL = rs.getString("CUSTOMER_USERNAME");
		   passwordSQL = rs.getString("CUSTOMER_PASSWORD");
		   int customerID = rs.getInt("CUSTOMER_ID");
		   String fName = rs.getString("CUSTOMER_FNAME");
		   String lName = rs.getString("CUSTOMER_LNAME");
		   String email = rs.getString("CUSTOMER_EMAIL");
		   String address = rs.getString("CUSTOMER_ADDRESS");
		   String city = rs.getString("CUSTOMER_CITY");
		   String state = rs.getString("CUSTOMER_STATE");
		   String zip = rs.getString("CUSTOMER_ZIP");
		   String cc = rs.getString("CUSTOMER_CCNUM");
		   account = new CustomerAccount(customerID, fName, lName, usernameSQL, passwordSQL, email, address, city, state, zip, cc);
	   }
	   if (usernameSQL.equals(username) && passwordSQL.equals(password))
	   {
		   isLoggedIn = true;
	   }
	   else
	   {
		   isLoggedIn = false;
	   }
		if (!isLoggedIn)
		{
			return null;
		}
		return account;
	}

	public CustomerAccount searchCustomerAccount(int customerID)throws SQLException
	{
		CustomerAccount searchedAccount = null;
		String sql =("SELECT * FROM CustomerAccount WHERE CUSTOMER_ID = " + customerID);
		ResultSet rs = stmt.executeQuery(sql);

		while(rs.next())
		{
			int customer_ID = rs.getInt("CUSTOMER_ID");
			String Fname = rs.getString("CUSTOMER_FNAME");
			String Lname = rs.getString("CUSTOMER_LNAME");
			String Address = rs.getString("CUSTOMER_ADDRESS");
			String City = rs.getString("CUSTOMER_CITY");
			String State = rs.getString("CUSTOMER_STATE");
			String Zip = rs.getString("CUSTOMER_ZIP");
			String Email = rs.getString("CUSTOMER_EMAIL");
			String Username = rs.getString("CUSTOMER_USERNAME");
			String Password = rs.getString("CUSTOMER_PASSWORD");
			String CCNUM = rs.getString("CUSTOMER_CCNUM");

			searchedAccount = new CustomerAccount(customer_ID, Fname, Lname,
					Username, Password, Email, Address, City, State, Zip, CCNUM);
		}

		rs.close();
		return searchedAccount;
	}

	public boolean editCustomerAccount(CustomerAccount account) throws SQLException
	{
		String sql = "UPDATE CustomerAccount SET " +
				"CUSTOMER_FNAME='"+account.getCustomerFirstName()+"'," +
				"CUSTOMER_LNAME='"+account.getCustomerLastName()+"'," +
				"CUSTOMER_USERNAME='"+account.getCustomerUsername()+"'," +
				"CUSTOMER_PASSWORD='"+account.getCustomerPassword()+"'," +
				"CUSTOMER_EMAIL='"+account.getCustomerEmail()+"',"+
				"CUSTOMER_ADDRESS='"+account.getCustomerAddress()+"',"+
				"CUSTOMER_CITY='"+account.getCustomerCity()+"',"+
				"CUSTOMER_STATE='"+account.getCustomerState()+"',"+
				"CUSTOMER_ZIP='"+account.getCustomerZipCode()+"',"+
				"CUSTOMER_CCNUM='"+account.getCustomerCCNum()+"' "+
				"WHERE CUSTOMER_ID= " + account.getID() + ";";
		stmt.executeUpdate(sql);

		return true;
	}
}
