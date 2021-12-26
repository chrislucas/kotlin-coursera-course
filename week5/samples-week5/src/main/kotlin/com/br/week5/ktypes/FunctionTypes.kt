package com.br.week5.ktypes

/*
    Kotlin           -         Java
    () -> Boolean              Function0<Boolean>
    (Order) -> Int             Function1<Order, Int>
    (Int, Int) -> Int          Function2<Int, Int, Int>

    A menos que a funcao lambda seja inline elas se tornaram implementacoes
    anonimas de Function interfaces.

    fun sample(lambda: () -> Unit)

    como sample nao eh inline o bytecode em java sera escrito
    solicitando uma instancia de
    function interface Function0<R>
 */

/*
    Decompiled
     private static final void checkLambda(Function0 lambda) {
      lambda.invoke();
    }

    O interessante aqui é que a interface passada por argumento
    executa o metodo invoke()
 */

private fun checkLambda(lambda: () -> Unit) = lambda()

/*
    private final static checkLambda1(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Z

    private static final boolean checkLambda1Arg(Object value, Function1 lambda) {
      return (Boolean)lambda.invoke(value);
   }

   Olha o metodo invoke com um argumento sendo executado
 */
private fun <T> checkLambda1Arg(value: T, lambda: (T) -> Boolean) = lambda(value)

/*
    private final static checkLambda2(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;

    private static final Object checkLambda2args(Object p, Object q, Function2 lambda) {
      return lambda.invoke(p, q);
   }
 */
private fun <P, Q, R> checkLambda2args(p: P, q: Q, lambda: (P, Q) -> R) = lambda(p, q)

/*
   Decompiled
   private static final Object checkInlineLambda2args(Object p, Object q, Function2 lambda) {
      int $i$f$checkInlineLambda2args = 0;
      return lambda.invoke(p, q);
   }
 */
private inline fun <P, Q, R> checkInlineLambda2args(p: P, q: Q, lambda: (P, Q) -> R) = lambda(p, q)


fun main() {
    checkLambda {
        println(0xff)
    }
    checkLambda1Arg(10) { it > 1 }

    checkLambda2args(10, 3) { p, q -> p xor q }

    /*
        Decompiled uma funcao inline
          byte p$iv = 10;
          int q$iv = 3;
          int $i$f$checkInlineLambda2args = false;
          int var5 = false;
          int var10000 = p$iv & q$iv;
     */
    checkInlineLambda2args(10, 3) { p, q -> p and q }
}