package DataHandling;

import DataHandling.DataTypes.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class DataHandler {
  private final ApiAggregator apiAggregator;
  private final JsonReader jsonReader;

  public DataHandler() {
    this.apiAggregator = new ApiAggregator();
    this.jsonReader = new JsonReader();
  }

  //read users from a json file specified by filepath
  public User[] readUsersFromFile(String filepath) throws IOException {
    Scanner sc = new Scanner(new File(filepath));
    sc.useDelimiter("\\u001a");
    String json = sc.next();
    sc.close();
    return jsonReader.readUser(json);
  }

  //read reviews from a json file specified by filepath
  public Review[] readReviewsFromFile(String filepath) throws IOException {
    Scanner sc = new Scanner(new File(filepath));
    sc.useDelimiter("\\u001a");
    String json = sc.next();
    sc.close();
    return jsonReader.readReview(json);
  }

  //read rentals from a json file specified by filepath
  public Rental[] readRentalsFromFile(String filepath) throws IOException {
    Scanner sc = new Scanner(new File(filepath));
    sc.useDelimiter("\\u001a");
    String json = sc.next();
    sc.close();
    return jsonReader.readRental(json);
  }
}
