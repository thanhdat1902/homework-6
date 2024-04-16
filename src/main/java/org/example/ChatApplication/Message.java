package org.example.ChatApplication;

import java.util.ArrayList;

public class Message {
    private User sender;
    private ArrayList<User> recipients;
    private long timestamp;
    private String message;

    public Message(User sender, ArrayList<User> recipients, String message){
        this.sender = sender;
        this.recipients = recipients;
        this.timestamp = System.currentTimeMillis();
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public ArrayList<User> getRecipients() {
        return recipients;
    }

    public void setRecipients(ArrayList<User> recipients) {
        this.recipients = recipients;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void print(){
        System.out.println("---Sender: " + sender);
        System.out.println("---Recipients: " + recipients);
        System.out.println("---Timestamp: " + timestamp);
        System.out.println("---Message Content: " + message);
    }

    @Override
    public String toString() {
        String recipientStringLog="";
        for(User recipient: recipients)
        {
            recipientStringLog+= recipient.getName();
            recipientStringLog+= ",";
        }

        return String.format("[ From:[%s],To:[%s],at:%s: '%s']",sender.getName(),recipientStringLog.substring(0, recipientStringLog.length()-1),timestamp,message);
    }
}
