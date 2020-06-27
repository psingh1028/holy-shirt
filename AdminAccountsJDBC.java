import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminAccountsJDBC
{
    private static Statement stmt = null;
    private static Connection conn = null;

    public AdminAccountsJDBC() throws SQLException, ClassNotFoundException
    {
        Class.forName(DBInfo.JDBC_DRIVER);
        conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
        stmt = conn.createStatement();
    }

    public boolean createAdminAccount(AdminAccount newAdminAccount) throws SQLException
    {
        String sql = "INSERT INTO AdminAccount(ADMIN_FNAME, ADMIN_LNAME, ADMIN_USERNAME, ADMIN_PASSWORD, ADMIN_EMAIL)"
                + "VALUES ("
                + "'" + newAdminAccount.getAdminFirstName() + "', "
                + "'" + newAdminAccount.getAdminLastName() +  "', "
                + "'"+ newAdminAccount.getAdminUsername() + "', "
                + "'"+ newAdminAccount.getAdminPassword() + "', "
                + "'"+ newAdminAccount.getAdminEmail()+ "' );";

        stmt.executeUpdate(sql);
        return true;
    }
    public boolean updateAdminPassword(int admin_ID, String admin_Password) throws SQLException
    {
        String sql = "UPDATE AdminAccount" + " SET ADMIN_PASSWORD = '" + admin_Password + "'\nWHERE AdminAccount.ADMIN_ID = " + admin_ID + ";";
        stmt.executeUpdate(sql);
        return true;
    }

    public String displayCustomerAccounts() throws SQLException
    {
        String str = "";
        String sql;
        sql = "SELECT * "
                + "FROM CustomerAccount;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next())
        {
            int CustomerId = rs.getInt("CUSTOMER_ID");
            String Fname = rs.getString("CUSTOMER_FNAME");
            String Lname = rs.getString("CUSTOMER_LNAME");
            String Address = rs.getString("CUSTOMER_ADDRESS");
            String City = rs.getString("CUSTOMER_CITY");
            String State = rs.getString("CUSTOMER_STATE");
            int Zip = rs.getInt("CUSTOMER_ZIP");
            String Email = rs.getString("CUSTOMER_EMAIL");
            String Username = rs.getString("CUSTOMER_USERNAME");
            String Password = rs.getString("CUSTOMER_PASSWORD");
            String CCNUM = rs.getString("CUSTOMER_CCNUM");

            str += "\nID : " + CustomerId + "\nFirst Name : " + Fname + "\nLast Name : " + Lname +  "\nAddress : " + Address + "\nCity : "  + City+ "\nState : " + State + " \nZIP : " + Zip + "\nEmail : " +
                    Email + "\nUsername : " + Username + "\nPassword : " + Password+ "\nCCNUM : " + CCNUM + "\n===========================";

        }
        rs.close();
        return str;
    }

    public boolean deleteAdminAccount(int admin_Id) throws SQLException
    {
        String sql = "SELECT COUNT(ADMIN_ID) FROM AdminAccount WHERE ADMIN_ID = " + admin_Id + ";";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        if (rs.getInt(1) == 0)
        {
            return false;
        }
        sql = "DELETE FROM AdminAccount"
                +"\n"+ "WHERE ADMIN_ID = " +admin_Id + ";";
        stmt.executeUpdate(sql);
        return true;
    }

    public AdminAccount searchAdminAccount(int admin_ID) throws SQLException
    {
        AdminAccount searchedAccount = null;
        String sql =("SELECT * \nFROM AdminAccount \nWHERE ADMIN_ID = " + admin_ID);
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
        {
            int AdminId = rs.getInt("ADMIN_ID");
            String AdminFName = rs.getString("ADMIN_FNAME");
            String AdminLName = rs.getString("ADMIN_LNAME");
            String Username = rs.getString("ADMIN_USERNAME");
            String Password = rs.getString("ADMIN_PASSWORD");
            String Email = rs.getString("ADMIN_EMAIL");

            searchedAccount = new AdminAccount(AdminFName, AdminLName, Username, Password, Email, AdminId);
        }
        rs.close();
        return searchedAccount;
    }

    public CustomerAccount searchCustomerAccounts(int customerID) throws SQLException
    {
        CustomerAccount account = null;
        String sql =("SELECT * \nFROM CustomerAccount \nWHERE CUSTOMER_ID = " + customerID);
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next())
        {
            String usernameSQL = rs.getString("CUSTOMER_USERNAME");
            String passwordSQL = rs.getString("CUSTOMER_PASSWORD");
            int customer_ID = rs.getInt("CUSTOMER_ID");
            String fName = rs.getString("CUSTOMER_FNAME");
            String lName = rs.getString("CUSTOMER_LNAME");
            String email = rs.getString("CUSTOMER_EMAIL");
            String address = rs.getString("CUSTOMER_ADDRESS");
            String city = rs.getString("CUSTOMER_CITY");
            String state = rs.getString("CUSTOMER_STATE");
            String zip = rs.getString("CUSTOMER_ZIP");
            String cc = rs.getString("CUSTOMER_CCNUM");
            account = new CustomerAccount(customer_ID, fName, lName, usernameSQL, passwordSQL, email, address, city, state, zip, cc);

        }
        rs.close();
        return account;
    }

    public AdminAccount logIn(String username, String password) throws SQLException
    {
        boolean isLoggedIn = false;
        String usernameSQL = "";
        String passwordSQL = "";
        AdminAccount account = null;
        String sql;
        sql = "SELECT * FROM AdminAccount WHERE ADMIN_USERNAME = '" + username + "';";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next())
        {
            usernameSQL = rs.getString("ADMIN_USERNAME");
            passwordSQL = rs.getString("ADMIN_PASSWORD");
            int admin_ID = rs.getInt("ADMIN_ID");
            String fName = rs.getString("ADMIN_FNAME");
            String lName = rs.getString("ADMIN_LNAME");
            String email = rs.getString("ADMIN_EMAIL");
            account = new AdminAccount(fName, lName, usernameSQL, passwordSQL, email, admin_ID);
        }
        if (usernameSQL.equalsIgnoreCase(username) && passwordSQL.equalsIgnoreCase(password))
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


}
