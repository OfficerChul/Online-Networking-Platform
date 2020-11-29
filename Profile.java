import java.io.Serializable;
// import java.lang.reflect.Array;
// import java.util.ArrayList;
import java.util.Arrays;

public class Profile implements Serializable {

    /**
     *
     */
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
    // private ArrayList<String> likesAndInterests = new ArrayList<>();
    // private ArrayList<String> friendUserNames = new ArrayList<>();
    // private ArrayList<FriendRequest> sentFriendRequests = new ArrayList<FriendRequest>();
    // private ArrayList<FriendRequest> receivedFriendRequests = new ArrayList<FriendRequest>();

    // Constructs the Profile object
    public Profile(String name, Account account, String email, String aboutMe, String likesAndInterests, String[] friendUserNames) {
        this.name = name;
        this.account = account;
        this.email = email;
        this.aboutMe = aboutMe;
        this.likesAndInterests = likesAndInterests;
        this.friendUserNames = friendUserNames;
        this.sentFriendRequests = new FriendRequest[0];
        this.receivedFriendRequests = new FriendRequest[0];
    }

    // getters and setters
    public Account getAccount() {
        return account;
    }

    public FriendRequest[] getReceivedFriendRequests() {
        return receivedFriendRequests;
    }

    public FriendRequest[] getSentFriendRequests() {
        return sentFriendRequests;
    }

    public String[] getFriendUserNames() {
        return friendUserNames;
    }

    public String getLikesAndInterests() {
        return likesAndInterests;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFriendUserNames(String[] friendUserNames) {
        this.friendUserNames = friendUserNames;
    }

    public void setLikesAndInterests(String likesAndInterests) {
        this.likesAndInterests = likesAndInterests;
    }

    public void setReceivedFriendRequests(FriendRequest[] receivedFriendRequests) {
        this.receivedFriendRequests = receivedFriendRequests;
    }

    public void setSentFriendRequests(FriendRequest[] sentFriendRequests) {
        this.sentFriendRequests = sentFriendRequests;
    }

    // public void addToLikesAndInterests(String interest) {
    //     likesAndInterests = Arrays.copyOf(likesAndInterests, likesAndInterests.length + 1);
    //     likesAndInterests[likesAndInterests.length - 1] = interest;
    // }

    public void addToFriendUsernames(String username) {
        friendUserNames = Arrays.copyOf(friendUserNames, friendUserNames.length + 1);
        friendUserNames[friendUserNames.length - 1] = username;
    }
}
