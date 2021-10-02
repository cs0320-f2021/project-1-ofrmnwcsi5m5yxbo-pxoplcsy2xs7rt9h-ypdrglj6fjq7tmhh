package DataHandling;

import DataHandling.DataTypes.DataType;

import java.io.IOException;
import java.net.http.HttpRequest;

public class MainTest {
  public static void main(String[] args) throws IOException {
    ClientRequestGenerator crg = new ClientRequestGenerator();
    HttpRequest req = crg.getSecuredGetRequest("https://runwayapi.herokuapp.com/users-one");
    ApiClient ac = new ApiClient();
    JsonReader jsonReader = new JsonReader();
    String returned = ac.makeRequest(req);
    DataHandler handler = new DataHandler();
    String filename = "data/project-1/justrentSMALL.json";
    //DataType[] col = jsonReader.readData(returned, "user");
    DataType[] col = handler.readUsersFromAPI();
    System.out.println();
  }
}
