=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: khtarnas
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the three core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections: I used collections to store the chunks of the snake's tail (in the Snake class)
     and for the enemies in my Game class. I used a LinkedList for the first and an ArrayList for
     the second. The implementations were appropriate for the situation they were used in. This is
     because they made the implementation easier. The main use, in the Snake class, was perfect
     because it allowed me to look through each chunk of tail individually and check them easily
     with the use of an iterator. It also was perfect for adding a head. I did't have to shift 
     anything in order to make my snake move. I merely had to add an element to the list and it
     already looked how I wanted it to -- and it was in order!

  2. Sub-typing and Dynamic Dispatch: I used this for the implementation of my enemies. I have
     3 different types of enemies and they all do similar things. They have numerous overlapping
     functionalities. So, this was perfect for an abstract class and some sub-types. If you look at
     my Enemies class you can see some functions are implemented and some have the abstract keyword. 
     In terms of dynamic dispatch, I used this as well. The ArrayList of Enemies mentioned in part
     one of this is a great example of this. I was able to have a group of different objects
     in one list because they all shared a super-type. 

  3. JUnit testing: I tested many of my classes (TODO: list them). I tested functions that
     did not have obvious results (I did not test setter and getter methods -- those are simple
     enough that eclipse can even auto-write them, so it was not necessary). BUT there were still
     many things to test and this was a time consuming part of my work. There were some classes
     that did not make sense to test (e.g. Direction because it is just an enum or Game because
     using JUnit tests for frames and panels is less useful and more difficult that just testing
     output visually). There were also some classes whose tests were very short and limited (e.g. 
     Food because most functions are straight forward or involves drawing something on the screen).
     TODO add.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  1.  Arena: This class is the main stage of the game. It puts all the different objects together
             into one spot and facilitates interactions between them. It also checks the lose
             condition.
  
  2.  Direction: This class is an enum. It has four values (UP, DOWN, LEFT, RIGHT). These were
                 usual when implementing any movement functionality as it is important to 
                 understand where an object is going and where it should be going in order to 
                 move it.
  
  3.  Enemies: This is an abstract class. It creates and completes numerous functions that can be 
               used by its children classes, but leaves some as abstract so they can be implemented
               individually. 
  
  4.  Food: This is an object that represents the food in the game.
  
  5.  Game: This is the highest level classes in this file. This puts together the frame. It uses
            buttons to allow the user to move between the home screen, the instructions and the
            game itself. 
  
  6.  Position: This is a user-define data type class. I created this data type to hold x and y
                values. It overrides the equals and compareTo functions as to be a more viable
                data type with respect to how I used it.
  
  7.  RotatingEnemy: This is one of the sub-types of the Enemies class. This object interacts with
                     the snake and has numerous functionality (including strange, semi-random
                     movement -- earning it the name Buffoon in the instructions).
  
  8.  Snake: This is the main object I created. It holds all pieces of the snake. It allows the
             snake to do many things, including interact with other objects. 
  
  9.  StationaryEnemy: This is another of the sub-types of the Enemies class. This object, like
                       the previous sub-type of Enemies interacts with the snake and has numerous
                       functionalities. Unlike the previously mentioned sub-type, this one does not
                       move (earning the name of the Rock in the instructions).
  
  10. TrackingEnemey: This is the third and final sub-type of the Enemies class. This object, as 
                      with the other two, interacts with the snake and has numerous functionalities.
                      This one is the most difficult enemy for the user -- it tracks the head of the
                      snake and runs towards it.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

  There was nothing significant that hindered my progress. The most time consuming and frustrating
  parts were (1) trying to use a library that I am unfamiliar with and need to constantly search
  for functions in and (2) JUnit testing. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

  I think I did have a good separation of functionality. The private state is well encapsulated. 
  I think I could have done better at doing less in my Arena class and more in my objects classes --
  this would take significantly better planning from the get-go. If I were to refactor, I would
  spend a lot of time on efficiency. There were definitely a couple functions that I create that
  could have been more efficient, but I just couldn't work through it in the time crunch (and, of
  course, it does work now anyway so it wasn't a priority).

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  1. I used a descending iterator instead of a regular one a couple times to be
     more efficient. To learn how to use it, I referenced this website: 
     https://www.geeksforgeeks.org/linkedlist-descendingiterator-method-in-java-with-examples/
  2. In order to understand how to change font size before drawing text using swing,
     I referenced this: https://stackoverflow.com/questions/18249592/
     how-to-change-font-size-in-drawstring-java
