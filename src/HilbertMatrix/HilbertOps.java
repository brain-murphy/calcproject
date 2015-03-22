package HilbertMatrix;

import java.util.Arrays;

/**
 * Created by Brian on 3/21/2015.
 */
public class HilbertOps {

    static double[][] generateHilbertMatrix(int width){

        double[][] H = new double[width][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                H[i][j] = 1.0 / (i + j + 1);
            }
        }

        return H;
    }

    public static void main(String[] args) {
        int x = 3;

        if (x > 1) {
            System.out.println("brian is right");
        } else if (x > 2) {
            System.out.println("brian is not right");
        }
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    static double norm(double[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToDouble(x -> Arrays.stream(x)).max().getAsDouble();
    }

    static void swap(double[][] matrix, int row, int row2) {
        double[] temp = matrix[row];
        matrix[row] = matrix[row2];
        matrix[row2] = temp;
    }

    static void LInverse(double[][] L) {
        for (int i = 0; i < L.length; i++) {
            for (int j = i + 1; j < L[0].length; j++) {
                L[i][j] = (- (L[i][j]));
            }
        }
    }

    static double cosine(int x, int y) {
        return x / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    static double sine(int x, int y) {
        return (-y) / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}