import java.sql.SQLException;
import java.util.ArrayList;

/**
 * <h1><strong>Carts</strong></h1>
 * <h2>A collection of methods that enables customers to manage the contents of the cart associated with their account</h2>
 */
public class Carts 
{
	private CartJDBC cartJDBC;
	
	public Carts() throws SQLException, ClassNotFoundException
	{
		cartJDBC = new CartJDBC();
	}

	/**
	 * <h2><strong>Add Shirt(s) to Cart</strong></h2>
	 * <strong>Description:</strong> Allows the customer to add one or more shirts to their cart
	 * @author CalvinHolt
	 * @param quantity The number of shirts to be added to the customer's cart
	 * @return True if the desired quantity of the given shirt are successfully added to the customer's cart
	 * @author CalvinHolt
	 */
	public boolean addToCart( int shirtID, int quantity, int customerID ) throws SQLException
	{ 
		return cartJDBC.addToCart(shirtID, quantity, customerID);
	}

	public boolean removeOutOfStockShirts(int customerID) throws SQLException
	{
		return cartJDBC.removeOutOfStockShirts(customerID);
	}
	
	/**
	 * <h2><strong>Update Specified Shirt Quantity in Cart</strong></h2>
	 * <strong>Description:</strong> Allows the customer to change the quantity of a specified shirt in the cart
	 * @return The updated shirt, in case the new quantity is zero
	 * @author CalvinHolt
	 */
	public boolean updateCart(int customer_id, int shirt_id, int updated_Quantity) throws SQLException
	{
		return cartJDBC.updateCart(customer_id, shirt_id, updated_Quantity);
	}
	
	/**
	 * <h2><strong>Remove All Contents from Cart</strong></h2>
	 * <strong>Description:</strong> Allows the customer to remove all shirts added to their cart
	 * @return True if all shirts are removed from the customer's cart
	 * @author TerrenceTeel
	 */
	public boolean emptyCart(int customer_id)  throws SQLException
	{ 
		return cartJDBC.emptyCart(customer_id); 
	}
	
	/**
	 * <h2><strong>Remove a Shirt of Any Quantity from Cart</strong></h2>
	 * <strong>Description:</strong> Allows the customer to remove a single specified shirt of any quantity from their cart
	 * @return The deleted shirt
	 * @author CalvinHolt
	 */
	public OrderItem deleteFromCart(OrderItem orderItem, int customerID ) throws SQLException
	{ 
		return cartJDBC.deleteFromCart(orderItem, customerID);
	}
	
	/**
	 * <h2><strong>Views the Items in the Cart</strong></h2>
	 * <strong>Description:</strong> Allows the customer to view all of the Items currently in their Cart.
	 * @return A String array, with each index containing a String that describes an Item in the Cart.
	 * @author MattMaltese
	 */
	public ArrayList<OrderItem> viewCart(int customer_id) throws SQLException
	{
		return cartJDBC.viewCart(customer_id);
	}
	
	public int getNumOfDifferentShirtInCart(int customerID) throws SQLException
	{
		return cartJDBC.getNumOfDifferentShirtInCart(customerID);
	}
	
	public int getTotalNumberOfShirtsInCart(int customerID) throws SQLException
	{
		return cartJDBC.getTotalNumberOfShirtsInCart(customerID);
	}
	
	public double getCartTotal(int customerID) throws SQLException
	{
		return cartJDBC.getCartTotal(customerID);
	}
}
