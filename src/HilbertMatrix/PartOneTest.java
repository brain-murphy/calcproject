package HilbertMatrix;

import General.Ops;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;

/**
 * Created by Brian on 3/22/2015.
 */
public class PartOneTest {

    private double[][] H;
    private double[] b_;

    @Before
    public void setUp() {
        H = HilbertOps.generateHilbertMatrix(4);
        b_ = new double[]{0.0464159, 0.0464159, 0.0464159, 0.0464159};
    }

    @Test(timeout = 1000)
    public void testLUFactorization() {

        LUFactorization factorization = LUFactorization.lu_fact(H);

        double[][] expectedL = {{1,0,0,0},
                                {0.5,1,0,0},
                                {1.0/3,1,1,0},
                                {.25,.9,1.5,1}};
        double[][] expectedU = {{1,.5,1.0/3,.25},
                                {0,.0833333,.0833333,.075},
                                {0,0,.00555556,.00833333},
                                {0,0,0,.000357143}};

        System.out.println("L");
        HilbertOps.printMatrix(factorization.getL());

        System.out.println("U");
        HilbertOps.printMatrix(factorization.getU());

        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                assertEquals("U matrix incorrect", expectedU[i][j],
                        factorization.getU()[i][j], .00001);
            }
        }
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                assertEquals("L matrix incorrect", expectedL[i][j],
                        factorization.getL()[i][j], .00001);
            }
        }
    }

    @Test(timeout = 1000)
    public void testQRHouseholderFactorization() {

        QRFactorization factorization = QRFactorization.qr_fact_househ(H);

        double[][] expectedQ = {{0.838116, -0.522648, 0.153973, 0.0263067},
                                {0.419058, 0.441713, -0.727754, -0.31568},
                                {0.279372, 0.528821, 0.139506, 0.7892},
                                {0.209529, 0.502072, 0.653609, -0.526134}};

        double[][] expectedR = {{1.19315, 0.670493, 0.474933, 0.369835},
                                {0, 0.118533, 0.125655, 0.117542},
                                {0, 0, 0.00622177, 0.00956609},
                                {0, 0, 0, -0.000187905}};

        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                assertEquals("Q matrix incorrect", expectedQ[i][j],
                        factorization.getQ()[i][j], .00001);
                assertEquals("R matrix incorrect", expectedR[i][j],
                        factorization.getR()[i][j], .00001);
            }
        }
    }

    @Test(timeout = 1000)
    public void testQRGivensFactorization() {

        QRFactorization factorization = QRFactorization.qr_fact_givens(H);

        double[][] expectedQ = {{0.838116, -0.522648, 0.153973, -0.0263067},
                                {0.419058, 0.441713, -0.727754, 0.31568},
                                {0.279372, 0.528821, 0.139506, -0.7892},
                                {0.209529, 0.502072, 0.653609, 0.526134}};

        double[][] expectedR = {{1.19315, 0.670493, 0.474933, 0.369835},
                                {0., 0.118533, 0.125655, 0.117542},
                                {0., 0., 0.00622177, 0.00956609},
                                {0., 0., 0., 0.000187905}};

        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                assertEquals("R matrix incorrect", expectedR[i][j],
                        factorization.getR()[i][j], .00001);
            }
        }
        for (int i = 0; i < H.length; i++) {
            for (int j = 0; j < H[0].length; j++) {
                assertEquals("Q matrix incorrect", expectedQ[i][j],
                        factorization.getQ()[i][j], .00001);
            }
        }
    }

    @Test(timeout = 1000)
    public void testLUSolving() {

        double[] expectedX_ = {-0.185664, 2.78495, -8.35486, 6.49822};

        LUFactorization factorization = LUFactorization.lu_fact(H);

        double[] x_ = HilbertOps.solve_lu_b(factorization, b_);

        for (int i = 0; i < x_.length; i++) {
            assertEquals("elements differ at index:" + i, expectedX_[i], x_[i], .00001);
        }
    }

    @Test(timeout = 1000)
    public void testQRHHSolving() {
        double[] expectedX_ =  {-0.185664, 2.78495, -8.35486, 6.49822};

        QRFactorization factorization = QRFactorization.qr_fact_househ(H);

        double[] x_ = HilbertOps.solve_qr_b(factorization, b_);

        for (int i = 0; i < x_.length; i++) {
            assertEquals("elements differ at index:" + i, expectedX_[i], x_[i], .00001);
        }
    }

    @Test(timeout = 1000)
    public void testQRGSolving() {
        double[] expectedX_ =  {-0.185664, 2.78495, -8.35486, 6.49822};

        QRFactorization factorization = QRFactorization.qr_fact_givens(H);

        double[] x_ = HilbertOps.solve_qr_b(factorization, b_);

        for (int i = 0; i < x_.length; i++) {
            assertEquals("elements differ at index:" + i, expectedX_[i], x_[i], .00001);
        }
    }
}
