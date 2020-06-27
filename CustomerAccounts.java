import java.sql.SQLException;

/**
 * <h1><strong>CustomerAccounts</strong></h1>
 * <h2>A collection of methods that enables Customers to manage their account</h2>
 */
public class CustomerAccounts {
	private CustomerAccountsJDBC customerJDBC = new CustomerAccountsJDBC();

	public CustomerAccounts() throws SQLException, ClassNotFoundException {

	}
	public boolean createCustomerAccount(CustomerAccount newCustomerAccount) throws SQLException {
		return customerJDBC.createCustomerAccount(newCustomerAccount);

	}
	public CustomerAccount searchCustomerAccount(int customerID) throws SQLException {
		return customerJDBC.searchCustomerAccount(customerID);
	}
	public CustomerAccount logIn(String username, String password) throws SQLException {
		return customerJDBC.logIn(username, password);
	}

	public boolean editCustomerAccount(CustomerAccount account) throws SQLException
	{
		return customerJDBC.editCustomerAccount(account);
	}
	
}