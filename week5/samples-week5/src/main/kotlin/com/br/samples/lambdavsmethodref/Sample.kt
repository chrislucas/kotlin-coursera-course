package com.br.samples.lambdavsmethodref

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
    https://proandroiddev.com/kotlin-lambda-vs-method-reference-fdbd175f6845
    Diferenca entre funcoes lambda e referencia de metodo

    No exemplo abaixo
    - Lamnda: Crian uma implementacao anonima de Function0 e encapsula a chamada da funcao
    lambda dentro do metodo invoke da interface Function0. Assim nao tenta usar uma variavel
    lateinit no momento que ela foi criada, trazendo um pouco mais de seguranca

    - Method reference (lateinit): A interface anonima de Function0 tambem é criada, mas a referencia
    de funcao vai ser usada antes da invocacao do metodo invoke
        - No exemplo abaixo ocorre o seguinte
        - a interface anonima criada  possui um atributo do tipo DefaultButtonClickListener
        - esse atributo será usado para executar o metodo onClick
        - porem ele precisa ser inicializado, e seu valor será exatamente a funcao passada como referencia
        - o codigo gerado testa se essa referencia foi inicializada, se nao foi lanca uma excecao

    Basicamente a diferenca entre lambda function e nethod reference eh que os argumentos passados por lambda
    function sao encapsulados e chamados na funcao invoke e nao sao chamados antes dela ser executada, trazendo
    mais seguranca. Porém se a variavel lateinit nao for inicializada a mesma excecao sera lancada. Veja o exemplo
    do botaoC. Esse exemplo tem um teste para verificar se o atributo lateinit foi inicializado, so para impedir
    erros
 */

class Button(private val onClick: () -> Unit) {
    fun performClick() = onClick()
}

class DefaultButtonClickListener {
    fun onClick() = println("Default Button Clicked")
}

/*
    https://kotlinlang.org/docs/delegated-properties.html#translation-rules-for-delegated-properties
 */
val delegate = object : ReadWriteProperty<ScreenView, DefaultButtonClickListener> {
    var listener: DefaultButtonClickListener = DefaultButtonClickListener()

    override fun getValue(thisRef: ScreenView, property: KProperty<*>): DefaultButtonClickListener =
        this.listener

    override fun setValue(thisRef: ScreenView, property: KProperty<*>, value: DefaultButtonClickListener) {
        this.listener = value
    }
}

class ScreenView {

    private var listener: DefaultButtonClickListener by delegate

    /*
         QUando o metodo onClick for executado, o listener
         ja tera sido construido
     */
    val buttonA = Button { listener.onClick() }

    val buttonB = Button(listener::onClick)

    lateinit var lazyListener: DefaultButtonClickListener

    /*
        Se lazyListener nao for inicializada antes do metodo performClick() da classe
        Button ser chamada, o codigo lancara uma exception: UninitializedPropertyAccessException
        Mas a uma forma de checar antes tentar usar uma propriedade lateinit
     */
    val buttonC = Button {
        if (this::lazyListener.isInitialized) {
            lazyListener.onClick()
        }
    }

    /*
        Exception in thread "main" kotlin.UninitializedPropertyAccessException:
        lateinit property anotherListener has not been initialized

        Aqui o atributo lazyListener ainda nao foi inicializado, por isso
        uma exception eh lancada
     */
    //val buttonD = Button(lazyListener::onClick)
    val buttonE = Button(::fn)
}

fun fn() {
    println("FN function")
}

private fun checkListenerA() {
    val screenView = ScreenView()
    screenView.buttonA.performClick()
}

private fun checkListenerB() {
    val screenView = ScreenView()
    screenView.buttonB.performClick()
}

private fun checkListenerC() {
    val screenView = ScreenView()
    //screenView.lazyListener = DefaultButtonClickListener()
    screenView.buttonC.performClick()
}

private fun checkListenerD() {
    val screenView = ScreenView()
    screenView.lazyListener = DefaultButtonClickListener()
    //screenView.buttonD.performClick()
}

private fun checkListenerE() {
    val screenView = ScreenView()
    screenView.buttonE.performClick()
}

fun main() {
    checkListenerB()
    //checkListenerC()
    checkListenerE()
}







