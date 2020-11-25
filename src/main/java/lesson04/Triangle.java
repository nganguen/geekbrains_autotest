package lesson04;

public class Triangle {

    public static double areaBySides(double side1, double side2, double side3) throws Exception {
        if (((side1 + side2) > side3) & ((side1 + side3) > side2) & ((side2 + side3) > side1)) {
            double p = (side1 + side2 + side3) / 2;
            return (Math.sqrt(p * (p - side1) * (p - side2) * (p - side3)));
        }
        else throw new Exception("No triangle exists");
    }
}
