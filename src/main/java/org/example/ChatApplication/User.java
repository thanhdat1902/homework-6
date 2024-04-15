package org.example.ChatApplication;

import org.example.ChatApplication.Iterable.IterableByUser;
import org.example.ChatApplication.Iterable.SearchMessagesByUser;

import java.util.ArrayList;
import java.util.UUID;

public class User implements IterableByUser {
    private String id;
    private String name;
    private ChatHistory chatHistory;
    private final MessageMemento messageMemento = new MessageMemento(null);

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChatHistory getChatHistory() {
        return chatHistory;
    }

    public void setChatHistory(ChatHistory chatHistory) {
        this.chatHistory = chatHistory;
    }


    public User(String name) {
        UUID uuid = UUID.randomUUID();
        this.id = uuid.toString();
        this.name = name;
        this.chatHistory = new ChatHistory();
    }

    public void setLastMessageSent(Message message) {
        messageMemento.setMessage(message);
    }

    public void receiveMessage(Message message) {
        System.out.println("System: [" + name + "] received message- from [" + message.getSender().getName() +"]: " + message.getMessage());
        chatHistory.addMessage(message);
    }

    public void undoMessage() {
        if (!chatHistory.getMessages().isEmpty()) {
            if (messageMemento != null) {

                // Remove messages of recipients
                for(User recipient: messageMemento.getMessage().getRecipients()) {
                    recipient.getChatHistory().removeMessage(messageMemento.getMessage());
                }
                // Remove messages of sender
                chatHistory.removeMessage(messageMemento.getMessage());

                System.out.println( this.name + " undo message successfully");
                messageMemento.setMessage(null);
            }
            // Restore new latest message from chat history to memento
            if (!chatHistory.getMessages().isEmpty()) {
                Message lastMesSent = null;
                ArrayList<Message> newChatHistory = chatHistory.getMessages();
                for (int i = newChatHistory.size() - 1; i >= 0; i -= 1) {
                    Message tmp = newChatHistory.get(i);
                    if(tmp.getSender() == this) {
                        lastMesSent = tmp;
                        break;
                    }
                }
                messageMemento.setMessage(lastMesSent);
            }
        } else {
            System.out.println("No message to undo.");
        }
    }


    @Override
    public SearchMessagesByUser iterator(User userToSearchWith) {
        return chatHistory.iterator(userToSearchWith);
    }

    @Override
    public String toString() {
        return String.format("%s: %s",name, chatHistory.toString());
    }

}
