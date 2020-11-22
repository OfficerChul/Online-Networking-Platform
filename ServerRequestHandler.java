import java.net.Socket;
import java.util.Objects;
import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * PJ04 Option 2 - Request Handler for Server
 *
 * @author Eashan Dubey
 * @version November 22, 2020
 */
public final class ServerRequestHandler implements Runnable {

    private final Socket clientSocket;
    private String[] userNames;
    private String[] onlineUsers;
    //private ArrayList<Profile> profiles;

    public ServerRequestHandler(Socket clientSocket) {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;
    } //PowerRequestHandler


    private String getResponse(String request) {
        String response;

        if ((request == null)) {
            return "MALFORMED_REQUEST";
        } //end if

        // get response logic
        response = "h";
        return response;
    } //getResponse

    /**
     * Serves the request made by the client connected to this request handler's client socket.
     */
    @Override
    public void run() {
        String request; // need to change this to be dynamic?
        String response; // need to change this to be dynamic?

        try (var inputStream = this.clientSocket.getInputStream();
             var reader = new BufferedReader(new InputStreamReader(inputStream));
             var outputStream = this.clientSocket.getOutputStream();
             var writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            request = reader.readLine();

            response = this.getResponse(request);

            writer.write(response);

            writer.newLine();

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } //end try catch
    } //run

    private void Login(String username, String password) {
        // check credentials from arraylist, return user details if correct
    }

    private void EditProfile(String[] UserDetails) {
        // update user with UserDetails, friendRequests should remain unchanged, return user details
    }

    private void CreateProfile(String[] UserDetails) {
        // create user with UserDetails, friendRequests should be set to 0, return user details
    }

    private void DeleteProfile() {
        // should be after login
        // remove user from arraylists
    }

    private void AcceptFriendRequest() {
        // update arraylists, return new user details
    }

    private void RejectFriendRequest() {
        // update arraylists, return new user details
    }

    private void SendFriendRequest() {
        // update arraylists, return new user details
    }

    private void RescindFriendRequest() {
        // update arraylists, return new user details
    }

}