package main.java.edu.brown.cs.student.main;

public interface Coord<T> {


  //calc distance from a T object to other
  public double calcDistance(T other);

  //axis is either 0,1,2 in this case
  public double calcAxisDistance(int axis ,T other);


  //max value for a coord
  public T maxValue();
}
