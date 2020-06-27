import java.util.ArrayList;
import java.util.List;

public class HTMLView
{

    public static String viewOrders(ArrayList<Order> orderList)
    {
        if (orderList == null)
        {
            String str = "<table style=\"height: 30px; width: 500px;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 500px;\">\n" +
                    "<h1 style=\"text-align: center;\">You have no orders</h1>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>";
            return str;
        }

        String str = "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1 style=\"text-align: center;\">Your Orders</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";
        for (int i = 0; i < orderList.size(); i++)
        {
            Order o = orderList.get(i);
            str += "<table style=\"height: 70px; width: 500px;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 25px; text-align: center;\">\n" +
                    "<h1><strong>" + (i + 1) + ":</strong></h1>\n" +
                    "</td>\n" +
                    "<td style=\"width: 118px; text-align: center;\">\n" +
                    "<p><strong>Order Placed</strong></p>\n" +
                    "<p>" + o.getDateCreated() + "</p>\n" +
                    "</td>\n" +
                    "<td style=\"width: 118px; text-align: center;\">\n" +
                    "<p><strong>Order Shipped</strong></p>\n" +
                    "<p>";

            if (o.getDateSent() == null)
                str += "";
            else
                str += o.getDateSent();

            str += "</p>\n" +
                    "</td>\n" +
                    "<td style=\"width: 118px; text-align: center;\">\n" +
                    "<p><strong>Total</strong></p>\n" +
                    "<p>" + o.getTotal() + "</p>\n" +
                    "</td>\n" +
                    "<td style=\"width: 118px; text-align: center;\">\n" +
                    "<p><strong>Status</strong></p>\n" +
                    "<p>" + o.getOrderStatus() + "</p>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>\n";

            str += "<table style=\"height: 30px; width: 500px;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 5px;\">\n" +
                    "<h2 style=\"text-align: center;\">&nbsp&nbsp&nbsp</h2>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3><span style=\"text-decoration: underline;\">Name</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3><span style=\"text-decoration: underline;\">Size</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3><span style=\"text-decoration: underline;\">Gender</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3><span style=\"text-decoration: underline;\">Color</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3><span style=\"text-decoration: underline;\">Qty</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3><span style=\"text-decoration: underline;\">Price</h3>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>";

            for (int j = 0; j < o.orderItems.size(); j++) {
                Shirt s = o.orderItems.get(j).getShirt();

                str += "<table style=\"height: 30px; width: 500px;\">\n" +
                        "<tbody>\n" +
                        "<tr>\n" +
                        "<td style=\"width: 5px;\">\n" +
                        "<h2 style=\"text-align: center;\">(" + (j+1) + ")</h2>\n" +
                        "</td>\n" +
                        "<td style=\"width: 82px; text-align: center;\">\n" +
                        "<h3>" + s.getName() + "</h3>\n" +
                        "</td>\n" +
                        "<td style=\"width: 82px; text-align: center;\">\n" +
                        "<h3>" + s.getSize() + "</h3>\n" +
                        "</td>\n" +
                        "<td style=\"width: 82px; text-align: center;\">\n" +
                        "<h3>" + s.getGender() + "</h3>\n" +
                        "</td>\n" +
                        "<td style=\"width: 82px; text-align: center;\">\n" +
                        "<h3>" + s.getColor() + "</h3>\n" +
                        "</td>\n" +
                        "<td style=\"width: 82px; text-align: center;\">\n" +
                        "<h3>" + o.orderItems.get(j).getQuantity() + "</h3>\n" +
                        "</td>\n" +
                        "<td style=\"width: 82px; text-align: center;\">\n" +
                        "<h3>" + o.orderItems.get(j).getTotal() + "</h3>\n" +
                        "</td>\n" +
                        "</tr>\n" +
                        "</tbody>\n" +
                        "</table>";
            }
                str += "<hr />";
        }
        return str;
    }

