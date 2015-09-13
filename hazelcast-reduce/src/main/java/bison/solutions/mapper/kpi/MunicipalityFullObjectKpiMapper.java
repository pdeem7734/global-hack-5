package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MunicipalityFullObjectKpiMapper implements  Mapper<String, Feature, String, Feature> {
    private String municipality;

    public MunicipalityFullObjectKpiMapper(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public void map(String s, Feature feature, Context<String, Feature> context) {
        if (feature.properties.court_name.equals((municipality))) {
            context.emit(municipality, feature);
        }
    }

}
