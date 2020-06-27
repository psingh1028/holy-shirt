import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.Date;

public class OrdersJDBC
{
	private static Statement stmt = null;
	private static Connection conn = null;

	public OrdersJDBC() throws SQLException, ClassNotFoundException
	{
		Class.forName(DBInfo.JDBC_DRIVER);
		conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
		stmt = conn.createStatement();
	}

	public double getOrderTotal(int orderID) throws SQLException
	{
		double total = 0;
		String sql = "SELECT ORDER_QUANTITY, SHIRT_PRICE FROM OrderItem JOIN Shirt USING (SHIRT_ID) "
					+ "WHERE ORDER_ID = " + orderID + ";";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			total += (rs.getInt("ORDER_QUANTITY") * rs.getDouble("SHIRT_PRICE"));
		}
		String.format("%.2f", total);
		rs.close();
		return total;
	}
	
	public Order searchOrderJDBC(int orderID) throws SQLException
	{
		Order searchedOrder = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName(DBInfo.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBInfo.DB_URL, DBInfo.username, DBInfo.password);
			stmt = conn.createStatement();
			String sql = "SELECT * "
					+ "FROM Orders "
					+ "WHERE ORDER_ID = " + orderID + ";";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int order_ID = rs.getInt("ORDER_ID");
				Date date_created = rs.getDate("date_created");
				Date date_sent = rs.getDate("date_sent");
				String order_status = rs.getString("ORDER_STATUS");

				searchedOrder = new Order(order_ID, date_created, date_sent, new ArrayList<OrderItem>(), order_status, getOrderTotal(orderID));
			}

			sql = "SELECT * "
					+ "FROM orderitem JOIN Shirt USING (SHIRT_ID)"
					+ "WHERE ORDER_ID = " + orderID + ";";

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int shirtID = rs.getInt("SHIRT_ID");
				String shirtSize = rs.getString("SHIRT_SIZE");
				String shirtName = rs.getString("SHIRT_NAME");
				String shirtDesc = rs.getString("SHIRT_DESC");
				double shirtPrice = rs.getDouble("SHIRT_PRICE");
				String shirtGender = rs.getString("SHIRT_GENDER");
				int shirtQuantity = rs.getInt("SHIRT_QUANTITY");
				String shirtColor = rs.getString("SHIRT_COLOR");
				int orderQuantity = rs.getInt("ORDER_QUANTITY");

				Shirt s = new Shirt(shirtID, shirtName, shirtDesc, shirtPrice, shirtGender, shirtColor, shirtSize, shirtQuantity);
				searchedOrder.addShirtToOrder(s, orderQuantity);
			}
		rs.close();
		}catch(SQLException se){se.printStackTrace();}
		catch(Exception e){e.printStackTrace();}
		finally{try{if (stmt!=null){stmt.close();}}catch(SQLException se2){}
				try{if (conn!=null){conn.close();}}catch (SQLException se){se.printStackTrace();}}

		return searchedOrder;
	}
	
	public ArrayList<Order> viewOrder(int customerID) throws SQLException
	{
		ArrayList<Order> customerOrders = new ArrayList<Order>();
		Connection conn = null;
		Statement stmt = null;
		try
		{
			Class.forName(DBInfo.JDBC_DRIVER);
			conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
			stmt = conn.createStatement();
			String sql;

			sql = "SELECT COUNT(CUSTOMER_ID) "
					+ "FROM OrderItem "
					+ "WHERE CUSTOMER_ID =" + customerID + ";";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				if (rs.getInt(1) == 0)
					return null;
			}

			sql = "SELECT * "
					+ "FROM Orders JOIN OrderItem USING (ORDER_ID) "
					+ "WHERE CUSTOMER_ID = " + customerID + ";";

			ResultSet rs1 = stmt.executeQuery(sql);

			int i = -1;
			int previousOrderID = -1;
			while (rs1.next())
			{
				int orderID = rs1.getInt("ORDER_ID");
				if (previousOrderID != orderID)
				{
					Date date_created = rs1.getDate("date_created");
					Date date_sent = rs1.getDate("date_sent");
					String order_status = rs1.getString("ORDER_STATUS");
					customerOrders.add(new Order(orderID, date_created, date_sent, new ArrayList<OrderItem>(), order_status, getOrderTotal(orderID)));
				}
				previousOrderID = orderID;
			}

			sql = "SELECT * "
					+ "FROM orderitem JOIN Shirt USING (SHIRT_ID)"
					+ "WHERE CUSTOMER_ID = " + customerID + ";";

			rs1 = stmt.executeQuery(sql);
			i = -1;
			previousOrderID = -1;
			while (rs1.next())
			{
				int orderID = rs1.getInt("ORDER_ID");
				if (previousOrderID != orderID)
				{
					i++;
				}
				previousOrderID = orderID;

				int shirtID = rs1.getInt("SHIRT_ID");
				String shirtSize = rs1.getString("SHIRT_SIZE");
				String shirtName = rs1.getString("SHIRT_NAME");
				String shirtDesc = rs1.getString("SHIRT_DESC");
				double shirtPrice = rs1.getDouble("SHIRT_PRICE");
				String shirtGender = rs1.getString("SHIRT_GENDER");
				int shirtQuantity = rs1.getInt("SHIRT_QUANTITY");
				String shirtColor = rs1.getString("SHIRT_COLOR");
				int orderQuantity = rs1.getInt("ORDER_QUANTITY");

				Shirt s = new Shirt(shirtID, shirtName, shirtDesc, shirtPrice, shirtGender, shirtColor, shirtSize, shirtQuantity);
				customerOrders.get(i).addShirtToOrder(s, orderQuantity);
			}

			rs.close();
			rs1.close();
			stmt.close();
			conn.close();
		}

		catch(SQLException se){se.printStackTrace();}
		catch(Exception e){e.printStackTrace();}
		finally{try{if (stmt!=null){stmt.close();}}catch(SQLException se2){}
			try{if (conn!=null){conn.close();}}catch (SQLException se){se.printStackTrace();}}

		if (customerOrders.size() == 0)
		{
			return null;
		}

		return customerOrders;
	}

	public Order cancelOrder( int orderID ) throws SQLException
	{
		if (orderID < 0)
		{
			return null;
		}
		Order canceledOrder = searchOrderJDBC(orderID);
		String sql = "UPDATE Orders SET ORDER_STATUS = " + "'Canceled'" + "\nWHERE ORDER_ID = " + orderID + " AND ORDER_STATUS ;";
		stmt.executeUpdate(sql);
		for (int i = 0; i < canceledOrder.orderItems.size(); i++)
		{
			sql = "UPDATE Shirt SET SHIRT_QUANTITY = SHIRT_QUANTITY - " + getQuantityDifference(canceledOrder, canceledOrder.orderItems.get(i).getShirt().getID(),
					0) + " WHERE SHIRT_ID = " + canceledOrder.orderItems.get(i).getShirt().getID() + ";";
			stmt.executeUpdate(sql);
		}
		return canceledOrder;
	}

	public double refundOrder (int order_id) throws SQLException
	{
		String sql = "SELECT * from orders where ORDER_ID  = " + order_id + ";";
		ResultSet rs = stmt.executeQuery(sql);
		String status = "";
		while(rs.next()){

			status = rs.getString("ORDER_STATUS");
		}
		if (status == "Refunded")
		{
			return -1;
		}
		else
		{
			sql = "UPDATE orders SET ORDER_STATUS = 'Refunded' WHERE ORDER_ID = " + order_id + ";";
			stmt.executeUpdate(sql);

			rs.close();
			return getOrderTotal(order_id);
		}
	}

	public boolean createOrder(int customerID) throws SQLException
	{
		if (customerID < 0)
		{
			return false;
		}
		LocalDate localDate = LocalDate.now();
		java.sql.Date date = java.sql.Date.valueOf(localDate);
		String sql = "INSERT INTO Orders(date_created, date_sent, ORDER_STATUS) " +
				"VALUES ('" + date + "', " + null + ", 'Pending');";

		stmt.executeUpdate(sql);

		sql = "SELECT DISTINCT MAX(ORDER_ID) FROM Orders;";

		ResultSet rs = stmt.executeQuery(sql);

		int newOrderID = -1;
		while(rs.next())
		{
			newOrderID = rs.getInt(1);
		}

		if (newOrderID == -1)
		{
			return false;
		}

		sql = "SELECT * FROM CartItem JOIN Shirt USING (SHIRT_ID) WHERE CUSTOMER_ID = " + customerID + ";";


		rs = stmt.executeQuery(sql);

		while(rs.next())
		{
			int shirtID = rs.getInt("SHIRT_ID");
			int cartQuantity = rs.getInt("CART_QUANTITY");
			int shirtQuantity = rs.getInt("SHIRT_QUANTITY");

			if (shirtQuantity < cartQuantity)
			{
				return false;
			}
			sql = "DELETE FROM CartItem WHERE CUSTOMER_ID = " + customerID + " AND SHIRT_ID = " + shirtID + ";";
			stmt.executeUpdate(sql);

			sql = "INSERT INTO OrderItem VALUES (" + newOrderID + ", " + customerID + ", " + shirtID + ", " + cartQuantity + ");";
			stmt.executeUpdate(sql);

			sql = "UPDATE Shirt SET SHIRT_QUANTITY = SHIRT_QUANTITY - " + cartQuantity + " WHERE SHIRT_ID = " + shirtID + ";";
			stmt.executeUpdate(sql);

			rs = stmt.executeQuery("SELECT * FROM CartItem JOIN Shirt USING (SHIRT_ID) WHERE CUSTOMER_ID = " + customerID + ";");
		}
		return true;
	}

	public Order modifyOrderJDBC(int orderID, int shirtID, int newQuantity) throws SQLException
	{
		boolean orderIsPending = false;
		Order beforeModify = searchOrderJDBC(orderID);
		String sql;

		sql = "SELECT * FROM Orders WHERE ORDER_ID = " + orderID + ";";

		ResultSet rs = stmt.executeQuery(sql);

		// Check if the order is pending (still able to be modified)
		while(rs.next())
		{
			if(rs.getString("ORDER_STATUS").equals("Pending"))
			{
				orderIsPending = true;
			}
		}

		// If the order is pending, modify it
		if(orderIsPending)
		{
			// If the new quantity is 0, delete the order item
			if(newQuantity == 0)
			{
				sql = "DELETE FROM orderitem WHERE ORDER_ID = "
						+ orderID + " AND SHIRT_ID = " + shirtID + ";";

			}
			// Otherwise, update the quantity of the item
			else
			{
				sql = "UPDATE orderitem SET ORDER_QUANTITY = "
						+ newQuantity + " WHERE ORDER_ID = "
						+ orderID + " AND SHIRT_ID = "
						+ shirtID + ";";
			}
			stmt.executeUpdate(sql);

			sql = "UPDATE Shirt SET SHIRT_QUANTITY = SHIRT_QUANTITY - " + getQuantityDifference(beforeModify, shirtID, newQuantity) + " WHERE SHIRT_ID = " + shirtID + ";";
			stmt.executeUpdate(sql);

			return beforeModify;
		}

		rs.close();

		return null;
	}

	private int getQuantityDifference(Order order, int shirtID, int newQuantity) throws SQLException
	{
		int oldQuantity = -1;
		for (int i = 0; i < order.orderItems.size(); i++)
		{
			if (order.orderItems.get(i).getShirt().getID() == shirtID)
			{
				oldQuantity = order.orderItems.get(i).getQuantity();
				break;
			}
		}
		return (newQuantity - oldQuantity);
	}

	public String createBusinessReport(Calendar startDate, Calendar endDate ) throws SQLException
	{
		double totalRevenue = 0;
		int totalOrders = 0;

		String startDateStr = new String(startDate.get(Calendar.YEAR)
				+ "-" + (startDate.get(Calendar.MONTH) + 1)
				+ "-" + startDate.get(Calendar.DATE));

		String endDateStr = new String(endDate.get(Calendar.YEAR)
				+ "-" + (endDate.get(Calendar.MONTH) + 1)
				+ "-" + endDate.get(Calendar.DATE));

		String sql = "SELECT * "
				+ "FROM orderitem JOIN Shirt USING (SHIRT_ID) JOIN orders USING (ORDER_ID)"
				+ "WHERE date_created >= '"
				+ startDateStr
				+ "' AND date_created <= '"
				+ endDateStr + "';";
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next())
		{
			double orderRevenue = rs.getInt("ORDER_QUANTITY") * rs.getDouble("SHIRT_PRICE");
			totalRevenue += orderRevenue;
		}
		sql = "SELECT * "
				+ "FROM orders "
				+ "WHERE date_created >= '"
				+ startDateStr
				+ "' AND date_created <= '"
				+ endDateStr + "';";

		rs = stmt.executeQuery(sql);
		while (rs.next())
		{
			totalOrders++;
		}
		rs.close();

		return new String("\nBusiness Report from " + startDateStr + " to " + endDateStr + "\n" +
				"==========\n" +
				"Total Orders in time period: " + totalOrders + "\n" +
				"Total Revenue in time period: " + String.format("%.2f", totalRevenue) + "\n" +
				"==========\n");
	}
}
