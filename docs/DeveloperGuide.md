---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# HireMe Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `/delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `InternshipApplicationListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `InternshipApplication` object residing in the `Model`.

The `HelpWindow` component is shown when you execute a help command. It contains a link to the detailed user and developer guide on this HireMe documentation website.

The `ChartWindow` component is shown when you execute a chart command. It contains a visual representation of the various statuses of your internship applications, in the form of a pie chart.

### Logic component

**API** : [`Logic.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline continues till the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a person).<br>
   Note that although this is shown as a single step in the diagram above (for simplicity), in the code it can take several interactions (between the command object and the `Model`) to achieve.
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component:

- **Stores HireMe application data**, which includes all `InternshipApplication` objects. These objects are stored in a UniqueList, ensuring that each application is unique.
- **Manages a filtered list** of currently 'selected' `InternshipApplication` objects (e.g., search results) as a separate, _filtered_ list. This filtered list is exposed as an unmodifiable `ObservableList<InternshipApplication>`, allowing the UI to automatically reflect any changes in the data, as the list is observable.
- **Stores a `UserPrefs` object**, representing the user’s preferences. This object is exposed externally as a `ReadOnlyUserPrefs`, ensuring that the preferences can be accessed but not modified directly.
- **Is self-contained**, meaning that the `Model` does not depend on any other external components. As it represents the core domain entities, it maintains logical independence to ensure modularity and encapsulation.


### Storage component

**API** : [`Storage.java`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.hireme.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Create new internship application
The implementation of the create command follows the convention of a normal command, where `AddressBookParser` is responsible for parsing the user input string into an executable command.
In this case, `AddressBookParser` creates `AddCommandParser` to parse user input string.

<puml src="diagrams/AddSequenceDiagram.puml" alt="AddSequenceDiagram" />

`AddressBookParser` first obtains the values corresponding to the prefixes `c/`, `r/`, `e/` and `d/`.
`AddressBookParser` ensures that:
- All values corresponding to the prefixes are valid
  If any of the above constraints are violated, `AddressBookParser` throws a ParseException. Otherwise, it creates a new instance of `AddCommand` that corresponds to the user input.
`AddCommand` comprises of the internship application to be added, which is an instance of `InternshipApplication`.

Upon execution, `AddCommand` first queries the supplied model if it contains a duplicate internship application. If no duplicate internship application exists, `AddCommand` then calls on `model::addItem` to add the internship application into the data.


### Find internship applications
The implementation of the find command follows the convention of a normal command, where `AddressBookParser` is responsible for parsing the user input string into an executable command.
In this case, `AddressBookParser` creates `FindCommandParser` to parse user input string.

<puml src="diagrams/FindSequenceDiagram.puml" alt="FindSequenceDiagram" />

`AddressBookParser` first obtains the keyword from the user's input.
`AddressBookParser` ensures that there is at least 1 keyword found. If there is no keyword found, `AddressBookParser` throws a ParseException.
Otherwise, it creates a new instance of `FindCommand` that corresponds to the user input.
  `FindCommand` comprises of a `NameContainsKeywordsPredicate`.

Upon execution, `FindCommand` calls on `model::updateFilteredList` which in turns calls on `filteredList::setPredicate`.
`setPredicate` updates the `filteredList` in `model` to contain all the internship applications that contain the keyword


### Filter internship applications

The implementation of the filter command follows the convention of a normal command, where `AddressBookParser` is responsible for parsing the user input string into an executable command.
`AddressBookParser` creates `FilterCommandParser` to parse user input string.

<puml src="diagrams/FilterSequenceDiagram.puml" alt="FilterSequenceDiagram"/>

Refer to AddSequenceDiagram for how `AddressBookParser` creates FilterCommandParser and FilterCommand.
`FilterCommandParser` obtains the specified status value and ensures that it is valid.

A new filter command is then created with a `StatusPredicate`.

Upon execution, `FilterCommand` passes the instance of `StatusPredicate` to the model through the method `model::updateFilteredList`. The model then uses the predicate internally to update the displayed list of internship applications.


### Delete an internship application
The implementation of the command follows the convention of a normal command, where `AddressBookParser` is responsible for parsing the user input string into an executable command.
In this case, `AddressBookParser` creates `DeleteCommandParser` to parse user input string.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="DeleteSequenceDiagram" />

`AddressBookParser` first obtains the index from the user's input.
`AddressBookParser` ensures that there is only 1 keyword found which is a number. If there is no valid keyword found, `AddressBookParser` throws a ParseException.
Otherwise, it creates a new instance of `DeleteCommand` that corresponds to the user input.
`DeleteCommand` comprises of a targetIndex which is the zero based index number of the internship application to be deleted.

Upon execution, `DeleteCommand` gets the internship application to be deleted and calls on `model::deleteItem` which deletes it from the list.



### Sort internship application list
The implementation of the command follows the convention of a normal command, where `AddressBookParser` is responsible for parsing the user input string into an executable command.
In this case, `AddressBookParser` creates `SortCommandParser` to parse user input string.

<puml src="diagrams/SortSequenceDiagram.puml" alt="SortSequenceDiagram" />

`AddressBookParser` first obtains the order from the user's input.
`AddressBookParser` ensures that there is only 1 keyword found which is the sorting order. If there is no valid keyword found, `AddressBookParser` throws a ParseException.
Otherwise, it creates a new instance of `SortCommand` that corresponds to the user input.
`SortCommand` comprises of a DateComparator which contains the sorting order, according to date of application, that the internship application list should be sorted by.

Upon execution, `SortCommand` calls on `model::sortFilteredList` which in turns calls on `addressBook::sortItems`.
`sortItems` updates the `filteredList` in `model` to sort the internship applications in the list according to the order specified by the user.


### Update the Status of an Internship Application
The `StatusCommand` updates the status of an internship application to `PENDING`, `ACCEPTED`, or `REJECTED`, triggered by commands `/pending`, `/accept`, or `/reject` respectively. `AddressBookParser` parses the command input, creating a `StatusCommandParser` to interpret the request.

<puml src="diagrams/StatusSequenceDiagram.puml" alt="StatusSequenceDiagram" />

The sequence diagram above illustrates the flow for the `/accept` command. Similar flows apply for `/reject` and `/pending`.

`AddressBookParser`:
1. Parses the command (e.g., `/accept 1`) and creates a `StatusCommandParser`.
2. `StatusCommandParser` extracts the index and maps the command to the appropriate `Status` enum value (`PENDING`, `ACCEPTED`, or `REJECTED`). If either the index or status is invalid, a `ParseException` is thrown.
3. If parsing succeeds, a `StatusCommand` is created with `targetIndex` and the specified `Status`.

Upon execution, `StatusCommand`:
1. **Retrieves and Validates** the internship application by calling `model::getFilteredList`. If `targetIndex` is invalid, it throws a `CommandException`.
2. **Updates the Status**: It creates a deep copy of the application, sets the new `Status`, and saves the updated application back with `model::setItem`.
3. **Refreshes the Filtered List**: The previous filter predicate is reapplied using `model::updateFilteredList`.

Finally, `StatusCommand` generates a `CommandResult` with a confirmation message, reflecting the updated status. This is then returned to `LogicManager`, completing the command execution.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* studies at School of Computing in NUS
* is constantly applying for internships
* has a need to keep track of significant number of internships
* can type fast
* prefers typing to mouse interaction
* is reasonably comfortable using CLI apps

**Value proposition**: manage internships faster than a typical mouse/GUI driven app


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                       | I want to …​                                 | So that …​                                                           |
|----------|-------------------------------|----------------------------------------------|----------------------------------------------------------------------|
| `* * *`  | CS Undergraduate              | List all the internship applications         | I can view all my past applications                                  |
| `* * *`  | An efficient CS Undergraduate | Type the commands                            | I do not have to lift my fingers off the keyboard                    |
| `* * *`  | CS Undergraduate              | Add an internship application                | I can add on to the records of all the internships I have applied to |
| `* * *`  | CS Undergraduate              | Delete an internship application             | I can remove irrelevant applications                                 |
| `* *`    | CS Undergraduate              | Save the internship application data locally | I will not lose my data when I exit the application                  |
| `* *`    | CS Undergraduate              | Load the internship from a saved file        | I can get back my data when I open the application                   |

*{More to be added}*

### Use cases

**Use Case: Add a new internship entry**

**MSS**

1. The user requests to add a new internship entry.
2. HireMe creates a new entry.

   Use case ends.

**Extensions**

* 1a. The user does not include the company name.
    * 1a1. HireMe shows an error message.

      Use case ends.

* 1b. The user does not include the internship role.
    * 1b1. HireMe shows an error message.

      Use case ends.

* 1c. The user does not include the company email.
    * 1c1. HireMe shows an error message.

      Use case ends.

* 1d. The user does not include the date of application.
    * 1d1. HireMe shows an error message.

      Use case ends.

**Use Case: Delete an internship entry**

**MSS**

1. The user requests to delete a particular internship entry.
2. HireMe deletes the entry.

   Use case ends.

**Extensions**

* 1a. The given index is invalid.
    * 1a1. HireMe shows an error message.

      Use case ends.

**Use Case: Load saved internship applications**

**MSS**

1. The user starts the application.
2. HireMe loads the previously saved internship applications.

   Use case ends.

**Extensions**

* 1a. No save file is found.
    * 1a1. HireMe creates a new empty save file.

      Use case ends.

* 1b. A file with an invalid format is found.
    * 1b1. HireMe shows an error message.

      Use case ends.

**Use Case: Auto-save the current state of the internship list**

**MSS**

1. The user performs an action that changes the internship list (e.g., adding, editing, or deleting an entry).
2. The system automatically saves the updated internship list to `HireMe.txt`.
3. The file is saved successfully without displaying a confirmation message.

   Use case ends.

**Extensions**

* 1a. The file cannot be saved due to an error.
    * 1a1. The system shows the error message: "Error! Unable to save file."
    * 1a2. The system retries the auto-save after a short delay.
    * 1a3. If the save operation still fails, the system logs the error and informs the user that changes might not have been saved.

      Use case ends.

**Use Case: List all internship entries**

**MSS**

1. The user requests to list all internship entries.
2. HireMe shows all internship entries.

   Use case ends.

**Extensions**

* 1a. There are no internship entries.
    * 1a1. HireMe shows a message indicating "no entries."

      Use case ends.


*{More to be added}*

### Non-Functional Requirements

1. **Performance**: The application should respond to user actions within **two seconds**.
2. **Scalability**: The application should handle **at least 500 internship applications** without any performance issues (e.g., lag or slowness).
3. **Cross-Platform Compatibility**: The application should run on any operating system that has **Java 17** installed.
4. **User Accessibility**: The system should be usable by a **novice** with no prior experience using a CLI application, without much difficulty.
5. **Project Scheduling**: The project should follow a **weekly delivery schedule**, releasing a set of features every week.
6. **Data Persistence**: The application should ensure that data **persists** after the user closes the application.
7. **Data Integrity**: Upon reopening the application, the **loaded data** should be identical to the **last saved state** and should not be volatile.

*{More to be added}*

# Glossary

- **Application Status**:
    - **PENDING**: The internship application is currently in progress.
    - **REJECTED**: The user has rejected or been rejected from this internship application.
    - **ACCEPTED**: The user has accepted the offer for this internship.

- **Action**: The task carried out by the HireMe application such as Add, Delete, Update entries.

- **Command**: The string the user types into the HireMe application’s command bar to carry out a particular action.

- **Command Bar**: The input bar at the top of the HireMe application which allows users to type in a string command.

- **Company Email**: The email of the company that the user is applying for an internship role at.

- **Company Name**: The name of the company that the user is applying for an internship role at.

- **Role**: The role of the internship the user applied for.


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a person

1. Deleting a person while all persons are being shown

   1. Prerequisites: List all persons using the `list` command. Multiple persons in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No person is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_
