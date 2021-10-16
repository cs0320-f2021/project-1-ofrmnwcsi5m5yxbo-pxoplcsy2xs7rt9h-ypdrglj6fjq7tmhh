package edu.brown.cs.student.main.ReplCommands;

import edu.brown.cs.student.main.DataHandling.DataTypes.StudentFromAPI;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentFromSQL;

import java.util.Comparator;

public class SortByIdSQL implements Comparator<StudentFromSQL> {
    @Override
    public int compare(StudentFromSQL o1, StudentFromSQL o2) {
        if (o1.getId() < o2.getId()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
