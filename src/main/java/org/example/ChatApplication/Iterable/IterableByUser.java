package org.example.ChatApplication.Iterable;

import org.example.ChatApplication.User;

import java.util.Iterator;

public interface IterableByUser {
    Iterator iterator(User userToSearchWith);
}
