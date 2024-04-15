import org.example.ChatApplication.ChatServer;
import org.example.ChatApplication.Message;
import org.example.ChatApplication.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    private ChatServer chatServer;
    private User sender;
    private User recipient;
    @BeforeEach
    public void init() {
        chatServer = new ChatServer();
        sender = new User("Dat");
        recipient = new User("Boi");
        chatServer.register(sender);
        chatServer.register(recipient);
    }
    @Test
    public void receiveMessageTest() {
        Message message = new Message(sender, new ArrayList<>(List.of(recipient)), "new message sent");
        recipient.receiveMessage(message);
        ArrayList<Message> recipientMessage = recipient.getChatHistory().getMessages();
        assertEquals(1, recipientMessage.size());
        assertEquals("new message sent", recipientMessage.getFirst().getMessage());
    }
    @Test
    public void undoMessageTest() {
        chatServer.sendMessage(sender, "Good morning", recipient);
        assertEquals(1, sender.getChatHistory().getMessages().size());
        assertEquals(1, recipient.getChatHistory().getMessages().size());

        sender.undoMessage();
        assertEquals(0, sender.getChatHistory().getMessages().size());
        assertEquals(0, recipient.getChatHistory().getMessages().size());
    }
    @Test
    public void getNameTest() {
        assertEquals("Dat", sender.getName());
        assertEquals("Boi", recipient.getName());
    }
}
