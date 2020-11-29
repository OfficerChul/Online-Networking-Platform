import java.net.*;
// import java.util.ArrayList;
import java.io.*;

public class SimpleSever {
    public static void main(String[] args) {
        while (true) {
            try {
                ServerSocket serverSocket;
                Socket socket;
                
                ObjectOutputStream oos;
                ObjectInputStream ois;

                // BufferedReader reader;
                // PrintWriter writer;

                try {
                    serverSocket = new ServerSocket(6868);
                    System.out.println("Waiting for the client to connect...");
                    socket = serverSocket.accept();
                    
                    ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
                    oos = new ObjectOutputStream(socket.getOutputStream());
                    

                    // reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // writer = new PrintWriter(socket.getOutputStream()); 
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
                
                System.out.println("Client connected!");

                try {
                    while (true) {
                        Object request;
                        Object response;

                        // request = reader.readLine();
                        request = ois.readObject();

                        if (request == null) {
                            continue;
                        }

                        System.out.println("---------------------------------------------------------");
                        System.out.println("Request: " + request);

                        
                        response = getResponse(request);
                        
                        oos.writeObject(response);
                        oos.flush();
                        // writer.println(response);
                        // writer.flush();
                        System.out.println("Response: " + response);

                    }
                } catch (Exception e) {
                    // reader.close();
                    // writer.close();

                    oos.close();
                    ois.close();

                    socket.close();
                    serverSocket.close();
                    System.out.println("Connection Lost");
                    System.out.println("---------------------------------------------------------");
                    continue;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    // private static String[] rHandler(String r) {
    //     return r.split(": ");
    // }

    
    
    private static Object getResponse(Object obj) {

        Account accountA = new Account("unA12345", "pwA12345");
        String name = "zyh";
        String email = "123123123@gmail.com";
        String aboutMe = "qwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwerqwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwerqwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwerqwerqwerqwerwqerwqerqwerwqerqwerqwerwqerwqerwer";
        String likesAndInterests = "";


        String[] friendUserNames = new String[0];
        


        Profile profileA = new Profile(name, accountA, email, aboutMe, likesAndInterests, friendUserNames);
        final Profile myProfile = profileA;

        if (obj instanceof String) {
            String[] rArray = ((String) obj).split(": ");
            switch (rArray[0]) {
                case "Req0":
                    return "Res0: Connection Established";
    
                case "Req1":
                    return profileA;
    
                case "Req2":
                    return "Res2: Registration success";
    
    
                case "Req3":
                    return profileA;
    
    
                case "Req4":
                    return "Res4: Success";
    
    
                // case "Req5":
                //     return "Res5: Successfully Modified";
    
    
                case "Req6":
                    return "Res6: Request Sent";
    
    
                case "Req7":
                    return "Res7: Request Accepted";
    
                case "Req8":
                    return "Res8: Request Refused";
                
            }
        } else {
            System.out.println(((Profile) obj).toString());
            return "Res5: Successfully Modified";
        }
        
        
        return null;
    }
}
