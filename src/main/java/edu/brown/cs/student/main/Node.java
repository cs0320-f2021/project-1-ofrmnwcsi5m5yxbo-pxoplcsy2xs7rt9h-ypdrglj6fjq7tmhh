package main.java.edu.brown.cs.student.main;

//node for KDTree
public class Node<T extends Comparable<T> & Coord<T>> {
//have a parent
  //have a left child
  //have a right child


  private final T value;
  private Node<T> parent;
  private Node<T> left;
  private Node<T> right;
  private int depth;


  public Node(T value){
    this.value = value;
    this.parent = null;
    this.left = null;
    this.right = null;
  }

  public T getValue() {
    return value;
  }

  public void setParent(Node<T> parent) {
    this.parent = parent;
  }

  public void setLeft(Node<T> left) {
    this.left = left;
  }

  public void setRight(Node<T> right) {
    this.right = right;
  }

  public Node<T> getLeft() {
    return left;
  }

  public Node<T> getParent() {
    return parent;
  }

  public Node<T> getRight() {
    return right;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }
}