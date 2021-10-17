package edu.brown.cs.student.main.ReplCommands.CommandRunnables;

import edu.brown.cs.student.main.DataHandling.DataTypes.Student;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentCategorical;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentNumeric;
import edu.brown.cs.student.main.StudentRecommender.SetupStudentRecommender;
import edu.brown.cs.student.main.StudentRecommender.StudentRecommender;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class Recsys_RecProcess implements ReplRunnable {
  private Recsys_LoadProcess loadedRec;
  private List<Student> studentList;
  private SetupStudentRecommender setup;
  private StudentRecommender<StudentNumeric, StudentCategorical> recommender;

  /**
   * Constructor
   * @param recsys_load - Recsys_LoadProcess object that contains the studentList
   */
  public Recsys_RecProcess(Recsys_LoadProcess recsys_load) {
    loadedRec = recsys_load;
  }

  @Override
  public void runCommand(String[] args)
      throws IOException, SQLException, InvocationTargetException, InstantiationException,
      IllegalAccessException, NoSuchMethodException {
    // Setup recommender
    studentList = loadedRec.getStudentList();
    setup = new SetupStudentRecommender(studentList);
    recommender = new StudentRecommender<StudentNumeric, StudentCategorical>(setup.studentNumerics(), 7,
        setup.numericCompartors(), setup.studentCategoricals(),
        0.1);

    // User Story 3 (recsys_rec <num_recs> <student_id>)
    if (args.length == 3) {
      int numRecs = Integer.parseInt(args[1]);
      userStory3(args[2], numRecs);
    }
  }

  /**
   * Gets the recommended students for a given student and prints results
   * @param studentID - (String) ID of student
   * @param numRecs - Number of students recommended
   */
  private void userStory3(String studentID, int numRecs) {
    List<String> results = recommender.getRecs(studentID, numRecs);
    for (String student : results) {
      System.out.println(student);
    }
  }
}
