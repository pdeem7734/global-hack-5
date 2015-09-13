package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;


public class MunicipalityContrabandHitRateKpiMapper implements Mapper<String, Feature, String, StringStringWrapperFacade> {
    private String municipality;

    public MunicipalityContrabandHitRateKpiMapper(String municipality) { this.municipality = municipality; }

    @Override
    public void map(String s, Feature feature, Context<String, StringStringWrapperFacade> context) {
        if (feature.properties.court_name.equals(municipality)) {

            StringStringWrapperFacade stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "asian_contraband_hit_rate";
            stringStringWrapperFacade.thing1 = feature.properties.asian_contraband_hit_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "black_contraband_hit_rate";
            stringStringWrapperFacade.thing1 = feature.properties.black_contraband_hit_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "indian_contraband_hit_rate";
            stringStringWrapperFacade.thing1 = feature.properties.indian_contraband_hit_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "white_contraband_hit_rate";
            stringStringWrapperFacade.thing1 = feature.properties.white_contraband_hit_rate;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "hispanic_contraband_hit_rate";
            stringStringWrapperFacade.thing1 = feature.properties.hispanic_contraband_hit_rate;

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "other_contraband_hit_rate";
            stringStringWrapperFacade.thing1 = feature.properties.other_contraband_hit_rate;
            context.emit(municipality, stringStringWrapperFacade);
        }
    }
}
