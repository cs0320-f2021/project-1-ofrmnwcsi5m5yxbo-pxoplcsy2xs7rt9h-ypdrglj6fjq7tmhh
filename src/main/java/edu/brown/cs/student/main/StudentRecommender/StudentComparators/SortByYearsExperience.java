package edu.brown.cs.student.main.StudentRecommender.StudentComparators;

import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;

import java.util.Comparator;

public class SortByYearsExperience implements Comparator<StudentNumeric> {

  @Override
  public int compare(StudentNumeric o1, StudentNumeric o2) {
    if (o1.getYears_of_experience() < o2.getYears_of_experience()) {
      return -1;
    } else {
      return 1;
    }
  }
}
