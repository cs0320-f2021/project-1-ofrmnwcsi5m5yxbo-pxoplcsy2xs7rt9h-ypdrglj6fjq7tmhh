package DataHandling;

import DataHandling.DataTypes.Rental;
import DataHandling.DataTypes.Review;
import DataHandling.DataTypes.User;
import com.google.gson.Gson;

public class JsonReader {
  private static final Gson gson = new Gson();

  //read users from json string
  public User[] readUser(String json) {
    User[] read = gson.fromJson(json, User[].class);
    return read;
  }
  //read reviews from json string
  public Review[] readReview(String json) {
    Review[] read = gson.fromJson(json, Review[].class);
    return read;
  }
  //read rentals from json string
  public Rental[] readRental(String json) {
    Rental[] read = gson.fromJson(json, Rental[].class);
    return read;
  }
  /*
  public<T extends Object> T[] readAll(String json, T[] type) {
    T[] read = gson.fromJson(json, (Type) type.getClass());
    return read;
  }
   */
}
