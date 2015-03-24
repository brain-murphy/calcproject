package ConvolutionalCodes;
import java.util.Arrays;

public class gauss_seidel {

    // finds upper triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findUpper(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] upper = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < width; j++) {
                upper[i][j] = matrix[i][j];
            }
        }
        System.out.println("This is Upper matrix");
        printMatrix(upper);
        System.out.println("");
        return upper;
    }

    // finds lower triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findLower(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] lower = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                lower[i][j] = matrix[i][j];
            }
        }
        System.out.println("This is lower matrix");
        printMatrix(lower);
        System.out.println("");
        return lower;
    }

    // finds diagonal triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findDiagonal(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] lower = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                lower[i][j] = matrix[i][j];
            }
        }
        System.out.println("This is the diagonal matrix");
        printMatrix(lower);
        System.out.println("");
        return lower;
    }



    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    public static void main(String[] args) {
        double[][] test = new double[3][4];
        test[0][0] = 2;
        test[0][1] = 3;
        test[0][2] = 4;
        test[0][3] = 5;

        test[1][0] = 2;
        test[1][1] = 4;
        test[1][2] = 6;
        test[1][3] = 5;

        test[2][0] = 9;
        test[2][1] = 8;
        test[2][2] = 7;
        test[2][3] = 6;


        System.out.println("This is test matrix");
        printMatrix(test);
        System.out.println("");
        double[][] u = findUpper(test);
        double[][] l = findLower(test);
    }
}