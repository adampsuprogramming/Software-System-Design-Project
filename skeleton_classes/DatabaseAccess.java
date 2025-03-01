import java.util.List;

public class DatabaseAccess {

    private String dBURL = "Database Connection URL Here";
    private double cnnctTimeOut = .25; // Input connection timeout limit here
    private static DatabaseAccess obj; // Store Singleton object

    private DatabaseAccess() {} // Private constructor for Singleton pattern

    public String reqJE(List<String> selections) {
        // 1. Call frmtEntryReq to format the request
        // 2. Call Database with request
        return null; // return entry data
    }

    private String frmtEntryReq(List<String> selections) {
        // Format request
        return null; // return formated request
    }

    public String reqLoan(int loanID) {
        // 1. Call frmtLoanReq to format the request
        // 2. Call Database with request
        return null; // return loan data
    }

    private String frmtLoanReq(int loanID) {
        // Code here to format loan request
        return null; // return formatted request
    }

    public String updateLoan(Loan loan) {
        // 1. Call frmtLoanUpdate to format the request
        // 2. Update loan in database
        return null; // return confirmation
    }

    private String frmtLoanUpdate(Loan loan) {
        // Code here to format loan update request
        return null; // return formatted update request
    }

    public String reqDashboardData(String request) {
        // 1. Call formatDashboardRequest to format the request
        // 2. Request Dashboard info from database
        return null; // return dashboard info
    }

    private String formatDashboardRequest(String request) {
        // Code here to format the request
        return null; // return formatted request
    }

    public String reqUserID(String userName) {
        // 1. Call formatUserRequest to format the request
        // 2. Request user data from database
        return null; // return user data
    }

    private String formatUserRequest(String request) {
        // Code here to format the request
        return null; // return formatted request
    }

    public String reqCF(List<String> selections) {
        // 1. Call frmtCFReq to format the request
        // 2. Request cashflow info from database
        return null; // return cashflow data
    }

    private String frmtCFReq(List<String> selections) {
        // Code here to format the request
        return null; // return formatted request
    }

    public static DatabaseAccess getInstance() {
        // Singleton code here to check if obj exists.  If it does, return it, otherwise create it and then return it.
        return obj; // returns object
    }
}
