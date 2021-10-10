package edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;

import java.util.Comparator;

public class SortByHeight implements Comparator<User> {
    public int compare(User a, User b) {
        if (a.getRealHeight() < b.getRealHeight()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
