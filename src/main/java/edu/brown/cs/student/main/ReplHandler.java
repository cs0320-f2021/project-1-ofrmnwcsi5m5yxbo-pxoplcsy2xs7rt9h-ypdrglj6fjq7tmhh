package edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataHandler;
import edu.brown.cs.student.main.DataHandling.DataTypes.DataType;
import edu.brown.cs.student.main.DataHandling.DataTypes.User;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReplHandler {
    private static final DataHandler dhandler = new DataHandler();
    public List<User> handleUsers(String[] args) throws IOException, IllegalArgumentException {
        if (args.length != 2) {
            throw new IllegalArgumentException();
        }
        DataType[] results;
        ArrayList<User> ret = new ArrayList<>();
        if (args[1].equals("online")) {
            results = dhandler.readFromAPI("users");
        } else {
            results = dhandler.readFromFile(args[1], "users");
        }
        for (DataType result : results) {
            if (result.getClass() == User.class) {
                ret.add((User) result);
            }
            else {
                throw new IllegalArgumentException("Return value was not of type User");
            }
        }
        return ret;
    }
    public String handleSimilar(String[] args, KDTree<User> tree, User findFrom) {
        String ret = "";
        if (args.length == 3) {
            List<Integer> results = tree.similar(Integer.parseInt(args[1]), findFrom, true);
            for (Integer result : results) {
                ret += result + "\n";
            }
        }
        else if (args.length == 5) {
            User ff = new User(-1, args[2], "", args[3], Double.parseDouble(args[4]), "", "");
            List<Integer> results = tree.similar(Integer.parseInt(args[1]), ff, false);
            for (Integer result : results) {
                ret += result + "\n";
            }
        }
        return ret;
    }
    public String handleClassify(String[] args, KDTree<User> tree, User findFrom, ArrayList<String> signs) {
        String ret = "";
        if (args.length == 3) {
            Map<String, Integer> results = tree.classify(Integer.parseInt(args[1]), findFrom, true, signs);
            for (String key : results.keySet()) {
                ret += key + ": " + results.get(key) + "\n";
            }
        }
        else if (args.length == 5) {
            User ff = new User(-1, args[2] + "lb", "", args[3], Double.parseDouble(args[4]), "", "");
            Map<String, Integer> results = tree.classify(Integer.parseInt(args[1]), ff, false, signs);
            for (String key : results.keySet()) {
                ret += key + ": " + results.get(key) + "\n";
            }
        }
        return ret;
    }
}
