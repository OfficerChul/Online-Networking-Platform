import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class App extends JComponent implements Runnable {
    Boolean loggedIn = false;
    Socket socket;
    String hostName = "localhost";
    int portNumber = 4242;

    JFrame loginFrame;
    JFrame registerFrame;
    JFrame mainFrame;

    BufferedReader reader;
    PrintWriter writer;

    JLabel userLabel;
    JLabel passwordLabel;
    JTextField userText;
    JPasswordField passwordText;
    JButton loginButton;
    JButton registerButton;
    JButton registerButton2;

    App app;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                String username = userText.getText();
                String password = passwordText.getPassword().toString();
                if (userLogin(username, password) == 1) {
                    loginFrame.dispose();
                    loggedIn = true;
                    showMainPanel();
                }
            }

            if (e.getSource() == registerButton) {
                showRegisterPanel();
            }

            if (e.getSource() == registerButton2) {
                String username = userText.getText();
                String password = passwordText.getPassword().toString();
                if (userRegister(username, password) == 1) {
                    registerFrame.dispose();
                }
            }

        }
    };

    public int userLogin(String username, String password) {
        String loginRequest = String.format("Req1: %s: %s", username, password);
        String loginResponse;
        
        loginResponse = sendRequest(loginRequest);
        if (rHandler(loginResponse)[0].equals("Res0")) {
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
        if (rHandler(registerResponse)[0].equals("Res0")) {
            JOptionPane.showMessageDialog(null, "Register Successfully", "User Login", JOptionPane.INFORMATION_MESSAGE);
            return 1;
        } else if (rHandler(registerResponse)[0].equals("E2")) {
            JOptionPane.showMessageDialog(null, "Register Failed", "User Login", JOptionPane.INFORMATION_MESSAGE);
        }

        return 0;
        
    }

    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new App());
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
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        loginButton.setBounds(30, 110, 80, 25);
        loginButton.addActionListener(actionListener);

        registerButton = new JButton("Register");
        registerButton.setBounds(150, 110, 80, 25);
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
        registerButton2.setBounds(30, 110, 80, 25);
        registerButton2.addActionListener(actionListener);

        panel.add(userLabel);
        panel.add(userText);
        panel.add(passwordLabel);
        panel.add(passwordText);
        panel.add(registerButton2);
    }

    private void showMainPanel() {
        mainFrame = new JFrame();
        JPanel panel = new JPanel();

		mainFrame.setVisible(true);
        mainFrame.setTitle("Profile!");
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.setBounds(100, 100, 900, 700);
        

		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainFrame.setContentPane(panel);
		panel.setLayout(null);
		
		JPanel myInfoPanel = new JPanel();
		myInfoPanel.setBounds(10, 10, 285, 130);
		panel.add(myInfoPanel);
		myInfoPanel.setLayout(null);
		
		JLabel myNameLabel = new JLabel("Ziyang Huang");
		myNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
		myNameLabel.setBounds(73, 10, 177, 40);
		myInfoPanel.add(myNameLabel);
		
		JPanel toolPanel = new JPanel();
		toolPanel.setBounds(10, 550, 285, 111);
		panel.add(toolPanel);
		toolPanel.setLayout(null);
		
		JButton friendRequestButton = new JButton("Find Friend");
		friendRequestButton.setFont(new Font("Arial", Font.BOLD, 12));
		friendRequestButton.setBounds(10, 10, 95, 30);
		toolPanel.add(friendRequestButton);
		
		JScrollPane friendListPanel = new JScrollPane();
		friendListPanel.setBounds(10, 149, 285, 391);
		panel.add(friendListPanel);
		
		JScrollPane profileDetailPanel = new JScrollPane();
		profileDetailPanel.setBounds(305, 10, 579, 651);
		panel.add(profileDetailPanel);
		
		JPanel panel1 = new JPanel();
		profileDetailPanel.setColumnHeaderView(panel);
		
		JLabel label = new JLabel("New label");
		panel.add(label);
		
		JTextField textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
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
    }
}