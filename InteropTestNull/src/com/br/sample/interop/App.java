package com.br.sample.interop;

import java.util.Random;

public class App {

    private static final Random r = new Random();

    public static void main(String[] args) {
        for (int i=0; i<100; i++) {
            var p = providePoint2D();
            // A IDE nos ajuda informando que eh melhor conter um IF, pois o metodo em kotlin
            // nao espera uma variavel nullable
            if (p == null)
                continue;
            SampleKt.show(p);
        }
    }


    private static Point providePoint2D() {
        return r.nextInt() % 2 == 1 ? null : new Point(0, 0);
    }
}
