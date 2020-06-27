
public class OrderItem 
{
	private Shirt shirt;
	private int quantity;
	
	public OrderItem(Shirt shirt, int quantity) 
	{
		this.shirt = shirt;
		this.quantity = quantity;
	}
	
	public Shirt getShirt()
	{
		return shirt;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setShirt(Shirt shirt)
	{
		this.shirt = shirt;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public double getTotal()
	{
		return shirt.getPrice() * quantity;
	}
	
	@Override
	public String toString()
	{
		return shirt + " \nQuantity: " + quantity + "\nTotal: " + (quantity * shirt.getPrice());
	}
}
