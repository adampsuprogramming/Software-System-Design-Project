# Software System Design for TBD

## A Commercial Mortgage Loan Accounting System

### Author: Adam Slager

#### Date: March 1, 2025

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
## Non-Functional Requirements

### Performance Requirements

The performance requirements of the System align with Management’s business goals (described above) to improve process efficiency. The table below outlines these NFRs (*Chisel Labs, 2023*; *GeeksforGeeks, 2025*; *Sree, 2020*).

| **NFR Type**                        | **NFR Description**                                                                                                                                       |
|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Response Time**                   | 95% of user requests must be processed and returned in under 1 second                                                                                     |
| **Scalability**                     | 100 concurrent users must be supported without any noticeable performance degradation (this is equal to the maximum concurrent users expected for the System plus a buffer as per Management). |
| **Throughput / Transactions Per Second** | The System shall process a minimum of 50 transactions per second                                                                                           |
| **Latency**                         | Round Trip Time (RTT) shall not exceed 100ms for 95 percent of transactions                                                                                 |

---

### Security Requirements

Due to the sensitive banking data and PII stored and accessed via the System, Security is a top NFR concern of Management. The table below outlines these NFRs (*Dilmegani & Alp, 2025*; *Sree, 2020*).

| **NFR Type**              | **NFR Description**                                                                                                                                                                                                                                             |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Authentication**        | The System shall incorporate MFA authentication to ensure that a user’s identity is verified. <br><br>The System shall meet or exceed ISO 27001 standards for password policies.                                                                              |
| **Authorization**         | System shall implement a RBAC solution to control access to sensitive or privileged data                                                                                                                                |
| **Data at Rest Encryption** | AES-256 or greater                                                                                                                                                                                                                                                  |
| **Data in Transit Encryption** | TLS 1.2 or greater                                                                                                                                                                                                                                                  |
| **Firewall**              | Web application firewall required that meets or exceeds all GDPRS and ISO 27001 standards                                                                                                                                |
| **GDPRS Requirements**    | System shall meet or exceed all GDPRS requirements                                                                                                                                                                                                               |

---

### Maintainability Requirements

Management has emphasized maintainability of the System, due to expected acquisitions and other expanded growth. They have also stipulated the formulation of a detailed testing strategy, due to the critical nature of the System (*Lenovo, n.d.*; *BrowserStack, 2024*; *OpenText, n.d.*)

| **NFR Type**         | **NFR Description**                                                                                                                                                                                                                                                                                                                                                                                                                    |
|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Code Modularity**  | The code base shall be segregated into logical and independent modules or components to facilitate testing and maintainability.                                                                                                                                                                                                                                                                                                           |
| **Documentation**    | The System design and code shall be documented in a clear and concise manner using an online blog format such as GitHub Markdown, in-line documentation, and use cases.<br><br>Code shall be documented within individual code files so that a junior developer can understand the purpose of 95 percent of code within 5 minutes of analyzing it.<br><br>APIs shall be documented via Swagger.           |
| **Testing Strategy** | Unit Test Coverage: At least 80 percent of code base should be covered using Unit Tests (using a framework such as Jest, Jasmine, Junit, etc.)<br><br>System Testing: System testing shall be automated using a framework such as Cypress or Selenium<br><br>CI/CD: Unit and System Testing shall be automated using a CI/CD workflow such as GitHub Actions<br><br>API Testing: Automated using a framework such as Postman<br><br>Logging and Monitoring: Consolidated using a tool such as Splunk<br><br>Performance Testing: The response time of the System under normal and peak loads shall be tested using automated toolsets<br><br>User Acceptance Testing: Based on Use Cases and any user stories and shall be run prior to System go-live. |

---

### Other Relevant Non-Functional Requirements

The following are additional NFRs not covered in the above sections:

| **NFR Type**                         | **NFR Description**                                                                                                                                                                                                  |
|--------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Availability**                     | The System shall maintain 99.9% availability between the hours of 7am EST and 11pm EST on weekdays and 99% availability otherwise (excluding scheduled maintenance windows)                               |
| **Recovery Time Objective (RTO)**    | 4 Hours (System is considered mission critical by Management)                                                                                                                                                      |
| **RPO (Recovery Point Objective)**   | 30 Minutes (Due to the transactional nature of the business, any data loss of greater than 30 minutes is deemed unacceptable by Management)                                                                               |
| **Data Retention**                   | All data must be retained for a minimum of seven years in accordance with the Company’s data retention standards                                                                                                      |



# UML Use Case Diagram

## Use Case and System Sequence Diagrams (SSDs) Introduction

In deciding which use cases are most relevant to the design of the System, potential use cases were evaluated and a selection of those which are critical to the desired business functionality, and which will have a substantial impact on design decisions were chosen *(Larman, 2005)*.  These use cases were also chosen because they involve primary user types of the System (the five primary actors depicted in the previous section).  By sampling functionality from each of those five actors, the major components and much of the primary desired functionality of the System were captured and documented.  Note that only a sampling of use case extensions was captured in the documentation of these use cases, due to the scope of this document.  

