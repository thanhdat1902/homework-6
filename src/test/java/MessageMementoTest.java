import org.example.ChatApplication.Message;
import org.example.ChatApplication.MessageMemento;
import org.example.ChatApplication.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class MessageMementoTest {
    @Test
    public void stateMementoTest() {
        User sender = new User("Dat");
        User recipient = new User("Boi");
        Message newMessage = new Message(sender,  new ArrayList<User>(List.of(recipient)), "New message");
        MessageMemento messageMemento = new MessageMemento(newMessage);
        assertEquals(messageMemento.getMessage(), newMessage);

        Message updatedMessage = new Message(sender,  new ArrayList<User>(List.of(recipient)), "Updated message");

        messageMemento.setMessage(updatedMessage);
        assertEquals(messageMemento.getMessage(), updatedMessage);

    }
}
