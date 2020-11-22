import java.net.ServerSocket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * PJ04 Option 2 - Server
 *
 * @author Eashan Dubey
 * @version November 22, 2020
 */
public final class Server {
    private final ServerSocket serverSocket;
    private ArrayList<String> userNames;
    private ArrayList<String> onlineUsers;
    //private ArrayList<Profile> profiles;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        // read all usernames from username file, instantiates userName arraylist
        // instantiates profiles arraylist
    } //PowerServer

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

        while (true) {
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
            server = new Server(0);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } //end try catch
        server.serveClients();
    } //main

    private void CloseServer {
        // write all the arraylist data back to the files
        // close the server
    }
}