package edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;

import java.util.Comparator;

public class SortByWeight implements Comparator<User> {
    public int compare(User a, User b) {
        if (a.getRealWeight() < b.getRealWeight()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
