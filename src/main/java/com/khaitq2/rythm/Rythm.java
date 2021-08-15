package com.khaitq2.rythm;

import org.rythmengine.RythmEngine;

import java.util.HashMap;
import java.util.Map;

public class Rythm {
    private final RythmEngine rythmEngine;

    public Rythm() {
        Map<String, String> config = new HashMap<>();
        config.put("home.template.dir", System.getProperty("user.dir") + "/src/main/resources/template");
        rythmEngine = new RythmEngine(config);
    }

    public RythmEngine getRythmEngine() {
        return rythmEngine;
    }
}
