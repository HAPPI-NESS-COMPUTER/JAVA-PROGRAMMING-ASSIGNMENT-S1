/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;

import com.mycompany.registrations2.Message;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testCheckMessageLengthSuccess() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testCheckRecipientCellSuccess() {
        Message msg = new Message(0, "+27718693002", "Valid text");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testCheckRecipientCellFailure() {
        Message msg = new Message(0, "08575975889", "Valid text");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    @Test
    public void testSentMessageOptions() {
        Message msg = new Message(0, "+27718693002", "Hi Mike");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testMessageIDLength() {

        Message msg
                = new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        assertTrue(
                msg.checkMessageID()
        );
    }

    @Test
    public void testCreateMessageHash() {

        Message msg
                = new Message(
                        0,
                        "+27718693002",
                        "Hello World"
                );

        String hash
                = msg.createMessageHash();

        assertNotNull(hash);
    }

    @Test
    public void testReturnTotalMessages() {

        Message msg
                = new Message(
                        0,
                        "+27718693002",
                        "Hello"
                );

        msg.sentMessage(1);

        assertTrue(
                msg.returnTotalMessagess() > 0
        );
    }
}
