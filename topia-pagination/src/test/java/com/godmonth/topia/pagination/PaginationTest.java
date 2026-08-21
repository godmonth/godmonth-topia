package com.godmonth.topia.pagination;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


public class PaginationTest {

    @Test
    public void map() {
        Pagination<String> p = new Pagination<>(10, 10, new SortPageParam(5, 5), Arrays.asList("1", "2", "3"));
        System.out.println(p);
        Pagination<Integer> map = p.map(Integer::parseInt);
        System.out.println(map);
        Assertions.assertIterableEquals(map.getItems(), Arrays.asList(1, 2, 3));
    }
}
