package HilbertMatrix;


import static General.Ops.*;

/**
 * Created by Brian on 3/21/2015.
 */
public class LUFactorization {

    private boolean hasRun;

    private double[][] L, U;
    private double error;

    private LUFactorization(double[][] L, double[][] U, double err) {
        this.L = L;
        this.U = U;
        this.error = err;
    }



    public static LUFactorization lu_fact(double[][] matrix) {
        if (matrix.length != matrix[0].length) {
            throw new IllegalArgumentException("LU factorization needs a square matrix");
        }

        //length, width equal. defined both for readability//
        int numCols = matrix.length;
        int numRows = numCols;


        //stores all transformation matrixes//
        double[][][] Ls = new double[numCols][numCols][numCols];



        //calculate all L matrices//
        for (int col = 0; col < numCols; col++) {
            int topRow = col;

            //set up L matrix to record transformations//
            Ls[col] = getIdentityMatrix(numCols);

            for (int rowToKill = topRow + 1; rowToKill < numRows; rowToKill++) {

                //partial pivot: find largest element in col//
                double largestInCol = 0.0;
                int rowOfLargest = 0;
                for (int row = topRow; row < numRows; row++) {
                    if (matrix[row][col] > largestInCol) {
                        largestInCol = matrix[row][col];
                        rowOfLargest = row;
                    }
                }
                //partial pivot: pivoting//
                swap(matrix, topRow, rowOfLargest);
                swap(Ls[col], topRow, rowOfLargest);

                //find value to multiply top row by to eliminate next row//
                double multFactor = matrix[rowToKill][col] / matrix[topRow][col];

                //eliminate row//
                for (int i = col; i < numCols; i++) {
                    matrix[rowToKill][i] = matrix[rowToKill][i]
                            - matrix[topRow][i] * multFactor;

                    //record changes//
                    Ls[col][rowToKill][col] = Ls[col][rowToKill][col]
                            - Ls[col][topRow][i] * multFactor;
                }

            }
        }


        //solve for L//

        for (int i = Ls.length - 2; i >= 0; i--) {
            LInverse(Ls[i + 1]);
            LInverse(Ls[i]);
            Ls[i] = matrixMult(Ls[i + 1], Ls[i]);
        }

        //TODO estimate error
        return null;
    }

    private static void swap(double[][] matrix, int row, int row2) {
        double[] temp = matrix[row];
        matrix[row] = matrix[row2];
        matrix[row2] = temp;
    }

    private static void LInverse(double[][] L) {
        for (int i = 0; i < L.length; i++) {
            for (int j = i + 1; j < L[0].length; j++) {
                L[i][j] = (- (L[i][j]));
            }
        }
    }




    /*
        GETTERS
     */

    public double[][] getL() {
        return L;
    }

    public double[][] getU() {
        return U;
    }

    public double getError() {
        return error;
    }

    public boolean isHasRun() {
        return hasRun;
    }
}
