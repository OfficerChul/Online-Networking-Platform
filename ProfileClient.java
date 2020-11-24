import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class ProfileClient extends JComponent implements Runnable {
    /**
     *
     */
    private static final long serialVersionUID = 5597188023823293381L;
    Boolean loggedIn = false;
    Socket socket;
    String hostName = "localhost";
    int portNumber = 4242;

    JFrame loginFrame;
    JFrame registerFrame;
    JFrame mainFrame;
    JFrame listAllUsFrame;
    JFrame friendRequestFrame;
    JFrame reqeustHistoryFrame;

    BufferedReader reader;
    PrintWriter writer;

    JLabel userLabel;
    JLabel passwordLabel;
    JTextField userText;
    JPasswordField passwordText;
    JButton loginButton;
    JButton registerButton;
    JButton registerButton2;
    JButton registerCancelButton;
    JButton listUserButton;
    JButton backToMeButton;
    JPanel friendListPanel;
    JScrollPane friendListScrollPanel;
    JButton friendsButton;
    JPanel lowerLeftPanel;
    JLabel myNameLabel;
    JLabel phoneLabel;
    JLabel profileAboutMeLabel;
    JButton profileAddFriendButton;
    JButton profileCancelButton;
    JLabel profileEmailLabel;
    JTextField profileEmailText;
    JLabel profileInterestsLabel;
    JTextField profileLikesAndInterestsText;
    JLabel profileNameLabel;
    JTextField profileNameText;
    JPanel upperLeftPanel;
    JTextField profilePhoneText;
    JButton profileSaveButton;
    JPanel profilePanel;
    JTextArea profileAboutMeArea;
    JPanel lowerRightPanel;

    ProfileClient profileClient;
    Profile currentProfile;
    // final Profile myProfile = profileA;

    

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                String username = userText.getText();
                String password = String.valueOf(passwordText.getPassword());
                
                if (!username.isBlank() && username.matches("^[a-zA-Z0-9]*$")) {
                    if (!password.isBlank() && password.matches("^[a-zA-Z0-9]*$")) {
                        if (password.length() >= 8) {
                            if (userLogin(username, password) == 1) {
                                loginFrame.dispose();
                                loggedIn = true;
                                showMainPanel();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Your password should be at least 8 characters long.", "Login", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your password should be alphanumeric.", "Login", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your username should be alphanumeric.", "Login", JOptionPane.ERROR_MESSAGE);
                }
            }

            if (e.getSource() == registerButton) {
                showRegisterPanel();
            }

            if (e.getSource() == registerButton2) {
                String username = userText.getText();
                String password = String.valueOf(passwordText.getPassword());

                if (!username.isBlank() && username.matches("^[a-zA-Z0-9]*$")) {
                    if (!password.isBlank() && password.matches("^[a-zA-Z0-9]*$")) {
                        if (password.length() >= 8) {
                            if (userRegister(username, password) == 1) {
                                registerFrame.dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Your password should be at least 8 characters long.", "Login", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Your password should be alphanumeric.", "Login", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Your username should be alphanumeric.", "Login", JOptionPane.ERROR_MESSAGE);
                }

            }

            if (e.getSource() == registerCancelButton) {
                registerFrame.dispose();
            }

            if (e.getSource() == listUserButton) {
                showListUserPanel();
                // showFriendRequestPanel();
            }

            if (e.getSource() == backToMeButton) {
                loadInfo(myProfile);
                updateUI();
            }

            if (e.getSource() == profileCancelButton) {
                loadInfo(myProfile);
                updateUI();
            }

            if (e.getSource() == profileSaveButton) {

            }

        }
    };

    public int userLogin(String username, String password) {
        String loginRequest = String.format("Req1: %s: %s", username, password);
        String loginResponse;
        
        loginResponse = sendRequest(loginRequest);
        if (rHandler(loginResponse)[0].equals("Res1")) {
            JOptionPane.showMessageDialog(null, "Login Successfully", "User Login", JOptionPane.INFORMATION_MESSAGE);
            return 1;
        } else if (rHandler(loginResponse)[0].equals("E1")) {
            JOptionPane.showMessageDialog(null, "Login Failed", "User Login", JOptionPane.INFORMATION_MESSAGE);
        }

        return 0;
        
    }

    public int userRegister(String username, String password) {
        String registerRequest = String.format("Req2: %s: %s", username, password);
        String registerResponse;
        
        registerResponse = sendRequest(registerRequest);
        if (rHandler(registerResponse)[0].equals("Res2")) {
            JOptionPane.showMessageDialog(null, "Registered Successfully!", "User Login", JOptionPane.INFORMATION_MESSAGE);
            return 1;
        } else if (rHandler(registerResponse)[0].equals("E2")) {
            JOptionPane.showMessageDialog(null, "The username already exists.", "User Login", JOptionPane.INFORMATION_MESSAGE);
        }

        return 0;
        
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new ProfileClient());
    }

    public void run() {
        initializeNetwork();
        
        showLoginPanel();
    }

    private void showLoginPanel() {
        loginFrame = new JFrame("User Login");
        JPanel panel = new JPanel();

        loginFrame.setSize(300, 200);
        loginFrame.setResizable(false);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 30, 80, 25);

        userText = new JTextField(15);
        userText.setBounds(110, 30, 165, 25);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 60, 80, 25);

        passwordText = new JPasswordField(15);
        passwordText.setBounds(110, 60, 165, 25);

        loginButton = new JButton("Login");
        loginButton.setBounds(30, 110, 90, 25);
        loginButton.addActionListener(actionListener);

        registerButton = new JButton("Register");
        registerButton.setBounds(170, 110, 90, 25);
        registerButton.addActionListener(actionListener);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(loginButton);
        panel.add(registerButton);

    }

    private void showRegisterPanel() {
        registerFrame = new JFrame("Register");
        JPanel panel = new JPanel();

        registerFrame.setSize(300, 200);
        registerFrame.setResizable(false);
        registerFrame.setLocationRelativeTo(null);
        registerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerFrame.setVisible(true);
        registerFrame.add(panel);

        panel.setLayout(null);

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 30, 80, 25);

        userText = new JTextField(15);
        userText.setBounds(110, 30, 165, 25);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 60, 80, 25);

        passwordText = new JPasswordField(15);
        passwordText.setBounds(110, 60, 165, 25);

        registerButton2 = new JButton("Register");
        registerButton2.setBounds(30, 110, 90, 25);
        registerButton2.addActionListener(actionListener);

        registerCancelButton = new JButton("Cancel");
        registerCancelButton.setBounds(170, 110, 90, 25);
        registerCancelButton.addActionListener(actionListener);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(registerButton2);
        panel.add(registerCancelButton);
    }

    

    private void showMainPanel() {
        mainFrame = new JFrame();
        JPanel panel = new JPanel();

        // mainFrame.setLocationRelativeTo(null);
        mainFrame.setSize(900, 700);
        mainFrame.setVisible(true);
        mainFrame.setTitle("Profile!");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainFrame.setContentPane(panel);
        panel.setLayout(null);
        
        upperLeftPanel = new JPanel();
		upperLeftPanel.setBounds(10, 10, 285, 130);
		panel.add(upperLeftPanel);
		upperLeftPanel.setLayout(null);
		
		myNameLabel = new JLabel("Ziyang Huang");
		myNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
		myNameLabel.setBounds(73, 10, 177, 40);
		upperLeftPanel.add(myNameLabel);
		
		lowerLeftPanel = new JPanel();
		lowerLeftPanel.setBounds(10, 550, 285, 111);
		panel.add(lowerLeftPanel);
		lowerLeftPanel.setLayout(null);
		
		listUserButton = new JButton("Find Friend");
		listUserButton.setFont(new Font("Arial", Font.BOLD, 12));
		listUserButton.setBounds(145, 10, 95, 30);
		lowerLeftPanel.add(listUserButton);
		
		backToMeButton = new JButton("My Profile");
		backToMeButton.setFont(new Font("Arial", Font.BOLD, 12));
        backToMeButton.setBounds(29, 10, 95, 30);
        backToMeButton.addActionListener(actionListener);
		lowerLeftPanel.add(backToMeButton);
		
		friendListScrollPanel = new JScrollPane();
		friendListScrollPanel.setBounds(10, 149, 285, 391);
		panel.add(friendListScrollPanel);
		
		friendListPanel = new JPanel();
		friendListPanel.setPreferredSize(new Dimension(285, 0));
		friendListScrollPanel.setViewportView(friendListPanel);
		friendListPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		profilePanel = new JPanel();
		profilePanel.setBounds(305, 70, 579, 500);
		panel.add(profilePanel);
		profilePanel.setLayout(null);
		
		profileNameLabel = new JLabel("Name: ");
		profileNameLabel.setBounds(10, 29, 54, 15);
		profilePanel.add(profileNameLabel);
		
		profileEmailLabel = new JLabel("Email: ");
		profileEmailLabel.setBounds(10, 68, 54, 15);
		profilePanel.add(profileEmailLabel);
		
		profileAboutMeLabel = new JLabel("About Me: ");
		profileAboutMeLabel.setBounds(10, 110, 80, 15);
		profilePanel.add(profileAboutMeLabel);
		
		profileNameText = new JTextField();
		profileNameText.setBounds(90, 26, 120, 21);
		profilePanel.add(profileNameText);
		profileNameText.setColumns(10);
		
		profileEmailText = new JTextField();
		profileEmailText.setBounds(90, 65, 200, 21);
		profilePanel.add(profileEmailText);
		profileEmailText.setColumns(10);
		
		profileAboutMeArea = new JTextArea();
		profileAboutMeArea.setBounds(20, 132, 535, 100);
		profilePanel.add(profileAboutMeArea);
		
		profileInterestsLabel = new JLabel("Likes & Interests: ");
		profileInterestsLabel.setBounds(10, 277, 163, 15);
		profilePanel.add(profileInterestsLabel);
		
		profileLikesAndInterestsText = new JTextField();
		profileLikesAndInterestsText.setBounds(24, 302, 531, 21);
		profilePanel.add(profileLikesAndInterestsText);
		profileLikesAndInterestsText.setColumns(10);
		
		profileAddFriendButton = new JButton("Add Friend");
        profileAddFriendButton.setBounds(462, 443, 93, 23);
        profileAddFriendButton.addActionListener(actionListener);
		profilePanel.add(profileAddFriendButton);
		
		lowerRightPanel = new JPanel();
		lowerRightPanel.setBounds(305, 580, 579, 81);
		panel.add(lowerRightPanel);
		lowerRightPanel.setLayout(null);
		
		profileCancelButton = new JButton("Cancel");
        profileCancelButton.setBounds(476, 32, 93, 23);
        profileCancelButton.addActionListener(actionListener);
		lowerRightPanel.add(profileCancelButton);
		
		profileSaveButton = new JButton("Save");
        profileSaveButton.setBounds(362, 32, 93, 23);
        profileSaveButton.addActionListener(actionListener);
		lowerRightPanel.add(profileSaveButton);


        
        
    }


    private void showListUserPanel() {
        listAllUsFrame = new JFrame();
        JPanel panel = new JPanel();

        listAllUsFrame.setLocationRelativeTo(null);
        listAllUsFrame.setSize(300, 500);
        listAllUsFrame.setVisible(true);
        listAllUsFrame.setTitle("List All Users");
        listAllUsFrame.setResizable(false);
        listAllUsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        
    }

    private void showFriendRequestPanel() {
        FriendRequest friendRequest = new FriendRequest();
        friendRequest.listUsersFriendRequest("username");
    }

    private void initializeNetwork() {
        String initializationRequest = "Req0: Initialization";
        String initializationResponse;
        try {
            socket = new Socket(hostName, portNumber);
            writer = new PrintWriter(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            initializationResponse = sendRequest(initializationRequest);
            if (rHandler(initializationResponse)[0].equals("E0")) {
                throw new IOException();
            } else if (rHandler(initializationResponse)[0].equals("Res0")) {
                JOptionPane.showMessageDialog(null, "Successfully connected to the server!", "Connection Established", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Connecting to the server failed. Please check you internet connection", "Connection Failed", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        
    }

    private String sendRequest(String request) {
        String response;
        try {

            writer.println(request);
            writer.flush();

            response = reader.readLine();

        } catch (UnknownHostException e) {
            response = "E0: Unknown Host";
            e.printStackTrace();
        } catch (IOException e) {
            response = "E0: Connection Failed";
            e.printStackTrace();
        }
        return response;
    }

    private String[] rHandler(String r) {
        return r.split(": ");
        // [Req1: username: password] -> []
    }

    private void loadInfo(Profile profile) {
        currentProfile = profileA;
        profileNameText.setText(profileA.getName());
        profileEmailText.setText(profileA.getEmail());
        profileLikesAndInterestsText.setText(profileA.getLikesAndInterests().toString());
    }
    
    Account accountA = new Account("unA12345", "pwA12345");
    String name = "zyh";
    String email = "123123123@gmail.com";
    String aboutMe = "qwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwerqwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwerqwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwerqwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwer";
    ArrayList<String> likesAndInterests= new ArrayList<>();


    ArrayList<String> friendUserNames = new ArrayList<>();
    


    Profile profileA = new Profile(name, accountA, email, aboutMe, likesAndInterests, friendUserNames);
    final Profile myProfile = profileA;
}