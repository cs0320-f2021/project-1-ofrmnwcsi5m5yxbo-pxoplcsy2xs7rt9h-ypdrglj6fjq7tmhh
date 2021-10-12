package edu.brown.cs.student.main.bloomfilter.recommender;

import java.util.List;

public interface Item {
  List<String> getVectorRepresentation();
  String getId();
}
