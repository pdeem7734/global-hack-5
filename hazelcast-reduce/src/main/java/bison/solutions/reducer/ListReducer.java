package bison.solutions.reducer;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;

import java.util.LinkedList;
import java.util.List;

public class ListReducer<KeyIn, ValueIn> implements ReducerFactory<KeyIn, ValueIn, List<ValueIn>> {

    @Override
    public Reducer<ValueIn, List<ValueIn>> newReducer(KeyIn keyIn) {
        return new FinalReducer();
    }

    class FinalReducer extends Reducer<ValueIn, List<ValueIn>> {
        List<ValueIn> valueList = new LinkedList<>();

        @Override
        public void reduce(ValueIn valueIn) {
            valueList.add(valueIn);
        }

        @Override
        public List<ValueIn> finalizeReduce() {
            return valueList;
        }
    }
}
