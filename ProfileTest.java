import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {

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
    Class<?> profile = Profile.class;
    String[] b = {"a"};
    static Account newAccount = new Account("abc","12345678");
    static FriendRequest newFriendRequest = new FriendRequest("a", "b");
    static FriendRequest[] newFriendRequestArray = {newFriendRequest};
    Profile newProfile = new Profile("Kyochul Jang", newAccount, "email@email.com", "aboutMe", "likesAndInterests", b);
    String className = "Profile";


    //test class
    @Test
    public void testAllClasses() {
        //check if Profile class exists or not
        try {
            Object object1 = Class.forName("Profile");
        } catch (ClassNotFoundException e) {
            System.out.println("The Profile class doesn't exist.");
            Assert.fail();
        }
        //check if Profile class inherits Object class or not
        if (object.isAssignableFrom(profile)) {
            //success!
        } else {
            System.out.println("The Profile class is inheriting wrong super class");
            Assert.fail();
        }
    }

    //test field
    @Test
    public void testAllFields() {
        //fields in Profile class
        Field accountField;
        Field emailField;
        Field aboutMeField;
        Field nameField;
        Field likesAndInterestsField;
        Field friendUserNamesField;
        Field sentFriendRequestsField;
        Field receivedFriendRequestsField;

        //check if account field exists or not
        try {
            accountField = Profile.class.getDeclaredField("account");
        } catch (NoSuchFieldException e) {
            System.out.println("account field does not exist.");
            Assert.fail();
            return;
        }
        //check if account field has correct type or not
        if (accountField.getType().equals(Account.class)) {
            //success
        } else {
            System.out.println("account field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int accountModifier = accountField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "account" + "` field is `private`!", Modifier.isPrivate(accountModifier));

        //check if email field exists or not
        try {
            emailField = Profile.class.getDeclaredField("email");
        } catch (NoSuchFieldException e) {
            System.out.println("email field does not exist.");
            Assert.fail();
            return;
        }
        //check if email field has correct type or not
        if (emailField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("email field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int emailModifier = emailField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "email" + "` field is `private`!", Modifier.isPrivate(emailModifier));

        //check if aboutMe field exists or not
        try {
            aboutMeField = Profile.class.getDeclaredField("aboutMe");
        } catch (NoSuchFieldException e) {
            System.out.println("aboutMe field does not exist.");
            Assert.fail();
            return;
        }
        //check if aboutMe field has correct type or not
        if (aboutMeField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("aboutMe field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int aboutMeModifier = aboutMeField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "aboutMe" + "` field is `private`!", Modifier.isPrivate(aboutMeModifier));

        //check if name field exists or not
        try {
            nameField = Profile.class.getDeclaredField("name");
        } catch (NoSuchFieldException e) {
            System.out.println("name field does not exist.");
            Assert.fail();
            return;
        }
        //check if name field has correct type or not
        if (nameField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("name field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int nameModifier = nameField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "name" + "` field is `private`!", Modifier.isPrivate(nameModifier));

        //check if likesAndInterests field exists or not
        try {
            likesAndInterestsField = Profile.class.getDeclaredField("likesAndInterests");

        } catch (NoSuchFieldException e) {
            System.out.println("likesAndInterests field does not exist.");
            Assert.fail();
            return;
        }
        //check if likesAndInterests field has correct type or not
        if (likesAndInterestsField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("likesAndInterests field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int likesAndInterestsModifier = likesAndInterestsField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "likesAndInterests" + "` field is `private`!", Modifier.isPrivate(likesAndInterestsModifier));

        //check if friendUserNames field exists or not
        try {
            friendUserNamesField = Profile.class.getDeclaredField("friendUserNames");

        } catch (NoSuchFieldException e) {
            System.out.println("friendUserNames field does not exist.");
            Assert.fail();
            return;
        }
        //check if friendUserNames field has correct type or not
        if (friendUserNamesField.getType().equals(String[].class)) {
            //success
        } else {
            System.out.println("friendUserNames field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int friendUserNamesModifier = friendUserNamesField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "friendUserNames" + "` field is `private`!", Modifier.isPrivate(friendUserNamesModifier));

        //check if sentFriendRequests field exists or not
        try {
            sentFriendRequestsField = Profile.class.getDeclaredField("sentFriendRequests");
        } catch (NoSuchFieldException e) {
            System.out.println("sentFriendRequests field does not exist.");
            Assert.fail();
            return;
        }
        //check if sentFriendRequests field has correct type or not
        if (sentFriendRequestsField.getType().equals(FriendRequest[].class)) {
            //success
        } else {
            System.out.println("sentFriendRequests field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int sentFriendRequestsModifier = sentFriendRequestsField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "sentFriendRequests" + "` field is `private`!", Modifier.isPrivate(sentFriendRequestsModifier));

        //check if receivedFriendRequests field exists or not
        try {
            receivedFriendRequestsField = Profile.class.getDeclaredField("receivedFriendRequests");
        } catch (NoSuchFieldException e) {
            System.out.println("receivedFriendRequests field does not exist.");

            Assert.fail();
            return;
        }
        //check if receivedFriendRequests field has correct type or not
        if (receivedFriendRequestsField.getType().equals(FriendRequest[].class)) {
            //success
        } else {
            System.out.println("receivedFriendRequests field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int receivedFriendRequestsModifier = receivedFriendRequestsField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "receivedFriendRequests" + "` field is `private`!", Modifier.isPrivate(receivedFriendRequestsModifier));

    }

    //test getters and setters

    @Test
    void getAccount() throws NoSuchFieldException, IllegalAccessException {

        Account result = newProfile.getAccount();

        assertEquals("Field wasn't retrieved properly", result, newAccount);
    }

    @Test
    void getReceivedFriendRequests() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("receivedFriendRequests");
        field.setAccessible(true);
        field.set(newProfile, newFriendRequestArray);
        FriendRequest[] result = newProfile.getReceivedFriendRequests();

        assertArrayEquals(result, new FriendRequest[]{newFriendRequest}, "Field wasn't retrieved properly");

    }

    @Test
    void getSentFriendRequests() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("sentFriendRequests");
        field.setAccessible(true);
        field.set(newProfile, newFriendRequestArray);
        FriendRequest[] result = newProfile.getSentFriendRequests();

        assertArrayEquals(result, newFriendRequestArray, "Field wasn't retrieved properly");

    }

    @Test
    void getFriendUserNames() {

        String[] result = newProfile.getFriendUserNames();

        assertArrayEquals(result, new String[]{"a"}, "Field wasn't retrieved properly");

    }

    @Test
    void getLikesAndInterests() {

        String result = newProfile.getLikesAndInterests();

        assertEquals("Field wasn't retrieved properly", result, "likesAndInterests");

    }

    @Test
    void getAboutMe() {

        String result = newProfile.getAboutMe();

        assertEquals("Field wasn't retrieved properly", result, "aboutMe");

    }

    @Test
    void getEmail() {

        String result = newProfile.getEmail();

        assertEquals("Field wasn't retrieved properly", result, "email@email.com");

    }

    @Test
    void getName() {

        String result = newProfile.getName();

        assertEquals("Field wasn't retrieved properly", result, "Kyochul Jang");

    }

    @Test
    void setName() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("name");
        field.setAccessible(true);

        newProfile.setName("Yeju Kim");

        assertEquals("Fields didn't match", field.get(newProfile), "Yeju Kim");

    }

    @Test
    void setAboutMe() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("aboutMe");
        field.setAccessible(true);

        newProfile.setAboutMe("myGirlFriend");

        assertEquals("Fields didn't match", field.get(newProfile), "myGirlFriend");

    }

    @Test
    void setAccount() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("account");
        field.setAccessible(true);

        newProfile.setAccount(new Account("Yeju", "girlFriend"));

        assertEquals("Fields didn't match", field.get(newProfile), new Account("Yeju", "girlFriend"));

    }

    @Test
    void setEmail() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("email");
        field.setAccessible(true);

        newProfile.setEmail("Yeju_Kim@email.com");

        assertEquals("Fields didn't match", field.get(newProfile), "Yeju_Kim@email.com");

    }

    @Test
    void setFriendUserNames() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("friendUserNames");
        field.setAccessible(true);

        newProfile.setFriendUserNames(new String[]{"myGirlLOL"});

        assertArrayEquals((Object[]) field.get(newProfile), new String[]{"myGirlLOL"}, "Fields didn't match");


    }

    @Test
    void setLikesAndInterests() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("likesAndInterests");
        field.setAccessible(true);

        newProfile.setLikesAndInterests("ILikeHer");

        assertEquals("Fields didn't match", field.get(newProfile), "ILikeHer");

    }

    @Test
    void setReceivedFriendRequests() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("receivedFriendRequests");
        field.setAccessible(true);

        newProfile.setReceivedFriendRequests(newFriendRequestArray);

        assertEquals("Fields didn't match", field.get(newProfile), newFriendRequestArray);

    }

    @Test
    void setSentFriendRequests() throws NoSuchFieldException, IllegalAccessException {

        Field field = newProfile.getClass().getDeclaredField("sentFriendRequests");
        field.setAccessible(true);

        newProfile.setSentFriendRequests(newFriendRequestArray);

        assertEquals("Fields didn't match", field.get(newProfile), newFriendRequestArray);

    }

//    @Test
//    void addToFriendUsernames() {
//
//
//    }
}
