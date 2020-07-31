Feature: Validating the functionalities of ZoomCar application

Scenario: Validating the functionality of car-booking module
Given User is on the URL: "https://www.zoomcar.com/chennai/"
When User clicks on "Start your wonderful journey"
Then User lands on "STARTING POINT" page
When User selects "pickup point" as "Porur"
And User clicks on "NEXT"
Then User lands on "PICKUP TIME" page
When User selects "Start Date" as "tomorrow"
And User clicks on "NEXT"
Then User lands on "DROP OFF TIME" page
When User selects "End Date" as "the last displayed one"
And User clicks on "DONE"
Then User gets to see list of available cars and count them.
And User finds the name of the car with "Highest" price.
