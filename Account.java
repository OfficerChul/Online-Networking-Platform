import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.ArrayList;
public class Account extends JComponent {
    String username;
    String password;
    JTextField usernameText;
    JTextField passwordText;
    JButton create;
    HashMap<String, String> accountsData;
    ArrayList<Account> allUserAccounts;
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Account() {

    }
    public void createAccount() {
        accountsData = new HashMap<String, String>();
        allUserAccounts = new ArrayList<Account>();
        JOptionPane.showMessageDialog(null, "You are going to create your account!", "Profile app",
                JOptionPane.INFORMATION_MESSAGE);
            JPanel panel = new JPanel();
            JFrame frame = new JFrame("Profile app");
            frame.setSize(300, 150);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);

            frame.setLocation(500,250);

            JLabel usernameLabel = new JLabel("username");
            panel.add(usernameLabel);

            usernameText = new JTextField(20);
            panel.add(usernameText);

            JLabel passwordLabel = new JLabel("password");
            panel.add(passwordLabel);

            passwordText = new JTextField(20);
            panel.add(passwordText);

            create = new JButton("sing up");
            panel.add(create);

            frame.setVisible(true);
            create.addActionListener(actionListener);
    }
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (new File("Data.txt").exists()) {
                StringBuilder sb = new StringBuilder();
                try {
                    FileReader fr = new FileReader("Data.txt");
                    BufferedReader bfr = new BufferedReader(fr);
                    String line = bfr.readLine();
                    while (line != null) {
                        allUserAccounts.add(new Account(line.split(" ")[0],
                                line.split(" ")[1]));
                        accountsData.put(line.split(" ")[0],
                                line.split(" ")[1]);
                        line = bfr.readLine();
                    }
                    bfr.close();
                } catch (IOException i) {
                    i.printStackTrace();
                }
            }
            if (e.getSource() == create ) {
                if (!accountsData.containsKey(usernameText.getText())
                        && usernameText.getText().matches("^[a-zA-Z0-9]*$")
                        && passwordText.getText().length() >= 8
                        && passwordText.getText().matches("^[a-zA-Z0-9]*$")) {
                    accountsData.put(usernameText.getText(), passwordText.getText());
                    allUserAccounts.add(new Account(usernameText.getText(),
                            passwordText.getText()));
                    if (!new File("Data.txt").exists()) {
                        File toWrite = new File("Data.txt");
                        try (BufferedWriter bw = new BufferedWriter(new FileWriter(toWrite))) {
                            StringBuilder sb = new StringBuilder();
                            sb.append(allUserAccounts.get(allUserAccounts.size() - 1).username + " "
                                    + allUserAccounts.get(allUserAccounts.size() - 1).password);
                            bw.write(sb.toString());
                            bw.newLine();
                            bw.flush();
                        } catch (IOException n) {
                            n.printStackTrace();
                            return;
                        }
                    } else if (new File("Data.txt").exists()) {
                        try {
                            Files.write(Paths.get("Data.txt"), (usernameText.getText()
                                    + " " + passwordText.getText() + "\n").getBytes(), StandardOpenOption.APPEND);
                        } catch (IOException io ) {
                            io.printStackTrace();
                        }
                    }
                } else if (!usernameText.getText().matches("^[a-zA-Z0-9]*$")) {
                    JOptionPane.showMessageDialog(null, "Your username should be alphanumeric",
                            "Profile app", JOptionPane.ERROR_MESSAGE);
                    usernameText.setText("");
                    passwordText.setText("");
                } else if ( passwordText.getText().length() <= 8
                        || !passwordText.getText().matches("^[a-zA-Z0-9]*$")) {
                    JOptionPane.showMessageDialog(null, "Your password should be alphanumeric. Also, the length can't be fewer than 8 characters.",
                            "Profile app", JOptionPane.ERROR_MESSAGE);
                    usernameText.setText(usernameText.getText());
                    passwordText.setText("");
                } else if (accountsData.containsKey(usernameText.getText())) {
                    JOptionPane.showMessageDialog(null, "This username has been used!",
                            "Profile app",
                            JOptionPane.ERROR_MESSAGE);
                    usernameText.setText("");
                    passwordText.setText("");
                }
            }
        }
    };

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
