package edu.brown.cs.student.main.ReplCommands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Class for a KDTree made of up of Nodes using T datatype
 *
 * T datatype must implement Comparable and Coord interface
 */
public class KDTree<T extends Comparable<T> & Coord<T>> {

  private final int k;
  private final List<Comparator<T>> dimsToCompare;
  private final Node<T> root;
  private int numNodes = 1;
  private final Set<Integer> checkedNodes = new HashSet<>();



  /**
   * A KD Tree constructor.
   *
   * @param k - number of dimensions
   * @param root - a root node
   * @param dimsToCompare - a list of Comparators that will be used to split
   *                      values at each level of the KDTree
   * @throws IllegalArgumentException - if the list of Comparators does not equal k
   */
  public KDTree(int k , Node<T> root, List<Comparator<T>> dimsToCompare){
    if(dimsToCompare.size() != k) {
      throw new IllegalArgumentException("Comparators do NOT match dimensions");
    }
    this.k = k;
    this.dimsToCompare = dimsToCompare;
    this.root = root;
    this.root.setDepth(0);
    this.root.setId(numNodes);
  }


  /**
   * Adds a node at the appropriate place in the KDTree
   *
   * @param checkAgainst - a root node to add a new node to
   * @param newNode - a new node to be added to KDTree
   */
  public void addNode(Node<T> checkAgainst, Node<T> newNode){
    //finds axis to split on
    Comparator<T> dim = this.dimsToCompare.get(checkAgainst.getDepth() % k);

    if (dim.compare(checkAgainst.getValue(), newNode.getValue()) > 0) {
      if (checkAgainst.getLeft() != null){
        this.addNode(checkAgainst.getLeft(), newNode);

      }else {
        checkAgainst.setLeft(newNode);

        newNode.setDepth(checkAgainst.getDepth() + 1);
        newNode.setParent(checkAgainst);
        numNodes++;
        newNode.setId(numNodes);
        System.out.println("inserted a node");
        return;
      }

    } else {
      if (checkAgainst.getRight() != null){
        this.addNode(checkAgainst.getRight(), newNode);

      }else {
        checkAgainst.setRight(newNode);
        newNode.setDepth(checkAgainst.getDepth() + 1);
        newNode.setParent(checkAgainst);
        numNodes++;
        newNode.setId(numNodes);
        System.out.println("inserted a node");
        return;
      }
    }
  }


  /**
   * Finds the most similar k Nodes to target value
   *
   * @param k - number of neighbors to find
   * @param target - target value to compare to
   * @param exclude - whether to include target value as part of neighbors list
   *                set to TRUE for similar <some userID> command
   *                set to FALSE for similar <weight in lbs> <height in inches> <age in years>
   * @return a list of integers that represent the ids of the values that are nearest neighbors
   */
  public List<Integer> similar(int k, T target, boolean exclude) {

    if(k == 0 || target == null){
      return new ArrayList<>();
    }

    ArrayList<Node<T>> neighbors = new ArrayList<>();
    this.checkNeighbor(k, neighbors, root, target, exclude);
    checkedNodes.clear();

    //now that we have nearest neighbors return their ids
    ArrayList<Integer> neighborsID = new ArrayList<>();
    for (Node<T> neighbor: neighbors) {
      neighborsID.add(neighbor.getValue().getIdentity());
    }
    return neighborsID;
  }

  /**
   * Return a map of all possible classifications to number of neighbors that are part of class
   *
   * @param k - number of neighbors to find
   * @param target - target value to compare to
   * @param exclude - whether to include target value as part of neighbors list
   *                set to TRUE for classify <some userID> command
   *                set to FALSE for classify <weight in lbs> <height in inches> <age in years>
   * @param groups - a list of all possible groups that target could be classified
   *               i.e. all horoscopes
   * @return a map of all possible classifications to number of neighbors that are part of class
   */
  public Map<String, Integer> classify(int k, T target, boolean exclude, List<String> groups) {

    HashMap<String, Integer> classes = new HashMap<>();
    ArrayList<Node<T>> neighbors = new ArrayList<>();
    this.checkNeighbor(k, neighbors, root, target, exclude);
    checkedNodes.clear();

    //now that we have nearest neighbors classify them
    for (String group: groups) {
      classes.put(group, 0);
    }

    for (Node<T> neighbor: neighbors) {
      String group = neighbor.getValue().getGroup();
      if (classes.containsKey(group)) {
        int prev = classes.get(group);
        classes.put(group, prev + 1);
      }
    }

    return classes;

  }

