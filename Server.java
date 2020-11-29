import java.io.*;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
// import java.util.ArrayList;
// import java.util.Objects;
// import java.util.Scanner;
import java.util.Arrays;

/**
 * PJ04 Option 2 - Server
 *
 * @author Eashan Dubey
 * @version November 22, 2020
 */
public final class Server {
    private final ServerSocket serverSocket;
    private String[] userNames;
    // private ArrayList<String> userNames;
    private String[] onlineUsers;
    // private ArrayList<String> onlineUsers;
    private static Profile[] profiles;
    // private static ArrayList<Profile> profiles;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        File serverDataFile = new File("serverData.txt");
        if (serverDataFile.exists()) {
            readProfilesFromFile("serverData.txt");
        } else {
            profiles = new Profile[0];
        }

        // read all usernames from username file, instantiates userName arraylist
        // instantiates profiles arraylist
    } //server constructor

    public void serveClients() {
        InetAddress address;
        String hostName;
        int port;
        Socket clientSocket;
        ServerRequestHandler handler;
        Thread handlerThread;

        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return;
        } //end try catch

        hostName = address.getCanonicalHostName();
        port = this.serverSocket.getLocalPort();
        System.out.printf("<Host Name: %s, Port: %d>%n", hostName, port);
        System.out.println("<Now serving clients...>");
        // System.out.println("To close the server: Input a character below");;

        while (true) {
            // Scanner scan = new Scanner(System.in);
            // if (scan.hasNext()) {
            //     CloseServer();
            //     break;
            // }
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                break;
            } //end try catch
            handler = new ServerRequestHandler(clientSocket);
            handlerThread = new Thread(handler);
            handlerThread.start();
        } //end while
    } //serveClients

    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(6868);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } //end try catch
        server.serveClients();
    } //main

    private void closeServer() {
        writeProfilesToFile("serverData.txt");
        // write all the arraylist data back to the files
        // close the server
    }

    public static void writeProfilesToFile(String filename) {
        File f = new File(filename);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < profiles.length; i++) {
                // oos.writeObject(profiles[i]);
                oos.writeUnshared(profiles[i]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readProfilesFromFile(String filename) {
        File f = new File(filename);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            ObjectInputStream oos = new ObjectInputStream(fis);
            Profile current;
            Profile[] newProfiles = new Profile[0];
            while (true) {
                try {
                    current = (Profile) oos.readObject();
                } catch (EOFException e) {
                    break;
                }
                newProfiles = Arrays.copyOf(newProfiles, newProfiles.length + 1);
                newProfiles[newProfiles.length - 1] = current;
            }
            profiles = newProfiles;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    public static Profile[] getProfiles() {
        return profiles;
    }

    public static void setProfiles(Profile[] profiles) {
        Server.profiles = profiles;
    }
}
