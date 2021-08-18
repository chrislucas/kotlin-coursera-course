package com.br.week4.oop.clazz.constants

import com.br.week4.oop.clazz.modifiers.data.Point2f

object ObjConstants {

    const val ZERO = 0.0

    /**
     * se usarmos JvmField numa variavel top-level de um Object um static field sera gerado
     *
     * bytecode
     *   public final static Lcom/br/week4/oop/clazz/modifiers/data/Point2f; origin
     *
     *   Essa eh a forma de expor o field sem precisar de metodos de acesso
     * */
    @JvmField
    val origin = Point2f(0.0, 0.0)

    /**
     * fields sao por padrao privates.  Ao tentarmos acessa-lo
     * via codigo java so sera possivel via seu metodo getter que sera gerado
     *
     * bytecode
     *   private final static Lcom/br/week4/oop/clazz/modifiers/data/Point2f; constantOrigin
     *
     *   como esse membro se tonar privado em bytecode, um getter se faz necessario
     *
     *     public final getConstantOrigin()Lcom/br/week4/oop/clazz/modifiers/data/Point2f;
     *     @Lorg/jetbrains/annotations/NotNull;() // invisible
     *
     *     sem o @JvmStatic precisamos de uma instancia de ObjConstants para acessar esse getter
     *     por ser um metodo de instancia.
     *
     *     O compilador kotlin gera uma classe chamada ObjConstants a partir desse objeto e um atributo ESTATICO que
     *     guardar a referencia para uma instancia da probria clase
     *
     *       public final static Lcom/br/week4/oop/clazz/constants/ObjConstants; INSTANCE
     *       @Lorg/jetbrains/annotations/NotNull;()
     * */
    val constantOrigin = Point2f(1.0, 1.0)

    /**
     *
     *   private final static Lcom/br/week4/oop/clazz/modifiers/data/Point2f; constantStaticOrigin
     *   @Lorg/jetbrains/annotations/NotNull;
     *
     *     public final static getConstantStaticOrigin()Lcom/br/week4/oop/clazz/modifiers/data/Point2f;
     *     @Lorg/jetbrains/annotations/NotNull;() // invisible
     *
     *     Aqui o getter gerado pelo compilador eh ESTATICO, nao precisando de uma instancia da classe
     *     para acessa-lo
     * */
    @JvmStatic
    val constantStaticOrigin = Point2f(2.0, 2.0)
}


class ClassConstants {



    /**
     *
     * Se utilizarmos JvmField numa variavel top-level de uma classe regular uma regular field
     * sera gerado
     * */
    @JvmField
    val origin = Point2f(0.0, 0.0)

    val otherOrigin = Point2f(1.0, 1.0)

    // um object com nome
    internal object StaticOrigin {
        /**
         * @JvmStatic so pode ser usardo dentro de um Object
         * */
        @JvmStatic
        val staticOrigin = Point2f(2.0, 2.0)
    }

    // object sem nome
    companion object {
        @JvmStatic
        val staticOrigin = Point2f(2.0, 2.0)

        const val ZERO = 0.0
    }
}