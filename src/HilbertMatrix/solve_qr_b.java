package HilbertMatrix;

import General.Ops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Brian on 3/31/2015.
 */
public class solve_qr_b {

    public static void main(String[] args) {


        //parse input//
        Scanner in  = new Scanner(System.in);

        ArrayList<String[]> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            lines.add(in.nextLine().trim().split(" "));
        }

        double[][] matrix = new double[lines.size()][lines.get(0).length - 1];
        double[] vector = new double[lines.size()];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length; j++) {
                if (j < matrix[0].length) {
                    matrix[i][j] = Double.parseDouble(lines.get(i)[j]);
                } else {
                    vector[i] = Double.parseDouble(lines.get(i)[j]);
                }
            }
        }


        //factor and solve//

        QRFactorization factorization = Math.random() > .5 ?
                QRFactorization.qr_fact_househ(matrix) :
                QRFactorization.qr_fact_givens(matrix);

        double[] solution = HilbertOps.solve_qr_b(factorization, vector);
        double[] Ax = Ops.matrixVectorMult(matrix, solution);
        double err = HilbertOps.norm(Ops.matrixSubtract(new double[][]{Ax},
                new double[][]{vector}));

        System.out.println("Solution(x):");
        System.out.println(Arrays.toString(solution));
        System.out.println("\n||Ax - b||");
        System.out.println(err);
    }
}
