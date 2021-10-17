package edu.brown.cs.student.main.StudentRecommender.StudentComparators;

import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;

import java.util.Comparator;

public class SortByOOPSkill implements Comparator<StudentNumeric> {

  @Override
  public int compare(StudentNumeric o1, StudentNumeric o2) {
    if (o1.getOopSkill() < o2.getOopSkill()) {
      return -1;
    } else {
      return 1;
    }
  }
}
