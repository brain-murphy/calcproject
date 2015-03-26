package PopDynamics;
import java.util.Arrays;

import static General.Ops.*;

/**
 * Calculates the power method
 * Created by Justin Joe
 */
public class PowerMethod {
    private double eigenValApprox;
    private double[][] eigenVecApprox;
    private double iterationNum;

    public PowerMethod(double eigenValApprox, double[][] eigenVecApprox, double iterationNum) {
        this.eigenValApprox = eigenValApprox;
        this.eigenVecApprox = eigenVecApprox;
        this.iterationNum = iterationNum;
    }

    /**
     * Calculates the approximations of the dominant eigen value and eigen vector for the matrix
     * using the power iteration method
     * @param a the matrix for which the eigen values and eigen vectors are being calculated
     * @param vecApprox the initial approximation for the eigen vector
     * @return the updated info
     */

    public void power_method(double[][] a, double[][] vecApprox, double tol) {
        double error = 100;
        while (error > tol && iterationNum <= 100) {
            double temp = eigenValApprox;
            vecApprox = scalarMult(matrixMult(a,  vecApprox), (1 / vecApprox[0][0]));
            eigenValApprox = vecApprox[0][0];
            iterationNum++;
            double norm = 0;
            for (int i = 0; i < vecApprox.length; i++) {
                norm += Math.pow(vecApprox[i][0], 2);
            }
            norm = Math.sqrt(norm);
            eigenVecApprox = scalarMult(vecApprox, (1 / norm));
            error = Math.abs(temp - eigenValApprox);
        }
        if (iterationNum >= 100) {
            System.out.println("Did not converge to tolerance after 100 iterations.");
        } else {
            System.out.println("Approximate Eigenvalue: " + getEigenValue());
            System.out.println("Approximate Eigenvector: " + Arrays.deepToString(getEigenVec()));
            System.out.println("Iterations needed: " + getIterationNum());
        }
    }

    public double getEigenValue() {
        return eigenValApprox;
    }

    public double[][] getEigenVec() {
        return eigenVecApprox;
    }

    public double getIterationNum() {
        return iterationNum;
    }

}