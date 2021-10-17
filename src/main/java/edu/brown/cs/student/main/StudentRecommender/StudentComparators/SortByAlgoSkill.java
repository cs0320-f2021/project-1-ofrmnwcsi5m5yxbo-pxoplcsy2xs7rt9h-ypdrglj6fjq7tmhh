package edu.brown.cs.student.main.StudentRecommender.StudentComparators;

import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;

import java.util.Comparator;

public class SortByAlgoSkill implements Comparator<StudentNumeric> {

  @Override
  public int compare(StudentNumeric o1, StudentNumeric o2) {
    if (o1.getAlgorithmSkill() < o2.getAlgorithmSkill()) {
      return -1;
    } else {
      return 1;
    }
  }
}
