package edu.brown.cs.student.main;

/**
 * An interface for Coordinate to be used in KDTree
 *
 */
public interface Coord<T> {



  /**
   * Calculates euclidean distance from one object to another
   *
   * @param other - other object to calculate distance from
   *
   */
  double calcDistance(T other);

  /**
   * Calculates relevant axis distance from one object to another
   *
   * @param axis - the axis number to use, must be zero-index and follow order of list of
   *             Comparators in KDTree
   * @param other - other object to calculate distance from
   */
  double calcAxisDistance(int axis ,T other);

  /**
   * Returns an unique identification number for object
   *
   */
  Integer getIdentity();

  /**
   * Returns group(classification) that object is a part of
   *
   */
  String getGroup();

  /**
   * Returns object as a coordinate string
   *
   * i.e. (x,y,z)
   *
   */
  String coordString();
}
