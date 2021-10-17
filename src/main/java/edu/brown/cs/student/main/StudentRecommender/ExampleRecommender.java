package edu.brown.cs.student.main.StudentRecommender;


import edu.brown.cs.student.main.DataHandling.DataTypes.Student;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentCategorical;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;

import java.util.ArrayList;
import java.util.List;

/**
 * Example class on how to create Recommender with fewest steps
 *
 */
public class ExampleRecommender {

  //Recsys LoadProcess has method to getStudentList

  //get new list of Student object, can be unsorted
  List<Student> studentList = new ArrayList<>();



  SetupStudentRecommender setup = new SetupStudentRecommender(studentList);

  //create recommender
  StudentRecommender<StudentNumeric, StudentCategorical> recommender =
      new StudentRecommender<>(setup.studentNumerics(), 7,
          setup.numericCompartors(), setup.studentCategoricals(),
          0.1);

  List<String> results = recommender.getRecs("", 0);

  String[][] groups = recommender.getGroups(2);

}


