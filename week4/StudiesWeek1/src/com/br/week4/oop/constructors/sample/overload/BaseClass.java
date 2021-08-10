package com.br.week4.oop.constructors.sample.overload;

public class BaseClass {

    private final Context context;
    private final int index;

    public BaseClass(Context context) {
        this(context, 0);
    }

    public BaseClass(Context context, int index) {
        this.context = context;
        this.index = index;
    }

    public static class Context {
        private final int initValue;
        private final String description;
        public Context(String description, int initValue) {
            this.initValue = initValue;
            this.description = description;
        }
    }
}

