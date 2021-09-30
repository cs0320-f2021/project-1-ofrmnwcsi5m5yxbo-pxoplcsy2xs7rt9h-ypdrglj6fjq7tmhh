package DataHandling.DataTypes;

public class Rental {
  private final String fit;
  private final int user_id;
  private final int item_id;
  private final double rating;
  private final String rented_for;
  private final String category;
  private final double size;
  private final int id;

  public Rental(String fit, Number user_id, Number item_id, Number rating, String rented_for, String category, Number size, Number id) {
    this.fit = fit;
    this.user_id = user_id.intValue();
    this.item_id = item_id.intValue();
    this.rating = rating.doubleValue();
    this.rented_for = rented_for;
    this.category = category;
    this.size = size.doubleValue();
    this.id = id.intValue();
  }
}
