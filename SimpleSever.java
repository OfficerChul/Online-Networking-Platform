import java.net.*;
import java.io.*;

public class SimpleSever {
    public static void main(String[] args) {
        while (true) {
            try {
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

                        response = getResponse(rHandler(request));

                        writer.println(response);
                        writer.flush();
                        System.out.println("Response: " + response);

                    }
                } catch (Exception e) {
                    reader.close();
                    writer.close();
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

    private static String[] rHandler(String r) {
        return r.split(": ");
    }

    private static String getResponse(String[] rArray) {
        switch (rArray[0]) {
            case "Req0":
                return "Res0: Connection Established";


            case "Req1":
                return "Res1: <Profile Object>";

            case "Req2":
                return "Res2: Registration success";


            case "Req3":
                return "Res3: <Profile Object>";


            case "Req4":
                return "Res4: Success";


            case "Req5":
                return "Res5: Successfully Modified";


            case "Req6":
                return "Res6: Request Sent";


            case "Req7":
                return "Res7: Request Accepted";

            case "Req8":
                return "Res8: Request Refused";

//            case "Req9":
//                return "Res9: Request Accepted";
//
//            case "Req10":
//                return "Res10: Request Accepted";
//
//            case "Req11":
//                return "Res11: Request Accepted";
//
//            case "Req12":
//                return "Res12: Request Accepted";
//
//            case "Req13":
//                return "Res13: Request Accepted";
            
        }
        return null;
    }
}
