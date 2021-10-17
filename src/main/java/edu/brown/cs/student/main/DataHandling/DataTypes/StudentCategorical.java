package edu.brown.cs.student.main.DataHandling.DataTypes;

import edu.brown.cs.student.main.bloomfilter.recommender.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentCategorical implements Item {
  private int id;
  private Set<String> interests;
  private Set<String> positiveTraits;
  private Set<String> negativeTraits;
  private String name;
  private String meeting;
  private String grade;
  private String horoscope;
  private String meeting_times;
  private String preferred_language;
  private String marginalized_groups;
  private String prefer_group;

  public StudentCategorical(Student stu) {
    this.id = stu.getId();
    this.interests = stu.getInterests();
    this.positiveTraits = stu.getPositiveTraits();
    this.negativeTraits = stu.getNegativeTraits();
    this.name = stu.getName();
    this.meeting = stu.getMeeting();
    this.grade = stu.getGrade();
    this.horoscope = stu.getHoroscope();
    this.meeting_times = stu.getMeeting_times();
    this.preferred_language = stu.getPreferred_language();
    this.marginalized_groups = stu.getMarginalized_groups();
    this.prefer_group = stu.getPrefer_group();
  }


  @Override
  public List<String> getVectorRepresentation() {

    List<String> vectorRep = new ArrayList<>();
    vectorRep.add(this.meeting);
    vectorRep.add(this.grade);
    vectorRep.add(this.horoscope);
    vectorRep.add(this.meeting_times);
    vectorRep.add(this.preferred_language);
    vectorRep.add(this.marginalized_groups);
    vectorRep.add(this.prefer_group);
    vectorRep.addAll(new ArrayList<>(this.interests));
    vectorRep.addAll(new ArrayList<>(this.positiveTraits));
    vectorRep.addAll(new ArrayList<>(this.negativeTraits));
    return vectorRep;
  }

  public String getId() {
    return String.valueOf(this.id);
  }

  public Set<String> getInterests() {
    return interests;
  }

  public Set<String> getPositiveTraits() {
    return positiveTraits;
  }

  public Set<String> getNegativeTraits() {
    return negativeTraits;
  }

  public String getName() {
    return name;
  }

  public String getMeeting() {
    return meeting;
  }

  public String getGrade() {
    return grade;
  }

  public String getHoroscope() {
    return horoscope;
  }

  public String getMeeting_times() {
    return meeting_times;
  }

  public String getPreferred_language() {
    return preferred_language;
  }

  public String getMarginalized_groups() {
    return marginalized_groups;
  }

  public String getPrefer_group() {
    return prefer_group;
  }


}
