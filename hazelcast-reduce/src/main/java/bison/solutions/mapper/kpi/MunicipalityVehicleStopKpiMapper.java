package bison.solutions.mapper.kpi;

import bison.solutions.domain.smaller.things.Feature;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class MunicipalityVehicleStopKpiMapper implements Mapper<String, Feature, String, Feature> {
    private String municipality;

    public MunicipalityVehicleStopKpiMapper(String municipality) {
        this.municipality = municipality;
    }

    @Override
    public void map(String s, Feature feature, Context<String, Feature> context) {
        if (feature.properties.court_name.equals(municipality)) {

            Feature newFeature = new Feature();
            newFeature.properties.asian_vehicle_stops = feature.properties.asian_vehicle_stops;
            newFeature.properties.black_vehicle_stops = feature.properties.black_vehicle_stops;
            newFeature.properties.indian_vehicle_stops = feature.properties.indian_vehicle_stops;
            newFeature.properties.white_vehicle_stops = feature.properties.white_vehicle_stops;
            newFeature.properties.hispanic_vehicle_stops = feature.properties.hispanic_vehicle_stops;
            newFeature.properties.other_vehicle_stops = feature.properties.other_vehicle_stops;

            context.emit(municipality, newFeature);
        }
    }
}
