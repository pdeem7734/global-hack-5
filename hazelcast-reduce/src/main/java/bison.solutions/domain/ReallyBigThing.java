package bison.solutions.domain;

import bison.solutions.domain.smaller.things.Feature;

import java.util.List;

public class ReallyBigThing {
    private String name;
    private String type;
    private List<Feature> features;

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public String get_type() {
        return type;
    }

    public void set_type(String type) {
        this.type = type;
    }

    public List<Feature> get_features() {
        return features;
    }

    public void set_features(List<Feature> features) {
        this.features = features;
    }
}
