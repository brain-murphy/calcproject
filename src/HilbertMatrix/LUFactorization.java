package HilbertMatrix;


import static General.Ops.*;

/**
 * Created by Brian on 3/21/2015.
 *
 * This class encapsulates the results of an LU factorization, including the
 * L matrix, the U matrix, and the error
 *
 * use the static method lu_fact() to generate a factorization
 */
public class LUFactorization {

    private double[][] L, U;
    private double error;

    private LUFactorization(double[][] L, double[][] U, double err) {
        this.L = L;
        this.U = U;
        this.error = err;
    }


    /**
     * calculates the LU factorization for the matrix passed, and returns
     * an {@link HilbertMatrix.LUFactorization} encapsulating the results.
     * @param matrix matrix to be factorized. Let each row of the matrix be
     *               represented by a sub array within the 2d array.
     * @return an {@link HilbertMatrix.LUFactorization} representing the result
     */
    public static LUFactorization lu_fact(double[][] matrix) {

        /*
            a copy of the original matrix will be modified into the U matrix in
            order that we can calculate the error afterwards
         */
        double[][] U = deepCopy(matrix);

        if (U.length != U[0].length) {
            throw new IllegalArgumentException("LU factorization needs a square matrix");
        }

        //For readability. length, width equal because of above check.//
        int numCols = U.length;
        int numRows = numCols;


        //stores all transformation matrixes//
        double[][][] Lmatrices = new double[numCols][numCols][numCols];


        //calculate all L matrices//
        for (int col = 0; col < numCols; col++) {

            /*  for readability. define index of row that will be used to
                eliminate other cols. */
            int topRowI = col;

            //set up current L matrix to record transformations//
            Lmatrices[col] = getIdentityMatrix(numCols);

            for (int rowToKill = topRowI + 1; rowToKill < numRows; rowToKill++) {

                //if row is already zero, no elimination required
                if (Math.abs(U[rowToKill][col]) < .00001) {
                    //TODO figure out if the constant .00001 is acceptable
                    continue;
                }

                //partial pivoting: find largest element in col//
                double largestInCol = 0.0;
                int rowOfLargest = 0;
                for (int row = topRowI; row < numRows; row++) {
                    if (U[row][col] > largestInCol) {
                        largestInCol = U[row][col];
                        rowOfLargest = row;
                    }
                }

                //partial pivot: pivoting//
                HilbertOps.swap(U, topRowI, rowOfLargest);
                HilbertOps.swap(Lmatrices[col], topRowI, rowOfLargest);



                //find value to multiply top row by in order to eliminate next row//
                double multFactor = U[rowToKill][col] / U[topRowI][col];

                //eliminate row//
                for (int i = col; i < numCols; i++) {

                    U[rowToKill][i] = U[rowToKill][i]
                            - U[topRowI][i] * multFactor;

                    //record changes//
                    Lmatrices[col][rowToKill][col] = Lmatrices[col][rowToKill][col]
                            - Lmatrices[col][topRowI][i] * multFactor;
                }

            }
        }

        // U is factored//


        //solve for L//

        for (int i = Lmatrices.length - 2; i >= 0; i--) {
            HilbertOps.LInverse(Lmatrices[i + 1]);
            HilbertOps.LInverse(Lmatrices[i]);
            Lmatrices[i] = matrixMult(Lmatrices[i + 1], Lmatrices[i]);
        }

        double[][] L = Lmatrices[0];

        //error//
        double[][] difference = matrixSubtract(matrixMult(L, U), matrix);

        return new LUFactorization(L, U, HilbertOps.norm(difference));
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
