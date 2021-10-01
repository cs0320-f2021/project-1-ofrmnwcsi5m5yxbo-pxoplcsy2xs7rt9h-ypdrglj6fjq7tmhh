package DataHandling.DataTypes;

import java.util.Objects;

public class Rental implements DataType {
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

  public String getFit() {
    return fit;
  }

  public int getUser_id() {
    return user_id;
  }

  public double getRating() {
    return rating;
  }

  public String getRented_for() {
    return rented_for;
  }

  public String getCategory() {
    return category;
  }

  public double getSize() {
    return size;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(DataType d) {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rental rental = (Rental) o;
    return user_id == rental.user_id && item_id == rental.item_id &&
        Double.compare(rental.rating, rating) == 0 &&
        Double.compare(rental.size, size) == 0 && id == rental.id &&
        Objects.equals(fit, rental.fit) &&
        Objects.equals(rented_for, rental.rented_for) &&
        Objects.equals(category, rental.category);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fit, user_id, item_id, rating, rented_for, category, size, id);
  }
}
