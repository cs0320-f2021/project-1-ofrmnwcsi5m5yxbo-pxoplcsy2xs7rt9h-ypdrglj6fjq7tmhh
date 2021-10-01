package DataHandling;

import DataHandling.DataTypes.*;

import java.net.http.HttpRequest;
import java.util.ArrayList;

public class ApiAggregator {

  private final ApiClient apiclient = new ApiClient();
  private final JsonReader jsonreader = new JsonReader();
  private final ClientRequestGenerator crq = new ClientRequestGenerator();
  //append endpoint name and number to this URL
  private final String endpointURL = "https://runwayapi.herokuapp.com/";
  //used for quick concatenation of relevant endpoint
  private final String[] numToWord = new String[] {"-one","-two","-three","-four","-five"};
  public ApiAggregator(){};

  public DataType[] aggregateResults(String type) {
    String typedURL = endpointURL + type;
    ArrayList<DataType> results = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      String finalURL = typedURL + numToWord[i];
      String body = queryAPI(finalURL);
      try {
        jsonreader.
      }
    }
    return new User[0];
  }

  private String queryAPI(String url) {
    HttpRequest req = crq.getSecuredGetRequest(url);
    return apiclient.makeRequest(req);
  }
}
