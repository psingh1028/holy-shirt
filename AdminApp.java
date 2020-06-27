import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;


import java.util.List;

public class AdminApp
{
	static Orders orders;

	static {
		try {
			orders = new Orders();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static CustomerAccounts customerAccounts;

	static {
		try {
			customerAccounts = new CustomerAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static AdminAccounts adminAccounts;

	static {
		try {
			adminAccounts = new AdminAccounts();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static int admin_id = 1;
	static boolean loggedIn = false;
	static Inventory inventory;

	static {
		try {
			inventory = new Inventory();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static int previous = 0;
	static int increment = 5;
	static int current = increment;
	static int back = -1;

	static final int MAIN_MENU = 0;
	static final int ORDER_MENU = 2;
	static final int ORDER_DETAIL_MENU = 3;
	static final int SHIRT_DETAIL_MENU = 4;
	
	
	static String welcome = "  _  _     _        ___ _    _     _   _ \n"
							+ " | || |___| |_  _  / __| |_ (_)_ _| |_| |\n"
							+ " | __ / _ \\ | || | \\__ \\ ' \\| | '_|  _|_|\n"
							+ " |_||_\\___/_|\\_, | |___/_||_|_|_|  \\__(_)\n"
							+ "             |__/                        	\n"
							+ "-----------------------------------------\n"
							+ "It's what you'll be saying when you see our selection!\n"
							+ "(minus the 'r')\n\n";
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		System.out.println(welcome);
		MainMenu1(new Scanner(System.in));
	}
	private static void createAccount(Scanner sc) throws SQLException {
		boolean run = true;
		while (run)
		{
			System.out.println("Enter your first name: \n");
			String admin_Fname_input = sc.next();
			System.out.println("Enter your last name: \n");
			String admin_Lname_input = sc.next();
			System.out.println("Enter your E-mail Address: \n");
			String admin_Email_input = sc.next();
			System.out.println("Enter your Username: \n");
			String admin_Username_input = sc.next();
			System.out.println("Enter your Password: \n");
			String admin_Password_input = sc.next();
			AdminAccount newAccount = new AdminAccount(admin_Fname_input,admin_Lname_input, admin_Username_input, 
					admin_Password_input, admin_Email_input,-1);
			boolean isCreated = adminAccounts.createAdminAccount(newAccount);
			if (isCreated)
			{
				System.out.println(newAccount.getAdminFirstName() + " " + newAccount.getAdminLastName() 
				+ ", Your account has been created. Happy shopping!");
				run = false;
			}
			else
			{
				System.out.println("Invalid information. Your account has not been created!");
				System.out.println("Try again (Y/N)?: ");
				String s = sc.next();
				if (s.equalsIgnoreCase("N"))
				{
					run = false;
				}
			}
		}
	}
	public static String updateSubMenu() throws SQLException
	{
		String menu="";
		if (!loggedIn)
		{
			menu = ""
					+ "MAIN MENU: \n\n"
					+ "1) Create Account\n"
					+ "2) Log In\n";
		}
		
		else if(loggedIn)
		{
 			menu = ""
					+ "MAIN MENU: \n\n"
					+ "1) Inventory Management\n"
					+ "2) Account Management\n"
					+ "3) Order Management\n"
					+ "4) Log Out\n";
		}
		return menu;
	}
	public static void MainMenu1(Scanner sc) throws SQLException, ClassNotFoundException {
		List<Shirt> shirtList = inventory.browseInventory();
		//displayInventory(shirtList);
		
		System.out.println(updateSubMenu());
		
		int selection = sc.nextInt(); 
		boolean run = true;
		while (run) 
		{
			switch (selection) 
			{
			case 1:
			{
				if(loggedIn)
				{
				InventoryMenu(sc);
				}
				else if(!loggedIn)
				{
					createAccount(sc);
				}
				break;
			}
			case 2:
			{
				if (loggedIn)
				{
					AccountManagementMenu(sc);;
				}
				else if(!loggedIn)
				{
					logIn(sc);
				}
				break;
			}
			//Order Management
			case 3:
			{
				OrderMenu(sc);
				break;
			}
			//delete shirt
			case 4:
			{
				if(loggedIn)
				{
				loggedIn=false;
				System.out.println("You are now logged out. Have a nice day!");
				}
				break;
			}
			
			// NONE OF THE ABOVE
			default:
				System.out.println("Invalid option. Returning to menu."); 
				break;
			}
			if (run) 
			{
 
				//displayInventory(shirtList);
				System.out.println(updateSubMenu());
				selection = sc.nextInt(); 
			}
		}
		System.out.println("Program shutdown."); 
		sc.close();
	}

	public static void logIn(Scanner sc) throws SQLException {
		while (!loggedIn)
		{
			System.out.print("Enter your username: ");
			String username = sc.next();
			System.out.print("Enter your password: ");
			String password = sc.next();
			AdminAccount account = adminAccounts.loginAdmin(username, password);
			if (account != null)
			{
				admin_id = account.getID();
				loggedIn = true;
				System.out.print("Welcome back " + account.getAdminFirstName() + " " + account.getAdminLastName() + ". You are logged in. Happy shopping!\n \n");
				//System.out.println("loggedIn?: " + loggedIn);
			}
			else
			{
				System.out.print("Incorrect username or password. Would you like to try again? (Y/N): ");
				String a = sc.next();
				if (a.equalsIgnoreCase("N"))
				{
					break;
				}
			}
		}
	}
	public static void AccountManagementMenu(Scanner sc) throws SQLException {
		String menu = "ACCOUNT MENU: \n\n" // create String menu to simulate a menu for user to interact with, giving the user 5 options
				+ "1) Delete Account\n"
				+ "2) Change Password\n"
				+ "3) Search Customer Accounts\n"
				+ "4) Search Admin Accounts\n"
				+ "5) Return to main menu\n";
		boolean run = true; // set to true to keep running the below while statement until changed to false
		System.out.println(menu); // print menu string
		int selection = sc.nextInt(); // grab user input for menu selection
		while (run) // keep user in menu until the user quits
		{
			switch (selection) // a switch statement is used to branch the following cases
			{
			//Delete Account
			case 1:
			{
				if (loggedIn)
				{
					System.out.print("Enter the Admin ID of the account you wish to delete: ");
					int admin_Id = sc.nextInt();
					boolean isDeleted = adminAccounts.deleteAdminAccount(admin_Id);
					if(isDeleted)
					{
						System.out.println("Account: " + admin_Id + " has been deleted");
					}
					else
					{
						System.out.println("Account not found and can not be deleted.");
					}
					break;
				}
			}
			//Change Password
			case 2:
			{
				System.out.println("Enter your username");
				String username= sc.next();
				System.out.print("Enter your desired password: ");
				String newPassword =sc.next();
				boolean account = adminAccounts.updateAdminPassword(admin_id, newPassword);
				System.out.println("Your password has been changed to: " + newPassword);
			
				
				
				break;
				
			}
				
			//Search Customer Accounts
			case 3:
			{
				if (loggedIn)
				{
					System.out.print("Enter a Customer ID number to search for: ");
					int customerId = sc.nextInt();
					CustomerAccount searchedAccount = customerAccounts.searchCustomerAccount(customerId);
					System.out.println(searchedAccount);
				} 
				break;
			}
			//Search Admin Accounts
			case 4:
			{
				if (loggedIn)
				{
					System.out.print("Enter a Admin ID number to search for: ");
					int admin_Id = sc.nextInt();
					AdminAccount searchedAccount = adminAccounts.searchAdminAccount(admin_Id);
					System.out.println(searchedAccount);
				} 
				break;
			}
			//Return to main menu
			case 5: 
			{
				run = false;
				break;
			}
			// NONE OF THE ABOVE
			default:
				System.out.println("Invalid option. Returning to menu."); // user input is not a valid option, therefore return user back to menu
				break;
			}
			if (run) // if run is still true (or if the user has not quit the program)
			{
				System.out.println(menu);
				selection = sc.nextInt(); // repeat process
			}
		}
	}
	private static void InventoryMenu(Scanner scannow) throws SQLException {
        int choice = 0;
        while(choice!=1) {
            String menu = "--Inventory Menu--\n" // create String menu to simulate a menu for user to interact with, giving the user 8 options
                    + "1) Back to Main Method\n"
                    + "2) Browse Inventory\n"
                    + "3) Search Inventory\n"
                    + "4) Insert Shirt\n";
            System.out.print(menu);

            choice = loopValidShirtOption(scannow);

            switch (choice) {
                case 1:
                    //Note: Do not call the Main Menu. In while loop so it will
                    break;
                case 2:
                    browse(scannow,false);
                    break;
                case 3:
                    browse(scannow,true);
                    break;
                case 4:
                    createShirt(scannow);
                    break;
                default: System.out.print("Please Enter a Valid Option\n");
                    break;
            }
        }
    }

    /**
     * <p> Allows user to view broad info on multiple items </p>
     * @param scannow allows user to input
     * @param isSearch checks if browse or search was called.
     * @author Gerard Cancino
     */
    private static void browse(Scanner scannow, boolean isSearch) throws SQLException {
        List <Shirt> browseInv = inventory.browseInventory();
        if(!isSearch)
			inventory.browseInventory();
        else
            browseInv = searchShirt(scannow);
        if(browseInv == null) {
            browseInv = inventory.browseInventory();
            System.out.print("\nNo Shirts Found in Search.  Defaulting to Browse Inventory\n");
        }
        //Variables for next and prev
        int current = 0; // pointer
        int choice = 6; //switch case statement
        int prev = current; //record previous pointer after each loop
        while (choice != 1) {

            if (choice == 7) { //choice 6 = previous 5 shirts.
                prev = prev - 5;
                current = prev;
            }
            if (choice == 6 || choice == 7) { //get next 5
                int next = current + 5;
                prev = current;
                if(isSearch)
                	System.out.print("--Search Inventory--\n");
                else
                	System.out.print("--Browse Inventory--\n");
                if(browseInv.isEmpty())
                    System.out.print("No Shirts in Inventory\n");
                while (current < next && current < browseInv.size()) {
                    Shirt temp = browseInv.get(current);
                    String strShirt = "| ID: " + temp.getID() + " | Name: " + temp.getName() + " | Description: " + temp.getDescription() + " | Price: " + temp.getPrice() + " |";
                    System.out.print(strShirt + "\n");
                    ++current;
                }
                System.out.print("--------------------\n");
            }

            //Options
            System.out.print("Please Enter an Option: \n");
            String options = "1) Go Back to Main\n" +
                    "2) Edit Shirt\n" +
                    "3) Delete Shirt\n" +
                    "4) View More Details on a Shirt\n";

            if(isSearch)
            	options += "5) Browse Inventory\n";
            else
            	options += "5) Search Inventory\n";

            if (current < browseInv.size()) {
                options += "6) Next 5 Shirts\n";
            }
            if (current > 5) {
                options += "7) Previous 5 Shirts\n";
            }

            System.out.print(options);

            choice = loopValidShirtOption(scannow);

            // analyze choice
            switch (choice) {
                case 1:
                    //Main Menu. Out of while goes to menu
                    break;
                case 2:
                    System.out.print("\n--Edit Form--\n");
                    int inputtedShirtID1 = loopValidShirtId(scannow);
                    System.out.print(inventory.viewShirtDetails(inputtedShirtID1).toString());
                    editShirtMenu(scannow,inputtedShirtID1);
                    browseInv = inventory.browseInventory();
                    current = 0;
                    break;
                case 3:
                    System.out.print("\n--Deletion Form--\n");
                    int inputtedShirtID2 = loopValidShirtId(scannow);
                    deleteShirtMenu(scannow,inputtedShirtID2);
                    browseInv = inventory.browseInventory();
                    current = 0;
                    choice = 6;
                    break;
                case 4:
                    System.out.println("\nView Details Form\n");
                    int inputtedShirtID3 = loopValidShirtId(scannow);
                    viewShirt(scannow,inputtedShirtID3);
                    browseInv = inventory.browseInventory();
                    current = 0;
                    choice = 6;
                    break;
                case 5:
                	if(!isSearch)
                    	browse(scannow,true);
                	else
                		browse(scannow,false);
                case 6:
                    if (current == browseInv.size()) {
                        System.out.print("Please Enter a Valid Option\n");
                        choice = 8;
                    }
                    break;
                case 7:
                    if (current == 5) {
                        System.out.print("Please Enter a Valid Option\n");
                        choice = 8;
                    }
                    break;
                default:
                    System.out.print("Please Enter a Valid Option\n");
            }
        }
    }

    private static ArrayList<Shirt> searchShirt(Scanner scannow) throws SQLException {
        System.out.print("Enter the name to search (press enter to skip): ");
        String name = scannow.nextLine();
        System.out.print("Enter the size to search (press enter to skip): ");
        String size = scannow.nextLine();
        System.out.print("Enter the gender to search (press enter to skip): ");
        String gender = scannow.nextLine();
        System.out.print("Enter the color to search (press enter to skip): ");
        String color = scannow.nextLine();
        System.out.print("Enter the minimum price to search (press enter to skip): ");
        String minPrice_s = scannow.nextLine();
        int minPrice = -1;
        if (!minPrice_s.equals(""))
            minPrice = Integer.valueOf(minPrice_s);
        System.out.print("Enter the maximum price to search (press enter to skip): ");
        String maxPrice_s = scannow.nextLine();
        int maxPrice = -1;
        if (!maxPrice_s.equals(""))
            maxPrice = Integer.valueOf(maxPrice_s);
        ArrayList<Shirt> searchedShirts = inventory.searchInventory(name, size, gender, color, minPrice, maxPrice);
        return searchedShirts;
    }


    /**
     * <p> Loops input to get a valid id of a shirt </p>
     * @param scannow Instance of Scanner
     * @return the inputted id in an integer
     * @author Gerard Cancino
     */
    private static int loopValidShirtId(Scanner scannow){
        int retShirtID;
        System.out.print("Please Enter the ShirtID: ");
        retShirtID = isValidOptionShirt(scannow.nextLine());
        while(retShirtID == -1) {
            System.out.print("Please Enter the ShirtID: ");
            retShirtID = isValidOptionShirt(scannow.nextLine());
        }
        return retShirtID;
    }

    /**
     * <p> Loops input to get a correct Option</p>
     * @param scannow allows for input
     * @return returns an int of an option
     * @author Gerard Cancino
     */
    private static String loopValidStringShirt(Scanner scannow){
        String retStr = null;
        retStr = scannow.nextLine().trim();
        while(retStr == null || retStr.compareTo("")==0) {
            System.out.print("\nPlease enter a Valid Input: ");
            retStr = scannow.nextLine().trim();
        }
        return retStr;
    }

	/**
	 * <p> Loops input to get a correct Option</p>
	 * @param scannow allows for input
	 * @return returns an int of an option
	 * @author Gerard Cancino
	 */
	private static int loopValidShirtOption(Scanner scannow){
		int retOption;
		System.out.print("Option: #");
		retOption = isValidOptionShirt(scannow.nextLine());
		while(retOption == -1 || retOption == -2) {
			if(retOption == -2)
				System.out.print("\nPlease enter a Valid Option: ");
			if(retOption == -1)
				System.out.print("");
			retOption = isValidOptionShirt(scannow.nextLine());
		}
		return retOption;
	}

    /**
     * <p> Checks the input. </p>
     * <p> Scanner works by getting a string then attempting to parse it to a string.</p>
     * <p> The function does the same, except more oriented to this project's needs</p>
     * @param testInput A string that will be converted to an int
     * @return an int in a correct format
     * @author Gerard Cancino
     */
    private static int isValidOptionShirt(String testInput){
        testInput = testInput.trim();
        if(testInput.compareTo("")==0 ) {
            return -1;
        }
        else if (testInput == null){
        	return -2;
		}
        else {
            try{
                int retInput = Integer.parseInt(testInput);
                return retInput;
            }
            catch(NumberFormatException nfe){
                return -1;
            }
        }
    }

    /**
     * <p> Confirms if the user wants to proceed </p>
     * @param scannow Instance of Scanner.  Allows user to input
     * @return true or false depending if the user wants to proceed or note.
     * @author Gerard Cancino
     */
    private static boolean isConfirmedShirt(Scanner scannow) {
        int choice = 1;
        while (choice == 1 || choice == 2) {
            System.out.print("Are you sure?\n" +
                    "1) No\n" +
                    "2) Yes\n");
            choice = loopValidShirtOption(scannow);

            if (choice == 1)
                return false;
            else if (choice == 2)
                return true;
            else
                System.out.print("Invalid Choice");
        }
        return false;
    }

    /**
     * @author Juvon Hyatt
     * @param scannow instance of scanner
     * @param inputID the id of a shirt
     */
    private static void viewShirt(Scanner scannow, int inputID) throws SQLException {
        Shirt current = inventory.viewShirtDetails(inputID);
        System.out.print(current.toString()+"\n");
        int choice = 0;
        while(choice != 1){
            String output = "1) Main Menu\n" +
                    "2) Delete Current Shirt\n" +
                    "3) Edit Current Shirt\n";
            System.out.print(output);
            choice = loopValidShirtOption(scannow);
            switch(choice) {
                case 1:
                    break;
                case 2:
					deleteShirtMenu(scannow,inputID);
                    break;
                case 3:
					editShirtMenu(scannow,inputID);
                    break;
                default:
                    System.out.print("Invalid Option\n");
                break;
            }
        }
    }

    /**
     * <p> Deletes a row of shirt based on an inputted shirt id </p>
     * @param scannow allows user to input into the program
     * @param inputID the id of a shirt that will be deleted
     * @author Gerard Cancino
     */
    private static void deleteShirtMenu(Scanner scannow, int inputID) throws SQLException {
        Shirt view = inventory.viewShirtDetails(inputID);
        if(view == null)
			System.out.print("Could not find shirt\n");
		else {
			boolean deleteShirt = false;
			boolean isConfirmed = isConfirmedShirt(scannow);
			if (isConfirmed) {
				deleteShirt = inventory.deleteShirt(inputID);
				if (deleteShirt)
					System.out.print("Deletion Successful!\n");
				else
					System.out.print("Deletion Failed\n");
			}
		}
        //Goes to main menu automatically
    }

    /**
     * @author Sarthak Thakur
     */
    private static void createShirt(Scanner scannow) throws SQLException {
        String genderStr = null;
        String nameStr = null;
        String descriptionStr = null;
        String sizeStr = null;
        String colorStr = null;

        DecimalFormat numberFormat = new DecimalFormat("#.00");
        while (nameStr == null) {
            System.out.print("What is the name of the shirt: ");
            if (scannow.hasNextLine()) {
                nameStr = scannow.nextLine();
                if(nameStr.length()>255)
                {
                    nameStr=null;
                }
            }
        }
        while (descriptionStr == null) {
            System.out.print("What is the description of the shirt: ");
            if (scannow.hasNextLine()) {
                descriptionStr = scannow.nextLine();
            }
        }
        double price = -1;
        while(price == -1) {
            System.out.print("What is the price of the shirt: ");
            if (!scannow.hasNextDouble()) {
                scannow.next();
            }
            else
                price = scannow.nextDouble();
        }
        double  priceD = Math.floor(price*100)/100;
        double priceFormatted = Double.parseDouble(numberFormat.format((priceD)));
        int gender = -1;
        while(gender==-1) {
            System.out.print("1 = Men\n" +
                    "2 = Women\n" +
                    "0 = to exit\n" +
                    "Enter a integer for gender: ");
            if (!scannow.hasNextInt()) {
                scannow.next();
            }
            else {
                gender = scannow.nextInt();

                switch (gender) {
                    case 0:
                        //MainMenu(new Scanner(System.in));
                        break;
                    case 1:
                        genderStr = "Men";
                        break;
                    case 2:
                        genderStr = "Women";
                        break;
                    default:
                        System.out.print("Please enter a valid choice.");
                        gender = -1;
                        break;
                }

            }
        }
        int size = -1;
        while(size==-1) {
            System.out.print("1 = Small\n" +
                    "2 = Medium\n" +
                    "3 = Large\n" +
                    "0 = to exit\n" +
                    "Enter a integer for size: ");
            if (!scannow.hasNextInt()) {
                scannow.next();
            }
            else {
                size = scannow.nextInt();
                switch (size) {
                    case 0:
                        //MainMenu(new Scanner(System.in));
                        break;
                    case 1:
                        sizeStr = "S";
                        break;
                    case 2:
                        sizeStr = "M";
                        break;
                    case 3:
                        sizeStr = "L";
                        break;
                    default:
                        System.out.print("Please enter a valid choice.");
                        size = -1;
                        break;
                }


            }
        }
        int color = -1;
        while(color==-1) {
            System.out.print("1 = Red\n" +
                    "2 = Orange\n" +
                    "3 = Yellow\n" + "" +
                    "4 = Green\n" +
                    "5 = Blue\n" +
                    "6 = Purple\n" +
                    "0 = to exit\n" +
                    "Enter a integer for color: ");
            if (!scannow.hasNextInt()) {
                scannow.next();
            }
            else {
                color = scannow.nextInt();
                switch (color) {
                    case 0:
                        //MainMenu(new Scanner(System.in));
                        break;
                    case 1:
                        colorStr = "Red";
                        break;
                    case 2:
                        colorStr = "Orange";
                        break;
                    case 3:
                        colorStr = "Yellow";
                        break;
                    case 4:
                        colorStr = "Green";
                        break;
                    case 5:
                        colorStr = "Blue";
                        break;
                    case 6:
                        colorStr = "Purple";
                        break;
                    default:
                        System.out.print("Please enter a valid choice.");
                        color = -1;
                        break;
                }
            }
        }
        int quantity = -1;
        while(quantity==-1){
            System.out.print("Enter the quantity:");
            if(!scannow.hasNextInt())
                scannow.next();
            else
                quantity = scannow.nextInt();
        }
        Shirt inputShirt = new Shirt(-1,nameStr,descriptionStr,priceFormatted,genderStr,colorStr,sizeStr,quantity);
        Shirt test = inventory.insertShirt(inputShirt);
        System.out.print(test.toString()+"\n");
    }

    /**
     * @author Sarthak Thakur
     * @param scannow instance of Scanner Object
     * @param shirtID the id of a shirt
     */
    private static void editShirtMenu(Scanner scannow,int shirtID) throws SQLException {

        System.out.println("\nPlease select an option from the following menu:" +
                "\n1:Edit Shirt Price" + "\n2:Edit Shirt Color" + "\n3:Edit Shirt Quantity" + "\n4:Edit Shirt Gender" + "\n5:Edit Size "+"\n6:Edit Description"+ "\n7:Edit Name" + "\n0:To Quit");

        int choice = loopValidShirtOption(scannow);
        // Inventory inventory = new Inventory();
        String genderStr = null;
        String nameStr = null;
        String descriptionStr = null;
        String sizeStr = null;
        String colorStr = null;
        String strQuantity = null;
        String priceFormat = null;
        Scanner scannowEdit = new Scanner(System.in);
        DecimalFormat numberFormat = new DecimalFormat("#.00");


        //  System.out.println("\n" + invDB.shirts.get(count).toString() + "\n");


        while (choice != 0) {
            switch (choice) {

                case 1:
                    double price = -1;
                    while (price == -1) {
                        System.out.print("What is the price of the shirt: ");
                        if (!scannowEdit.hasNextDouble()) {
                            scannowEdit.nextLine().trim();
                            //

                        } else
                            price = scannowEdit.nextDouble();

                    }
                    double priceD = Math.floor(price * 100) / 100;
                    //numberFormat.format(priceD);
                    double priceFormatted = Double.parseDouble(numberFormat.format((priceD)));

                    priceFormat = Double.toString(priceFormatted);
                    if(inventory.editShirt(shirtID,priceFormat,choice)){
                        System.out.print("Success!");
                    }
                    else
                        System.out.print("Failed");
                    break;
                case 2:


                    int color = -1;


                    while (color == -1) {
                        System.out.print("1 = Red\n" +
                                "2 = Orange\n" +
                                "3 = Yellow\n" + "" +
                                "4 = Green\n" +
                                "5 = Blue\n" +
                                "6 = Purple\n" +
                                "0 = to exit\n" +
                                "Enter a integer for color: ");
                        if (!scannowEdit.hasNextInt()) {
                            scannowEdit.next();
                        } else {
                            color = scannowEdit.nextInt();
                            switch (color) {
                                case 0:
                                    //MainMenu(new Scanner(System.in));
                                    break;
                                case 1:
                                    colorStr = "Red";
                                    break;
                                case 2:
                                    colorStr = "Orange";
                                    break;
                                case 3:
                                    colorStr = "Yellow";
                                    break;
                                case 4:
                                    colorStr = "Green";
                                    break;
                                case 5:
                                    colorStr = "Blue";
                                    break;
                                case 6:
                                    colorStr = "Purple";
                                    break;
                                default:
                                    System.out.print("Please enter a valid choice.");
                                    color = -1;
                                    break;
                            }
                        }
                    }
                    if(inventory.editShirt(shirtID, colorStr, choice)){
                        System.out.print("Success!");
                    }
                    else
                        System.out.print("Failed");
                    break;
                case 3:
                    int quantity = -1;
                    while (quantity == -1) {
                        System.out.print("Enter the quantity:");
                        if (!scannowEdit.hasNextInt())
                            scannowEdit.nextLine();
                        else
                            quantity = scannowEdit.nextInt();
                        strQuantity = Integer.toString(quantity);


                    }
                    if(inventory.editShirt(shirtID, strQuantity, choice)){
                        System.out.print("Success!");
                    }
                    else
                        System.out.print("Failed");
                    break;
                case 4:

                    int gender = -1;
                    while (gender == -1) {
                        System.out.print("1 = Men\n" +
                                "2 = Women\n" +
                                "0 = to exit\n" +
                                "Enter a integer for gender: ");
                        if (!scannowEdit.hasNextInt()) {
                            scannowEdit.next();
                        } else {
                            gender = scannowEdit.nextInt();

                            switch (gender) {
                                case 0:
                                    break;
                                case 1:
                                    genderStr = "Men";
                                    break;
                                case 2:
                                    genderStr = "Women";
                                    break;
                                default:
                                    System.out.print("Please enter a valid choice.");
                                    gender = -1;
                                    break;
                            }

                        }
                    }
                    if(inventory.editShirt(shirtID, genderStr, choice)){
                        System.out.print("Success!");
                    }
                    else
                        System.out.print("Failed");
                    break;

                case 5:
                    System.out.println("Please enter new size");

                    int size = -1;

                    while (size == -1) {
                        System.out.print("1 = Small\n" +
                                "2 = Medium\n" +
                                "3 = Large\n" +
                                "0 = to exit\n" +
                                "Enter a integer for size: ");
                        if (!scannowEdit.hasNextInt()) {
                            scannowEdit.next();
                        } else {
                            size = scannowEdit.nextInt();
                            switch (size) {
                                case 0:
                                    break;
                                case 1:
                                    sizeStr = "S";
                                    break;
                                case 2:
                                    sizeStr = "M";
                                    break;
                                case 3:
                                    sizeStr = "L";
                                    break;
                                default:
                                    System.out.print("Please enter a valid choice.");
                                    size = -1;
                                    break;
                            }


                        }
                    }

                    if(inventory.editShirt(shirtID, sizeStr, choice)){
                        System.out.print("Success!");
                    }
                    else
                        System.out.print("Failed");
                    break;

                case 6:
                    System.out.print("\nWhat is the description of the shirt: ");
                    descriptionStr = loopValidStringShirt(scannow);
                    inventory.editShirt(shirtID,descriptionStr,choice);

                    if(inventory.editShirt(shirtID,descriptionStr,choice)){
                        System.out.print("Success!");
                        descriptionStr=null;
                    }
                    else {
                        System.out.print("Failed");
                    }


                    break;
                case 7:
                    while (nameStr == null) {
                        System.out.print("What is the name of the shirt: ");
                        if (scannowEdit.hasNext()) {
                            nameStr = scannowEdit.nextLine();
                            if (nameStr.length() > 255) {
                                nameStr = null;
                            }
                        }
                    }
                    if(inventory.editShirt(shirtID,nameStr,choice)==true){
                        System.out.print("Success!");
                        nameStr = null;
                    }
                    else
                        System.out.print("Failed");
                    break;
            }
            System.out.println("\nPlease select an option from the following menu:" +
                    "\n1:Edit Shirt Price" + "\n2:Edit Shirt Color" + "\n3:Edit Shirt Quantity" + "\n4:Edit Shirt Gender" + "\n5:Edit Size "+"\n6:Edit Description"+ "\n7:Edit Name" + "\n0:To Quit");
            choice = loopValidShirtOption(scannow);
        }
    }


	public static void OrderMenu(Scanner sc) throws SQLException, ClassNotFoundException {
		String menu = "ORDER MENU: \n\n" // create String menu to simulate a menu for user to interact with, giving the user 5 options
				+ "1) View order\n"
				+ "2) Cancel order\n"
				+ "3) Search Order*\n"
				+ "4) Refund order\n"
				+ "5) Ship Order\n"
				+ "6) Create Business Report\n"
				+ "7) Logout\n"
				+ "8) Return to main menu\n";
		boolean run = true; // set to true to keep running the below while statement until changed to false
		System.out.println(menu); // print menu string
		int selection = sc.nextInt(); // grab user input for menu selection
		while (run) // keep user in menu until the user quits
		{
			switch (selection) // a switch statement is used to branch the following cases
			{
			//View order
			case 1:
			{
				List<Order> orderList = orders.viewOrder(admin_id);
				if (orderList.equals(""))
				{
					System.out.println("You have no orders!");
				}
				else
				{
					
					System.out.println(orderList);
					
				}
				break;
			}
			//Cancel order
			case 2:
			{
				System.out.println("Enter the order number of the order you wish to cancel: ");
				int orderID = sc.nextInt();
				Order cancelledOrder=orders.cancelOrder(orderID);
				System.out.println("Order " + orderID + " has been cancelled");
				break;
			}
			//Search order
			case 3:
			{
				System.out.print("Enter the order ID number: ");
				int orderID = sc.nextInt();
				Order searchedOrder = orders.searchOrder(orderID);
				System.out.println(searchedOrder);
				break;
			}
			//Refund order
			case 4:
			{
				System.out.println("Enter the Order ID number you want to refund: ");
				int orderID= sc.nextInt();
				double refund = orders.refundOrder(orderID);
				System.out.println("Your order has been refunded: " + refund);
				break;
			}
			//ShipOrder
			case 5: 
			{
				System.out.print("Enter the order ID number you would like to ship: ");
				int orderID = sc.nextInt();
				boolean isShipped = inventory.shipShirt(orderID);
				if (isShipped)
				{
					System.out.println("The order has been shipped");
				}
				else
				{
					System.out.println("The order could not be shipped");
				}
				break;
			}
			//Create Business Report
			case 6:
			{
				System.out.print("Inputting a start date:\nEnter the Year: ");
				int sYear, sMonth, sDay,
				eYear, eMonth, eDay;

				// START DATE
				sYear = sc.nextInt();
				while(sYear < 0 || sYear > 9999)
				{
					System.out.println("ERROR: Invalid Year entered, please enter a valid year");
					sYear = sc.nextInt();
				}

				System.out.println("Enter the Month: ");
				sMonth = sc.nextInt();
				while(sMonth < 1 || sMonth > 12)
				{
					System.out.println("ERROR: Invalid Month entered, please enter a valid month");
					sMonth = sc.nextInt();
				}

				System.out.println("Enter the Day: ");
				sDay = sc.nextInt();
				while(sDay < 1 || sDay > 31)
				{
					System.out.println("ERROR: Invalid Date entered, please enter a valid date");
					sDay = sc.nextInt();
				}

				// END DATE
				System.out.print("Inputting an end date:\nEnter the Year: ");
				eYear = sc.nextInt();
				while(eYear < 0 || eYear > 9999)
				{
					System.out.println("ERROR: Invalid Year entered, please enter a valid year");
					eYear = sc.nextInt();
				}

				System.out.println("Enter the Month: ");
				eMonth = sc.nextInt();
				while(eMonth < 1 || eMonth > 12)
				{
					System.out.println("ERROR: Invalid Month entered, please enter a valid month");
					eMonth = sc.nextInt();
				}

				System.out.println("Enter the Day: ");
				eDay = sc.nextInt();
				while(eDay < 1 || eDay > 31)
				{
					System.out.println("ERROR: Invalid Date entered, please enter a valid date");
					eDay = sc.nextInt();
				}

				//Initialize the startDate
				Calendar startDate = Calendar.getInstance();
				startDate.set(sYear, sMonth - 1, sDay);

				//Initialize the endDate
				Calendar endDate = Calendar.getInstance();
				endDate.set(eYear, eMonth - 1, eDay);

				Orders businessOrders = new Orders();
				System.out.println(businessOrders.createBusinessReport(startDate, endDate));
				break;
				
			}
			case 7:
			{
				if(loggedIn)
				{
				loggedIn=false;
				run=false;
				System.out.println("You are now logged out. Have a nice day!");
				}
				break;
			}
			case 8:
			{
				run=false;
				break;
			}
			// NONE OF THE ABOVE
			default:
				System.out.println("Invalid option. Returning to menu."); // user input is not a valid option, therefore return user back to menu
				break;
			}
			if (run) // if run is still true (or if the user has not quit the program)
			{
				System.out.println(menu);
				selection = sc.nextInt(); // repeat process
			}
		}
	}

}


