package edu.brown.cs.student.main.ReplCommands;

import edu.brown.cs.student.main.DataHandling.DataTypes.StudentFromAPI;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentFromSQL;

import java.util.Comparator;

public class SortByIdAPI implements Comparator<StudentFromAPI>{
    @Override
    public int compare(StudentFromAPI o1, StudentFromAPI o2) {
        if (o1.getId() < o2.getId()) {
            return -1;
        }
        else {
            return 1;
        }
    }
}
