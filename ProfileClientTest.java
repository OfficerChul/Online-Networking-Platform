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
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PJ05 Option 2 - ProfileClientTest
 *
 * @author Gilbert Hsu, Kyochul Jang
 * @version November 30, 2020
 */

class ProfileClientTest {

    private final PrintStream originalOutput = System.out;
    private final InputStream originalSysIn = System.in;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayInputStream testIn;

    @SuppressWarnings("FieldCanBeLocal")
    private ByteArrayOutputStream testOut;
    private long testA;

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

    String className = "ProfileClient";

    //test class
    @Test
    public void testProfileClient() {
        Class<?> object = Object.class;
        Class<?> profileClient = ProfileClient.class;

        //check if profileClient class exists or not
        try {
            Object object1 = Class.forName("ProfileClient");
        } catch (ClassNotFoundException e) {
            System.out.println("The profileClient class doesn't exist.");
            Assert.fail();
        }
        //check if profileClient class inherits Object class or not
        if (object.isAssignableFrom(profileClient)) {
            //success!
        } else {
            System.out.println("The profileClient class is inheriting wrong super class");
            Assert.fail();
        }
    }

    //test fields
    @Test
    public void testAllFields() throws NoSuchFieldException {
        //fields in ProfileClient class
        Field serialVersionUIDField;
        Field loggedInField;
        Field socketField;
        Field hostNameField;
        Field portNumberField;
        Field myProfileField;

        //check if serialVersionUID field exists or not
        try {
            serialVersionUIDField = ProfileClient.class.getDeclaredField("serialVersionUID");
        } catch (NoSuchFieldException e) {
            System.out.println("serialVersionUID field does not exist.");
            Assert.fail();
            return;
        }
        //check if serialVersionUID field has correct type or not

        int modifiers = serialVersionUIDField.getModifiers();

        long testA = 1L;
        Field a1 = ProfileClientTest.class.getDeclaredField("testA");

        Class<?> expected = a1.getType();
        Class<?> type = serialVersionUIDField.getType();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "serialVersionUID" +
                "` field is `private`!", Modifier.isPrivate(modifiers));

        Assert.assertTrue("Ensure that `" + className + "`'s `" + "serialVersionUID" +
                "` field is `final`!", Modifier.isFinal(modifiers));

        Assert.assertTrue("Ensure that `" + className + "`'s `" + "serialVersionUID" +
                "` field is `static`!", Modifier.isStatic(modifiers));

        Assert.assertEquals("Ensure that `" + className + "`'s `" + "serialVersionUID" +
                "` field is the correct type!", a1.getType(), serialVersionUIDField.getType());


        //check if socket field exists or not
        try {
            socketField = ProfileClient.class.getDeclaredField("socket");
        } catch (NoSuchFieldException e) {
            System.out.println("socket field does not exist.");
            Assert.fail();
            return;
        }
        //check if socket field has correct type or not
        if (socketField.getType().equals(Socket.class)) {
            //success
        } else {
            System.out.println("socket field has wrong type");
            Assert.fail();
        }

        //check if hostName field exists or not
        try {
            hostNameField = ProfileClient.class.getDeclaredField("hostName");
        } catch (NoSuchFieldException e) {
            System.out.println("hostName field does not exist.");
            Assert.fail();
            return;
        }
        //check if hostName field has correct type or not
        if (hostNameField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("hostName field has wrong type");
            Assert.fail();
        }

        //check if portNumber field exists or not
        try {
            portNumberField = ProfileClient.class.getDeclaredField("portNumber");
        } catch (NoSuchFieldException e) {
            System.out.println("portNumber field does not exist.");
            Assert.fail();
            return;
        }
        //check if portNumber field has correct type or not
        if (portNumberField.getType().equals(int.class)) {
            //success
        } else {
            System.out.println("portNumber field has wrong type");
            Assert.fail();
        }

        //check if myProfile field exists or not
        try {
            myProfileField = ProfileClient.class.getDeclaredField("myProfile");
        } catch (NoSuchFieldException e) {
            System.out.println("myProfile field does not exist.");
            Assert.fail();
            return;
        }
        //check if myProfile field has correct type or not
        if (myProfileField.getType().equals(Profile.class)) {
            //success
        } else {
            System.out.println("myProfile field has wrong type");
            Assert.fail();
        }
    }

}
