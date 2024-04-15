import org.example.ChatApplication.ChatServer;
import org.example.ChatApplication.Iterable.SearchMessagesByUser;
import org.example.ChatApplication.Message;
import org.example.ChatApplication.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class IterableTest {
    private ChatServer chatServer;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void init() {
        chatServer = new ChatServer();
        user1 = new User("Dat");
        user2 = new User("Boi");
        user3 = new User("Nhu");
        chatServer.register(user1);
        chatServer.register(user2);
        chatServer.register(user3);
    }
    @Test
    public void iterableTest() {
        chatServer.sendMessage(user1, "Hi Boi, nice to meet you!", user2);
        chatServer.sendMessage(user1, "Hi everyone, I am new student in the class", user2, user3);
        chatServer.sendMessage(user2, "Hi Dat, do you want to connect for work ?", user1);
        chatServer.sendMessage(user3, "Do you want to play badminton", user1);
        chatServer.sendMessage(user1, "I don't want to talk to you!", user3);



        SearchMessagesByUser user1User2Query = user1.getChatHistory().iterator(user2);
        ArrayList<Message> totalMessage = new ArrayList<Message>();
        while(user1User2Query.hasNext()) {
            totalMessage.add(user1User2Query.next());
        }
        assertEquals(3, totalMessage.size());
    }
}
