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
        performClick. Dessa forma, receiver nao muda de valor mesmo que var listener mude
        Diferente do que ocorre na passagem via lambda onde listener só é usado no momento
        qie o metodo performClick eh executado, assim se o valor mudar N vezes antes do
        metodo performClick ser chamado, o valor de listener sera o da ultima mudanca
     */
    val btnRef = Button(listener::onClick)
}


fun main() {
    val screenView = ScreenView()
    screenView.listener = ButtonClickListener("second")
    println(screenView.listener)
    screenView.listener = ButtonClickListener("third")
    screenView.listener = ButtonClickListener("fourth")

    screenView.btnLambda.performClick()
    screenView.btnRef.performClick()

    println(screenView.btnLambda.onClick)
    println(screenView.btnRef.onClick)
}