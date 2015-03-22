package HilbertMatrix;

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

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for (int col = 0; col < numCols; col++) {

            //calculate u//
            double[] u_ = new double[matrix.length];




        }
        return null;
    }

    public static QRFactorization qr_fact_givens(double[][] matrix) {
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
