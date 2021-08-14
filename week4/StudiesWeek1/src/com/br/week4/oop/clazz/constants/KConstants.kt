package com.br.week4.oop.clazz.constants

import com.br.week4.oop.clazz.modifiers.data.Point2f


/**
 * Em java se uma constante e definida como um dos tipos primitvos ou uma String
 * e o valor dela eh conhecido em tempo de compilacao, o compilador ira substituir
 * o uso da variavel pelo proprio valor em todo o código -
 *
 * ISSO EH CHAMADO DE COMPILE-TIME CONSTANT
 *
 * O compilador kotlin faz o mesmo
 *
 * */

const val INACCURATE_PI = 22.0 / 7


/**
 * a anotacao @JvmField expoem uma propriedade ou membro de uma classe
 * como um field. assim esse campo pode ser acessado do codigo Java
 * sem a necessidade de usar um getter
 *
 * */
@JvmField
val TOP_LEVEL_ORIGIN = Point2f(0.0, 0.0)


/**
 * Podemos usar a anotacao JvmField para tipos primitivos
 * e Strings, mas o compilador nos adverte para o uso
 * da palavra reservada const
 *
 *

public final static Ljava/lang/String; GREETING = "Hello World"
@Lorg/jetbrains/annotations/NotNull;() //

 ao utilizar a anotacao em uma variavel read-only, nao sera criado metodo
 getter, se for uma variavel mutavel nao sera criado um metodo setter

 Aplicar @JvmField numa variavel top-level eh o mesmo que definir uma variavel
 static field: static final GREETING = "Hello World]
 ou
 static final Point2f TOP_LEVEL_ORIGIN = new Point2f(0.0, 0.0)

 Podemos querer eliminar metodos de acesso por questoes de performance
 * */
@JvmField
val GREETING = "Hello World"

/**
 * 'const' might be used instead of '@JvmField'
 * */
@JvmField
val PRIMITIVE_TOP_LEVEL_DOUBLE_TYPE_FIELD = INACCURATE_PI / 7.0

const val PRIMITIVE_TOP_LEVEL_DOUBLE_CONST = INACCURATE_PI / 7.0

class KConstants {

    /**
     * as variaveis que recebem a anotacao JvmField poderao
     * ser acessadas via Java com a sintaxe INSTANCE.field/property
     *
     * as que nao recebem vao ser acessadas com a sintaxe INSTANCE.getter()
     * */
    @JvmField
    val origin = Point2f(0.0, 0.0)

    @JvmField
    var mutableOrigin = Point2f(origin)

    /**
     * Para esse membro será criado um getter para que ele
     * possa ser acessado
     * */
    val hash = 0xff
}