The use cases listed were further illustrated through the documentation of SSDs, which show interactions and treat the System as a black box.  These SSDs are useful in determining the inflows and outflows between the primary actors, the System, and any secondary actors that might be involved in the chosen use cases.  They are also helpful in developing Sequence Diagrams (SDs).

## Use Cases and System Sequence Diagrams

### Use Case 1

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

### SSD for Use Case 1

![SSD1](/Images/SSD-Loan_Details.png)

### Use Case 2

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

### SSD for Use Case 2

![SSD2](/Images/SSD-Perform_Acct_Proc.png)

### Use Case 3

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

### SSD for Use Case 3

![SSD3](/Images/SSD-Generate_Cust_Inv.png)

### Use Case 4

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

### SSD for Use Case 4

![SSD4](/Images/SSD-Analyze_Portfolio_Metrics.png)

### Use Case 5

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


### SSD for Use Case 5

![SSD5](/Images/SSD-Generate_Cash_Flow_Data.png)

## Use Case Diagram Introduction

Finally, the use case diagram was generated from the interactions elaborated in the above use cases and SSDs.  The use case diagrams show the primary actors interacting with the System via the use cases and the involvement of secondary actors.  Note that only the use cases below were included in the use case diagram.  While many more use cases would exist, their inclusion would be outside of the scope of this document. 

## Use Case Diagram

![Use Case Diagram](/Images/Use_Case_Diagram.png)


# UML Domain Model

## Conceptual Class Identification

The Domain Model was developed using a brainstorming exercise where a concept category list is used to identify potential conceptual classes for the Domain Model. Conceptual classes should represent real things in the problem domain.

| **Conceptual Class Category**                            | **Conceptual Class Example**                                                                                                                                                                      |
|----------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Physical or Tangible Objects**                        | Collateral                                                                                                                                                                                    |
| **Specifications, Designs or Descriptions of Things**  | Loan Details, Commentary                                                                                                                                                                     |
| **Places**                                             | Collateral, Borrower Business Address                                                                                                                                                        |
| **Transactions**                                       | Loan Closing, Invoice, Loan Payoff, Loan Default                                                                                                                                             |
| **Transaction Line Items**                             | Monthly Interest, Monthly Principal, Principal Remaining, Closing Fees, Ongoing Fees                                                                                                         |
| **Roles of People**                                    | Loan Analyst, Loan Accountant, Billing Analyst, Credit Officer, Treasury Analyst, Customer Contact, Auditor, Regulatory, Management, Legal, User                                             |
| **Containers of Other Things**                         | Invoice, Journal Entry, Dashboard, Portfolio, Cash Flows, Database                                                                                                                           |
| **Things in a Container**                              | Loan (Portfolio), Principal Due (Invoice), Interest Due (Invoice), Journal Entry (Accounting Report), Journal Entry Line Items (Journal Entry), Loan Covenants (Loan Credit Analysis), Cash Flow Line Item |
| **Other Computers/Systems (external)**                 | Reporting System, Corporate Accounting System, Email System, Banking Software                                                                                                                |
| **Abstract Noun Concepts**                             | Interest Rate, Financial Information, Loan Covenants, Dashboard, Commentary, Borrower, Amortization, Loan Details                                                                          |
| **Organizations**                                      | SEC                                                                                                                                                                                          |
| **Events**                                            | Default, Accounting Close, Foreclosure, Loan Closing                                                                                                                                         |
| **Processes**                                         | Banking System Transmission, Accounting System Transmission, Email System Transmission, Reporting System Transmission                                                                       |
| **Rules and Policies**                                | GAAP, Dodd-Frank Act, AML Policies                                                                                                                                                           |
| **Catalogs**                                          | General Ledger Account Catalogue, Consolidation Tree                                                                                                                                         |
| **Records of Finance, Work, Contracts, Legal Matters** | Invoice, Loan Documentation, Commentary, Loan Covenants                                                                                                                                      |
| **Financial Instruments and Services**                | Commercial Mortgage Loan                                                                                                                                                                     |
| **Manuals, Books, Documents, Reference Papers**       | Loan Documentation                                                                                                                                                                           |

## Class Pruning

After possible conceptual classes have been identified, the list is pruned to remove classes that are not appropriate for the domain model.  Below is a brief analysis of those classes that have been kept and those classes that were pruned.  

### Kept Classes

Address (Shortened from Borrower Business Address), Amortization Line Item, Amortization Schedule, Banking Software, Billing Analyst, Borrower, Cash Flow Line Item, Cash Flows, Collateral, Commentary, Corporate Accounting System (renamed to Corp Acc Sys Gateway), Credit Officer, Customer Contact, Dashboard, Database (renamed to Database Access) Email System (renamed to Email Gateway), Financial Information, Invoice, Journal Entry, Journal Entry Line Items, Loan, Loan Accountant, Loan Analyst, Loan Details, Portfolio, Reporting System (Renamed to Reporting Sys Gateway), Treasury Analyst, User

