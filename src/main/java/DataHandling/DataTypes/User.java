package DataHandling.DataTypes;

public class User {
  private final int user_id;
  private String weight;
  private String bust_size;
  private String height;
  private double age;
  private String body_type;
  private final String horoscope;

  public User(int user_id, String weight, String bust_size, String height, Double age, String body_type, String horoscope) {
    this.user_id = user_id;
    this.weight = weight;
    this.bust_size = bust_size;
    this.height = height;
    this.age = age;
    this.body_type = body_type;
    this.horoscope = horoscope;
  }

  public int getID() {
    return user_id;
  }

  public String getWeight() {
    return weight;
  }

  public String getBust() {
    return bust_size;
  }

  public String getHeight() {
    return height;
  }

  public double getAge() {
    return age;
  }

  public String getBody() {
    return body_type;
  }

  public String getHoroscope() {
    return horoscope;
  }
}
