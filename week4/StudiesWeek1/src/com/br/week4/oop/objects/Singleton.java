package com.br.week4.oop.objects;

public class Singleton {

    public static Singleton INSTANCE = new Singleton();

    private Singleton() {}

    public void log() {
        System.out.println("I am a singleton class");
    }
}
