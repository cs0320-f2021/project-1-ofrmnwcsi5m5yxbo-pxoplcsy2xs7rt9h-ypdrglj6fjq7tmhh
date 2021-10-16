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
    private int ormComfort;
    private int apiComfort;
    private int kdtreeComfort;
    private int bloomfilterComfort;
    private String howOften;
    private String whatMedium;
    private String whatTime;
    private String workloadDistribution;
    private String whichTrack;
    private String genderIdentity;
    private boolean matchGender;
    private String erIdentity;
    private boolean matcher;

    public Set<String> getInterests() {
        return interests;
    }

    public Set<String> getPositiveTraits() {
        return positiveTraits;
    }

    public Set<String> getNegativeTraits() {
        return negativeTraits;
    }

    public int getOrmComfort() {
        return ormComfort;
    }

    public int getApiComfort() {
        return apiComfort;
    }

    public int getKdtreeComfort() {
        return kdtreeComfort;
    }

    public int getBloomfilterComfort() {
        return bloomfilterComfort;
    }

    public String getHowOften() {
        return howOften;
    }

    public String getWhatMedium() {
        return whatMedium;
    }

    public String getWhatTime() {
        return whatTime;
    }

    public String getWorkloadDistribution() {
        return workloadDistribution;
    }

    public String getWhichTrack() {
        return whichTrack;
    }

    public String getGenderIdentity() {
        return genderIdentity;
    }

    public boolean isMatchGender() {
        return matchGender;
    }

    public String getErIdentity() {
        return erIdentity;
    }

    public boolean isMatcher() {
        return matcher;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student(int id, Set<String> interests, Set<String> positiveTraits, Set<String> negativeTraits, int commentingSkill,
                   int testingSkill, int oopSkill, int algorithmSkill, int teamworkSkill, int frontendSkill, int ormComfort, int apiComfort, int kdtreeComfort,
                   int bloomfilterComfort, String howOften, String whatMedium, String whatTime, String workloadDistribution, String whichTrack,
                   String genderIdentity, boolean matchGender, String erIdentity, boolean matcher) {
        this.id = id;
        this.interests = interests;
        this.positiveTraits = positiveTraits;
        this.negativeTraits = negativeTraits;
        this.commentingSkill = commentingSkill;
        this.testingSkill = testingSkill;
        this.oopSkill = oopSkill ;
        this.algorithmSkill = algorithmSkill;
        this.teamworkSkill = teamworkSkill;
        this.frontendSkill = frontendSkill;
        this.ormComfort = ormComfort;
        this.apiComfort = apiComfort;
        this.kdtreeComfort = kdtreeComfort;
        this.bloomfilterComfort = bloomfilterComfort;
        this.howOften = howOften;
        this.whatMedium = whatMedium;
        this.whatTime = whatTime;
        this.workloadDistribution = workloadDistribution;
        this.whichTrack = whichTrack;
        this.genderIdentity = genderIdentity;
        this.matchGender = matchGender;
        this.erIdentity = erIdentity;
        this.matcher = matcher;
    }
}
