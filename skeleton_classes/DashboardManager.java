import java.util.List;
import java.util.Map;

public class DashboardManager {

    private List<String> viewLayout;
    private List<String> selections;
    private Map<String, Integer> userMap;
    private List<Integer> userIds;
    private Loan loan;
    private static DashboardManager obj; // Store Singleton object

    private DashboardManager() {} // Private constructor for Singleton pattern

    public String updateDashboard(List<String> selections) {
        this.selections=selections;
        // 1. Call createDBRequest helper class
        // 2. Get Singleton instance of DatabaseAccess Class
        // 3. Call DatabaseAccess class with database request via reqDasahboardData method
        // 4. Call parseData class
        // 5. Get Singleton instance of LoanFactory class
        // 6. Call LoanFactory class and pass ParsedData to get loan and setting loan instance variable equal to it via createLoan method
        // 7. Get Singleton instance of ReportSysGateway class
        // 8. Pass parsed data to ReportSysGateway via generateDashboard method and get view in return
        return null; // Returns formatted view of the Dashboard
    }

    private String createDBRequest(List<String> selections) {
        //Create formatted request
        return null; // returns formatted Database Request
    }

    private String parse(String dashboardData) {
        // Parse dashBoardData
        return null; // returns Parsed Dashboard Data
    }

    public Map<String, Integer> lookupUser(String name) {
        // 1. Get Singleton instance of DatabaseAccess Class
        // 2. Call DatabaseAccess class with username request via reqUserID method
        // 3. Call createMap helper method passing data returned from DB and setting userMap instance variable equal to it
        return null; // returns map of usernames to user ids
    }

    private Map<String, Integer> createMap(String data) {
        // Code here creating map
        return null; // should return mapped data
    }

    public void userTag(List<Integer> list) {
        userIds = list; // Sets list of user ids to selected users
    }

    public String saveCommentary(String commentary) {
        // 1. Creating Commentary object
        // 2. Call processCommentary to format it appropriately
        // 3. Add commentary to Loan object
        // 4. Get Singleton instance of DatabaseAccess Class
        // 5. Call on DatabaseAccess class and passing loan object via updateLoan method
        // 6. Get Singleton instance of EmailSystemGateway Class
        // 7. Call on emailCommentary method in EmailSystemGateway Class to send commentary via email
        return null; // Returns confirmation
    }

    private String processCommentary(String commentary) {
        // Code here processing commentary
        return null; // return processed commentary
    }

    public static DashboardManager getInstance() {
        // Singleton code here to check if obj exists.  If it does, return it, otherwise create it and then return it.
        return obj; // returns object
    }

}
