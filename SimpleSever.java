import java.net.*;
import java.io.*;

public class SimpleSever {
    public static void main(String[] args) {
        ServerSocket serverSocket;
        Socket socket;
        BufferedReader reader;
        PrintWriter writer;

        try {
            serverSocket = new ServerSocket(4242);
            System.out.println("Waiting for the client to connect...");
            socket = serverSocket.accept();
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream()); 
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
         
        System.out.println("Client connected!");

        try {
            while (true) {
                String request;
                String response;

                request = reader.readLine();

                if (request == null) {
                    continue;
                }

                System.out.println("---------------------------------------------------------");
                System.out.println("Request: " + request);

                response = "Res0: Connection Established";

                writer.println(response);
                writer.flush();
                System.out.println("Response: " + response);
                    
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
