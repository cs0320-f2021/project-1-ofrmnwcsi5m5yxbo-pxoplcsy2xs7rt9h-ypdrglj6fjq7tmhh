package edu.brown.cs.student.main.StudentRecommender;

import edu.brown.cs.student.main.DataHandling.DataTypes.Student;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentCategorical;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByAlgoSkill;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByComSkill;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByFrontEndSkill;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByOOPSkill;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByTeamSkill;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByTestSkill;
import edu.brown.cs.student.main.StudentRecommender.StudentComparators.SortByYearsExperience;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A SetUp class specifically made to be used with Student Recommender
 * so that all we need to input is a List of Student objects
 *
 */
public class SetupStudentRecommender {
  List<Student> students;

  /**
   * Constructor for Setup Class
   *
   * @param students - a list of Student objects
   *
   */
  public SetupStudentRecommender(List<Student> students){
    this.students = students;
  }



  /**
   * Creates a List to be used in KDTree portion of Recommender
   *
   * @return a List of StudentNumerics
   *
   */
  public List<StudentNumeric> studentNumerics(){
    List<StudentNumeric> numerics = new ArrayList<>();
    for (Student stu: students) {
      StudentNumeric numeric = new StudentNumeric(stu);
      numerics.add(numeric);
    }
    return numerics;
  }

  /**
   * Creates a Map to be used in BloomFilter portion of Recommender
   *
   * @return a List of StudentCat
   *
   */
  public Map<String, StudentCategorical> studentCategoricals(){
    Map<String, StudentCategorical> cats = new HashMap<>();
    for (Student stu: students) {
      StudentCategorical cat = new StudentCategorical(stu);
      cats.put(cat.getId(), cat);
    }
    return cats;
  }

  /**
   * Creates a List of Student Comparator to be used in KDTree portion of Recommender
   *
   * @return a List of StudentCat
   *
   */
  public List<Comparator<StudentNumeric>> numericCompartors(){
    List<Comparator<StudentNumeric>> comps = new ArrayList<>();
    comps.add(new SortByComSkill());
    comps.add(new SortByTestSkill());
    comps.add(new SortByOOPSkill());
    comps.add(new SortByAlgoSkill());
    comps.add(new SortByTeamSkill());
    comps.add(new SortByFrontEndSkill());
    comps.add(new SortByYearsExperience());
    return comps;
  }



}
