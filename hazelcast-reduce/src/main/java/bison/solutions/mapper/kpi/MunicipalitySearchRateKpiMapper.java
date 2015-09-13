package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;


public class MunicipalitySearchRateKpiMapper implements Mapper<String, Feature, String, StringStringWrapperFacade> {
    private String municipality;
    
    public MunicipalitySearchRateKpiMapper(String municipality) { this.municipality = municipality; }

    @Override
    public void map(String s, Feature feature, Context<String, StringStringWrapperFacade> context) {
        if (feature.properties.court_name.equals(municipality)) {

            StringStringWrapperFacade stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Asian Search Rate";
            stringStringWrapperFacade.thing1 = feature.properties.asian_search_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Black Search Rate";
            stringStringWrapperFacade.thing1 = feature.properties.black_search_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Indian Search Rate";
            stringStringWrapperFacade.thing1 = feature.properties.indian_search_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "White Search Rate";
            stringStringWrapperFacade.thing1 = feature.properties.white_search_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Hispanic Search Rate";
            stringStringWrapperFacade.thing1 = feature.properties.hispanic_search_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Other Search Rate";
            stringStringWrapperFacade.thing1 = feature.properties.other_search_rate;
            context.emit(municipality, stringStringWrapperFacade);
        }
    }
}
