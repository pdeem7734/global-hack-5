package bison.solutions.mapper;

import bison.solutions.domain.Citation;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class LicenseMapper implements Mapper<String, Citation, String, Citation> {
    Mapper<String, Citation, String, Citation> delegationMapper;
    String licenseNumber;

    public LicenseMapper(String licenseNumber, Mapper<String, Citation, String, Citation> delegationMapper) {
        this.licenseNumber = licenseNumber;
        this.delegationMapper = delegationMapper;

    }

    @Override
    public void map(String s, Citation citation, Context<String, Citation> context) {
        if (licenseNumber.equals(citation.getDriversLicenseNumber())) {
            context.emit(s, citation);
        }
    }
}
