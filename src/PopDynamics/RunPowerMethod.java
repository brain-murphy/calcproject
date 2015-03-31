package PopDynamics;
import General.Ops;

/**
 * Created by Justin Joe on 3/31/2015.
 */
public class RunPowerMethod {
    public static void main(String[] args) {
        double[][] matrix = Ops.parseMatrix();
        double[][] initialVectorApproximation;
        //first, initialize the vector approximation as a n by 1 two dimensional double array.

        double tolerance;
        //then initialize tolerance

        PowerMethod pm = new PowerMethod(0, null, 0);

        //uncomment the following line and run the program, inputting your matrix data file
        //pm.power_method(matrix, initialVectorApproximation, tolerance);
    }
}
