import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Data access layer class for the Employee class
 *
 * @ version 1.0
 */
public class EmployeeDAL extends SQLiteConnection {

    /**
     * Return employee details from the database
     *
     * @param id user ID
     * @return      employee object
     */
    public Employee getUserById(int id) {
        Employee employee = null;
        String sql =
                "SELECT EmployeeId, " +
                        "EmployeeName, " +
                        "Email, " +
                        "Password, " +
                        "UserType, " +
                        "TFN, " +
                        "StreetAddress, " +
                        "City, " +
                        "State, " +
                        "Postal, " +
                        "ContactNumber, " +
                        "StoreId " +
                "FROM user " +
                "WHERE user.EmployeeId=" + id;
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                employee = new Employee(rs.getInt("EmployeeId"),
                        rs.getString("EmployeeName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("UserType"),
                        rs.getString("TFN"),
                        rs.getString("StreetAddress"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getInt("Postal"),
                        rs.getString("ContactNumber"),
                        rs.getInt("StoreId")
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
        return employee;
    }

    /**
     * Return employee details from the database by the employee name
     *
     * @param name employee name
     * @return      array list of Employee objects
     */
    public ArrayList<Employee> getUserByNameAndStoreId(String name, int storeId) {
        ArrayList<Employee> employees = new ArrayList<>();
        String sql =
                "SELECT EmployeeId, " +
                        "EmployeeName, " +
                        "Email, " +
                        "Password, " +
                        "UserType, " +
                        "TFN, " +
                        "StreetAddress, " +
                        "City, " +
                        "State, " +
                        "Postal, " +
                        "ContactNumber, " +
                        "StoreId " +
                        "FROM user " +
                        "WHERE lower(user.EmployeeName) LIKE '%" + name.trim().toLowerCase() + "%'";
        // connection to the database and executing query
        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("EmployeeId"),
                        rs.getString("EmployeeName"),
                        rs.getString("Email"),
                        rs.getString("Password"),
                        rs.getString("UserType"),
                        rs.getString("TFN"),
                        rs.getString("StreetAddress"),
                        rs.getString("City"),
                        rs.getString("State"),
                        rs.getInt("Postal"),
                        rs.getString("ContactNumber"),
                        rs.getInt("StoreId")
                );
                employees.add(employee);
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
        return employees;
    }

    /**
     * Update employee details in the database
     *
     * @param employee  Employee object
     * @return          integer
     */
    public int updateEmployee(Employee employee) {
        String sql = "UPDATE user\n" +
                "SET EmployeeName = '" + employee.getName() + "'," +
                "    Email = '" + employee.getEmail() + "'," +
                "    Password = '" + employee.getPassword() + "'," +
                "    UserType = '" + employee.getUserType() + "'," +
                "    TFN = '" + employee.getTFN() + "'," +
                "    StreetAddress = '" + employee.getStreetAddress() + "'," +
                "    City = '" + employee.getCity() + "'," +
                "    State = '" + employee.getState() + "'," +
                "    Postal = " + employee.getPostal() + "," +
                "    ContactNumber = '" + employee.getContactNumber() + "'," +
                "    StoreId = " + employee.getStoreId() +
                " WHERE\n" +
                "    EmployeeId = " + employee.getUserId();

        // connection to the database and executing query
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
        return 0;
    }
}