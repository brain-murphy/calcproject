package HilbertMatrix;

import static General.Ops.*;
import java.util.Arrays;

/**
 * Created by Brian on 3/21/2015.
 */
public class HilbertOps {

    private static LUFactorization pastLUFactorization;
    private static double[][] pastLUMatrix;

    private static QRFactorization pastQRFactorization;
    private static double[][] pastQRMatrix;

    public static double[] solve_lu_b(double[][] matrix, double[] b) {
        if (matrix.length != b.length) {
            throw new IllegalArgumentException("cannot solve for matrix and b different lengths");
        }

        //get factorization or use old//
        if (!deepEquals(matrix, pastLUMatrix)) {
            pastLUFactorization = LUFactorization.lu_fact(matrix);
        }

        //solve for x//

        return null;
    }

    public static double[] solve_qr_b(double[][] matrix, double[] b) {
        if (matrix.length != b.length) {
            throw new IllegalArgumentException("cannot solve for matrix and b different lengths");
        }

        //get factorization or use old//
        if (!deepEquals(matrix, pastLUMatrix)) {
            pastQRFactorization = QRFactorization.qr_fact_househ(matrix);
        }
                return null;
    }

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
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    static double norm(double[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToDouble(x -> Arrays.stream(x))
                .map(x -> Math.abs(x))
                .max().getAsDouble();
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

    static double cosine(double x, double y) {
        return x / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    static double sine(double x, double y) {
        return (-y) / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}