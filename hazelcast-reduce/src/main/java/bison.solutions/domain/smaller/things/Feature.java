package bison.solutions.domain.smaller.things;

import bison.solutions.domain.smaller.things.tiny.things.Properties;

import java.io.Serializable;

public class Feature implements Serializable {
    public String type;
    public Object geometry;
    public Properties properties;
}
