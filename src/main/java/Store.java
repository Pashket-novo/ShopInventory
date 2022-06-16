/**
 * Store class contain attributes of the stores:
 * store ID, store address, city, suburb, postcode, contact number
 *
 * @version 1.0
 */
public class Store
{
  private int storeId;
  private String storeAddress;
  private String city;
  private String Suburb;
  private int postCode;
  private String contactNumber;

  /**
   * Constructor for class Store
   *
   * @param storeId store ID
   * @param storeAddress store address
   * @param suburb suburb
   * @param city city
   * @param postCode postcode
   * @param contactNumber contact number
   */
  public Store(int storeId, String storeAddress, String suburb, String city, int postCode, String contactNumber)
  {
    this.storeId = storeId;
    this.storeAddress = storeAddress;
    this.city = city;
    Suburb = suburb;
    this.postCode = postCode;
    this.contactNumber = contactNumber;
  }

  /**
   * Return store ID
   *
   * @return    store ID
   */
  public int getStoreId()
  {
    return storeId;
  }

  /**
   * Set store ID
   *
   * @param storeId store ID
   */
  public void setStoreId(int storeId)
  {
    this.storeId = storeId;
  }

  /**
   * Return store address
   *
   * @return    store address
   */
  public String getStoreAddress()
  {
    return storeAddress;
  }

  /**
   * Set store address
   *
   * @param storeAddress store address
   */
  public void setStoreAddress(String storeAddress)
  {
    this.storeAddress = storeAddress;
  }

  /**
   * Return store city
   *
   * @return    store city
   */
  public String getCity()
  {
    return city;
  }

  /**
   * Set store city
   *
   * @param city  store city
   */
  public void setCity(String city)
  {
    this.city = city;
  }

  /**
   * Return store suburb
   *
   * @return    store suburb
   */
  public String getSuburb()
  {
    return Suburb;
  }

  /**
   * Set store suburb
   *
   * @param suburb store suburb
   */
  public void setSuburb(String suburb)
  {
    Suburb = suburb;
  }

  /**
   * Return store post code
   *
   * @return    store postcode
   */
  public int getPostCode()
  {
    return postCode;
  }

  /**
   * Set store postcode
   *
   * @param postCode store postcode
   */
  public void setPostCode(int postCode)
  {
    this.postCode = postCode;
  }

  /**
   * Return store contact number
   *
   * @return    store contact number
   */
  public String getContactNumber()
  {
    return contactNumber;
  }

  /**
   * Set store contact number
   *
   * @param contactNumber store contact number
   */
  public void setContactNumber(String contactNumber)
  {
    this.contactNumber = contactNumber;
  }
}
