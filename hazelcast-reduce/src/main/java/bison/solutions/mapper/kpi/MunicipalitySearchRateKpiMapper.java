package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;


public class MunicipalitySearchRateKpiMapper implements Mapper<String, Feature, String, Feature> {
    private String municipality;
    
    public MunicipalitySearchRateKpiMapper(String municipality) { this.municipality = municipality; }

    @Override
    public void map(String s, Feature feature, Context<String, Feature> context) {
        if (feature.properties.court_name.equals(municipality)) {

            Feature newFeature = new Feature();
            newFeature.properties.asian_search_rate = feature.properties.asian_search_rate;
            newFeature.properties.black_search_rate = feature.properties.black_search_rate;
            newFeature.properties.indian_search_rate = feature.properties.indian_search_rate;
            newFeature.properties.white_search_rate = feature.properties.white_search_rate;
            newFeature.properties.hispanic_search_rate = feature.properties.hispanic_search_rate;
            newFeature.properties.other_search_rate = feature.properties.other_search_rate;

            context.emit(municipality, newFeature);
        }
    }
}
