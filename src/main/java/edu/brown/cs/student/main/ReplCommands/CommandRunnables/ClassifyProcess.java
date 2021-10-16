package edu.brown.cs.student.main.ReplCommands.CommandRunnables;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;
import edu.brown.cs.student.main.ReplCommands.ReplHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class ClassifyProcess implements ReplRunnable {
    private UserProcess uprocess;
    private ReplHandler rhandler = new ReplHandler();
    public ClassifyProcess(UserProcess uprocess) {
        this.uprocess = uprocess;
    }
    public void runCommand(String[] arguments) {
        String[] starSigns =
                {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo",
                        "Libra", "Scorpio", "Sagittarius", "Capricorn", "Aquarius", "Pisces"};
        ArrayList<String> starSignsList = new ArrayList<>(Arrays.asList(starSigns));
        User target = null;
        if (arguments.length == 3) {
            for (User user : uprocess.getUserList()) {
                if (user.getUser_id() == Integer.parseInt(arguments[2])) {
                    target = user;
                }
            }
        }
        System.out.println(rhandler.handleClassify(arguments, uprocess.getUserTree(), target, starSignsList));
    }
}
