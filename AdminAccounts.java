import java.sql.SQLException;

public class AdminAccounts {

	private AdminAccountsJDBC adminJDBC = new AdminAccountsJDBC();

	public AdminAccounts() throws SQLException, ClassNotFoundException {
	}
	public boolean createAdminAccount(AdminAccount newAdminAccount) throws SQLException {

		return adminJDBC.createAdminAccount(newAdminAccount);
	}

	public String displayCustomerAccounts() throws SQLException {
		return adminJDBC.displayCustomerAccounts();
	}

	public boolean deleteAdminAccount(int admin_Id) throws SQLException {
		return adminJDBC.deleteAdminAccount(admin_Id);
	}

	public AdminAccount searchAdminAccount(int adminID) throws SQLException {
		return adminJDBC.searchAdminAccount(adminID);
	}
	public AdminAccount loginAdmin(String username, String password) throws SQLException {
		return adminJDBC.logIn(username, password);
	}
	public  boolean updateAdminPassword(int admin_ID, String admin_Password) throws SQLException {
		return adminJDBC.updateAdminPassword(admin_ID, admin_Password);
	}

	public CustomerAccount searchCustomerAccounts(int customerID) throws SQLException
	{
		return adminJDBC.searchCustomerAccounts(customerID);
	}

}
	
	