### Pruned Classes

**Irrelevant Classes**

The following classes were pruned because they are not relevant as classes in the System or because they are not Primary or Supporting Actors:
GAAP, Dodd-Frank Act, AML Policies, SEC, Loan Documentation, Consolidation Tree, Auditor, Regulatory, Management, Legal

**Implementation Specific Classes**

The following classes represent processes that will occur and will be captured in the Banking, Reporting, Accounting, and Email Gateway classes:
Banking System Transmission, Accounting System Transmission, Email System Transmission, Reporting System Transmission

**Attributes**

The following concepts (or modifications thereof) are used as attributes in the System:
Interest Rate, Loan Covenants, Default, Accounting Close, Foreclosure, Loan Closing, General Ledger Account Catalogue, Principal Due, Interest Due, Loan Payoff, Principal Remaining, Closing Fees, Ongoing Fees

**Redundant Classes**

The following redundant classes were pruned: 
Monthly Interest (Redundant with Interest Due), Monthly Principal (Redundant with Principal Due), Commercial Mortgage Loan (Redundant with Loan)

## Domain Model Creation
Once the conceptual class list has been pruned, the Domain Model is drawn.  Attributes are listed for the conceptual classes, associations and dependencies are drawn and multiplicity is added on both ends of the associations/dependencies.

## Additional Notes
Note that to keep the Domain Model up to date with the Design Class Model, certain additional classes were added in (specifically the Treasury Report Processor, Journal Entry Processor, Invoice Processor, and Dashboard Manager classes), which are all Pure Fabrications. These classes add value to the Domain Model as they demonstrate how the users interact with the System.  On the other hand, the Simple Factory classes are shown in the Design Class Diagram, but intentionally omitted from Domain Model, as they serve no purpose in the problem domain and are purely implementation classes.  


## Diagram

![Domain Model](/Images/Domain_Model.png)
Please click on above image for a full size version

# UML Class Diagram

## Introduction

