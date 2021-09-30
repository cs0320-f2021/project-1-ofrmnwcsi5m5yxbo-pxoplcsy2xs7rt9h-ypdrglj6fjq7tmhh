package DataHandling;

import com.google.gson.Gson;

public class JsonReader {
  private static final Gson gson = new Gson();

  public User[] readUser(String json) {
    //get collection type and use to read from Json
    User[] read = gson.fromJson(json, User[].class);
    return read;
  }
}
