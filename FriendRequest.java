import java.io.Serializable;

/**
 * PJ05 Option 2 - FriendRequest Class
 *
 * @author Gilbert Hsu
 * @version November 23, 2020
 */

public class FriendRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1699480957029563158L;

    String usernameWhoSent;
    String usernameWhoReceive;
    int status; // -1: refused; 0: pending; 1: accepted
    
    // creates a FriendRequest object with the usernames of the sender and the recipient
    // sets the request status to pending
    public FriendRequest(String usernameWhoSent, String usernameWhoReceive) {
        this.usernameWhoSent = usernameWhoSent;
        this.usernameWhoReceive = usernameWhoReceive;
        this.status = 0;
    }

    // returns the recipient's username
    public String getUsernameWhoReceive() {
        return usernameWhoReceive;
    }

    // returns the sender's username
    public String getUsernameWhoSent() {
        return usernameWhoSent;
    }

    // returns the request status
    public int getStatus() {
        return status;
    }

    // updates the request status
    public void setStatus(int status) {
        this.status = status;
    }

}
