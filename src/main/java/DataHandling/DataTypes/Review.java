package DataHandling.DataTypes;

import java.util.Date;

public class Review {
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
  public String getRevtext() {
    return review_text;
  }
  public String getRevsum() {
    return review_summary;
  }
  public Date getRevdate() {
    return review_date;
  }
  public int getID() {
    return id;
  }
}
