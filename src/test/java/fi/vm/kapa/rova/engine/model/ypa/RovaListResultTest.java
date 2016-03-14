package fi.vm.kapa.rova.engine.model.ypa;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RovaListResultTest {

    @Test
    public void testHappyCreation() {
        String single = "single";
        RovaListResult<String> list = new RovaListResult<>(10, 0, 2, Collections.singletonList(single));
        assertEquals(1, list.size());
        assertEquals(10, list.getTotal());
        assertEquals(0, list.getOffset());
        assertEquals(2, list.getLimit());
    }

    @Test
    public void testHappyGet() {
        List<String> fullList = Arrays.asList(new String[]{"first", "second", "third"});
        RovaListResult<String> list = new RovaListResult<>(10, 0, 2, Collections.singletonList("first"));

        list = new RovaListResult<>(10, 2, 2, fullList);
    }

}