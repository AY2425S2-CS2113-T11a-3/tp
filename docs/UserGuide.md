# InternSprint User Guide
## Introduction

***InternSprint is your Ultimate Internship Application Assistant!***
<div align="center">
<img src="images/UGImages/welcome_is.png" alt="WelcomeToInternsprintSS" width="100%">
</div>
Navigating internship applications can be overwhelming - multiple platforms, countless CV versions, and keeping track
of application stages can quickly become chaotic. InternSprint is here to streamline the entire process, acting as your
personal assistant for managing and organizing internship applications efficiently.

Designed for **Command Line Interface (CLI)** users, InternSprint is optimized for speed, flexibility, and simplicity.
Whether you're applying through countless online jop application portals, or personal connections, our tool ensures
you stay on top of your applications.

Key features include, logging, monitoring and updating your applications across multiple platforms in one place
and easily tracking status of your applications.

*With InternSprint, you’ll never lose track of an application again!*


## Table of Contents

<!-- TOC -->
* [Quick Start](#quick-start)
* [Features](#features)
    * [Help: `help`](#help-help)
    * [Internship Commands](#internship-commands)
        * [Listing all internships: `list`](#listing-all-internships-list)
        * [Adding a new internship: `add [TYPE]`](#adding-a-new-internship-add-type)
          * [Adding a general category of internship: `add general`](#adding-a-general-category-of-internship-add-general)
          * [Adding a software category of internship: `add software`](#adding-a-software-category-of-internship-add-software)
          * [Adding a hardware category of internship: `add hardware`](#adding-a-hardware-category-of-internship-add-hardware)
        * [Editing an internship: `edit`](#editing-an-internship-edit)
        * [Deleting an internship: `delete`](#deleting-an-internship-delete)
        * [Finding internships: `find`](#finding-internships-find)
        * [Viewing extended description of a specific internship:
          `desc`](#viewing-extended-description-of-a-specific-internship-desc)
        * [Adding interviews for internships: `interview for`](#adding-interviews-for-internships-interview-for)
        * [Sorting all interviews added by date: `sort interviews`](#sorting-all-interviews-added-by-date-sort-interviews)
    * [User Commands](#user-commands)
        * [Updating user profile information: `my`](#updating-user-profile-information-my)
        * [Viewing user profile information: `view user`](#viewing-user-profile-information-view-user)
        * [Adding a general category of project: `project general`](#adding-a-general-category-of-project-project-general)
        * [Adding a software category of project: `project software`](#adding-a-software-category-of-project-project-software)
        * [Adding a hardware category of project: `project hardware`](#adding-a-hardware-category-of-project-project-hardware)
        * [Viewing a general category of project: `view general`](#viewing-a-general-category-of-project-view-general)
        * [Viewing a software category of project: `view software`](#viewing-a-software-category-of-project-view-software)
        * [Viewing a hardware category of project: `view hardware`](#viewing-a-hardware-category-of-project-view-hardware)
    * [Exit `bye`](#exit-bye)
* [Saving the data](#saving-the-data)
* [Editing the data file](#editing-the-data-file)
* [Date and Time Formats](#date-and-time-formats)
* [FAQ](#faq)
* [Command Summary](#command-summary)
<!-- TOC -->

## Quick Start

1. Ensure you have **Java 17** or above installed in your Computer, and it is compatible with your device operating
   system (Mac or Windows).

2. Download the latest `.jar` file from [here](https://github.com/AY2425S2-CS2113-T11a-3/tp/releases) under latest release.

3. Copy the file to the folder you want to use as the home folder for InternSprint.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the  `java -jar InternSprint.jar`
   command to
   run the application.
   InternSprint should appear in your terminal window as shown below.

   > If you are a Mac user, do note that due to Mac Gatekeeper, this .jar file and its home folder cannot be in the
   `Downloads` directory, and will have to be relocated to another
   directory such as `Desktop` to bypass Mac's additional security check,
   hence if you see `Error: unable to access jarfile InternSprint.jar` simply relocate the directory. (You may bypass
   this
   quarantine on your Mac
   but this will require admin privileges and could be potentially be a security risk since anyone can now access this
   file, thus
   the relocation method is best).

    - Note the app will generate a data.txt file in this home folder which
      will be empty when the app is first launched.


5. Type the command in the terminal window and press Enter to execute it. e.g. typing list and pressing Enter will list
   all stored tasks.
   Some example commands you can try:

   `list` : Lists all internships.

   `add general /c Google /r Human Resource /dept HR`  : Adds a general type of internship at Google, of Human
   resources role, under HR department.

   `edit /index 1 /eli Year 2 students` : Adds description of eligibility to internship indexed at 1 in your list.

   `bye` : Exits the app.

## Features

> **Notes about the command format:**
> * Words in `UPPER_CASE` are the parameters to be supplied by the user.
> * Items in square brackets `[...]` are optional.
> * `/` is a reserved character to represent flag prefixes. It should not be used to give the value of any parameter.
>   Use hyphens (`-`) instead.


### Internship Commands

### Help: `help`
Allows users to view the usage of all the available commands, or the usage of a specific command.

Format: `help` OR `help COMMAND`

* `help` is used to view the usage of all commands.
* `help COMMAND` is used to view the usage of the specific command.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> help add general
------------------------------------------------------------------------------------------------------------------------
    -> add general: Adds a general internship to the internship list.
    Parameters: /c COMPANY_NAME /r ROLE /dept DEPARTMENT
    Example: add general /c Google /r Human Resource /dept HR
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Listing all internships: `list`
Adds a new item to the list of todo items.

Format: `list`

* `list` is used to view the list of all added internships.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> list
------------------------------------------------------------------------------------------------------------------------
  Here is your internship list!
    Software Internships:
      1. Company: google, Role: intern, Tech: c++
    Hardware Internships:
    General Internships:
      2. Company: UBS, Role: IT Intern, Dept: IT
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---
<div style="page-break-after: always;"></div>
### Adding a new internship: `add [TYPE]`

Internships are categorized into 3 types: `general`, `software` and `hardware`.

They have multiple parameters that can be set, and some of them are optional.
* The `COMPANY_NAME`, `ROLE` and `DEPARTMENT/TECHNOLOGIES` must be unique to the list of internships and are mandatory
  parameters. They cannot be blank.
* Adding an internship with the same company name, role and department/technologies as an existing internship is not
  allowed and will result in an error message.
* The `DESCRIPTION`, `ELIGIBILITY`, `EXPECTATIONS` and `STATUS` are optional parameters.

Examples of usage for each type of internship can be found below.

---

### Adding a general category of internship: `add general`
Allows users to add a new general internship to their list of internship applications.

Basic Format: `add general /c COMPANY_NAME /r ROLE /dept DEPARTMENT`

Extended Format (with optional parameters): `add general /c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY 
/dept DEPARTMENT /desc DESCRIPTION /status STATUS`

* The `COMPANY_NAME`, `ROLE` and `DEPARTMENT` must be unique to the list of internships and are mandatory parameters.

Examples of usage:
<div style="font-size: 0.85em;">
<pre><code>
> add general /c Google /r Human Resource /dept HR
------------------------------------------------------------------------------------------------------------------------
    New internship added
    Company: Google, Role: Human Resource, Dept: HR
    Now you have 1 internships in the list
------------------------------------------------------------------------------------------------------------------------
</code></pre>
<pre><code>
> add general /c Lazada /r Adviser /dept HR /ex Good Communication /eli Year 2 student
------------------------------------------------------------------------------------------------------------------------
    New internship added
    Company: Lazada, Role: Adviser, Dept: HR
    Now you have 2 internships in the list
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Adding a software category of internship: `add software`
Allows users to add a new software internship to their list of internship applications.

Basic Format: `add software /c COMPANY_NAME /r ROLE /tech TECHNOLOGIES`

Extended Format (with optional parameters): `add software /c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY
/tech TECHNOLOGIES /desc DESCRIPTION /status STATUS`

* The `COMPANY_NAME`, `ROLE` and `TECHNOLOGIES` must be unique to the list of internships and are mandatory parameters.
<div style="page-break-after: always;"></div>

Examples of usage:
<div style="font-size: 0.85em;">
<pre><code>
> add software /c Google /r Software Engineer /tech Java, Python
------------------------------------------------------------------------------------------------------------------------
    New internship added
    Company: Google, Role: Software Engineer, Tech: Java, Python
    Now you have 3 internships in the list
------------------------------------------------------------------------------------------------------------------------
</code></pre>

<pre><code>
> add software /c IBM /r Data Analytics /tech Python, PowerBI /ex Good project showcase
------------------------------------------------------------------------------------------------------------------------
    New internship added
    Company: IBM, Role: Data Analytics, Tech: Python, PowerBI
    Now you have 4 internships in the list
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Adding a hardware category of internship: `add hardware`
Allows users to add a new hardware internship to their list of internship applications.

Basic Format: `add hardware /c COMPANY_NAME /r ROLE /hardtech HARDWARE_TECHNOLOGIES`

Extended Format (with optional parameters): `add hardware /c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY
/hardtech HARDWARE_TECHNOLOGIES /desc DESCRIPTION /status STATUS`

* The `COMPANY_NAME`, `ROLE` and `HARDWARE_TECHNOLOGIES` must be unique to the list of internships and are
  mandatory parameters.

Examples of usage:
<div style="font-size: 0.85em;">
<pre><code>
> add hardware /c Google /r Hardware Engineer /hardtech Arduino, Raspberry Pi
------------------------------------------------------------------------------------------------------------------------
    New internship added
    Company: Google, Role: Hardware Engineer, Tech: Arduino, Raspberry Pi
    Now you have 5 internships in the list
------------------------------------------------------------------------------------------------------------------------
</code></pre>
<pre><code>
> add hardware /c AMD /r FPGA analyser /hardtech FPGA, Verilog /desc Low latency high throughput machine learning inference
------------------------------------------------------------------------------------------------------------------------
    New internship added
    Company: AMD, Role: FPGA analyser, Tech: FPGA, Verilog
    Now you have 6 internships in the list
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Editing an internship: `edit`
Allows users to add (job description, eligibility, and expectations) or modify (company name, role,tech and status)
details for a specific internship application.

Basic Format: `edit /index INDEX_NUMBER /c COMPANY_NAME /r ROLE`

Extended Format (with optional parameters): `edit /index INDEX_NUMBER /c COMPANY_NAME /r ROLE /ex EXPECTATIONS /eli ELIGIBILITY /dept DEPARTMENT 
        /hardtech HARDWARE_TECHNOLOGIES /desc DESCRIPTION /tech TECHNOLOGIES /status STATUS`

> This format shows you all the possible flags or parameters you can set for an internship, however they are optional
> (as-needed basis) -
> i.e. you do not need to utilize all flags when using this command, only those relevant to you.

* The `INDEX_NUMBER` must be valid within the internship list else this will return an error out of range message.
* The parameters cannot effectively be edited to duplicate another internship in the list.
* To edit the tech of a software internship use the `/tech` flag and to edit the tech of a hardware internship use the
  `/hardtech` flag else if you try and set a software tech flag to a hardware role an error message will be displayed.

Example of usage:
<div style="font-size: 0.65em;">
<pre><code>
> edit /index 1 /c IBM /tech C, C++ /eli Y3 student /ex Fast Learner
------------------------------------------------------------------------------------------------------------------------
    Internships saved successfully
    You can view the list of internships at data/internships.txt
    Internship edited successfully.
    [Company: IBM, Role: Software Engineer, Eligibility: Y3 student, Expectations: Fast Learner, Tech Stack: C, C++]
------------------------------------------------------------------------------------------------------------------------
</code></pre>
<pre><code>
> edit /index 2 /desc Some extended description for our users
------------------------------------------------------------------------------------------------------------------------
    Internships saved successfully
    You can view the list of internships at data/internships.txt
    Internship edited successfully.
    [Company: IBM, Role: Data Analytics, Description: Some extended description for our users, Expectations: Good project showcase, Tech Stack: Python, PowerBI]
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>


---

### Deleting an internship: `delete`
Allows users to delete an internship from the list of internship applications.

Format: `delete /index INDEX_OF_INTERNSHIP`

* The `INDEX_OF_INTERNSHIP` should not be out of range of the internship list.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> delete /index 1
------------------------------------------------------------------------------------------------------------------------
    Internships saved successfully
    You can view the list of internships at data/internships.txt
    Successfully deleted internship: Company: Mavericks, Role: Consultant, Dept: Technical Consultation
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Finding internships: `find`
Allows users to find internships based on the internship type, company name or role.

Format: `find [TYPE] [/c COMPANY_NAME] [/r ROLE]`

* `TYPE` can be `general`, `software`, or `hardware`.
* `TYPE`, `COMPANY_NAME` and `ROLE` are optional parameters.
* It is required to specify at least one of the parameters.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> find software /c Google
------------------------------------------------------------------------------------------------------------------------
    Here are the matching internships in your list:
    1. Company: Google, Role: Software Engineer, Eligibility: Y3 student, Expectations: Fast Learner, Tech Stack: C, C++
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Viewing extended description of a specific internship: `desc`
Allows users to view the description of an internship from the list of internship applications.

Format: `desc /index INDEX_OF_INTERNSHIP`

* The `INDEX_OF_INTERNSHIP` should not be out of range of the internship list.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> desc /index 1
------------------------------------------------------------------------------------------------------------------------
    Here is your internship description!
    Company: google
    Role: intern
    Tech Stack: c++
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Adding interviews for internships: `interview for`
Allows users to add multiple rounds of interviews for each internship added.

Basic Format: `interview for /index INDEX_OF_INTERNSHIP /date DATE /start START_TIME /end END_TIME /type TYPE`

Extended Format (With optional parameters): `interview for /index INDEX_OF_INTERNSHIP /date DATE /start START_TIME 
/end END_TIME /type TYPE /email INTERVIEWER_EMAIL /notes NOTES`

* Duplicate interviews are not allowed. However, adding interviews with different parameters for the same index
  of internship is allowed as it will add multiple rounds of interviews for that particular internship.
* The `INDEX_OF_INTERNSHIP` should not be out of range of the internship list.
* Refer to [Date and Time Formats](#date-and-time-formats) for acceptable date and time formats.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> interview for /index 1 /date 2025-01-01 /start 10:00 /end 14:00 /type technical round
------------------------------------------------------------------------------------------------------------------------
    Internships saved successfully
    You can view the list of internships at data/internships.txt
    New interview added
    Interview Date: 2025-01-01, Start Time: 10:00, End Time: 14:00, Round Name: technical round
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---



### Sorting all interviews added by date: `sort interviews`
Allows users to sort all rounds of interviews added across multiple internships by date.

Format: `sort interviews`

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> sort interviews
------------------------------------------------------------------------------------------------------------------------
    Here are your interviews sorted by date and time:
    1. google - intern
        Date: 2025-10-01
        Start: 10:00
        End: 11:00
        Type: Coding
&nbsp;
    2. google - intern
        Date: 2025-10-01
        Start: 15:00
        End: 17:00
        Type: HR  
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### User Commands

### Updating user profile information: `my`
Allows users to update user profile information with details such as name, or preferrred industries, roles
or companies.
Basic Format: `my /c COMPANIES_YOU_PREFER /r ROLES_YOU_PREFER /ygoals YEARLY_GOALS /mgoals MONTHLY_GOALS /pay MIN_PAY-MAX_PAY
                 /ind INDUSTRIES_YOU_PREFER /time TIME_RANGE /name YOUR_NAME`

***NOTE:***  When entering pay range `/pay MIN_PAY - MAX_PAY`, ensure minimum and maximum are both numerical values with minimum less than maximum,
and separate the two with a hyphen "-". Otherwise, you will be prompted again to re-enter these details. 

Do note there is no date-time format mandatory for your preferred `/time TIME_RANGE`, so that you may enter any string such as "Semester 1" or even "Next year" as per your 
convenience.

> This format shows you all the possible flags or parameters you can set for an internship, however they are ALL optional
> (as-needed basis) -
> i.e. you do not need to utilize all flags when using this command, only those relevant to you.

* This feature is implemented to allow the user to be able to simply copy, paste, and use tabular-formatted user profile
  data for their CVs and job applications, hence all data is output in neat ASCII tables which can be copied and pasted into
  required contexts.
<div style="page-break-after: always;"></div>
Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> my /name John Doe /ind Software /c Google /r Developer /mgoals 100 applications /ygoals 2 internships
------------------------------------------------------------------------------------------------------------------------
    Successfully updated your user profile as shown below:
&nbsp;
    Name: John Doe
    Preferred Industries: [Software]
    Preferred Companies: [Google]
    Preferred Roles: [Developer]
    Monthly Goals: 100 applications
    Yearly Goals: 2 internships
&nbsp;
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Viewing user profile information: `view user`
Allows users to view user profile information with details such as name, or preferrred industries, roles
or companies.
Basic Format: `view user`

* This feature is implemented to allow the user to be able to simply copy, paste, and use tabular-formatted user profile
  data for their CVs and job applications, hence all data is output in neat ASCII tables which can be copied and pasted into
  required contexts.

Example of usage:
<div style="font-size: 0.85em;">
<pre><code>
> view user
------------------------------------------------------------------------------------------------------------------------
    Your personalized profile information as shown below:
&nbsp;
┌───────────────────────────────────────┬──────────────────────────────────────┐
│Name                                   │John Doe                              │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Preferred Industries                   │Software                              │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Preferred Companies                    │Google                                │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Preferred Roles                        │Developer                             │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Target Stipend Range                   │N/A                                   │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Internship Date Range                  │N/A                                   │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Monthly Goals                          │100 applications                      │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Yearly Goals                           │2 internships                         │
└───────────────────────────────────────┴──────────────────────────────────────┘
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Adding a general category of project: `project general`
Allows users to add a new general project to their list of projects stored in their user profile.
This is useful for CV formatted project lists, and job applications.

Basic Format: `project general /n PROJECT_NAME /r ROLE /dept DEPARTMENT /obj OBJECTIVES /desc DESCRIPTION /dur DURATION`

* All parameters/flags are mandatory and must be present in the command string for successful execution.

Examples of usage:
<div style="font-size: 0.65em;">
<pre><code>
>  project general /n Team Project for CS2113 /r Unit Tester /dept Software Engineering /obj To get an A+ /desc Worked at identifying feature flaws in app /dur May-August
------------------------------------------------------------------------------------------------------------------------
    Project successfully added to user profile. Below are the details for the same: 
&nbsp;
    Project: Team Project for CS2113, Role: Unit Tester, Dept: Software Engineering
    Objectives: To get an A+, Duration: May-August, Description: Worked at identifying feature flaws in app
    Now you have 3 projects in your user profile.
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Adding a software category of internship: `project software`
Allows users to add a new software project to their list of projects stored in their user profile.
This

Basic Format: `project software /n PROJECT_NAME /r ROLE /dept DEPARTMENT /pro PROGRAMMING_LANGUAGES /obj OBJECTIVES /desc DESCRIPTION /dur DURATION`

* All parameters/flags are mandatory and must be present in the command string for successful execution.

Examples of usage:

<div style="font-size: 0.65em;">
<pre><code>
>  project software /n Team Project for CS2113 /r Unit Tester /pro Java, C++ /obj To get an A+ /desc Worked at identifying feature flaws in app /dur May-August
------------------------------------------------------------------------------------------------------------------------
    Project successfully added to user profile. Below are the details for the same: 
&nbsp;
    Project: Team Project for CS2113, Role: Unit Tester, Programming Languages: Java, C++
    Objectives: To get an A+, Duration: May-August, Description: Worked at identifying feature flaws in app
    Now you have 1 projects in your user profile.
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Adding a hardware category of internship: `project hardware`
Allows users to add a new hardware project to their list of projects stored in their user profile.
This

Basic Format: `project hardware /n PROJECT_NAME /r ROLE /dept DEPARTMENT /hcomp HARDWARE_COMPONENTS /obj OBJECTIVES /desc DESCRIPTION /dur DURATION`

* All parameters/flags are mandatory and must be present in the command string for successful execution.

Examples of usage:
<div style="font-size: 0.65em;">
<pre><code>
> project hardware /n Team Project for EE2026 /r Ui Developer /hcomp Basys Board/obj To get an A+ /desc Worked at creating pixel art for the UI /dur May-August
------------------------------------------------------------------------------------------------------------------------
    Project successfully added to user profile. Below are the details for the same: 
&nbsp;
    Project: Team Project for EE2026, Role: Ui Developer, Hardware Components: Basys Board
    Objectives: To get an A+, Duration: May-August, Description: Worked at creating pixel art for the UI
    Now you have 2 projects in your user profile.
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Viewing a general category of project: `view general`
Allows users to view list of saved general projects.
Basic Format: `view general`

* This feature is implemented to allow the user to be able to simply copy, paste, and use tabular-formatted project
  data for their CVs and job applications, hence all data is output in neat ASCII tables which can be copied and pasted into
  required contexts.

<div style="page-break-after: always;"></div>

Examples of usage:
<div style="font-size: 0.85em;">
<pre><code>
> view general
------------------------------------------------------------------------------------------------------------------------
    Your personalized projects information as shown below:
&nbsp;
┌───────────────────────────────────────┬──────────────────────────────────────┐
│Project:                               │Team Project for CS2113               │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Role:                                  │Unit Tester                           │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Objectives:                            │To get an A+                          │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Description:                           │Worked at identifying feature flaws in│
│                                       │app                                   │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Duration:                              │May-August                            │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Department:                            │Software Engineering                  │
└───────────────────────────────────────┴──────────────────────────────────────┘
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Viewing a software category of internship: `view software`
Allows users to view list of saved software projects.
Basic Format: `view software`

* This feature is implemented to allow the user to be able to simply copy, paste, and use tabular-formatted project
  data for their CVs and job applications, hence all data is output in neat ASCII tables which can be copied and pasted into
  required contexts.

Examples of usage:
<div style="font-size: 0.85em;">
<pre><code>
> view software
------------------------------------------------------------------------------------------------------------------------
    Your personalized projects information as shown below:
&nbsp;
┌───────────────────────────────────────┬──────────────────────────────────────┐
│Project:                               │Team Project for CS2113               │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Role:                                  │Unit Tester                           │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Objectives:                            │To get an A+                          │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Description:                           │Worked at identifying feature flaws in│
│                                       │app                                   │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Duration:                              │May-August                            │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Programming Languages:                 │Java, C++                             │
└───────────────────────────────────────┴──────────────────────────────────────┘
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>

---

### Viewing a hardware category of internship: `view hardware`
Allows users to view list of saved hardware projects.
Basic Format: `view hardware`

* This feature is implemented to allow the user to be able to simply copy, paste, and use tabular-formatted project
  data for their CVs and job applications, hence all data is output in neat ASCII tables which can be copied and pasted into
  required contexts.

<div style="page-break-after: always;"></div>

Examples of usage:
<div style="font-size: 0.85em;">
<pre><code>
> view hardware
------------------------------------------------------------------------------------------------------------------------
    Your personalized projects information as shown below:
&nbsp;
┌───────────────────────────────────────┬──────────────────────────────────────┐
│Project:                               │Team Project for EE2026               │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Role:                                  │Ui Developer                          │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Objectives:                            │To get an A+                          │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Description:                           │Worked at creating pixel art  for  the│
│                                       │UI                                    │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Duration:                              │May-August                            │
├───────────────────────────────────────┼──────────────────────────────────────┤
│Hardware Components:                   │Basys Board                           │
└───────────────────────────────────────┴──────────────────────────────────────┘
------------------------------------------------------------------------------------------------------------------------
</code></pre>
</div>
---

### Exit `bye`

Exits the program. A user can exit the program at any time by typing `bye` in the command line.
If the first word of user input is `bye`, the program will terminate automatically without checking the rest of the
inputs.

---

## Saving the data

InternSprint data is saved in the hard disk automatically after any command that changes the data.
There is no need to save manually.

---

## Editing the data file

InternSprint data is saved automatically in `.json` format `[JAR file location]/data/internships.txt`.
Advanced users are welcome to update data directly by editing that data file.

> CAUTION!:
> The data file has particular `.json` formatting, any change to this file that violates this formatting could **corrupt
the
data** and would require the data file to be deleted
> and started again. Therefore, edit the data file only if you are confident that you can update it correctly.

---

## Date and Time Formats

InternSprint uses `Natty` library to parse dates and times.
Acceptable formats include but is not limited to the following:
- 1st of January 2020
- January 1st 2020
- 1/1/2020

Relative dates are also accepted:
- next week
- next month
- tomorrow
- next Friday

> Provide only valid dates in the command line. `Natty` cannot read years after 10000, which is not valid in this era.

---

## FAQ

**Q**: How do I transfer my data to another computer?

**A**: Simply copy the file `[JAR file location]/data/internships.txt` and transfer it to the secondary device in
the same subdirectory of `[JAR file location]/data`. This will ensure your data is transferred, but keep in mind there
should be only one `internships.txt` in the location.

---

<div style="page-break-after: always;"></div>

## Command Summary

| Command             | Format                                                                                                                                                                 |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Internship Commands |
| `add general`       | `add general /c COMPANY_NAME /r ROLE /dept DEPARTMENT`                                                                                                                 |
| `add software`      | `add software /c COMPANY_NAME /r ROLE /tech TECHNOLOGIES`                                                                                                              |
| `add hardware`      | `add hardware /c COMPANY_NAME /r ROLE /hardtech HARDWARE_TECHNOLOGIES`                                                                                                 |
| `edit`              | `edit /index INDEX_OF_INTERNSHIP`                                                                                                                                      |
| `delete`            | `delete /index INDEX_OF_INTERNSHIP`                                                                                                                                    |
| `find`              | `find [TYPE] [/c COMPANY_NAME] [\r ROLE]`                                                                                                                              |
| `desc`              | `desc /index INDEX_OF_INTERNSHIP`                                                                                                                                      |
| `interview for`     | `interview for /index INDEX_OF_INTERNSHIP /date DATE /start START_TIME /end END_TIME /type TYPE [/email INTERVIEWER_EMAIL] [/notes NOTES]`                             |
| `sort interviews`   | `sort interviews`                                                                                                                                                      |
| User Commands       |                                                                                                                                                                        |
| `my`                | `my /c COMPANIES_YOU_PREFER /r ROLES_YOU_PREFER /ygoals YEARLY_GOALS /mgoals MONTHLY_GOALS /pay PAY_RANGE /ind INDUSTRIES_YOU_PREFER /time TIME_RANGE /name YOUR_NAME` |
| `view user`         | `view user`                                                                                                                                                            |
| `project general`   | `project general /n PROJECT_NAME /r ROLE /dept DEPARTMENT /obj OBJECTIVES /desc DESCRIPTION /dur DURATION`                                                             |
| `project software`  | `project software /n PROJECT_NAME /r ROLE /dept DEPARTMENT /pro PROGRAMMING_LANGUAGES /obj OBJECTIVES /desc DESCRIPTION /dur DURATION`                                 |
| `project hardware`  | `project hardware /n PROJECT_NAME /r ROLE /dept DEPARTMENT /hcomp HARDWARE_COMPONENTS /obj OBJECTIVES /desc DESCRIPTION /dur DURATION`                                 |
| `view general`      | `view general`                                                                                                                                                         |
| `view software`     | `view software`                                                                                                                                                        |
| `view hardware`     | `view hardware`                                                                                                                                                        |
| `bye`               | `bye`                                                                                                                                                                  |
