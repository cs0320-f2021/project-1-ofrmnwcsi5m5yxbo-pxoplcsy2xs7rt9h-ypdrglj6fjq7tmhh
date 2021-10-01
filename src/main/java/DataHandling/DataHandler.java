package DataHandling;

import DataHandling.DataTypes.*;

import javax.xml.crypto.Data;
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

  //read specified datatype from a json file specified by filepath
  public DataType[] readFromFile(String filepath, String type) throws IOException {
    Scanner sc = new Scanner(new File(filepath));
    sc.useDelimiter("\\u001a");
    String json = sc.next();
    sc.close();
    return jsonReader.readData(json, type);
  }

  public User[] readUsersFromAPI() {

  }

  public Review[] readReviewsFromAPI() {

  }

  public Rental[] readRentalsFromFile() {

  }
}
