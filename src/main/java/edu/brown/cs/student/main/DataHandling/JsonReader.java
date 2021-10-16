package edu.brown.cs.student.main.DataHandling;

import edu.brown.cs.student.main.DataHandling.DataTypes.*;
import com.google.gson.Gson;

public class JsonReader {
  private static final Gson gson = new Gson();

  //read users from json string
  private User[] readUser(String json) {
    User[] read = gson.fromJson(json, User[].class);
    return read;
  }
  //read reviews from json string
  private Review[] readReview(String json) {
    Review[] read = gson.fromJson(json, Review[].class);
    return read;
  }
  //read rentals from json string
  private Rental[] readRental(String json) {
    Rental[] read = gson.fromJson(json, Rental[].class);
    return read;
  }

  private StudentFromAPI[] readStudentFromAPI(String json) {
    StudentFromAPI[] read = gson.fromJson(json, StudentFromAPI[].class);
    return read;
  }

  public DataType[] readData(String json, String type) {
    switch (type) {
      case "users":
        return readUser(json);
      case "reviews":
        return readReview(json);
      case "rent":
        return readRental(json);
      case "integration":
        return readStudentFromAPI(json);
      default:
        return null;
    }
  }
}
