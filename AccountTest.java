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

/**
 * PJ05 Option 2 - AccountTest
 *
 * @author Gilbert Hsu, Kyochul Jang
 * @version November 30, 2020
 */

public class AccountTest {

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
     * method to get String output
     *
     * @return String
     */
    private String getOutput() {
        return testOut.toString();
    }

    
    /**
     * method to receive String input
     *
     * @param str
     */
    @SuppressWarnings("SameParameterValue")
    private void receiveInput(String str) {
        testIn = new ByteArrayInputStream(str.getBytes());
        System.setIn(testIn);
    }

    Class<?> object = Object.class;
    Class<?> account = Account.class;
    String className = "Account";
    Method method;
    Account newAccount = new Account("abc", "12345678");

    /**
     * test Account class
     */
    @Test
    public void testAccount() {
        //check if Account class exists or not
        try {
            Object object1 = Class.forName("Account");
        } catch (ClassNotFoundException e) {
            System.out.println("The class doesn't exist.");
            Assert.fail();
        }
        //check if Account class inherits Object class or not
        if (object.isAssignableFrom(account)) {
            //success!
        } else {
            System.out.println("The class is inheriting wrong super class");
            Assert.fail();
        }
    }

    /**
     * test all of the fields in Account class
     */
    @Test
    public void testAllFields() {
        //fields in Account class
        Field usernameField;
        Field passwordField;

        //check if username field exists or not
        try {
            usernameField = Account.class.getDeclaredField("username");
        } catch (NoSuchFieldException e) {
            System.out.println("username field does not exist.");
            Assert.fail();
            return;
        }
        //check if username field has correct type or not
        if (usernameField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("username field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int usernameModifier = usernameField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" +
                "password" + "` field is `private`!", Modifier.isPrivate(usernameModifier));

        //check if password field exists or not
        try {
            passwordField = Account.class.getDeclaredField("password");
        } catch (NoSuchFieldException e) {
            System.out.println("password field does not exist.");
            Assert.fail();
            return;
        }
        //check if password field has correct type or not
        if (passwordField.getType().equals(String.class)) {
            //success
        } else {
            System.out.println("password field has wrong type");
            Assert.fail();
        }
        //check if the field is private
        int passwordModifier = passwordField.getModifiers();
        Assert.assertTrue("Ensure that `" + className + "`'s `" + "password" +
                "` field is `private`!", Modifier.isPrivate(passwordModifier));

    }

    
    /**
     * test getPassword()
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */

    @Test
    public void getPassword() throws NoSuchFieldException, IllegalAccessException {

        Field field = account.getDeclaredField("password");
        field.setAccessible(true);

        String result = newAccount.getPassword();

        String methodName = "getPassword";

        // Attempt to access the class method
        try {
            method = account.getDeclaredMethod(methodName);
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

        assertEquals("Field wasn't retrieved properly", result, "12345678");
    }

    
    /**
     * test getUsername()
     *
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */

    @Test
    public void getUsername() throws NoSuchFieldException, IllegalAccessException {
        Field field = account.getDeclaredField("password");
        field.setAccessible(true);

        String result = newAccount.getUsername();

        String methodName = "getUsername";

        // Attempt to access the class method
        try {
            method = account.getDeclaredMethod(methodName);
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

        assertEquals("Field wasn't retrieved properly", result, "abc");
    }
}
