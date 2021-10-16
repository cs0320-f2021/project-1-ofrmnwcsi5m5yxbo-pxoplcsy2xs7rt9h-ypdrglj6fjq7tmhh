package edu.brown.cs.student.main.DataHandling.DataTypes;

import java.util.Objects;

public class StudentFromAPI implements DataType{
    private int id;
    private String name;
    private String meeting;
    private String grade;
    private double years_of_experience;
    private String horoscope;
    private String meeting_times;
    private String preferred_language;
    private String marginalized_groups;
    private String prefer_group;

    public StudentFromAPI(Number id, String name, String meeting, String grade, Number years_of_experience, String horoscope,
                          String meeting_times, String preferred_language, String marginalized_groups, String prefer_group) {
        this.id = id.intValue();
        this.name = name;
        this.meeting = meeting;
        this.grade = grade;
        this.years_of_experience = years_of_experience.doubleValue();
        this.horoscope = horoscope;
        this.meeting_times = meeting_times;
        this.preferred_language = preferred_language;
        this.marginalized_groups = marginalized_groups;
        this.prefer_group = prefer_group;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentFromAPI that = (StudentFromAPI) o;
        return id == that.id && Double.compare(that.years_of_experience, years_of_experience) == 0 && Objects.equals(name, that.name) && Objects.equals(meeting, that.meeting) && Objects.equals(grade, that.grade) && Objects.equals(horoscope, that.horoscope) && Objects.equals(meeting_times, that.meeting_times) && Objects.equals(preferred_language, that.preferred_language) && Objects.equals(marginalized_groups, that.marginalized_groups) && Objects.equals(prefer_group, that.prefer_group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, meeting, grade, years_of_experience, horoscope, meeting_times, preferred_language, marginalized_groups, prefer_group);
    }
}
