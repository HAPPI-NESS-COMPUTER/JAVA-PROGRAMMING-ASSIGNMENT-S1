/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package TestClasses;

import com.mycompany.registrations2.MessageManager;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManageMessage {

    @Test
    public void testAddMessageRecord() {
        MessageManager manager = new MessageManager();
        assertEquals(0, manager.getMessageCount());

        manager.addMessageRecord("1234567890", "12:1:HELLO", "+27838968976", "Hello World", "Stored");
        assertEquals(1, manager.getMessageCount());
    }

    @Test
    public void testSearchByMessageID_Success() {
        MessageManager manager = new MessageManager();
        manager.addMessageRecord("9876543210", "98:1:TEST", "+27718693002", "Testing search functionality", "Sent");

        String result = manager.searchByMessageID("9876543210");
        assertTrue(result.contains("+27718693002"));
        assertTrue(result.contains("Testing search functionality"));
    }

    @Test
    public void testSearchByMessageID_Failure() {
        MessageManager manager = new MessageManager();
        String result = manager.searchByMessageID("NONEXISTENT_ID");
        assertEquals("Message ID not found.", result);
    }

    @Test
    public void testDeleteMessageByHash_Success() {
        MessageManager manager = new MessageManager();
        manager.addMessageRecord("1111111111", "11:1:DELETEME", "+27838968976", "Delete this text", "Stored");

        String result = manager.deleteMessageByHash("11:1:DELETEME");
        assertTrue(result.contains("successfully deleted"));
        assertEquals(0, manager.getMessageCount());
    }

    @Test
    public void testDeleteMessageByHash_Failure() {
        MessageManager manager = new MessageManager();
        String result = manager.deleteMessageByHash("INVALIDHASH");
        assertEquals("Message Hash not found.", result);
    }
}
