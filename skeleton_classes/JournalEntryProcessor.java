import java.util.List;

public class JournalEntryProcessor {

    private static JournalEntryProcessor obj; // Store Singleton object
    private String report; // Data for closing journal entries

    private JournalEntryProcessor() {} // Private constructor for Singleton pattern

    public String generateReport(List<String> selections) {
        // 1 - Get Singleton instance of DatabaseAccess Class
        // 2 - Calling DatabaseAccess class with database request via reqJE method
        // 3 - Calling parseEntryData helper method to parse the database request
        // 4 - Get Singleton instance of JournalEntryFactory Class
        // 5 - Loop over every journal entry in parsed data
            // 5a Call JournalEntryFactory class with request to make Journal Entry object via CreateJE
            // 5b Add returned journal entry to a list
        // 6 - Get Singleton instance of ReportSysGateway Class
        // 7 - Call generateAccReport on ReportSysGateway and passing journal entry list
        return null; // return formatted report of journal entries
    }

    private String parseEntryData(String entryData) {
        // Parse journal entry data received from database
        return null; // return parsed data
    }

    public String closePeriod(int periodID) {
        // 1. Call finalized period helper method
        // 2. Call formatTransmissionData
        // 3. Get Singleton instance of CorpAccSysGateway class
        // 4. Use CorpAccSysGateway class to transmit accounting data
        return null; // return confirmation
    }

    private void finalizePeriod(int periodID){
        // Helper method for closing accounting period in System
    }

    private String formatTransmissionData(String data) {
        // Code here to format transmission to accounting system
        return null;
    }

    public static JournalEntryProcessor getInstance() {
        // 1 - Singleton code to check if obj exists.
        // 2 - If it does
            //  a. Return it
            //  b. Otherwise create it and then return it.
        return obj; // returns object
    }
}
