package com.br.sample.getindexer

interface PageMetadata<T> {
    fun pageNumber(): Int
    fun pageSize(): Int
    fun elements(): MutableList<T>

    fun isValidIndex(index: Int) = (index >= 0 && index < Int.MAX_VALUE && index < this.elements().size)

    // overloading uma funcao membro
/*
    // Estudar o porque quando definimos o retorno para T o codigo nao compila
    // e quando deixamos o compilador inferir, ao tentar usar o operador temos
    // problemas de inferencia pelo compilador e somos obrigados a usar a funcao get explicitamente
    operator fun <T> get(index: Int) : T =
        if (isValidIndex(index)) {
            elements()[index]
        } else {
            throw IllegalArgumentException("index value must to be > 0 < Int.MAX")
        }
*/

    operator fun <T> get(index: Int) =
        if (isValidIndex(index)) {
            elements()[index]
        } else {
            throw IllegalArgumentException("index value must to be > 0 < Int.MAX")
        }

    operator fun <T> get(startIndex: Int, endInclusiveIndex: Int) =
        if (isValidIndex(startIndex) && isValidIndex(endInclusiveIndex+1)) {
            elements().subList(startIndex, endInclusiveIndex+1)
        } else {
            throw IllegalArgumentException(DEFAULT_MESSAGE_ILLEGAL_ARG_INDEX)
        }


}