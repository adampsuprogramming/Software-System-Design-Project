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

## Use Case Introduction

In deciding which use cases are most relevant to the design of the System, potential use cases were evaluated and a selection of those which are critical to the desired business functionality, and which will have a substantial impact on design decisions were chosen (Larman, 2005).  These use cases were also chosen because they involve primary user types of the System (the five primary actors depicted in the previous section).  By sampling functionality from each of those five actors, the major components and much of the primary desired functionality of the System were captured and documented.  Note that only a sampling of use case extensions was captured in the documentation of these use cases, due to the scope of this document.  

## Use Cases

## System Sequence Diagram Introduction

The use cases listed were further illustrated through the documentation of System Sequence Diagrams (SSDs), which show interactions and treat the System as a black box.  These SSDs are useful in determining the inflows and outflows between the primary actors, the System, and any secondary actors that might be involved in the chosen use cases.  They are also helpful in developing Sequence Diagrams (SDs).

## System Sequence Diagrams

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






