import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
* Utilities class provides utility methods to the other classes
 *
 * @version 1.0
 *
 */
public class Utilities {

    /**
     * Read operations from the keyboard
     *
     * @param scanner Scanner object
     * @param limit limit
     * @param blankReturn blank return
     * @return            String keyboard input
     */
    public static String readKeyBoard(Scanner scanner, int limit, boolean blankReturn) {
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.length() == 0) {
                if (blankReturn) return line;
                else continue;
            }
            if (limit > 0 && line.length() > limit) {
                System.out.println("The max length is " + limit + ". Please type again.");
                continue;
            }
            break;
        }
        return line;
    }

    /**
     * Read operations from the keyboard (number)
     *
     * @param scanner Scanner object
     * @param min minimum limit
     * @param max maximum limit
     * @return      integer, keyboard input
     */
    public static int readNumber(Scanner scanner, int min, int max) {
        while(true) {
            String tmp = Utilities.readKeyBoard(scanner, max, true);
            if (!Utilities.rexValidate(tmp, "-?\\d+(\\.\\d+)?") || tmp.isEmpty()) {
                System.out.println("Should be a number. Please select again.");
                continue;
            }
            int number = Integer.parseInt(tmp);
            if(number < min && number > max) {
                System.out.println("The number must be >= " + min +  " and <= " + max);
                continue;
            }
            return number;
        }
    }

    /**
     * Read selection from the keyboard
     *
     * @param scanner Scanner object
     * @return keyboard selection
     */
    public static char readSelection(Scanner scanner) {
        char c;
        for (; ; ) {
            String str = readKeyBoard(scanner, 1, false);
            c = str.toUpperCase().charAt(0);
            if (c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E'
                    && c != 'F' && c != 'G' && c != 'H' && c != 'I' && c != 'J'
                    && c != 'L' && c != 'M') {
                System.out.print("Please enter again: ");
            } else break;
        }
        return c;
    }

    /**
     * Confirm selection
     *
     * @param scanner Scanner object
     * @return        confirmation
     */
    public static char readConfirmSelection(Scanner scanner) {
        char c;
        for (; ; ) {
            String str = readKeyBoard(scanner, 1, false).toUpperCase();
            c = str.toUpperCase().charAt(0);
            if (c == 'Y' || c == 'N') {
                break;
            } else {
                System.out.print("Please enter again");
            }
        }
        return c;
    }

    /**
     * Return to home page
     *
     * @param scanner Scanner object
     */
    public static void readReturn(Scanner scanner){
        System.out.println("Please enter return to home page");
        readKeyBoard(scanner, 100,true);
    }

    /**
     * Match text against regular expression
     *
     * @param text text
     * @param rex regular expression
     * @return      return boolean result of match
     */
    public static boolean rexValidate(String text, String rex) {
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();

    }

    /**
     * Print message on the screen for features, which are not developed
     */
    public static void printUnderDevelopmentMsg() {

        System.out.println("This feature is under development");
    }

    /**
     * Frequent days for weeks
     *
     * @param list list integers
     * @return frequent days for weeks
     */
    public static List<Integer> frequentDaysForWeeks(List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer x : list) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for (Integer number : map.keySet()) {
            int freq = map.get(number);
            if (!freqMap.containsKey(freq))
                freqMap.put(freq, new ArrayList<>());
            freqMap.get(freq).add(number);
        }
        List<Integer> ans = new ArrayList<>();
        Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
        ans.addAll(entry.getValue());
        return ans;
    }

    /**
     * Days of the week
     *
     * @return map with days of the week
     */
    public static Map<Integer, String> weekdaysNameMap() {
        Map<Integer, String> weekDaysMap = new HashMap<>();
        weekDaysMap.put(0, "Sunday");
        weekDaysMap.put(1, "Monday");
        weekDaysMap.put(2, "Tuesday");
        weekDaysMap.put(3, "Wednesday");
        weekDaysMap.put(4, "Thursday");
        weekDaysMap.put(5, "Friday");
        weekDaysMap.put(6, "Saturday");
        return weekDaysMap;
    }

    /**
     * Days of the week for the report
     *
     * @param res                     list strings
     * @param weekdaysNameMap         map days of the week
     * @param salesDayForEachStoreMap report
     * @return days of the week
     */
    public static Map<Integer, List<String>> nameTheDays(Map<Integer, List<String>> res, Map<Integer, String> weekdaysNameMap
            , Map<Integer, List<Integer>> salesDayForEachStoreMap) {
        for (int id : salesDayForEachStoreMap.keySet()) {
            List<String> list = new ArrayList<>();
            for (Integer days : salesDayForEachStoreMap.get(id)) {
                String weekday = weekdaysNameMap.get(days);
                list.add(weekday);
            }
            res.put(id, list);
        }
        return res;
    }
}
