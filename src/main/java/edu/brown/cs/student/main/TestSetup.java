package edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataTypes.Test3D;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Example class to setup KDTree to use with REPL
 */
public class TestSetup {


  public TestSetup(){}


  /**
   * Method that will set up KDTree manually using Test3D datatype
   */
  public KDTree<Test3D> makeKDTree() {

    //SETUP list of T values, T must implement Comparable and Coord interface
    ArrayList<Test3D> listValues = new ArrayList<>();

    listValues.add(new Test3D( 10, 60.0, 100.0, "boy", 0));
    listValues.add(new Test3D( 9, 45.0, 110.0, "girl", 1));
    listValues.add(new Test3D( 12, 50.0, 121.0, "boy", 2));
    listValues.add(new Test3D( 9, 40.0, 120.0, "boy", 3));
    listValues.add(new Test3D( 12, 48.0, 120.0, "boy", 4));


    //SETUP list of comparators

    /*order of list MUST match order of coordinate values
    i.e. if (X,Y,Z) is a coordinate, order of list of Comparators must be
    X_Comparator, Y_Comparator, Z_Comparator
    */

    ArrayList<Comparator<Test3D>> dimensions = new ArrayList<>();
    dimensions.add(new TestComparatorByAge());
    dimensions.add(new TestComparatorByHeight());
    dimensions.add(new TestComparatorByWeight());

    //SORT list of T values by first axis

    listValues.sort(new TestComparatorByAge());

    //FIND median of list to use as root for balanced tree

    int median = (int) Math.floor(listValues.size() / 2.0);

    //Create new KDTree object, this example uses 3 dimensions

    Node<Test3D> root = new Node<>(listValues.get(median));
    KDTree<Test3D> testTree = new KDTree<>(3,root, dimensions);

    //ADD all values before median as nodes into KDTree
    for (Test3D value: listValues.subList(0, median)) {
      testTree.addNode(root, new Node<>(value));

    }

    //ADD all values after median as nodes into KDTree

    for (Test3D value: listValues.subList(median + 1, listValues.size())) {
      testTree.addNode(root, new Node<>(value));
    }

    return testTree;
  }



  /**
   * Example Comparators for first, second, and third dimensions to use with Test3D datatype
   * (USER datatype in Datatype package should be similar)
   */
  public static class TestComparatorByAge implements Comparator<Test3D> {
    TestComparatorByAge() {}
    @Override
    public int compare(Test3D o1, Test3D o2) {
      return Integer.compare(o1.getAge(), o2.getAge());
    }

  }

  public static class TestComparatorByHeight implements Comparator<Test3D> {
    TestComparatorByHeight() {}
    @Override
    public int compare(Test3D o1, Test3D o2) {
      return Double.compare(o1.getHeight(), o2.getHeight());
    }
  }

  public static class TestComparatorByWeight implements Comparator<Test3D> {
    TestComparatorByWeight() {}
    @Override
    public int compare(Test3D o1, Test3D o2) {
      return Double.compare(o1.getWeight(), o2.getWeight());
    }
  }
}
