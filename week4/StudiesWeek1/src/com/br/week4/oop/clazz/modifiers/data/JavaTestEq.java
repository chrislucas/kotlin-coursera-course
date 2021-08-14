package com.br.week4.oop.clazz.modifiers.data;

public class JavaTestEq {

    public static void main(String[] args) {
        Point2f p = new Point2f(0.0, 1.0);
        Point2f q = new Point2f(p);

        System.out.println(p);
        System.out.println(q);
        System.out.println(p == q);
    }
}
