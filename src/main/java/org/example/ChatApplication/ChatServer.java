package org.example.ChatApplication;

import java.util.*;

public class ChatServer {
    private ArrayList<User> users;
    private Map<String, ArrayList<String>> blockedUsers;

    public ChatServer() {
        this.users = new ArrayList<>();
        blockedUsers = new HashMap<>();
    }
    public ArrayList<User> getUsers() {
        return users;
    }
    public void register(User user) {
        users.add(user);
    }

    public void unregister(User user) {
        users.remove(user);
    }


    public boolean checkUserBlocked(String sender, String recipient) {
        ArrayList<String> blockedList = blockedUsers.get(sender);
        return blockedList != null && blockedList.contains(recipient);
    }
    public void blockUser(User request, User target) {
        String idRequest = request.getId();
        String idTarget = target.getId();
        if(Objects.equals(idRequest, idTarget)) {
            throw new Error("System: Cannot blocked yourself");
        }
        ArrayList<String> blockedList = blockedUsers.get(idRequest);

        // if list does not exist create it
        if(blockedList == null) {
            blockedList = new ArrayList<String>();
            blockedList.add(idTarget);
            blockedUsers.put(idRequest, blockedList);
        } else {
            // add if item is not already in list
            if(!blockedList.contains(idTarget)) blockedList.add(idTarget);
        }
    }
    public void sendMessage(User sender, String message, User ... recipients) {
        Message newMessage = new Message(sender, new ArrayList<>(List.of(recipients)), message);
        for (User recipient : recipients) {
            // Both sender and recipient cannot block each other to be able to send message
            if (recipient != sender && !checkUserBlocked(recipient.getId(), sender.getId()) && !checkUserBlocked(sender.getId(), recipient.getId())) {
                recipient.receiveMessage(newMessage);
            } else {
                System.out.println(String.format("System: [%s] cannot send message to blocked user", sender.getName()));
            }
        }

        if (sender != null) {
            sender.getChatHistory().addMessage(newMessage);
            sender.setLastMessageSent(newMessage);
        }
    }
}