The diagram below represents the Design Class Model for the System.  Note that the scope of the Design Class Model is specifically focused on the portions of the Use Cases that were further detailed in the Sequence Diagrams.  The below represents significant design decisions that were utilized (note that many of these design decisions are discussed in greater detail in the [Design Patterns](#design-patterns) section of this document).

Factory Methods – The LoanFactory and JournalEntryFactory classes were introduced to move the responsibility of object creation into a separate class.  You can read more about this design decision in the [Design Patterns](#design-patterns) section.

The DashboardManager class was introduced to handle the functionality associated with generating and updating the dashboard used by the Credit Officers.  It primarily does this by acting as an intermediary between data received from the database (via the DatabaseAccess class) and the external Reporting System (via the ReportSysGateway class).  It is also responsible for interfacing with the EmailSystemGateway class to send loan commentary.  

The JournalEntryProcessor class is assigned the functionality associated with reporting on the journal entries using the external Reporting System (via the ReportSysGateway class) and initiating their transmission to the external Corporate Accounting System via the CorpAcctSystGateway class.

The TreasuryReportProcessor class is responsible for retrieving forecasted cash flows from the database, reporting on them (using the external Reporting class via the ReportSysGateway class), and initiating their transmission to the external banking systems via the BankSoftwareGateway class.

The InvoiceProcessor class is responsible for generating invoices and emailing them to the customer using the external email system via the EmailSystemGateway class.

The above four classes (DashboardManager, JournalEntryProcessor, TreasuryReportProcessor, and InvoiceProcessor) represent Pure Fabrications and Indirections.  You can read more about these design decisions in the [Design Patterns](#design-patterns) section.

Interface Classes: The BankSoftwareGateway, EmailSystemGateway, CorpActSystGateway, and DatabaseAccess classes act as intermediaries between the System and external systems (or in the case of the DatabaseAccess class, the database).  They represent Pure Fabrications and Indirections.  You can read more about these design decisions in the [Design Patterns](#design-patterns) section.


## Diagram

![Class Diagram](/Images/Design_Class_Diagram.png)
Please click on above image for a full size version

# Sequence Diagrams

## Introduction

## Diagrams

### Sequence Diagram 1 - generateReport(List<selections>) from the Perform Accounting Procedures SSD

The below SD details the generateReport(List<selections>) method from the Perform Accounting Procedures SSD.  It demonstrates the internal and external interactions required to generate a report detailing journal entries for a specified period.  The majority of the functionality is orchestrated by the JournalEntryProcessor.  After receiving a request for the report with a list of specifications, the JournalEntryProcessor class utilizes the DatabaseAccess class to interact with the Database and retrieve the required journal entries.  Once the journal entries are retrieved, the JournalEntryProcessor class parses them into the required format and then uses the JournalEntryFactory class to create JournalEntry objects and the associated JournalEntryLineItem objects.  The JournalEntryLineItem objects are added to their respective JournalEntry objects and these objects are added to a list.  The JournalEntryProcessor class then sends the list of JournalEntry objects to the ReportSysGateway, which interacts with the external Reporting System to generate the formatted report of journal entries and return it.

![SD Acc Report](/Images/Sequence_Diagrams-AccReport.png)
Please click on above image for a full size version

### Sequence Diagram 2 - Selection of Methods from the Analyze Portfolio Metrics SSD

The below SD details a selection of methods from the Analyze Portfolio Metrics SSD.   It demonstrates the internal and external interactions required to generate the reporting dashboard utilized by the Credit Officers.  It further details interactions required to email the commentary written by the Credit Officers to the users that they tag.  The majority of the functionality is orchestrated by the DashboardManager class.  After receiving a request to generate a dashboard with a list of specifications, the DashboardManager class utilizes the DatabaseAccess class to interact with the database and retrieve the dashboard data.  When it receives this data, it first parses it into the required format and then uses the LoanFactory class to create the Loan object associated with the requested dashboard.  The DashboardManager class sends the parsed data to the ReportSysGateway class, which interacts with the external Reporting System to generate the formatted dashboard (in a format that the frontend can use to display the Dashboard, such as HTML), and returns it.  

![SD Dashboard](/Images/Sequence_Diagrams-Dashboard.png)
Please click on above image for a full size version

### Sequence Diagram 3 - Selection of Methods from the Generate Customer Invoices SSD

The below SD details a selection of methods from the Generate Customer Invoices SSD.  It demonstrates the internal and external interactions required to generate invoices and email them to the Customers.  The majority of the functionality is orchestrated by the InvoiceProcessor class.  After receiving a request to generate invoices along with a list of selections,  the InvoiceProcessor class utilizes the DatabaseAccess class to interact with the database and the retrieve the appropriate data.  After receiving the data, the InvoiceProcessor class parses it into the required format and then utilizes the LoanFactory class to make a Loan objects (which will be used later in the process).  It then creates Invoice objects for each loan that was selected and adds them to a list.  InvoiceProcessor then returns the list of invoices to the billing analyst.  After the billing analyst reviews the invoices for accuracy, they initiate the finalizeInvoices(message) method. InvoiceProcessor sends the list of invoices, the list of sections, and the message to the EmailSystemGateway class, which formats and transmits the emails to the Customers.  After receiving confirmation that the invoices were sent, InvoiceProcessor then adds the Invoice objects to their respective loans objects and uses the DatabaseAccess class to update the database.  

![SD Invoice](/Images/Sequence_Diagrams-Invoice.png)
Please click on above image for a full size version

### Sequence Diagram 4 - Selection of Methods from the Generate Cash Flow Data SSD

The below SD details a selection of methods from the Generate Cash Flow Data SSD.  It demonstrates the internal and external interactions required to create a report of forecasted cash flow data and send the data to the external Banking Software.  The majority of the functionality is orchestrated by the TreasuryReportProcessor class.  After receiving a request to generate a report of cash flow data along with a list of selections,  the TreasuryReportProcessor class utilizes the DatabaseAccess class to interact with the database and the retrieve the appropriate data.  After receiving the data, the TreasuryReportProcessor class parses it into the required format and sends it to the ReportSysGateway class.  The ReportsSysGateway class then formats it and sends it to the Reporting System which returns a formatted report of forecasted cash flows.  It sends this report to the frontend for the Treasury Analyst to review.  After reviewing, the Treasury Analyst uses the transmitToBank() method in TreasuryReportProcessor to forward the data to the BankSoftwareGateway class.  This class structures the data in the appropriate format and sends it to the external Banking Software, which returns a confirmation.

![SD Treasry](/Images/Sequence_Diagrams-Treasury.png)
Please click on above image for a full size version

# State Diagram

## Introduction

The state diagram below represents the various states that a Loan object can enter over its life in the System.  The states are represented by rounded rectangles and the transitions between states are represented by lines with arrows.  The states of a Loan are important to the System, as loans at different states can have different attributes that become mandatory, different reporting requirements, etc.

Loans are only entered into the System after they are originated and fully funded.  Prior to this, loan opportunities that have not yet closed are tracked in a separate system specifically designed to manage leads and opportunities.  Once a loan has legally closed and funded, it enters the **Performing** state.  As long as the loan remains current with its payments and stays within the confines of its covenants, the loan remains in this state.  Upon full repayment, the Loan will enter the **Repaid** state, which is a final state.

If a Loan breaches a covenant, it will enter the **Covenant Default** state.  At this point, the covenant breach is either amended or waived (as further described in the Swimlane Diagram), thus returning it to **Performing**, or it is foreclosed upon and enters the **Foreclosed** state.

If a Loan misses a payment, it enters the **Delinquent** state. Typically, underlying loan documents have a built-in grace period for missed payments and, if the missed payment(s) are brought current before that grace period expires, the Loan moves back into the **Performing** state.  If the grace period expires prior to payments being made, the loan enters the **Payment Default** state.

A payment default is typically a serious incident and at this point, the lender can either agree upon an amendment that restructures the Loan (thus entering the **Restructured** state), or the lender can foreclose on the loan, thus entering the **Foreclosed** state.  Note that from the **Restructured** state, a loan can enter the **Covenant Default** state or the **Delinquent State**, similar to a loan in the **Performing** state.

From the **Foreclosed** state, the lender will try to exercise any remedies (typically involving monetizing the collateral) to recover its loan.  If the Loan is full recovered (including all principal and interest payments), it moves into the **Recovered** state, which is a final state.  If the loan is not fully recovered (meaning that the exercised remedies did not fully cover the principal and interest owed), the Loan enters the **Written Off** state, which is also a final state.

## State Diagram for Loan Object

![State Diagram](/Images/State_Machine.png)
Please click on above image for a full size version

# Swimlane Diagram

## Introduction

The UML Swimlane Diagram presented below details the steps taken during the compliance process.  In the commercial mortgage industry, borrowers are often required to submit monthly or quarterly compliance reports showing that they are meeting the requirements outlined in the Mortgage documents.  If they do not, the lender is entitled to take certain remedies, up to and including foreclosure on the mortgage.  Typically, the lender would like to avoid foreclosure, if possible, because it is costly and often does not result in a full recovery.  For a small covenant miss, the lender might simply waive the breach (either for free or for a fee).  For a larger breach, the lender might enter into an agreement with the borrower to add collateral to the mortgage or to increase the coupon rate.  In extreme cases and, especially in situations where it looks like a delay could comprise their recovery, the lenders will foreclose on the mortgage.  Note that the situation outlined in the Swimlane diagram below does not include a situation involving a payment default, which is often separate and distinct from a covenant breach.

Note that the Swimlane diagram has been annotated with references that correspond with the situations explained below.  These two explanations follow the two most likely cases: No Issues Noted During Compliance and Issues Noted and Mortgage Amended.  *To avoid overcrowding the diagram, the rarer foreclosure situation was not annotated.*

**No Issues Noted During Compliance**

  A1) The Swimlane Diagram begins with the Customer sending in the required compliance package to the Company.
  
  A2) The Loan Analyst is the first to handle the compliance package and will review and analyze it while entering relevant data into the System (i.e. covenant levels, financial results, etc.).
  
  A3) In a typical situation where there are no issues, the Loan Analyst will finalize the data, and the Credit Officer will review it within a standard timeframe.
  
  A4) The Credit Officer will view it on a dashboard in the System and write commentary, which will then be forwarded to Management.
  
  A5) Management will review the commentary and, if no concerns are noted, the compliance process will be finalized.

