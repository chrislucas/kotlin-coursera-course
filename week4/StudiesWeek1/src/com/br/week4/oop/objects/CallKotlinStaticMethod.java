package com.br.week4.oop.objects;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class CallKotlinStaticMethod {

    private static void checkStaticMethodsInKotlin() {

        /**
         * Para acessar um metodo de um companion object com a sintaxe de um metodo estatico, eh necessairo
         * adiconar a anotacao @JvmStatic no metodo
         * */
        CheckStaticMethodCompanionObject.Companion.a();

        CheckStaticMethodCompanionObject.b();
        // nao a necessidade de usar Companion
        CheckStaticMethodCompanionObject.Companion.b();

        CheckStaticMethodObject.INSTANCE.a();

        CheckStaticMethodObject.b();

        // Static member 'com.br.week4.oop.objects.CheckStaticMethodObject.b()' accessed via instance reference
        CheckStaticMethodObject.INSTANCE.b();
    }

    private static void checkNestedObject() {
        OuterClass.NestedObject.INSTANCE.invokeSomething(() -> {
            System.out.println(0xff);
            return Unit.INSTANCE;
        });
    }

    private static void checkNestedObjectII() {
        CheckStaticMethodObject.NestedObject.INSTANCE.haveFun( () ->  {
            System.out.println("Have Fun with Kotlin");
            return Unit.INSTANCE;
        });
    }

    public static void main(String[] args) {
        checkNestedObjectII();
    }
}
