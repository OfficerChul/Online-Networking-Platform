import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PJ05 Option 2 - ServerRequestHandlerTest
 *
 * @author Gilbert Hsu, Kyochul Jang
 * @version November 30, 2020
 */

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
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "clientSocket" +
                "` field is `private`!", Modifier.isPrivate(clientSocketModifier));

        Assert.assertTrue("Ensure that `" + className + "`'s `" + "clientSocket" +
                "` field is `final`!", Modifier.isFinal(clientSocketModifier));

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
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "profiles" +
                "` field is `private`!", Modifier.isPrivate(profilesModifier));
    }

    Class<?> clazz = ServerRequestHandler.class;

    //test serverRequestHandlerConstructorDeclarationTest()
    @org.junit.Test
    public void serverRequestHandlerConstructorDeclarationTest() {
        Constructor<?> constructor;
        int modifiers;

        try {
            constructor = clazz.getDeclaredConstructor(Socket.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className +
                    "` declares a constructor that is `public` and has one parameters of type Socket!");
            return;
        } //end try catch

        modifiers = constructor.getModifiers();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s parameterized constructor is `public`!"
                , Modifier.isPublic(modifiers));
    }

    //test getResponseTest()
    @org.junit.Test
    public void getResponseTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "getResponse";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = Object.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, Object.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has one parameter of type Object!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                "` method is `private`!", Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName +
                "` method is NOT `static`!", Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName +
                "` method has the correct return type!", expectedReturnType, actualReturnType);
    }

    //test runTest()
    @org.junit.Test
    public void runTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "run";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = void.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has no parameters or does not exist!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName
                + "` method is `public`!", Modifier.isPublic(modifiers));
        Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName
                + "` method is NOT `static`!", Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName
                + "` method has the correct return type!", expectedReturnType, actualReturnType);
    }

    //test loginTest()
    @org.junit.Test
    public void loginTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "login";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = boolean.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has two parameters of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName
                + "` method is `private`!", Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `" + className + "`'s `" + methodName
                + "` method is NOT `static`!", Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName
                + "` method has the correct return type!", expectedReturnType, actualReturnType);
    }

    //test returnProfileFromUsernameTest()
    @org.junit.Test
    public void returnProfileFromUsernameTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "returnProfileFromUsername";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = Profile.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has one parameter of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test updateProfileTest()
    @org.junit.Test
    public void updateProfileTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "updateProfile";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = void.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, Profile.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has one parameter of type Profile!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test usernameIsTakenTest()
    @org.junit.Test
    public void usernameIsTakenTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "usernameIsTaken";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = boolean.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has one parameter of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test deleteAccountTest()
    @org.junit.Test
    public void deleteAccountTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "deleteAccount";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = void.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has one parameter of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test acceptFriendRequestTest()
    @org.junit.Test
    public void acceptFriendRequestTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "acceptFriendRequest";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = void.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has two parameters of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test rejectFriendRequestTest()
    @org.junit.Test
    public void rejectFriendRequestTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "rejectFriendRequest";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = void.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has two parameters of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test sendFriendRequestTest()
    @org.junit.Test
    public void sendFriendRequestTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "sendFriendRequest";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = boolean.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has two parameters of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test friendRequestAlreadyExistsTest()
    @org.junit.Test
    public void friendRequestAlreadyExistsTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "friendRequestAlreadyExists";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = boolean.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has two parameters of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }

    //test usersAreFriendsTest()
    @org.junit.Test
    public void usersAreFriendsTest() {
        Method method;
        int modifiers;
        Class<?> actualReturnType;

        // Set the method that you want to test
        String methodName = "usersAreFriends";

        // Set the return type of the method you want to test
        // Use the type + .class
        // For example, String.class or int.class
        Class<?> expectedReturnType = String.class;

        // Attempt to access the class method
        try {
            method = clazz.getDeclaredMethod(methodName, String.class, String.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has two parameters of type String!");
            return;
        } //end try catch
        // Perform tests

        modifiers = method.getModifiers();
        actualReturnType = method.getReturnType();
        Assert.assertTrue("Ensure that `" + className
                        + "`'s `" + methodName + "` method is `private`!"
                , Modifier.isPrivate(modifiers));
        Assert.assertFalse("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method is NOT `static`!"
                , Modifier.isStatic(modifiers));
        Assert.assertEquals("Ensure that `"
                        + className + "`'s `" + methodName
                        + "` method has the correct return type!"
                , expectedReturnType, actualReturnType);
    }
}
