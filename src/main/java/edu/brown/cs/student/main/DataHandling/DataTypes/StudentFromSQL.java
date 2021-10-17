package edu.brown.cs.student.main.DataHandling.DataTypes;

import java.util.Set;

public class StudentFromSQL {
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

    public void setInterests(Set<String> interests) {
        this.interests = interests;
    }

    public void setPositiveTraits(Set<String> positiveTraits) {
        this.positiveTraits = positiveTraits;
    }

    public void setNegativeTraits(Set<String> negativeTraits) {
        this.negativeTraits = negativeTraits;
    }

    public void setCommentingSkill(int commentingSkill) {
        this.commentingSkill = commentingSkill;
    }

    public void setTestingSkill(int testingSkill) {
        this.testingSkill = testingSkill;
    }

    public void setOopSkill(int oopSkill) {
        this.oopSkill = oopSkill;
    }

    public void setAlgorithmSkill(int algorithmSkill) {
        this.algorithmSkill = algorithmSkill;
    }

    public void setTeamworkSkill(int teamworkSkill) {
        this.teamworkSkill = teamworkSkill;
    }

    public void setFrontendSkill(int frontendSkill) {
        this.frontendSkill = frontendSkill;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentFromSQL(int id, Set<String> interests, Set<String> positiveTraits, Set<String> negativeTraits, int commentingSkill,
                          int testingSkill, int oopSkill, int algorithmSkill, int teamworkSkill, int frontendSkill) {
        this.id = id;
        this.interests = interests;
        this.positiveTraits = positiveTraits;
        this.negativeTraits = negativeTraits;
        this.commentingSkill = commentingSkill;
        this.testingSkill = testingSkill;
        this.oopSkill = oopSkill;
        this.algorithmSkill = algorithmSkill;
        this.teamworkSkill = teamworkSkill;
        this.frontendSkill = frontendSkill;
    }

}
