package edu.brown.cs.student.main.DataHandling.DataTypes;

import edu.brown.cs.student.main.Coord;

import java.util.List;
import java.util.Objects;

//DO COORD METHODS

public class User implements DataType, Coord<User> {
  private final int user_id;
  private String weight;
  private String bust_size;
  private String height;
  private double age;
  private String body_type;
  private final String horoscope;

  public User(Number user_id, String weight, String bust_size, String height, Number age, String body_type, String horoscope) {
    this.user_id = user_id.intValue();
    this.weight = weight;
    this.bust_size = bust_size;
    this.height = height;
    this.age = age.doubleValue();
    this.body_type = body_type;
    this.horoscope = horoscope;
  }

  public int getUser_id() {
    return user_id;
  }

  public String getWeight() {
    return weight;
  }

  public String getBust_size() {
    return bust_size;
  }

  public String getHeight() {
    return height;
  }

  public double getAge() {
    return age;
  }

  public String getBody_type() {
    return body_type;
  }

  public String getHoroscope() {
    return horoscope;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return user_id == user.user_id && Double.compare(user.age, age) == 0 &&
        Objects.equals(weight, user.weight) &&
        Objects.equals(bust_size, user.bust_size) &&
        Objects.equals(height, user.height) &&
        Objects.equals(body_type, user.body_type) &&
        Objects.equals(horoscope, user.horoscope);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_id, weight, bust_size, height, age, body_type, horoscope);
  }

  @Override
  public double calcDistance(User other) {
    return 0;
  }

  @Override
  public double calcAxisDistance(int axis, User other) {
    return 0;
  }

  @Override
  public Integer getIdentity() {
    return null;
  }

  @Override
  public List<String> allGroups() {
    return null;
  }

  @Override
  public String getGroup() {
    return horoscope;
  }

  @Override
  public String coordString() {
    return null;
  }
}
