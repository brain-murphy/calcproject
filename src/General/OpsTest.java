package General;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 * Created by Brian on 3/25/2015.
 */
public class OpsTest {

    double[][] A;
    double[] b_;
    double[] expectedX_;

    @Test
    public void backSubDownTest() {

        A = new double[][]{ {1,0,0},
                {4,2,0},
                {5,6,3}};
        b_ = new double[]{7, 8, 9};


        expectedX_ = new double[]{7, -10, 34.0/3};

        double[] x_ = Ops.backSubstitution_down(A, b_);

        for (int i = 0; i < expectedX_.length; i++) {
            assertEquals("x_ incorrect at index:" + i, expectedX_[i], x_[i], .000001);
        }
    }

    @Test
    public void backSubUpTest() {
        A = new double[][]{ {3,6,5},
                            {0,2,4},
                            {0,0,1}};

        b_ = new double[]   {9, 8, 7};

        expectedX_ = new double[] {34.0/3, -10, 7};

        double[] x_ = Ops.backSubstitution_up(A, b_);


        for (int i = 0; i < expectedX_.length; i++) {
            assertEquals("x_ incorrect at index:" + i, expectedX_[i], x_[i], .000001);
        }
    }
}
