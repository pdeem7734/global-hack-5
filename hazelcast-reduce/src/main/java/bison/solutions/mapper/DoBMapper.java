package bison.solutions.mapper;

import bison.solutions.domain.Citation;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

import java.util.Date;

public class DoBMapper implements Mapper<String, Citation, String, Citation> {
    Mapper<String, Citation, String, Citation> delegationMapper;
    Date dateOfBirth;

    public DoBMapper(Date dateOfBirth, Mapper<String, Citation, String, Citation> delegationMapper) {
        this.delegationMapper = delegationMapper;
        this.dateOfBirth = dateOfBirth;

    }

    @Override
    public void map(String s, Citation citation, Context<String, Citation> context) {
        if (dateOfBirth.equals(citation.getDateOfBirth())) {
            context.emit(s, citation);
        }
    }
}
