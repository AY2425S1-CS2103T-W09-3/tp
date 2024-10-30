# HireMe User Guide

HireMe is a **desktop app for managing internship applications, optimized for use via a Command Line Interface** (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, HireMe can get your internship tracking tasks done faster than traditional GUI apps.

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed on your computer.

1. Download the latest `.jar` file from [here](https://github.com/AY2425S1-CS2103T-W09-3/tp/releases/tag/v1.5).

1. Copy the file to the folder you want to use as the _home folder_ for your HireMe.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar hireme.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. (e.g. typing **`/help`** and pressing Enter will open the help window.)<br>
   Some example commands you can try:

    * `/list` : Lists all internship applications.

    * `/add n/Google r/Software Engineer Intern e/google@gmail.com d/16/09/24` : Adds an internship application at Google to HireMe.

    * `/delete 1` : Deletes the 1st internship application shown in the current list.

    * `/clear` : Deletes all internship applications.

    * `/exit` : Exits the app.
   
    * `/chart` : Shows an pie chart with your applications statistics.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<box type="info" seamless>

**Notes about the command format:**<br>

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME r/ROLE`, `r/ROLE n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `/help`, `/list`, `/exit`, `/clear` and `/chart`) will be ignored.<br>
  (e.g. if the command specifies `/help 123`, it will be interpreted as `/help`.)

</box>

### Viewing help : `/help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)

Format: `/help`

### Adding an internship application: `/add`

Adds an internship application to HireMe.

Format: `/add n/COMPANY_NAME r/ROLE e/EMAIL d/DATE`

Examples:
* `/add n/Google r/Software Engineer Intern e/google@gmail.com d/16/09/24`
* `/add n/Facebook r/Data Scientist Intern e/fb@example.com d/21/10/24`

### Listing all internship applications : `/list`

Shows a list of all internship applications in HireMe.

Format: `/list`

### Finding applications by company name: `/find`

Finds applications whose company names contains words starting with the given pattern of characters.

Format: `/find PATTERN`

* The search is case-insensitive. e.g. `goo` will match `Google`.
* The searches are made only for company names.
* The search uses prefix search.
* If you search for `oo`, then you will see a list of all the internship applications that you have applied for
  whose companies have words starting with `oo` in their names.

Examples:
* `/find Goo` returns `Google` and `Google 2`
* `/find face` returns `Facebook`
* `/find app` returns `Apple`, but not `CashApp`
* `/find young` returns `Ernest & Young`

### Filtering internship applications by status: `/filter`

Filters internship applications with specified status.

Format: `/filter STATUS`

* Valid statuses are `pending`, `accepted` or `rejected`.
* The status is case-insensitive. (e.g. `"Pending"`, `"pending"` and `"PENDING"` are all allowed.)

Examples:
* `/filter Pending`
* `/filter Accepted`
* `/filter Rejected`

### Changing the status of an internship application : `/accept`, `/pending`, `/reject`

Updates the status of the specified internship application to `ACCEPTED`, `PENDING`, or `REJECTED`.

Formats:
- `/accept INDEX`: Changes the status to `ACCEPTED`.
- `/pending INDEX`: Changes the status to `PENDING`.
- `/reject INDEX`: Changes the status to `REJECTED`.

* The `INDEX` refers to the index number shown in the displayed internship application list.
* The `INDEX` **must be a positive integer** (e.g. 1, 2, 3, …​)
* The `INDEX` **must be a valid index number** (e.g. 5 is not valid when there is less than 5 applications)

Examples:
* `/list` followed by `/accept 2` marks the 2nd application in the list as accepted.
* `/pending 3` changes the 3rd application in the current list to pending.
* `/reject 1` changes the 1st application in the the current list to rejected.
* `/list` followed by `/accept 4` when you only have 3 internship applications returns `The internship application index provided is invalid`.

### Deleting an internship application : `/delete`

Deletes the specified internship application from HireMe.

Format: `/delete INDEX`

* Deletes the internship application at the specified `INDEX`.
* The `INDEX` refers to the index number shown in the displayed internship application list.
* The `INDEX` **must be a positive integer** 1, 2, 3, …​
* The `INDEX` **must be a valid index number** (e.g. 5 is not valid when there is less than 5 applications)

Examples:
* `/list` followed by `/delete 2` deletes the 2nd application in the list.
* `/find Google` followed by `/delete 1` deletes the 1st application in the results of the `/find` command.
* `/list` followed by `/delete 4` when you only have 3 internship applications returns `The internship application index provided is invalid`.

### Clearing all entries : `/clear`

[!WARNING]
Clears all entries from HireMe. This removes all the internship applications that you have been tracking. You will not
be able to revert and get back your saved data once this command is executed.

Format: `/clear`

### Exiting the program : `/exit`

Exits the program.

Format: `/exit`

### Saving the data

HireMe data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

HireMe data are saved automatically as a JSON file `[JAR file location]/data/hireme.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file make its format invalid, HireMe will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause HireMe to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `/add n/COMPANY_NAME r/ROLE e/EMAIL d/DATE` <br> e.g., `/add n/Google r/Software Engineer Intern e/google@gmail.com d/16/09/24`
**Clear**  | `/clear`
**Delete** | `/delete INDEX`<br> e.g., `/delete 3`
**Find**   | `/find KEYWORD [MORE_KEYWORDS]`<br> e.g., `/find Google Facebook`
**List**   | `/list`
**Help**   | `/help`
**Accept** | `/accept INDEX`<br> e.g., `/accept 2`
**Pending**| `/pending INDEX`<br> e.g., `/pending 3`
**Reject** | `/reject INDEX`<br> e.g., `/reject 1`
**Chart**  | `/chart`
