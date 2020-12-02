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
*Most test cases are done via JUnit except for the GUI interactions*
* **Account**
  * **Class**
   * Tests whether the class exists or not
   * Tests if the class inherits correctly
  * **Fields** 
   * Tests whether the fields exist or not
   * Tests if the field has correct type
   * Tests if the field has the correct modifier
  * **Accessor and Mutator methods**
   * Tests whether the methods exist or not
   * Tests if the method has correct parameters
   * Tests if the method has correct modifiers
   * Tests if the method has correct return type
   * Tests if the method retrives properly
* **FriendRequestTest**
  * **Class**
   * Tests whether the class exists or not
   * Tests if the class inherits correctly
  * **Fields**
   * Tests whether the fields exist or not
   * Tests if the field has correct type
   * Tests if the field has the correct modifier
  * **Accessor and Mutator methods**
   * Tests whether the methods exist or not
   * Tests if the method has correct parameters
   * Tests if the method has correct modifiers
   * Tests if the method has correct return type
   * Tests if the method retrives properly
* **ProfileTest**
  * **Class**
   * Tests whether the class exists or not
   * Tests if the class inherits correctly
  * **Fields**
   * Tests whether the fields exist or not
   * Tests if the field has correct type
   * Tests if the field has the correct modifier
  * **Accessor and Mutator methods**
   * Tests whether the methods exist or not
   * Tests if the method has correct parameters
   * Tests if the method has correct modifiers
   * Tests if the method has correct return type
   * Tests if the method retrives properly
* **ProfileClientTest**
  * **Class**
   * Tests whether the class exists or not
   * Tests if the class inherits correctly
  * **Fields**
   * Tests whether the fields exist or not
   * Tests if the field has correct type
   * Tests if the field has the correct modifier
  * **Accessor and Mutator methods**
   * Tests whether the methods exist or not
   * Tests if the method has correct parameters
   * Tests if the method has correct modifiers
   * Tests if the method has correct return type
   * Tests if the method retrives properly
* **ServerTest**
  * **Class**
   * Tests whether the class exists or not
   * Tests if the class inherits correctly
  * **Fields**
   * Tests whether the fields exist or not
   * Tests if the field has correct type
   * Tests if the field has the correct modifier
  * **Accessor and Mutator methods**
   * Tests whether the methods exist or not
   * Tests if the method has correct parameters
   * Tests if the method has correct modifiers
   * Tests if the method has correct return type
   * Tests if the method retrives properly
* **ServerRequestHandlerTest**
  * **Class**
   * Test whether the class exists or not
   * Test if the class inherits correctly
  * **Fields**
   * Test whether the fields exist or not
   * Test if the field has correct type
   * Test if the field has the correct modifier
  * **Accessor and Mutator methods**
   * Tests whether the methods exist or not
   * Tests if the method has correct parameters
   * Tests if the method has correct modifiers
   * Tests if the method has correct return type
   * Tests if the method retrives properly