**Issues Noted and Mortgage Amended**

  B1) In a situation where there is an issue, the Loan Analyst is typically the first to notice and will notify the Credit Officer immediately after inputting the data into the System.
  
  B2) If the issue is substantial, the Credit Officer will raise it to a higher level and review it with Management.
  
  B3) In the event that Management agrees that the issue is substantive, they will refer the issue to the Legal Department.
  
  B4) Legal will work on formulating a solution and seek approval on it from Management.
  
  B5) After getting approval, the Legal Department will draft the solution.
  
  B6) In the event that the loan is not foreclosed on, the borrower and legal will (usually after several iterations of negotiations and review) agree on a remedy and execute it.
  
  B7) The terms of the remedy are then entered into the System by the Loan Analyst.

## Swimlane Diagram
![Swimlane Diagram](/Images/Swimlane.png)
Please click on above image for a full size version

# UML Component Diagram



### Introduction

The Component Diagram below is a logical representation of the major components, modules, and interfaces of the System. Connectors between components are shown with a solid line and dependencies between components are represented by dotted lines with arrows.  The System has been developed using a standard three-tier architecture of a presentation, application, and data layer.  This architectural approach was chosen because it offers separation of concerns and separate codebases for each of the three-tiers.  This allows independent development of the tiers and independent scalability, which is useful due to the expected growth of the Company *(IBM, n.d.)*. Additionally, since this is a newly developed System, the Canvas module recommends that development starts out using a traditional architecture and then microservices or lambda functionality can be carved out during future refinements.

*Important Note: The Component Diagram includes additional functionality than that which is illustrated on the previous diagrams (i.e. Design Class Diagram) to demonstrate a fully realized system.*

### Frontend

The Frontend is comprised of two conceptual components: The presentation layer and the logic layer.  

<ins>Presentation Layer</ins> - The presentation layer houses those elements that the user is going to directly interact with.  It includes HTML, CSS, JavaScript and any other UI components. 

