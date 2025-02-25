# Software System Design for TBD

## A Commercial Mortgage Loan Accounting System

### Author: Adam Slager

#### Date: February 25, 2025

# Table of Contents

# Problem Statement and Requirements

## Business Requirements

### Problem Statement

ABC Corporation (the “Company”) is a Commercial Mortgage Loan (“Loan”) originator and servicer that has been was established in the early 1990’s.  In recent years, the company has grown substantially, entering several new territories and hiring a team of experienced originators.  Origination volume has nearly doubled in both count and dollar amount.  The company has traditionally used a series of spreadsheets and a Microsoft Access Database to keep track of the accounting and servicing of its Loan portfolio.  Recently, several month-end closes were delayed due to errors found in spreadsheet formulas.  Additionally, a major default event for a large Loan was missed, due to a lack of communication between the credit officer and the loan team.  Lastly, KPI’s related to the timeliness of accounting functions have been increasing substantially, as the Loan portfolio has grown. The management team and ownership group (“Management”) have mandated that a new software system be implemented to replace the current spreadsheets and databases.  Their primary goal is to reduce time spent on issues related to poor systems and to instead allocate to value added activities, such as reporting and analysis.  Additionally, Management has requested a single source of truth for multiple constituents to be able to reliably reference, for all aspects of loan accounting and servicing, including certain key metrics and covenants monitored for portfolio management..

### System Functionalities Required

The following are the required System Functionalities.  These were derived from conversations with management and the users, as well as by working on user stories as elaborated below.  In order to begin design, a selection of five use cases were selected, which would involve various components of the system, various actors, and represent System functionality that management and the users have deemed as critical.  

### Use Cases

Use Case #1

# Use Case #1

## Enter Loan Details

| Section                 | Details |
|-------------------------|---------|
| **Scope**               | Commercial Mortgage Loan Accounting System |
| **Goal in Context**     | A Loan Analyst wants to enter details of a Loan into the system following the loan closing. |
| **Level**               | User goal |
| **Primary Actors**      | Loan Analyst |
| **Stakeholders and Interests** | Loan Analyst: Wants to enter and save details of loan post-closing. <br> Loan Accountant: Needs accurate details to generate journal entries. <br> Billing Analyst: Uses information for invoice generation. <br> Treasury Analyst: Uses data for reconciliation and cash forecasting. <br> Credit Officers: Uses data for credit migration analysis. <br> Corporate Accounting System: Interfaces with the system to receive accounting data. <br> Reporting System: Interfaces for reports and analytics. <br> Email System: Sends invoices to customers. <br> Loan Closing Team: Performance is partially measured based on this data. <br> Customers: Bills are generated based on entered details. <br> Corporate Accounting Team: Uses information for financial statements. <br> Management: Uses accurate records for company operations. <br> External Auditors: Use data for financial statement audits. <br> Financial Regulators: Rely on data for financial reporting compliance. |
| **Preconditions**       | Loan has closed and funded. <br> Loan Analyst has legal documents and summary data prepared. <br> Loan Analyst is authenticated. |
| **Main Success Scenario** | 1. Loan Analyst clicks ‘New Loan Boarding’ button. <br> 2. System displays ‘New Loan Boarding’ screen. <br> 3. Loan Analyst enters loan details. <br> 4. Loan Analyst selects ‘Cash Flow’ tab. <br> 5. System displays cash flow screen. <br> 6. Loan Analyst enters details and selects ‘Generate Cash Flow’. <br> 7. System calculates and returns cash flow and amortization schedule. <br> 8. Loan Analyst reviews and clicks ‘Save’. <br> 9. System creates new record. |

# Use Case #2

## Perform Accounting Procedures

| Section                 | Details |
|-------------------------|---------|
| **Scope**               | Commercial Mortgage Loan Accounting System |
| **Goal in Context**     | A Loan Accountant wants to perform period-end accounting procedures. |
| **Level**               | User goal |
| **Primary Actors**      | Loan Accountant |
| **Stakeholders and Interests** | Corporate Accounting System: Interfaces for accounting data. <br> Corporate Accounting Team: Requires timely and accurate data. <br> Management: Uses data for financial decision-making. <br> External Auditors: Ensures data accuracy. <br> Financial Regulators: Ensures compliance. |
| **Preconditions**       | Loan Accountant is authenticated. <br> All transactional data is loaded and reconciled. |
| **Main Success Scenario** | 1. Loan Accountant selects ‘Reporting’ tab. <br> 2. System displays reporting screen. <br> 3. Loan Accountant fills out fields and selects ‘Generate Report’. <br> 4. System processes data and displays the report. <br> 5. Loan Accountant reviews and refines the report. <br> 6. Loan Accountant selects ‘Close Out Period’ tab. <br> 7. System displays Period Close screen. <br> 8. Loan Accountant finalizes period close and transmits data. <br> 9. System locks data and sends it to Corporate Accounting System. |

(*Additional use cases formatted similarly*)

