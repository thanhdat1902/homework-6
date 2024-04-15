package org.example;

import org.example.ChatApplication.ChatServer;
import org.example.ChatApplication.Iterable.SearchMessagesByUser;
import org.example.ChatApplication.User;

import java.util.ArrayList;

public class Driver {
    public static void printChatHistoryLog(ChatServer server) {
        System.out.println("\n====== Server Chat History Log ======");
        for (User user: server.getUsers()) {
            System.out.println(user);
        }
        System.out.println("=================================");
    }
    public static void main(String[] args) {
        ChatServer server = new ChatServer();
        User dat = new User("Dat");
        User boi = new User("Boi");
        User nhu = new User("Nhu");

        server.register(dat);
        server.register(boi);
        server.register(nhu);

        System.out.println("\n---------- Chating function -----------------");

        server.sendMessage(dat, "Hi Boi, nice to meet you!", boi);
        server.sendMessage(dat, "Hi everyone, I am new student in the class", boi, nhu);
        server.sendMessage(nhu, "Hi Dat, do you want to connect for work ?", dat);
        printChatHistoryLog(server);

        System.out.println("\n------------ After undo message -----------------");
        dat.undoMessage();
        printChatHistoryLog(server);

        System.out.println("\n------------ Blocking function -----------------");

        server.blockUser(dat, nhu);
        server.sendMessage(dat, "Hi Nhu, I don't want to talk to you", nhu);
        server.sendMessage(boi, "Do you want to play badminton ?", nhu, dat);
        printChatHistoryLog(server);


        System.out.println("\n------------ Iterable function -----------------");
        SearchMessagesByUser datBoiQuery = dat.getChatHistory().iterator(boi);
        while(datBoiQuery.hasNext()) {
            System.out.println(datBoiQuery.next());
        }





    }
}