package com.br.week4.oop.clazz.constants;

public class InteroperabilityIII {

    private static void testObject() {

        /**
         *
         * origin em bytecode
         *   public final static Lcom/br/week4/oop/clazz/modifiers/data/Point2f; origin
         *
         *   membro ESTATICO e PUBLICO, que pode ser acessado diretamente
         * */

        System.out.println(ObjConstants.origin);
        /**
         *
         * constantOrigin em bytecode
         * private final static Lcom/br/week4/oop/clazz/modifiers/data/Point2f; constantOrigin
         *
         * e seu metodo de acesso
         *   public final getConstantOrigin()Lcom/br/week4/oop/clazz/modifiers/data/Point2f;
         *   @Lorg/jetbrains/annotations/NotNull;()
         * metodo de instancia precisa de uma instancia para acessa-lo
         *
         *   public final static Lcom/br/week4/oop/clazz/constants/ObjConstants; INSTANCE
         * */
        System.out.println(ObjConstants.INSTANCE.getConstantOrigin());

        /**
         * constantStaticOrigin em bytecode
         * private final static Lcom/br/week4/oop/clazz/modifiers/data/Point2f; constantStaticOrigin
         *
         * metodo de acesso
         *   public final static getConstantStaticOrigin()Lcom/br/week4/oop/clazz/modifiers/data/Point2f;
         *
         *   como eh um metodo estatico, nao precisamos de uma instancia da classe ObjConstants para
         *   acessa-lo
         * */
        System.out.println(ObjConstants.getConstantStaticOrigin());
    }

    private static void testClass() {
        ClassConstants c = new ClassConstants();
        System.out.println(c.origin);
        System.out.println(c.getOtherOrigin());
    }

    private static void testStaticMemberClass() {
        System.out.println(ClassConstants.getStaticOrigin());
        System.out.println(ClassConstants.StaticOrigin.getStaticOrigin());
    }

    public static void main(String[] args) {
        testStaticMemberClass();
    }
}
