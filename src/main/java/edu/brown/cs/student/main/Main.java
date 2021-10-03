package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.student.main.DataHandling.DataHandler;
import edu.brown.cs.student.main.DataHandling.DataTypes.DataType;
import edu.brown.cs.student.main.DataHandling.DataTypes.Rental;
import edu.brown.cs.student.main.DataHandling.DataTypes.Review;
import edu.brown.cs.student.main.DataHandling.DataTypes.Test3D;
import edu.brown.cs.student.main.DataHandling.DataTypes.User;
import freemarker.template.Configuration;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

import javax.xml.crypto.Data;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {
    // set up parsing of command line flags
    OptionParser parser = new OptionParser();

    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
        .defaultsTo(DEFAULT_PORT);

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    // TODO: Add your REPL here!
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      // Variables to be instantiated in REPL below
      KDTree<User> kdTree = null;
      List<User> users = new ArrayList<>();
      List<Rental> rentals = new ArrayList<>();
      List<Review> reviews = new ArrayList<>();

      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");

          // [SECTION:] Load user data into KDTree using DataHandler
          if (arguments[0].equals("users")) {
            // Setup List of Users
            DataHandler dataHandler = new DataHandler();
            // If using Api aggregator
            if (arguments[1].equals("online")) {
              DataType[] data = dataHandler.readFromAPI(arguments[0]);
              try {
                data = dataHandler.readFromAPI(arguments[0]);
                if (data instanceof User[]) {
                  User[] usersArray = (User[]) data;
                  Collections.addAll(users, usersArray);
//                  users = Arrays.asList(usersArray);
                } else {
                  throw new IOException();
                }
              } catch (Exception e) {
                System.out.println("ERROR: Something went wrong with API aggregator");
              }
            }
            // If reading straight from a .json file
            else {
              DataType[] data = dataHandler.readFromFile(arguments[1], arguments[0]);
              try {
                if (data instanceof User[]) {
                  User[] usersArray = (User[]) data;
                  Collections.addAll(users, usersArray);
//                  users = Arrays.asList(usersArray);
                } else {
                  throw new IOException();
                }
              } catch (Exception e) {
                System.out.println("ERROR: Could not read from input file");
              }
            }

            // Setup List of dimensions for KDTree (weight, height, age)
            List<Comparator<User>> dimensions = new ArrayList<>();
            dimensions.add(new compareByWeight());
            dimensions.add(new compareByHeight());
            dimensions.add(new compareByAge());
            // Sort users by first axis, age
            users.sort(new compareByAge());
            int median = (int) Math.floor(users.size() / 2.0); // get median for balanced KDTree
            // Create KDTree
            Node<User> root = new Node<>(users.get(median)); // Had to make User implement Comparator interface; that ok?
            kdTree = new KDTree<>(3, root, dimensions);
            // Add users before median to KDTree
            for (User user: users.subList(0, median)) {
              kdTree.addNode(root, new Node<>(user));
            }
            // Add users after median to KDTree
            for (User user: users.subList(median + 1, users.size())) {
              kdTree.addNode(root, new Node<>(user));
            }
            // KDTree should be set up now; let user know this is done
            System.out.println("Loaded " + users.size() + " users from " + arguments[1]);
          }
          // [SECTION]: Get reviews/rentals information from api-aggregator/.json files
          else if (arguments[0].equals("reviews") || arguments[0].equals("rent")) {
            DataHandler dataHandler = new DataHandler();
            // If using api aggregator
            if (arguments[1].equals("online")) {
              DataType[] data = dataHandler.readFromAPI(arguments[0]);
              try {
                data = dataHandler.readFromAPI(arguments[0]);
                // Check if the datatype is Review
                if (data instanceof Review[]) {
                  Review[] reviewsArray = (Review[]) data;
                  reviews = Arrays.asList(reviewsArray);
                }
                // Check if the datatype is Rental
                else if (data instanceof Rental[]) {
                  Rental[] rentalsArray = (Rental[]) data;
                  rentals = Arrays.asList(rentalsArray);
                }
                else {
                  throw new IOException();
                }
              } catch (Exception e) {
                System.out.println("ERROR: Something went wrong with API aggregator");
              }
            }
            // If reading from .json files directly
            else {
              DataType[] data = dataHandler.readFromFile(arguments[1], arguments[0]);
              try {
                // Check if the datatype is Review
                if (data instanceof Review[]) {
                  Review[] reviewsArray = (Review[]) data;
                  reviews = Arrays.asList(reviewsArray);
                }
                // Check if the datatype is Rental
                else if (data instanceof Rental[]) {
                  Rental[] rentalsArray = (Rental[]) data;
                  rentals = Arrays.asList(rentalsArray);
                }
                else {
                  throw new IOException();
                }
              } catch (Exception e) {
                System.out.println("ERROR: Could not read from input file");
              }
            }
          }
          // [SECTION]: Execute similar methods from KDTree
          else if (arguments[0].equals("similar")){
            if (arguments.length == 3) {
              //similar <k> <some_user_id>
              //FIND object with same id as <some_user_id> to use as TARGET
              User targetUser = null;
              try {
                int inputtedUser = Integer.parseInt(arguments[1]);
                for (User user : users) {
                  if (user.getUser_id() == inputtedUser) {
                    targetUser = user;
                    break;
                  }
                }
                // Check that user was found
                if (targetUser == null) {
                  throw new IllegalArgumentException();
                }
              } catch (Exception e) {
                System.out.println("ERROR: User ID not found");
              }

              try {
                //CALL 'similar' method from KDTree object with <k> <TARGET> <exclude == TRUE>
                List<Integer> similarUsers = kdTree.similar(Integer.parseInt(arguments[1]), targetUser, true);
                //PRINT ids from List returned
                for (int id : similarUsers) {
                  System.out.println(id);
                }
              } catch (Exception e) {
                System.out.println("ERROR: KDTree was not initialized");
              }

            } else if ((arguments.length == 5)){
              //similar <k> <weight in lbs> <height in inches> <age in years>

              // CREATE object with same values as <weight in lbs> <height in inches> <age in years>
              User targetUser = null;
              try {
                int heightInInches = Integer.parseInt(arguments[3]);
                String heightInFeet = (heightInInches / 12) + "' " + (heightInInches % 12);
                // Get age of input as double
                double age = Double.parseDouble(arguments[4]);
                // Create User object to be the target for similar();
                // Set other attributes as empty strings
                targetUser = new User(-1, arguments[2], "", heightInFeet, age, "", "");
              } catch (Exception e) {
                System.out.println("ERROR: Couldn't create User object from inputs");
              }

              // CALL 'similar' method from KDTree object with <k> <TARGET> <exclude == FALSE>
              try {
                // CALL 'similar' method from KDTree object with <k> <TARGET> <exclude == TRUE>
                List<Integer> similarUsers = kdTree.similar(Integer.parseInt(arguments[1]), targetUser, false);
                // PRINT ids from List returned
                for (int id : similarUsers) {
                  System.out.println(id);
                }
              } catch (Exception e) {
                System.out.println("ERROR: KDTree was not initialized");
              }
            }
          }
          // [SECTION:] Execute classify methods from KDTree
          else if (arguments[0].equals("classify")) {
            if (arguments.length == 3) {
              //classify <k> <some_user_id>

              //FIND object with same id as <some_user_id> to use as TARGET
              User targetUser = null;
              try {
                int inputtedUser = Integer.parseInt(arguments[1]);
                for (User user : users) {
                  if (user.getUser_id() == inputtedUser) {
                    targetUser = user;
                    break;
                  }
                }
                // Check that user was found
                if (targetUser == null) {
                  throw new IllegalArgumentException();
                }
              } catch (Exception e) {
                System.out.println("ERROR: User ID not found");
              }

              //Collect all possible groups i.e. all horoscopes. should be 12 types total
              List<String> horoscopes = Arrays.asList("Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
                  "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces");
              try {
                //CALL 'classify' method from KDTree object with <k> <TARGET> <exclude == TRUE> <groups>
                Map<String, Integer> mappedUsers = kdTree.classify(Integer.parseInt(arguments[1]),
                    targetUser, true, horoscopes);

                //PRINT each key value pair from Map returned
                mappedUsers.forEach((horoscope, numUsers) -> System.out.println(horoscope + ": " + numUsers));
              } catch (Exception e) {
                System.out.println("ERROR: KDTree was not initialized");
              }
            } else if ((arguments.length == 5)){
              //classify <k> <weight in lbs> <height in inches> <age in years>
              //CREATE object with same attribute values as <weight in lbs> <height in inches> <age in years>
              //all other attributes can be random
              //will be used as TARGET
              User targetUser = null;
              try {
                int heightInInches = Integer.parseInt(arguments[3]);
                String heightInFeet = (heightInInches / 12) + "' " + (heightInInches % 12);
                // Get age of input as double
                double age = Double.parseDouble(arguments[4]);
                // Create User object to be the target for similar();
                // Set other attributes as empty strings
                targetUser = new User(-1, arguments[2], "", heightInFeet, age, "", "");
              } catch (Exception e) {
                System.out.println("ERROR: Couldn't create User object from inputs");
              }
              //Collect all possible groups i.e. all horoscopes
              List<String> horoscopes = Arrays.asList("Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
                  "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces");
              try {
                //CALL 'classify' method from KDTree object with <k> <TARGET> <exclude == TRUE> <groups>
                Map<String, Integer> mappedUsers = kdTree.classify(Integer.parseInt(arguments[1]),
                    targetUser, true, horoscopes);

                //PRINT each key value pair from Map returned
                mappedUsers.forEach((horoscope, numUsers) -> System.out.println(horoscope + ": " + numUsers));
              } catch (Exception e) {
                System.out.println("ERROR: KDTree was not initialized");
              }
            }
          }
          else {
            throw new IllegalArgumentException();
          }
        } catch (IllegalArgumentException e) {
          System.out.println("ERROR: Invalid input for REPL");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ERROR: Invalid input for REPL");
    }

  }

  private static FreeMarkerEngine createEngine() {
    Configuration config = new Configuration(Configuration.VERSION_2_3_0);

    // this is the directory where FreeMarker templates are placed
    File templates = new File("src/main/resources/spark/template/freemarker");
    try {
      config.setDirectoryForTemplateLoading(templates);
    } catch (IOException ioe) {
      System.out.printf("ERROR: Unable use %s for template loading.%n",
          templates);
      System.exit(1);
    }
    return new FreeMarkerEngine(config);
  }

  private void runSparkServer(int port) {
    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");

    // when there's a server error, use ExceptionPrinter to display error on GUI
    Spark.exception(Exception.class, new ExceptionPrinter());

    // initialize FreeMarker template engine (converts .ftl templates to HTML)
    FreeMarkerEngine freeMarker = createEngine();

    // setup Spark Routes
    Spark.get("/", new MainHandler(), freeMarker);
  }

  /**
   * Display an error page when an exception occurs in the server.
   */
  private static class ExceptionPrinter implements ExceptionHandler<Exception> {
    @Override
    public void handle(Exception e, Request req, Response res) {
      // status 500 generally means there was an internal server error
      res.status(500);

      // write stack trace to GUI
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  /**
   * A handler to serve the site's main page.
   *
   * @return ModelAndView to render.
   * (main.ftl).
   */
  private static class MainHandler implements TemplateViewRoute {
    @Override
    public ModelAndView handle(Request req, Response res) {
      // this is a map of variables that are used in the FreeMarker template
      Map<String, Object> variables = ImmutableMap.of("title",
          "Go go GUI");

      return new ModelAndView(variables, "main.ftl");
    }
  }

  /**
   * Comparators for the weight, height, and age of Users read from .json files
   * Used to set up KDTree
   */
  public static class compareByWeight implements Comparator<User> {
    compareByWeight() {}
    @Override
    public int compare(User o1, User o2) {
      return Double.compare(o1.getRealWeight(), o2.getRealWeight());
    }
  }
  public static class compareByHeight implements Comparator<User> {
    compareByHeight() {}
    @Override
    public int compare(User o1, User o2) {
      return Double.compare(o1.getRealHeight(), o2.getRealHeight());
    }
  }
  public static class compareByAge implements Comparator<User> {
    compareByAge() {}
    @Override
    public int compare(User o1, User o2) {
      return Double.compare(o1.getAge(), o2.getAge());
    }
  }
}
