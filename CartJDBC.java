import java.sql.*;
import java.util.ArrayList;


public class CartJDBC 
{
	private static Statement stmt = null;
	private static Connection conn = null;

	public CartJDBC() throws SQLException, ClassNotFoundException
	{
		Class.forName(DBInfo.JDBC_DRIVER);
		conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
		stmt = conn.createStatement();
	}

	public boolean removeOutOfStockShirts(int customerID) throws SQLException
	{
		String sql = "SELECT * FROM CartItem JOIN Shirt USING (Shirt_ID) WHERE CUSTOMER_ID = " + customerID + ";";
		ResultSet rs = stmt.executeQuery(sql);
		ArrayList<Integer> updatedQuantities = new ArrayList<Integer>();
		int i = 0;
		while (rs.next())
		{
			int cartQuantity = rs.getInt("CART_QUANTITY");
			int shirtQuantity = rs.getInt("SHIRT_QUANTITY");

			if (cartQuantity > shirtQuantity)
			{
				updatedQuantities.add(shirtQuantity);
//				stmt.executeUpdate("UPDATE CartItem SET CART_QUANTITY = " + shirtQuantity + " WHERE CUSTOMER_ID = " + customerID + ";");
			}
			else
			{
				updatedQuantities.add(-1);
			}
			i++;
		}
		boolean isUpdated = false;
		for (int j = 0; j < i; j++)
		{
			if (updatedQuantities.get(j) != -1)
			{
				isUpdated = true;
				stmt.executeUpdate("UPDATE CartItem SET CART_QUANTITY = " + updatedQuantities.get(j) + " WHERE CUSTOMER_ID = " + customerID + ";");
			}
		}

		return isUpdated;
	}
	
	public int getNumOfDifferentShirtInCart(int customerID) throws SQLException
	{
		int numOfShirts = -1;

		String sql = "SELECT DISTINCT COUNT(SHIRT_ID) FROM CartItem WHERE CUSTOMER_ID = " + customerID + ";";
		ResultSet rs = stmt.executeQuery(sql);

		rs.next();
		numOfShirts = rs.getInt(1);
			
		return numOfShirts;
	}
	
	public int getTotalNumberOfShirtsInCart(int customerID) throws SQLException
	{
		int totalNumOfShirts = -1;

		String sql = "SELECT SUM(CART_QUANTITY) FROM CartItem WHERE CUSTOMER_ID = " + customerID + ";";
		ResultSet rs = stmt.executeQuery(sql);

		rs.next();
		totalNumOfShirts = rs.getInt(1);

		rs.close();
		return totalNumOfShirts;
	}
	
	public double getCartTotal(int customerID) throws SQLException
	{
		double cartTotal = 0;

		String sql = "SELECT SHIRT_PRICE, CART_QUANTITY FROM CartItem JOIN Shirt USING (SHIRT_ID) WHERE CUSTOMER_ID = " + customerID + ";";
		ResultSet rs = stmt.executeQuery(sql);

		while(rs.next())
		{
			cartTotal += (rs.getDouble("SHIRT_PRICE") * rs.getInt("CART_QUANTITY"));
		}

		rs.close();

		return cartTotal;
	}
	
	public OrderItem deleteFromCart(OrderItem orderItem, int customerID ) throws SQLException
	{ 
		String sql = "DELETE FROM CartItem "
				+ "WHERE CUSTOMER_ID = " + customerID
				+ " AND SHIRT_ID = " + orderItem.getShirt().getID() + ";";

		stmt.executeUpdate(sql);
			
		return orderItem;
	}
	
	public boolean addToCart( int shirt_id, int quantity, int customer_id ) throws SQLException
	{
		if (quantity < 0 || shirt_id < 0)
			return false;
		
		String sql = "SELECT SHIRT_QUANTITY "
				+ "FROM Shirt "
				+ "WHERE SHIRT_ID = " + shirt_id + ";";

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			if (rs.getInt("SHIRT_QUANTITY") < quantity)
				return false;
		}

		sql = "SELECT COUNT(CUSTOMER_ID) "
				+ "FROM CartItem "
				+ "WHERE CUSTOMER_ID = " + customer_id + " "
				+ "AND SHIRT_ID = " + shirt_id + ";";

		rs = stmt.executeQuery(sql);
		int cartItem = 0;
		while (rs.next())
		{
			cartItem = rs.getInt(1);
		}
		//3a) IF SHIRT IS NOT ADDED TO CART
		if (cartItem == 0)
		{
			sql = "INSERT INTO CartItem "
					+ "VALUES (" + customer_id + ", " + shirt_id + ", " + quantity + ")";
		}
		//3b)IF SHIRT IS ADDED TO CART
		else
		{
			sql = "UPDATE CartItem "
					+ "SET CART_QUANTITY = CART_QUANTITY + " + quantity + " "
					+ "WHERE CUSTOMER_ID = " + customer_id + " "
					+ "AND SHIRT_ID = " + shirt_id + ";";
		}
		stmt.executeUpdate(sql);
		rs.close();
		return true;
	}

	public boolean emptyCart(int customer_id) throws SQLException
	{
		if (customer_id < 0)
		{
			return false;
		}

		String sql = "DELETE FROM cartitem WHERE CUSTOMER_ID = " + customer_id + ";";
		stmt.executeUpdate(sql);

        return true;   
    }

	public boolean updateCart (int customer_id, int shirt_id, int updated_Quantity) throws SQLException
	{
		if (updated_Quantity < 0)
			return false;

		String sql = "";

		if (updated_Quantity == 0)
		{
			sql += "DELETE FROM cartitem WHERE CUSTOMER_ID = " + customer_id + " AND SHIRT_ID = " + shirt_id + ";";
		}
		else
		{
			sql += "UPDATE cartitem SET CART_QUANTITY = " + updated_Quantity + " WHERE CUSTOMER_ID = " + customer_id + " AND SHIRT_ID = " + shirt_id + ";";
		}

		stmt.executeUpdate(sql);

		return true;
	}
	
	public ArrayList<OrderItem> viewCart(int customer_id) throws SQLException
	{
		String str = "";
		ArrayList<OrderItem> cartItems = new ArrayList<OrderItem>();

		String sql = "SELECT * FROM CartItem JOIN Shirt USING (SHIRT_ID) WHERE CUSTOMER_ID = " + customer_id + ";";

		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			int shirtID = rs.getInt("SHIRT_ID");
			String shirtSize = rs.getString("SHIRT_SIZE");
			String shirtName = rs.getString("SHIRT_NAME");
			String shirtDesc = rs.getString("SHIRT_DESC");
			double shirtPrice = rs.getDouble("SHIRT_PRICE");
			String shirtGender = rs.getString("SHIRT_GENDER");
			int shirtQuantity = rs.getInt("SHIRT_QUANTITY");
			String shirtColor = rs.getString("SHIRT_COLOR");
			int cartQuantity = rs.getInt("CART_QUANTITY");
			Shirt shirt = new Shirt(shirtID, shirtName, shirtDesc, shirtPrice, shirtGender, shirtColor, shirtSize, shirtQuantity);
			OrderItem cartItem = new OrderItem(shirt, cartQuantity);
			cartItems.add(cartItem);
		}

		rs.close();

		if (cartItems.size() != 0)
		{
			return cartItems;
		}
		return null;
	}

}
