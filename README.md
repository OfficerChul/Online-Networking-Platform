# CS 180 Project 5 - A Social Network "Profile" Application 
## Implementations:
* **Users can create, edit, and delete accounts for themselves.**
* **Users can create, edit, and delete profiles.**
* **Users can create a friends list of other users of the application.**
  * To be friends, both users must have an account, and both must confirm to be friends with one another. 
  
* **Users can see a list of their sent and received friend requests. They can rescind friend requests or confirm them.** 
  * If a friend request is rescinded, it will no longer appear for the recipient or the sender.
  * If a friend request is confirmed, it will no longer appear for the recipient or the sender. Both users will be added to each other's friend list.
  
* **Users can view a list of all the application's users and send any given individual a friend request or view their profile.**
* **The application supports simultaneous use by multiple users over a network. Changes to profiles and friend requests appears in real-time as users add them.**
* **All user interactions are GUI based.**
* **Data persists regardless of whether or not a user is connected. If a user disconnects and reconnects, their data are still present.**
  
## Optional Features:
* Indicating whether a User is online.
* Adding profile photos.
* Allow accounts to be set to "private", where no other user can view them, and "protected", where only current friends can view them.

*Note: Yet to be added*

## Description of Each Class
* **Account**
  * Creates an Account object with user's name and password
  * Contains generic getter and setter methods
  * Implements Serializable
* **Profile**
  * Creates a Profile object with user's name, account, email, aboutMe, likes and interests, and an array of friend usernames
  * Contains generic getter and setter methods
  * Method for adding a friend to the friend list
  * Implements Serializable
* **ProfileClient**
  * Client class that is capable of simultaneous use by multiple users over the same network
  * Manages all the GUI interfaces
* **Server**
  * Server class that is able to simulatenously hold multiple users using a separate thread for each client
  * All the saved data during the use of the application persists regardless of whether or not a user is connected to the server
  * Implements Object Input/Output Stream to read and write any type of data transferred between each client and the server to a file
* **ServerRequestHandler**
  * Request Handler used for the server to handle the requests sent from the client
  * Implements synchronization while handling the request to control the access of multiple threads
  
 *Note: More to be added*

## Testcases
* **AccountTest**
  \n[Class]
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
  \n[Field]
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  \n[Getters and Setters]
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
* **FriendRequestTest**
  [Class]
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
  [Field]
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  [Getters and Setters]
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
* **ProfileTest**
  [Class]
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
  [Field]
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  [Getters and Setters]
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
* **ProfileClientTest**
  [Class]
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
  [Field]
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  [Getters and Setters]
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
* **ServerTest**
  [Class]
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
  [Field]
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  [Getters and Setters]
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
* **ServerRequestHandlerTest**
  [Class]
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
  [Field]
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  [Getters and Setters]
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
