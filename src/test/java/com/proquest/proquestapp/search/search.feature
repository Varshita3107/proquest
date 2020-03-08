@tag
Feature: Navigate to google and search for ProQuest.Write the titles of all results on the first page to a text file on the operating system.
Open the ProQuest website from the results, search for ‘QA’ in the top nav, and take a screenshot

@tag1
Scenario: Search ProQuest in Google search engine
Given that User enter the "www.google.com" into the chrome browser and is on the Google page
When the search phrase "ProQuest" is entered
Then write titles of all results on the first page to a text file

@tag2
Scenario: Search QA in ProQuest and take screenshot
Given that User clicked the Proquest website link from the results
When the search phrase "QA" is entered in top nav
Then take the screenshot