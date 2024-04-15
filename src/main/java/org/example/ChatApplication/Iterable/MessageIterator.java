package org.example.ChatApplication.Iterable;

import org.example.ChatApplication.Message;
import org.example.ChatApplication.User;

import java.util.ArrayList;
import java.util.Iterator;

public class MessageIterator implements Iterator<Message> {
    private ArrayList<Message> messages;
    private User userToSearchWith;
    private int currentIndex;

    public MessageIterator(ArrayList<Message> messages, User userToSearchWith) {
        this.messages = messages;
        this.userToSearchWith = userToSearchWith;
        this.currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < messages.size()) {
            Message message = messages.get(currentIndex);
            if (message.getSender().equals(userToSearchWith) || message.getRecipients().contains(userToSearchWith)) {
                return true;
            }
            currentIndex++;
        }
        return false;
    }

    @Override
    public Message next() {
        if (!hasNext()) {
            throw new IllegalStateException("No more messages");
        }
        return messages.get(currentIndex++);
    }
}

