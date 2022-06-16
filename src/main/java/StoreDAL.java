import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
/**
 * Data access layer class for the Store class
 *
 * @ version 1.0
 */
public class StoreDAL extends SQLiteConnection {

    /**
     *  Get all information about each store from the database and print it to the screen
     */
    public void GetAll() {
        String sql = "SELECT StoreId, StreetAddress, City, Suburb, Postal, ContactNumber FROM store";
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("StoreId") + "\t" +
                        rs.getString("StreetAddress") + "\t" +
                        rs.getString("City") + "\t" +
                        rs.getString("Suburb") + "\t" +
                        rs.getInt("Postal") + "\t" +
                        rs.getString("ContactNumber")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Return the total number of stores in the database
     *
     * @return      total number of stores
     */
    public int getTotalNumaabersofStore() {
        String sql = "select max(StoreId)StoreId from store";
        int numberOfStore = -1;
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            numberOfStore = rs.getInt("StoreId");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return numberOfStore;
    }

    /**
     * Generate report - "Type of coffee sold the most per store in the last month"
     *
     * @return      ArrayList, contains the attributes required for this report
     */
    public ArrayList<ReportCoffeeTypeSoldPerStoreLastMonth> getTheMostCoffeeTypeSoldPerStoreLastMonth() {
        LocalDateTime now = LocalDateTime.now().minusMonths(1);
        int currentMonth = now.getMonth().getValue();
        int currentYear = now.getYear();
        String sql = "SELECT Id,\n" +
                "       StoreId,\n" +
                "       OrderDate,\n" +
                "       ItemId,\n" +
                "       ItemName,\n" +
                "       MAX(Quantity) as quantity \n" +
                "FROM (SELECT\n" +
                "       o.Id, o.StoreId,\n" +
                "       o.OrderDate, od.ItemId, SUM(od.Quantity) as Quantity,\n" +
                "       i.ItemName\n" +
                "FROM \"order\" o inner join order_detail od ON o.Id = od.OrderId\n" +
                "INNER JOIN item i on i.Id = od.ItemId\n" +
                "WHERE ((i.ItemName LIKE '%Coffee%'\n" +
                " and i.ItemName NOT LIKE '%coffee beans%')\n" +
                " or i.ItemName LIKE '%Expresso%'\n" +
                " or i.ItemName LIKE '%Cappuccino%'\n" +
                " or i.ItemName LIKE '%ice latte%'\n" +
                " or i.ItemName LIKE '%double expresso' or i.ItemName LIKE '%Mocha%')" +
                " and substr(o.OrderDate, 1, 2) = '" + String.format("%02d", currentMonth) + "'\n" +
                " and substr(OrderDate, -4, 4) = '" + currentYear + "'\n" +
                "GROUP BY o.StoreId, i.ItemName)\n" +
                "GROUP BY StoreId";
        ArrayList<ReportCoffeeTypeSoldPerStoreLastMonth> result = new ArrayList<>();
        // connection to the database and executing select query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                ReportCoffeeTypeSoldPerStoreLastMonth model = new ReportCoffeeTypeSoldPerStoreLastMonth();
                model.storeId = rs.getInt("StoreId");
                model.orderId = rs.getInt("Id");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                model.orderDate = LocalDate.parse(rs.getString("OrderDate"), formatter);
                model.itemId = rs.getString("ItemId");
                model.quantity = rs.getInt("Quantity");
                model.itemName = rs.getString("ItemName");
                result.add(model);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();

                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }


}
