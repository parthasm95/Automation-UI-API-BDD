@ui @regression
Feature: Home Page Feature

Background:
Given user has already logged in to application
|username|password|
|partha9595@gmail.com|Password@124|
And User navigate to Home Page

@home
Scenario: Verify Home page title
Given user is on Home page
When user gets the title of the page
Then page title should be "My Shop"

@home
Scenario: Verify Home page elements
Given user is on Home page
Then user gets items in Navigation Bar
|Partha Mohapatra|
|Sign out|
|Contact us|
And search bar should be present
And user get list of categories
|WOMEN|
|DRESSES|
|T-SHIRTS|
|BLOG|