    public static String viewShirts(ArrayList<Shirt> shirtList, int previous, int current)
    {
        String str = "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1 style=\"text-align: center;\">Shirt Inventory</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
            "<tbody>\n" +
            "<tr>\n" +
            "<td style=\"width: 5px;\">\n" +
            "<h2 style=\"text-align: center;\">&nbsp&nbsp&nbsp</h2>\n" +
            "</td>\n" +
            "<td style=\"width: 82px; text-align: center;\">\n" +
            "<h3><span style=\"text-decoration: underline;\">Name</h3>\n" +
            "</td>\n" +
            "<td style=\"width: 82px; text-align: center;\">\n" +
            "<h3><span style=\"text-decoration: underline;\">Size</h3>\n" +
            "</td>\n" +
            "<td style=\"width: 82px; text-align: center;\">\n" +
            "<h3><span style=\"text-decoration: underline;\">Gender</h3>\n" +
            "</td>\n" +
            "<td style=\"width: 82px; text-align: center;\">\n" +
            "<h3><span style=\"text-decoration: underline;\">Color</h3>\n" +
            "</td>\n" +
            "<td style=\"width: 82px; text-align: center;\">\n" +
            "<h3><span style=\"text-decoration: underline;\">Price</h3>\n" +
            "</td>\n" +
            "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">In Stock</h3>\n" +
                "</td>\n" +
            "</tr>\n" +
            "</tbody>\n" +
            "</table>";

        System.out.println("HTMLVIEW: previous: " + previous + ", current: " + current);
        for (int i = previous; i < current; i++)
        {
            Shirt s = shirtList.get(i);
            str += "<table style=\"height: 30px; width: 500px;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 5px;\">\n" +
                    "<h2 style=\"text-align: center;\">(" + (i+1) + ")</h2>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getName() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getSize() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getGender() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getColor() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getPrice() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>";
            if (s.getShirtQuantity() > 0)
                str += "Yes";
            else
                str += "No";
            str += "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>";
        }
        return str;
    }

    public static String viewCart(ArrayList<OrderItem> cartItems, double totalPrice, int totalShirts)
    {
        if (cartItems == null)
        {
            String str = "<table style=\"height: 30px; width: 500px;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 500px;\">\n" +
                    "<h1 style=\"text-align: center;\">Your cart is empty</h1>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>";
            return str;
        }
        String str = "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1 style=\"text-align: center;\">Your Cart</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 5px;\">\n" +
                "<h2 style=\"text-align: center;\">&nbsp&nbsp&nbsp</h2>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Name</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Size</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Gender</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Color</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Qty</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Price</h3>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        for (int i = 0; i < cartItems.size(); i++)
        {
            Shirt s = cartItems.get(i).getShirt();
            str += "<table style=\"height: 30px; width: 500px;\">\n" +
                    "<tbody>\n" +
                    "<tr>\n" +
                    "<td style=\"width: 5px;\">\n" +
                    "<h2 style=\"text-align: center;\">(" + (i+1) + ")</h2>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getName() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getSize() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getGender() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + s.getColor() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + cartItems.get(i).getQuantity() + "</h3>\n" +
                    "</td>\n" +
                    "<td style=\"width: 82px; text-align: center;\">\n" +
                    "<h3>" + cartItems.get(i).getTotal() + "</h3>\n" +
                    "</td>\n" +
                    "</tr>\n" +
                    "</tbody>\n" +
                    "</table>";
        }

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1>Shirts: " + totalShirts + "</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1>Total: $" + String.format("%.2f", totalPrice) + "</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        return str;
    }

    public static String viewShirtDetail(Shirt s)
    {
        String str = "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1 style=\"text-align: center;\">" + s.getName() + "</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 5px;\">\n" +
                "<h2 style=\"text-align: center;\">&nbsp&nbsp&nbsp</h2>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Name</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Size</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Gender</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Color</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">Price</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3><span style=\"text-decoration: underline;\">In Stock</h3>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 5px;\">\n" +
                "<h2 style=\"text-align: center;\">•</h2>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3>" + s.getName() + "</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3>" + s.getSize() + "</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3>" + s.getGender() + "</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3>" + s.getColor() + "</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3>" + s.getPrice() + "</h3>\n" +
                "</td>\n" +
                "<td style=\"width: 82px; text-align: center;\">\n" +
                "<h3>";
        if (s.getShirtQuantity() > 0)
            str += "Yes";
        else
            str += "No";
        str += "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        str += "<table style=\"height: 30px; width: 500px;\">\n" +
                "<tbody>\n" +
                "<tr>\n" +
                "<td style=\"width: 500px;\">\n" +
                "<h1>" + s.getDescription() + "</h1>\n" +
                "</td>\n" +
                "</tr>\n" +
                "</tbody>\n" +
                "</table>";

        return str;

    }
}
