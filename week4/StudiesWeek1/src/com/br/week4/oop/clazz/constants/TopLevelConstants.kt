package com.br.week4.oop.clazz.constants


/**
 * para esse arquivo o compilador criara uma classe chamada TopLevelConstantsKt
 *
 * bytecode:
 *  public final class com/br/week4/oop/clazz/constants/TopLevelConstantsKt
 *
 * */

/**
 * numa constant definida diretamente no arquivo
 * fora de um objeto ou classe, ou seja uma top-level constant
 * o compilador nos avisa o seguinte
 *
 * 'const' might be used instead of '@JvmField'
 *
 * bytecode
 *     public final static D constant1
 *     @Lkotlin/jvm/JvmField;() // invisible
 *
 * */
@JvmField
val constant1 = 0.0

/**
 * bytecode:
 *    public final static D constant2 = 0.0
 * */
const val constant2 = 0.0

/**
 * para essa variavel top-level sera criado um getter]
 *
 * bytecode:
 *   // access flags 0x1A
 *   private final static D constant3
 *   public final static getConstant3()D
 * */
val constant3 = 0.0