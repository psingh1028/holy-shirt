/**
 * <h1><strong>CustomerAccount</strong></h1>
 * <h2>A collection of mutators and accessors that enables customers to store and update their information</h2>
 */
public class CustomerAccount 
{
	int ID ;
	String customer_FName="";
	String customer_LName="";
	String customer_Username="";
	String customer_Password="";
	String customer_Email="";
	String customer_Address="";
	String customer_City="";
	String customer_State="";
	String customer_ZipCode="";
	String customer_CCNum="0000000000000000";
	
	public CustomerAccount(int id, String fname, String lname, String username, String password, String email, String address, 
			String city, String state, String zip, String cc)
	{
		ID = id;
		customer_FName = fname;
		customer_LName = lname;
		customer_Username=username;
		customer_Password=password;
		customer_Email=email;
		customer_Address=address;
		customer_City=city;
		customer_State=state;
		customer_ZipCode=zip;
		customer_CCNum=cc;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public void setID(int id)
	{
		ID = id;
	}
	
	/**
	 * <h2><strong>Get Customer's First Name</strong></h2>
	 * <strong>Description:</strong> retrieves customer's first name from the system
	 * @return customer's first name
	 */
	public String getCustomerFirstName()
	{
		return customer_FName;
	}
	/**
	 *  <h2><strong>Get Customer's Last Name</strong></h2>
	 * <strong>Description:</strong> retrieves customer's last name from the system
	 * @return customer's last name
	 */
	public String getCustomerLastName()
	{
		return customer_LName;
	}
	/**
	 *  <h2><strong>Get Customer's Username</strong></h2>
	 * <strong>Description:</strong> retrieves customer's username from the system
	 * @return customer's username
	 */
	public String getCustomerUsername()
	{
		return customer_Username;
	}
	/**
	 *  <h2><strong>Get Customer's Password</strong></h2>
	 * <strong>Description:</strong> retrieves customer's password from the system
	 * @return customer's password used to log into the system
	 */
	public String getCustomerPassword()
	{
		return customer_Password;
	}
	/**
	 *  <h2><strong>Get Customer's Email</strong></h2>
	 * <strong>Description:</strong> retrieves customer's email from the system
	 * @return customer's email
	 */
	public String getCustomerEmail()
	{
		return customer_Email;
	}
	/**
	 *  <h2><strong>Get Customer's Address</strong></h2>
	 * <strong>Description:</strong> retrieves customer's street address from the system
	 * @return customer's street address
	 */
	public String getCustomerAddress()
	{
		return customer_Address;
	}
	public String getCustomerState()
	{
		return customer_State;
	}
	public String getCustomerCity()
	{
		return customer_City;
	}
	public String getCustomerZipCode()
	{
		return customer_ZipCode;
	}
	public String getCustomerCCNum()
	{
		return customer_CCNum;
	}
	/**
	 *  <h2><strong>Set Customer's First Name</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param firstname customer's first name
	 */
	public void setCustomerFirstName(String firstname)
	{
		customer_FName=firstname;
	}
	/**
	 *<h2><strong>Set Customer's Last Name</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param lastname customers last name
	 */
	public void setCustomerLastName(String lastname)
	{
		customer_LName=lastname;
	}
	/**
	 * <h2><strong>Set Customer's Username</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param username customer's username to log into the system
	 */
	public void setCustomerUsername(String username)
	{
		customer_Username=username;
	}
	/**
	 * <h2><strong>Set Customer's Password</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param password customer's password to log into the system
	 */
	public void setCustomerPassword(String password)
	{
		customer_Password=password;
	}
	/**
	 * <h2><strong>Set Customer's Email</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param email customer's email
	 */
	public void setCustomerEmail(String email)
	{
		customer_Email=email;
	}
	/**
	 * <h2><strong>Set Customer's Address</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param address customer's street address
	 */
	public void setCustomerAddress(String address)
	{
		customer_Address=address;
	}
	/**
	 * <h2><strong>Set Customer's Credit Card Number</strong></h2>
	 * <strong>Description:</strong>Sets the instance variable equal to what the customer inputs
	 * @param creditCardNum customer's credit card number
	 */
	public void setCustomerCreditCardNum(String creditCardNum)
	{
		customer_CCNum=creditCardNum;
	}
	
	@Override
	public String toString()
	{
		String str = "Customer Account Information: \n";
		str += customer_FName + " " + customer_LName + "\n";
		str += customer_Address + "\n";
		str += customer_City + ", " + customer_State + "  " + customer_ZipCode + "\n";
		str += customer_Email + "\n";
		str += "Username: " + customer_Username + ", Password: " + customer_Password + "\n";
		str += "CC: " + customer_CCNum + "\n";
		return str;
	}

}
