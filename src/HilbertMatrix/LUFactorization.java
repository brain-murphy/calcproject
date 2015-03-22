package HilbertMatrix;


import static General.Ops.*;

/**
 * Created by Brian on 3/21/2015.
 */
public class LUFactorization {

    private double[][] L, U;
    private double error;

    private LUFactorization(double[][] L, double[][] U, double err) {
        this.L = L;
        this.U = U;
        this.error = err;
    }



    public static LUFactorization lu_fact(double[][] matrix) {

        double[][] U = deepCopy(matrix);

        if (U.length != U[0].length) {
            throw new IllegalArgumentException("LU factorization needs a square matrix");
        }

        //length, width equal. defined both for readability//
        int numCols = U.length;
        int numRows = numCols;


        //stores all transformation matrixes//
        double[][][] Ls = new double[numCols][numCols][numCols];



        //calculate all L matrices//
        for (int col = 0; col < numCols; col++) {
            int topRow = col;

            //set up L matrix to record transformations//
            Ls[col] = getIdentityMatrix(numCols);

            for (int rowToKill = topRow + 1; rowToKill < numRows; rowToKill++) {

                if (Math.abs(U[rowToKill][col]) < .00001) {
                    continue;
                    //TODO figure out if this is acceptable
                }

                //partial pivot: find largest element in col//
                double largestInCol = 0.0;
                int rowOfLargest = 0;
                for (int row = topRow; row < numRows; row++) {
                    if (U[row][col] > largestInCol) {
                        largestInCol = U[row][col];
                        rowOfLargest = row;
                    }
                }
                //partial pivot: pivoting//
                HilbertOps.swap(U, topRow, rowOfLargest);
                HilbertOps.swap(Ls[col], topRow, rowOfLargest);


                //find value to multiply top row by to eliminate next row//
                double multFactor = U[rowToKill][col] / U[topRow][col];

                //eliminate row//
                for (int i = col; i < numCols; i++) {
                    U[rowToKill][i] = U[rowToKill][i]
                            - U[topRow][i] * multFactor;

                    //record changes//
                    Ls[col][rowToKill][col] = Ls[col][rowToKill][col]
                            - Ls[col][topRow][i] * multFactor;
                }

            }
        }

        //solve for L//

        for (int i = Ls.length - 2; i >= 0; i--) {
            HilbertOps.LInverse(Ls[i + 1]);
            HilbertOps.LInverse(Ls[i]);
            Ls[i] = matrixMult(Ls[i + 1], Ls[i]);
        }

        double[][] L = Ls[0];

        //error//
        double[][] difference = matrixSubtract(matrixMult(L, U), matrix);

        return new LUFactorization(L,U, HilbertOps.norm(difference));
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
}
