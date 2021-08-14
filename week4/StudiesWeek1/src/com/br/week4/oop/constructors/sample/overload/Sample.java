package com.br.week4.oop.constructors.sample.overload;

public class Sample {

    public void log(int value) {
        log(value, 0.0);
    }

    public void log(int value, double decimal) {
        log(value, decimal, new Point2D(0, 0));
    }

    public void log(int value, double decimal, Point2D p) {
        System.out.println(value);
        System.out.println(decimal);
        System.out.println(p);
    }

    public void logInfo(int value) {
        System.out.println(value);
    }

    public void logInfo(int value, double decimal) {
        logInfo(value);
        System.out.println(decimal);
    }

    public void logInfo(int value, double decimal, Point2D p) {
        logInfo(value, decimal);
        System.out.println(p);
    }

    public static class Point2D {
        private final int x;
        private final int y;

        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("P(%d, %d)", x, y);
        }
    }
}
