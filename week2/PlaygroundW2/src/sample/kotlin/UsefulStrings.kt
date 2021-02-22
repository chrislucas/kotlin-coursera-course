package sample.kotlin


fun multilineStrings() {
    val s = """um teste com uma cadeia de caracteres de multiplas linhas.
        |O caracter '|' eh o caracter padrao para definir aonde comeca a margem da proxima linhas
        Sem esse caracter, o espaco deixado entre a ultima palavra da linha anterior e a seguinte será
        |o tamanho da margem
    """.trimMargin()

    println(s)

    val s2 = """Podemos alterar o caracter padrao,
        *basta passar como argumento para funcao 'trimMargin' qual  caracter que será utilizado
    """.trimMargin(marginPrefix = "*")

    println(s2)
}


fun usingTripleQuotesRegex() {
    // Colocando a string entre 3 aspas nao precisamos usar usar usar o recurso para escapar a \
    val cpfPattern = """(\d{3})(\.\1){2}-\d{2}""".toRegex()
    println(cpfPattern.matches("123.123.434-23"))

    val sampleBackreference = """([a-c])x\1x\1""".toRegex()

    println("Sample")
    // https://www.regular-expressions.info/backref.html
    arrayOf("axaxa", "axaxb", "axaxc").forEach {
        println(sampleBackreference.matches(it))
    }


}


fun main() {
    usingTripleQuotesRegex()
}