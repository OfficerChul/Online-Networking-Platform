import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

public class ServerRequestHandlerTest {

    private final PrintStream originalOutput = System.out;
    private final InputStream originalSysIn = System.in;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;

    @Before
    public void outputStart() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @After
    public void restoreInputAndOutput() {
        System.setIn(originalSysIn);
        System.setOut(originalOutput);
    }

    private String getOutput() {
        return testOut.toString();
    }

    @SuppressWarnings("SameParameterValue")
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    String className = "ServerRequestHandler";
    Class<?> serverRequestHandler = ServerRequestHandler.class;
    //test class
    @org.junit.Test
    public void testServerRequestHandler() {
        Class<?> object = Object.class;


        //check if serverRequestHandler class exists or not
        try {
            Object object1 = Class.forName("ServerRequestHandler");
        } catch (ClassNotFoundException e) {
            System.out.println("The ServerRequestHandler class doesn't exist.");
            Assert.fail();
        }
        //check if serverRequestHandler class inherits Object class or not
        if (object.isAssignableFrom(serverRequestHandler)) {
            //success!
        } else {
            System.out.println("The ServerRequestHandler class is inheriting wrong super class");
            Assert.fail();
        }
    }

    //test fields
    @org.junit.Test
    public void testAllFields() {

        Field clientSocketField;
        Field profilesField;

        //check if clientSocket field exists or not
        try {
            clientSocketField = serverRequestHandler.getDeclaredField("clientSocket");
        } catch (NoSuchFieldException e) {
            System.out.println("clientSocket field does not exist.");
            Assert.fail();
            return;
        }
        //check if clientSocket field has correct type or not
        if (clientSocketField.getType().equals(Socket.class)) {
            //success
        } else {
            System.out.println("clientSocket field has wrong type");
            Assert.fail();
        }
        //check if the field is private and final
        int clientSocketModifier = clientSocketField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "clientSocket" + "` field is `private`!", Modifier.isPrivate(clientSocketModifier));

        Assert.assertTrue("Ensure that `" + className + "`'s `" + "clientSocket" + "` field is `final`!", Modifier.isFinal(clientSocketModifier));

        //check if profiles field exists or not
        try {
            profilesField = serverRequestHandler.getDeclaredField("profiles");
        } catch (NoSuchFieldException e) {
            System.out.println("profiles field does not exist.");
            Assert.fail();
            return;
        }
        //check if profiles field has correct type or not
        if (profilesField.getType().equals(Profile[].class)) {
            //success
        } else {
            System.out.println("profiles field has wrong type");
            Assert.fail();
        }
        //check if the field is private and final
        int profilesModifier = profilesField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "profiles" + "` field is `private`!", Modifier.isPrivate(profilesModifier));
    }

//    @Test
//    void run() {
//
//
//
//    }
}
