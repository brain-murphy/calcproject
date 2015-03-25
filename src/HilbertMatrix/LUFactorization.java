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
        double[][][] Lmatrices = new double[numCols][numRows][numCols];


        //calculate all L matrices and reduce U//
        for (int col = 0; col < numCols; col++) {

            /*  for readability. define index of row that will be used to
                eliminate other cols. */
            int topRowI = col;

            //set up current L matrix to record transformations//
            Lmatrices[col] = getIdentityMatrix(numCols);


            for (int rowToKill = topRowI + 1; rowToKill < numRows; rowToKill++) {

                System.out.println("col:" + col + " row:" + rowToKill);
                HilbertOps.printMatrix(U);
                System.out.println();

                //if row is already zero, no elimination required
                if (Math.abs(U[rowToKill][col]) < .000000001) {
                    //TODO figure out if the constant .000001 is acceptable
                    continue;
                }

                //find value to multiply top row by in order to eliminate next row//
                double multFactor = U[rowToKill][col] / U[topRowI][col];

                //eliminate row//
                for (int i = col; i < numCols; i++) {

                    U[rowToKill][i] = U[rowToKill][i]
                            - U[topRowI][i] * multFactor;

                    //record changes//
                    Lmatrices[col][rowToKill][i] = Lmatrices[col][rowToKill][i]
                            - Lmatrices[col][topRowI][i] * multFactor;
                }
                HilbertOps.printMatrix(U);
                System.out.println();
                System.out.println();
            }
        }

        // U is factored//


        //solve for L//

        HilbertOps.LInverse(Lmatrices[0]);
        for (int i = 1; i < Lmatrices.length; i++) {
            HilbertOps.LInverse(Lmatrices[i]);
            Lmatrices[i] = matrixMult(Lmatrices[i - 1], Lmatrices[i]);
        }

        double[][] L = Lmatrices[Lmatrices.length - 1];

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
