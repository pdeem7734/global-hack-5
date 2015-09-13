package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;


public class MunicipalityVehicleStopKpiMapper implements Mapper<String, Feature, String, StringStringWrapperFacade> {
    private String municipality;

    public MunicipalityVehicleStopKpiMapper(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public void map(String s, Feature feature, Context<String, StringStringWrapperFacade> context) {
        if (feature.properties.court_name.equals(municipality)) {

            StringStringWrapperFacade stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Total Population";
            stringStringWrapperFacade.thing1 = feature.properties.total_population;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Asian Vehicle Stops";
            stringStringWrapperFacade.thing1 = feature.properties.asian_vehicle_stops;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Black Vehicle Stops";
            stringStringWrapperFacade.thing1 = feature.properties.black_vehicle_stops;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Indian Vehicle Stops";
            stringStringWrapperFacade.thing1 = feature.properties.indian_vehicle_stops;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "White Vehicle Stops";
            stringStringWrapperFacade.thing1 = feature.properties.white_vehicle_stops;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Hispanic Vehicle Stops";
            stringStringWrapperFacade.thing1 = feature.properties.hispanic_vehicle_stops;
            context.emit(municipality, stringStringWrapperFacade);

            stringStringWrapperFacade = new StringStringWrapperFacade();
            stringStringWrapperFacade.thing0 = "Other Vehicle Stops";
            stringStringWrapperFacade.thing1 = feature.properties.other_vehicle_stops;
            context.emit(municipality, stringStringWrapperFacade);
        }
    }
}
