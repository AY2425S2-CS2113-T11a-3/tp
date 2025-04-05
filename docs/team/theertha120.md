# Aravind Theertha - Project Portfolio Page

By: Aravind Theertha (@theertha120)

## Project: InternSprint

InternSprint is your Ultimate Internship Application Assistant. Navigating internship 
applications can be overwhelming - multiple platforms, countless CV versions, and keeping track of application 
stages can quickly become chaotic. InternSprint is here to streamline the entire process, acting as your personal 
assistant for managing and organizing internship applications efficiently. InternSprint is a CLI application used for
internship management. It is written in Java, and has about 8 kLoC.

### Summary of Contributions

## Code Contributed

Here is the Code I have contributed:
[Code Contributed](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=nmarwah7&tabRepo=AY2425S2-CS2113-T11a-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)


### Enhancements implemented:
1. **New Feature**: Added the ability delete added internships using index parameter

   * **What it does**: Allows the user to delete saved internships: the user just needs the parameter of 
   index to delete the internship form the list of internships

   * **Highlights**: The index-based approach was used furthered in other commands like edit, enhancing 
   consistency in the application.

2. **New Feature**: Added the ability to view usage for all the existing commands

   * **What it does**: Allows the user to view the usage of all the available commands in InternSprint, including 
   format to use the command and parameters needed

   * **Highlights**: This feature enhances user accessibility by providing a quick reference for all available 
   commands, their formats, and required parameters. It utilizes a LinkedHashMap to record all the information for the commands.

3. **New Feature**: Added the feature to save and load interview, project and user profile data.

   * **What it does**: Allows the user to view the information they have entered for projects, interviews and profile 
   in the respective txt files.

   * **Highlights**: This feature required implementing a Storage interface to standardize saving and loading 
   mechanisms, along with a StorageManager to coordinate data persistence across different components.

4. **Additional Enhancement**: Introduced a categorized project structure (software, hardware, and general) 
from which the add and view projects commands are built.

   * **What it does**: Establishes a base class (Project) with core parameters: projectName, role, objectives, 
   description, and duration, along with additional parameters for each project type.


**Contributions to team-based tasks :**
* Created `DeleteCommand` and `HelpCommand` classes from `Command` and `CommandResult` classes.
* Created `StorageManager`, `InterviewStorageHandler`, `ProjectStorageHandler`, `ProfileStorageHandler` from the 
`Storage` interface to handle storage of different kinds of data.
* Created `Project`, `ProjectList`, `HardwareProject`, `SoftwareProject`, `GeneralProject` using which the 
project commands where built.

**Testing**
* Delete Command 
* Help Command 
* View Projects Commands (General, Hardware, Software) 

**Contributions to Documentation:**
* **User Guide**
    * Delete Command Feature
    * Help Command Feature
    * Introduction
* **Developer Guide**
    * Storage Component 
    * Delete an Internship

{this is only first draft, to be edited...}