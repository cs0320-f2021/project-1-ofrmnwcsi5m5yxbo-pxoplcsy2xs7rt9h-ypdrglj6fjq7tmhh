package edu.brown.cs.student.main.DataHandling;

//import edu.brown.cs.student.client.ClientAuth;

import java.net.URI;
import java.net.http.HttpRequest;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   *
   * @return an HttpRequest object for accessing the secured resource.
   */
  public static HttpRequest getSecuredGetRequest(String reqUri) {
    String key = ClientAuth.getKey();;
    reqUri = reqUri + key;
    System.out.println(reqUri);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .build();
    // Hint: .header("x-api-key", apiKey)
    return request;
  }
}