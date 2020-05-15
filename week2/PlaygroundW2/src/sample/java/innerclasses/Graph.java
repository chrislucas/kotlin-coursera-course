package sample.java.innerclasses;
// https://docs.oracle.com/javase/specs/jls/se7/html/jls-8.html#jls-8.1.3

public class Graph {

    // interfaces aninhadas sao sempre considera estaticas, portando nunca Inner Class
    public interface Navigation{}

    // Essa eh uma Inner class
    class Vertex {
        // se Vertex.class nao for estatica esse membro nao pode ser, pois ela
        // eh uma inner class, a nao ser que seja declarada como constante
        //static int i = 0;
        // constante
        static final int i = 0;

        // inner class nao podem ter blocos de inicializacao estaticos
        // static { }
    }

    private class Representation2D extends Representation {}

    // Nest class mas nao Inner class
    static class Representation {
        static int id = 0x0f;
        static final String descriptionId = "0x0f";
        static {
            System.out.println("Classe para uma possivel representacao do grafico");
        }
    }

    //private static final Representation2D rep = new Graph().new Representation2D();

    public static void main(String[] args) {
        System.out.println(Representation2D.id);
    }

}

