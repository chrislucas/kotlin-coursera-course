package com.br.week4.oop.clazz.constants;

public class InnerConstantsInteroperabilityIV {

    public static void main(String[] args) {
        /**
         * Aqui o compilador vai trazer o valor dessa constante para o codigo ao ivnes de criar um
         * codigo que acessa a variavel
         *
         * Se fosse usada a annotation @JvmStatic o compilador
         * criaria um codigo que acessaria o metodo getter de forma implicita
         * */
        System.out.println(ObjConstants.ZERO);
        System.out.println(ClassConstants.ZERO);
    }
}
