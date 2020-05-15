package sample.java.initializers;

/**
 * https://www.geeksforgeeks.org/g-fact-79/
 * https://www.geeksforgeeks.org/g-fact-26-the-initializer-block-in-java/
 * https://www.geeksforgeeks.org/instance-initialization-block-iib-java/
 * */

class TestStaticBlock {
    static int i;
    private int j;

    static  {
        i = 10;
        System.out.printf("Static Block %s class\n", TestStaticBlock.class.getName());
    }
    // instance init
    {
        System.out.println("Instance Initialize Block e executado sempre que um construtor for executado");
    }

    TestStaticBlock() {
        System.out.printf("Construtor padrao de: %s\n", getClass().getName());
    }

    TestStaticBlock(int j) {
        this.j = j;
        System.out.printf("Construtor executado, valor de i: %d\n", j);
    }

    @Override
    public String toString() {
        return String.format("%d, %d", i, j);
    }
}

public class InitTests {

    private static void testStaticInitBlock() {
        // o bloco estatico sera executado uma unica vez
        System.out.println(TestStaticBlock.i);
        System.out.println(TestStaticBlock.i);
    }

    private static void testInitBlock() {
        // o bloco de inicializacao 'nao estatico' sera executado
        // primeiro que cada execucao do construtor
        new TestStaticBlock();
        new TestStaticBlock(12);
        new TestStaticBlock(13);
    }

    public static void main(String[] args) {
        testInitBlock();
    }
}
