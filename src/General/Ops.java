package General;

/**
 * Created by Brian on 3/21/2015.
 */
public class Ops {


    public static double[][] getIdentityMatrix(int width) {
        double[][] I = new double[width][width];

        for (int i = 0; i < width; i++) {
            I[i][i] = 1;
        }

        return I;
    }

    /**
     * produces a deep copy of a matrix
     * @param matrix matrix to copy
     * @return new matrix without reference to the old one.
     */
    public static double[][] deepCopy(double[][] matrix) {

        double[][] newM = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newM[i][j] = matrix[i][j];
            }
        }
        return newM;
    }

    public static double[][] matrixMult(double[][] A, double[][] B) {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException("matrix dimensions incompatible");
        }

        double[][] C = new double[A.length][B[0].length];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
}
