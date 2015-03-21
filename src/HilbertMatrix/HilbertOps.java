package HilbertMatrix;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * Created by Brian on 3/21/2015.
 */
public class HilbertOps {

    public static double[][] generateHilbertMatrix(int width){

        double[][] H = new double[width][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                H[i][j] = 1.0 / (i + j + 1);
            }
        }

        return H;
    }

    public static void main(String[] args) {
        System.out.println(norm(generateHilbertMatrix(5)));
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    public static double norm(double[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToDouble(x -> Arrays.stream(x)).max().getAsDouble();
    }

}
