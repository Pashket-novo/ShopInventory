/**
 * Boundary class for BakeShopSystem
 *
 * @version 1.0
 */
public class SystemInterface {

    /**
     * Home page menu
     */
    public void homePage() {
        System.out.println("Please enter your option: ");
        System.out.println("A. Manage an order");
        System.out.println("B. User information");
        System.out.println("C. Stores information");
        System.out.println("D. View the report");
        System.out.println("E. Inventory");
        System.out.println("F. Manage users");
        System.out.println("G. Exit the system");
    }

    /**
     * Employee menu, shows options available to do with employee
     */
    public void employeeMenu() {
        System.out.println("Please enter your option: ");
        System.out.println("A. Create an employee");
        System.out.println("B. Update an employee details");
        System.out.println("C. Delete an employee");
        System.out.println("D. View an employee details");
        System.out.println("E. Back to home menu");
    }

    /**
     * Manage order menu
     */
    public void manageOrderPage() {
        //inMenu = MENU_ENUM.ORDER_MENU;
        System.out.println("Please enter your order option: ");
        System.out.println("A. Add an order");
        System.out.println("B. Delete an order");
        System.out.println("C. Update an order");
        System.out.println("D. View List of order");
        System.out.println("F. Exit the Home Page");
    }

    /**
     * Report menu
     */
    public void reportPage() {
        System.out.println("Please enter your report option: ");
        System.out.println("1. View the coffee sold in last month");
        System.out.println("2. View coffee bean sold in last month");
        System.out.println("3. View food items bean sold in last month");
        System.out.println("4. View low in inventory at the end of each week");
        System.out.println("5. View the days of week that made the most sale in the last month");
        System.out.println("6. View sale made in dollars in the last month");
        System.out.println("7. View type of coffee sold the most in the last month");
        System.out.println("8. Exit to the Home Page");
    }
}
