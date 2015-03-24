package PopDynamics;
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
        while (error > tol) {
            double temp = eigenValApprox;
            eigenValApprox = vecApprox[0][0];
            vecApprox = scalarMult(matrixMult(a,  vecApprox), (1 / vecApprox[0][0]));
            iterationNum++;
            double norm = 0;
            for (int i = 0; i < vecApprox.length; i++) {
                norm += Math.pow(vecApprox[i][0], 2);
            }
            norm = Math.sqrt(norm);
            eigenVecApprox = scalarMult(vecApprox, (1 / norm));
            error = Math.abs(temp - eigenValApprox);
            System.out.println(eigenVecApprox[0][0]);

        }
    }

    private void calculateIterations() {

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