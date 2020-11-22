import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;

public class FriendRequest extends JComponent {
    String usernameWhoSent;
    String usernameWhoReceive;
    ArrayList<FriendRequest> friendRequestList;
    public FriendRequest(String usernameWhoSent, String usernameWhoReceive) {
        this.usernameWhoSent = usernameWhoSent;
        this.usernameWhoReceive = usernameWhoReceive;
    }
    public FriendRequest() {
        friendRequestList = new ArrayList<FriendRequest>();
        if (new File("friendRequest.txt").exists() ) {
            try {
                FileReader fr = new FileReader("friendRequest.txt");
                BufferedReader bfr = new BufferedReader(fr);
                String line = bfr.readLine();
                while (line != null && !line.equals("")) {
                    usernameWhoSent = line.split(" ")[0];
                    usernameWhoReceive = line.split(" ")[1];
                    FriendRequest friendRequest = new FriendRequest(usernameWhoSent, usernameWhoReceive);
                    friendRequestList.add(friendRequest);
                    line = bfr.readLine();
                }
                bfr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File toWrite = new File("friendRequest.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(toWrite));
                    StringBuilder sb = new StringBuilder();
                    sb.append("sent receive");
                    bw.write(sb.toString());
                    bw.newLine();
                    bw.flush();
                } catch (IOException n) {
                    n.printStackTrace();
                }
        }
    }
    public void listUsersFriendRequest(String usernameWhoReceived) {
        JFrame frame = new JFrame("Your Friend Request");
        frame.setSize(450, 600);
        frame.setLocation(400,0);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int i = 0, n = 0 ; i < friendRequestList.size() ; i++) {
            if (usernameWhoReceived.equals(friendRequestList.get(i).getUsernameWhoReceive())) {
                GridLayout obj = new GridLayout(++n/2 + 1, ++n/2 + 1);
                frame.setLayout(obj);
                JButton buttonName = new JButton(friendRequestList.get(i).getUsernameWhoSent());
                frame.add(buttonName);
                buttonName.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int Button = JOptionPane.showConfirmDialog(null,
                                "Do you want to accept this user to be your friend?",
                                "Friend Request", JOptionPane.YES_NO_OPTION);
                        if (Button == JOptionPane.YES_OPTION) {
                            //friend.add(buttonName.getText());
                            //write new friend to friendList file
                            for (int i = 0; i < friendRequestList.size(); i++) {
                                if (friendRequestList.get(i)
                                        .getUsernameWhoSent().equals(buttonName.getText())) {
                                    friendRequestList.remove(i);
                                    File toReWrite = new File("friendRequest.txt");
                                    try {
                                        toReWrite.delete();
                                        toReWrite.createNewFile();
                                        BufferedWriter bw = new BufferedWriter(new FileWriter(toReWrite));
                                        StringBuilder sb = new StringBuilder();
                                        for (int n = 0; n < friendRequestList.size(); n++) {
                                            sb.append(friendRequestList.get(n).getUsernameWhoSent() + " "
                                                    + friendRequestList.get(n).getUsernameWhoReceive() +"\n");
                                        }
                                        bw.write(sb.toString());
                                        bw.newLine();
                                        bw.flush();
                                    } catch (IOException io) {
                                        io.printStackTrace();
                                    }

                                }
                            }
                            try {
                                FileReader fr = new FileReader("friendRequest.txt");
                                BufferedReader bfr = new BufferedReader(fr);
                                String line = bfr.readLine();
                                if (line.equals("")) {
                                    File toDelete = new File("friendRequest.txt");
                                    toDelete.delete();
                                } else {
                                    frame.dispose();
                                    listUsersFriendRequest(usernameWhoReceived);
                                }
                            } catch (IOException a) {
                                a.printStackTrace();
                            }

                        } else if (Button == JOptionPane.NO_OPTION) {
                            for (int i = 0; i < friendRequestList.size(); i++) {
                                if (friendRequestList.get(i)
                                        .getUsernameWhoSent().equals(buttonName.getText())) {
                                    friendRequestList.remove(i);
                                    File toReWrite = new File("friendRequest.txt");
                                    try {
                                        toReWrite.delete();
                                        toReWrite.createNewFile();
                                        BufferedWriter bw = new BufferedWriter(new FileWriter(toReWrite));
                                        StringBuilder sb = new StringBuilder();
                                        for (int n = 0; n < friendRequestList.size(); n++) {
                                            sb.append(friendRequestList.get(n).getUsernameWhoSent() + " "
                                                    + friendRequestList.get(n).getUsernameWhoReceive() +"\n");
                                        }
                                        bw.write(sb.toString());
                                        bw.newLine();
                                        bw.flush();
                                    } catch (IOException io) {
                                        io.printStackTrace();
                                    }
                                }
                            }
                            try {
                                FileReader fr = new FileReader("friendRequest.txt");
                                BufferedReader bfr = new BufferedReader(fr);
                                String line = bfr.readLine();
                                if (line.equals("")) {
                                    File toDelete = new File("friendRequest.txt");
                                    toDelete.delete();
                                } else {
                                    frame.dispose();
                                    listUsersFriendRequest(usernameWhoReceived);
                                }
                            } catch (IOException a) {
                                a.printStackTrace();
                            }

                        }

                    }

                });
            }
        }
        frame.setVisible(true);
        frame.setResizable(true);
    }

    public void friendRequest(String usernameWhoSent, String usernameWhoReceive) {
        try {
            Files.write(Paths.get("friendRequest.txt"), (usernameWhoSent
                    + " " + usernameWhoReceive + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException io ) {
            io.printStackTrace();
        }
    }

    public String getUsernameWhoReceive() {
        return usernameWhoReceive;
    }

    public String getUsernameWhoSent() {
        return usernameWhoSent;
    }
    
}
