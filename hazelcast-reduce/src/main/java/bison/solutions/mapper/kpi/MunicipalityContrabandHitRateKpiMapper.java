package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;


public class MunicipalityContrabandHitRateKpiMapper implements Mapper<String, Feature, String, Feature> {
    private String municipality;

    public MunicipalityContrabandHitRateKpiMapper(String municipality) { this.municipality = municipality; }

    @Override
    public void map(String s, Feature feature, Context<String, Feature> context) {
        if (feature.properties.court_name.equals(municipality)) {

            Feature newFeature = new Feature();
            newFeature.properties.asian_contraband_hit_rate = feature.properties.asian_contraband_hit_rate;
            newFeature.properties.black_contraband_hit_rate = feature.properties.black_contraband_hit_rate;
            newFeature.properties.indian_contraband_hit_rate = feature.properties.indian_contraband_hit_rate;
            newFeature.properties.white_contraband_hit_rate = feature.properties.white_contraband_hit_rate;
            newFeature.properties.hispanic_contraband_hit_rate = feature.properties.hispanic_contraband_hit_rate;
            newFeature.properties.other_contraband_hit_rate = feature.properties.other_contraband_hit_rate;

            context.emit(municipality, newFeature);
        }
    }
}
