package edu.brown.cs.student.main.StudentRecommender.StudentComparators;

import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;

import java.util.Comparator;

public class SortByFrontEndSkill implements Comparator<StudentNumeric> {

  @Override
  public int compare(StudentNumeric o1, StudentNumeric o2) {
    if (o1.getFrontendSkill() < o2.getFrontendSkill()) {
      return -1;
    } else {
      return 1;
    }
  }
}
