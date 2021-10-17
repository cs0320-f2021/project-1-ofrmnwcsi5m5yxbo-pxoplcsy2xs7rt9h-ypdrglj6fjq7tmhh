package test.java.edu.brown.cs.student.main;

import static org.junit.Assert.assertEquals;

import edu.brown.cs.student.main.DataHandling.DataHandler;
import edu.brown.cs.student.main.DataHandling.DataTypes.DataType;
import edu.brown.cs.student.main.DataHandling.DataTypes.Rental;
import edu.brown.cs.student.main.DataHandling.DataTypes.Review;
import edu.brown.cs.student.main.DataHandling.DataTypes.User;
import org.junit.Test;

import javax.xml.crypto.Data;
import java.io.IOException;

public class jsonTest {
  /**
   * Test that DataHandler is correctly reading the .json file into usable and correctly formated
   * User objects
   */
  @Test
  public void testUserData() throws IOException {
    try {
      DataHandler dataHandler = new DataHandler();
      DataType[] usersDataType = dataHandler.readFromFile("data/project-1/justusersSMALL.json", "users");
      User[] users = (User[]) usersDataType;
      // Check that the users stuff is all there
      assertEquals(users[0].getUser_id(), 151944, 0.1);
      assertEquals(users[1].getRealWeight(), 114, 0.1);
      assertEquals(users[14].getUser_id(), 909926, 0.1);
    } catch (Exception e) {
      System.out.println("ERROR: this userData test shouldn't be throwing an error :P");
    }
  }

  /**
   * Test that DataHandler is correctly reading the .json file into usable and correctly formated
   * Review objects
   */
  @Test
  public void testReviewsData() {
    try {
      DataHandler dataHandler = new DataHandler();
      DataType[] reviewsDataType = dataHandler.readFromFile("data/project-1/justreviewsSMALL.json", "reviews");
      Review[] reviews = (Review[]) reviewsDataType;
      // Check that the users stuff is all there
      String reviewSummary = "So many compliments!";
      assertEquals(reviews[0].getReview_summary(), reviewSummary);
      assertEquals(reviews[13].getID(), 14, 0.1);

    } catch (Exception e) {
      System.out.println("ERROR: this reviewsData test shouldn't be throwing an error :P");
    }
  }

  /**
   * Test that DataHandler is correctly reading the .json file into usable and correctly formated
   * Rental objects
   */
  @Test
  public void testRentData() {
    try {
      DataHandler dataHandler = new DataHandler();
      DataType[] rentsDataType = dataHandler.readFromFile("data/project-1/justrentSMALL.json", "rent");
      Rental[] rents = (Rental[]) rentsDataType;
      // Check that the users stuff is all there
      assertEquals(rents[0].getFit(), "fit");
      assertEquals(rents[29].getUser_id(), 420272, 0.1);
    } catch (Exception e) {
      System.out.println("ERROR: this rentData test shouldn't be throwing an error :P");
    }
  }
}
