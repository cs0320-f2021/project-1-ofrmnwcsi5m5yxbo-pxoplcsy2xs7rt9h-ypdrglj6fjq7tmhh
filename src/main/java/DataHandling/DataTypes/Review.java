package DataHandling.DataTypes;

import java.util.Date;
import java.util.Objects;

public class Review implements DataType {
  private final String review_text;
  private final String review_summary;
  private final Date review_date;
  private final int id;

  public Review(String review_text, String review_summary, String review_date, Number id) {
    this.review_text = review_text;
    this.review_summary = review_summary;
    this.review_date = new Date(review_date);
    this.id = id.intValue();
  }
  public String getReview_text() {
    return review_text;
  }
  public String getReview_summary() {
    return review_summary;
  }
  public Date getReview_date() {
    return review_date;
  }
  public int getID() {
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
    Review review = (Review) o;
    return id == review.id && Objects.equals(review_text, review.review_text) &&
        Objects.equals(review_summary, review.review_summary) &&
        Objects.equals(review_date, review.review_date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(review_text, review_summary, review_date, id);
  }
}
