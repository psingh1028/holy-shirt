import java.sql.Date;
import java.util.ArrayList;
/**
 * A collection of shirts purchased by and shipped to a customer
 */
public class Order 
{
	private int orderID;
	private Date dateCreated;
	private Date dateSent;
	public ArrayList<OrderItem> orderItems;
	private String orderStatus;
	private double total;
	
	/**
	 * Creates an order
	 * @param _orderID The order's ID number
	 * @param _dateCreated Date order is created
	 * @param _contents Shirts in order
	 * @param _quantities Quantities of each shirt
	 */
	public Order()
	{
		orderID = -1;
		dateCreated = null;
		dateSent = null;
		orderItems = new ArrayList<OrderItem>();
		orderStatus = null;
		total = -1;
	}

	public String getOrderStatus()
	{
		return orderStatus;
	}

	public Date getDateCreated()
	{
		return dateCreated;
	}

	public Date getDateSent()
	{
		return dateSent;
	}

	public double getTotal()
	{
		return total;
	}

	public Order(int _orderID, Date _dateCreated, Date _dateSent, ArrayList<OrderItem> _orderItems, String _orderStatus, double _total) 
	{
		orderID = _orderID;
		dateCreated = _dateCreated;
		dateSent = _dateSent;
		orderItems = _orderItems;
		orderStatus = _orderStatus;
		total = _total;
	}
	
	public boolean addShirtToOrder(Shirt shirt, int quantity)
	{
		OrderItem orderItem = new OrderItem(shirt, quantity);
		orderItems.add(orderItem);
		return true;
	}
	
	/**
	 * Get the order ID number
	 * @return
	 */
	public int geOrderID() 
	{ 
		return orderID; 
	}
	
	/**
	 * Set the date when for when the order is sent
	 * @param setDaySent
	 * @return True is completed successfully
	 */
	public boolean setDateSent(Date setDaySent) 
	{ 
		dateSent = setDaySent;
		return true; 
	}
	
	/**
	 * Modify a shirt's quantity in the order
	 * @param index The index of the shirt to edit
	 * @param newQuantity The new quantity of the shirt to edit
	 * @return True is completed successfully
	 */
	public boolean modifyOrderContents( int index, int newQuantity) 
	{ 
		if (index < orderItems.size())
		{
			orderItems.get(index).setQuantity(newQuantity);
			return true;
		}
		return false;
	}
	
	/**
	 * Change the order's status
	 * @param newOrderStatus The new order status
	 * @return True is completed successfully
	 */
	public void setOrderStatus( String newOrderStatus ) 
	{  
		orderStatus = newOrderStatus;
	}
	
	@Override
	public String toString()
	{
		String str = "\nOrder ID: " + orderID + "\n";
		str += "Date Created: " + dateCreated + "\n";
		str += "Date Sent: " + dateSent + "\n";
		str += "Status: " + orderStatus + "\n";
		str += "- - - - - \n";
		if (orderItems != null)
		{
			for (int i = 0; i < orderItems.size(); i++)
			{
				Shirt s = orderItems.get(i).getShirt();
				str += (i+1) +") " + s.getName() + ", Size: " + s.getSize() + ", Gender: " + s.getGender() + ", Color: " + s.getColor() + "\n";
				str += "Quantity: " + orderItems.get(i).getQuantity() + "\n";
				str += "Price: " + (s.getPrice() * orderItems.get(i).getQuantity()) + "\n";
				str += "- - - - - \n";
			}
		}
		return str;
	}
	
}
