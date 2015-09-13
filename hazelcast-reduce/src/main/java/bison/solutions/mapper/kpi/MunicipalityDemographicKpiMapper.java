package bison.solutions.mapper.kpi;
import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MunicipalityDemographicKpiMapper implements Mapper<String, Feature, String, StringStringWrapperFacade> {
    private String municipality;

    public MunicipalityDemographicKpiMapper(String municipality) { this.municipality = municipality; }

    @Deprecated
    public void map(String s, Feature feature, Context<String, StringStringWrapperFacade> context) {
        if (feature.properties.court_name.equals(municipality)) {

            StringStringWrapperFacade stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Total Population";
            stringStringWrapperFacade.thing1 = feature.properties.total_population;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "demographics_asian_percentage";
            stringStringWrapperFacade.thing1= feature.properties.demographics_asian_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "demographics_black_percentage";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_black_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "demographics_white_percentage";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_white_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "demographics_other_percentage";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_other_percentage;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "demographics_below_poverty_percentage";
            stringStringWrapperFacade.thing1 = feature.properties.demographics_below_poverty_percentage;
            context.emit(municipality, stringStringWrapperFacade);
        }
    }
}
