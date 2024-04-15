package org.example.ChatApplication;

import org.example.ChatApplication.Iterable.IterableByUser;
import org.example.ChatApplication.Iterable.SearchMessagesByUser;

import java.util.ArrayList;

public class ChatHistory implements IterableByUser {
    private ArrayList<Message> messages;

    public ChatHistory() {
        this.messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void removeMessage(Message message) {
        messages.remove(message);
    }

    @Override
    public SearchMessagesByUser iterator(User userToSearchWith) {
        return new SearchMessagesByUser(messages, userToSearchWith);
    }

    @Override
    public String toString() {
        String messageToString = "[ ";
        for(Message mess: messages){
            messageToString+=mess;
            messageToString+=",";
        }
        return messageToString.substring(0,messageToString.length()-1)+" ]";
    }



}
