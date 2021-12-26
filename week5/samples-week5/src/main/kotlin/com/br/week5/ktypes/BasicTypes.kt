package com.br.week5.ktypes

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/GzTa3/basic-types
    - Em kotlin nao existe tipos primitivos, todos sao tipos de referencia
        - Int(?), Long(?), Boolean(?)
 */

/*
    Procure por "show kotlin bytecode" no menu help> find action

      private static Ljava/lang/Integer; sample
        @Lorg/jetbrains/annotations/Nullable;() // invisible

    Decompile
       @Nullable
       private static Integer sample;

       @Nullable
       public static final Integer getSample() {
          return sample;
       }

       public static final void setSample(@Nullable Integer var0) {
          sample = var0;
       }

     Correspondencia entre tipos em java kotlin java
     K    - J
     Int -> int
     Int? -> Integer
     Isso ocorre para os demais tipos primitivos de Java

     Como em java nao eh possivel criar colecoes de tipos primitivos
     pq parametros genericos nao permitem fazer isso, segue assim a correspondencia
     List<Int> = List<Integer>
     Array<Int> = Integer[]

     Se quisermos um array de tipos primitivos temos
     IntArray = int []
     DoubleArray = double []

     Any -> Object

     Any eh um supertipo para tipos Nao nulos e diferente de Java que cuja classe
     Object e o supertipo de tipos de referencia, Any é um supertipo de classes
     como Int, Double e todos os outros tipos definidos na linguagem
 */

var nullableIntType: Int? = null

/*
      private final static [Z booleanTable
        @Lorg/jetbrains/annotations/Nullable;()
 */
val booleanTable: BooleanArray? = null
val intTable: IntArray? = null
val doubleTable: DoubleArray? = null

fun main() {
    println(nullableIntType)
    println(booleanTable)
    println(intTable)
}