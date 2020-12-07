import java.io.*;
import java.net.ServerSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * PJ05 Option 2 - Server
 * 
 * Server used to allow multiple clients to be connected synchronously.
 *
 * @author Eashan Dubey
 * @version November 22, 2020
 */

public final class Server {
    private final ServerSocket serverSocket;
    private static Profile[] profiles; // data of all the profiles

    /**
     * Server Constructor
     *
     * if it exists, reads all profiles from serverData file, and instantiates Profile array
     * 
     * @param port
     * @throws IOException
     */
    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        File serverDataFile = new File("serverData.txt");
        if (serverDataFile.exists()) {
            readProfilesFromFile("serverData.txt");
        } else {
            profiles = new Profile[0];
        }
    } // server constructor

    /**
     * Allow server to serve multiple clients simultaneously,
     * by starting a new ServerRequestHandler thread for each client in
     * an endless loop until server is closed.
     */
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
        } // end try catch

        hostName = address.getCanonicalHostName();
        port = this.serverSocket.getLocalPort();
        System.out.printf("<Host Name: %s, Port: %d>%n", hostName, port);
        System.out.println("<Now serving clients...>");

        while (true) {
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                break;
            } // end try catch
            handler = new ServerRequestHandler(clientSocket);
            handlerThread = new Thread(handler);
            handlerThread.start();
        } // end while
    } // serveClients

    /**
     * Invokes constructor and instantiates a serversocket object
     * invokes serveclients so clients can connect to server
     * and requests can be hendled
     *
     * @param args
     */
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(6868);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } // end try catch

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                closeServer();

                System.out.println("Shutting down...");
            }
        }); // save server data when server is interrupted
        server.serveClients();
    } // main

    /**
     * Write all the Profiles array data back to the file
     */
    private static void closeServer() {
        writeProfilesToFile("serverData.txt");
    }

    /**
     * Write all the Profiles array data back to the file
     * 
     * @param filename
     */
    public static void writeProfilesToFile(String filename) {
        File f = new File(filename);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (int i = 0; i < profiles.length; i++) {
                oos.writeUnshared(profiles[i]);
            }
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads profiles from server file upon call
     * 
     * @param filename
     */
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
            oos.close();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }

    /**
     * Accessor method for profiles array
     * 
     * @return Profile[]
     */
    public static Profile[] getProfiles() {
        return profiles;
    }

    /**
     * Modifier method for profiles array
     *
     * @param profiles
     */
    public static void setProfiles(Profile[] profiles) {
        Server.profiles = profiles;
    }
}
