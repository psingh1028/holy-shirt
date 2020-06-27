/**
 * Created by Java Group 1: Team 2 on 4/13/18.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> A collection of methods that enables customers and admins to access a list of shirts from the database</p>
 * <p> Admins are able to modify the inventory by deleting, inserting, and shipping shirts</p>
 * <p> InventoryJDBC has a composition relationship with Inventory.  In other words, Inventory can't exist without InventoryJDBC.  It also has a aggregation relationship with Shirt.  In other words, Shirt can exist when InventoryJDBC doesn't.  This can be shown when Shirt is used in Order</p>
 * @author GerardCancino
 */
public class InventoryJDBC
{
    private static Statement stmt = null;
    private static Connection conn = null;

    public InventoryJDBC() throws SQLException, ClassNotFoundException
    {
        Class.forName(DBInfo.JDBC_DRIVER);
        conn = DriverManager.getConnection(DBInfo.DB_URL,DBInfo.username, DBInfo.password);
        stmt = conn.createStatement();
    }

    public ArrayList<Shirt> browseInventory() throws SQLException
    {
        ArrayList<Shirt> al = new ArrayList<Shirt>();

        String sql = "SELECT * FROM Shirt; ";

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

            Shirt shirt = new Shirt(shirtID, shirtName, shirtDesc, shirtPrice, shirtGender, shirtColor, shirtSize, shirtQuantity);

            al.add(shirt);

        }

        rs.close();

