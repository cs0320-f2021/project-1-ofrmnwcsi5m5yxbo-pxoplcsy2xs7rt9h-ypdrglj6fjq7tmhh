package edu.brown.cs.student.main;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;

public class SimilarProcess implements ReplRunnable {
    UserProcess uprocess;
    ReplHandler rhandler = new ReplHandler();

    public SimilarProcess(UserProcess uprocess) {
        this.uprocess = uprocess;
    }
    public void runCommand(String[] arguments) {
        User target = null;
        if (arguments.length == 3) {
            for (User user : uprocess.getUserList()) {
                if (user.getUser_id() == Integer.parseInt(arguments[2])) {
                    target = user;
                }
            }
        }
        System.out.println(rhandler.handleSimilar(arguments, uprocess.getUserTree(), target));
    }
}
