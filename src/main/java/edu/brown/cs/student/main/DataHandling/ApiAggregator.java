package edu.brown.cs.student.main.DataHandling;

import edu.brown.cs.student.main.DataHandling.DataTypes.*;
import edu.brown.cs.student.main.DataHandling.JsonReader;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.HashSet;

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
    ArrayList results;
    if (type.equals("users")) {
      results = new ArrayList<User[]>();
    }
    else if (type.equals("reviews")) {
      results = new ArrayList<Review[]>();
    }
    else {
      results = new ArrayList<Rental[]>();
    }
    while (results.size() < 3) {
      for (int i = 0; i < 5; i++) {
        String finalURL = typedURL + numToWord[i];
        String body = queryAPI(finalURL);
        try {
          DataType[] returnedResult = jsonreader.readData(body, type);
          if (returnedResult != null) {
            results.add(returnedResult);
          }
        } catch (Exception e) {
          System.out.println("bad endpoint, skipping");
          continue;
        }
      }
    }
    return combineResults(results);
  }

  private String queryAPI(String url) {
    HttpRequest req = crq.getSecuredGetRequest(url);
    return apiclient.makeRequest(req);
  }

  private DataType[] combineResults(ArrayList<DataType[]> results) {
    HashSet<DataType> resultMap = new HashSet<>();
    for (DataType[] result : results) {
      for (int i = 0; i < result.length; i++) {
        resultMap.add(result[i]);
      }
    }
    DataType[] ret = new DataType[resultMap.size()];
    resultMap.toArray(ret);
    return ret;
  }
}
