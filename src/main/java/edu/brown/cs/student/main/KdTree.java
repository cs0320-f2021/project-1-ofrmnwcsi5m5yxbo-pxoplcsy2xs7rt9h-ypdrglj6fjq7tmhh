package main.java.edu.brown.cs.student.main;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KdTree<T extends Comparable<T> & Coord<T>> {
  //need root that is of type Node
 //need a way to know how many dimensions and which attribute to compare at each level
  //could be a list where we take the order and use that order as the pattern
  //need way to add nodes
  //need way to get value of node
  //do we have to worrry a bout balance RIGHT AWAY?
  //are we given list of points? might have to make a processor class to sort then create KDTREE
  //keep track of how many nodes are at each level
  //when adding a node do we specificy at what depth
 //use custom compartors for each level



  private final int k;
  private final List<Comparator<T>> dimsToCompare;
  private Node<T> root = null;
  private int numNodes = 0;

  //check if dims is not equal to k
  //check if root has dims?
  public KdTree (int k , List<Comparator<T>> dimsToCompare, Node<T> root){
    this.k = k;
    this.dimsToCompare = dimsToCompare;
    this.root = root;
    this.root.setDepth(0);
  }


  //check parent, then if have children, check children
  //have to check if newNode has attributes?
  public void addNode(Node<T> checkAgainst, Node<T> newNode){
    Comparator<T> dim = this.dimsToCompare.get(checkAgainst.getDepth() % k);

    if (dim.compare(checkAgainst.getValue(), newNode.getValue()) > 0) {
      if (checkAgainst.getLeft() != null){
        this.addNode(checkAgainst.getLeft(), newNode);
      }
      checkAgainst.setLeft(newNode);

    } else if (dim.compare(root.getValue(), newNode.getValue()) <= 0 ) {
      if (checkAgainst.getRight() != null){
        this.addNode(checkAgainst.getRight(), newNode);
      }
      checkAgainst.setRight(newNode);
    }
    newNode.setDepth(checkAgainst.getDepth() + 1);
    newNode.setParent(checkAgainst);
    numNodes++;
  }

  public Node<T> getNode() {
    return null;
  }

  public List<Node<T>> similar(int k, T target) {
    //no neighbors to return
    if(k == 0){
      return null;
    }
    ArrayList<Node<T>> neighbors = new ArrayList<>();
    neighbors.add(new Node<>(target.maxValue()));
    this.checkNeighbor(k, neighbors, root, target, true);
    return neighbors;
  }

  private void checkNeighbor(int k, ArrayList<Node<T>> neighbors, Node<T> current, T target,
                             boolean exclude) {
    double distCurrent = current.getValue().calcDistance(target);
    double distFarthestBest = neighbors.get(neighbors.size() - 1).getValue().calcDistance(target);

    if (neighbors.size() < k || distCurrent < distFarthestBest) {
      if(exclude) {
        if(!current.getValue().equals(target)) {

          neighbors.add(neighbors.size() - 1, current );
        }
      }else {
        neighbors.add(neighbors.size() - 1, current );
      }
      if (neighbors.size() > k) {
        neighbors.subList(k, neighbors.size()).clear();
      }
    }
    int dim = current.getDepth() % k;

    Comparator<T> dimCheck = this.dimsToCompare.get(current.getDepth() % k);


    //what happens when there is less than k nodes
    //what happens when we have enough nodes, but by only going left at the begin we still wont
    //satisfy k
    if(numNodes < k) {
      if(current.getLeft() != null ){
        checkNeighbor(k, neighbors, current.getLeft(), target, true);
      }
      if(current.getRight() != null) {
        checkNeighbor(k, neighbors, current.getRight(), target, true);
      }
    } else if (distCurrent > current.getValue().calcAxisDistance(dim, target)) {
    if(current.getLeft() != null && current.getRight() != null){
      checkNeighbor(k, neighbors, current.getLeft(), target, true);
      checkNeighbor(k, neighbors, current.getRight(), target, true);
    }
    } else if (dimCheck.compare(current.getValue(), target) > 0) {
      if(current.getLeft() != null) {
        checkNeighbor(k, neighbors, current.getLeft(), target, true);
      }

    } else if (dimCheck.compare(current.getValue(), target) <= 0) {
      checkNeighbor(k, neighbors, current.getRight(), target, true);
    }
  }



  public List<T> classify(int k, int id) {
    return null;
  }


   /*
  public List<T> similar(int k, List<String> depths) {
    return null;
  }
  public List<T> classify(int k, List<String> depths) {
  return null;
  }
   */

}