        return al;
    }

    public Shirt viewShirtDetails(int id) throws SQLException
    {
        int SHIRT_ID= 0;
        String SHIRT_NAME="null";
        String SHIRT_SIZE="null";
        String SHIRT_COLOR="null" ;
        String SHIRT_DESC="null";
        double SHIRT_PRICE=0;
        String SHIRT_GENDER="null";
        int SHIRT_QUANTITY=0;
        Shirt shirtRes = null;

        String sql = "SELECT * FROM Shirt WHERE SHIRT_ID ="+id+";";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next())
        {
            //Retrieve by column name
            shirtRes.setID(rs.getInt("SHIRT_ID"));
            shirtRes.setName(rs.getString("SHIRT_NAME"));
            shirtRes.setSize(rs.getString("SHIRT_SIZE"));
            shirtRes.setColor(rs.getString("SHIRT_COLOR"));
            shirtRes.setDescription(rs.getString("SHIRT_DESC"));
            shirtRes.setPrice(rs.getDouble("SHIRT_PRICE"));
            shirtRes.setGender(rs.getString("SHIRT_GENDER"));
            shirtRes.setShirtQuantity(rs.getInt("SHIRT_QUANTITY"));
            shirtRes = new Shirt(SHIRT_ID,SHIRT_NAME,SHIRT_DESC,SHIRT_PRICE,SHIRT_GENDER,SHIRT_COLOR,SHIRT_SIZE,SHIRT_QUANTITY);
        }

        rs.close();
        return shirtRes;
    }

    public Shirt insertShirt(Shirt inputtedShirt) throws SQLException
    {
        Shirt retShirt = null;
        String sql = "INSERT INTO SHIRT (SHIRT_SIZE, SHIRT_NAME, SHIRT_DESC,SHIRT_PRICE,SHIRT_GENDER,SHIRT_QUANTITY,SHIRT_COLOR)VALUES("
                    + "\"" + inputtedShirt.getSize() + "\"" + "," + "\"" + inputtedShirt.getName() + "\"" + "," + "\"" + inputtedShirt.getDescription() + "\"" + "," + inputtedShirt.getPrice() + "," + "\"" + inputtedShirt.getGender() + "\"" + ","
                    + inputtedShirt.getShirtQuantity() + "," + "\"" + inputtedShirt.getColor() + "\"" + ")";
        int rs = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        retShirt = new Shirt(rs, inputtedShirt.getName(), inputtedShirt.getDescription(), inputtedShirt.getPrice(), inputtedShirt.getGender(), inputtedShirt.getColor(), inputtedShirt.getSize(), inputtedShirt.getShirtQuantity());
        return retShirt;
    }

    public boolean editShirt(int shirtID, String attributeChoice, int switchChoice) throws SQLException
    {
        String sql ="";
        switch (switchChoice)
        {
            case 1:
                sql = "UPDATE Shirt" + " SET Shirt_price = " + Double.parseDouble(attributeChoice) + "\nWHERE Shirt.Shirt_ID = " + shirtID + ";";
                break;
            case 2:
                sql = "UPDATE SHIRT" + " SET Shirts_Color = " + "\"" + attributeChoice + "\"" + " Where Shirt_ID = " + shirtID;
                break;
            case 3:
                sql = "UPDATE Shirt" + " SET Shirt_Quantity = " + "\"" + Integer.parseInt(attributeChoice) + "\"" + "\nWHERE Shirt.Shirt_ID = " + shirtID + ";";
                break;
            case 4:
                sql = "UPDATE Shirt" + " SET Shirt_Gender = " + "\"" + attributeChoice + "\"" + "\nWHERE Shirt.Shirt_ID = " + shirtID + ";";
                break;
            case 5:
                sql = "UPDATE Shirt" + " SET Shirt_Size = " + "\"" + attributeChoice + "\"" + "" +
                            "\nWHERE Shirt.Shirt_ID = " + shirtID + ";";
                break;
            case 6:
                sql = "UPDATE Shirt" + " SET Shirt_Desc = " + "\"" + attributeChoice + "\"" + "" +
                            "\nWHERE Shirt.Shirt_ID = " + shirtID + ";";
                break;
            case 7:
                sql = "UPDATE Shirt" + " SET Shirt_NAME = " + "\"" + attributeChoice + "\"" + "" +
                            "\nWHERE Shirt.Shirt_ID = " + shirtID + ";";
                break;
            default:
                return false;
        }
        int rs = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        return true;
    }

    public boolean deleteShirt(int inputtedID) throws SQLException {

        String sql = "DELETE FROM SHIRT " +
                "WHERE SHIRT_ID = " + inputtedID +";";

        int isSuccessful = stmt.executeUpdate(sql);
        if(isSuccessful == 0)
            return false;
        else
            return true;
    }

    public ArrayList<Shirt> searchInventory(String name, String size, String gender, String color, int minPrice, int maxPrice) throws SQLException
    {
        String sql = "SELECT * FROM Shirt WHERE ";
        if (!name.equals(""))
        {
            sql += "SHIRT_NAME = '" + name + "' AND ";
        }
        if (!size.equals(""))
        {
            sql += "SHIRT_SIZE = '" + size + "' AND ";
        }
        if (!gender.equals(""))
        {
            sql += "SHIRT_GENDER = '" + gender + "' AND ";
        }
        if (!color.equals(""))
        {
            sql += "SHIRT_COLOR = '" + color + "' AND ";
        }
        if (minPrice > 0 && maxPrice > minPrice)
        {
            sql += "SHIRT_PRICE >= " + minPrice + " AND SHIRT_PRICE <= " + maxPrice + " AND ";
        }
        int length = sql.length();
        sql = sql.substring(0, length-4) + ";";
        ArrayList<Shirt> foundShirts = new ArrayList<Shirt>();
        ResultSet rs = stmt.executeQuery(sql);

        if (!rs.next())
        {
            return null;
        }

        rs.beforeFirst();

        while(rs.next())
        {
            int shirtID = rs.getInt("SHIRT_ID");
            String shirtSize = rs.getString("SHIRT_SIZE");
            String shirtName = rs.getString("SHIRT_NAME");
            String shirtDesc = rs.getString("SHIRT_DESC");
            double shirtPrice = rs.getDouble("SHIRT_PRICE");
            String shirtGender = rs.getString("SHIRT_GENDER");
            int shirtQuantity = rs.getInt("SHIRT_QUANTITY");
            String shirtColor = rs.getString("SHIRT_COLOR");

            Shirt shirt = new Shirt(shirtID, shirtName, shirtDesc, shirtPrice, shirtGender, shirtColor, shirtSize, shirtQuantity);
            System.out.println(shirt);
            foundShirts.add(shirt);
        }
        return foundShirts;
    }

    public boolean shipShirt(int orderNumber) throws SQLException
    {
        String sql = "UPDATE Shirt " +
                "JOIN orderitem USING (SHIRT_ID) JOIN Orders USING (ORDER_ID) " +
                "SET SHIRT_QUANTITY = SHIRT_QUANTITY - ORDER_QUANTITY, ORDER_STATUS = 'Shipped' " +
                "WHERE ORDER_ID =" + orderNumber +" AND ORDER_STATUS = 'Pending';";

        int rs = stmt.executeUpdate(sql);
        if(rs==0)
            return false;
        else
            return true;
    }

}