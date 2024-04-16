package com.blog;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainUtil {
    public static void main(String[] args) {
        List<Integer> data = Arrays.asList(5,5,6,7,2,9,6,7,8);
        List<Integer> collect = data.stream().distinct().collect(Collectors.toList());
        System.out.print(collect);


    }
}
