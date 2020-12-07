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
    
    /*
     * creates a FriendRequest object with the usernames of the sender and the recipient
     */
    public FriendRequest(String usernameWhoSent, String usernameWhoReceive) {
        this.usernameWhoSent = usernameWhoSent;
        this.usernameWhoReceive = usernameWhoReceive;
        this.status = 0; // sets the request status to pending
    }

    
    /** 
     * returns the recipient's username
     * 
     * @return String
     */
    public String getUsernameWhoReceive() {
        return usernameWhoReceive;
    }

    
    /** 
     * returns the sender's username
     * 
     * @return String
     */
    public String getUsernameWhoSent() {
        return usernameWhoSent;
    }

    
    /** 
     * returns the request status
     * 
     * @return int
     */
    public int getStatus() {
        return status;
    }

    
    /** 
     * updates the request status
     * 
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
