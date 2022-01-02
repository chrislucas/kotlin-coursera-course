package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list
 * (by removing nulls) and merges equal elements.
 * The parameter 'merge' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the resulting list
 * instead of two merged elements.
 *
 * If the function 'merge("a")' returns "aa",
 * then the function 'moveAndMergeEqual' transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
 *
 * You can find more examples in 'TestGame2048Helper'.
*/
fun <T : Any> List<T?>.moveAndMergeEqual(merge: (T) -> T): List<T> =
    with(this) {
        val filtered = this.asSequence()
            .filterNotNull()
            .toMutableList()

        fun mergeAdjacentEquals() {
            var idx = 0
            while (idx < filtered.size - 1) {
                if (filtered[idx] == filtered[idx + 1]) {
                    filtered[idx] = merge(filtered[idx])
                    filtered.removeAt(idx + 1)
                }
                idx += 1
            }
        }

        mergeAdjacentEquals()

        filtered.toList()
    }


