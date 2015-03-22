import static General.Ops.*;

public class PowerMethod {
    double[][] a;
    double tol;
    double eigenValApprox;
    double[][] eigenVecApprox;

    public PowerMethod(double[][] a, double tol, double[][] initial) {
        this.a = a;
        this.tol = tol;
        eigenVecApprox = initial;
    }

    public int findErrorTol() {
        return 0;
    }

    public int powerMethod(double[][] a, double[][] vecApprox) {
        eigenValApprox = vecApprox[0][0];
        eigenVecApprox = scalarMult(matrixMult(a,  vecApprox), vecApprox[0][0]);
        //should be dandy//
        return 1;
    }

    public double getEigenValue() {
        return eigenValApprox;
    }

    public double[][] getEigenVec() {
        return eigenVecApprox;
    }
}
