import java.util.Map;
/**
 * Inventory class represent inventory for particular store.
 * It store storeId and Map (Item, Qty)
 *
 * @version 1.0
 */
public class Inventory {

    private int storeId;
    private Map<Item, Integer> inventoryItemQty;

    /**
     *Constructor for class Inventory
     *
     * @param storeId particular store ID
     */
    public Inventory(int storeId) {
        this.storeId = storeId;
    }

    /**
     * Access the Map with item and quantities
     *
     * @return      Map with item and quantities
     */
    public Map<Item, Integer> getInventoryItemQty() {
        return inventoryItemQty;
    }

    /**
     * Set the inventory details - item and quantity
     *
     * @param inventoryItemQty map containing item and quantity
     */
    public void setInventoryItemQty(Map<Item, Integer> inventoryItemQty) {
        this.inventoryItemQty = inventoryItemQty;
    }

    /**
     * Method provides store ID
     *
     * @return      store ID
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Method to set store ID
     *
     * @param storeId store ID
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
