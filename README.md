# TCSS305

Assignment 1b

Kassie Whitney

Winter 2025

## Assignment Overview
 For assignment 1b, the task involved creating a JUnit test to evaluate all classes for the application, 
 implementing the provided interfaces to create the necessary classes, and closely follow the application's API 
 documentation to ensure all features were added, The application being developed is a generic online store.
 The user interacts with a GUI to add items in their shopping cart, and the GUI displays the number of items in 
 their cart and the number of unique items inside their shopping cart, as well as the total price of all the items. 
 The GUI was provided, and the task required connecting the application logic to the GUI.
 Additionally, a custom testing platform was to be built and implemented to ensure the application meets defined 
 standards and to itdentify any bugs that could potentially break features before the application is pushed to 
 production.

## Technical Impression:
For the record, impressions will be recorded every time work is done on this project.
On the first day of working on the project, the StoreItem class and the StoreItemOrder class were completed.
The StoreItem class was not challenging at all, as it was more or less assignment 1a, which was previously completed.
The primary difference was the need to create overridden methods of the toString, equals, and hashCode methods.
Creating the equals and hashCode methods was a new experience, as these methods weren't used at all 
(although they were introduced) in CSS 142 and CSS 143. This provided an opportunity to relearn how these methods 
should function within the project.
The StoreItemOrder class was a similar experience to the StoreItem class.
The most difficult class to construct from scratch was the StoreCart class.
Apart from having to conduct research on what a record class is, how it's implemented within a child class, 
and how to utilize the getCartSize method throughout the class in order to ensure proper information was displayed in 
the GUI, the add method was the real troublemaker.
There was a persistent bug where every time the user clicked on the quantity box in the GUI, the number of unique items 
inside the cart would increase.
The debugger feature in IntelliJ really helped narrow down the location of the bug within the add method.
The second thing that was challenging was the calculate method.
Although the concept is quite simple, having to figure out how to cleanly write code so that the bulk sets would get 
multiplied to calculate the total price of the bulk set and then add that to the total price of the remaining items not 
in bulk was definitely headache-inducing.
The next step now is to create the tests for the application.
After spending the last couple of days learning about JUnit tests, proper implementation, and grasping the syntax, 
it was actually a lot of fun.
It was definitely a different experience with programming because I had to find ways to implement methods that would 
essentially break the code.
All in all, Assignment 1b was super effective in terms of learning about unit testing and BigDecimal.

## Unresolved problems in my submission:
none

## Citations and Collaborations:
StackOverflow, JAVA DOC, and various youtube videos were used during the production of this code for research, 
learning, syntax, and general information, for the record class, GUI operations, and data storages such as arrays, 
lists, maps, and linked tree.

## Questions:
none
