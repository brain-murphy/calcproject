import static General.Ops.*;

import junit.framework.TestCase;
import org.junit.Test;

public class PowerMethodTest extends TestCase {

    private double[][] setUpLeslie() {
        double[][] leslie = new double[9][9];
        leslie[0][1] = 1.2;
        leslie[0][2] = 1.1;
        leslie[0][3] = .9;
        leslie[0][4] = .1;
        leslie[1][0] = .7;
        leslie[2][1] = .85;
        leslie[3][2] = .9;
        leslie[4][3] = .9;
        leslie[5][4] = .88;
        leslie[6][5] = .8;
        leslie[7][6] = .77;
        leslie[8][7] = .4;
        return leslie;
    }

    private double[][] setUpInitial() {
        double[][] initial = new double[9][1];
        initial[0][0] = 2.1*(Math.pow(10, 5));
        initial[1][0] = 1.9*(Math.pow(10,5));
        initial[2][0] = 1.8*(Math.pow(10,5));
        initial[3][0] = 2.1*(Math.pow(10,5));
        initial[4][0] = 2.0*(Math.pow(10,5));
        initial[5][0] = 1.7*(Math.pow(10,5));
        initial[6][0] = 1.2*(Math.pow(10,5));
        initial[7][0] = .9*(Math.pow(10,5));
        initial[8][0] = .5*(Math.pow(10,5));
        return initial;
    }
    @Test(timeout = 1000)
    public void testPower() {
        double[][] mat = new double[2][2];
        double[][] ini = new double[2][1];
        ini[0][0] = 1;
        ini[1][0] = 1;
        PowerMethod ex = new PowerMethod(0, null, 0);
        mat[0][0] = 3;
        mat[1][0] = 2;
        mat[0][1] = 1;
        mat[1][1] = 4;
        ex.power_method(mat, ini, .00001);
        assertEquals(4.999995973473565, ex.getEigenValue());
        assertEquals(15.0, ex.getIterationNum());
        double[][] eigVecActual = new double[2][1];
        eigVecActual[0][0] = .4472138836151307;
        eigVecActual[1][0] = .8944270469422714;
        assertEquals(eigVecActual[0][0], ex.getEigenVec()[0][0]);
        assertEquals(eigVecActual[1][0], ex.getEigenVec()[1][0]);
//        assertEquals(eigVecActual, ex.getEigenVec());
    }

    @Test(timeout = 1000)
    public void testLeslieMatrix() {
        double[][] leslie = setUpLeslie();

        double[][] initial = setUpInitial();

        //2010 data
        double[][] pop2010 = matrixMult(leslie, initial);
        double totalPop2010 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2010 = totalPop2010 + pop2010[i][0];
        }
        System.out.println(totalPop2010);

        //2020 data
        double[][] pop2020 = matrixMult(matrixMult(leslie, leslie), initial);
        double totalPop2020 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2020 = totalPop2020 + pop2020[i][0];
        }
        System.out.println(totalPop2020);

        //2030 data
        double[][] pop2030 = matrixMult(matrixMult(leslie, matrixMult(leslie, leslie)), initial);
        double totalPop2030 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2030 = totalPop2030 + pop2030[i][0];
        }
        System.out.println(totalPop2030);

        //2040 data
        double[][] pop2040 = matrixMult(
                matrixMult(leslie, matrixMult(leslie, matrixMult(leslie, leslie))), initial);
        double totalPop2040 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2040 = totalPop2040 + pop2040[i][0];
        }
        System.out.println(totalPop2040);

        //2050 data
        double[][] pop2050 = matrixMult(
                matrixMult(leslie, matrixMult(leslie, matrixMult(
                        leslie, matrixMult(leslie, leslie)))), initial);
        double totalPop2050 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2050 = totalPop2050 + pop2050[i][0];
        }
        System.out.println(totalPop2050);

        double[][] pop2100 = matrixMult(
                matrixMult(leslie, matrixMult(leslie, matrixMult(
                        leslie, matrixMult(leslie, matrixMult(
                                leslie, matrixMult(leslie, matrixMult(
                                        leslie, matrixMult(leslie, matrixMult(
                                                leslie, leslie))))))))), initial);
        double totalPop2100 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2100 = totalPop2100 + pop2100[i][0];
        }
        System.out.println(totalPop2100);

        double[][] pop2110 = matrixMult(
                matrixMult(leslie, matrixMult(leslie, matrixMult(
                        leslie, matrixMult(leslie, matrixMult(
                                leslie, matrixMult(leslie, matrixMult(
                                        leslie, matrixMult(leslie, matrixMult(
                                                leslie, matrixMult(leslie, leslie)))))))))), initial);
        double totalPop2110 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2110 = totalPop2110 + pop2110[i][0];
        }
        System.out.println(totalPop2110);
    }

    @Test(timeout = 1000)
    public void testLeslieEigenValue() {
        double[][] leslie = setUpLeslie();
        double[][] initial = new double[9][1];
        initial[0][0] = 1;
        initial[1][0] = 1;
        initial[2][0] = 1;
        initial[3][0] = 1;
        initial[4][0] = 1;
        initial[5][0] = 1;
        initial[6][0] = 1;
        initial[7][0] = 1;
        initial[8][0] = 1;
        //calculate the eigenvalue of the leslie matrix
        PowerMethod ex = new PowerMethod(0, null, 0);
        ex.power_method(leslie, initial, .00000001);
        assertEquals(1, 1);
    }

    @Test(timeout = 1000)
    public void testLeslieHalfBirthRate() {
        double[][] leslie = setUpLeslie();
        double[][] initial = setUpInitial();

        double[][] pop2010 = matrixMult(leslie, initial);
        double totalPop2010 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2010 = totalPop2010 + pop2010[i][0];
        }
        System.out.println(totalPop2010);

        //2020 data
        double[][] pop2020 = matrixMult(matrixMult(leslie, leslie), initial);
        double totalPop2020 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2020 = totalPop2020 + pop2020[i][0];
        }
        System.out.println(totalPop2020);

        leslie[0][1] = leslie[0][1] / 2;

        //2030 data
        double[][] pop2030 = matrixMult(matrixMult(leslie, matrixMult(leslie, leslie)), initial);
        double totalPop2030 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2030 = totalPop2030 + pop2030[i][0];
        }
        System.out.println(totalPop2030);

        //2040 data
        double[][] pop2040 = matrixMult(
                matrixMult(leslie, matrixMult(leslie, matrixMult(leslie, leslie))), initial);
        double totalPop2040 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2040 = totalPop2040 + pop2040[i][0];
        }
        System.out.println(totalPop2040);

        //2050 data
        double[][] pop2050 = matrixMult(
                matrixMult(leslie, matrixMult(leslie, matrixMult(
                        leslie, matrixMult(leslie, leslie)))), initial);
        double totalPop2050 = 0;
        for (int i = 0; i < 9; i++) {
            totalPop2050 = totalPop2050 + pop2050[i][0];
        }
        System.out.println(totalPop2050);

        // calculate eigenvalue of new leslie matrix
        PowerMethod ex = new PowerMethod(0, null, 0);
        ex.power_method(leslie, initial, .00000001);
        System.out.println(ex.getEigenValue());
    }
}
