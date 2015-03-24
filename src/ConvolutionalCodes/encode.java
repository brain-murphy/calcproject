package ConvolutionalCodes;
import java.util.Arrays;
import java.util.Random;

/**
 *Created bu Yen Huang
*/ 

public class encode {

	// Add 3 more slots to x array 
	 private static int[][] addThreeToX(int[][] matrix) {
        int[][] newM = new int[matrix.length + 3][(matrix[0].length)];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newM[i][j] = matrix[i][j];
            }
        }
        return newM;
    }

	//This will generate a n x 1 matrix
	private static int[][] generateX(int length) {
		int[][] x = new int[length][1];
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < 1; j++) {
				x[i][j] = rand.nextInt(2);
			}	
		}

		int[][] newX = addThreeToX(x);
		for (int i = newX.length - 3; i < newX.length; i++) {
			for (int j = 0; j < 1; j++) {
				newX[i][j] = 0;
			}	
		}
		System.out.println("This is X");
		printMatrix(x);
		System.out.println("");
		return newX;
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

 	//This generates a n x n matrix for A1
	private static int[][] generateA1(int[][] arr) {
		int size = arr.length;
		int[][] a = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i == j) {
					a[i][j] = 1;
				} else if (i > j && i - j == 1) {
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

 	// Multiply A with x
 	public static int[][] matrixMultiply(int[][] a, int[][] b) {
        int[][] c = new int[a.length][1];

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

    //this is the encoding portion to find y
 	public static String[][] findY(int len) {
		int[][] x = generateX(len);

		int[][] a0 = generateA0(x);
		int[][] y0 = matrixMultiply(a0, x);
		// System.out.println("This is Y0");
		// printMatrix(y0);
		// System.out.println("");

		int[][] a1 = generateA1(x);
		int[][] y1 = matrixMultiply(a1, x);
		// System.out.println("This is Y1");
		// printMatrix(y1);
		// System.out.println("");

		String[][] y = new String[len + 3][1];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < y.length; i++) {
			for (int j = 0; j < 1; j++) {

				for (int k = 0; k < y0.length; k++) {
					y[i][j] = Integer.toString(y0[i][j]) + Integer.toString(y1[i][j]) ;
				}

			}
		}
		System.out.println("This is Y");
		printStringMatrix(y);
		return y;
	}





	public static void printMatrix(int[][] matrix) {
        for (int[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    public static void printStringMatrix(String[][] matrix) {
        for (String[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }


	public static void main(String[] args) {
        findY(5);
	}	
	
}