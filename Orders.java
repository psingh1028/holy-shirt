import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;

/**
 *<h1><strong>Orders</strong></h1>
 * <h2>A collection of methods that allows the manipulation of customer orders</h2>
 */

public class Orders {
	
	OrdersJDBC ordersJDBC = new OrdersJDBC();
	
    public Orders () throws SQLException, ClassNotFoundException {}
   
	/**    
	 * <h2><strong>Creates Order</strong></h2>
	 * <strong>Description:</strong> Allows the customer to create an order
	 * @return Order ID number
	 * @author TerrenceTeel
	 */
	public boolean createOrder( int customerID ) throws SQLException
	{
		return ordersJDBC.createOrder(customerID);
	}
        
	/**    
	 * <h2><strong>View Saved Orders</strong></h2>
	 * <strong>Description:</strong> Allows the customer to view current order status on their account
	 * @return Order array list
	 * @author KieranLonergan
	 */
	public ArrayList<Order> viewOrder(int customerID) throws SQLException
	{
		return ordersJDBC.viewOrder(customerID);
	}
        
    /**    
	 * <h2><strong>Modify an Existing Order </strong></h2>
	 * <strong>Description:</strong> Allows the customer to modify an order
     * @param orderID ID of the order to be modified
     * @param shirtID The index of the OrderItem to modify
     * @param newQuantity The new quantity of the selected OrderItem
	 * @return Previous order before modification
	 * @author KieranLonergan
	 */
	public Order modifyOrder( int orderID, int shirtID, int newQuantity ) throws SQLException
	{
		return ordersJDBC.modifyOrderJDBC(orderID, shirtID, newQuantity);
	}
        
	/**    
	 * <h2><strong>Cancel Order</strong></h2>
	 * <strong>Description:</strong> Allows the customer to cancel an order
	 * @param orderID The ID of the order to be canceled
	 * @return The canceled order 
	 * @author KieranLonergan
	 */
	public Order cancelOrder( int orderID ) throws SQLException
	{
		return ordersJDBC.cancelOrder(orderID);
	}
        
	/**
	 * <h2><strong>Search for an Order</strong></h2>
	 * <strong>Description:</strong> Searches for an Order with a specific orderID, and returns the order found
	 * @author MattMaltese
	 * @param orderID  The ID number associated with the order to be searched for
	 * @return The order object with the desired orderID
	 * @author MattMaltese
	 */
	public Order searchOrder(int orderID) throws SQLException
	{ 
		return ordersJDBC.searchOrderJDBC(orderID);
	}
        
	/**    
	 * <h2><strong>Refunds an Order</strong></h2>
	 * <strong>Description:</strong> Allows the customer to request a refund for a order
	 * @param orderID The ID of the order to be refunded
	 * @return The refunded order's total
	 * @author TerrenceTeel
	 */
	public double refundOrder ( int orderID ) throws SQLException
	{
		return ordersJDBC.refundOrder(orderID);
	}
	
	/**
	 * <h2><strong>Creates a Business Report</strong></h2>
	 * <strong>Description:</strong> Allows an administrator to create an earnings report given a range of dates
	 * @param startDate The starting date
	 * @param endDate The ending date
	 * @return A formatted String report
	 * @author MattMaltese
	 */
	public String createBusinessReport(Calendar startDate, Calendar endDate ) throws SQLException
	{
		return ordersJDBC.createBusinessReport(startDate, endDate);
	}
}