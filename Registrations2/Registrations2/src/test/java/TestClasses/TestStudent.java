/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;

import com.mycompany.registrations2.Login2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {

    public TestStudent() {
    }

    @Test
    public void testUsernameCorrect() {

        Login2 user = new Login2("Kyle", "Smith");

        boolean actual = user.checkUserName("kyl_1");

        assertTrue(actual,
                "Username should be valid");
    }

    @Test
    public void testUsernameIncorrect() {

        Login2 user = new Login2("Kyle", "Smith");

        boolean actual = user.checkUserName("kyle123");

        assertFalse(actual,
                "Username should be invalid");
    }

    @Test
    public void testPasswordCorrect() {

        Login2 user = new Login2("Kyle", "Smith");

        user.registerUser(
                "kyl_1",
                "Ch&sec@ke99",
                "+27838968976"
        );

        boolean actual
                = user.checkPasswordComplexity("Ch&sec@ke99");

        assertTrue(actual,
                "Password should be valid");
    }

    @Test
    public void testPasswordIncorrect() {

        Login2 user = new Login2("Kyle", "Smith");

        user.registerUser(
                "kyl_1",
                "password",
                "+27838968976"
        );

        boolean actual
                = user.checkPasswordComplexity("password");

        assertFalse(actual,
                "Password should be invalid");
    }

    @Test
    public void testCellPhoneCorrect() {

        Login2 user = new Login2("Kyle", "Smith");

        user.registerUser(
                "kyl_1",
                "Password@1",
                "+27838968976"
        );

        boolean actual
                = user.checkCellPhoneNumber("+27838968976");

        assertTrue(actual,
                "Phone number should be valid");
    }

    @Test
    public void testCellPhoneIncorrect() {

        Login2 user = new Login2("Kyle", "Smith");

        user.registerUser(
                "kyl_1",
                "Password@1",
                "+27838968976"
        );

        boolean actual
                = user.checkCellPhoneNumber("083456789");

        assertFalse(actual,
                "Phone number should be invalid");
    }

    @Test
    public void testLoginSuccess() {

        Login2 user = new Login2("Kyle", "Smith");

        user.registerUser(
                "kyl_1",
                "Ch&sec@ke99",
                "+27838968976"
        );

        String message
                = user.loginUser(
                        "kyl_1",
                        "Ch&sec@ke99"
                );

        assertEquals(
                "Welcome Kyle Smith, it is great to see you again.",
                message
        );
    }

    @Test
    public void testLoginFail() {

        Login2 user = new Login2("Kyle", "Smith");

        user.registerUser(
                "kyl_1",
                "Ch&sec@ke99",
                "+27838968976"
        );

        String message
                = user.loginUser(
                        "wrong",
                        "wrong"
                );

        assertEquals(
                "Username or password incorrect, please try again.",
                message
        );
    }

    @Test
    public void testRegisterUserSuccess() {

        Login2 user = new Login2("Kyle", "Smith");

        String result
                = user.registerUser(
                        "kyl_1",
                        "Password@1",
                        "+27838968976"
                );

        assertEquals(
                "User registered successfully.",
                result
        );
    }

    @Test
    public void testRegisterUserFail() {

        Login2 user = new Login2("Kyle", "Smith");

        String result
                = user.registerUser(
                        "kyle123",
                        "pass",
                        "08345"
                );

        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                result
        );
    }
}