  /**
   * Checks if node should be added to nearest neighbors list
   *
   * @param k - number of neighbors to find
   * @param neighbors - a list of nearest neighbors
   * @param current - current node to check
   * @param target - target value to compare to
   * @param exclude - whether to include target value as part of neighbors list
   */
  private void checkNeighbor(int k, ArrayList<Node<T>> neighbors, Node<T> current, T target,
                             boolean exclude) {

    if (!checkedNodes.contains(current.getId()) && checkedNodes.size() != numNodes){
      if (neighbors.size() == 0 && k != 0) {
        //only for root node
        neighbors.add(current);
      }else if (current.getValue().calcDistance(target) <
            neighbors.get(neighbors.size() - 1).getValue().calcDistance(target)) {
        //current node is closer to target point than one of k-nearest neighbors
        double distCurrent = current.getValue().calcDistance(target);

        if(exclude) {
          if (!current.getValue().equals(target)) {
            //place in appropriate position
            this.insertNeighbor(neighbors, current, target, distCurrent);
          }
        }else {
          this.insertNeighbor(neighbors, current, target, distCurrent);
        }

      } else if (neighbors.size() < k) {
        //collection of neighbors is not full
        neighbors.add(current);
      }
    }

    //truncate list to have only size k
    if (neighbors.size() > k) {
      neighbors.subList(k, neighbors.size()).clear();
    }

    checkedNodes.add(current.getId());

    nextNode(k, neighbors, current, target, exclude);

  }

  /**
   * Finds next node to check if we should add to nearest neighbors list
   *
   * @param k - number of neighbors to find
   * @param neighbors - a list of nearest neighbors
   * @param current - current node we checked
   * @param target - target value to compare to
   * @param exclude - whether to include target value as part of neighbors list
   */
  private void nextNode(int k, ArrayList<Node<T>> neighbors, Node<T> current, T target,
                        boolean exclude) {

    if (neighbors.size() == 0) {
      return;
    }
    double distBestFarthest = neighbors.get(neighbors.size() - 1).getValue().calcDistance(target);
    int dim = current.getDepth() % this.k;

    Comparator<T> dimCheck = this.dimsToCompare.get(current.getDepth() % this.k);


    if (distBestFarthest > current.getValue().calcAxisDistance(dim, target)) {
      /*Euclidean distance between the target point and the farthest of the current
      “best neighbors” is greater than the relevant axis distance
       between the current node and target point, so recur on both children
      */
      if(current.getLeft() != null){
        checkNeighbor(k, neighbors, current.getLeft(), target, exclude);
      }
      if(current.getRight() != null){
        checkNeighbor(k, neighbors, current.getRight(), target, exclude);
      }

    } else if (dimCheck.compare(current.getValue(), target) > 0) {
      //current node's coordinate on the relevant axis is less than the target's coordinate on the
      // relevant axis
      if(current.getLeft() != null) {
        checkNeighbor(k, neighbors, current.getLeft(), target, exclude);
      } else if(current.getRight() != null) {
        checkNeighbor(k, neighbors, current.getRight(), target, exclude);
      }
    } else if (dimCheck.compare(current.getValue(), target) <= 0) {
      //current node's coordinate on the relevant axis is greater than the target's coordinate on the
      // relevant axis
      if(current.getRight() != null) {
        checkNeighbor(k, neighbors, current.getRight(), target, exclude);
      } else if(current.getLeft() != null){
        checkNeighbor(k, neighbors, current.getLeft(), target, exclude);
      }
    }


  }

  /**
   * Inserts node in appropriate position in list of nearest neighbors
   *
   * @param neighbors - a list of nearest neighbors
   * @param current - current node we will insert
   * @param target - target value to compare to
   * @param distCurrent - euclidean distance between current node and target value
   */
  public void insertNeighbor(List<Node<T>> neighbors, Node<T> current,
                             T target, double distCurrent) {
    for (int i = 0; i < neighbors.size(); i++) {
      if (neighbors.get(i).getValue().calcDistance(target) >= distCurrent) {
        neighbors.add(i, current );
        System.out.println(current.getValue().coordString() + " inserted at " + i);
        break;
      }
    }
  }

  /**
   * Prints the tree in String format
   *
   * @param node - node to start from
   * @param indent - how much space to separate each node from its children
   */
  public void printTree(Node<T> node, String indent) {
   String coord = "";
   coord += (node.getValue().coordString()) +  " dim: " + (node.getDepth() % k);
   System.out.println(coord);
   if (node.getLeft() != null) {
     System.out.print(indent + "Left: ");
     this.printTree(node.getLeft(), indent + indent);
   }
    if (node.getRight() != null) {

      System.out.print(indent + "Right: ");
      this.printTree(node.getRight(), indent + indent);
    }

  }
}


//when k is not filled, and we are the bottom of the tree DO NOT WORRY BOUT THIS

    /*
    if(current.getLeft() == null && current.getRight() == null && neighbors.size() < k){
      System.out.print("We have to go back up tree to find more neighbors");
      //go until we have checked all the nodes or k is filled
      while(checkedNodes.size() < numNodes || neighbors.size() < k){
        if (current.getParent() == null) {
          break;
        }
        Node<T> checkParent = current.getParent();
        //check if we should get back to left or right side of parent
        if (!checkedNodes.contains(checkParent.getLeft().getId())){
          checkNeighbor(k, neighbors, checkParent.getLeft(), target, exclude);
        }
        if (!checkedNodes.contains(checkParent.getRight().getId())){
          checkNeighbor(k, neighbors, checkParent.getRight(), target, exclude);
        }
        //go back up the tree
        current = checkParent;

      }

    }
    */

