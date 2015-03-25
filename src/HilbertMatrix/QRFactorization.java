package HilbertMatrix;

import java.util.LinkedList;

import static General.Ops.*;
import static HilbertMatrix.HilbertOps.*;

/**
 *
 * Encapsulated a QR factorization. In order to obtain a QR factorization,
 * pass the matrix to be factored to the static qr_fact_househ method, or the
 * qr_fact_givens method
 *
 * Created by Brian on 3/21/2015.
 */
public class QRFactorization {

    ///DEBUG////////////////////////////
    public static void main(String[] args){
        double[][] matrix = {   {1,2,3},
                                {4,5,6},
                                {7,8,9} };

        qr_fact_househ(matrix);
    }

    private boolean isHouseholder;

    private double[][] Q, R;
    private double error;

    private QRFactorization(double[][] q, double[][] r, double error,
                            boolean isHouseholder) {
        Q = q;
        R = r;
        this.error = error;
        this.isHouseholder = isHouseholder;
    }

    /**
     * factorizes a matrix using the householder algorithm
     * @param matrix matrix to be factored
     * @return the factorization
     */
    public static QRFactorization qr_fact_househ(double[][] matrix) {

        /* keep an unmodified copy of the matrix, so that the error can be
           calculated later.*/
        double[][] A = deepCopy(matrix);

        //for readability//
        int numRows = A.length;
        int numCols = A[0].length;

        //stores all of the householder reflections, so Q can be calculated//
        double[][][] HouseholderMs = new double[numCols - 1][numRows][numCols];

        for (int col = 0; col < numCols; col++) {

            //for readability. index of the row on the diagonal//
            int topRowI = col;


            //calculate u_//

            //should be size of elements on or below the diagonal//
            double[] u_ = new double[numRows - topRowI];

            //norm of the vector eliminated. for calculating u_//
            double normV = 0.0;

            for (int i = 0; i < u_.length; i++) {
                //copy values into u_//
                u_[i] = A[i + topRowI][col];

                normV += Math.pow(u_[i], 2);
            }

            //check if we can skip because there are no rows to reduce//
            boolean allZero = true;
            for (int i = 1; i < u_.length; i++) {
                if (Math.abs(u_[i]) > .00001) {
                    allZero = false;
                }
            }

            if (allZero) {
                continue;
            }

            //norm of vector now calculated//
            normV = Math.sqrt(normV);

            //add norm to first element using same sign, to avoid cancellation//
            u_[0] += (u_[0] > 0) ? normV : -normV;

            //find norm of u_, squared//
            double normU_2 = 0;
            for (int i = 0; i < u_.length; i++) {
                normU_2 += Math.pow(u_[i], 2);
            }

            //calculate u_ u_t//
            double[][] u_ut = new double[u_.length][u_.length];
            for (int i = 0; i < u_.length; i++) {
                for (int j = 0; j < u_.length; j++) {
                    //offset the placement, so we don't reflect past elements//
                    u_ut[i][j] = u_[i] * u_[j];
                }
            }

            double multFactor = 2 / normU_2;
            double[][] uutMultiplied = scalarMult(u_ut, multFactor);

            double[][] toSubtract = new double[numRows][numCols];

            for (int i = topRowI; i < numRows; i++) {
                for (int j = col; j < numCols; j++) {
                    toSubtract[i][j] = uutMultiplied[i - topRowI][j - col];
                }
            }
//            System.out.println("normU squared:" + normU_2);
//            System.out.println("toSubtract");
//            printMatrix(toSubtract);


            System.out.println("toSubtract");
            printMatrix(toSubtract);
            double[][] H = getIdentityMatrix(numRows);
            H = matrixSubtract(H, toSubtract);

            System.out.println("H");
            printMatrix(H);

            //store reflection//
            HouseholderMs[col] = H;

            System.out.println("before");
            HilbertOps.printMatrix(A);
            System.out.println();
            //apply reflection//
            A = matrixMult(H, A);

            System.out.println("after");
            HilbertOps.printMatrix(A);
            System.out.println();
        }

        //matrix is now equal to R//

        //calculate Q//
        System.out.println("Hs");
        printMatrix(HouseholderMs[0]);
        for (int i = 1; i < HouseholderMs.length; i++) {
            HouseholderMs[i] = matrixMult(HouseholderMs[i - 1], HouseholderMs[i]);
            printMatrix(HouseholderMs[i]);
        }

        double[][] Q = HouseholderMs[HouseholderMs.length - 1];

        //calculate error//

        double error = HilbertOps.norm(matrixSubtract(matrix, matrixMult(Q, A)));

        return new QRFactorization(Q, A, error, true);
    }

    public static QRFactorization qr_fact_givens(double[][] matrix) {
        /* keep an unmodified copy of the matrix, so that the error can be
           calculated later.*/
        double[][] A = deepCopy(matrix);

        //for readability//
        int numRows = A.length;
        int numCols = A[0].length;

        //stores all rotations to calculate Q//
        LinkedList<double[][]> givenses = new LinkedList<>();



        for (int col = 0; col < numCols; col++) {

            //For readability. Index of row on diagonal//
            int topRowI = col;

            for (int rowToKill = topRowI + 1; rowToKill < numRows; rowToKill++) {

                //if already zero, move on//
                if (Math.abs(A[rowToKill][col]) < .00001) {
                    continue;
                }


                //make rotation matrix//

                //start with id matrix and overwrite with sine and cosine//
                double[][] G = getIdentityMatrix(numRows);

                double cosine = cosine(A[topRowI][col], A[rowToKill][col]);
                double sine = sine(A[topRowI][col], A[rowToKill][col]);

                //spacing of sine and cosine in matrix//
                int spacing = rowToKill - topRowI;

                G[topRowI][col] = cosine;
                G[rowToKill][col] = sine;
                G[topRowI][col + spacing] = -sine;
                G[rowToKill][col + spacing] = cosine;


                //store rotation matrix//
                givenses.add(G);

                //multiply//
                A = matrixMult(G, A);
            }
        }

        double[][] R = A;
        
        //calculate Q//
        double[][] Q = givenses.stream().reduce(getIdentityMatrix(numRows),
                (Gtot, G) -> matrixMult(Gtot, transpose(G)));

        //calculate error//
        double error = norm(matrixSubtract(matrix, matrixMult(Q, R)));

        return new QRFactorization(Q, R, error, false);
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

    public boolean usedHouseholder() {
        return isHouseholder;
    }

    public boolean usedGivens() {
        return !isHouseholder;
    }
}
