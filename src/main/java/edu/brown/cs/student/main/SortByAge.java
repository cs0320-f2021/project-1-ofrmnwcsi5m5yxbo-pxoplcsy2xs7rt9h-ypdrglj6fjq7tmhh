package edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;

import java.util.Comparator;

public class SortByAge implements Comparator<User> {
    public int compare(User a, User b) {
        if (a.getAge() < b.getAge()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
