package edu.brown.cs.student.main.DataHandling.DataTypes;

import edu.brown.cs.student.main.Coord;

/**
 * A Test datatype class with 3 dimensions to be used as example for KDTree
 *
 */
public class Test3D implements DataType, Coord<Test3D>, Comparable<Test3D> {

  private final int age;
  private final double height;
  private final double weight;
  private final String group;
  private final int id;



  /**
   * Constructor for Test3D
   *
   * @param age - age value
   * @param height - height value in inches
   * @param weight - weight value in pounds
   * @param group - group that object belongs to
   *              in this example either "boy or girl"
   * @param id - unique id for object
   */
  public Test3D(int age, double height, double weight, String group, int id) {
    this.age = age;
    this.height = height;
    this.weight = weight;
    this.group  = group;
    this.id = id;
  }


  @Override
  public double calcDistance(Test3D other) {
    return Math.pow(other.getAge() - this.age, 2) + Math.pow(other.getHeight() - this.height, 2)
        + Math.pow(other.getWeight() - this.weight, 2);
  }


  @Override
  public double calcAxisDistance(int axis, Test3D other) {
    if(axis == 0) {
      return Math.pow(other.getAge() - this.age, 2);
    }else if (axis == 1) {
      return Math.pow(other.getHeight() - this.height, 2);
    } else if (axis == 2) {
      return Math.pow(other.getWeight() - this.weight, 2);
    } else {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Integer getIdentity() {
    return this.id;
  }

  @Override
  public String coordString() {
    return "(Age: " + this.age + ",Height: " + this.height + ",Weight: " + this.weight + ")";
  }

  public String getGroup() {
    return group;
  }

  public int getAge() {
    return age;
  }

  public double getHeight() {
    return height;
  }

  public double getWeight() {
    return weight;
  }

  @Override
  public int compareTo(Test3D o) {
    return 0;
  }


}




