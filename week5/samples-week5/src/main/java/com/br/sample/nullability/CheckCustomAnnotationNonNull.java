package com.br.sample.nullability;

public class CheckCustomAnnotationNonNull {

    public void show(String value) {
        /**
         *   @see ParameterNonNullDefault
            Por conta da anotacao ParameterNonNullDefault que foi adicionada no
            arquivo package-info.java o compilador nos da essa informacao abaixo.
            - Condition 'value != null' is always 'true'
            Como se fosse desnecessario validar se o argumento passado é nulo ou nao
         */
        //if (value != null) {
            System.out.println(value);
        //}
    }

    public static void main(String[] args) {
        CheckCustomAnnotationNonNull c = new CheckCustomAnnotationNonNull();
        c.show(null);
        c.show("value");
    }
}
