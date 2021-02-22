package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)


infix fun <T : Comparable<T>> T.eq(that: T) = this.compareTo(that) == 0

fun evaluateGuess(secret: String, guess: String): Evaluation {
    return if (secret eq guess) {
        Evaluation(4, 0)
    } else {

        val sFreq = mutableMapOf<Char, Int>()
        val gFreq = mutableMapOf<Char, Int>()

        var s = false
        secret.forEach {
            sFreq[it] = sFreq[it]?.plus(1) ?: 1
            sFreq[it]?.let { c ->
                if (c > 2)
                    s = true
            }
        }
        var g = false
        guess.forEach {
            gFreq[it] = gFreq[it]?.plus(1) ?: 1
            gFreq[it]?.let { c ->
                if (c > 2)
                    g = true
            }
        }
        var rightPosition = 0
        // as 2 strins tem caracteres unicos
        var wrongPosition = 0
        for (i in secret.indices) {
            if (secret[i] == guess[i]) {
                rightPosition += 1
                sFreq[secret[i]] = sFreq[secret[i]]?.minus(1) ?: 1
                gFreq[guess[i]] = gFreq[guess[i]]?.minus(1) ?: 1
            }
        }

        for (i in secret.indices) {
            if (secret[i] != guess[i]) {
                // o ith caracter da string guess existe na string secret
                sFreq[guess[i]]?.let { fs ->
                    /**
                     * A aqui sabemos que guess[i] existe em secret, o que queremos
                     * Para cada guess[i] existente em secret nos computaremos
                     * uma posicao errada, ate que a quantidade desse caracter
                     * na string guess seja igual a quantidade na string secret.
                     * Passando disso sabemos que o excedente nao eh uma posicao errada
                     * mas sim um erro
                     * Exemplo de caracteres repitidos com a mesma quantidade mas em
                     * posicoes diferentes
                     *
                     * S = AABC e G = BCAA; AABC e ABCA;
                     * Resposta Evaluation(0, 2)
                     *
                     * S = AAAF e G = ABCA
                     * Resposta E(1, 1)
                     *
                     *
                     * */
                    if (fs > 0) {
                        // segundo a regra do game, dizemos que o usuario acertou
                        // o carater mas erro a posicao
                        sFreq[guess[i]] = sFreq[guess[i]]?.minus(1) ?: 1
                        gFreq[guess[i]] = gFreq[guess[i]]?.minus(1) ?: 1
                        wrongPosition += 1
                    }
                }
            }
        }
        Evaluation(rightPosition, wrongPosition)
    }
}
