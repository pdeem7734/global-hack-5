package bison.solutions.mapper.kpi;
import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MunicipalityDemographicKpiMapper implements Mapper<String, Feature, String, StringStringWrapperFacade> {
    private String municipality;

    public MunicipalityDemographicKpiMapper(String municipality) { this.municipality = municipality; }

    @Override
    public void map(String s, Feature feature, Context<String, StringStringWrapperFacade> context) {
        if (feature.properties.court_name.equals(municipality)) {

            StringStringWrapperFacade stringStringWrapperFacade = new StringStringWrapperFacade();

            stringStringWrapperFacade.thing0 = "Asian";
            stringStringWrapperFacade.thing1= feature.properties.demographics_asian_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Black";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_black_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "White";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_white_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Other";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_other_percentage;
            context.emit(municipality, stringStringWrapperFacade);

        }
    }
}
