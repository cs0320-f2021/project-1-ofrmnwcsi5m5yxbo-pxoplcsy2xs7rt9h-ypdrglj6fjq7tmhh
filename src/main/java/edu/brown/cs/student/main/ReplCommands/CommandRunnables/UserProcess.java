package edu.brown.cs.student.main.ReplCommands.CommandRunnables;

import edu.brown.cs.student.main.DataHandling.DataTypes.User;
import edu.brown.cs.student.main.ReplCommands.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class UserProcess implements ReplRunnable {
    private KDTree<User> kdTree = null;
    private List<User> users = new ArrayList<>();
    private ReplHandler rhandler = new ReplHandler();

    public void runCommand(String[] arguments) throws IOException {
        users = rhandler.handleUsers(arguments);
        Collections.sort(users, new SortByWeight());
        int middle = (int) users.size() / 2;
        Node<User> root = new Node(users.get(middle));
        ArrayList<Comparator<User>> comps = new ArrayList<>();
        comps.add(new SortByWeight());
        comps.add(new SortByHeight());
        comps.add(new SortByAge());
        kdTree = new KDTree<>(3, root, comps);
        users.remove(middle);
        int inserted = 1;
        for (User user : users) {
            kdTree.addNode(root, new Node(user));
            inserted++;
        }
        System.out.println("Read " + inserted  + " users from " + arguments[1]);
    }
    public KDTree<User> getUserTree() {
        return kdTree;
    }
    public List<User> getUserList() {
        return users;
    }
}
