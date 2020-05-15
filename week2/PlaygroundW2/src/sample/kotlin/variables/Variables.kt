package sample.kotlin.variables


object TestVarRef {

    @JvmStatic
    fun mutableValues() {
        // A keyword val permite a criacao de variaveis imutaveis
        // similar a keyword final do Java.
        val types = mutableListOf("String", "Int", "Float")
        // Em kotlin tudo eh um objeto, ao definir valores a um objeto (mutavel ou imutavel)
        // o que esta sendo feito e definir uma referencia na memoria a um determinado valor
        // Quando usamos a keyword val, somos impedidos em tempo de compilacao a mudar a definicao
        // dessa referencia, mas nao o valor para onde essa referencia aponta
        // por isso  o trecho de codigo abaixo funciona
        types.addAll(mutableListOf("Double", "Boolean"))
        types.forEach { println("Types: $it") }
        // aqui teremos uma erro de compilacao, pois estamos tentando redefinir
        // a referencia de memoria para qual o objeto 'types' aponta
        //types = listOf("1", "2")
    }

    @JvmStatic
    fun immutableValues() {
        // Objeto list nao possui metodos para modificar os valores
        // armazenados na estrutura de lista
        val types = listOf("String", "Int", "Float")
        // a linha abaixo nao eh permitada
        //types[0] = "Teste"
    }

}


fun main() {
    TestVarRef.mutableValues()
}