/**
 * Created by Java Group 1: Team 2 on 4/13/18.
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> A collection of methods used to pass parameters to InventoryDB.</p>
 * <p> Admins are able to modify the inventory by deleting, inserting, and shipping shirts</p>
 * <p> Inventory has a composition relationship with InventoryDB. In other words, Inventory can't exist without InventoryDB</p>
 * @author GerardCancino
 */
public class Inventory {
    InventoryJDBC invDB;

    /**
     * <p>Constructs Inventory for the sole purpose of passing in parameters to InventoryDB</p>
     */
    public Inventory() throws SQLException, ClassNotFoundException {
        invDB = new InventoryJDBC();
    }

    /**
     * <p>Allows the customer or admin to view all merchandises.</p>
     * <p>The parameters are sent to a corresponding method in inventory database.  It would return the data of the corresponding method</p>
     * @return a list of all shirts.  Looped to get all the names and prices for later display
     * @author GerardCancino
     */

    public ArrayList<Shirt> browseInventory() throws SQLException {
        return invDB.browseInventory();
    }

    /**
     * Gets a list of shirts based on attributes given
     * @param name condition to get shirts based on a specific name
     * @param size condition to get shirts based on a specific size
     * @param gender condition to get shirts based on a specific gender
     * @param color condition to get shirts based on a specific color
     * @param minPrice condition to get shirts based on a specific price
     * @param maxPrice condition to get shirts based on a specific price
     * @return a list of shirts based on attributes
     * @author
     */
    public ArrayList<Shirt> searchInventory(String name, String size, String gender, String color, int minPrice, int maxPrice) throws SQLException {
        return invDB.searchInventory(name, size, gender, color, minPrice, maxPrice);
    }

    /**
     * <p> Orders that are completed go through this method, afterwards the quantity of the specific shirt is decremented with amount of the items that have been ordered </p>
     * @param orderNumber gets the order the Customer placed by using its order Number
     * @return true if order Number and orderInventory is successfully found
     * @author SarthakThakur
     */
    public boolean shipShirt(int orderNumber) throws SQLException {
        return invDB.shipShirt(orderNumber);
    }

    /**
     * @param id user passes in shirts id to get the corresponding shirts attributes.
     * @return returns shirt from the result set.
     * @author Sarthak Thakur
     */
    public Shirt viewShirtDetails(int id) throws SQLException {return invDB.viewShirtDetails(id);}

    /**
     * <p> creates new shirt in inventory </p>
     * @param inputtedShirt shirt to insert into the database
     * @author Juvon Hyatt
     */
    public Shirt insertShirt(Shirt inputtedShirt) throws SQLException {
        return invDB.insertShirt(inputtedShirt);
    }

    /**
     * <p> Selects an item based off of it's id, and then deletes the whole row.</p>
     * <p> It returns true or false if it was delete successfully.  </p>
     * @param inputtedID passed ID of a shirt that is going to be deleted
     * @return the deleted shirt for potential reinsertion or edit
     * @author Gerard Cancino
     */
    public boolean deleteShirt(int inputtedID) throws SQLException {
        return invDB.deleteShirt(inputtedID);
    }

    public boolean editShirt(int shirtID, String attributeChoice, int switchChoice) throws SQLException {
        return invDB.editShirt(shirtID,attributeChoice,switchChoice);
    }
}