<ins>Logic Layer</ins>- The logic layer interacts with both the Presentation Layer and with the Backend (via the API).  Its responsibilities include event handling, form validation, state management, etc.  Importantly, it is the conduit that handles any requests arising from user interaction and routes them to the proper component of the backend via the API.

### Backend

The Backend handles the business logic and security of the System.  It also serves as the middle layer between the frontend and the database, handling requests from the frontend and fetching and processing data housed in the database.  Additionally, in interacts with external serves via established interfaces (in this document labeled “gateways” so not to be confused with Java interfaces).  

<ins>Backend API</ins> – The Backend API routes requests from the frontend to the functionality of the backend via REST API calls.  The REST calls are also another step in security framework, as they ensure that only users who are authorized and authenticated can access them (and thereby access the data and functionality in the backend ).  

<ins>Security Component</ins> – Security is critical with the System, as it houses confidential borrower data (potentially include PII), privileged financial information, and banking information.  The following are the subcomponents of the security component and their responsibilities:
-	<ins>Authentication</ins> – Due to the security requirements, instead of using an external service provider such as Facebook or Google, the decision was made to keep authentication within the system.  While external service providers are secure, regulators may prefer an in-system solution
-	<ins>Multi-Factor Authentication</ins> – The decision was made to utilize MFA as another layer of security to ensure that users are who they say they are.  As this is not the primary layer of security and due to the complexity of setting up an MFA system, the decision was made to use an external provider.
-	<ins>Role Based Policy Control</ins> – As different users will have different levels of view and modify access based on their responsibilities, the decision was made to implement a Role Based Policy Control.  This will allow the System Administrator to assign a role to a user that will limit their access to only those areas of the system that they have privileges to access *(GeeksforGeeks, 2024)*.
-	<ins>Encryption/Decryption</ins> – While the cloud provider will provide encryption for both data at rest and in transit, the System will have an internal layer of encryption to encrypt sensitive information (PII, banking data, etc.) before it reaches AWS.  

<ins>Business Logic Components</ins> – Due to the size and complexity of the System it is useful to logically group functionality together.  These groupings are highly cohesive and their interactions with other components are few and well defined. The components are as follows:
-	<ins>Mortgage Tracking</ins> – The Mortgage Tracking component is a grouping of subcomponents that allow the user (primarily the Loan Analyst) to create and update the terms of a Mortgage.  The two subcomponents are *Input and Validation*, which handles all mortgage on-boarding and editing, and *Calculation*, which handles the complex calculation mechanics of mortgage amortization.
-	<ins>Credit</ins> – The Credit component is a grouping of subcomponents that allow those involved in the credit function to utilize the System.  It includes the *Dashboard* subcomponent, a functionality that was previously described, a *Portfolio Reporting* subcomponent, which would be responsible for generating portfolio level reports as opposed to loan level reports, and the *Credit Modification* subcomponent, which would give the Credit team limited modification ability of the loans (primary around internal classification and commentary) via their dashboard.  
-	<ins>Billing and Servicing</ins> – The Billing and Servicing component is a grouping of subcomponents that encapsulates much of the functionality of the Treasury Analyst and Billing Analyst actors.  The two are very closely related, as the *Billing* subsystem is responsible for creating and sending invoices to the Customer and the *Treasury* subsystem is responsible for forecasting the cashflows of the Loans (including from the data on the invoices).  
-	<ins>Accounting</ins> – The Accounting component is a grouping of subcomponents that is responsible for the accounting functionality of the System.  This includes *Journal Entry Generation*, a subcomponent that is responsible for the calculation and organization of Journal Entries; *Financial Reporting*, a subcomponent that is responsible for all accounting level financial reporting for the Loans; and *Forecasting*, a subcomponent that is responsible for generating and displaying accounting income forecast (not to be confused with cash forecasting).
-	<ins>Interfaces</ins> – As many of the submodules interact with external components, interfaces have been established to promote loose coupling and separation of concerns between the subcomponents and the external systems.

### Database

The Database represents the main data store for the system, which will be a RDBMS, due to the highly structured nature of the System.  The Database will be described in more detail in the Cloud Deployment Diagram.  The Database Component interacts with all backend components via an interface to promote loose coupling between the components and the database. 


## Diagram

![Component Diagram](/Images/Component.png)
Please click on above image for a full size version

# Cloud Deployment Diagram

*Note that the explanation below will only highlight those key decisions and will not explain every component of the AWS diagram, due to scope limitations.*

## Inclusion of Certificate Manager
Due to the security requirements of the system, Route 53 integrates with the Certificate Manager to enforce HTTPS traffic between users and the ALB.  Additionally, the Certificate Manager will automatically handle all certificate renewal and automatically integrates with ALB.  

## Frontend on ECS
The frontend will be hosted within ECS instead of S3 due to the dynamic nature of the System and due to the security nonfunctional requirements being emphasized by management.  Much of the content served up to the users will be dynamic (Dashboards, reports, etc.) and S3 is better suited for large public websites that serve significant amounts of static content.  Additionally, the System should only be accessed by users who are authorized, since security is essential.  Therefore,  the decision was made to completely house it within a private subnet in the VPC and in order to better control security.  

