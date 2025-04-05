# Nikita Marwah - Project Portfolio Page

## Overview
InternSprint is your Ultimate Internship Application Assistant. 
Navigating internship applications can be overwhelming - multiple platforms, countless CV versions, and keeping track of 
application stages can quickly become chaotic. InternSprint is here to streamline the entire process, acting as your personal 
assistant for managing and organizing internship applications efficiently. InternSprint is a CLI application used internship management.
It is written in Java, and has about 8 kLoC.
## Summary of Contributions
### Code contributed: 
[Click Here!](https://nus-cs2113-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=nmarwah7&tabRepo=AY2425S2-CS2113-T11a-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)
    
### Enhancements implemented: 
1. **New Feature**: Added the ability edit added internships and set up optional parameters

    **What it does**: allows the user to edit saved internships: either the user can edit preexisting parameters, or make use of a set of additional optional parameters like status or description.

    **Highlights**: This enhancement affected how we designed the implementation of optional or extended parameters further on in our code requiring changes to certain classes, and its index-based approach was furthered in other commands. 


2. **New Feature**: Added the ability to create and view user profile information

   **What it does**: allows the user to edit their profile information for use on CVs, by making use of a set of additional optional parameters. This involved implementing two new commands, view and update.

   **Highlights**: This enhancement creating a new user class, and restructuring the command execution logic. It also involved JUnit Testing. It involved the implementation of the ASCIITable library.


3. **New Feature**: Added the command to add and view three project types

    **What it does**: allows the user to edit their software,hardware and general projects for use on CVs. This involved implementing six new commands, view and update for each type of project respectively.

    **Highlights**: This enhancement creating three new command classes, and restructuring the command execution logic. It also involved JUnit Testing. It involved the implementation of the ASCIITable library.


4. **Additional Method Implementation**: Added the ability to load saved data.
    
    **What it does**: allows the user to load .json saved data through storage handler


5. **Additional Enhancement**: Added the Ui features, like ASCII art, and standardized Ui formatting.
    
    **What it does**: Keeps Ui cohesive.

### Contributions to the UG: 
- Quick Start
- Edit Command Feature
- All user commands - view user, update user, view and update general, software and hardware projects commands
- Saving Data
- Editing Data
- FAQ

### Contributions to the DG:
- Edit Command Section 
- UI component section
- Create/Update User Profile section
- Add/View Projects under User Profile section
- Instructions for manual testing section

### Contributions to team-based tasks:
- Setting up the GitHub team org/repo 
- Maintaining the issue tracker (with help of others - created issues for V2.0)
- Incorporating ASCII table library into the product

{this is only first draft, to be edited...}