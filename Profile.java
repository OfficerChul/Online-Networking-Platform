import java.io.Serializable;
import java.util.Arrays;

/**
 * PJ05 Option 2 - Profile Class
 *
 * Used to create a Profile object and return and set its parameters
 * 
 * @author Kyochul Jang
 * @version November 23, 2020
 */
public class Profile implements Serializable {
    private static final long serialVersionUID = 2395638123565212616L;

    // userName should be unique for the Account object
    private Account account;
    private String email;
    private String aboutMe;
    private String name;
    private String likesAndInterests;
    private String[] friendUserNames;
    private FriendRequest[] sentFriendRequests;
    private FriendRequest[] receivedFriendRequests;

    // Constructs the Profile object with the given parameters
    public Profile(String name, Account account, String email,
                   String aboutMe, String likesAndInterests, String[] friendUserNames) {
        this.name = name;
        this.account = account;
        this.email = email;
        this.aboutMe = aboutMe;
        this.likesAndInterests = likesAndInterests;
        this.friendUserNames = friendUserNames;
        this.sentFriendRequests = new FriendRequest[0];
        this.receivedFriendRequests = new FriendRequest[0];
    }
    
    
    /**
     * return an Account object
     * 
     * @return Account
     */
    public Account getAccount() {
        return account;
    }

    
    /**
     * return an array of friend requests received from other users
     * 
     * @return FriendRequest[]
     */
    public FriendRequest[] getReceivedFriendRequests() {
        return receivedFriendRequests;
    }

    
    /**
     * return an array of friend requests sent to other users
     * 
     * @return FriendRequest[]
     */
    public FriendRequest[] getSentFriendRequests() {
        return sentFriendRequests;
    }

    
    /**
     * return a String array of usernames of friended users
     * 
     * @return String[]
     */
    public String[] getFriendUserNames() {
        return friendUserNames;
    }

    
    /**
     * return user's likes and interests
     * 
     * @return String
     */
    public String getLikesAndInterests() {
        return likesAndInterests;
    }

    
    /**
     * return user's "About Me"
     * 
     * @return String
     */
    public String getAboutMe() {
        return aboutMe;
    }

    
    /**
     * return user's email address 
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /**
     * return user's name
     * 
     * @return String
     */
    public String getName() {
        return name;
    }

    
    /**
     * updates user's name with the given name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * updates user's "About Me" page with the given string of texts
     *
     * @param aboutMe
     */
    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    
    /**
     * updates the user's account
     * 
     * @param account
     */
    public void setAccount(Account account) {
        this.account = account;
    }

    
    /**
     * updates email
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /**
     * updates friendlist of usernames
     *
     * @param friendUserNames
     */
    public void setFriendUserNames(String[] friendUserNames) {
        this.friendUserNames = friendUserNames;
    }

    
    /**
     * updates likes and interests page
     * @param likesAndInterests
     */
    public void setLikesAndInterests(String likesAndInterests) {
        this.likesAndInterests = likesAndInterests;
    }

    
    /**
     * updates an array of received friend requests
     *
     * @param receivedFriendRequests
     */
    public void setReceivedFriendRequests(FriendRequest[] receivedFriendRequests) {
        this.receivedFriendRequests = receivedFriendRequests;
    }

    
    /**
     * updates an array of sent friend requests
     *
     * @param sentFriendRequests
     */
    public void setSentFriendRequests(FriendRequest[] sentFriendRequests) {
        this.sentFriendRequests = sentFriendRequests;
    }

    
    /**
     * adds a username to the friend list
     *
     * @param username
     */
    public void addToFriendUsernames(String username) {
        friendUserNames = Arrays.copyOf(friendUserNames, friendUserNames.length + 1);
        friendUserNames[friendUserNames.length - 1] = username;
    }
}
