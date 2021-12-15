package com.br.week5.inlinefun.exercise.filter;


import java.util.ArrayList;
import java.util.List;

public class CheckInlineIterable {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            integers.add(i);
        }

        /*
            funcoes inline kotlin nao sao copiadas e substituidas as suas chamadas
            pelos seu corpo, inline eh uma feature do compilador koltin
         */
        IterableFilterKt.filter(integers, integer -> (integer & 1) == 1);

        for (int i : integers) {
            System.out.println(i);
        }
    }
}
