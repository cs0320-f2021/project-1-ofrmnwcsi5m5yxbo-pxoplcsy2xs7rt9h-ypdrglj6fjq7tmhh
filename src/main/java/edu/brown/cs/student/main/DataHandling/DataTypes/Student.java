package edu.brown.cs.student.main.DataHandling.DataTypes;

import java.util.Set;

public class Student implements DataType {
    private int id;
    private Set<String> interests;
    private Set<String> positiveTraits;
    private Set<String> negativeTraits;
    private int commentingSkill;
    private int testingSkill;
    private int oopSkill;
    private int algorithmSkill;
    private int teamworkSkill;
    private int frontendSkill;
    private String name;
    private String meeting;
    private String grade;
    private double years_of_experience;
    private String horoscope;
    private String meeting_times;
    private String preferred_language;
    private String marginalized_groups;
    private String prefer_group;

    public Student(StudentFromSQL sql, StudentFromAPI api) {
        if (sql.getId() != api.getId()) {
            throw new IllegalArgumentException("sql and api students have different ids");
        }
        this.id = sql.getId();
        this.interests = sql.getInterests();
        this.positiveTraits = sql.getPositiveTraits();
        this.negativeTraits = sql.getNegativeTraits();
        this.commentingSkill = sql.getCommentingSkill();
        this.testingSkill = sql.getTestingSkill();
        this.oopSkill = sql.getOopSkill();
        this.algorithmSkill = sql.getAlgorithmSkill();
        this.teamworkSkill = sql.getTeamworkSkill();
        this.frontendSkill = sql.getFrontendSkill();
        this.name = api.getName();
        this.meeting = api.getMeeting();
        this.grade = api.getGrade();
        this.years_of_experience = api.getYears_of_experience();
        this.horoscope = api.getHoroscope();
        this.meeting_times = api.getMeeting_times();
        this.preferred_language = api.getPreferred_language();
        this.marginalized_groups = api.getMarginalized_groups();
        this.prefer_group = api.getPrefer_group();
    }

    public int getId() {
        return id;
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

    public String getMeeting() {
        return meeting;
    }

    public String getGrade() {
        return grade;
    }

    public double getYears_of_experience() {
        return years_of_experience;
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
