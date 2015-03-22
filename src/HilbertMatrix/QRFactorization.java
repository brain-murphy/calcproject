package HilbertMatrix;

import static General.Ops.*;

/**
 * Created by Brian on 3/21/2015.
 */
public class QRFactorization {

    boolean isHouseholder;

    private double[][] Q, R;
    private double error;

    private QRFactorization(double[][] q, double[][] r, double error,
                            boolean isHouseholder) {
        Q = q;
        R = r;
        this.error = error;
        this.isHouseholder = isHouseholder;
    }


    public static QRFactorization qr_fact_househ(double[][] matrix) {

        double[][] A = deepCopy(matrix);

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        double[][][] HouseholderMs = new double[numCols][numRows][numCols];

        for (int col = 0; col < numCols; col++) {

            int topRow = col;


            //calculate u//
            double[] u_ = new double[numCols - topRow];
            double normV = 0.0;

            boolean allZero = true;

            for (int i = topRow; i < numRows; i++) {
                u_[i] = matrix[i][col];

                if (Math.abs(u_[i]) > .00001) {
                    allZero = false;
                }

                normV += Math.pow(u_[i], 2);
            }

            if (allZero) {
                continue;
            }

            normV = Math.sqrt(normV);

            //add norm to first element with same sign, to avoid cancellation//
            u_[0] += (u_[0] > 0) ? normV : -normV;

            //find norm of u_ squared//
            double normU_2 = 0;
            for (int i = 0; i < u_.length; i++) {
                normU_2 += Math.pow(u_[0], 2);
            }

            //calculate u_ u_t//
            double[][] u_ut = new double[u_.length][u_.length];
            for (int i = 0; i < u_.length; i++) {
                for (int j = 0; j < u_.length; j++) {
                    u_ut[i][j] = u_[i] * u_[j];
                }
            }

            //calculate I -2 * u_ut_ / norm(u_)^2//
            double[][] H = getIdentityMatrix(numRows);
            double multFactor = -2 / normU_2;
            H = matrixSubtract(H, scalarMult(u_ut, multFactor));

            HouseholderMs[col] = H;

            matrix = matrixMult(H, matrix);

        }

        //matrix is now equal to R//

        //calculate Q//
        for (int i = 1; i < HouseholderMs.length; i++) {
            HouseholderMs[i] = matrixMult(HouseholderMs[i - 1], HouseholderMs[i]);
        }

        double[][] Q = HouseholderMs[HouseholderMs.length - 1];


        //calculate error//

        double error = HilbertOps.norm(matrixSubtract(A, matrixMult(Q, matrix)));

        return new QRFactorization(Q, matrix, error, true);
    }

    public static QRFactorization qr_fact_givens(double[][] matrix) {
        double[][] A = deepCopy(matrix);

        int numRows = matrix.length;
        int numCols = matrix[0].length;


        for (int col = 0; col < numCols; col++) {

            int topRow = col;

            for (int rowToKill = topRow + 1; rowToKill < numRows; rowToKill++) {

                if (Math.abs(matrix[rowToKill][col]) < .00001) {
                    continue;
                }

                //make rotation matrix//

            }

        }
        return null;
    }






    /*
        GETTERS
     */

    public double getError() {
        return error;
    }

    public double[][] getQ() {
        return Q;
    }

    public double[][] getR() {
        return R;
    }
}
