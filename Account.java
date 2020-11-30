import java.io.Serializable;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;
// import java.io.*;
// import java.nio.file.Files;
// import java.nio.file.NoSuchFileException;
// import java.nio.file.Paths;
// import java.nio.file.StandardOpenOption;
// import java.util.HashMap;
// import java.util.ArrayList;

/**
 * PJ05 Option 2 - Account Class
 *
 * @author Kyochul Jang, Gilbert Hsu
 * @version November 23, 2020
 */

public class Account implements Serializable {
    private String username;
    private String password;
    // JTextField usernameText;
    // JTextField passwordText;
    // JButton create;
    // HashMap<String, String> accountsData;
    // ArrayList<Account> allUserAccounts;

    /**
     *
     */
    private static final long serialVersionUID = 6666842209341941741L;
    // public class Account extends JComponent implements Serializable

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public Account(String username) {
        this.username = username;
    }

    // retrieves user's password
    public String getPassword() {
        return password;
    }

    // retrieves user's username
    public String getUsername() {
        return username;
    }

    // creates an account with the given user username and password

    // /**
    //  * A empty constructor, just used for calling method
    //  */
    // public Account() {}

    // public void createAccount() {
    //     accountsData = new HashMap<String, String>();
    //     allUserAccounts = new ArrayList<Account>();
    //     JOptionPane.showMessageDialog(null, "You are going to create your account!", "Profile app",
    //             JOptionPane.INFORMATION_MESSAGE);
    //     JPanel panel = new JPanel();
    //     JFrame frame = new JFrame("Profile app");
    //     frame.setSize(300, 150);
    //     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     frame.add(panel);
    //     frame.setLocation(500,250);

    //     JLabel usernameLabel = new JLabel("username");
    //     panel.add(usernameLabel);

    //     usernameText = new JTextField(20);
    //     panel.add(usernameText);

    //     JLabel passwordLabel = new JLabel("password");
    //     panel.add(passwordLabel);

    //     passwordText = new JTextField(20);
    //     panel.add(passwordText);

    //     create = new JButton("sing up");
    //     panel.add(create);

    //     frame.setVisible(true);
    //     /**
    //      * when user click sign up, this method will read a file of all data of username and password
    //      * and put them in both hashmap and arraylist.
    //      * hashmap is used to check if there is duplicate username
    //      * arraylist is used to save new data in file
    //      */
    //     create.addActionListener(new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             if (new File("Data.txt").exists()) { //read file
    //                 StringBuilder sb = new StringBuilder();
    //                 try {
    //                     FileReader fr = new FileReader("Data.txt");
    //                     BufferedReader bfr = new BufferedReader(fr);
    //                     String line = bfr.readLine();
    //                     while (line != null) {
    //                         allUserAccounts.add(new Account(line.split(" ")[0],
    //                                 line.split(" ")[1]));
    //                         accountsData.put(line.split(" ")[0],
    //                                 line.split(" ")[1]);
    //                         line = bfr.readLine();
    //                     }
    //                     bfr.close();
    //                 } catch (IOException i) {
    //                     i.printStackTrace();
    //                 }
    //             }
    //             if (e.getSource() == create ) { // check if username is unique and the format is alphanumeric
    //                 if (!accountsData.containsKey(usernameText.getText())
    //                         && usernameText.getText().matches("^[a-zA-Z0-9]*$")
    //                         && passwordText.getText().length() >= 8  //also, password need to be valid
    //                         && passwordText.getText().matches("^[a-zA-Z0-9]*$")) {
    //                     accountsData.put(usernameText.getText(), passwordText.getText());
    //                     allUserAccounts.add(new Account(usernameText.getText(),
    //                             passwordText.getText()));
    //                     if (!new File("Data.txt").exists()) {
    //                         File toWrite = new File("Data.txt"); //if file is not existed
    //                         //create it, write new account in file
    //                         try (BufferedWriter bw = new BufferedWriter(new FileWriter(toWrite))) {
    //                             StringBuilder sb = new StringBuilder();
    //                             sb.append(allUserAccounts.get(allUserAccounts.size() - 1).username + " "
    //                                     + allUserAccounts.get(allUserAccounts.size() - 1).password);
    //                             bw.write(sb.toString());
    //                             bw.newLine();
    //                             bw.flush();
    //                         } catch (IOException n) {
    //                             n.printStackTrace();
    //                             return;
    //                         }
    //                     } else if (new File("Data.txt").exists()) { // if file is existed,
    //                         // just append new account in the file
    //                         try {
    //                             Files.write(Paths.get("Data.txt"), (usernameText.getText()
    //                                     + " " + passwordText.getText() + "\n").getBytes(),
    //                                      StandardOpenOption.APPEND);
    //                         } catch (IOException io ) {
    //                             io.printStackTrace();
    //                         }
    //                     }
    //                     frame.dispose();
    //                     JOptionPane.showMessageDialog(null, "Sign up successfully!", "Profile app",
    //                             JOptionPane.INFORMATION_MESSAGE);

    //                     /**
    //                      * if username format is not valid
    //                      * set the text to blank and show error message
    //                      */
    //                 } else if (!usernameText.getText().matches("^[a-zA-Z0-9]*$")) {
    //                     JOptionPane.showMessageDialog(null, "Your username should be alphanumeric",
    //                             "Profile app", JOptionPane.ERROR_MESSAGE);
    //                     usernameText.setText("");
    //                     passwordText.setText("");
    //                     /**
    //                      * if password format is not valid
    //                      * just set the text of password to blank and show error message
    //                      * keep the username so user doesn't need to type username again
    //                      */
    //                 } else if ( passwordText.getText().length() <= 8
    //                         || !passwordText.getText().matches("^[a-zA-Z0-9]*$")) {
    //                     JOptionPane.showMessageDialog(null,
    //                  "Your password should be alphanumeric. Also, the length can't be fewer than 8 characters.",
    //                             "Profile app", JOptionPane.ERROR_MESSAGE);
    //                     usernameText.setText(usernameText.getText());
    //                     passwordText.setText("");
    //                     /**
    //                      * if username format is duplicate
    //                      * set the text to blank and show error message
    //                      */
    //                 } else if (accountsData.containsKey(usernameText.getText())) {
    //                     JOptionPane.showMessageDialog(null, "This username has been used!",
    //                             "Profile app",
    //                             JOptionPane.ERROR_MESSAGE);
    //                     usernameText.setText("");
    //                     passwordText.setText("");
    //                 }
    //             }
    //         }
    //     });

    // }

}
