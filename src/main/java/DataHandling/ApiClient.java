package DataHandling;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * This class encapsulates the client request handling logic. It is agnostic of what kinds of requests are being made.
 * The exact request formatting is outsourced to ClientRequestGenerator.
 */
public class ApiClient {

  private HttpClient client;

  public ApiClient() {
    HttpClient client = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .connectTimeout(Duration.ofSeconds(60))
        .build();
    this.client = client;
  }

  public String makeRequest(HttpRequest req) {
    try {
      HttpResponse<String> apiResponse = client.send(req, HttpResponse.BodyHandlers.ofString());
      System.out.println("Status " + apiResponse.statusCode());
      return apiResponse.body();

    } catch (IOException ioe) {
      System.out.println("An I/O error occurred when sending or receiving data.");
      System.out.println(ioe.getMessage());

    } catch (InterruptedException ie) {
      System.out.println("The operation was interrupted.");
      System.out.println(ie.getMessage());

    } catch (IllegalArgumentException iae) {
      System.out.println(
          "The request argument was invalid. It must be built as specified by HttpRequest.Builder.");
      System.out.println(iae.getMessage());

    } catch (SecurityException se) {
      System.out.println("There was a security configuration error.");
      System.out.println(se.getMessage());
    }
    return "";
  }
}