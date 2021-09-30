package DataHandling;

import DataHandling.DataTypes.User;

import java.io.IOException;
import java.net.http.HttpRequest;

public class MainTest {
  public static void main(String[] args) throws IOException {
    ClientRequestGenerator crg = new ClientRequestGenerator();
    HttpRequest req = crg.getIntroGetRequest("https://runwayapi.herokuapp.com/users-one?auth=jdai15&key=04cp225");
    ApiClient ac = new ApiClient();
    String returned = ac.makeRequest(req);
    DataHandler handler = new DataHandler();
    String filename = "data/project-1/justrentSMALL.json";
    User[] col = handler.readUsersFromFile(filename);
    System.out.println();
  }
}
