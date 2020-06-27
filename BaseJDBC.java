import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class BaseJDBC
{
    protected static Statement stmt = null;
    protected static Connection conn = null;

    public BaseJDBC() throws ClassNotFoundException, SQLException
    {
        Class.forName(DBInfo.JDBC_DRIVER);
        conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
        stmt = conn.createStatement();
    }
}
