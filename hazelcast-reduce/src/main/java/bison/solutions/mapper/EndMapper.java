package bison.solutions.mapper;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import sun.nio.cs.KOI8_R;

public class EndMapper<KeyIn, ValueIn> implements Mapper<KeyIn, ValueIn, KeyIn, ValueIn> {

    @Override
    public void map(KeyIn keyIn, ValueIn valueIn, Context<KeyIn, ValueIn> context) {
        context.emit(keyIn, valueIn);
    }
}
