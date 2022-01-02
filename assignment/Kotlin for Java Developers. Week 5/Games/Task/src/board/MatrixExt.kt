package board

inline fun <reified T> createMatrixWithInitFun(w: Int, h: Int, fn: (Int, Int) -> T): Array<Array<T>> =
    Array(w) { i -> Array(h) { j -> fn(i, j) } }

inline fun <reified T> createAdjList(w: Int) : MutableList<MutableList<T>> {
    val mutableList: MutableList<MutableList<T>> = mutableListOf()
    for (i in 0 .. w) {
        mutableList[i].addAll(emptyList())
    }
    return mutableList
}

operator fun <T> Array<Array<T>>.get(i: Int, j: Int) = this[i][j]

operator fun <T> Array<Array<T>>.set(i: Int, j: Int, value: T) {
    this[i][j] = value
}

operator fun <T> MutableList<MutableList<T>>.get(i: Int, j: Int) = this[i][j]

operator fun <T> MutableList<MutableList<T>>.set(i: Int, j: Int, value: T) {
    this[i][j] = value
}