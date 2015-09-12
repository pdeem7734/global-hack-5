package bison.solutions.mapper;

import bison.solutions.domain.Citation;
import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import com.hazelcast.mapreduce.impl.task.MappingPhase;

import java.util.Date;

public class CitationNumberMapper implements Mapper<String, Citation, String, Citation> {
    Mapper<String, Citation, String, Citation> delegationMapper;
    Long citationNumber;

    public CitationNumberMapper(long citationNumber, Mapper<String, Citation, String, Citation> delegationMapper) {
        this.delegationMapper = delegationMapper;
        this.citationNumber = citationNumber;

    }

    @Override
    public void map(String s, Citation citation, Context<String, Citation> context) {
        if (citationNumber.equals(citation.getCitationNumber())) {
            context.emit(s, citation);
        }
    }
}
