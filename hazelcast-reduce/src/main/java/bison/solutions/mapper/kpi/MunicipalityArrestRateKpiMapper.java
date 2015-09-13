package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MunicipalityArrestRateKpiMapper implements Mapper<String, Feature, String, Feature> {
    private String municipality;

    public MunicipalityArrestRateKpiMapper(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public void map(String s, Feature feature, Context<String, Feature> context) {
        if (feature.properties.court_name.equals(municipality)) {

            Feature newFeature = new Feature();
            newFeature.properties.asian_arrest_rate = feature.properties.asian_arrest_rate;
            newFeature.properties.black_arrest_rate = feature.properties.black_arrest_rate;
            newFeature.properties.indian_arrest_rate = feature.properties.indian_arrest_rate;
            newFeature.properties.white_arrest_rate = feature.properties.white_arrest_rate;
            newFeature.properties.hispanic_arrest_rate = feature.properties.hispanic_arrest_rate;
            newFeature.properties.other_arrest_rate = feature.properties.other_arrest_rate;

            context.emit(municipality, newFeature);
        }
    }
}
