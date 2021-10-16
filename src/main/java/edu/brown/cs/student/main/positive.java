package edu.brown.cs.student.main;

import java.util.Map;

public class positive {
    int id;
    String trait;

    public positive(Map<String, String> inputs) {
        this.id = Integer.parseInt(inputs.get("id"));
        this.trait = inputs.get("trait");
    }
}
