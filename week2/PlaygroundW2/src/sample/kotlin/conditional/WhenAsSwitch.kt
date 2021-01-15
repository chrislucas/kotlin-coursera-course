package sample.kotlin.conditional


enum class Color {
    BLUE, RED, GREEN, YELLOW, ORANGE, PURPLE, ORANGE_RED;

    val cromaticClassification : CromaticClassification
        get() = when (this) {
            BLUE, YELLOW, RED -> CromaticClassification.PRIMARY
            GREEN, ORANGE, PURPLE -> CromaticClassification.SECONDARY
            else -> CromaticClassification.TERTIARY
        }
}

enum class CromaticClassification {
    PRIMARY, SECONDARY, TERTIARY
}

fun Pair<Color, Color>.isSecondaryOrTertiary(): CromaticClassification {
    val (p, q) = this
    return when(setOf(p.cromaticClassification, q.cromaticClassification)) {
        setOf(CromaticClassification.PRIMARY, CromaticClassification.PRIMARY) -> CromaticClassification.SECONDARY
        else -> CromaticClassification.TERTIARY
    }
}

fun main() {
    println("${Color.ORANGE_RED}:${Color.ORANGE_RED.cromaticClassification}")
    println("${Color.YELLOW}:${Color.YELLOW.cromaticClassification}")
    println("${Color.GREEN}:${Color.GREEN.cromaticClassification}")

    println(Pair(Color.YELLOW, Color.BLUE).isSecondaryOrTertiary())
}