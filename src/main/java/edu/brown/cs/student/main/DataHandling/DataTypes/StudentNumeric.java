package edu.brown.cs.student.main.DataHandling.DataTypes;

import edu.brown.cs.student.main.ReplCommands.Coord;

public class StudentNumeric implements Comparable<StudentNumeric>, Coord<StudentNumeric> {

  private int id;
  private String name;
  private int commentingSkill;
  private int testingSkill;
  private int oopSkill;
  private int algorithmSkill;
  private int teamworkSkill;
  private int frontendSkill;
  private double years_of_experience;

  public StudentNumeric(Student stu) {
    this.id = stu.getId();
    this.commentingSkill = stu.getCommentingSkill();
    this.testingSkill = stu.getTestingSkill();
    this.oopSkill = stu.getOopSkill();
    this.algorithmSkill = stu.getAlgorithmSkill();
    this.teamworkSkill = stu.getTeamworkSkill();
    this.frontendSkill = stu.getFrontendSkill();
    this.name = stu.getName();
    this.years_of_experience = stu.getYears_of_experience();
  }

  @Override
  public int compareTo(StudentNumeric o) {
    return 0;
  }

  public int complement(int skill){
    return 10 - skill;
  }
  @Override
  public double calcDistance(StudentNumeric other) {
    return  Math.pow(other.getCommentingSkill() - this.complement(commentingSkill), 2) +
        Math.pow(other.getTestingSkill() - this.complement(testingSkill), 2) +
        Math.pow(other.getOopSkill() - this.complement(oopSkill), 2) +
        Math.pow(other.getAlgorithmSkill() - this.complement(algorithmSkill), 2) +
        Math.pow(other.getTeamworkSkill() - this.complement(teamworkSkill), 2) +
        Math.pow(other.getFrontendSkill() - this.complement(frontendSkill), 2) +
        Math.pow(other.getYears_of_experience() - this.years_of_experience, 2);
  }

  @Override
  public double calcAxisDistance(int axis, StudentNumeric other) {
    if(axis == 0){
      return Math.pow(other.getCommentingSkill() - this.complement(commentingSkill), 2);
    }else if(axis == 1) {
      return Math.pow(other.getTestingSkill() - this.complement(testingSkill), 2);
    }else if(axis == 2) {
      return Math.pow(other.getOopSkill() - this.complement(oopSkill), 2);
    }else if(axis == 3) {
      return Math.pow(other.getAlgorithmSkill() - this.complement(algorithmSkill), 2);
    }else if(axis == 4) {
      return Math.pow(other.getTeamworkSkill() - this.complement(teamworkSkill), 2);
    }else if(axis == 5) {
      return Math.pow(other.getFrontendSkill() - this.complement(frontendSkill), 2);
    }else if(axis == 6) {
      return Math.pow(other.getYears_of_experience() - this.years_of_experience, 2);
    }else{
      throw new IllegalArgumentException();
    }
  }

  @Override
  public Integer getIdentity() {
    return this.id;
  }

  @Override
  public String getGroup() {
    return null;
  }

  @Override
  public String coordString() {
    return "(Weight: " + this.id + ")";
  }

  public int getId() {
    return id;
  }

  public int getCommentingSkill() {
    return commentingSkill;
  }

  public int getTestingSkill() {
    return testingSkill;
  }

  public int getOopSkill() {
    return oopSkill;
  }

  public int getAlgorithmSkill() {
    return algorithmSkill;
  }

  public int getTeamworkSkill() {
    return teamworkSkill;
  }

  public int getFrontendSkill() {
    return frontendSkill;
  }

  public String getName() {
    return name;
  }

  public double getYears_of_experience() {
    return years_of_experience;
  }



}
