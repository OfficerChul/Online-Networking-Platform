import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;
// import java.util.ArrayList;
import java.util.Objects;

/**
 * PJ04 Option 2 - Request Handler for Server
 *
 * @author Eashan Dubey
 * @version November 22, 2020
 */
public final class ServerRequestHandler implements Runnable {

    private final Socket clientSocket;
    private String[] userNames;
    // private ArrayList<String> userNames;
    private String[] onlineUsers;
    private Profile[] profiles;
    // private ArrayList<Profile> profiles;

    public ServerRequestHandler(Socket clientSocket) {
        Objects.requireNonNull(clientSocket, "the specified client socket is null");
        this.clientSocket = clientSocket;
    } //ServerRequestHandler

    private synchronized Object getResponse(Object request) { // synchronized to avoid race conditions
        profiles = Server.getProfiles(); // updates ServerRequestHandler data from Server
        Object response = null;
        String req = null;
        if ((request == null)) {
            return "MALFORMED_REQUEST";
        } //end if

        if (request instanceof Profile) {
            Profile receivedProfile = (Profile) request;
            if (usernameIsTaken(receivedProfile.getAccount().getUsername())) {
                updateProfile(receivedProfile);
            } else {
                profiles = Arrays.copyOf(profiles, profiles.length + 1);
                profiles[profiles.length - 1] = receivedProfile;
            }
            response = receivedProfile;
        } else if (request instanceof String) {
            req = (String) request;
            String[] requestVals = req.split(":");
            switch (requestVals[0]) {
                case "Req0": {
                    response = "Res0: Connection Esablished";
                    break;
                }
                case "Req1": { // Login: “Req1: <username>: <password>”
                    String username = removeSpaceAtStart(requestVals[1]);
                    String password = removeSpaceAtStart(requestVals[2]);

                    if (Login(username, password)) {
                        response = returnProfileFromUsername(username);
                    } else {
                        response = "E1: ERROR: Login Failed";
                    }
                    break;
                }
                case "Req2": { // Check if username is unique: “Req2: <username>”
                    String username = removeSpaceAtStart(requestVals[1]);
                    boolean usernameTaken = usernameIsTaken(username);
                    if (usernameTaken) {
                        response = "E2: ERROR: Username already exists";
                    } else {
                        response = "Res2: username is available";
                    }
                    break;
                }
                case "Req4": { // Delete user: “Req4: <username>”
                    String username = removeSpaceAtStart(requestVals[1]);
                    DeleteProfile(username);
                    response = "Res4: Successfully Deleted";
                    break;
                }
                case "Req6": { // Send FriendRequest: “Req6: <username>: <targetUsername>”
                    String senderUsername = removeSpaceAtStart(requestVals[1]);
                    String recipientUsername = removeSpaceAtStart(requestVals[2]);

                    if (usersAreFriends(senderUsername,
                            recipientUsername).equals("true")) {
                        response = "E61: ERROR: Users are already friends";
                        break;
                    } else if (usersAreFriends(senderUsername,
                            recipientUsername).equals("Invalid Username")) {
                        response = "E62: ERROR: Invalid username";
                        break;
                    } else if (!friendRequestAlreadyExists(senderUsername,
                            recipientUsername)) {
                        if (SendFriendRequest(senderUsername, recipientUsername)) {
                            response = "Res6: Request Sent";
                        }
                    } else if (friendRequestAlreadyExists(senderUsername, recipientUsername)) {
                        response = "E63: ERROR: Friend request already sent";
                    }
                    break;
                }
                case "Req7": { // Accept FriendRequest: “Req7: <username>: <targetUsername>”
                    String senderUsername = removeSpaceAtStart(requestVals[1]);
                    String recipientUsername = removeSpaceAtStart(requestVals[2]);
                    AcceptFriendRequest(senderUsername, recipientUsername);

                    // response is the profile of the recipient user / user that accepts request
                    response = returnProfileFromUsername(recipientUsername);
                    break;
                }
                case "Req8": { // Refuse Friend Request: “Req8: <username>: <targetUsername>”
                    String senderUsername = removeSpaceAtStart(requestVals[1]);
                    String recipientUsername = removeSpaceAtStart(requestVals[2]);
                    RejectFriendRequest(senderUsername, recipientUsername);

                    // response is the profile of the recipient user / user that refuses request
                    response = returnProfileFromUsername(recipientUsername);
                    break;
                }
                case "Req9": { // Get all profile usernames: “Req9: ”
                    // ArrayList<String> userList = new ArrayList<String>();
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < profiles.length; i++) {
                        sb.append(profiles[i].getAccount().getUsername());
                        sb.append(",");
                        // userList.add(profiles[i].getAccount().getUsername());
                    }
                    // userNames = userList;

                    // response is string arraylist of all current users' usernames to display
                    // response = userNames;
                    response = sb.toString();
                    break;
                }
                case "Req10": { // Refresh / resend Profile Data “Req10: <username>”
                    String username = removeSpaceAtStart(requestVals[1]);
                    response = returnProfileFromUsername(username);
                    break;
                }
                default: {
                    response = "invalid request";
                }
            }   
        }
        Server.setProfiles(profiles); // updates server with ServerRequestHandler Data
        // Server.writeProfilesToFile("serverData.txt");
        // all responses are either Profile or String Objects
        return response;
    } //getResponse

    /**
     * Serves the request made by the client connected to this request handler's client socket.
     */
    @Override
    public void run() {
        Object request;
        Object response;

        try (var inputStream = this.clientSocket.getInputStream();
             var reader = new ObjectInputStream(inputStream);
             var outputStream = this.clientSocket.getOutputStream();
             var writer = new ObjectOutputStream(outputStream)) {
            while (true) {
                request = reader.readObject();
                response = this.getResponse(request);
                // writer.writeObject(response);
                writer.writeUnshared(response);
                writer.flush();
                writer.reset();
            }
        } catch (SocketException e) {
            String ipAddress = clientSocket.getInetAddress().getHostAddress();
            System.out.println("Connection Lost from: " + ipAddress);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } //end try catch
    } //run

    private boolean Login(String username, String password) {
        // check credentials from arraylist, return user details if correct
        boolean login = false;
        String profilePassword = null;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(username)) {
                login = true;
                profilePassword = profiles[i].getAccount().getPassword();
            }
        }
        if (login) {
            login = false;
            if (profilePassword.equals(password)) {
                login = true;
            }
        }
        return login;
    }

    private Profile returnProfileFromUsername(String username) {
        Profile profile = null;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(username)) {
                profile = profiles[i];
            }
        }
        return profile;
    }

    private void updateProfile(Profile profileToUpdate) {
        String username = profileToUpdate.getAccount().getUsername();
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(username)) {
                // profiles.set(i, profileToUpdate);
                profiles[i] = profileToUpdate;
            }
        }
    }

    private boolean usernameIsTaken(String username) {
        boolean usernameExists = false;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(username)) {
                usernameExists = true;
            }
        }
        return usernameExists;
    }

    private void profileRequest(Profile profile) {
        // if username exists, update username with new profile
        // if username doesn't exist, profile is added
        // returns index of the new or updated profile

        boolean usernameExists = false;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(profile.getAccount().getUsername())) {
                // profiles.set(i, profile);
                profiles[i] = profile;
                usernameExists = true;
            }
        }
        if (!usernameExists) {
            // profiles.add(profile);
            profiles = Arrays.copyOf(profiles, profiles.length + 1);
            profiles[profiles.length - 1] = profile;
        }
    }

    private void DeleteProfile(String username) {
        // should be after login
        // remove user from arraylists
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(username)) {
                // profiles.remove(i);
                profiles[i - 1] = profiles[profiles.length - 1];
                profiles = Arrays.copyOf(profiles, profiles.length - 1);
            }
        }
    }

    private void AcceptFriendRequest(String senderUsername, String recipientUsername) {
        // deletes friendRequest from sender & recipient records
        // adds to both users' friend lists
        FriendRequest[] senderSentRequests;
        FriendRequest[] recipientreceivedRequests;
        // ArrayList<FriendRequest> senderSentRequests;
        // ArrayList<FriendRequest> recipientreceivedRequests;

        int senderIndex = -1;
        int recipientIndex = -1;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(senderUsername)) {
                senderIndex = i;
            }
            if (profiles[i].getAccount().getUsername().equals(recipientUsername)) {
                recipientIndex = i;
            }
            if ((senderIndex != -1) && (recipientIndex != -1)) {
                break;
            }
        }
        senderSentRequests = profiles[senderIndex].getSentFriendRequests();
        for (int i = 0; i < senderSentRequests.length; i++) {
            if (senderSentRequests[i].usernameWhoReceive.equals(recipientUsername)) {
                senderSentRequests[i].setStatus(1);
                // senderSentRequests.remove(i);
            }
        }
        profiles[senderIndex].setSentFriendRequests(senderSentRequests);

        recipientreceivedRequests = profiles[recipientIndex].getReceivedFriendRequests();
        for (int i = 0; i < recipientreceivedRequests.length; i++) {
            if (recipientreceivedRequests[i].usernameWhoSent.equals(senderUsername)) {
                recipientreceivedRequests[i].setStatus(1);
                // recipientreceivedRequests.remove(i);
            }
        }
        profiles[recipientIndex].setReceivedFriendRequests(recipientreceivedRequests);

        profiles[senderIndex].addToFriendUsernames(recipientUsername);
        profiles[recipientIndex].addToFriendUsernames(senderUsername);
    }

    private void RejectFriendRequest(String senderUsername, String recipientUsername) {
        // deletes friendRequest from sender & recipient records
        FriendRequest[] senderSentRequests;
        FriendRequest[] recipientreceivedRequests;
        // ArrayList<FriendRequest> senderSentRequests;
        // ArrayList<FriendRequest> recipientreceivedRequests;
        int senderIndex = -1;
        int recipientIndex = -1;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(senderUsername)) {
                senderIndex = i;
            }
            if (profiles[i].getAccount().getUsername().equals(recipientUsername)) {
                recipientIndex = i;
            }
            if ((senderIndex != -1) && (recipientIndex != -1)) {
                break;
            }
        }
        senderSentRequests = profiles[senderIndex].getSentFriendRequests();
        for (int i = 0; i < senderSentRequests.length; i++) {
            if (senderSentRequests[i].usernameWhoReceive.equals(recipientUsername)) {
                senderSentRequests[i].setStatus(-1);
                // senderSentRequests.remove(i);
            }
        }
        profiles[senderIndex].setSentFriendRequests(senderSentRequests);

        recipientreceivedRequests = profiles[recipientIndex].getReceivedFriendRequests();
        for (int i = 0; i < recipientreceivedRequests.length; i++) {
            if (recipientreceivedRequests[i].usernameWhoSent.equals(senderUsername)) {
                recipientreceivedRequests[i].setStatus(-1);
                // recipientreceivedRequests.remove(i);
            }
        }
        profiles[recipientIndex].setReceivedFriendRequests(recipientreceivedRequests);
    }

    private boolean SendFriendRequest(String senderUsername, String recipientUsername) {
        // creates a new FriendRequest Object, adds this to both users' respective arraylists
        // returns false if either username is invalid

        FriendRequest thisFriendRequest = new FriendRequest(senderUsername, recipientUsername);
        boolean invalidSenderUsername = true;
        boolean invalidRecipientUsername = true;

        FriendRequest[] senderSentRequests;
        FriendRequest[] recipientreceivedRequests;
        // ArrayList<FriendRequest> senderSentRequests;
        // ArrayList<FriendRequest> recipientreceivedRequests;
        int senderIndex = -1;
        int recipientIndex = -1;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(senderUsername)) {
                senderIndex = i;
                invalidSenderUsername = false;
            }
            if (profiles[i].getAccount().getUsername().equals(recipientUsername)) {
                recipientIndex = i;
                invalidRecipientUsername = false;
            }
            if ((senderIndex != -1) && (recipientIndex != -1)) {
                break;
            }
        }
        if (invalidRecipientUsername || invalidSenderUsername) {
            return false;
        } else {
            senderSentRequests = profiles[senderIndex].getSentFriendRequests();
            // senderSentRequests.add(thisFriendRequest);
            senderSentRequests = Arrays.copyOf(senderSentRequests, senderSentRequests.length + 1);
            senderSentRequests[senderSentRequests.length - 1] = thisFriendRequest;
            profiles[senderIndex].setSentFriendRequests(senderSentRequests);

            recipientreceivedRequests = profiles[recipientIndex].getReceivedFriendRequests();
            // recipientreceivedRequests.add(thisFriendRequest);
            recipientreceivedRequests = Arrays.copyOf(recipientreceivedRequests, recipientreceivedRequests.length + 1);
            recipientreceivedRequests[recipientreceivedRequests.length - 1] = thisFriendRequest;
            profiles[recipientIndex].setReceivedFriendRequests(recipientreceivedRequests);
            return true;
        }
    }

    private void RescindFriendRequest(String senderUsername, String recipientUsername) {
        // logically the same as RejectFriendRequest method:
        // deletes friendRequest from sender & recipient records
        RejectFriendRequest(senderUsername, recipientUsername);
    }

    private boolean friendRequestAlreadyExists(String senderUsername, String recipientUsername) {
        FriendRequest[] senderSentRequests;
        FriendRequest[] recipientreceivedRequests;
        // ArrayList<FriendRequest> senderSentRequests;
        // ArrayList<FriendRequest> recipientreceivedRequests;
        boolean exists = false;
        Boolean existsInSenderRecords = false;
        Boolean existsInRecipientRecords = false;

        int senderIndex = -1;
        int recipientIndex = -1;
        for (int i = 0; i < profiles.length; i++) {
            if (profiles[i].getAccount().getUsername().equals(senderUsername)) {
                senderIndex = i;
            }
            if (profiles[i].getAccount().getUsername().equals(recipientUsername)) {
                recipientIndex = i;
            }
            if ((senderIndex != -1) && (recipientIndex != -1)) {
                break;
            }
        }

        senderSentRequests = profiles[senderIndex].getSentFriendRequests();
        for (int i = 0; i < senderSentRequests.length; i++) {
            if (senderSentRequests[i].usernameWhoReceive.equals(recipientUsername)) {
                existsInSenderRecords = true;
            }
        }
        profiles[senderIndex].setSentFriendRequests(senderSentRequests);

        recipientreceivedRequests = profiles[recipientIndex].getReceivedFriendRequests();
        for (int i = 0; i < recipientreceivedRequests.length; i++) {
            if (recipientreceivedRequests[i].usernameWhoSent.equals(senderUsername)) {
                existsInRecipientRecords = true;
            }
        }
        profiles[recipientIndex].setReceivedFriendRequests(recipientreceivedRequests);

        if (existsInRecipientRecords && existsInSenderRecords) {
            // if request exists on records of both sides
            exists = true;
        } else if (!existsInRecipientRecords && existsInSenderRecords) {
            // if request only exists on records of one side, update other side
            recipientreceivedRequests = Arrays.copyOf(recipientreceivedRequests, recipientreceivedRequests.length + 1);
            recipientreceivedRequests[recipientreceivedRequests.length - 1] = new FriendRequest(senderUsername, recipientUsername);
            // recipientreceivedRequests.add(new FriendRequest(senderUsername, recipientUsername));
            profiles[recipientIndex].setReceivedFriendRequests(recipientreceivedRequests);
            exists = true;
        } else if (existsInRecipientRecords && !existsInSenderRecords) {
            // if request only exists on records of one side, update other side
            senderSentRequests = Arrays.copyOf(senderSentRequests, senderSentRequests.length + 1);
            senderSentRequests[senderSentRequests.length - 1] = new FriendRequest(senderUsername, recipientUsername);
            // senderSentRequests.add(new FriendRequest(senderUsername, recipientUsername));
            profiles[senderIndex].setSentFriendRequests(senderSentRequests);
            exists = true;
        } else {
            // if request doesn't exist on records of either side
            // in this case, friend request can be sent as long as usernames exist
            exists = false;
        }

        return exists;
    }

    private String usersAreFriends(String username1, String username2) {
        Profile profile1 = returnProfileFromUsername(username1);
        Profile profile2 = returnProfileFromUsername(username2);
        boolean areFriends = false;
        if ((profile1 == null) || (profile2 == null)) {
            return "Invalid Username";
        } else {
            var profile1FriendList = profile1.getFriendUserNames();
            var profile2FriendList = profile2.getFriendUserNames();

            if ((Arrays.asList(profile1FriendList).contains(username2))
                // profile1FriendList.contains(username2))
                    && (Arrays.asList(profile2FriendList).contains(username2))) {
                return "true";
            } else if (!(Arrays.asList(profile1FriendList)).contains(username2)
                    && (Arrays.asList(profile2FriendList).contains(username2))) {
                // profile1FriendList.add(username2);
                profile1FriendList = Arrays.copyOf(profile1FriendList, profile1FriendList.length + 1);
                profile1FriendList[profile1FriendList.length - 1] = username2;

                profile1.setFriendUserNames(profile1FriendList);
                updateProfile(profile1);
                return "true";
            } else if ((Arrays.asList(profile1FriendList)).contains(username2)
                    && (!Arrays.asList(profile2FriendList).contains(username2))) {
                // profile2FriendList.add(username1);
                profile2FriendList = Arrays.copyOf(profile2FriendList, profile2FriendList.length + 1);
                profile2FriendList[profile2FriendList.length - 1] = username1;
                profile2.setFriendUserNames(profile2FriendList);
                updateProfile(profile2);
                return "true";
            } else {
                // users are not friends
                return "false";
            }
        }
    }

    private String removeSpaceAtStart(String input) {
        if (input.charAt(0) == ' ') {
            input = input.substring(1);
        }
        return input;
    }
}
