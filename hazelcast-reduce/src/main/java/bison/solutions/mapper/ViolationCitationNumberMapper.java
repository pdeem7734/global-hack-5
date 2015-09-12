package bison.solutions.mapper;

import bison.solutions.domain.Citation;
import bison.solutions.domain.Violation;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class ViolationCitationNumberMapper implements Mapper<String, Violation, String, Violation> {
    Mapper<String, Citation, String, Citation> delegationMapper;
    Long citationNumber;

    public ViolationCitationNumberMapper(long citationNumber, Mapper<String, Citation, String, Citation> delegationMapper) {
        this.delegationMapper = delegationMapper;
        this.citationNumber = citationNumber;

    }

    @Override
    public void map(String s, Violation violation, Context<String, Violation> context) {
        if (citationNumber.equals(violation.getCitationNumber())) {
            context.emit(s, violation);
        }
    }
}
