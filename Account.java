import java.io.Serializable;

/**
 * PJ05 Option 2 - Account Class
 *
 * Used to create an Account object and return its parameters 
 * 
 * @author Kyochul Jang, Gilbert Hsu
 * @version November 23, 2020
 */
public class Account implements Serializable {
    private String username;
    private String password;
    
    private static final long serialVersionUID = 6666842209341941741L;

    // creates an Account object
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Account(String username) {
        this.username = username;
    }

    
    /** 
     * retrieves user's password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    
    /**
     * retrieves user's username
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

}
