package General;

import HilbertMatrix.HilbertOps;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

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

    public static double[][] matrixSubtract(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }

        return C;
    }

    public static double[][] matrixAdd(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    public static double[][] scalarMult(double[][] matrix, double scalar) {
        double[][] result = deepCopy(matrix);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = result[i][j] * scalar;
            }
        }

        return result;
    }

    public static double vectorNorm(double[] vector) {
        double dot = 0;

        for (double ele : vector) {
            dot += Math.pow(ele, 2);
        }

        return Math.sqrt(dot);
    }

    public static double[][] transpose(double[][] matrix) {
        double[][] T = deepCopy(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                T[i][j] = matrix[j][i];
                T[j][i] = matrix[i][j];
            }
        }

        return T;
    }

    public static boolean deepEquals(double[][] A, double[][] B) {
        if (A == null || B == null) {
            return false;
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {

                if (Math.abs(A[i][j] - B[i][j]) > .00001) {
                    return false;
                }

            }
        }

        return true;
    }

    /**
     * for lower triangular matrix
     * @param matrix
     * @param b_
     * @return
     */
    public static double[] backSubstitution_down(double[][] matrix, double[] b_) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (b_.length != numCols) {
            throw new IllegalArgumentException("wrong dimensions for solving");
        }

        double[] x_ = new double[b_.length];

        for (int row = 0; row < numRows; row++) {

            //use previously substituted vals//
            double known = 0.0;
            for (int col = 0; col < row; col++) {
                known += matrix[row][col] * x_[col];
            }

            x_[row] = (b_[row] - known) / matrix[row][row];
        }

        return x_;
    }

    /**
     * for upper triangular matrix
     * @param matrix
     * @param b_
     * @return
     */
    public static double[] backSubstitution_up(double[][] matrix, double[] b_) {

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        if (b_.length != numCols) {
            throw new IllegalArgumentException("wrong dimensions for solving");
        }

        double[] x_ = new double[b_.length];

        for (int row = numRows - 1; row >= 0; row--) {

            //use previous vals//
            double known = 0.0;
            for (int col = numCols - 1; col > row; col--) {
                known += matrix[row][col] * x_[col];
            }

            x_[row] = (b_[row] - known) / matrix[row][row];
        }

        return x_;
    }

    public static double[] matrixVectorMult(double[][] matrix, double[] v_) {
        double[] b_ = new double[matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < v_.length; j++) {
                b_[i] += matrix[i][j] * v_[j];
            }
        }

        return b_;
    }

    public static double[][] parseMatrix() {
        Scanner in = new Scanner(System.in);
        LinkedList<Double> elementList = new LinkedList<>();
        while (in.hasNextDouble()) {
            elementList.add(in.nextDouble());
        }

        int n = (int) Math.round(Math.sqrt(elementList.size()));

        double[][] matrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = elementList.get((i * n) + j);
            }
        }
        return matrix;
    }
}
