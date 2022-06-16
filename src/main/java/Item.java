import java.util.Date;
/**
 * Item class stores item details
 * Item name, item ID, date added to inventory, price
 *
 * @version 1.0
 */
public class Item
{
  private String itemName;
  private String itemId;
  private Date addedDate;
  private int price;

  /**
   * Constructor for class Item
   *
   * @param itemId item ID
   * @param itemName item name
   * @param addedDate date added to inventory
   * @param price price
   */
  public Item(String itemId, String itemName, Date addedDate, int price)
  {
    this.itemName = itemName;
    this.itemId = itemId;
    this.addedDate = addedDate;
    this.price = price;
  }

  /**
   * Return item price
   *
   * @return item price
   */
  public int getPrice() {
    return price;
  }

  /**
   * Set item price
   *
   * @param price item price
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * Return date added to inventory
   *
   * @return date added to inventory
   */
  public Date getAddedDate()
  {
    return addedDate;
  }

  /**
   * Set date added to inventory
   *
   * @param addedDate date added to inventory
   */
  public void setAddedDate(Date addedDate)
  {
    this.addedDate = addedDate;
  }

  /**
   * Return item name
   *
   * @return  item name
   */
  public String getItemName()
  {
    return itemName;
  }

  /**
   * Set item name
   *
   * @param itemName item name
   */
  public void setItemName(String itemName)
  {
    this.itemName = itemName;
  }

  /**
   * Return item ID
   *
   * @return item ID
   */
  public String getItemId()
  {
    return itemId;
  }

  /**
   * Set item ID
   *
   * @param itemId item ID
   */
  public void setItemId(String itemId)
  {
    this.itemId = itemId;
  }
}
