

import java.util.Arrays;
import java.util.Random;

/**
 *Created bu Yen Huang
*/ 

public class ConvolutionalCode {

	//This will generate a n x 1 matrix
	private static int[][] generateX(int length) {
		int[][] x = new int[length][1];
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 1; j++) {
				x[i][j] = rand.nextInt(2);
			}	
		}
		return x;
	}

	//This generates a n x n matrix for A0
	private static int[][] generateA0(int[][] arr) {
		int size = arr.length;
		int[][] a = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					a[i][j] = 1;
				} else if (i > j && i - j == 2) {
					a[i][j] = 1;
				} else if (i > j && i - j == 3) {
					a[i][j] = 1;
				} else {
					a[i][j] = 0;
				}
			}
		}
		return a;
 	}

 	private int add2Binary(int x, int y) {
 		int sum = x + y;
 		if (sum == 2 || sum == 0) {
 			return 0;
 		} else {
 			return 1;
 		}
 	}

 	private int add3Binary(int x, int y, int z) {
 		int sum = x + y + z;
 		if (sum % 2 != 0) {
 			return 1;
 		} else {
 			return 0;
 		}
 	}

 	public static int[][] matrixMultiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            throw new IllegalArgumentException("matrix dimensions incompatible");
        }

        int[][] c = new int[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                    if (c[i][j] % 2 != 0) {
                    	c[i][j] = 1;
                    } else {
                    	c[i][j] = 0;
                    }
                }
            }
        }
        return c;
    }


 	public static int[][] findY0(int len) {
		int[][] x = generateX(len);
		int[][] a = generateA0(x);
		int[][] y = matrixMultiply(a, x);

		return y;
	}





	public static void printMatrix(int[][] matrix) {
        for (int[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }



	public static void main(String[] args) {

		int[][] s = findY0(6);
		printMatrix(s);
				
	}


	
	
}