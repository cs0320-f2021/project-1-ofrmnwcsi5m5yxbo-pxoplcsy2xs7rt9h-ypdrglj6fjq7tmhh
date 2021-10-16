package edu.brown.cs.student.main;

import java.util.Map;

public class skills {
    int id;
    String name;
    int commenting;
    int testing;
    int oop;
    int algorithms;
    int teamwork;
    int frontend;

    public skills(Map<String, String> inputs) {
        this.id = Integer.parseInt(inputs.get("id"));
        this.name = inputs.get("name");
        this.commenting = Integer.parseInt(inputs.get("commenting"));
        this.testing = Integer.parseInt(inputs.get("testing"));
        this.oop = Integer.parseInt(inputs.get("oop"));
        this.algorithms = Integer.parseInt(inputs.get("algorithms"));
        this.teamwork = Integer.parseInt(inputs.get("teamwork"));
        this.frontend = Integer.parseInt(inputs.get("frontend"));
    }
}
