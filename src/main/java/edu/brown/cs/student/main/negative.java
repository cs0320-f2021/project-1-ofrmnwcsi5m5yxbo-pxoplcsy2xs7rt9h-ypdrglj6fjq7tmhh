package edu.brown.cs.student.main;

import java.util.Map;

public class negative {
    int id;
    String trait;

    public negative(Map<String, String> inputs) {
        this.id = Integer.parseInt(inputs.get("id"));
        this.trait = inputs.get("trait");
    }
}

