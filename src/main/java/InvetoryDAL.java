import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
/**
 * Data access layer class for the Inventory class
 *
 * @version 1.0
 */
public class InvetoryDAL extends SQLiteConnection {

    /**
     * Provide Inventory details for a particular store from the database.
     * Method ensure connection to SQLite DB and executing select query.
     *
     * @param storeId store ID for store, for which Inventory details retrieved
     * @return      Inventory class object (inventory for particular store)
     */
    public Inventory getItemsByStoreId(int storeId) {
        Inventory inventory = null;
        String sql =
                "SELECT inv.Id, " +
                        "inv.StoreId, " +
                        "inv.ItemId, " +
                        "i.ItemName, " +
                        "inv.Quantity, " +
                        "inv.AddedDate, " +
                        "i.price " +
                        "FROM inventory inv INNER JOIN 'item' i on inv.ItemId = i.Id " +
                        "WHERE inv.storeId=" + storeId;
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                inventory = new Inventory(rs.getInt("StoreId"));
                Map<Item, Integer> inventoryItemQty = new HashMap<>();

                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date addedDate = formatter.parse(rs.getString("AddedDate"));
                int price = rs.getInt("Price");
                Item item = new Item(rs.getString("ItemId"),
                        rs.getString("ItemName"),
                        addedDate, price);
                inventoryItemQty.put(item, rs.getInt("Quantity"));
                inventory.setInventoryItemQty(inventoryItemQty);
            }
        } catch (SQLException | ParseException e) {
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
        return inventory;
    }

    /**
     * Provide item details based on the Store ID and Item name
     * Method ensure connection to SQLite DB and executing select query.
     *
     * @param storeId storeID, inventory details for which, method is retrieving
     * @param itemName item name, item details for which, method is retrieving
     * @return         Item details, including quantity
     */
    public Map<Item, Integer> getItemsByStoreIdAndName(int storeId, String itemName) {
        Map<Item, Integer> items = new HashMap<>();
        String sql =
                "SELECT  inv.ItemId, " +
                        "i.ItemName, " +
                        "inv.Quantity, " +
                        "i.Price, " +
                        "AddedDate " +
                        "FROM inventory inv INNER JOIN 'item' i on inv.ItemId = i.Id " +
                        "WHERE inv.StoreId = " + storeId + " " +
                        "AND lower(i.ItemName) LIKE '%" + itemName.trim().toLowerCase() + "%' " +
                "ORDER BY i.ItemName";
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date addedDate = formatter.parse(rs.getString("AddedDate"));
                int price = rs.getInt("Price");
                Item item = new Item(rs.getString("ItemId"),
                        rs.getString("ItemName"),
                        addedDate, price);
                items.put(item, rs.getInt("Quantity"));
            }
        } catch (SQLException | ParseException e) {
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
        return items;
    }

    /**
     * Update inventory details for particular store and item.
     * Method ensure connection to SQLite DB and executing update query.
     *
     * @param storeId store ID, for which inventory details are updated
     * @param updatedItems Map with item information and quantity
     */
    public void updateInventory(int storeId, Map<String, Integer> updatedItems) {
        for (String itemId : updatedItems.keySet()) {
            String sql = "UPDATE inventory " +
                    "SET Quantity = " + updatedItems.get(itemId) +
                    " WHERE  Id = ( SELECT Id FROM inventory " +
                    "WHERE" +
                    " ItemId = '" + itemId + "'" +
                    " AND StoreId = " + storeId +
                    " LIMIT 1)";
            // connection to the database and executing  update query
            try (Connection conn = this.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
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
     * Check the items, low in inventory in each store
     * Method ensure connection to SQLite DB and executing select query.
     *
     * @return  Map contains items (item name and quantity) low in inventory for each store
     */
    public Map<Integer, List<String>> getLowInventoryForEachStore(){
        Map<Integer, List<String>> lowInventoryMap = new HashMap<>();
        List<String> list = new ArrayList<>();
        String sql  = "select StoreId,ItemName,Quantity from inventory where Quantity <= 10";
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
            // loop through the result set
            while (rs.next()) {
                int id = rs.getInt("StoreId");
                String situation = rs.getString("ItemName") + "  " + rs.getInt("Quantity");
                if (!lowInventoryMap.containsKey(id))
                    lowInventoryMap.put(id,new ArrayList<>());
                lowInventoryMap.get(id).add(situation);
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
        return lowInventoryMap;
    }
}
