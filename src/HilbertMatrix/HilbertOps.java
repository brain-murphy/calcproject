package HilbertMatrix;

import General.Ops;

import static General.Ops.*;
import java.util.Arrays;

/**
 * Created by Brian on 3/21/2015.
 */
public class HilbertOps {

    public static void main(String[] args) {

        for (int i = 2; i <= 20; i++) {

            double[][] H = generateHilbertMatrix(i);
            double[] b_ = new double[i];
            double[][] matrixb = {b_};
            Arrays.fill(b_, Math.pow(.1, i / 3));


            //LU//

            LUFactorization luFact = LUFactorization.lu_fact(H);
            double[] luSolution = solve_lu_b(luFact, b_);

            double[] Hx_forLU = matrixVectorMult(H, luSolution);
            double normDifferenceLU = norm(matrixSubtract(new double[][]{Hx_forLU},
                    matrixb));

            System.out.println("============LU============");
            System.out.println("x_sol: " + Arrays.toString(luSolution));
            System.out.println("||LU - H|| = " + luFact.getError());
            System.out.println("||Hx - b|| = " + normDifferenceLU);
            System.out.println();


            //QR HH//

            QRFactorization QRHHFact = QRFactorization.qr_fact_househ(H);
            double[] QRHHSolution = solve_qr_b(QRHHFact, b_);

            double[] Hx_forHH = matrixVectorMult(H, QRHHSolution);
            double normDifferenceHH = norm(matrixSubtract(new double[][]{Hx_forHH},
                    matrixb));

            System.out.println("======QR Householder=======");
            System.out.println("x_sol: " + Arrays.toString(QRHHSolution));
            System.out.println("||QR - H|| = " + QRHHFact.getError());
            System.out.println("||Hx - b|| = " + normDifferenceHH);
            System.out.println();


            QRFactorization QRGFact = QRFactorization.qr_fact_givens(H);
            double[] QRGSolution = solve_qr_b(QRGFact, b_);

            double[] Hx_forG = matrixVectorMult(H, QRGSolution);
            double normDifferenceG = norm(matrixSubtract(new double[][]{Hx_forG},
                    matrixb));


            System.out.println("=========QR Givens=========");
            System.out.println("x_sol: " + Arrays.toString(QRGSolution));
            System.out.println("||QR - H|| = " + QRGFact.getError());
            System.out.println("||Hx - b|| = " + normDifferenceG);
            System.out.println();
        }
    }

    public static double[] solve_lu_b(LUFactorization factorization, double[] b_) {
        //solve for y_ for  Ly_ = b_//

        double[] y_ = Ops.backSubstitution_down(factorization.getL(), b_);

        return Ops.backSubstitution_up(factorization.getU(), y_);
    }

    public static double[] solve_qr_b(QRFactorization factorization, double[] b_) {

        double[] y_ = new double[b_.length];

        double[][] Qt = transpose(factorization.getQ());

        for (int i = 0; i < b_.length; i++) {
            for (int j = 0; j < Qt[0].length; j++) {
                y_[i] += Qt[i][j] * b_[j];
            }
        }

        return backSubstitution_up(factorization.getR(), y_);

    }

    static double[][] generateHilbertMatrix(int width){

        double[][] H = new double[width][width];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < width; j++) {
                H[i][j] = 1.0 / (i + j + 1);
            }
        }

        return H;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    static double norm(double[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToDouble(x -> Arrays.stream(x))
                .map(x -> Math.abs(x))
                .max().getAsDouble();
    }

    static void swap(double[][] matrix, int row, int row2) {
        double[] temp = matrix[row];
        matrix[row] = matrix[row2];
        matrix[row2] = temp;
    }

    /**
     * flip sign of elements below diagonal
     * @param L lower triangular matrix
     */
    static void LInverse(double[][] L) {
        for (int i = 0; i < L.length; i++) {
            for (int j = i + 1; j < L[0].length; j++) {
                L[j][i] = (-(L[j][i]));
            }
        }
    }

    /**
     * cosine in the context of Givens rotations
     * @param x element on the diagonal
     * @param y element to be eliminated
     * @return the cosine element of the givens matrix
     */
    static double cosine(double x, double y) {
        return x / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * sine in the context of Givens rotations
     * @param x element on the diagonal
     * @param y element to be eliminated
     * @return the sine element of the givens matrix
     */
    static double sine(double x, double y) {
        return (-y) / Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}