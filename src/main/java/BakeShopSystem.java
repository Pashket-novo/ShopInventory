import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
/**
 * Main class of the Bake shop system
 *
 * @version 1.0
 */
public class BakeShopSystem {
    private final Scanner scanner;
    private final InvetoryDAL invetoryDAL;
    private final EmployeeDAL employeeDAL;
    private final OrdxerDAL orderDAL;
    private final StoreDAL storeDAL;
    private final SystemInterface systemInterface;
    private boolean isLogIn;
    private MENU_ENUM inMenu;
    private Employee employee;

    /**
     * Constructor for the class Bake shop system
     *
     * @param invetoryDAL Inventory data layer class
     * @param employeeDAL Employee data layer class
     * @param orderDAL    Order data layer class
     * @param storeDAL    Store data layer class
     */
    public BakeShopSystem(InvetoryDAL invetoryDAL, EmployeeDAL employeeDAL, OrderDAL orderDAL, StoreDAL storeDAL, SystemInterface systemInterface) {
        this.invetoryDAL = invetoryDAL;
        this.employeeDAL = employeeDAL;
        this.orderDAL = orderDAL;
        this.storeDAL = storeDAL;
        this.systemInterface = systemInterface;
        inMenu = MENU_ENUM.HOME_MENU;
        isLogIn = false;
        scanner = new Scanner(System.in);
    }

    /**
     * Main method of the Bake shop system
     *
     * @param args Java command line arguments
     */
    public static void main(String[] args) {
        BakeShopSystem bss = new BakeShopSystem(new InvetoryDAL(), new EmployeeDAL(), new OrderDAL(), new StoreDAL(), new SystemInterface());
        Employee employee = bss.logInFunction();
        StringBuffer sb = new StringBuffer();
        // user login
        if (bss.isLogIn) {
            System.out.println("==Welcome " + employee.getName() + " to the Bake Shop System, Store Number: " + employee.getStoreId() + "==");
            bss.selectMenu(sb);
        } else
            System.out.println("Log in failed");
    }

    /**
     * Set login status
     *
     * @param whetherLoggedIn status of the login
     */
    public void setLogIn(boolean whetherLoggedIn) {
        isLogIn = whetherLoggedIn;
    }

    /**
     * Login function
     *
     * @return Employee object
     */
    public Employee logInFunction() {
        String welcomeMessage = "Welcome to BakeShop System, please log in first";
        String logInMessage = "Please input your employee ID: ";
        String passwordMessage = "Please type your password: ";
        String wrongIdMessage = "we don't recognize this ID, please retype: ";
        String wrongPasswordMessage = "Incorrect password, Please retype: ";
        System.out.println(welcomeMessage);
        System.out.println(logInMessage);
        int eId;
        while (true) {
            eId = Utilities.readNumber(scanner, 1, 4);
            employee = employeeDAL.getUserById(eId);
            if (employee == null)
                System.out.println(wrongIdMessage);
            else
                break;
        }
        System.out.println(passwordMessage);
        // Check correctness of password
        while (true) {
            String inputPassword = Utilities.readKeyBoard(scanner, 20, true);
            if (!inputPassword.equals(employee.getPassword())) {
                System.out.println(wrongPasswordMessage);
            }
            else{
                isLogIn = true;
                break;
            }
        }
        return employee;
    }

