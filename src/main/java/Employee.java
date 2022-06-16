/**
 * Employee class contain attributes of the employee
 * User ID, name, email, password, user type, TFN, street address, city, state, postal code,
 * contact number, store ID
 *
 * @version 1.0
 */
public class Employee {
    private int userId;
    private String name;
    private String email;
    private String password;
    private String userType;
    private String TFN;
    private String streetAddress;
    private String city;
    private String state;
    private int postal;
    private String contactNumber;
    private int storeId;

    /**
     * Constructor for class Employee
     *
     * @param userId user ID
     * @param name name
     * @param email email
     * @param password password
     * @param userType user type
     * @param TFN TFN
     * @param streetAddress street address
     * @param city city
     * @param state state
     * @param postal postal
     * @param contactNumber contact number
     * @param storeId store ID
     */
    public Employee(int userId, String name, String email, String password, String userType, String TFN, String streetAddress, String city, String state, int postal, String contactNumber, int storeId) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.TFN = TFN;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.contactNumber = contactNumber;
        this.storeId = storeId;
    }

    /**
     * Constructor for class Employee
     */
    public Employee() {
    }

    /**
     * Return employee name
     *
     * @return employee name
     */
    public String getName() {
        return name;
    }

    /**
     * Set employee name
     *
     * @param name employee name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return employee email
     *
     * @return employee email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set employee email
     *
     * @param email employee email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return employee password
     *
     * @return employee password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set employee password
     *
     * @param password employee password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return user type
     *
     * @return user type
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Set user type
     *
     * @param userType user type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * Return TFN
     *
     * @return TFN
     */
    public String getTFN() {
        return TFN;
    }

    /**
     * Set TFN
     *
     * @param TFN TFN
     */
    public void setTFN(String TFN) {
        this.TFN = TFN;
    }

    /**
     * Return street address
     *
     * @return street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Set street address
     *
     * @param streetAddress street address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Return city
     *
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set city
     *
     * @param city city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Return state
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * Set state
     *
     * @param state state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Return postal code
     *
     * @return  postal code
     */
    public int getPostal() {
        return postal;
    }

    /**
     * Set postal code
     *
     * @param postal postal code
     */
    public void setPostal(int postal) {
        this.postal = postal;
    }

    /**
     * Return contact number
     *
     * @return contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Set contact number
     *
     * @param contactNumber contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Return store ID belongs to employee
     *
     * @return store ID
     */
    public int getStoreId() {
        return storeId;
    }

    /**
     * Set store ID belongs to employee
     *
     * @param storeId store ID
     */
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    /**
     * Return user ID
     *
     * @return user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set user ID
     *
     * @param userId user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Show the state of the Employee class object
     *
     * @return      String with all attributes of Employee class object
     */
    @Override
    public String toString() {
        return "Employee{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                ", TFN='" + TFN + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postal=" + postal +
                ", contactNUmber='" + contactNumber + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
