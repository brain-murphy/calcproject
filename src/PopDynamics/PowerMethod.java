// package PopDynamics;
// import static General.Ops.*;

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
            eigenVecApprox = scalarMult(matrixMult(a,  vecApprox), (1 / vecApprox[0][0]));
            iterationNum++;
            vecApprox = eigenVecApprox;
            error = Math.abs(temp - eigenValApprox);
            System.out.println(eigenVecApprox[0][0]);

        }

        // for (int i = 0; i < 6; i++) {
        //     double temp = eigenValApprox;
        //     eigenValApprox = vecApprox[0][0];
        //     eigenVecApprox = scalarMult(matrixMult(a,  vecApprox), (1 / vecApprox[0][0]));
        //     iterationNum++;
        //     vecApprox = eigenVecApprox;
        //     // error = Math.abs(temp - eigenValApprox);
        //     System.out.println(eigenVecApprox[0][0]);
        // }
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

    public static double[][] matrixMult(double[][] A, double[][] B) {
        if (A[0].length != B.length) {
            throw new IllegalArgumentException("matrix dimensions incompatible");
        }

        double[][] C = new double[A.length][B[0].length];

        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    public static double[][] scalarMult(double[][] matrix, double scalar) {
        double[][] result = deepCopy(matrix);

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = result[i][j] * scalar;
            }
        }

        return result;
    }
    
    public static double[][] deepCopy(double[][] matrix) {

        double[][] newM = new double[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newM[i][j] = matrix[i][j];
            }
        }
        return newM;
    }

    public static void main(String[] args) {
        double[][] mat;
        mat = new double[2][2];
        mat[0][0] = 3;
        mat[1][0] = 2;
        mat[0][1] = 1;
        mat[1][1] = 4;
        double[][] ini = new double[2][1];
        ini[0][0] = 1;
        ini[1][0] = 1;
        PowerMethod ex = new PowerMethod(0, null, 0);
        ex.power_method(mat, ini, 0);
    }


}