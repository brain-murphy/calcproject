package HilbertMatrix;

import General.Ops;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Brian on 3/31/2015.
 */
public class solve_lu_b {

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

        LUFactorization factorization = LUFactorization.lu_fact(matrix);

        double[] solution = HilbertOps.solve_lu_b(factorization, vector);
        double[] Ax = Ops.matrixVectorMult(matrix, solution);
        double err = HilbertOps.norm(Ops.matrixSubtract(new double[][]{Ax},
                new double[][]{vector}));

        System.out.println("Solution(x):");
        System.out.println(Arrays.toString(solution));
        System.out.println("\n||Ax - b||");
        System.out.println(err);
    }
}
