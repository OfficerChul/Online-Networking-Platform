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
  * Creates an Account object with the client's username and password
  * Methods:
    * **getPassword()**: returns client's password
    * **getUsername()**: returns client's username
  * Implements Serializable for ObjectInputStream and ObjectOutputStream classes
  
* **Profile**
  * Creates a Profile object with user's name, account, email, aboutMe, likes and interests, and an array of friend usernames
  * The constructor initializes the length of sentFriendRequests and receivedFriendRequests arrays to zero
  * Methods:
    * **getAccount()**: returns an Account object
    * **getReceivedFriendRequests()**: returns an array of FriendRequest objects received from other users
    * **getSentFriendRequests()**: returns an array of FriendRequest objects sent to other users
    * **getFriendUserNames()**: returns a String array of usernames of  friended users
    * **getLikesAndInterests()**: returns user's likes and interests
    * **getAboutMe()**: returns user's "About Me" page
    * **getEmail()**: returns user's email
    * **getName()**: returns user's name
    * **setName(String name)**: updates user's name with the given new name
    * **setEmail(String email)**: updates user's email with the given new email
    * **setAboutMe(String aboutMe)**: updates user's "About Me" page with the newly given string of texts
    * **setLikesAndInterests(String likesAndInterests)**: updates user's likes and interests with the newly given string of texts
    * **setFriendUserNames(String[] friendUserNames)**: updates user's friend list
    * **setReceviedFriendRequests(String[] receivedFriendRequests)**: updates an array of received friend requests
    * **setSentFriendRequests(String[] sentFriendRequests)**: updates an array of sent friend requests
    * **addToFriendUsernames(String username)**: adds a username to the friend list
  * Implements Serializable for ObjectInputStream and ObjectOutputStream classes
  
* **FriendRequest**
  * Creates a FriendRequest object with the usernames of the sender and the recipient. Uses an integer variable to keep track of the status of the request (-1: refused; 0: pending; 1: accepted).
  * Methods:
    * **getUsernameWhoReceive()**: returns the username of the recipient
    * **getUsernameWhoSent()**: returns the username of the sender
    * **getStatus()**: returns the status of the request
    * **setStatus(int status)**: updates the status of the request
  * Implements Serializable for ObjectInputStream and ObjectOutputStream classes
  
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

## Testing
* **AccountTest**
  
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)
 
  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
  
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
  
* **FriendRequestTest**
 
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)

  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not
 
  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
  
* **ProfileTest**
  
  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)

  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not

  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
  
* **ProfileClientTest**

  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)

  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not

  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
* **ServerTest**

  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)

  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not

  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
  
* **ServerRequestHandlerTest**

  * Test if the class exists or not
  * Test if the class inherits correctly or not(if it doesn't inherit any classm then it mean it inherits Object class)

  * Test if the field exists or not
  * Test if the field has correct type or not
  * Test if the field has the correct modifier or not

  * Test if the method exists or not
  * Test if the method has correct parameters or not
  * Test if the method has correct modifiers or not
  * Test if the method has correct return type or not
  * Test if the method retrives properly or not
