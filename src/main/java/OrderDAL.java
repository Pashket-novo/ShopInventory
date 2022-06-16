import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
/**
 * Data access layer class for the Order class
 *
 * @ version 1.0
 */
public class OrderDAL extends SQLiteConnection {

    /**
     * Create order
     * Method ensure connection to SQLite DB and executing insert query into the DB.
     * Order contains order information.
     *
     * @param order Order object
     * @return      order ID
     */
    public int createOrder(Order order) {
        String orderSql = "INSERT INTO 'order'(StoreId, EmployeeId, OrderDate, OrderTime, CustomerName, OrderStatus, CustomerPhone)" +
                "VALUES(?,?,?,?,?,?,?)";
        String orderDate = order.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        String orderTime = order.getOrderTime().format(DateTimeFormatter.ofPattern("HH:mm"));
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(orderSql)) {
            pstmt.setInt(1, order.getStoreId());
            pstmt.setInt(2, order.getCreatedBy().getUserId());
            pstmt.setString(3, orderDate);
            pstmt.setString(4, orderTime);
            pstmt.setString(5, order.getCustomerName());
            pstmt.setString(6, order.getOrderStatus().toString());
            pstmt.setString(7, order.getCustomerPhone());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    int latestOrderId = getLatestIdByTableName("order");
                    createOrderDetail(latestOrderId, order.getOrderItemQty());
                    System.out.println("Order " + latestOrderId + " is created successfully.");
                    return latestOrderId;
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return 0;
    }

