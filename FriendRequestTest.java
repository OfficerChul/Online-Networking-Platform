import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

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

    Class<?> object = Object.class;
    Class<?> friendRequest = FriendRequest.class;
    FriendRequest newFriendRequest = new FriendRequest("sentUser","receivedUser");

    //test class
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
    @Test
    void getUsernameWhoReceive() throws NoSuchFieldException {

        String result = newFriendRequest.getUsernameWhoReceive();

        assertEquals("Field wasn't retrieved properly", result, "receivedUser");
    }

    @Test
    void getUsernameWhoSent() {
        String result = newFriendRequest.getUsernameWhoSent();

        assertEquals("Field wasn't retrieved properly", result, "sentUser");
    }

    @Test
    void getStatus() {
        int result = newFriendRequest.getStatus();

        assertEquals("Field wasn't retrieved properly", result, 0);
    }

    @Test
    void setStatus() throws NoSuchFieldException, IllegalAccessException {
        newFriendRequest.setStatus(1);

        final Field field = friendRequest.getDeclaredField("status");
        field.setAccessible(true);
        assertEquals("Fields didn't match", field.get(newFriendRequest), 1);
    }
}