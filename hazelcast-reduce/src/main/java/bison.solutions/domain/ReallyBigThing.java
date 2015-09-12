package bison.solutions.domain;

import bison.solutions.domain.smaller.things.Feature;

import java.io.Serializable;
import java.util.List;

public class ReallyBigThing implements Serializable {
    public String name;
    public String type;
    public List<Feature> features;
}
