import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * PJ05 Option 2 - FriendRequestTest
 *
 * @author Gilbert Hsu, Kyochul Jang
 * @version November 30, 2020
 */

public class FriendRequestTest {

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

    String className = "FriendRequest";
    Method method;
    Class<?> object = Object.class;
    Class<?> friendRequest = FriendRequest.class;
    FriendRequest newFriendRequest = new FriendRequest("sentUser", "receivedUser");

    //test class
    @Test
    public void testFriendRequest() {
        //check if FriendRequest class exists or not
        try {
            Object object1 = Class.forName("FriendRequest");
        } catch (ClassNotFoundException e) {
            System.out.println("The FriendRequest class doesn't exist.");
            Assert.fail();
        }
        //check if FriendRequest class inherits Object class or not
        if (object.isAssignableFrom(friendRequest)) {
            //success!
        } else {
            System.out.println("The FriendRequest class is inheriting wrong super class");
            Assert.fail();
        }
    }

    //test fields
    @Test
    public void testAllFields() {
        //fields in FriendRequest class
        Field usernameWhoSentField;
        Field usernameWhoReceiveField;
        Field statusField;

        //check if usernameWhoSent field exists or not
        try {
            usernameWhoSentField = FriendRequest.class.getDeclaredField("usernameWhoSent");
        } catch (NoSuchFieldException e) {
            System.out.println("usernameWhoSent field does not exist.");
            Assert.fail();
            return;
        }
        //check if usernameWhoSent field has correct type or not
        if (usernameWhoSentField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("usernameWhoSent field has wrong type");
            Assert.fail();
        }

        //check if usernameWhoReceive field exists or not
        try {
            usernameWhoReceiveField = FriendRequest.class.getDeclaredField("usernameWhoReceive");
        } catch (NoSuchFieldException e) {
            System.out.println("usernameWhoReceive field does not exist.");
            Assert.fail();
            return;
        }
        //check if usernameWhoReceive field has correct type or not
        if (usernameWhoReceiveField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("usernameWhoReceive field has wrong type");
            Assert.fail();
        }

        //check if status field exists or not
        try {
            statusField = FriendRequest.class.getDeclaredField("status");
        } catch (NoSuchFieldException e) {
            System.out.println("status field does not exist.");
            Assert.fail();
            return;
        }
        //check if status field has correct type or not
        if (statusField.getType().equals(int.class)) {
            //success
        } else {
            System.out.println("status field has wrong type");
            Assert.fail();
        }

    }

    //test getters and setters
    //test getUsernameWhoReceive()
    @Test
    public void getUsernameWhoReceive() throws NoSuchFieldException {

        String result = newFriendRequest.getUsernameWhoReceive();

        String methodName = "getUsernameWhoReceive";

        // Attempt to access the class method
        try {
            method = friendRequest.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has no parameter or does not exist!");
            return;
        } //end try catch

        Class<?> expectedReturnType = method.getReturnType();
        Class<?> actualReturnType = String.class;

        // Perform tests
        int modifiers = method.getModifiers();

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                "` method is `public`!", Modifier.isPublic(modifiers));

        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName +
                "` method has the correct return type!", expectedReturnType, actualReturnType);

        assertEquals("Field wasn't retrieved properly", result, "receivedUser");
    }
    
    //test getUsernameWhoSent()
    @Test
    public void getUsernameWhoSent() {
        String result = newFriendRequest.getUsernameWhoSent();

        String methodName = "getUsernameWhoSent";

        // Attempt to access the class method
        try {
            method = friendRequest.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has no parameter  or does not exist!");
            return;
        } //end try catch

        Class<?> expectedReturnType = method.getReturnType();
        Class<?> actualReturnType = String.class;

        // Perform tests
        int modifiers = method.getModifiers();

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                "` method is `public`!", Modifier.isPublic(modifiers));

        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName +
                "` method has the correct return type!", expectedReturnType, actualReturnType);

        assertEquals("Field wasn't retrieved properly", result, "sentUser");
    }
    
    //test getStatus()
    @Test
    public void getStatus() {
        int result = newFriendRequest.getStatus();

        String methodName = "getStatus";

        // Attempt to access the class method
        try {
            method = friendRequest.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has no parameter  or does not exist!");
            return;
        } //end try catch

        Class<?> expectedReturnType = method.getReturnType();
        Class<?> actualReturnType = int.class;

        // Perform tests
        int modifiers = method.getModifiers();

        Assert.assertTrue("Ensure that `" + className + "`'s `" + methodName +
                "` method is `public`!", Modifier.isPublic(modifiers));

        Assert.assertEquals("Ensure that `" + className + "`'s `" + methodName
                + "` method has the correct return type!", expectedReturnType, actualReturnType);

        assertEquals("Field wasn't retrieved properly", result, 0);
    }

    //test setStatus()
    @Test
    public void setStatus() throws NoSuchFieldException, IllegalAccessException {
        newFriendRequest.setStatus(1);

        final Field field = friendRequest.getDeclaredField("status");
        field.setAccessible(true);

        String methodName = "setStatus";

        // Attempt to access the class method
        try {
            method = friendRequest.getDeclaredMethod(methodName, int.class);
        } catch (NoSuchMethodException e) {
            Assert.fail("Ensure that `" + className + "` declares a method named `" + methodName + "` that" +
                    " has one parameter!");
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

        assertEquals("Fields didn't match", field.get(newFriendRequest), 1);
    }
}
