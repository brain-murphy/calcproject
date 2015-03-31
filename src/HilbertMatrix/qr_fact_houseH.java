package HilbertMatrix;

import General.Ops;

/**
 * Created by Brian on 3/31/2015.
 */
public class qr_fact_houseH {
    public static void main(String[] args) {

        double[][] matrix = Ops.parseMatrix();

        QRFactorization factorization = QRFactorization.qr_fact_househ(matrix);

        System.out.println("Q:");
        HilbertOps.printMatrix(factorization.getQ());
        System.out.println("\nR:");
        HilbertOps.printMatrix(factorization.getR());
        System.out.println("\n||QR - H||");
        System.out.println(factorization.getError());
    }
}
