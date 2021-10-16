package edu.brown.cs.student.main.ReplCommands.CommandRunnables;

import edu.brown.cs.student.main.DataHandling.ApiAggregator;
import edu.brown.cs.student.main.DataHandling.DataTypes.DataType;
import edu.brown.cs.student.main.DataHandling.DataTypes.Student;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentFromAPI;
import edu.brown.cs.student.main.DataHandling.DataTypes.StudentFromSQL;
import edu.brown.cs.student.main.ReplCommands.SortByIdAPI;
import edu.brown.cs.student.main.ReplCommands.SortByIdSQL;
import edu.brown.cs.student.main.orm.Database;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

public class Recsys_LoadProcess implements ReplRunnable {
    private List<Student> studentList;
    private HashMap<Integer, StudentFromSQL> studentSQLHashMap;
    private HashMap<Integer, StudentFromAPI> studentAPIHashMap;
    private HashMap<Integer, Student> studentHashMap;
    private Database db = new Database("data/integration.sqlite3");
    private ApiAggregator ag = new ApiAggregator();

    public Recsys_LoadProcess() throws SQLException, ClassNotFoundException {
        studentSQLHashMap = new HashMap<>();
        studentAPIHashMap = new HashMap<>();
        studentHashMap = new HashMap<>();
    }

    public void runCommand(String[] arguments) throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<StudentFromSQL> sqlstud = getFromSQL();
        List<StudentFromAPI> apistud = getFromAPI();
        studentList = aggregateStudents(sqlstud, apistud);
        System.out.println("Loaded recsys_load with " + studentList.size() + " students.");
    }

    private List<StudentFromSQL> getFromSQL() throws SQLException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<interests> interests = db.select(interests.class, new HashMap<String, String>());
        List<negative> negatives = db.select(negative.class, new HashMap<String, String>());
        List<positive> positives = db.select(positive.class, new HashMap<String, String>());
        List<skills> skills = db.select(skills.class, new HashMap<String, String>());
        for (interests irest : interests) {
            if (studentSQLHashMap.containsKey(irest.id)) {
                StudentFromSQL curStudent = studentSQLHashMap.get(irest.id);
                Set<String> i = curStudent.getInterests();
                i.add(irest.interest);
                curStudent.setInterests(i);
            }
            else {
                Set<String> i = new HashSet<>();
                i.add(irest.interest);
                StudentFromSQL newStudent = new StudentFromSQL(irest.id, i, new HashSet<>(), new HashSet<>(), 0, 0, 0, 0, 0, 0);
                studentSQLHashMap.put(irest.id, newStudent);
            }
        }
        for (negative ngative : negatives) {
            if (studentSQLHashMap.containsKey(ngative.id)) {
                StudentFromSQL curStudent = studentSQLHashMap.get(ngative.id);
                Set<String> i = curStudent.getNegativeTraits();
                i.add(ngative.trait);
                curStudent.setNegativeTraits(i);
            }
            else {
                Set<String> i = new HashSet<>();
                i.add(ngative.trait);
                StudentFromSQL newStudent = new StudentFromSQL(ngative.id, new HashSet<>(), i, new HashSet<>(), 0, 0, 0, 0, 0, 0);
                studentSQLHashMap.put(ngative.id, newStudent);
            }
        }
        for (positive psitive : positives) {
            if (studentSQLHashMap.containsKey(psitive.id)) {
                StudentFromSQL curStudent = studentSQLHashMap.get(psitive.id);
                Set<String> i = curStudent.getPositiveTraits();
                i.add(psitive.trait);
                curStudent.setPositiveTraits(i);
            }
            else {
                Set<String> i = new HashSet<>();
                i.add(psitive.trait);
                StudentFromSQL newStudent = new StudentFromSQL(psitive.id, new HashSet<>(), new HashSet<>(), i, 0, 0, 0, 0, 0, 0);
                studentSQLHashMap.put(psitive.id, newStudent);
            }
        }
        for (skills skl : skills) {
            if (studentSQLHashMap.containsKey(skl.id)) {
                StudentFromSQL curStudent = studentSQLHashMap.get(skl.id);
                curStudent.setCommentingSkill(skl.commenting);
                curStudent.setTestingSkill(skl.testing);
                curStudent.setOopSkill(skl.oop);
                curStudent.setAlgorithmSkill(skl.algorithms);
                curStudent.setTeamworkSkill(skl.teamwork);
                curStudent.setFrontendSkill(skl.frontend);
            }
            else {
                StudentFromSQL newStudent = new StudentFromSQL(skl.id, new HashSet<>(), new HashSet<>(), new HashSet<>(), skl.commenting, skl.testing, skl.oop, skl.algorithms, skl.teamwork, skl.frontend);
                studentSQLHashMap.put(skl.id, newStudent);
            }
        }
        return new ArrayList<>(studentSQLHashMap.values());
    }

    private List<StudentFromAPI> getFromAPI() {
        DataType[] results = ag.aggregateResults("integration");
        ArrayList<StudentFromAPI> ret = new ArrayList<>();
        for (DataType result : results) {
            if (result.getClass() == StudentFromAPI.class){
                ret.add((StudentFromAPI) result);
            }
            else {
                throw new IllegalArgumentException("Return value was not of type StudentFromAPI");
            }
        }
        return ret;
    }
    private List<Student> aggregateStudents(List<StudentFromSQL> sqlstud, List<StudentFromAPI> apistud) {
        sqlstud.sort(new SortByIdSQL());
        apistud.sort(new SortByIdAPI());
        ArrayList<Student> studs = new ArrayList<>();
        if (sqlstud.size() != apistud.size()) {
            throw new IllegalArgumentException("sql and api have different numbers of students");
        }
        for (int i = 0; i < sqlstud.size(); i++) {
            StudentFromSQL sql = sqlstud.get(i);
            StudentFromAPI api = apistud.get(i);
            Student stud = new Student(sql, api);
            studs.add(stud);
        }
        return studs;
    }
    public List<Student> getStudentList() {
        return studentList;
    }
}

