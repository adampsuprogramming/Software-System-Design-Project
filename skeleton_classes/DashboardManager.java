import java.util.List;
import java.util.Map;

public class DashboardManager {

    private List<String> viewLayout;
    private List<String> selections;
    private Map<String, Integer> userMap;
    private List<Integer> userIds;
    private Loan loan;

    public String updateDashboard(List<String> selections) {
        this.selections=selections;
        // Code here calling createDBRequest helper class
        // Code here getting Singleton instance of DatabaseAccess Class
        // Code here calling DatabaseAccess class with database request via reqDasahboardData method
        // Code here calling parseData class
        // Code here getting Singleton instance of LoanFactory class
        // Code here calling LoanFactory class and passing ParsedData to get loan and setting loan instance variable equal to it via createLoan method
        // Code here getting Singleton instance of ReportSysGateway class
        // Code here calling passing parsed data to ReportSysGateway via generateDashboard method and getting view in return
        return null; // Returns formatted view of the Dashboard
    }

    private String createDBRequest(List<String> selections) {
        //Code here creating formatted request
        return null; // returns formatted Database Request
    }

    private String parse(String dashboardData) {
        // Code here parsing dashBoardData
        return null; // returns Parsed Dashboard Data
    }

    public Map<String, Integer> lookupUser(String name) {
        // Code here getting Singleton instance of DatabaseAccess Class
        // Code here calling DatabaseAccess class with username request via reqUserID method
        // Code here calling createMap helper method passing data returned from DB and setting userMap instance variable equal to it
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
        // Code here creating Commentary object
        // Code here calling processCommentary to format it appropriately
        // Code here adding commentary to Loan object
        // Code here getting Singleton instance of DatabaseAccess Class
        // Code here calling on DatabaseAccess class and passing loan object via updateLoan method
        // Code here getting Singleton instance of EmailSystemGateway Class
        // Code here calling on emailCommentary method in EmailSystemGateway Class to send commentary via email
        return null; // Returns confirmation
    }

    private String processCommentary(String commentary) {
        // Code here processing commentary
        return null; // return processed commentary
    }


}
