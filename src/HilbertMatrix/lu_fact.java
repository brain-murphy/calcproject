package HilbertMatrix;

import General.Ops;

/**
 * Created by Brian on 3/31/2015.
 */
public class lu_fact {

    public static void main(String[] args) {
        double[][] matrix = Ops.parseMatrix();

        LUFactorization factorization = LUFactorization.lu_fact(matrix);

        System.out.println("L:");
        HilbertOps.printMatrix(factorization.getL());
        System.out.println("\nU:");
        HilbertOps.printMatrix(factorization.getU());
        System.out.println("\n||LU - H||");
        System.out.println(factorization.getError());
    }
}
