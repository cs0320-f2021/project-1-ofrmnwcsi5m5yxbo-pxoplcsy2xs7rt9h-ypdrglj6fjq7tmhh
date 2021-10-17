package edu.brown.cs.student.main.StudentRecommender;

import edu.brown.cs.student.main.ReplCommands.Coord;
import edu.brown.cs.student.main.ReplCommands.KDTree;
import edu.brown.cs.student.main.ReplCommands.Node;
import edu.brown.cs.student.main.bloomfilter.AndSimilarityComparator;
import edu.brown.cs.student.main.bloomfilter.BloomFilter;
import edu.brown.cs.student.main.bloomfilter.BloomFilterRecommender;
import edu.brown.cs.student.main.bloomfilter.recommender.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A Recommender class specifically made to be used with Student objects
 *
 */
public class StudentRecommender<T extends Comparable<T> & Coord<T>, E extends Item> {

  List<T> treeItems;
  Map<String, E> bloomItems;
  KDTree<T> kdTree;
  BloomFilterRecommender<E> bloomFilter;


  /**
   * StudentRecommender constructor
   *
   * @param treeItems - a list of items to be used in the KDTree, can be unsorted
   * @param k - dimension of KDTree
   * @param comps - a list of Comparators to be used in the KDTree
   * @param bloomItems - a Map of String identifcations to Objects to be used with BloomFilter
   * @param desiredFalsePositiveRate - desired false positive rate for BloomFilter
   */
  public StudentRecommender(List<T> treeItems, int k, List<Comparator<T>> comps,
                            Map<String, E> bloomItems, double desiredFalsePositiveRate){

    //Constructing KDTree
    this.treeItems = treeItems;
    this.bloomItems = bloomItems;
    List<T> numericalItems = new ArrayList<>(treeItems);
    numericalItems.sort(comps.get(0));
    int middle = numericalItems.size() / 2;
    Node<T> root = new Node<>(numericalItems.get(middle));

    this.kdTree = new KDTree<>(k, root, comps);
    numericalItems.remove(middle);
    for (T item : numericalItems) {
      this.kdTree.addNode(root, new Node<>(item));
    }


    //constructing BloomFilter
    this.bloomFilter = new BloomFilterRecommender<>(
        (HashMap<String, E>) bloomItems, desiredFalsePositiveRate);

  }


  /**
   * Finds the student recommendations
   *
   * @param identity - identity of Student target
   * @param r - number of recommendations desired
   */
  public List<String> getRecs(String identity, int r){

    //want to get all items possible in order to compare
    // the results between the KDTree & BloomFilter
    int k = treeItems.size();

    T target = null;
    for (T item : treeItems) {
      if (String.valueOf(item.getIdentity()).equals(identity)) {
          target = item;
      }
    }

    List<Integer> kdResults = this.kdTree.similar(k, target ,true);

    //not sure if this how to set up BloomFilterComparator
    //this sets up AndSimilarity Comparator which needs a BloomFilter as an input
    //I decided to create a BloomFilter around our target Student

    BloomFilter<String> inputFilter = new BloomFilter<>(
        this.bloomFilter.getDesiredFalsePositiveRate(),
        this.bloomFilter.getMaxNumValues());
    List<String> inputItemVector = bloomItems.get(identity).getVectorRepresentation();
    for (String s : inputItemVector) {
      inputFilter.add(s);
    }

    this.bloomFilter.setBloomFilterComparator(new AndSimilarityComparator(inputFilter));

    List<E> bloomResults = this.bloomFilter.getTopKRecommendations(bloomItems.get(identity),k);


    //map from index sums to a list of identities that added up to that index sum
    //index sums are the combined value of the indexes in each result list
    Map<Integer, List<String>> indexSum = new HashMap<>();


    //Printing the size of the two results bc I am not sure if BloomFilter excludes our target
    //which may lead to bugs
//    System.out.println("Size of kdTree results:" + kdResults.size());

//    System.out.println("Size of bloomFilter results:" + bloomResults.size());


    for(int i =0; i < bloomResults.size(); i++){
      String id = bloomResults.get(i).getId();

      //adding index of identity in BloomFilter results to index of identity in KDTree results
      int sum = i + kdResults.indexOf(Integer.parseInt(id));

      if(!indexSum.containsKey(sum)){
        List<String> base = new ArrayList<>();
        base.add(id);
        indexSum.put(sum, base );
      }else{
        indexSum.get(sum).add(id);
      }
    }

    List<String> finalResults = new ArrayList<>();
    int index = 0;

    //Going through all "buckets" in order in hashmap in order to add to our final results list
    //Ex:
    while(r > 0){
      //this would be the max possible index sum so we should stop search
      if(index > 2 * (bloomResults.size() - 1)){
        return finalResults;
      }

      if(indexSum.containsKey(index)){
        for(String id: indexSum.get(index)){
          finalResults.add(id);
          r--;
          //once we found the amount of recs desired stop
          if(r <= 0){
            return finalResults;
          }
        }
      }
      index++;
    }

    return finalResults;
  }


  /**
   * Finds group recommendations
   *
   * @param groupSize- size of Groups to make
   */
  public String[][] getGroups(int groupSize){

    int totalSize = treeItems.size();
    int totalGroup = totalSize / 2;
    if(totalSize % 2 != 0){
      totalGroup += 1;
    }

    String[][] groups = new String[totalGroup][groupSize];
    Set<String> alreadyUsed = new HashSet<>();

    int idIndex = 0;
    for(T item: treeItems){
      String id = String.valueOf(item.getIdentity());
      groups[idIndex][0] = id;
      alreadyUsed.add(id);
      List<String> sortedRecs = getRecs(id, totalSize);
      int matchIndex = 1;

      for (String rec: sortedRecs) {
        if(!alreadyUsed.contains(rec)){
          if(matchIndex < groupSize){
            groups[idIndex][matchIndex] = rec;
            matchIndex++;
          }else{
            break;
          }
        }
      }
      idIndex++;
      if (idIndex == groups.length) {
        break;
      }
    }
    return groups;
  }


}
