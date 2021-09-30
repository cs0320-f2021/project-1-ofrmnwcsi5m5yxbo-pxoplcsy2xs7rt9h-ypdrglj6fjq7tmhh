package DataHandling;

//import edu.brown.cs.student.client.ClientAuth;

import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 * This class generates the HttpRequests that are then used to make requests from the ApiClient.
 */
public class ClientRequestGenerator {

  /**
   * The basic introductory GET request. You should fill it out so it calls our server at the given URL.
   *
   * @return an HttpRequest object for accessing the introductory resource.
   */
  public static HttpRequest getIntroGetRequest(String reqUri) {
    // The resource we want is hosted at https://cq2gahtw4j.execute-api.us-east-1.amazonaws.com/.
    //String reqUri = "https://epb3u4xo11.execute-api.us-east-1.amazonaws.com/Prod/introResource";
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .build();
    return request;
    // TODO build and return a new GET HttpRequest.
    // See https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.html and
    // https://docs.oracle.com/en/java/javase/11/docs/api/java.net.http/java/net/http/HttpRequest.Builder.html
  }

  /**
   * Similar to the introductory GET request, but restricted to api key holders only. Try calling it without the API
   * Key configured and see what happens!
   *
   * @return an HttpRequest object for accessing the secured resource.
   */
  public static HttpRequest getSecuredGetRequest(String reqUri) {
    // TODO get the secret API key by using the ClientAuth class.
    String key = ClientAuth.getApiKey();;
    reqUri = reqUri + key;
    // TODO build and return a new GET HttpRequest with an api key header.
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(reqUri))
        .build();
    // Hint: .header("x-api-key", apiKey)
    return request;
  }
}