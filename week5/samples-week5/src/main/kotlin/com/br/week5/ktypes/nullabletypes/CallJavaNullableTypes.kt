package com.br.week5.ktypes.nullabletypes

import com.br.sample.nullability.JavaNullableTypes
import com.br.sample.nullability.SampleNullableType
import com.br.sample.SimpleClass
import com.br.sample.nullability.CheckCustomAnnotationNonNull

/*
    https://www.coursera.org/learn/kotlin-for-java-developers/lecture/QB15k/nullable-types
    Java              Kotlin
    @Nullable Type -> Type?
    @NotNull Type -> Type

    Atributos de classes Java que nao defininem anotacoes nullable ou notnull sao
    determinado como platform types

    Java    Kotlin

    Type -> Type!
    O Simbolo de exclamação significa que o código kotlin está tentando acessar um tipo
    definido em Java mas ele nao pode ser definido em kotlin

    A abordagem mais segura seria tratar em kotlin t0do tipo vindo de java como nullable
    para evitar NPE
        - Mas essa abordagem nao foi bem recebida pois os desenvolvedores tinha que usar
        muito o recurso de exclamation mark !! para forçar o uso de uma variavel que eles
        sabiam que nao seria nula

        - o segundo problema é que essa abordagem não funciona com Generics


    COMO EVITAR NPE quando chamamos codigo Java a partir de Kotlin ?
    - Usar anotacoes @Nullable ou @NotNull nos tipos em Java. O Kotlin vai
    interpretar isso como Type? ou Type respectivamente
        - pode-se anotar return types, paramter types

    - Na pratica precisamos anotar somente o que pode ser Nullable, por definicao/padrao
    o que nao receber anotacao eh NotNUll

    -------------------------------------------------------------------------------------------
    NOTE QUE NEM SEMPRE SERÄ POSSÍVEL ADICIONAR ANOTACOES NAS CLASSES POR EXEMPLO
    NO CASO DE ESTARMOS TRABALHANDO COM UMA BIBLIOTECA EXTERNAS QUE NAO TEMOS ACESSO AO CODIGO FONTE.

    NESSE CASO A SAIDA É UMA SEGUNDA OPCAO, DEFINIR EXPLICITAMENTE COMO NULLABLE OU NON-NULL
    ( (String|Int|Whatever)? OU String|Int|Whatever) O TIPO DA VARIAVEL QUE ESTAMOS
    RECEBENDO DE UM CODIGO JAVA.

    Se nao estivermos trabalhando com código java e kotlin ao mesmo tempo, a abordagem
    de definir explicitamente o tipo nos argumentos de funcoes, membros de classes e etc.
    eh mais simples e o suficiente, pois o kotlin tem o operador null safety para lidar
    com tipos nulos e a hierarquia de classes nao permite adicionar um tipo nulo a um nao
    nulo, esse ato infringe a hierarquia causando um erro de compilacao

    Lembrado

    Int? <- Int e para t0do tipo nulável

    val p: Int = 10
    val q: Int? = p - atribuir a um Int? um Int nao nulo e possivel
    val s: Int = q -mas a reciproca nao eh verdadeira

    -------------------------------------------------------------------------------------------

    A segunda forma de se evitar NPE é definir explicitamente o tipo da sua variavel
    como nullable Type? ou nao nullable Type

 */



private fun checkJavaNullableTypes() {
    /*
        compileKotlin error
        Null can not be a value of a non-null type String
     */
    val data = JavaNullableTypes.Data(
        null,
        System.currentTimeMillis(),
        "null" //null
    )
    println(data)
}

private fun check() {
    SimpleClass.show()
}

private fun checkCallNullableReturnFunction() {
    val sample = SampleNullableType()
    // kotlin infere que o tipo do atributo type é String! (com exclamacao mesmo)
    // Um tipo platform type pois nao foi definido nem como Nullable nem como NotNull
    val type = sample.type

    println(type)
    /*
        Isso deve gerar um NPE. GetType em java ou type em kotlin
        eh uma funcao getter que retorna um nulo e nao recebe a anotacao Nullable
        e o compilador nao consegue inferir que o valor retornado pode ser nulo
        Por isso a importancia de usar pelo menos a anotacao @Nullable em Java, pois
        isso força que em kotlin q o compilador gere um erro caso nao usemos a operador
        null safety "?"
     */
    println(type.length)

    /*
        Description foi descrito como tipo Nullable, assim kotlin consegue inferir
        que description eh do tipo String? e exige que usemos o operador null safety
     */
    val description = sample.description
    println(description?.length)

    // Anotando parameter type - show(String?)
    println(SampleNullableType.show(null))

    println(SampleNullableType.showNotNull("null"))
}

@ParameterNonNullByDefault
private fun checkParameterNonNUllByDefault(value: Int?) {
    println(value)
    val check = CheckCustomAnnotationNonNull()
    /*
        compilerKotlin error
        Null can not be a value of a non-null type String
     */
    //check.show(null)
    check.show("String")
}


fun main() {
    checkParameterNonNUllByDefault(null)
}