package edu.brown.cs.student.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;
import edu.brown.cs.student.main.ReplCommands.*;
import edu.brown.cs.student.main.ReplCommands.CommandRunnables.*;
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
  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() throws SQLException, ClassNotFoundException {
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
    HashMap<String, ReplRunnable> replCommands = new HashMap<>();
    //add desired replCommands here.
    replCommands.put("users", new UserProcess());
    replCommands.put("similar", new SimilarProcess((UserProcess) replCommands.get("users")));
    replCommands.put("classify", new ClassifyProcess((UserProcess) replCommands.get("users")));
    replCommands.put("recsys_load", new Recsys_LoadProcess());
    /*
     I imagine for any of the rec/grouping commands you would have to pass this recsys_load object
     to the constructor of the process (using replCommands.get("recsys_load"))
    */
    // TODO: Add your REPL here!
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      String input;
      // Variables to be instantiated in REPL below
      while ((input = br.readLine()) != null) {
        try {
          input = input.trim();
          String[] arguments = input.split(" ");
          if (replCommands.containsKey(arguments[0])) {
            replCommands.get(arguments[0]).runCommand(arguments);
          }
          else {
            throw new IllegalArgumentException();
          }
        } catch (IllegalArgumentException e) {
          System.out.println("Error: Invalid input for REPL");
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
