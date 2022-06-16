import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Report class, responsible for the Bake shop reports
 *
 * @version 1.0
 */
public class Report {

    private final InvetoryDAL invetoryDAL;
    private final OrderDAL orderDAL;
    private final StoreDAL storeDAL;

    /**
     * Constructor for the Report class
     *
     * @param invetoryDAL Inventory data layer class object
     * @param orderDAL Order data layer class object
     * @param storeDAL Store data layer class object
     */
    public Report(InvetoryDAL invetoryDAL, OrderDAL orderDAL, StoreDAL storeDAL) {
        this.invetoryDAL = invetoryDAL;
        this.orderDAL = orderDAL;
        this.storeDAL = storeDAL;
    }

    /**
     * Report - "Type of coffee sold the most per store in the last month"
     *
     * @return report
     */
    public String GetMostCoffeeReport() {
        LocalDateTime now = LocalDateTime.now().minusMonths(1);
        int currentMonth = now.getMonth().getValue();
        int currentYear = now.getYear();
        ArrayList<ReportCoffeeTypeSoldPerStoreLastMonth> reportDate = storeDAL.getTheMostCoffeeTypeSoldPerStoreLastMonth();
        StringBuffer sb = new StringBuffer("Type of coffee sold the most per store in the last month (" + currentMonth + "-" + currentYear + ")\n");
        for (ReportCoffeeTypeSoldPerStoreLastMonth data : reportDate) {
            sb.append("Store : " + data.storeId + "    Number of " + data.itemName.trim() + " sold in the last month " + ":  " + data.quantity + "\n");
        }
        return sb.toString();
    }

    /**
     * Report - "Total sale made in dollars in the last month per store"
     *
     * @param sb string buffer
     * @return report
     */
    public String revenueForLastMonthReport(StringBuffer sb) {
        Map<Integer, Integer> map = new HashMap<>();
        map = orderDAL.totalDollarsMadeLastMonthInLastMonth();
        int maxStore = storeDAL.getTotalNumaabersofStore();
        sb.delete(0, sb.length());
        sb = new StringBuffer("Total sale made in dollars in the last month per store" + "\n");
        for (int i = 1; i <= maxStore; i++) {
            if (map.containsKey(i))
                sb.append("Store : " + i + "    Last month revenue is :  $" + map.get(i) + "\n");
            else
                sb.append("Store : " + i + "    Last month revenue is :  $0" + "\n");
        }
        return sb.toString();
    }

    /**
     * Report - "Days of the week that made the most sale in the last month per store"
     *
     * @param sb string buffer
     * @return report
     */
    public String daysOfWeekMostSaleReport(StringBuffer sb) {
        Map<Integer, List<Integer>> map = orderDAL.daysOfTheWeekMadeMostSaleInLastMonth();
        Map<Integer, List<Integer>> salesDayForEachStoreMap = new HashMap<>();
        Map<Integer, String> weekdaysMap = Utilities.weekdaysNameMap();
        for (int storeId : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            list = Utilities.frequentDaysForWeeks(map.get(storeId));
            salesDayForEachStoreMap.put(storeId, list);
        }
        Map<Integer, List<String>> res = new HashMap<>();
        res = Utilities.nameTheDays(res, weekdaysMap, salesDayForEachStoreMap);
        int maxStore = storeDAL.getTotalNumaabersofStore();
        sb.delete(0, sb.length());
        sb = new StringBuffer("Days of the week that made the most sale in the last month per store" + "\n");
        for (int i = 1; i <= maxStore; i++) {
            if (res.containsKey(i)) {
                String report = res.get(i).toString();
                sb.append("Store : " + i + "    Days of the week that made the most sale in the last month :  " + report + "\n");
            } else
                sb.append("Store : " + i + "    Days of the week that made the most sale in the last month :  " + "No cash in for last month" + "\n");
        }
        return sb.toString();
    }

    /**
     * Report - "Low in inventory at the end of each week in each store"
     *
     * @param sb string buffer
     * @return report
     */
    public String lowInventoryReport(StringBuffer sb) {
        Map<Integer, List<String>> map = invetoryDAL.getLowInventoryForEachStore();
        sb.delete(0, sb.length());
        sb = new StringBuffer("Inventory Report for each store" + "\n");
        for (int id : map.keySet()) {
            String report = map.get(id).toString();
            sb.append("Store : " + id + " " + report + "\n");
        }
        return sb.toString();
    }

    /**
     * Report - "Total number of food items sold in last month in each store"
     *
     * @param sb string buffer
     * @return report
     */
    public String foodReport(StringBuffer sb) {
        Map<Integer, Integer> map = new HashMap<>();
        map = orderDAL.getAllStoreFoodItemsSalesForLastMonth();
        int maxStore = storeDAL.getTotalNumaabersofStore();
        sb.delete(0, sb.length());
        sb = new StringBuffer("Total number of food items sold in last month in each store" + "\n");
        for (int i = 1; i <= maxStore; i++) {
            if (map.containsKey(i))
                sb.append("Store : " + i + "    Number of food items sold in the last month :  " + map.get(i) + "\n");
            else
                sb.append("Store : " + i + "    Number of food items sold in the last month :  0" + "\n");
        }
        return sb.toString();
    }

    /**
     * Report - "Total number of coffee beans (in quantity) sold in last month in each store"
     *
     * @param sb string buffer
     * @return report
     */
    public String CoffeeBeansReport(StringBuffer sb) {
        Map<Integer, Integer> map = orderDAL.getAllStoreCoffeeBeanSalesForLastMonth();
        int maxStore = storeDAL.getTotalNumaabersofStore();
        sb.delete(0, sb.length());
        sb = new StringBuffer("Total number of coffee beans sold in the last month in each store" + "\n");
        for (int i = 1; i <= maxStore; i++) {
            if (map.containsKey(i))
                sb.append("Store : " + i + "    Number of Coffee beans sold in the last month :  " + map.get(i) + "\n");
            else
                sb.append("Store : " + i + "    Number of Coffee beans sold in the last month :  0" + "\n");
        }
        return sb.toString();
    }

    /**
     * Report - "Total number of coffee sold in the last month in each store"
     *
     * @param sb string buffer
     * @return report
     */
    public String CoffeeReport(StringBuffer sb) {
        Map<Integer, Integer> map = new HashMap<>();
        map = orderDAL.getAllStoreCoffeeSalesForLastMonth();
        int maxStore = storeDAL.getTotalNumaabersofStore();
        sb.delete(0, sb.length());
        sb = new StringBuffer("Total number of coffee sold in the last month in each store" + "\n");
        for (int i = 1; i <= maxStore; i++) {
            if (map.containsKey(i))
                sb.append("Store : " + i + "    Number of Coffee sold in the last month :  " + map.get(i) + "\n");
            else
                sb.append("Store : " + i + "    Number of Coffee sold in the last month :  0" + "\n");
        }
        return sb.toString();
    }
}
