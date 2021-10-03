package edu.brown.cs.student.main.DataHandling;

import edu.brown.cs.student.main.DataHandling.DataTypes.*;

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

  /**
   * @param filepath: path of file.
   * @param type: pass "users" for users, "reviews" for reviews, and "rent" for rentals.
   * @return array with specified type of data.
   * @throws IOException
   */
  //read specified datatype from a json file specified by filepath
  public DataType[] readFromFile(String filepath, String type) throws IOException {
    Scanner sc = new Scanner(new File(filepath));
    sc.useDelimiter("\\u001a");
    String json = sc.next();
    sc.close();
    return jsonReader.readData(json, type);
  }

  //read data from the api endpoints they gave us.

  /**
   * @param type: pass "users" for users, "reviews" for reviews, and "rent" for rentals.
   * @return array of specified datatype.
   */
  public DataType[] readFromAPI(String type) {
    return apiAggregator.aggregateResults(type);
  }
}
