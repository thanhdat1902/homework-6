package org.example.ChatApplication.Iterable;

import org.example.ChatApplication.Message;
import org.example.ChatApplication.User;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchMessagesByUser implements Iterator<Message> {
    private User userToSearch;
    private int indexInChatHistory;
    private ArrayList<Message> chatHistory;

    public SearchMessagesByUser(ArrayList<Message> messages, User user) {
        this.userToSearch = user;
        this.chatHistory = messages;
        this.indexInChatHistory = 0;
    }

    @Override
    public boolean hasNext() {
        Message message = null;
        while (indexInChatHistory < chatHistory.size()) {
            message = chatHistory.get(indexInChatHistory);
            if (message.getRecipients().contains(userToSearch) || message.getSender().equals(userToSearch)) {
                return true;
            } else {
                indexInChatHistory++;
            }
        }
        return false;
    }

    @Override
    public Message next() {
        if (hasNext()) {
            return chatHistory.get(indexInChatHistory++);
        }
        return null;
    }
}