    /**
     * Update employee menu. Shows the available options,
     * to change employee details
     *
     * @param employee Employee object
     */
    private void updateEmployeeMenu(Employee employee) {
        boolean flag = true;
        boolean isContinuous = false;
        while (flag) {
            System.out.println("Please enter your option to update: ");
            System.out.println("A. Name: " + employee.getName());
            System.out.println("B. Password: *******");
            System.out.println("C. TFN: " + employee.getTFN());
            System.out.println("D. Street address: " + employee.getStreetAddress());
            System.out.println("E. City: " + employee.getCity());
            System.out.println("F. State: " + employee.getState());
            System.out.println("G. Postal code: " + employee.getPostal());
            System.out.println("H. Contact number: " + employee.getContactNumber());
            System.out.println("I. Email: " + employee.getEmail());
            System.out.println("J. Exit without save");
            System.out.println("L. Save and exit");
            char selection = Utilities.readSelection(scanner);
            switch (selection) {
                case 'A':
                    System.out.println("Current name is: " + employee.getName());
                    System.out.print("Please enter new name: (maxlength is 30) ");
                    isContinuous = true;
                    while (isContinuous) {
                        String name = Utilities.readKeyBoard(scanner, 30, false);
                        if (Utilities.rexValidate(name, "^[a-zA-Z ]*$")) {
                            employee.setName(name);
                            System.out.print("Update successfully.");
                            isContinuous = false;
                        } else {
                            System.out.println("Special characters are not allowed");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'B':
                    System.out.print("Please enter new password: ");
                    String password = Utilities.readKeyBoard(scanner, 0, false);
                    employee.setPassword(password);
                    System.out.print("Update successful.");
                    break;
                case 'C':
                    isContinuous = true;
                    while (isContinuous) {
                        System.out.println("Current TFN is: " + employee.getTFN());
                        System.out.print("Please enter new TFN: (an 8 or 9 digit number) ");
                        String tfn = Utilities.readKeyBoard(scanner, 0, false);
                        String regTFN = "[0-9]{8,9}$";

                        if (Utilities.rexValidate(tfn, regTFN)) {
                            employee.setTFN(tfn);
                            System.out.print("Update successful.");
                            isContinuous = false;
                        } else {
                            System.out.println("Is not TFN format (an 8 or 9 digit number) ");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'D':
                    System.out.println("Current street address is: " + employee.getStreetAddress());
                    System.out.print("Please enter new street address: (maxlength is 100) ");
                    isContinuous = true;
                    while (isContinuous) {
                        String streetAddress = Utilities.readKeyBoard(scanner, 100, false);
                        if (Utilities.rexValidate(streetAddress, "^[a-zA-Z 0-9]*$")) {
                            employee.setStreetAddress(streetAddress);
                            System.out.print("Update successfully.");
                            isContinuous = false;
                        } else {
                            System.out.println("Special characters are not allowed");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'E':
                    System.out.println("Current city is: " + employee.getCity());
                    System.out.print("Please enter new city: (maxlength is 30) ");
                    isContinuous = true;
                    while (isContinuous) {
                        String city = Utilities.readKeyBoard(scanner, 30, false);
                        if (Utilities.rexValidate(city, "^[a-zA-Z 0-9]*$")) {
                            employee.setCity(city);
                            System.out.print("Update successfully.");
                            isContinuous = false;
                        } else {
                            System.out.println("Special characters are not allowed");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'F':
                    System.out.println("Current state is: " + employee.getState());
                    System.out.print("Please enter new state: (maxlength is 30) ");
                    isContinuous = true;
                    while (isContinuous) {
                        String state = Utilities.readKeyBoard(scanner, 30, false);
                        if (Utilities.rexValidate(state, "^[a-zA-Z ]*$")) {
                            employee.setState(state);
                            System.out.print("Update successfully.");
                            isContinuous = false;
                        } else {
                            System.out.println("Number and special characters are not allowed");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'G':
                    System.out.println("Current postal is: " + employee.getPostal());
                    System.out.print("Please enter new postal: (a 4 digit number) ");
                    isContinuous = true;
                    while (isContinuous) {
                        String postalString = Utilities.readKeyBoard(scanner, 4, false);
                        if (Utilities.rexValidate(postalString, "[0-9]{4}$")) {
                            employee.setPostal(Integer.parseInt(postalString));
                            System.out.print("Update successfully.");
                            isContinuous = false;
                        } else {
                            System.out.println("Is not Postal format (a 4 digit number) ");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'H':
                    System.out.println("Current contact number is: " + employee.getContactNumber());
                    System.out.print("Please enter new contact number: ");
                    isContinuous = true;
                    while (isContinuous) {
                        String contactNumber = Utilities.readKeyBoard(scanner, 10, false);
                        if (Utilities.rexValidate(contactNumber, "[0-9]{10}$")) {
                            employee.setContactNumber(contactNumber);
                            System.out.print("Update successful.");
                            isContinuous = false;
                        } else {
                            System.out.println("Is not a contact number (a 10 digit number) ");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'I':
                    System.out.println("Current email is: " + employee.getEmail());
                    System.out.print("Please enter new email: ");
                    isContinuous = true;
                    while (isContinuous) {
                        String email = Utilities.readKeyBoard(scanner, 0, false);
                        if (Utilities.rexValidate(email, "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                            employee.setEmail(email);
                            System.out.print("Update successful.");
                            isContinuous = false;
                        } else {
                            System.out.println("Is not a email format. ");
                            System.out.println("Please type again");
                        }
                    }
                    break;
                case 'J':
                    flag = false;
                    break;
                case 'L':
                    flag = false;
                    employeeDAL.updateEmployee(employee);
                    System.out.println("Update to database successfully.");
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Report choice
     *
     * @param sb string buffer
     */
    private void reportChoice(StringBuffer sb) {
        Report report = new Report(invetoryDAL, orderDAL, storeDAL);
        switch (selectReport()) {
            case '1':
                System.out.println(report.CoffeeReport(sb));
                break;
            case '2':
                System.out.println(report.CoffeeBeansReport(sb));
                break;
            case '3':
                System.out.println(report.foodReport(sb));
                break;
            case '4':
                System.out.println(report.lowInventoryReport(sb));
                break;
            case '5':
                System.out.println(report.daysOfWeekMostSaleReport(sb));
                break;
            case '6':
                System.out.println(report.revenueForLastMonthReport(sb));
                break;
            case '7':
                System.out.println(report.GetMostCoffeeReport());
                break;
            case '8':
                    System.out.println("Go back to HOME MENU");
                    inMenu = MENU_ENUM.HOME_MENU;
                break;
        }
    }

    /**
     * Select item for the order
     *
     * @param items map with item and quantity
     * @return Item object or null
     */
    private Item selectItems(Map<Item, Integer> items) {
        if (items.size() > 0) {
            System.out.println("Please select one below: ");
            int i = 0;
            for (Item item : items.keySet()) {
                System.out.println(++i + ". Item: '" + item.getItemName() + "' quantity: " + items.get(item) + " price: $" + item.getPrice());
            }
            while (true) {
                String tmp = Utilities.readKeyBoard(scanner, 3, false);
                if (!Utilities.rexValidate(tmp, "-?\\d+(\\.\\d+)?")) {
                    System.out.println("Should be a number. Please select again.");
                    continue;
                }
                int itemIndex = Integer.parseInt(tmp);
                if (itemIndex > items.size() || itemIndex <= 0) {
                    System.out.println("Should be > 0 and <= " + items.size() + ". Please select again.");
                    continue;
                }
                i = 1;
                for (Item item : items.keySet()) {
                    if (i == itemIndex) {
                        return item;
                    }
                    i++;
                }
            }
        }
        return null;
    }

    /**
     * Select employee
     *
     * @param employees Array list of employee objects
     * @return Employee object or null
     */
    private Employee selectEmployee(ArrayList<Employee> employees) {
        if (employees.size() > 0) {
            while (true) {
                System.out.println("Please select one below: ");
                int i = 0;
                for (Employee employee : employees) {
                    System.out.println(++i + ". Name: " + employee.getName());
                }
                int index = Utilities.readNumber(scanner, 1, employees.size());
                if (index > employees.size() || index <= 0) {
                    continue;
                }
                return employees.get(index - 1);
            }
        }
        return null;
    }

    /**
     * Select menu and executing functions from menu
     *
     * @param sb string buffer
     */
    private void selectMenu(StringBuffer sb) {
        boolean flag = true;
        char selection = 0;
        while (flag) {
            switch (inMenu) {
                case HOME_MENU:     // home menu
                    systemInterface.homePage();
                    selection = Utilities.readSelection(scanner);
                    System.out.println(inMenu.toString());
                    switch (selection) {
                        case 'A':
                            inMenu = MENU_ENUM.ORDER_MENU;
                            break;
                        case 'B':
                            System.out.println("B. User information");
                            Utilities.printUnderDevelopmentMsg();
                            break;
                        case 'C':
                            System.out.println("C. Stores information");
                            Utilities.printUnderDevelopmentMsg();
                            break;
                        case 'D':
                            if (employee.getUserType().equals("Owner") || employee.getUserType().equals("Manager")) {
                                systemInterface.reportPage();
                                reportChoice(sb);
                            } else {
                                System.out.println("============================================");
                                System.out.println("Sorry, you do not have access to view report.");
                                System.out.println("============================================");
                            }
                            Utilities.readReturn(scanner);
                            break;
                        case 'E':
                            System.out.println("E. Inventory");
                            Utilities.printUnderDevelopmentMsg();
                            break;
                        case 'F':
                            System.out.println("F. Employee");
                            inMenu = MENU_ENUM.EMPLOYEE_MENU;
                            break;
                        case 'G':
                            System.out.println("Do you want to log out??   y / n");
                            char isExit = Utilities.readConfirmSelection(scanner);
                            if (isExit == 'Y') {
                                System.out.println("See you later");
                                flag = false;
                            }
                            break;
                    }
                    break;
                case ORDER_MENU: // in ORDER Page
                    systemInterface.manageOrderPage();
                    selection = Utilities.readSelection(scanner);
                    System.out.println(inMenu.toString());
                    switch (selection) {
                        case 'A':
                            boolean isContinuous = true;
                            Map<Item, Integer> orderDetail = new HashMap<>();
                            System.out.println("Add an order");
                            Map<String, Integer> updatedItems = new HashMap<>();
                            while (isContinuous) {
                                System.out.println("Please type the item name: (e.g: sandwich) ");
                                String itemName = Utilities.readKeyBoard(scanner, 0, false);

                                Map<Item, Integer> items = invetoryDAL.getItemsByStoreIdAndName(employee.getStoreId(), itemName);
                                if (items.size() == 0) {
                                    System.out.println("No " + itemName + " in inventory. Please select others.");
                                    continue;
                                }
                                Item item = selectItems(items);
                                if (item == null) {
                                    System.out.println("Please select again.");
                                    continue;
                                }
                                System.out.println("Please type the number of item: (must be <= " + items.get(item) + ") ");
                                int quantity = Utilities.readNumber(scanner, 1, items.get(item));

                                if (updatedItems.get(item.getItemId()) == null) {
                                    updatedItems.put(item.getItemId(), items.get(item) - quantity);
                                } else {
                                    updatedItems.put(item.getItemId(), updatedItems.get(item.getItemId()) - quantity);
                                }
                                orderDetail.put(item, quantity);
                                Order.printOrderDetail(orderDetail, false);
                                System.out.println("Do you want to add more item? (Y/N)");
                                char confirmation = Utilities.readConfirmSelection(scanner);
                                if (confirmation == 'N') {
                                    isContinuous = false;
                                }
                            }
                            System.out.println("Is special order? (Y/N)");
                            char isSpecialOrder = Utilities.readConfirmSelection(scanner);
                            String phoneNumber = null;
                            String customerName = null;
                            if (isSpecialOrder == 'Y') {
                                System.out.println("Please type the customer name");
                                customerName = Utilities.readKeyBoard(scanner, 0, false);
                                System.out.println("Please type the customer phone");
                                phoneNumber = Utilities.readKeyBoard(scanner, 0, false);
                            }
                            LocalDate orderDate = LocalDate.now();
                            LocalTime orderTime = LocalTime.now();
                            Order.STATUS status = Order.STATUS.PREPARED;
                            Order.printOrderDetail(orderDetail, true);
                            Order order = new Order(0, employee.getStoreId(), orderDate, orderTime, employee, null, status, customerName, phoneNumber, orderDetail);
                            orderDAL.createOrder(order);
                            invetoryDAL.updateInventory(employee.getStoreId(), updatedItems);
                            break;
                        case 'F':
                            System.out.println("Do you want to back HOME MENU?? y / n");
                            char isExit = Utilities.readConfirmSelection(scanner);
                            if (isExit == 'Y') {
                                System.out.println("Go back to HOME MENU");
                                inMenu = MENU_ENUM.HOME_MENU;
                            }
                            break;
                        default:
                            Utilities.printUnderDevelopmentMsg();
                            break;
                    }
                    break;
                case EMPLOYEE_MENU:     // employee menu
                    if (!employee.getUserType().equals("Manager")) {
                        System.out.println("You don't have permission to access this feature.");
                        System.out.println("Go back to Home Menu.");
                        inMenu = MENU_ENUM.HOME_MENU;
                        continue;
                    }
                    systemInterface.employeeMenu();
                    selection = Utilities.readSelection(scanner);
                    System.out.println(inMenu.toString());
                    switch (selection) {
                        case 'A':
                            Utilities.printUnderDevelopmentMsg();
                            break;
                        case 'B':
                            boolean isContinuous = true;
                            Employee selectedEmployee = null;
                            while (isContinuous) {
                                System.out.println("Please type the employee name: (e.g: steven)");
                                String employeeName = Utilities.readKeyBoard(scanner, 0, false);

                                ArrayList<Employee> employees = employeeDAL.getUserByNameAndStoreId(employeeName, employee.getStoreId());
                                if (employees.size() == 0) {
                                    System.out.println("No employee named " + employeeName + " in system. Please select others.");
                                    continue;
                                }
                                selectedEmployee = selectEmployee(employees);
                                if (employee == null) {
                                    System.out.println("Please select again.");
                                    continue;
                                }
                                isContinuous = false;
                            }
                            updateEmployeeMenu(selectedEmployee);
                            break;
                        case 'C':
                            Utilities.printUnderDevelopmentMsg();
                            break;
                        case 'D':
                            Utilities.printUnderDevelopmentMsg();
                            break;
                        case 'E':
                            System.out.println("Go back to HOME MENU");
                            inMenu = MENU_ENUM.HOME_MENU;
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Select report input
     *
     * @return report choice
     */
    public char selectReport() {
        char c;
        for (; ; ) {
            String str = Utilities.readKeyBoard(scanner, 1, false);
            c = str.charAt(0);
            if (c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7'|| c == '8') {
                break;
            } else {
                System.out.println("Please enter again");
            }
        }
        return c;
    }

    /**
     * Group of constants for the bake shop class
     */
    private enum MENU_ENUM {
        HOME_MENU,
        ORDER_MENU,
        EMPLOYEE_MENU
    }
}
