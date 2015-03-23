import static General.Ops.*;

public class PowerMethod {
    private double[][] a;
    private double tol;
    private double eigenValApprox;
    private double[][] eigenVecApprox;
    private double iterationNum;

    public PowerMethod(double[][] a, double tol, double[][] initial) {
        this.a = a;
        this.tol = tol;
        eigenVecApprox = initial;
    }


    public int powerMethod(double[][] a, double[][] vecApprox) {
        double error = 100;
        while (error > tol) {
            double temp = eigenValApprox;
            eigenValApprox = vecApprox[0][0];
            eigenVecApprox = scalarMult(matrixMult(a,  vecApprox), (1 / vecApprox[0][0]));
            iterationNum++;
            vecApprox = eigenVecApprox;
            error = Math.abs(temp - eigenValApprox);
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
