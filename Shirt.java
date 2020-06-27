import java.util.Iterator;
import java.util.List;

/**
 * <p>A collection of methods that keeps track of the attributes of a Shirt</p>
 * <p>Shirt has an aggregation relationship with InventoryJDBC, where Shirt can exist without the existance of InventoryJDBC</p>
 * @author Java Group 2
 */

public class Shirt {

    private int iD;
    private String name;
    private String description;
    private double price;
    private String size;
    private String gender;
    private String color;
    private int quantity;

    /**
     * <p> Constructs an instance of shirt </p>
     * @param name the name
     * @param description a description of shirt
     * @param price the cost of shirt
     * @param gender list of genders.  An example is men, women
     * @param quantity an int of the number of shirt
     * @param iD the id of shirt assigned by the database
     * @author GerardCancino
     */
    public Shirt(int iD, String name, String description, double price, String gender,String color, String size, int quantity) {
        this.iD = iD;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.gender = gender;
        this.color = color;
        this.quantity = quantity;
    }

    /**
     * <p> edits items from editable form passed in id number </p>
     * @author JuvonHyatt
     */
    public Shirt editItem() {
        return null;
    }

    /**
     * <p> this method is activated when the user wants to view the details about a specific shirt </p>
     * @return its attributes such as, description, price, size, and color
     * @author Gerard Cancino and Sarthak Thakur
     */

    public String toString() {
        String retString = "";
        retString ="Shirt ID: " + iD +"\nprice: " + price + "\nsize: " + size +"\nGender:" + gender+ "\nColor: "+ color +"\nDescription: "+ description +"\nStock: " + quantity ;


        return retString;
    }

    /**
     * <p> gets the shirt quantity </p>
     * @return the quantity of this instance of shirt
     * @author GerardCancino and SarthakThakur
     */
    public int getShirtQuantity() {

        return quantity;
    }

    /**
     * <p> gets the name of shirt </p>
     * @return name of shirt in string
     * @author GerardCancino and SarthakThakur
     */
    public String getName() {
        return name;
    }

    /**
     * <p> gets the description of shirt </p>
     * @return description of shirt in string
     * @author GerardCancino and SarthakThakur
     */
    public String getDescription() {
        return description;
    }

    /**
     * <p> gets the price of shirt </p>
     * @return price of shirt in double
     * @author GerardCancino  and SarthakThakur
     */
    public double getPrice() {
        return price;
    }

    /**
     * <p> gets all the sizes of shirts </p>
     * @return a list of sizes
     * @author GerardCancino  and SarthakThakur
     */
    public String getSize() {
        return size;
    }

    /**
     * <p> gets all the genders of shirts </p>
     * @return a list of genders
     * @author GerardCancino  and SarthakThakur
     */
    public String getGender() {
        return gender;
    }

    /**
     * <p> gets all the colors of shirts </p>
     * @return a list of colors
     * @author GerardCancino  and SarthakThakur
     */
    public String getColor() {
        return color;
    }

    /**
     * <p> gets the shirt's id </p>
     * @return the id of the shirt
     * @author GerardCancino and SarthakThakur
     */
    public int getID() {
        return iD;
    }

    /**
     * <p> sets the name of shirt </p>
     * @param shirtID ID of shirt
     * @author GerardCancino and SarthakThakur
     */
    public void setID(int shirtID) {
        this.iD = shirtID;
    }

    /**
     * <p> sets a list of shirts </p>
     * @param size A List of Shirts
     * @author GerardCancino and SarthakThakur
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * <p> sets a list of genders </p>
     * @param gender A List of Shirts
     * @author GerardCancino and SarthakThakur
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * <p> sets a list of colors</p>
     * @param color A list of colors
     * @author GerardCancino and SarthakThakur
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * <p> sets the description of a shirt</p>
     * @param description A description of shirt in a string
     * @author GerardCancino and SarthakThakur
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * <p> sets the prices </p>
     * @param price the new value of price
     * @author GerardCancino and SarthakThakur
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * <p> sets the name </p>
     * @param name the new value of name
     * @author GerardCancino and SarthakThakur
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <p> sets the quantity of shirts </p>
     * @param shirtQuantity the new value of name
     * @author GerardCancino and SarthakThakur
     */
    public void setShirtQuantity(int shirtQuantity) {
        this.quantity = shirtQuantity;
    }

}