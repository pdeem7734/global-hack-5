package bison.solutions.mapper.kpi;
import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MunicipalityDemographicKpiMapper implements Mapper<String, Feature, String, Feature> {
    private String municipality;

    public MunicipalityDemographicKpiMapper(String municipality) { this.municipality = municipality; }

    @Deprecated
    public void map(String s, Feature feature, Context<String, Feature> context) {
        if (feature.properties.court_name.equals(municipality)) {

            Feature newFeature = new Feature();
            newFeature.properties.demographics_asian_percentage = feature.properties.demographics_asian_percentage;
            newFeature.properties.demographics_black_percentage = feature.properties.demographics_black_percentage;
            newFeature.properties.demographics_white_percentage = feature.properties.demographics_white_percentage;
            newFeature.properties.demographics_other_percentage = feature.properties.demographics_other_percentage;
            newFeature.properties.demographics_below_poverty_percentage = feature.properties.demographics_below_poverty_percentage;

            context.emit(municipality, newFeature);
        }
    }
}
