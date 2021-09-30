package DataHandling;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApiAggregator {

  private final ApiClient apiclient = new ApiClient();
  private static final String loginkey = "?auth=jdai15&key=04cp225";

  public ApiAggregator(){};

  public Collection aggregateJson() {
    return new HashSet();
  }
}
