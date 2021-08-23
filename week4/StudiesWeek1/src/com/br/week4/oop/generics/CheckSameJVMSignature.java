package com.br.week4.oop.generics;

import java.util.ArrayList;

public class CheckSameJVMSignature {

    public static void main(String[] args) {
        ArrayList<Double> numbers = new ArrayList<>();
        SameJVMSignatureKt.averageDouble(numbers);
    }
}