## Two Regions and Latency Based Routing (via Route 53)
The Company has offices that are located across the United States, with the majority of operations being on the east and west Coasts.  As performance was a driving nonfunctional requirement of the System, the System will be in two regions, us-east-1 and us-west-2.  Additionally, Route 53 will implement latency-based routing to the region that has the lowest latency for each individual user.

## Web Application Firewall
Due to the emphasis on security, the decision was made to utilize the Amazon Web Application Firewall, which will allow web traffic filtering (which could be useful to prevent access from outside of the Company’s domain) and blocking attacks and bots. *(Amazon Web Services, n.d.-a)*

## Application Load Balancer (ALB)
The ALB is a key component of the System that promotes the nonfunctional requirements of scalability, performance, and availability.  It will automatically route traffic based on its algorithms and volume of traffic to the appropriate docker containers within the ECS instances. *(Amazon Web Services, n.d.-b)*.

## Auto-Scaling with ECS
One of the key components of the AWS deployment structure that helps facility performance and scalability is the autoscaling, which is managed by ECS in conjunction with AWS Auto Scaling.  Based on preferences that can be configured, ECS scales up or down Fargate tasks (running Docker Instances) depending on volume and ECS task utilization thresholds.  The ALB works in conjunction with this autoscaling to automatically ensure that users are being routed to tasks that have sufficient capacity and, when high traffic is detected, ECS works to provision additional Fargate tasks to accommodate the traffic *(Amazon Web Services, n.d.-c)*.

## NAT Gateway
The NAT Gateway allows elements from with private subnets to securely interface with services on the internet without exposing the private subnets to internet traffic.  Instead, it only allows specific outgoing requests and responses back from those services to the outgoing requests.  It is another layer in promoting the emphasis on security with the System.

## AWS Elastic Container Registry
The ECR serves two key functions.  It interfaces with GitHub’s CI/CD mechanism and houses the new Docker images that GitHub pushes to it.  It also acts as the repository for these images so that, when ECS needs to provision a new instance of a Docker Container, it pulls the newest container version directly from the ECR.

## CloudWatch and Splunk Integration
Since all utilized AWS components integrate with AWS CloudWatch’s monitoring capabilities, the decision was made to centralize monitoring with CloudWatch.  CloudWatch will periodically forward its collected data to Splunk, which will serve as the central repository for all logs.  Splunk provides tools, dashboards, and alerts for System monitoring and log management.

## AWS Aurora Database
The decision was made to utilize AWS Aurora Database, as the System’s highly structured data was a good candidate for a RDBMS.  AWS Aurora is fully managed and offers high availability, scaling, and performance *(Amazon Web Services, n.d.-d)*.  Additionally, a primary-replica setup is being used (utilizing the Data Replication design pattern), in which the primary database will continuously replicate any changes to the replica database.  Both regions will read from their individual databases, but all writes will be centralized with the primary database (in us-east-1, where most of the users reside).  In the event of an outage of the primary DB, Aurora will automatically promote the replica database to the new primary.


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

![Entity Relationship Diagram](/Images/Entity_Relationship_Diagram.png)
Please click on above image for a full size version

# Design Patterns

Throughout the design of the System, multiple design patterns have been utilized.  While some of these design patterns were listed in the explanations of the individual diagrams in this document, the below serves to consolidate the explanations into one location.

## Pure Fabrication and Indirection Patterns

In the design of the System, the Pure Fabrication and Indirection GRASP patterns are very closely related.  In the Pure Fabrication Pattern, a class is created that is not a real-world object or concept, often to promote low coupling.  In the Indirection Pattern, a class is introduced as an intermediary between two objects to support low coupling. In the System, classes that were not part of the problem domain were created to act as an intermediary between the system and other external or internal components (these classes are CorpAcctSysGateway, BankSoftwareGateway, EmailSystemGateway, ReportSysGateway, and DatabaseAccess), thus incorporating both patterns. The purpose of these classes is to separate the knowledge about the connection and the API format of the external Systems from the classes that utilize the external systems.  This promotes low coupling, since if the external system ever changes, only these intermediary classes would need to change instead of having to alter each class that uses the external systems.  The JournalEntryProcessor, InvoiceProcessor, and DashboardManager classes also represent Pure Fabrications and Indirections, but their purpose is facilitate interactions between the other internal classes.

## Single Responsibility Principle
The SOLID Single Responsibility Principle states that a class should have only one reason to change *(Martin, 1996)*.  By introducing the intermediary components described above along with other components such as the Simple Factory classes described below, responsibilities have been removed from classes that are unrelated to their core mission, thus allowing them to focus on one responsibility and promote higher cohesion within the classes.  For example, by introducing the LoanFactory class, the InvoiceProcessor class does not need to know how to make Loan objects; instead, it can focus on its responsibilities of invoicing.

