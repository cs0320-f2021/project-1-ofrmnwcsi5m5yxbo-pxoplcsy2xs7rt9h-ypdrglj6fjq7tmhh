package edu.brown.cs.student.main.DataHandling.DataTypes;

public class StudentFromAPI {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentFromAPI(int apiComfort, int kdtreeComfort, int bloomfilterComfort, String howOften, String whatMedium,
                          String whatTime, String workloadDistribution, String whichTrack, String genderIdentity,
                          boolean matchGender, String erIdentity, boolean matcher) {
        this.id = id;
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
