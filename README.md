# Software System Design for TBD

## A Commercial Mortgage Loan Accounting System

### Author: Adam Slager

#### Date: February 25, 2025

# Table of Contents
- [Problem Statement and Requirements](#Problem-Statement-and-Requirements)
- [UML Use Case Diagram](#uml-use-case-diagram)
- [UML Domain Model](#uml-domain-model)
- [UML Class Diagram](#uml-class-diagram)
- [Sequence Diagrams](#sequence-diagrams)
- [State Diagram](#state-diagram)
- [Swimlane Diagram](#swimlane-diagram)
- [UML Component Diagram](#uml-component-diagram)
- [Cloud Deployment Diagram](#cloud-deployment-diagram)
- [Skeleton Classes and Tables Definition](#skeleton-classes-and-tables-definition)
- [Design Patterns](#design-patterns)
- [References](#references)

# Problem Statement and Requirements

## Business Requirements

### Problem Statement

**Background**

*Note: The following document uses the example of The Commercial Mortgage Corporation throughout.  All situations are fictional, and the company does not exist.*

The Commercial Mortgage Corporation (the “Company”) is a Commercial Mortgage Loan (“Loan”) originator and servicer that was founded in the early 1990’s.  In recent years, the company has grown substantially, entering several new territories and hiring a team of experienced originators and support staff, such as accountants, treasury analysts, and loan analysts.  Origination volume has nearly doubled in both count and dollar amount and the servicing portfolios are growing at a rate of 20 percent per year.  

**Problem Description**

Recently, several month-end closes were delayed due to errors found in spreadsheet formulas.  In a separate incident, financial statements had to be reissued due to a material error in calculating loan losses.  Additionally, a major default event for a large Loan was missed, due to a lack of communication between the credit officer and the loan team.  Lastly, KPI’s related to the timeliness of accounting functions have been increasing substantially, as the Loan portfolio has grown.  

Over the course of several months, multiple meetings and discussions were initiated by the management team and ownership group (“Management”) to address the issues.  They concluded that the software being used by the Company was the primary problem in all the major issues.  The Company uses a series of spreadsheets and a Microsoft Access Database to keep track of the accounting, servicing, and analytics of its Loan portfolio.  The spreadsheets are unwieldy and prone to error and the Access Database is slow and cumbersome to use.  The two are not connected and significant time is spent reconciling them during period closes.  Management has proposed that a new software system be developed to replace the current spreadsheets and databases.  The Company is already using new accounting software for its primary general ledger, so this new software would need to interface with it.  

**Desired Outcomes**

Management’s primary goal is to reduce time spent on issues related to poor systems and to instead allocate to value added activities, such as reporting and analysis.  Additionally, Management has requested a single source of truth for multiple constituents to be able to reliably reference, for all aspects of loan accounting and servicing, including certain key metrics and covenants monitored for portfolio management.  

**Problem Statement Summary**

The Company has been missing expectations and goals set by Management due to substantial growth and reliance on dated loan accounting, monitoring, and servicing technology.  Management would like to implement a new loan servicing system to help remedy these problems.

### System Functionalities Required

The following are the required System Functionalities.  
- **Record Keeping** – The System is required to track all key aspects of the loan from origination until its ultimate satisfaction or termination.  The following are key areas that need to be tracked:
  - **Loan Details** – Includes all loan closing information, such as loan amount, interest rate, fees, and pertinent dates.
  - **Loan Financial Information** – Information about the underlying financial performance of the loan that is updated at defined periods.  This includes most information that a Credit Officer would be responsible for monitoring, such as covenants, data input from financial statements, default information, etc.
  - **Collateral Details** – Information about the underlying property collateralizing the mortgages, including appraised value, property description, etc. 
  - **Amortization Schedule** – Includes all information pertinent to creating a loan amortization schedule.
  - **Cash Flows** – Includes specific information detailing every cash flow made by the loan, both forecasted and actual.
- **Billing** – The System is required to create invoices for Borrowers and automatically send those invoices via email.
- **Portfolio Level Accounting** – The System is required to provide all functions of a subledger for mortgages.  This includes:
  - **Calculations** – The System will calculate all pertinent revenue, cash flows, losses, amortization, etc.
  - **Journal Entries** – The System will create all required journal entries for the above calculated amounts
  - **Interface with Corporate Accounting** – After review, the System will interface with the Corporate Accounting System on a periodic basis (coinciding with monthly/quarterly accounting closes) and send complete journal entries to the Corporate Accounting System.
- **Portfolio Monitoring** – The System will provide a dashboard for the Credit Officers, allowing for the following:
  - **Credit Information** – The Dashboard will display views related to individual credits that both detail and summarize pertinent information, such as trends in loan financial information, covenants, etc.
  - **Credit Commentary** – The Dashboard will provide functionality for the Credit Officers to write detailed commentary about the Loan.  The Credit Officers will be able to tag other uses in the commentary.  These users (who may not be frequent users of the System, such as upper management) will be sent copies of the commentary via email.  The System will interface directly with the email system to send the commentary.
- **Treasury Management** – The System will provide functionality for the Treasury department:
  - **Forecast incoming cash flows** - A primary function of Treasury is to ensure that cash is managed properly (anticipating cash flows, rightsizing borrowings, ensuring sufficient cash on hand for fundings).  The System will provide Treasury with forecasted cash flow information.
  - **Interaction with Banking System** - The System will allow Treasury to directly upload cash flow data into the bank’s system.  Using the bank’s tools (external to the System), treasury will be able to automatically match actual cash movements with forecasted cash.
- **Data Entry** – The System will provide a user interface to input data and manage loans, which will be intuitive and promote efficiency.

### Target Users and Their Needs

The following table describes the target users of the System (the “Primary Actors”) and their needs (i.e. their goal description).  In addition to the target users of the system, the table also details the Supporting and Offstage actors (as defined in the Canvas module). *(Larman, 2005)*

| Type      | Actor                         | Goal Description                                                                                                                                                                                                                                     |
|-----------|-------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Primary   | Loan Analyst                  | The Loan Analyst wants to use the System to input Mortgage attributes after loan closing and update any loan attributes after amendments.                                                                                                           |
| Primary   | Loan Accountant               | The Loan Accountant wants to use the system to generate journal entries and revenue, expense, and cash flow reports for the population of mortgages (the “Portfolio”) and to have the System feed the corporate accounting system.          |
| Primary   | Billing Analysts              | The Billing Analysts want to use the System to generate and send invoices for Loan borrowers (the “Customers”).                                                                                                                                      |
| Primary   | Treasury Analysts             | The Treasury Analysts want to use the System to generate cash flow files for forecasting and upload them to banking software.                                                                                                                          |
| Primary   | Credit Officers               | Credit Officers want to use the System to review data related to the Customer’s financial position and credit worthiness and the Loan collateral’s value. They also want to utilize the System to view loan dashboards and add commentary. |
| Supporting| Corporate Accounting System   | The Corporate Accounting System is an external system that wants to use journal entries generated by the System to consolidate into the main general ledger.                                                                                       |
| Supporting| Email System                  | The Email System is an external system that wants to send invoices, messages, and attachments from the System to internal and external parties.                                                                                                     |
| Supporting| Reporting System              | The Reporting System is an external system that wants to use the data produced by the System for analysis, report, and dashboard generation.                                                                                                        |
| Supporting| Banking Software              | The Banking Software is an external system (or series of systems) that wants to use the data generated by the system for account reconciliation.                                                                                                    |
| Supporting| System Administrator          | The System Administrator wants the System to remain running and error free and to help users when errors do arise.                                                                                                                                    |
| Offstage  | Loan Closing Team             | The Loan Closing Team wants to ensure that all relevant information from the closing process is accurately input into the System, as the loan performance inputs their financial targets and compensation.                                      |
| Offstage  | Legal Department              | The Legal Department wants to be notified of any defaulting loans by Credit Officers and Management (via System) to begin remediation.                                                                                                             |
| Offstage  | Customers (Borrowers)         | The Customers want to receive accurate billing statements from the System.                                                                                                                                                                           |
| Offstage  | Corporate Accounting Team     | The Corporate Accounting team wants to utilize the information generated by the System as an asset subledger and to be able to rely on the accuracy of the data produced.                                                                         |
| Offstage  | Management Team/Owners of Company | The Management Team/Owners of the Company want to ensure that the System is efficient, accurate, and scalable. They want to ensure that it helps facilitate existing processes and that it can adapt to new ones.                               |
| Offstage  | External Auditors             | External auditors want to be able to utilize and rely on the output of the System. They want to be able to test the System for controls reliance and be able to generate required reports for their audit procedures.                         |
| Offstage  | Financial Regulators          | Financial regulators want to ensure that the System is accurate from both a billing and financial reporting perspective, to protect the Customers and those parties that would rely on the financial information that it produces.              |

### Business Goals that System Is Required to Support

| Goal                                                                                                                                     | How System Supports Goal                                                                                                                                                                                                                                   |
|------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Shortening monthly accounting close time by two days and Quarterly accounting close by three days within next two years              | Efficiencies created by combining disparate systems, ease of use with user interface design, and error prevention through carefully design calculations and automatic journal entry generation.                                                     |
| Supporting increased originations of 20 percent annually and a servicing portfolio growth of a similar degree.                           | Efficiencies created by ease of training and use, performance of system, and scalable design.                                                                                                                                                                |
| Supporting the potential acquisition of a smaller competitor within next five years                                                      | Scalable design, ease of use, and use of GRASP techniques for interoperability.                                                                                                                                                                            |
| Decreasing the processing time of financial information received from borrowers by 50 percent within the next three years.                | Efficiencies created by moving onto a modern system, including modern GUI, system response time, and built in workflows.                                                                                                                                      |
| Zero financial statement reissuance due to accounting errors                                                                             | Similar to shortening monthly accounting close, the system will support accuracy by implementing error prevention through carefully design calculations and automatic journal entry generation.                                                      |
| Decrease in realized losses of 10 percent in the next three years                                                                         | Integrating loan credit reporting directly into the System and decreasing time in processing borrower financial information (as per above).                                                                                                                |
| Shortening loan onboarding process by 25 percent over the next three years                                                                 | Similar to processing financial information, efficiencies will be created by moving onto a modern system, including modern GUI, system response time, and built in workflows.                                                                      |
| Decreasing overnight cash balances by 50 percent over next two years                                                                       | Providing accurate cash forecasting information to Treasury, allowing them to better forecast cash balances and pay down short-term debt.                                                                                                                   |



# UML Use Case Diagram

## Use Case and System Sequence Diagrams (SSDs) Introduction

In deciding which use cases are most relevant to the design of the System, potential use cases were evaluated and a selection of those which are critical to the desired business functionality, and which will have a substantial impact on design decisions were chosen *(Larman, 2005)*.  These use cases were also chosen because they involve primary user types of the System (the five primary actors depicted in the previous section).  By sampling functionality from each of those five actors, the major components and much of the primary desired functionality of the System were captured and documented.  Note that only a sampling of use case extensions was captured in the documentation of these use cases, due to the scope of this document.  

The use cases listed were further illustrated through the documentation of SSDs, which show interactions and treat the System as a black box.  These SSDs are useful in determining the inflows and outflows between the primary actors, the System, and any secondary actors that might be involved in the chosen use cases.  They are also helpful in developing Sequence Diagrams (SDs).

## Use Cases and System Sequence Diagrams

**Use Case 1**

| Use Case Section           | Comment |
|----------------------------|---------|
| **Use Case Name**              | Enter Loan Details |
| **Scope**                      | Commercial Mortgage Loan Servicing System |
| **Goal in Context**            | A Loan Analyst wants to enter details of a Loan into the system following the loan closing |
| **Level**                      | User goal |
| **Primary Actors**             | Loan Analyst |
| **Stakeholders and Interests** | <ins>Loan Analyst</ins>: Wants to enter and save details of the loan, including financial, categorical, contact, and description details<br><br><ins>Loan Accountant</ins>: Wants the details to be entered and saved accurately, as the accountant will be responsible for generating journal entries based the information<br><br><ins>Billing Analyst</ins>: Wants information to be entered and saved accurately, as invoices will be generated using this information<br><br><ins>Treasury Analyst</ins>: Wants information to be entered and saved accurately, as they need to reconcile and forecast cash activity generated by this information<br><br><ins>Credit Officers</ins>: Want information to be entered and saved accurately, as they need to use this information as a starting point for credit analysis<br><br><ins>Corporate Accounting System</ins>: Wants to interface with the System to receive accounting information partially based on this input<br><br><ins>Reporting System</ins>: Wants to interface with the System to generate reports and analytics based on this input<br><br><ins>Email System</ins>: Wants to interface with the System to send invoices to Customers based on this input<br><br><ins>Loan Closing Team</ins>: Wants information to be entered and saved accurately, since their performance and compensation will be partially measured by this information<br><br><ins>Customers</ins>: Want information to be entered and saved accurately, since they will be billed based on this information<br><br><ins>Corporate Accounting Team</ins>: Wants information to be entered and saved accurately, since it will ultimately feed financial statements (via the Corporate Accounting System) and because they are ultimately responsible for reporting correct financial information<br><br><ins>Management</ins>: Wants information to be entered and saved accurately, as they run the Company and the records represent the results of the Company’s core product offering. They are also judged on financial information, which is partially based on this input<br><br><ins>External Auditors</ins>: Want information to be entered and saved accurately, as this information will be used to produce financial statements, which the auditors are required to opine on<br><br><ins>Financial Regulators</ins>: Want information to be entered and saved accurately, as this information will be used to produce financial information that will affect both investors and Customers. |
| **Preconditions**              | - Loan has closed and funded<br>- Loan Analyst has legal documents and any summary data prepared by Loan Closing Team<br>- Loan Analyst has an account and is authenticated  |
| **Success Guarantee**          | Loan is created and saved in System |
| **Main Success Scenario**      | 1. Loan Analyst clicks on ‘New Loan Boarding’ button<br>2. System displays New Loan Boarding screen (opening to Customer Information)<br>3. Loan Analyst completes fields on screen (may include text field, numerical field, dropdowns, etc.) that are related to Customer and its financial information at the Loan closing date.<br>*** May need to separate into several tabs or screens ***<br>4. Loan Analyst selects ‘Cash Flow’ tab/section<br>5. System displays screen with quantitative fields and empty cash flow schedule<br>6. Loan Analyst fills out fields and selects ‘Generate Cash Flow’<br>7. System calculates and returns screen populated with a calculated cash flow and amortization schedule.<br>8. Loan Analyst reviews information for correctness and clicks ‘Save’<br>9. System creates new record |
| **Extensions**                 | <ins>Loan Analyst Does Not Fill Out Enough Information for Cash Flow Creation</ins><br>4.a. System displays message informing Loan Analyst that data required to calculate cash flow is missing.<br>4.b. System highlights required and missing fields in red.<br>4.c. Loan Analyst inputs fields.<br>*After inputting fields, repeat Step 4.* <br><br><ins>System Finds Error When Processing Cash Flows</ins><br>6.a. System displays message informing Loan Analyst of error<br>6.b. System highlights fields on Cash Flow tab that are causing error.<br>6.c. Loan Analyst corrects fields.<br>*After correcting fields, repeat Step 6.* |
| **Special Requirements**       | Refer to Non-functional Requirement Section of document for any related non-functional requirements. |

**SSD for Use Case 1**

![SSD1](/Images/SSD-Loan_Details.png)

**Use Case 2**

| Use Case Section           | Comment |
|----------------------------|---------|
| **Use Case Name**              | Perform Accounting Procedures |
| **Scope**                      | Commercial Mortgage Loan Servicing System |
| **Goal in Context**            | A Loan Accountant wants to perform period-end accounting procedures to generate accounting reports and feed into primary general ledger system (the “Corporate Accounting System”) |
| **Level**                      | User goal |
| **Primary Actors**             | Loan Accountant |
| **Stakeholders and Interests** | <ins>Corporate Accounting System</ins>: Wants to interface with the System to receive information generated as a result of this step.<br><br><ins>Corporate Accounting Team</ins>: Wants this information and its transfer into the Corporate Accounting System to be timely and accurate, as they are responsible for closing the books and producing the financial statements based on this data.<br><br><ins>Management</ins>: Wants this information and its transfer into the accounting system to be accurate, as they utilize and are judged by the information in the financial statements and Corporate Accounting System.<br><br><ins>External Auditors</ins>: Want information and its transfer to be accurate, as they are responsible for auditing the System, the Corporate Accounting System, the transfer process, and the Financial Statements.<br><br><ins>Financial Regulators</ins>: Want information to be accurate, as it is used to produce financial statements and other information that investors utilize and rely on to make investing decisions. |
| **Preconditions**              | - Loan Accountant has an account and is authenticated<br>- All relevant transactional data has been loaded into the System and reconciled, as appropriate. |
| **Success Guarantee**          | System has displayed relevant reports and has transferred accounting information to Corporate Accounting System. |
| **Main Success Scenario**      | 1. Loan Accountant selects Reporting tab from Accounting Menu screen<br>2. System displays screen showing Reporting tab, including multiple reports and input fields (i.e. for start date, end date, accounting period, etc.)<br>3. Loan Accountant fills out relevant fields and selects ‘Generate Report’ button<br>4. System processes reporting data.<br>5. System sends relevant information to external Reporting System and displays returned report<br>*** Loan Accountant repeats steps 3, 4, and 5 for as many reports as required ***<br>6. Loan Accountant selects Close Out Period tab<br>7. System displays Period Close screen<br>8. Loan Accountant fills out any relevant fields and selects ‘Close Period and Transmit Data’<br>9. System locks period's accounting data and transmits to external Corporate Accounting System |
| **Extensions**                 | <ins>Loan Accountant Enters Fields Incorrectly</ins><br>3.a. System informs Loan Accountant of error by displaying error message and highlighting fields entered erroneously.<br>3.b. Loan Accountant corrects fields<br>*Repeat Step 3*<br><br><ins>Error in Connecting to Reporting System</ins><br>4.a. System displays message to Loan Accountant that they are unable to generate report at this time.<br>4.b. System sends error message to System Administrator.<br>*System does not continue main success scenario*<br><br><ins>Loan Accountant Finds Error</ins><br>5.a. Loan Accountant cancels out of process.<br>5.b. Loan Accountant contacts Loan Analyst to correct data.<br>*Upon correction, process would begin again at step 1* |
| **Special Requirements**       | Refer to Non-functional Requirement Section of document for any related non-functional requirements. |

**SSD for Use Case 2**

![SSD2](/Images/SSD-Perform_Acct_Proc.png)

**Use Case 3**

| Use Case Section           | Comment |
|----------------------------|---------|
| **Use Case Name**              | Generate Customer Invoices |
| **Scope**                      | Commercial Mortgage Loan Servicing System |
| **Goal in Context**            | A Billing Analyst wants to a generate monthly invoices and automatically email to customers |
| **Level**                      | User goal |
| **Primary Actors**             | Billing Analyst |
| **Stakeholders and Interests** | <ins>Billing Analyst</ins>: Wants to quickly, easily, and accurately generate and send monthly invoices<br><ins>Treasury Analyst</ins>: Wants this billing information to be accurate and to feed into cash activity reporting, as it will be utilized to forecast cash and feed into external banking system<br><ins>Corporate Accounting System</ins>: Wants to interface with the System to receive information generated as a result of this step<br><ins>Customers</ins>: Want this information to be easy to read, accurate, and timely, as the Customers will be remitting payments based on the generated invoices.<br><ins>Corporate Accounting Team</ins>: Wants this information to be correct, as any errors in calculation could have ramifications for the financial statements and Corporate Accounting System<br><ins>Management</ins>: Wants this information to be easy to read, accurate, and timely, as delivering a quality experience to the Customer ensures repeat business. Also, any errors could have legal ramifications for the Company.<br><ins>External Auditors</ins>: Want this information to be accurate and easy to trace, as they will be auditing and opining on these calculations<br><ins>Financial Regulators</ins>: Want this information to be accurate as it impacts both the financial statements and the Customers. |
| **Preconditions**              | - Billing Analyst has an account and is authenticated<br>- All necessary information required to generate an invoice is up to date in System (i.e. any variable reference rates, any changes to contractual terms, etc.) |
| **Success Guarantee**          | - System has generated and displayed invoices.<br>- System has emailed invoices to appropriate parties. |
| **Main Success Scenario**      | 1. Billing Analyst selects ‘Invoices’ page<br>2. System displays screen with multiple fields and menu selections<br>3. Billing Analyst selects appropriate borrowers, billing periods, and any other relevant selections. Billing Analyst clicks on ‘Generate’<br>4. System processes and generates invoice data<br>5. System processes data and refines into invoices. Billing Analyst review invoices.<br>6. Billing Analyst confirms proper selections have been made and selects ‘Email Invoices to Customer’<br>7. System displays option screen allowing Billing Analyst to customize email<br>8. Billing Analyst customizes and clicks ‘Send’<br>9. System sends relevant information (i.e. email address, messages, invoices) to external Email System and email system sends email |
| **Extensions**                 | <ins>Billing Analyst Has Not Entered All Required Fields</ins><br>3.a. System informs Billing Analyst of missing fields and highlights them.<br>3.b. Billing Analyst enters missing fields and selects ‘Generate’<br>*Proceed with Step 4*<br><br><ins>System Cannot Connect to Email System</ins><br>8.a. System informs Billing Analyst that invoices have not been sent to Customer and to try again later.<br>8.b. System Sends Error Message to System Administrator<br>*System does not continue main success scenario*<br><br><ins>Billing Analyst Finds Error In Invoices</ins><br>5.a. Billing analyst cancels out of process.<br>5.b. Billing analyst contacts Loan Analyst to correct data.<br>*Upon correction, process would begin again at step 1* |
| **Special Requirements**       | Refer to Non-functional Requirement Section of document for any related non-functional requirements. |

**SSD for Use Case 3**

![SSD3](/Images/SSD-Generate_Cust_Inv.png)

**Use Case 4**

| Use Case Section           | Comment |
|----------------------------|---------|
| **Use Case Name**              | Analyze Portfolio Metrics |
| **Scope**                      | Commercial Mortgage Loan Servicing System |
| **Goal in Context**            | A Credit Officer wants to view a dashboard for a selected portion of the portfolio detailing credit metrics, write commentary based on it, and send commentary to relevant parties. |
| **Level**                      | User goal |
| **Primary Actors**             | Credit Officer |
| **Stakeholders and Interests** | <ins>Credit Officers</ins> – Want to utilize this information to anticipate any potential risk situations or defaults as early as possible<br><ins>Management</ins> – Want to use the analysis generated by the Credit Officers to enter into any workout situation and exercise any legal remedies as early as possible<br><ins>Legal Department</ins> – Wants to be made aware of any required amendments or foreclosures required |
| **Preconditions**              | - Monthly or Quarterly Covenant and Financial Statement Data has been entered into the System by Loan Analyst (Use Case not detailed in this document but will be similar to Enter Loan Details use case, except will occur on recurring basis).<br>- Credit Officer has an account and is authenticated |
| **Success Guarantee**          | - Dashboard is generated<br>- Commentary is Saved<br>- Email with commentary is sent to appropriate parties |
| **Main Success Scenario**      | 1. Credit Officer selects ‘Reporting Dashboard’ menu<br>2. System displays ‘Reporting Dashboard’ options and sends to front-end<br>3. Credit Officer makes relevant selections and selects ‘update dashboard’<br>4. System processes and generates dashboard data.<br>5. System sends relevant information to external Reporting System and displays returned dashboard. Credit Officer reviews dashboard.<br>6. Credit officer writes commentary (could be credit related, accounting related, payment related, etc.).<br>7. Credit officer selects related users (i.e. Treasury analyst for payment-related commentary) and saves commentary.<br>8. System saves data and generates emails.<br>9. System sends email via Email System along with relevant commentary to selected users. |
| **Extensions**                 | <ins>System Cannot Connect to Reporting System</ins><br>5.a. System informs Credit Officer that it cannot generate the dashboard and to try again later.<br>5.b. Sends Error Message to System Administrator<br>*System does not continue main success scenario*<br><br><ins>Credit Officer Does Not Completely Fill Out Selections</ins><br>3.a. System informs Credit Officer of missing fields and highlights them.<br>3.b. Credit Officer Analyst enters missing fields and selects ‘update dashboard’<br>*Scenario continues with step 4*<br><br><ins>System Cannot Connect to Email System</ins><br>7.a. System informs Credit Officer that cannot send commentary<br>7.b. Sends Error Message to System Administrator<br>*System does not continue main success scenario*<br><br><ins>Credit Officer Finds Errors When Reviewing data</ins><br>5.a. Credit Officer cancels out of process<br>5.b. Credit Officer informs Loan Analyst of errors<br>*Scenario continues with step 1 once Loan Analyst has corrected errors.* |
| **Special Requirements**       | Refer to Non-functional Requirement Section of document for any related non-functional requirements. |

**SSD for Use Case 4**

![SSD4](/Images/SSD-Analyze_Portfolio_Metrics.png)

**Use Case 5**

| Use Case Section           | Comment |
|----------------------------|---------|
| **Use Case Name**              | Generate Cash Flow Data |
| **Scope**                      | Commercial Mortgage Loan Servicing System |
| **Goal in Context**            | A Treasury Analyst wants to generate a flat file of cash activity to use in cash forecasting |
| **Level**                      | User goal |
| **Primary Actors**             | Treasury Analyst |
| **Stakeholders and Interests** | <ins>Treasury Analyst</ins>: Wants to use cash activity file to anticipate cash flows for the day, so that cash position can be managed<br><ins>Loan Analyst</ins>: Wants to know if any payments have not been received, as they are the first line of communication to the Customer in the case of a missed payment<br><ins>Credit Officers</ins>: Wants to work with the Loan Analyst to remedy any missed payments so that Customer defaults can be prevented. |
| **Preconditions**              | - All relevant information to generate cash flows has been entered into System (i.e. new Loans have been onboarded and any changes to contractual terms have been made, etc.)<br>- Treasury Analyst has an account and is authenticated<br>- System is displaying Treasury Operations menu |
| **Success Guarantee**          | - Cash Activity has been displayed on user’s screen<br>- Data has been sent to external banking software |
| **Main Success Scenario**      | 1. Treasury Analyst selects ‘Cash Activity” from Treasury Operations screen<br>2. System displays ‘Cash Activity’ page<br>3. Treasury Analyst selects various options, including date, activity type, receiving bank account, etc. and selects ‘Generate Activity’<br>4. System processes request<br>5. System sends data to external Reporting System and displays returned report<br>6. After review, Treasury analyst selects ‘Transmit to banking software’<br>7. System formats data and transmits to banking software (for reconciliation services offered by banking software) |
| **Extensions**                 | <ins>Treasury Analyst Does Not Completely Fill Out Selections</ins><br>3.a. System informs Treasury Analyst of missing fields and highlights them.<br>3.b. Treasury Analyst enters missing fields and selects ‘Generate’<br>*Scenario continues with step 4*<br><br><ins>Treasury Analyst Finds Errors When Reviewing data</ins><br>5.a. Treasury Analyst cancels out of process<br>5.b. Treasury Analyst informs Loan Analyst of errors<br>*System does not continue main success scenario*<br><br><ins>System Cannot Connect to Reporting System</ins><br>4.a. System informs Treasury Analyst that it cannot generate report<br>4.b. Sends Error Message to System Administrator<br>*System does not continue main success scenario*<br><br><ins>System Cannot Connect to Banking System</ins><br>6.a. System informs Treasury Analyst that it cannot send cash flows to bank<br>6.b. Sends Error Message to System Administrator<br>*System does not continue main success scenario* |
| **Special Requirements**       | Refer to Non-functional Requirement Section of document for any related non-functional requirements. |


**SSD for Use Case 5**

![SSD5](/Images/SSD-Generate_Cash_Flow_Data.png)

## Use Case Diagram Introduction

Finally, the use case diagram was generated from the interactions elaborated in the above use cases and SSDs.  The use case diagrams show the primary actors interacting with the System via the use cases and the involvement of secondary actors.  Note that only the use cases below were included in the use case diagram.  While many more use cases would exist, their inclusion would be outside of the scope of this document. 

## Use Case Diagram

![Use Case Diagram](/Images/Use_Case_Diagram.png)


# UML Domain Model

## Introduction

## Diagram

![Domain Model](/Images/Domain_Model.png)
Please click on above image for a full size version

# UML Class Diagram

## Introduction

## Diagram

![Class Diagram](/Images/Design_Class_Diagram.png)
Please click on above image for a full size version

# Sequence Diagrams

## Introduction

## Diagrams

### Sequence Diagram 1

![SD Acc Report](/Images/Sequence_Diagrams-AccReport.png)
Please click on above image for a full size version

#### Sequence Diagram 2

![SD Dashboard](/Images/Sequence_Diagrams-Dashboard.png)
Please click on above image for a full size version

### Sequence Diagram 3

![SD Invoice](/Images/Sequence_Diagrams-Invoice.png)
Please click on above image for a full size version

### Sequence Diagram 4

![SD Treasry](/Images/Sequence_Diagrams-Treasury.png)
Please click on above image for a full size version

# State Diagram

## Introduction

## Diagram

![State Diagram](/Images/State_Machine.png)
Please click on above image for a full size version

# Swimlane Diagram

## Introduction

## Diagram

![State Diagram](/Images/Swimlane.png)
Please click on above image for a full size version

# UML Component Diagram

## Introduction

## Diagram

![Component Diagram](/Images/Component.png)
Please click on above image for a full size version

# Cloud Deployment Diagram

## Introduction

## Diagram

![Cloud Deployment Diagram](/Images/AWS_Deployment.png)
Please click on above image for a full size version

# Skeleton Classes and Tables Definition

## Skeleton Classes

### Introduction

### Exhibits

## Database Tables

### Introduction

### Exhibits

# Design Patterns

# References
Larman, C. (2005). *Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development* (3rd ed.). Pearson.