## High Cohesion
The Pure Fabrication, Indirection, and Single Responsibility Patterns all facilitate the High Cohesion principle, since they remove unrelated responsibilities from a class, thus allowing that class to focus on its primary responsibilities.

## Low Coupling
The Pure Fabrication and Indirection support the Low Coupling principle, since they introduce an intermediary class with the goal of reducing coupling between two other classes.

## Simple Factory Pattern
While the Simple Factory Pattern is *NOT A GOF PATTERN*, it fits neatly into the design of the System.  The goal of the Simple Factory Pattern is move the logic of object creation to a separate class in order to promote better cohesion amongst the classes that would otherwise create the objects.  So, by introducing the LoanFactory class, neither the DashboardManager nor the InvoiceProcessor classes need to know how to create a Loan object, which might involve complex logic.  Instead, they can call the LoanFactory class, which will do so for them.

## Singleton Pattern
The Singleton GOF pattern ensures that multiple instances of commonly used objects are not created (i.e. only one instance of it exists for all objects to access).  This pattern will be useful when creating those objects such as the Gateway objects described previously that should not be created each time another object wants to use it, thus reducing the memory required for the System and increasing performance.  It also simplifies the System, since creation logic only needs to be called once.  While not explicitly shown in the UML charts due to scope, examples of the implementation of this pattern can be found in the Skeleton classes. 

## Three-Tier Architecture 
While not explicitly detailed in the module, the three-tier architecture of the presentation later, logic layer, and data layer, is a commonly used pattern in System Architecture that helps with development (since each tier can be worked on separately), scalability (since the tiers can scale independently from each other), and separation of concerns *(IBM, n.d.).*

## Data Replication Design Pattern
The Data Replication design pattern is being used (as further explained in the AWS Cloud Deployment section) to replicate the database across two AWS regions.  This pattern was chosen for increased availability in the event of a failure and increased performance, since users will use the replica that that is closest to their location.  It is being used as this data is critical to the operations of the Company.

## Well Architected Framework Pillars
Throughout the design, there has been an emphasis on the Operational Excellence, Security, Reliability, and Performance Efficiency pillars of the AWS Well-Architected Framework.  The initial elicitation and documentation of NFRs helped focus the design on these attributes.  

# References
Larman, C. (2005). *Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development* (3rd ed.). Pearson.

IBM. (n.d.). *Three-tier architecture*. Retrieved February 26, 2025, from https://www.ibm.com/think/topics/three-tier-architecture

GeeksforGeeks. (2024, July 23). *Security in distributed system*. Retrieved February 26, 2025, from https://www.geeksforgeeks.org/security-in-distributed-system/#authentication-mechanisms-in-distributed-system

Martin, R. (1996). *Class design principles*. Object Mentor.

Amazon Web Services. (n.d.-a). *AWS Web Application Firewall (WAF)*. AWS. Retrieved February 27, 2025, from https://aws.amazon.com/waf/

Amazon Web Services. (n.d.-b). *What is an Application Load Balancer? - Elastic Load Balancing*. Retrieved February 27, 2025, from https://docs.aws.amazon.com/elasticloadbalancing/latest/application/introduction.html

Amazon Web Services. (n.d.-c). *Service Auto Scaling for Amazon ECS. AWS Documentation*. Retrieved February 27, 2025, from https://docs.aws.amazon.com/AmazonECS/latest/developerguide/service-auto-scaling.html

Amazon Web Services. (n.d.-d). *Amazon Aurora*. AWS. Retrieved February 27, 2025, from https://aws.amazon.com/rds/aurora/

Chisel Labs. (2023, June 19). *What is non-functional requirements?* Chisel Labs. Retrieved March 1, 2025, from https://chisellabs.com/glossary/what-is-non-functional-requirements/

GeeksforGeeks. (2025, February 1). *Non-functional requirements in software engineering.* Retrieved March 1, 2025, from https://www.geeksforgeeks.org/non-functional-requirements-in-software-engineering/

Dilmegani, C., & Alp, E. (2025, February 22). *Key components of firewall compliance: Guidance in 2025.* AIMultiple. Retrieved March 1, 2025, from https://research.aimultiple.com/firewall-compliance/ 

Sree. (2020, August 16). *Non-functional requirements basics. Technical Program Management.* Retrieved March 1, 2025, from https://technicalprogrammanager.com/non-functional-requirements-basics/

Lenovo. (n.d.). *Modularity: What is it and how does it enhance business productivity?* Retrieved March 1, 2025, from https://www.lenovo.com/us/en/glossary/modularity/

BrowserStack. (2024, September 22). *NFRs: What is Non Functional Requirements (Example & Types)*. https://www.browserstack.com/guide/non-functional-requirements-examples

OpenText. (n.d.). *What is performance testing?* OpenText. Retrieved March 1, 2025, from https://www.opentext.com/what-is/performance-testing




