package DataHandling;

import java.net.http.HttpRequest;

public class MainTest {
  public static void main(String[] args) {
    ClientRequestGenerator crg = new ClientRequestGenerator();
    HttpRequest req = crg.getIntroGetRequest("https://runwayapi.herokuapp.com/users-one?auth=jdai15&key=04cp225");
    ApiClient ac = new ApiClient();
    String returned = ac.makeRequest(req);
    JsonReader reader = new JsonReader();
    User[] col = reader.readUser(returned);
    System.out.println();
  }
}
