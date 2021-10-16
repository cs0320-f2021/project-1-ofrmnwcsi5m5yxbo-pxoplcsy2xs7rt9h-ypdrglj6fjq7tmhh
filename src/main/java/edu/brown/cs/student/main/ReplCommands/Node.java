package edu.brown.cs.student.main.ReplCommands;

/**
 * A Node class for use in a KDTree.
 *
 */
public class Node<T extends Comparable<T> & Coord<T>> {

  private final T value;
  private Node<T> parent;
  private Node<T> left;
  private Node<T> right;
  private int depthLevel;
  private int id;


  /**
   * Node constructor.
   *
   * @param value - a value (acting as a coordinate) for a node
   */
  public Node(T value){
    this.value = value;
    this.parent = null;
    this.left = null;
    this.right = null;
  }




  /**
   * Sets the parent of a Node
   *
   */
  public void setParent(Node<T> parent) {
    this.parent = parent;
  }

  /**
   * Sets the left child of a Node
   *
   */
  public void setLeft(Node<T> left) {
    this.left = left;
  }


  /**
   * Sets the right child of a Node
   *
   */
  public void setRight(Node<T> right) {
    this.right = right;
  }


  /**
   * Sets the depth level of a Node in a KDTree
   *
   */
  public void setDepth(int depth) {
    this.depthLevel = depth;
  }

  /**
   * Sets the id of a Node in a KDTree
   *
   */
  public void setId(int id) {this.id = id;}


  /**
   * Returns the value of a Node
   *
   * @return value (acting as a coordinate) for a Node
   */
  public T getValue() {
    return value;
  }


  /**
   * Returns the left child of a Node
   *
   * @return  left Node
   */
  public Node<T> getLeft() {
    return left;
  }


  /**
   * Returns the parent of a Node
   *
   * @return  parent Node
   */
  public Node<T> getParent() {
    return parent;
  }


  /**
   * Returns the right child of a Node
   *
   * @return  right Node
   */
  public Node<T> getRight() {
    return right;
  }


  /**
   * Returns the depth level of a Node in a KDTree
   *
   * @return  the value of the level
   */
  public int getDepth() {
    return depthLevel;
  }


  /**
   * Returns the id of a Node in a KDTree
   *
   * @return  the id of Node
   */
  public int getId(){return this.id;}
}