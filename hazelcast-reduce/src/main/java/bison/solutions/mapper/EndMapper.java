package bison.solutions.mapper;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;

public class EndMapper<KeyIn, ValueIn> implements Mapper<KeyIn, ValueIn, KeyIn, ValueIn> {

    @Override
    public void map(KeyIn keyIn, ValueIn valueIn, Context<KeyIn, ValueIn> context) {
        context.emit(keyIn, valueIn);
    }
}
