package com.br.sample.getindexer


const val DEFAULT_MESSAGE_ILLEGAL_ARG_INDEX = "index value must to be >= 0 < Int.MAX and <= collection.size"

// ou uma extension function
operator fun <T> PageMetadata<T>.get(index: Int): T {
    fun isValidIndex(index: Int) = (index >= 0 && index < Int.MAX_VALUE && index <= this.elements().size)
    return if (isValidIndex(index)) {
        elements()[index]
    } else {
        throw IllegalArgumentException("index value must to be > 0 < Int.MAX")
    }
}

operator fun <T> PageMetadata<T>.get(startIndex: Int, endExclusiveIndex: Int): List<T> {

    fun isValidIndex(index: Int) = (index >= 0 && index < Int.MAX_VALUE && index <= this.elements().size)

    return if (isValidIndex(startIndex) && isValidIndex(endExclusiveIndex + 1)) {
        elements().subList(startIndex, endExclusiveIndex + 1)
    } else {
        throw IllegalArgumentException(DEFAULT_MESSAGE_ILLEGAL_ARG_INDEX)
    }
}

operator fun <T> PageMetadata<T>.iterator() = elements().iterator()

operator fun <T> PageMetadata<T>.set(index: Int, value: T) {
    fun isValidIndex(index: Int) = (index >= 0 && index < Int.MAX_VALUE && index <= this.elements().size)
    if (isValidIndex(index)) {
        throw IllegalArgumentException("index value must to be > 0 < Int.MAX")
    } else {
        elements()[index] = value
    }
}
