import org.example.ChatApplication.ChatHistory;
import org.example.ChatApplication.Message;
import org.example.ChatApplication.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ChatHistoryTest {
    private ChatHistory chatHistory;
    private User testUser;
    private User recipient;
    @BeforeEach
    public void init() {
        chatHistory = new ChatHistory();
        testUser = new User("Dat");
        recipient = new User("Boi");
    }
    @Test
    public void testAddMessage() {
        Message message = new Message(testUser, new ArrayList<>(List.of(recipient)), "Hi");
        this.chatHistory.addMessage(message);
        ArrayList<Message> messages = this.chatHistory.getMessages();
        assertTrue(messages.contains(message));
        assertEquals(1, messages.size());
    }
    @Test
    public void testRemoveMessage() {
        Message message = new Message(testUser, new ArrayList<>(List.of(recipient)), "Hi");
        this.chatHistory.addMessage(message);
        ArrayList<Message> messages = this.chatHistory.getMessages();
        this.chatHistory.removeMessage(message);
        assertFalse(messages.contains(message));
        assertEquals(0, messages.size());
    }
}
