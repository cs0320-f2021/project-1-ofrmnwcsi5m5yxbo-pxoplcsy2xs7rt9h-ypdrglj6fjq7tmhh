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

public class Recsys_Gen_GroupProcess implements ReplRunnable{
  private Recsys_LoadProcess loadedRec;
  private List<Student> studentList;
  private SetupStudentRecommender setup;
  private StudentRecommender<StudentNumeric, StudentCategorical> recommender;

  /**
   * Constructor
   * @param recsys_load - Recsys_LoadProcess object that contains studentList
   */
  public Recsys_Gen_GroupProcess(Recsys_LoadProcess recsys_load) {
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

    // User Story 4 (recsys_gen_groups <team_size>)
    int teamSize = Integer.parseInt(args[1]);
    userStory4(teamSize);
  }

  /**
   * Gets the teams for everyone in the class from recommender and prints results
   * @param teamSize - number of people per team
   */
  private void userStory4(int teamSize) {
    String[][] groups = recommender.getGroups(teamSize);
    for (String[] group : groups) {
      for (String student : group) {
        System.out.print(student + ", ");
      }
      System.out.println("");
    }
  }
}
