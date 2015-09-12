package bison.solutions.mapper;

import bison.solutions.domain.Citation;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class FirstAndLastNameMapper implements Mapper<String, Citation, String, Citation> {
    Mapper<String, Citation, String, Citation> delegationMapper;
    String firstName;
    String lastName;

    public FirstAndLastNameMapper(String firstName, String lastName, Mapper<String, Citation, String, Citation> delegationMapper) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.delegationMapper = delegationMapper;

    }

    @Override
    public void map(String s, Citation citation, Context<String, Citation> context) {
        if (citation.getFirstName().equals(firstName) && citation.getLastName().equals(lastName)) {
            context.emit(s, citation);
        }
    }
}
