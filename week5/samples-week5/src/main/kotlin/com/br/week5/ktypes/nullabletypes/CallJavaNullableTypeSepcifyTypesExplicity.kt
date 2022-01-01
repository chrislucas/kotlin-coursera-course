package com.br.week5.ktypes.nullabletypes

import com.br.sample.nullability.SampleNullableType


/*
    Definir o tipo explicitamente evita NPE
 */
private fun checkSpecifyExplicitTypeAsNullable() {
    val sample = SampleNullableType()
    /*
        Como a funcao getType retorna um null de proposito, esse método irá funciona
     */
    val type: String? = sample.type
    println(type?.length)

}

private fun checkSpecifyExplicitTypeAsNotNull() {
    val sample = SampleNullableType()
    /*
        Aqui veremos um erro, pois a funcao getType() retorna de propósito
        null, para que pudessemos testar algumas ideias como
            1) Anotacoes que forçam que métodos de  classes de um determinado pacote nao aceitem receber nulos
                - a principio o compilador emite simplesmente um warning
                - podemos configurar o compilador, através de um script gradle para que esse warning se transforme num
                erro em tempo de compilacao
            2) Evitar NPE definindo explicitamente o tipo de uma variável
                - se for do tipo nullable, o kotlin nos força em tempo de compilacao a usar o operador null-safety
                - se for nonnull, é muito improvável que ocorra um NPE
     */
    val type: String = sample.type
    /*
        Ao definir explicitamente que o tipo da minha variavel como nao nulo, o codigo gerado pelo compilador cria
        um método para verificar se o tipo de fato e nao nulo
        Veja a funcao descompilada em Java

            private static final void checkSpecifyExplicitTypeAsNotNull() {
                SampleNullableType sample = new SampleNullableType();
                String string = sample.getType();
                // aqui esta o metodo que checa se o tipo e nao nulo
                Intrinsics.checkNotNullExpressionValue((Object)string, (String)"sample.type");
                String type = string;
                System.out.println(type.length());
            }

        Olhando a implementacao do metodo checkNotNull...
            public static void checkNotNullExpressionValue(Object value, String expression) {
                if (value == null) {
                    throw sanitizeStackTrace(new NullPointerException(expression + " must not be null"));
                }
            }

            entendemos pq um NPE eh lancado
     */
    println(type.length)

}

private fun checkMethodMustNotReturnNull() {
    val sample = SampleNullableType()
    val returnedValue = sample.mustNotReturnNull()
    if (returnedValue != null) {
        println(returnedValue)
    }
}

fun main() {
    checkSpecifyExplicitTypeAsNotNull()
}