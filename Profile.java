import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2395638123565212616L;

    
    // userName should be unique for the Account object
    Account account;
    String email;
    String aboutMe;
    String name;
    private ArrayList<String> likesAndInterests = new ArrayList<>();
    private ArrayList<String> friendUserNames = new ArrayList<>();
    private ArrayList<FriendRequest> sentFriendRequests = new ArrayList<FriendRequest>();
    private ArrayList<FriendRequest> receivedFriendRequests = new ArrayList<FriendRequest>();

    // Constructs the Profile object
    public Profile(String name, Account account, String email, String aboutMe, ArrayList<String> likesAndInterests, ArrayList<String> friendUserNames) {
        this.name = name;
        this.account = account;
        this.email = email;
        this.aboutMe = aboutMe;
        this.likesAndInterests = likesAndInterests;
        this.friendUserNames = friendUserNames;
    }

    // getters and setters
    public Account getAccount() {
        return account;
    }

    public ArrayList<FriendRequest> getReceivedFriendRequests() {
        return receivedFriendRequests;
    }

    public ArrayList<FriendRequest> getSentFriendRequests() {
        return sentFriendRequests;
    }

    public ArrayList<String> getFriendUserNames() {
        return friendUserNames;
    }

    public ArrayList<String> getLikesAndInterests() {
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

    public void setFriendUserNames(ArrayList<String> friendUserNames) {
        this.friendUserNames = friendUserNames;
    }

    public void setLikesAndInterests(ArrayList<String> likesAndInterests) {
        this.likesAndInterests = likesAndInterests;
    }

    public void setReceivedFriendRequests(ArrayList<FriendRequest> receivedFriendRequests) {
        this.receivedFriendRequests = receivedFriendRequests;
    }

    public void setSentFriendRequests(ArrayList<FriendRequest> sentFriendRequests) {
        this.sentFriendRequests = sentFriendRequests;
    }

}
