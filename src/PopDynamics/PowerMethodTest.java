package PopDynamics;

import junit.framework.TestCase;
import org.junit.Test;

public class PowerMethodTest extends TestCase {
    double[][] mat = new double[2][2];
    double[][] ini = new double[2][1];

    @Test(timeout = 1000)
    public void testPower() {
        ini[0][0] = 1;
        ini[1][0] = 1;
        PowerMethod ex = new PowerMethod(0, null, 0);
        mat[0][0] = 3;
        mat[1][0] = 2;
        mat[0][1] = 1;
        mat[1][1] = 4;
        ex.power_method(mat, ini, 0);
//        assertEquals(1, ex.getEigenValue());
        assertEquals(1, ex.getEigenVec());
    }

}