    /**
     * Create order details.
     * Order details is the combination of order id, item id, quantity and price of the order position.
     * Method ensure connection to SQLite DB and executing insert query into the order_detail table
     *
     * @param orderId order id
     * @param orderDetail map, contains item and quantity
     */
    public void createOrderDetail(int orderId, Map<Item, Integer> orderDetail) {
        String orderDetailSql = "INSERT INTO 'order_detail'(OrderId, ItemId, Quantity, Price)" +
                "VALUES(?,?,?,?)";
        for (Item item : orderDetail.keySet()) {
            // connection to the database and executing insert query
            try (Connection conn = this.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(orderDetailSql)) {
                pstmt.setInt(1, orderId);
                pstmt.setString(2, item.getItemId());
                pstmt.setInt(3, orderDetail.get(item));
                pstmt.setInt(4, item.getPrice());
                pstmt.executeUpdate();
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
    }

    /**
     * Generate report - "Total number of coffee sold in the last month in each store"
     *
     * @return      map, contains all stores and quantity of the sold coffee in each store
     */
    public Map<Integer, Integer> getAllStoreCoffeeSalesForLastMonth(){
        Map<Integer, Integer> coffeeSoldEachStoreMap = new HashMap<>();
        String formatDate = getLastMonth();
        String sql = "select StoreId,Quantity from \"order\" join order_detail on \"order\".Id = order_detail.OrderId\n" +
                "where (ItemId like 'I-033' or ItemId like 'I-046' or ItemId like 'I-078' " +
                "or ItemId like 'I-013' or ItemId like 'I-100' or ItemId like 'I-105') and OrderDate like " + formatDate;
        // connection to the database and executing select query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("StoreId");
                int quantity = rs.getInt("Quantity");
                coffeeSoldEachStoreMap.put(id,coffeeSoldEachStoreMap.getOrDefault(id,0) + quantity);
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
        return coffeeSoldEachStoreMap;
    }

    /**
     * Generate report - "Total number of coffee beans (in quantity) sold in last month in each store"
     *
     * @return      map, contains all stores and quantity of the sold coffee beans in each store
     */
    public Map<Integer, Integer> getAllStoreCoffeeBeanSalesForLastMonth(){
        Map<Integer, Integer> coffeeBeansSoldEachStoreMap = new HashMap<>();
        String formatDate = getLastMonth();
        String sql = "select StoreId,Quantity,OrderDate from \"order\" join order_detail on \"order\".Id = order_detail.OrderId\n" +
                "where ItemId like 'I-110' and OrderDate like " + formatDate;
        // connection to the database and executing select query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("StoreId");
                int quantity = rs.getInt("Quantity");
                coffeeBeansSoldEachStoreMap.put(id,coffeeBeansSoldEachStoreMap.getOrDefault(id,0) + quantity);
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
        return coffeeBeansSoldEachStoreMap;
    }

    /**
     * Generate report - "Total sale made in dollars in the last month per store"
     *
     * @return      Map, contains store id and revenue for this store
     */
    public Map<Integer, Integer> totalDollarsMadeLastMonthInLastMonth(){
        Map<Integer, Integer> revenueMap = new HashMap<>();
        String formatDate = getLastMonth();
        String sql = "select StoreId,Quantity,Price from \"order\" join order_detail on \"order\".Id = order_detail.OrderId\n" +
                "where OrderDate like "  + formatDate;
        // connection to the database and executing select query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("StoreId");
                int revenue = rs.getInt("Quantity") * rs.getInt("Price");
                revenueMap.put(id,revenueMap.getOrDefault(id,0) + revenue);
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
        return revenueMap;
    }

    /**
     * Generate report - "Days of the week that made the most sale in the last month per store"
     *
     * @return      Map, contains store id and days of the week
     */
    public Map<Integer,List<Integer>> daysOfTheWeekMadeMostSaleInLastMonth(){
        Map<Integer,List<Integer>> daysOfWeekSaleMap = new HashMap<>();
        String formatDate = getLastMonth();
        String sql = "select StoreId,weekday,week\n" +
                "from(select storeId, max(revenue),weekday,week from (select StoreId, sum(Quantity * Price) as revenue,\n" +
                "       strftime('%w',substr(OrderDate, 7, 4)||\"-\"||substr(OrderDate, 1,2)||\"-\"||substr(OrderDate, 4,2)) as weekday,\n" +
                "       strftime('%W',substr(OrderDate, 7, 4)||\"-\"||substr(OrderDate, 1,2)||\"-\"||substr(OrderDate, 4,2)) as week\n" +
                "from \"order\" join order_detail on \"order\".Id = order_detail.OrderId where OrderDate like " + formatDate +
                " group by  StoreId,weekday,week) group by storeId,  week)";
        // connection to the database and executing select query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("StoreId");
                int weekday = rs.getInt("weekday");
                if (!daysOfWeekSaleMap.containsKey(id))
                    daysOfWeekSaleMap.put(id,new ArrayList<>());
                daysOfWeekSaleMap.get(id).add(weekday);
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
        return daysOfWeekSaleMap;
    }

    /**
     * Return last month date
     *
     * @return      last month date
     */
    public String getLastMonth(){
        LocalDate now = LocalDate.now();
        LocalDate earlier = now.minusMonths(1);
        Integer lastMonth = earlier.getMonth().getValue();
        Integer year = earlier.getYear();
        String lastM = "";
        if (lastMonth < 10)
            lastM = "0" + lastMonth;
        else
            lastM = Integer.toString(lastMonth);
        lastM = "'" + lastM ;
        String yearNow = year.toString() + "'";
        String formatDate = lastM + "/%/"+ yearNow;
        return  formatDate;
    }

    /**
     * Generate report - "Total number of food items sold in last month in each store"
     *
     * @return map, contains all stores and quantity of the food items in each store
     */
    public Map<Integer, Integer> getAllStoreFoodItemsSalesForLastMonth(){
        Map<Integer, Integer> foodSoldEachStoreMap = new HashMap<>();
        String formatDate = getLastMonth();
        String sql = "select StoreId,Quantity from \"order\" join order_detail on \"order\".Id = order_detail.OrderId\n" +
                "where (ItemId not in ('I-008', 'I-009', 'I-010','I-013','I-018','I-033','I-046','I-078','I-081',\n" +
                "                'I-100', 'I-101','I-105', 'I-110')) and OrderDate like " + formatDate;
        // connection to the database and executing select query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("StoreId");
                int quantity = rs.getInt("Quantity");
                foodSoldEachStoreMap.put(id,foodSoldEachStoreMap.getOrDefault(id,0) + quantity);
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
        return foodSoldEachStoreMap;
    }
}