package sample.kotlin.ext;

public class UseSampleKt {

    public static void main(String[] args) {
        Point2D p = new Point2D(2.3,  3.4);
        SamplesKt.log(p);
        SamplesKt.staticExtFunction(Point2D.Companion);
    }
}
