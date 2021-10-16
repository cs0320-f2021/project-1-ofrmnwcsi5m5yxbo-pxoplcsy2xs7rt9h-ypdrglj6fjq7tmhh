package edu.brown.cs.student.main;

import java.util.Map;

public class interests {
    int id;
    String interest;

    public interests(Map<String, String> inputs) {
        this.id = Integer.parseInt(inputs.get("id"));
        this.interest = inputs.get("interest");
    }
}
