# HireMe User Guide

## Welcome to HireMe!

Thank you for choosing **HireMe** to simplify your internship journey.
With so many positions to apply for, managing applications becomes a real challenge, on top of classes, projects, and exams.
**HireMe** is here to keep you organised and focused on landing your dream internship.
In this guide, new users will find everything you need to get started while experienced users can user the [command summary](#command-summary) as a quick reference. Let's dive in and make your internship search a little easier!

*Use HireMe to get hired now!*
## Overview

HireMe is a **free desktop application that helps you manage your extensive list of internship applications.**

* **Easy to use**
  - Type simple commands: If you can type fast, HireMe can get your internship tracking tasks done faster than traditional apps.
* **High quality**
  - User friendly interface: sleek list design to view all of your of internship applications.
* **Streamlined management**
  - Add, delete and update your internship applications: everything you need to manage your list of internship applications.
* **Detailed Insights**
  - Tailored summary of your list of internship applications so that you can gain hidden insights.

<!-- TOC -->
* [HireMe User Guide](#hireme-user-guide)
  * [Quick start](#quick-start)
  * [Features](#features)
    * [Viewing help : `/help`](#viewing-help-help)
    * [Adding an internship application: `/add`](#adding-an-internship-application-add)
    * [Listing all internship applications : `/list`](#listing-all-internship-applications-list)
    * [Deleting an internship application : `/delete`](#deleting-an-internship-application-delete)
    * [Finding applications by company name: `/find`](#finding-applications-by-company-name-find)
    * [Updating the status of an internship application : `/accept`, `/pending`, `/reject`](#updating-the-status-of-an-internship-application-accept-pending-reject)
    * [Filtering internship applications by status: `/filter`](#filtering-internship-applications-by-status-filter)
    * [Sorting internship applications: `/sort`](#sorting-internship-applications-sort)
    * [Clearing all entries : `/clear`](#clearing-all-entries-clear)
    * [Viewing status chart: `/chart`](#viewing-status-chart-chart)
    * [Exiting the program : `/exit`](#exiting-the-program-exit)
    * [Saving the data](#saving-the-data)
  * [FAQ](#faq)
  * [Command summary](#command-summary)
<!-- TOC -->

--------------------------------------------------------------------------------------------------------------------

## Overview of target users

If you are a Computer Science undergraduate who **does not have much time**, can **type fast** and want to **better manage your internship applications**,
HireMe is the perfect solution for you! Sometimes, tracking internship applications can be troublesome and time-consuming. 
We understand the importance of tracking as it allows you to follow up on your applications. Hence, we came up with HireMe to help you
**stay organised without having to put in much effort** because we know that you may be busy with your coursework. Let's get you
started with HireMe!


--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed on your computer.

2. Download the latest `.jar` file from [here](https://github.com/AY2425S1-CS2103T-W09-3/tp/releases/tag/v1.5).

3. Copy the file to the folder you want to use as the _home folder_ for your HireMe.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hireme.jar` command to run the application.<br>

    <br>A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. (e.g. typing **`/help`** and pressing Enter will open the help window.)<br>
   Some example commands you can try:

    * `/list` : Lists all your internship applications.

    * `/add n/Google r/Software Engineer Intern e/google@gmail.com d/16/09/24` : Adds an internship application at Google to HireMe.

    * `/delete 1` : Deletes the 1st internship application shown in the current list.

    * `/clear` : Deletes all your internship applications.

    * `/chart` : Shows a pie chart with your applications' statistics.

    * `/exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME r/ROLE`, `r/ROLE n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `/help`, `/list`, `/exit`, `/clear` and `/chart`) are not permitted.<br>
  e.g. if the command specifies `/help 123` instead of `/help`, an "Invalid command format!" error message will be shown.

* Command names must be in **lower case only**.<br>
  e.g. `/list` is acceptable but `/List` and `/LIST` are not.

</box>

### Viewing help : `/help`

Shows a message explaining how you can access the help page.

![help message](images/helpMessage.png)

Format: `/help`

<br>

### Adding an internship application: `/add`

Adds your internship application to HireMe with required details such as company's name, internship role, email and date of application.

Format: `/add n/COMPANY_NAME r/ROLE e/EMAIL d/DATE`

* `NAME` must be alphanumeric but these special characters `_`,`&`,`/`,`.`,`:`,`(`, and `)` can also be used.
* `ROLE` must be alphanumeric but these special characters `/` can also be used.
* `EMAIL` must be a valid email address.
* The `DATE` must be within the year 2000 and the current year. It also cannot be a future date which means that the date used must be before today or today's date.
* It must be in the format `dd/mm/yy`, and each field must be double digits (e.g. 01 is valid while 1 is not)

Examples:
* `/add r/Data Scientist Intern n/Facebook e/fb@example.com d/21/10/24`
* `/add n/Google r/Software Engineer Intern e/google@gmail.com d/01/01/24`

<p>Before and after images of the second example</p>
<div style="display: flex;">
  <img src="images/add-before.png" width="500" alt="Add-Before" style="margin-right: 20px;">
  <img src="images/add-after.png" width="500" alt="Add-After">
</div>

<br>

### Listing all internship applications : `/list`

Shows a list of all of your internship applications in HireMe.

Format: `/list`

<br>

### Deleting an internship application : `/delete`

Deletes the specified internship application from HireMe.

Format: `/delete INDEX`

* Deletes the internship application at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed internship application list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` **must be a valid index number** (e.g. 5 is not valid when there is less than 5 applications).

Examples:
* `/list` followed by `/delete 2` deletes the 2nd application in the list.
* `/find Google` followed by `/delete 1` deletes the 1st application in the results of the `/find` command.
* `/list` followed by `/delete 4` when you only have 3 internship applications returns `The internship application index provided is invalid`.

<p>Before and after images of the first example</p>
<div style="display: flex;">
  <img src="images/delete-before.png" width="500" alt="Delete-Before" style="margin-right: 20px;">
  <img src="images/delete-after.png" width="500" alt="Delete-After">
</div>

<br>

### Finding applications by company name: `/find`

Finds all of your applications whose company names contains words that starts with the specified characters.

Format: `/find KEYWORD [ADDITIONAL_KEYWORDS]`

* The search is **case-insensitive**. e.g. `goo` will match `Google`.
* The search is **only for company names**.
* The search uses **prefix search**.
* The search allows for **more than 1 `KEYWORD`**
* If you search for `go`, then you will see a list of all the internship applications that you have applied for
  whose companies have words starting with `go` in their names.

Examples:
* `/find Goo` returns `Google` and `Google 2`
* `/find face` returns `Facebook`
* `/find app` returns `Apple`, but not `CashApp`
* `/find young` returns `Ernest & Young`
* `/find amaZoN NetFlix` returns `Amazon` and `Netflix`

<p>Before and after images of the last example</p>
<div style="display: flex;">
  <img src="images/find-before.png" width="500" alt="Find-Before" style="margin-right: 20px;">
  <img src="images/find-after.png" width="500" alt="Find-After">
</div>

<br>

### Updating the status of an internship application : `/accept`, `/pending`, `/reject`

Updates the status of the specified internship application to `ACCEPTED`, `PENDING`, or `REJECTED`.

Formats:
- `/accept INDEX`: Changes the status to `ACCEPTED`.
- `/pending INDEX`: Changes the status to `PENDING`.
- `/reject INDEX`: Changes the status to `REJECTED`.

* The `INDEX` refers to the index number shown in the displayed internship application list.
* The `INDEX` **must be a positive integer** (e.g. 1, 2, 3, …​)
* The `INDEX` **must be a valid index number** (e.g. 5 is not valid when there is less than 5 applications)

Examples:
* `/list` followed by `/accept 2` marks the status of the 2nd application in the list as accepted.
* `/pending 3` changes the status of the 3rd application in the current list to pending.
* `/reject 1` changes the status of the 1st application in the current list to rejected.
* `/filter pending` followed by `/accept 1` changes the status of the 1st application to accepted. The application will hence no longer be on the list shown on the application since it no longer has a `pending` status.
* `/list` followed by `/accept 4` when you only have 3 internship applications returns `The internship application index provided is invalid`.

<p>Before and after images of the first example</p>
<div style="display: flex;">
  <img src="images/accept-status-before.png" width="500" alt="Accept-Status-Before" style="margin-right: 20px;">
  <img src="images/accept-status-after.png" width="500" alt="Accept-Status-After">
</div>

<br>

### Filtering internship applications by status: `/filter`

Filters all of your existing internship applications with specified status.

Format: `/filter STATUS`

* Valid statuses are `pending`, `accepted` or `rejected` only.
* The status is case-insensitive. (e.g. `"pending"`, `"PenDiNg"` and `"PENDING"` are all allowed.)

Examples:
* `/filter pending` displays all the internship applications that have a status of pending.
* `/filter accepted` displays all the internship applications that have a status of accepted.
* `/filter rejected` displays all the internship applications that have a status of rejected.

<p>Before and after images of the second example</p>
<div style="display: flex;">
  <img src="images/filter-before.png" width="500" alt="Filter-Before" style="margin-right: 20px;">
  <img src="images/filter-after.png" width="500" alt="Filter-After">
</div>

<br>

### Sorting internship applications: `/sort`

Sorts your internship applications in **ascending / descending** order based on the date of application.

Format: `/sort ORDER`

* There are only 2 orders that are valid: `earliest` or `latest` only.
* The order is case-insensitive. (e.g. `"earliest"`, `"eaRLiEsT"` and `"EARLIEST"` are all allowed.)
* Once you sort the list, even if you exit the application and reopen it, the list remains sorted unless you add more internship applications.

Examples:
* `/sort earliest` displays the internship application list sorted according to the earliest applications first.
* `/sort latest` displays the internship application list sorted according to the latest applications first.

<p>Before and after images of the first example</p>
<div style="display: flex;">
  <img src="images/sort-before.png" width="500" alt="Sort-Before" style="margin-right: 20px;">
  <img src="images/sort-after.png" width="500" alt="Sort-After">
</div>

<br>

### Clearing all entries : `/clear`

Clears all of your internship application entries from the HireMe application.

<box type="warning" seamless>

**Caution:**
This removes all the internship applications that you have been tracking. You will not be able to revert and get back your saved data once this command is executed.
</box>

Format: `/clear`

<br>

### Viewing status chart: `/chart`

<img src="images/piechart.png" alt="drawing" width="500"/>

Opens a new window that displays a pie chart with summary data of the statuses of all of your internship applications.

Format: `/chart`

<br>

### Exiting the program : `/exit`

Exits the HireMe application.

Format: `/exit`

<br>

### Saving the data

HireMe data are saved in the hard disk automatically after any command that changes the data. There is no need for you to save manually.

<br>

--------------------------------------------------------------------------------------------------------------------

## FAQ
#### Q1: Where is the data for the application stored?
Ans: HireMe data are saved automatically as a JSON file `[JAR file location]/data/hireme.json`. You can make a backup of the file if you wish to.

#### Q2: Can I edit the data file directly?
Ans: You are strongly encouraged to **not** edit the JSON data file directly. You can use the commands as mentioned above to augment any data.
Should the changes made to the data file causes the format to be invalid, HireMe will discard all data and start with an empty data file.
It is highly recommended to make a copy of the date file before editing it. <br>

Users should only edit the data file only if they are confident in updating it correctly.

#### Q3: What is considered valid, when editing the JSON data file directly?
Ans: Data entries are valid, if they are achievable through a sequence of commands. <br>

Examples: <br>
Having `30/02/24` is an invalid `dateString` field for an application, as there is no sequence of commands that will lead to an application having the respective date.<br>

Having `REJECTED` is a valid `statusString` field for an application, as the user can `/add` an internship and update the status via `/reject`.

#### Q4: Can I find followed by filter to filter out the applications with a specific keyword?
Ans: Currently, `/find` and `/filter` do not stack. These commands will find or filter based on all of your internship application entries.

#### Q5: After I sort my internship applications, will a new entry be added in the right order automatically?
Ans: New entries will not be inserted in the right order. They are inserted to the bottom of the list.

#### Q6: Will adding / deleting new entries or changing the statuses of existing entries be reflected in the pie chart?
Ans: Yes! You are not required to close the window. Any changes made will be reflected immediately in the pie chart.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Help**   | `/help`
**Add**    | `/add n/COMPANY_NAME r/ROLE e/EMAIL d/DATE` <br> e.g., `/add n/Google r/Software Engineer Intern e/google@gmail.com d/16/09/24`
**List**   | `/list`
**Delete** | `/delete INDEX`<br> e.g., `/delete 3`
**Find**   | `/find KEYWORD [MORE_KEYWORDS]`<br> e.g., `/find Google Facebook`
**Accept** | `/accept INDEX`<br> e.g., `/accept 2`
**Pending**| `/pending INDEX`<br> e.g., `/pending 3`
**Reject** | `/reject INDEX`<br> e.g., `/reject 1`
**Filter** | `/filter STATUS`<br> e.g., `/filter pending`
**Sort**   | `/sort ORDER`<br> e.g., `/sort earliest`
**Clear**  | `/clear`
**Chart**  | `/chart`
**Exit**   | `/exit`
