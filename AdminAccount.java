/**
 * <h1><strong>AdminAccount</strong></h1>
 * <h2>A collection of mutators and accessors that enables admins to store and update their information</h2>
 */
public class AdminAccount
{
	String admin_Fname= "";
	String admin_Lname= "";
	String admin_Username= "";
	String admin_Password= "";
	String admin_Email= "";
	int admin_ID= 0;

	public AdminAccount(String fname, String lname, String username, String password, String email, int id)
	{
		admin_Fname = fname;
		admin_Lname = lname;
		admin_Username=username;
		admin_Password=password;
		admin_Email=email;
		admin_ID= id;
	}
	/**
	 * <h2><strong>Get Admin's First Name</strong></h2>
	 * <strong>Description:</strong>  retrieves Admin's first name from the system
	 * @return admin's first name
	 */
	public String getAdminFirstName()
	{
		return admin_Fname;
	}
	/**
	 * <h2><strong>Get Admin's Last Name</strong></h2>
	 * <strong>Description:</strong>  retrieves Admin's last name from the system
	 * @return admin's last name
	 */
	public String getAdminLastName()
	{
		return admin_Lname;
	}
	/**
	 * <h2><strong>Get Admin's Username</strong></h2>
	 * <strong>Description:</strong>  Retrieves admin's username from the system
	 * @return admin's username
	 */
	public String getAdminUsername()
	{
		return admin_Username;
	}
	/**
	 * <h2><strong>Get Admin's Password</strong></h2>
	 * <strong>Description:</strong>  Retrieves admin's password from the system
	 * @return admin's password
	 */
	public String getAdminPassword()
	{
		return admin_Password;
	}
	/**
	 * <h2><strong>Get Admin's Email</strong></h2>
	 * <strong>Description:</strong>  retrieves admin's email from the system
	 * @return admin's email
	 */
	public String getAdminEmail()
	{
		return admin_Email;
	}

	public int getID()
	{
		return admin_ID;
	}
	/**
	 * <h2><strong>Set Admin's First Name</strong></h2>
	 * <strong>Description:</strong>  Sets the instance variable equal to what the admin inputs
	 * @param firstname admins first name
	 */
	public void setAdminFirstName(String firstname)
	{
		admin_Fname=firstname;
	}
	/**
	 * <h2><strong>Set Admin's Last Name</strong></h2>
	 * <strong>Description:</strong>  Sets the instance variable equal to what the admin inputs
	 * @param lastname admin's last name
	 */
	public void setAdminLastName(String lastname)
	{
		admin_Lname=lastname;
	}
	/**
	 * <h2><strong>Set Admin's Username</strong></h2>
	 * <strong>Description:</strong>  Sets the instance variable equal to what the admin inputs
	 * @param username admins username to log into the system
	 */
	public void setAdminUsername(String username)
	{
		admin_Username=username;
	}
	/**
	 * <h2><strong>Set Admin's Password</strong></h2>
	 * <strong>Description:</strong>  Sets the instance variable equal to what the admin inputs
	 * @param password admin's password to log into the system
	 */
	public void setAdminPassword(String password)
	{
		admin_Password=password;
	}
	/**
	 * <h2><strong>Set Admin's Email</strong></h2>
	 * <strong>Description:</strong>  Sets the instance variable equal to what the admin inputs
	 * @param email admins company email
	 */
	public void setAdminEmail(String email)
	{
		admin_Email=email;
	}
	public void setID(int id)
	{
		admin_ID=id;
	}
	public String toString()
	{
		return "Admin first name: " + admin_Fname +"\nAdmin last name: "+ admin_Lname + "\nAdmin Username: " + admin_Username
				+ "\nAdmin Password: " + admin_Password + "\nAdmin Email: " + admin_Email;
	}
}
