package com.br.week5.ktypes

private fun checkReplaceAll() {
    /*
        Em java, a classe String possui o metodo replaceAll
        com a mesma assinatura que o metodo replace em kotlin,
        porem o metodo em Java se comporta diferente. O argumento
        oldValue espera uma Regex, entao no exemplo abaixo se
        passarmos um ".", tudo que for um caracter sera substituido por
        "*"
     */
    val target = "one.two."
    var replaced = target.replace(".", "*")
    println(replaced)

    replaced = target.replace(Regex("."), "*")
    println(replaced)

}


fun main() {
    checkReplaceAll()
}