import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PJ05 Option 2 - ServerTest
 * 
 * Test cases for Server class
 *
 * @author Gilbert Hsu, Kyochul Jang
 * @version November 30, 2020
 */

public class ServerTest {

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

    
    /** 
     * returns the output from the test
     * 
     * @return String
     */
    private String getOutput() {
        return testOut.toString();
    }

    
    /** 
     * checks for the same parameter value in the input
     * 
     * @param str
     */
    @SuppressWarnings("SameParameterValue")
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    String className = "Server";
    Class<?> server = Server.class;
    Method method;

    /**
     * tests Server.java
    */
    @Test
    public void testServer() {
        Class<?> object = Object.class;


        //check if Server class exists or not
        try {
            Object object1 = Class.forName("Server");
        } catch (ClassNotFoundException e) {
            System.out.println("The Server class doesn't exist.");
            Assert.fail();
        }
        //check if Server class inherits Object class or not
        if (object.isAssignableFrom(server)) {
            //success!
        } else {
            System.out.println("The Server class is inheriting wrong super class");
            Assert.fail();
        }
    }

    /**
     * tests all fields in Server.java
    */
    @org.junit.Test
    public void testAllFields() {

        Field serverSocketField;
        Field profilesField;

        //check if serverSocket field exists or not
        try {
            serverSocketField = server.getDeclaredField("serverSocket");
        } catch (NoSuchFieldException e) {
            System.out.println("serverSocket field does not exist.");
            Assert.fail();
            return;
        }
        //check if serverSocket field has correct type or not
        if (serverSocketField.getType().equals(ServerSocket.class)) {
            //success
        } else {
            System.out.println("serverSocket field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int serverSocketModifier = serverSocketField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "serverSocket" +
                        "` field is `private`!", Modifier.isPrivate(serverSocketModifier));

        Assert.assertTrue("Ensure that `" + className + "`'s `" + "serverSocket" +
                        "` field is `final`!", Modifier.isFinal(serverSocketModifier));

        //check if profiles field exists or not
        try {
            profilesField = server.getDeclaredField("profiles");
        } catch (NoSuchFieldException e) {
            System.out.println("profiles field does not exist.");
            Assert.fail();
            return;
        }
        //check if serverSocket field has correct type or not
        if (profilesField.getType().equals(Profile[].class)) {
            //success
        } else {
            System.out.println("profiles field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int profilesModifier = profilesField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "profiles" +
                        "` field is `private`!", Modifier.isPrivate(profilesModifier));

        Assert.assertTrue("Ensure that `" + className + "`'s `" + "profiles" +
                    "` field is `static`!", Modifier.isStatic(profilesModifier));


    }

    
    /**
     * tests getters and setters
     * tests getProfiles()
     * 
     * @throws NoSuchFieldException
     */
    @Test
    void getProfiles() throws NoSuchFieldException {

        Field field = server.getDeclaredField("profiles");
        field.setAccessible(true);

        String methodName = "getProfiles";

        // Attempt to access the class method
        try {
            method = server.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has no parameter or does not exist!");
            return;
        } //end try catch

        Class<?> expectedReturnType = method.getReturnType();
        Class<?> actualReturnType = Profile[].class;

        // Perform tests
        int modifiers = method.getModifiers();

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                    "` method is `public`!", Modifier.isPublic(modifiers));

        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName +
                    "` method has the correct return type!", expectedReturnType, actualReturnType);

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                    "` method is `static`!", Modifier.isStatic(modifiers));

    }

    
    /**
     * test setProfiles()
     * 
     * @throws NoSuchFieldException
     */
    @Test
    void setProfiles() throws NoSuchFieldException {

        Field field = server.getDeclaredField("profiles");
        field.setAccessible(true);

        String methodName = "setProfiles";

        // Attempt to access the class method
        try {
            method = server.getDeclaredMethod(methodName, Profile[].class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has no parameter or does not exist!");
            return;
        } //end try catch

        Class<?> expectedReturnType = method.getReturnType();
        Class<?> actualReturnType = Void.TYPE;

        // Perform tests
        int modifiers = method.getModifiers();

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                        "` method is `public`!", Modifier.isPublic(modifiers));

        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName +
                        "` method has the correct return type!", expectedReturnType, actualReturnType);

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                        "` method is `static`!", Modifier.isStatic(modifiers));

    }
}
