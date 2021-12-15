package com.br.samples.lambdavsmethodref.s2


/*
    https://proandroiddev.com/kotlin-lambda-vs-method-reference-fdbd175f6845
    exercicio final
 */

class Button(val onClick: () -> Unit) {
    fun performClick() = onClick()
}

class ButtonClickListener(private val name: String) {
    fun onClick() = println(name)
}


class ScreenView {
    var listener = ButtonClickListener("first")
    val btnLambda = Button { listener.onClick() }
    /*
        No caso da passagem via referencia, o atributo listener e usado assim que o
        construtor de Button eh invocado para definir o valor do
        atributo receiver da interface anonima que sera usada para invocar o metodo
        performClick.

        Dessa forma, o valor a referencia a funcao lambda passada pelo
        construtor nao muda mesmo que a variavel mutável listener mude

        Algo diferente ocorre na passagem via lambda onde listener só é usado no momento
        que o metodo performClick eh executado, assim se o valor mudar N vezes antes do
        metodo performClick ser chamado, o valor de listener sera o da ultima mudanca
     */
    val btnRef = Button(listener::onClick)
}


private fun checkInstance() {
    val screenView = ScreenView()
    screenView.listener = ButtonClickListener("second")
    println(screenView.listener)
}

private fun checkPerformClickFun() {
    val screenView = ScreenView()
    screenView.listener = ButtonClickListener("second")
    screenView.listener = ButtonClickListener("third")
    screenView.listener = ButtonClickListener("fourth")

    screenView.btnLambda.performClick()
    screenView.btnRef.performClick()
}

private fun check1() {
    val screenView = ScreenView()
    screenView.listener = ButtonClickListener("second")
    screenView.listener = ButtonClickListener("third")
    screenView.listener = ButtonClickListener("fourth")
    /*
        Apos todas as mudancas de referencia do atributo listener
        a instancia de Button criada atraves de uma lambda vai
        usar o ultimo valor definido para listener
     */
    println(screenView.btnLambda.onClick)
    println(screenView.btnRef.onClick)
}

fun main() {
    checkPerformClickFun()
}