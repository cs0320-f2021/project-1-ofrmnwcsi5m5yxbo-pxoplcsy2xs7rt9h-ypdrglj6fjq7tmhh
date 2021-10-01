package DataHandling;

import DataHandling.DataTypes.DataType;
import DataHandling.DataTypes.Rental;
import DataHandling.DataTypes.Review;
import DataHandling.DataTypes.User;
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

  public DataType[] readData(String json, String type) {
    switch (type) {
      case "user":
        return readUser(json);
      case "review":
        return readReview(json);
      case "rental":
        return readRental(json);
      default:
        return null;
    }
  }
}
