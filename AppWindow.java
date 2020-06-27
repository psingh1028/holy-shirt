import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AppWindow extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private Inventory inventory = new Inventory();
	private Carts cart = new Carts();
	private Orders orders = new Orders();
	private CustomerAccounts customerAccounts = new CustomerAccounts();
	private AdminAccounts adminAccounts = new AdminAccounts();
	
	private int customer_id = -1;
	private CustomerAccount customer_account;
	private boolean loggedIn = false;
	
	private MenuButtons menuButtons;
	private Header header;
	private TextDisplay textDisplay;
	
	private int previous = 0;
	private int increment = 5;
	private int current = increment;
	
	private final int MAIN_MENU = 0;
	private final int CART_MENU = 1;
	private final int ORDER_MENU = 2;
	private final int ORDER_DETAIL_MENU = 3;
	private final int SHIRT_DETAIL_MENU = 4;
	private final int SHIRT_SEARCHED_MENU = 5;
	
	private ArrayList<Shirt> shirtList;
	private ArrayList<OrderItem> cartItems;
	private ArrayList<Order> orderList;
	private Shirt selectedShirt;
	
	private int currentMenu = MAIN_MENU;
	
	public AppWindow() throws SQLException, ClassNotFoundException
	{
		setLayout(new BorderLayout());
		header = new Header();
		menuButtons = new MenuButtons();
		textDisplay = new TextDisplay();
		add(menuButtons, BorderLayout.WEST);
		add(header, BorderLayout.NORTH);
		add(textDisplay, BorderLayout.CENTER);
		setSize(900, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);	
		shirtList = inventory.browseInventory();
		textDisplay.setText(displayInventory(shirtList));
		menuButtons.setButtonText(updateMainMenu(loggedIn, previous > 0));
		
		menuButtons.menuChoice(new MenuButtonListener() {
			@Override
			public void menuChoice(int choice) throws SQLException {
				determineCurrentMenu(choice);
			}
		});
	}
	
	private void determineCurrentMenu(int choice) throws SQLException
	{
		System.out.println("currentMenu: " + currentMenu);
		switch (currentMenu)
		{
		case MAIN_MENU:
			shirtList = inventory.browseInventory();
			mainMenuSwitch(choice);
			break;
		case CART_MENU:
			cartMenuSwitch(choice);
			break;
		case ORDER_MENU:
			orderMenuSwitch(choice);
			break;
		case ORDER_DETAIL_MENU:
			break;
		case SHIRT_DETAIL_MENU:
			shirtMenuSwitch(choice);
			break;
		case SHIRT_SEARCHED_MENU:
			shirtSearchMenuSwitch(choice);
			break;
		}
		if (currentMenu == MAIN_MENU)
		{
			menuButtons.setButtonText(updateMainMenu(loggedIn, previous > 0));
			textDisplay.setText(displayInventory(shirtList));
		}
		else if (currentMenu == SHIRT_SEARCHED_MENU)
		{
			menuButtons.setButtonText(shirtSearchMenu());
			textDisplay.setText(displayInventory(shirtList));
		}
		else if (currentMenu == CART_MENU)
		{
			menuButtons.setButtonText(cartMenu());
			try {
				cartItems = cart.viewCart(customer_id);
			} catch (SQLException e){
				e.printStackTrace();
			}
			double totalPrice = 0;
			try {
				totalPrice = cart.getCartTotal(customer_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			int totalShirts = 0;
			try {
				totalShirts = cart.getTotalNumberOfShirtsInCart(customer_id);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			textDisplay.setText(HTMLView.viewCart(cartItems, totalPrice, totalShirts));
		}
		else if (currentMenu == ORDER_MENU)
		{
			menuButtons.setButtonText(orderMenu());
			orderList = orders.viewOrder(customer_id);
			textDisplay.setText(HTMLView.viewOrders(orderList));
		}
		else if (currentMenu == SHIRT_DETAIL_MENU)
		{
			menuButtons.setButtonText(shirtDetailMenu());
			textDisplay.setText(HTMLView.viewShirtDetail(selectedShirt));
		}
	}

	private void mainMenuSwitch(int choice) throws SQLException {
		switch (choice)
		{
			case 0:
			{
				viewShirt();
				break;
			}
			case 1:
			{
				if (loggedIn)
				{
					addToCart(null, previous, current);
				}
				else
				{
					int selection = DialogBox.logInOrCreateAccountPanel(new String[] {"Log in", "Create Account"});
					if (selection == 0)
					{
						logIn();
					}
					else if (selection == 1)
					{
						createCustomerAccount();
					}

				}
				break;
			}
			case 2:
			{
				next();
				break;
			}
			case 3:
			{
				if (!loggedIn && previous == 0)
				{

					searchShirt();
				}
				else if (!loggedIn && previous > 0)
				{
					previous();
				}
				else if (loggedIn && previous == 0)
				{
					searchShirt();
				}
				else
				{
					previous();
				}
				break;
			}
			case 4:
			{
				if (!loggedIn && previous == 0)
				{
					createCustomerAccount();;
				}
				else if (!loggedIn && previous > 0)
				{
					searchShirt();
				}
				else if (loggedIn && previous == 0)
				{
					setMenu(CART_MENU);
				}
				else
				{
					searchShirt();
				}
				break;
			}
			case 5:
			{
				if (!loggedIn && previous == 0)
				{
					logIn();
				}
				else if (!loggedIn && previous > 0)
				{
					createCustomerAccount();
				}
				else if (loggedIn && previous == 0)
				{
					setMenu(ORDER_MENU);
				}
				else
				{
					setMenu(CART_MENU);
				}
				break;
			}
			case 6:
			{
				if (!loggedIn && previous == 0)
				{
					//nothing
				}
				else if (!loggedIn && previous > 0)
				{
					logIn();
				}
				else if (loggedIn && previous == 0)
				{
					editCustomerAccount();
				}
				else
				{
					setMenu(ORDER_MENU);
				}
				break;
			}
			case 7:
			{
				if (!loggedIn && previous == 0)
				{
					//nothing
				}
				else if (!loggedIn && previous > 0)
				{
					//nothing
				}
				else if (loggedIn && previous == 0)
				{
					logOut();
				}
				else
				{
					editCustomerAccount();
				}
				break;
			}
			case 8:
			{
				if (!loggedIn && previous == 0)
				{
					//nothing
				}
				else if (!loggedIn && previous > 0)
				{
					//nothing
				}
				else if (loggedIn && previous == 0)
				{
					//nothing
				}
				else
				{
					logOut();
				}
				break;
			}
		}
	}

	private void orderMenuSwitch(int choice) throws SQLException {
		switch (choice)
		{
			//Modify order
			case 0:
			{
				modifyOrder();
				break;
			}
			//Cancel Order
			case 1:
			{
				if (orderList == null)
				{
					DialogBox.plainMessage("You have no orders to cancel", "Message");
				}
				int index = DialogBox.enterNumberPanel("Enter the number of a pending or modified order you want to cancel", "Number:", 1, orderList.size());
				Order canceledOrder = null;
				try {
					canceledOrder = orders.cancelOrder(orderList.get(index-1).geOrderID());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (canceledOrder != null)
				{
					DialogBox.plainMessage("The following order has been canceled:\n" + canceledOrder, "Message");
				}
				else
				{
					DialogBox.plainMessage("The order could not be canceled", "Error");
				}
				break;
			}
			//Back
			case 2:
			{
				setMenu(MAIN_MENU);
				break;
			}
		}
	}

	private void cartMenuSwitch(int choice) throws SQLException {
		switch (choice)
		{
			//Update quantity
			case 0:
			{
				updateCart();
				break;
			}
			//Remove all of one
			case 1:
			{
				if (cartItems.size() > 0)
				{
					int index = DialogBox.enterNumberPanel("Enter the number of the shirt you want to remove", "Number:", 1, cartItems.size());
					OrderItem deletedItem = null;
					try {
						deletedItem = cart.deleteFromCart(cartItems.get(index-1), customer_id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (deletedItem != null)
					{
						DialogBox.plainMessage("The following shirt has been removed from your cart:\n" + deletedItem.getShirt(), "Message");
					}
					else
					{
						DialogBox.plainMessage("The shirt could not be removed", "Error");
					}
				}
				else
				{
					DialogBox.plainMessage("You can't remove a shirt because it's empty", "Message");
				}
				break;
			}
			//Empty Cart
			case 2:
			{
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to empty your cart", "WARNING", JOptionPane.YES_NO_OPTION);

				if (option == 0)
				{
					boolean empty = false;
					try {
						empty = cart.emptyCart(customer_id);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (empty)
					{
						DialogBox.plainMessage("Your cart is now empty", "Message");
					}
					else
					{
						DialogBox.plainMessage("Your cart could not be emptied", "Error");
					}
				}
				else
				{
					DialogBox.plainMessage("Phew, that was close :)", "Message");
				}
				break;
			}
			//CREATE ORDER
			case 3:
			{
				boolean isCreated = orders.createOrder(customer_id);
				if (isCreated)
				{
					DialogBox.plainMessage("Your order has been created!", "Purchase Successful");
				}
				else
				{
					DialogBox.plainMessage("Whoops, there was an error...", "Error");
				}
				break;
			}
			case 4:
			{
				setMenu(MAIN_MENU);
				break;
			}
		}
	}

	private void shirtMenuSwitch(int choice) throws SQLException {
		switch (choice)
		{
			//Add Shirt to Cart
			case 0:
			{
				if (loggedIn)
				{
					addToCart(selectedShirt, 0, 1);
				}
				else
				{
					int selection = DialogBox.logInOrCreateAccountPanel(new String[] {"Log in", "Create Account"});
					if (selection == 0)
					{
						logIn();
					}
					else if (selection == 1)
					{
						createCustomerAccount();
					}

				}
				break;
			}
			case 1:
			{
				if (loggedIn)
				{
					setMenu(CART_MENU);
				}
				else
				{
//					searchInventory()
				}
				break;
			}
			case 2:
			{
				if (loggedIn)
				{
//					searchInventory()
				}
				else
				{
					setMenu(MAIN_MENU);
				}
				break;
			}
			//View Orders
			case 3:
			{
				setMenu(ORDER_MENU);
				break;
			}
			case 4:
			{
				setMenu(MAIN_MENU);
				break;
			}
		}
	}

	private void shirtSearchMenuSwitch(int choice) throws SQLException {
		switch(choice)
		{
			case 0:
				viewShirt();
				break;
			case 1:
				addToCart(null, previous, current);
				break;
			case 2:
				if (shirtList.size() <= increment)
					searchShirt();
				else
					next();
				break;
			case 3:
				if (shirtList.size() <= increment)
					setMenu(MAIN_MENU);
				else if (current > increment)
					previous();
				else
					searchShirt();
				break;
			case 4:
				if (shirtList.size() > increment && previous == 0)
					setMenu(MAIN_MENU);
				else
					searchShirt();
				break;
			case 5:
				setMenu(MAIN_MENU);
		}
	}

	private void updateCart() throws SQLException
	{
		if (cartItems.size() == 0)
		{
			DialogBox.plainMessage("You have no items in your cart to update!", "Message");
		}
		else if (cartItems.size() == 1)
		{
			int newQuantity = DialogBox.enterNumberPanel("Enter a new quantity for this shirt: ", "New quantity: ", 0, cartItems.get(0).getShirt().getShirtQuantity());
			if (newQuantity != -1)
			{
				boolean updated = cart.updateCart(customer_id, cartItems.get(0).getShirt().getID(), newQuantity);
				if (updated)
				{
					DialogBox.plainMessage("Your cart has been updated!", "Message");
				}
				else
				{
					DialogBox.plainMessage("Your cart could not be updated", "Error");
				}
			}
		}
		else
		{
			ArrayList<Shirt> cartShirts = new ArrayList<>();
			for (int i = 0; i < cartItems.size(); i++)
			{
				cartShirts.add(cartItems.get(i).getShirt());
			}
			Shirt shirt = DialogBox.selectShirtPanel("Select which shirt to update: ", cartShirts, 0, cartItems.size());
			if (shirt != null)
			{
				int index = -1;
				for (int i = 0; i < cartItems.size(); i++)
				{
					if (cartShirts.get(i).getID() == cartItems.get(i).getShirt().getID())
					{
						index = i;
						break;
					}
				}
				int newQuantity = DialogBox.enterNumberPanel("Enter a new quantity for this shirt: ", "New quantity: ", 0, cartItems.get(index).getShirt().getShirtQuantity());
				if (newQuantity != -1)
				{
					boolean updated = cart.updateCart(customer_id, cartItems.get(index).getShirt().getID(), newQuantity);
					if (updated)
					{
						DialogBox.plainMessage("Your cart has been updated!", "Message");
					}
					else
					{
						DialogBox.plainMessage("Your cart could not be updated", "Error");
					}
				}
			}
		}
	}

	private void searchShirt() throws SQLException
	{
		Shirt shirtInfo = DialogBox.searchShirt();
		if (shirtInfo == null)
		{
			DialogBox.plainMessage("No matches!", "Message");
		}
		else
		{
			shirtList = inventory.searchInventory(shirtInfo.getName(), shirtInfo.getSize(), shirtInfo.getGender(),
					shirtInfo.getColor(), shirtInfo.getID(), shirtInfo.getShirtQuantity());
			setMenu(SHIRT_SEARCHED_MENU);
			previous = 0;
			if (shirtList.size() < increment)
				current = shirtList.size();
			else
				current = increment;
			System.out.println("previous: " + previous + ", current: " + current);
		}
	}

	private void modifyOrder() throws SQLException {
		if (orderList.size() == 0)
		{
			DialogBox.plainMessage("You have no orders!", "Message");
		}
		else if (orderList.size() == 1)
		{
			ArrayList<Shirt> shirtsInOrder = new ArrayList<Shirt>();
			for (int i = 0; i < orderList.get(0).orderItems.size(); i++)
			{
				shirtsInOrder.add(orderList.get(0).orderItems.get(i).getShirt());
			}
			Shirt shirt = DialogBox.selectShirtPanel("Select which shirt to update in this order: ", shirtsInOrder, previous, current);
			int newQuantity = DialogBox.enterNumberPanel("Enter a new quantity for the shirt in this order: ", "New quantity: ",
					0, orderList.get(0).orderItems.get(0).getShirt().getShirtQuantity());
			if (newQuantity != -1)
			{
				Order modifiedOrder = orders.modifyOrder(orderList.get(0).geOrderID(), orderList.get(0).orderItems.get(0).getShirt().getID(), newQuantity);
				if (modifiedOrder != null)
				{
					DialogBox.plainMessage("Your order has been updated!", "Message");
					orderList = orders.viewOrder(customer_id);
				}
				else
				{
					DialogBox.plainMessage("Your order could not be updated", "Error");
				}
			}
		}
		else
		{
			int index = DialogBox.enterNumberPanel("Which order number would you like to modify: ", "Order #: ", 1, orderList.size());
			System.out.println("index: " + index);
			index--;
			ArrayList<Shirt> shirtsInOrder = new ArrayList<Shirt>();
			for (int i = 0; i < orderList.get(index).orderItems.size(); i++)
			{
				shirtsInOrder.add(orderList.get(index).orderItems.get(i).getShirt());
			}
			Shirt shirt = DialogBox.selectShirtPanel("Select which shirt to update in this order: ", shirtsInOrder, 0, shirtsInOrder.size());
			int newQuantity = DialogBox.enterNumberPanel("Enter a new quantity for the shirt in this order: ", "New quantity: ",
					0, orderList.get(0).orderItems.get(0).getShirt().getShirtQuantity());
			if (newQuantity != -1)
			{
				Order modifiedOrder = orders.modifyOrder(orderList.get(index).geOrderID(), orderList.get(0).orderItems.get(0).getShirt().getID(), newQuantity);
				if (modifiedOrder != null)
				{
					DialogBox.plainMessage("Your order has been updated!", "Message");
				}
				else
				{
					DialogBox.plainMessage("Your order could not be updated", "Error");
				}
			}
		}
	}

	private void logOut()
	{
		loggedIn = false;
		customer_id = -1;
		customer_account = null;
	}

	private void next()
	{
		int numOfShirt = shirtList.size();
		if ((current + increment) > numOfShirt)
		{
			current = numOfShirt;
			previous = current - increment;
		}
		else
		{
			previous += increment;
			current += increment;
		}
		textDisplay.setText(displayInventory(shirtList));
	}

	private void previous()
	{
		if (previous > 0)
		{
			previous -= increment;
			current -= increment;
			if (previous < 0)
			{
				previous = 0;
				current = increment;
			}
		}
	}

	private void createCustomerAccount() throws SQLException
	{
		CustomerAccount account = DialogBox.createAccountPanel(null);
		if (account == null)
		{
			JOptionPane.showMessageDialog(null, "Canceled account creation", "", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			boolean added = customerAccounts.createCustomerAccount(account);
			if (added)
			{
				JOptionPane.showMessageDialog(null, account.getCustomerFirstName() + " " + account.getCustomerLastName() +
						", your account has been created.");
				account = customerAccounts.logIn(account.getCustomerUsername(), account.getCustomerPassword());
				loggedIn = true;
				customer_id = account.getID();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "That username has already been taken");
			}
		}
	}

	private void editCustomerAccount() throws SQLException
	{
		CustomerAccount account = DialogBox.createAccountPanel(adminAccounts.searchCustomerAccounts(customer_id));
		if (account == null)
		{
			JOptionPane.showMessageDialog(null, "Canceled", "", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			boolean added = customerAccounts.editCustomerAccount(account);
			if (added)
			{
				JOptionPane.showMessageDialog(null, account.getCustomerFirstName() + " " + account.getCustomerLastName() +
						", your account has been edited.");
				customer_account = account;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "That username has already been taken");
			}
		}
	}

	private void logIn() throws SQLException {
		String[] uAndP = DialogBox.logIn();
		if (uAndP != null)
		{
			CustomerAccount account = customerAccounts.logIn(uAndP[0], uAndP[1]);
			if (account != null)
			{
				DialogBox.plainMessage("Welcome back " + account.getCustomerFirstName() + " " + account.getCustomerLastName() + ".\nHappy shopping!", "Log in successful");
				loggedIn = true;
				customer_id = account.getID();
				customer_account = account;
				boolean isUpdated = cart.removeOutOfStockShirts(customer_id);
				System.out.println("isUpdated: " + isUpdated);
				if (isUpdated)
				{
					DialogBox.plainMessage("Some of the shirts in your cart exceed the amount in our inventory, so your cart has been changed to reflect this", "Message");
				}
			}
			else
			{
				DialogBox.plainMessage("Username or password is incorrect", "Uh oh");
			}
		}
	}

	private void viewShirt()
	{
		selectedShirt = DialogBox.selectShirtPanel("Select which shirt to view:", shirtList, previous, current);
		if (selectedShirt != null)
		{
			setMenu(SHIRT_DETAIL_MENU);
		}
	}

	private void addToCart(Shirt shirt, int min, int max) throws SQLException {
		if (loggedIn)
		{
			if (shirt == null) {
				shirt = DialogBox.selectShirtPanel("Select which shirt to add to your cart:", shirtList, min, max);
			}
			int quantity = -1;
			quantity = DialogBox.enterNumberPanel("Enter your desired quantity below", "Quantity", 0, shirt.getShirtQuantity());
			if (quantity > 0) {
				boolean added = false;
				try {
					added = cart.addToCart(shirt.getID(), quantity, customer_id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (added) {
					JOptionPane.showMessageDialog(null, "The shirt has been added to your cart!", "Added to Cart", JOptionPane.PLAIN_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Sorry, we don't have that many of that shirt in stock", "Uh Oh!", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
		else
		{
			int selection = DialogBox.logInOrCreateAccountPanel(new String[] {"Log in", "Create Account"});
			if (selection == 0)
			{
				logIn();
			}
			else if (selection == 1)
			{
				createCustomerAccount();
			}
		}
	}

	private String displayInventory(ArrayList<Shirt> shirtList)
	{
		return HTMLView.viewShirts(shirtList, previous, current);
	}
	
	private String[] updateMainMenu(boolean loggedIn, boolean isPreviousAvailable)
	{
		if (!loggedIn && !isPreviousAvailable)
		{
			String[] menu = new String[6];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Next " + increment + " Shirts";
			menu[3] = "Search for Shirt";
			menu[4] = "Create Account";
			menu[5] = "Log In";
			return menu;
		}
		else if (!loggedIn && isPreviousAvailable)
		{
			String[] menu = new String[7];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Next " + increment + " Shirts";
			menu[3] = "Previous " + increment + " Shirts";
			menu[4] = "Search for Shirt";
			menu[5] = "Create Account";
			menu[6] = "Log In";
			return menu;
		}
		else if (loggedIn && !isPreviousAvailable)
		{
			String[] menu = new String[8];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Next " + increment + " Shirts";
			menu[3] = "Search for Shirt";
			try {
				menu[4] = "View Cart (" + cart.getNumOfDifferentShirtInCart(customer_id) + ")";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			menu[5] = "View Orders";
			menu[6] = "Edit Account";
			menu[7] = "Log Out";
			return menu;
		}
		else 
		{
			String[] menu = new String[9];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Next " + increment + " Shirts";
			menu[3] = "Previous " + increment + " Shirts";
			menu[4] = "Search for Shirt";
			try {
				menu[5] = "View Cart (" + cart.getNumOfDifferentShirtInCart(customer_id) + ")";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			menu[6] = "View Orders";
			menu[7] = "Edit Account";
			menu[8] = "Log Out";
			return menu;
		}
	}

	private String[] cartMenu()
	{
		String[] menu = new String[5];
		menu[0] = "Update Quantity";
		menu[1] = "Remove all of one shirt";
		menu[2] = "Empty Cart";
		menu[3] = "Checkout";
		menu[4] = "<< Back";
		return menu;
	}

	private String[] orderMenu()
	{
		String[] menu = new String[3];
		menu[0] = "Modify Order";
		menu[1] = "Cancel Order";
		menu[2] = "<< Back";
		return menu;
	}

	private String[] shirtDetailMenu()
	{
		if (loggedIn)
		{
			String[] menu = new String[5];
			menu[0] = "Add Shirt to Cart";
			try {
				menu[1] = "View Cart (" + cart.getNumOfDifferentShirtInCart(customer_id) + ")";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			menu[2] = "Search Shirt";
			menu[3] = "View Orders";
			menu[4] = "<< Back";
			return menu;
		}
		else
		{
			String[] menu = new String[3];
			menu[0] = "Add Shirt to Cart";
			menu[1] = "Search Shirt";
			menu[2] = "<< Back";
			return menu;
		}
	}

	private String[] shirtSearchMenu()
	{
		if (shirtList.size() <= increment)
		{
			String[] menu = new String[4];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Search for Shirt";
			menu[3] = "<< Back";
			return menu;
		}
		else if (shirtList.size() > increment && previous == 0)
		{
			String[] menu = new String[5];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Next " + increment + " Results";
			menu[3] = "Search for Shirt";
			menu[4] = "<< Back";
			return menu;
		}
		else
		{
			String[] menu = new String[6];
			menu[0] = "View Shirt";
			menu[1] = "Add Shirt to Cart";
			menu[2] = "Next " + increment + " Results";
			menu[3] = "Previous " + increment + " Results";
			menu[4] = "Search for Shirt";
			menu[5] = "<< Back";
			return menu;
		}
	}

	private void setMenu(int newMenu)
	{
		currentMenu = newMenu;
		if (currentMenu == MAIN_MENU)
		{
			previous = 0;
			current = increment;
		}
	}


}
