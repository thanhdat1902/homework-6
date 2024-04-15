import org.example.ChatApplication.ChatServer;
import org.example.ChatApplication.Message;
import org.example.ChatApplication.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ChatServerTest {
    private ChatServer chatServer;
    private User testUser;
    private User recipient;
    @BeforeEach
    public void init() {
        testUser = new User("Dat");
        recipient = new User("Boi");
        chatServer = new ChatServer();
    }
    @Test
    public void testRegisterUser() {
        chatServer.register(testUser);
        assertTrue(chatServer.getUsers().contains(testUser));
        chatServer.unregister(testUser);
        assertFalse(chatServer.getUsers().contains(testUser));
    }
    @Test
    public void testSendMessage() {
        chatServer.sendMessage(testUser, "Hi, how are you", recipient);
        Message testMessage = testUser.getChatHistory().getMessages().getFirst();
        assertEquals("Hi, how are you", testMessage.getMessage());
        assertEquals(testUser, testMessage.getSender());
        assertEquals(recipient, testMessage.getRecipients().getFirst());
    }
    @Test
    public void testBlockUser() {
        chatServer.blockUser(testUser, recipient);
        assertTrue(chatServer.checkUserBlocked(testUser.getId(), recipient.getId()));
        chatServer.sendMessage(testUser, "Hi", recipient);
        ArrayList<Message> testMessage = recipient.getChatHistory().getMessages();
        assertEquals(0, testMessage.size());
    }
}
