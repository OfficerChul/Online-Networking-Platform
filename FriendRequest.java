import java.io.Serializable;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
// import java.util.ArrayList;
// import java.util.Arrays;

// public class FriendRequest extends JComponent implements Serializable {
public class FriendRequest implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1699480957029563158L;
    String usernameWhoSent;
    String usernameWhoReceive;
    int status; // -1: refuese; 0: pending; 1: accepted
    
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


    // /**
    //  * this constructor is used to make a FriendRequest object
    //    so that we can call method in this class.
    //  * I put all the data of username who sent request to whom in a file.
    //  * So when I call this constructor, it will read the data in friendRequest.txt first
    //  * then put those data in an arraylist.
    //  */
    // public FriendRequest() {
    //     friendRequestList = new ArrayList<FriendRequest>();
    //     if (new File("friendRequest.txt").exists() ) { //if friendRequest.txt exist,
    //                                                            // read file.
    //         try {
    //             FileReader fr = new FileReader("friendRequest.txt");
    //             BufferedReader bfr = new BufferedReader(fr);
    //             String line = bfr.readLine();
    //             while (line != null && !line.equals("")) {
    //                 usernameWhoSent = line.split(" ")[0];
    //                 usernameWhoReceive = line.split(" ")[1];
    //                 FriendRequest friendRequest = new FriendRequest(usernameWhoSent, usernameWhoReceive);
    //                 friendRequestList.add(friendRequest);
    //                 line = bfr.readLine();
    //             }
    //             bfr.close();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //     } else {  // if not exist, create it
    //         try {
    //             File toWrite = new File("friendRequest.txt");
    //             BufferedWriter bw = new BufferedWriter(new FileWriter(toWrite));
    //                 StringBuilder sb = new StringBuilder();
    //                 sb.append("sent receive"); //first line, showing which column is sent and receive 
    //                 bw.write(sb.toString());
    //                 bw.newLine();
    //                 bw.flush();
    //             } catch (IOException n) {
    //                 n.printStackTrace();
    //             }
    //     }
    // }

    // /**
    //  * A GUI of users' friend request list, and user can accept or reject after clicking that user
    //  */
    // public void listUsersFriendRequest(String usernameWhoReceived) {
    //     JFrame frame = new JFrame("Your Friend Request"); 
    //     frame.setSize(450, 600);
    //     frame.setLocation(400,0); //you can change the size and location if you want
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     for (int i = 0, n = 0 ; i < friendRequestList.size() ; i++) {
    //         if (usernameWhoReceived.equals(friendRequestList.get(i).getUsernameWhoReceive())) { // n is the number of username we want
    //             // arraylist friendRequest is a list for every request,
    //             // so i use for loop to pick the username we want to find(who sent request to that user)
                
    //             GridLayout obj = new GridLayout(++n/2 + 1, ++n/2 + 1); //the number of the the
    //                                                                              //button increased as the
    //                                                                             // friend request increase
    //             /**
    //              * each button is a username and has it own actionListener
    //              * I used for loop to do this
    //              */
    //             frame.setLayout(obj);
    //             JButton buttonName = new JButton(friendRequestList.get(i).getUsernameWhoSent());
    //             frame.add(buttonName);
    //             buttonName.addActionListener(new ActionListener() {
    //                 @Override
    //                 public void actionPerformed(ActionEvent e) {
    //                     int Button = JOptionPane.showConfirmDialog(null,
    //                             "Do you want to accept this user to be your friend?",
    //                             "Friend Request", JOptionPane.YES_NO_OPTION);
    //                     if (Button == JOptionPane.YES_OPTION) { // click yes (accept)
    //                         //To DO...
    //                         //friend.add(buttonName.getText());
    //                         //write new friend to friendList file
    //                         for (int i = 0; i < friendRequestList.size(); i++) {
    //                             if (friendRequestList.get(i)
    //                                     .getUsernameWhoSent().equals(buttonName.getText())) {
    //                                 friendRequestList.remove(i); // remove from the friendRequest list
    //                                 File toReWrite = new File("friendRequest.txt");
    //                                 try { //rewrite the file (update file every time we accept or reject)
    //                                     toReWrite.delete();
    //                                     toReWrite.createNewFile();
    //                                     BufferedWriter bw = new BufferedWriter(new FileWriter(toReWrite));
    //                                     StringBuilder sb = new StringBuilder();
    //                                     for (int n = 0; n < friendRequestList.size(); n++) {
    //                                         sb.append(friendRequestList.get(n).getUsernameWhoSent() + " "
    //                                                 + friendRequestList.get(n).getUsernameWhoReceive() +"\n");
    //                                     }
    //                                     bw.write(sb.toString());
    //                                     bw.newLine();
    //                                     bw.flush();
    //                                 } catch (IOException io) {
    //                                     io.printStackTrace();
    //                                 }

    //                             }
    //                         }
    //                         try {
    //                             FileReader fr = new FileReader("friendRequest.txt");
    //                             BufferedReader bfr = new BufferedReader(fr);
    //                             String line = bfr.readLine();
    //                             if (line.equals("")) {
    //                                 File toDelete = new File("friendRequest.txt");
    //                                 toDelete.delete();
    //                             } else {
    //                                 frame.dispose(); //when we accept or reject a user, we will update the gui
    //                                 listUsersFriendRequest(usernameWhoReceived); //recursion, call this method again with fewer user
    //                             }
    //                         } catch (IOException a) {
    //                             a.printStackTrace();
    //                         }

    //                     } else if (Button == JOptionPane.NO_OPTION) { //click no (reject)
    //                         for (int i = 0; i < friendRequestList.size(); i++) {
    //                             if (friendRequestList.get(i)
    //                                     .getUsernameWhoSent().equals(buttonName.getText())) {
    //                                 friendRequestList.remove(i);
    //                                 File toReWrite = new File("friendRequest.txt");
    //                                 try {
    //                                     toReWrite.delete();
    //                                     toReWrite.createNewFile();
    //                                     BufferedWriter bw = new BufferedWriter(new FileWriter(toReWrite));
    //                                     StringBuilder sb = new StringBuilder();
    //                                     for (int n = 0; n < friendRequestList.size(); n++) {
    //                                         sb.append(friendRequestList.get(n).getUsernameWhoSent() + " "
    //                                                 + friendRequestList.get(n).getUsernameWhoReceive() +"\n");
    //                                     }
    //                                     bw.write(sb.toString());
    //                                     bw.newLine();
    //                                     bw.flush();
    //                                 } catch (IOException io) {
    //                                     io.printStackTrace();
    //                                 }
    //                             }
    //                         }
    //                         try {
    //                             FileReader fr = new FileReader("friendRequest.txt");
    //                             BufferedReader bfr = new BufferedReader(fr);
    //                             String line = bfr.readLine();
    //                             if (line.equals("")) {
    //                                 File toDelete = new File("friendRequest.txt");
    //                                 toDelete.delete();
    //                             } else {
    //                                 frame.dispose();
    //                                 listUsersFriendRequest(usernameWhoReceived);
    //                             }
    //                         } catch (IOException a) {
    //                             a.printStackTrace();
    //                         }

    //                     }

    //                 }

    //             });
    //         }
    //     }
    //     frame.setVisible(true);
    //     frame.setResizable(true);
    // }

    // /**
    //  * when user click the button send request call this function
    //  * it will append request data in the existing friendRequest.txt
    //  */

    // public void friendRequest(String usernameWhoSent, String usernameWhoReceive) {
    //     try {
    //         Files.write(Paths.get("friendRequest.txt"), (usernameWhoSent
    //                 + " " + usernameWhoReceive + "\n").getBytes(), StandardOpenOption.APPEND); //append text
    //     } catch (IOException io ) {
    //         io.printStackTrace();
    //     }
    // }

    
}
