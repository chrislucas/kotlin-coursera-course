package com.br.week4.oop.clazz.constants;

import com.br.week4.oop.clazz.modifiers.data.Point2f;

public class Interoperability {

    private static void testJvmFieldAnnotation() {
        System.out.println(KConstantsKt.INACCURATE_PI);

        KConstants constants = new KConstants();
        System.out.println(constants.origin);
        // Cannot assign a value to final variable 'origin'
        //constants.origin = new Point2f(-1.0, -1.0);

        System.out.println(constants.mutableOrigin);
        constants.mutableOrigin = new Point2f(1.0,1.0);
        System.out.println(constants.mutableOrigin);
    }

    private static void testTopLevelJvmField() {
        System.out.println(KConstantsKt.TOP_LEVEL_ORIGIN);
    }

    private static void testAccessMemberClass() {
        KConstants kc = new KConstants();
        System.out.println(kc.origin);
        System.out.println(kc.getHash());

    }

    private static void testNonPrimitiveTypeJvmField() {
        System.out.println(KConstantsKt.GREETING);
    }

    private static void testPrimitiveTypeJvmField() {
        System.out.println(KConstantsKt.PRIMITIVE_TOP_LEVEL_DOUBLE_TYPE_FIELD);
        System.out.println(KConstantsKt.PRIMITIVE_TOP_LEVEL_DOUBLE_CONST);
    }

    public static void main(String[] args) {
        testNonPrimitiveTypeJvmField();
        testPrimitiveTypeJvmField();
    }
}
