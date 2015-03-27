package ConvolutionalCodes;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yenhuang on 3/27/15.
 */

// goes from Y to X
public class decode {

    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }


    //This generates a n x n matrix for A0
    private static double[][] generateA0(int size) {

        double[][] a = new double[size][size];

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
    private static double[][] generateA1(int size) {
        double[][] a = new double[size][size];

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

    private static double[][] generateY0(int length) {
        double[][] y0 = new double[length][1];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 1; j++) {
                y0[i][j] = rand.nextInt(2);
            }
        }
        printMatrix(y0);
        return y0;

    }

    private static double[][] generateY1(int length) {
        double[][] y1 = new double[length][1];
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 1; j++) {
                y1[i][j] = rand.nextInt(2);
            }
        }
        printMatrix(y1);
        return y1;

    }


    private static double[][] createY(double[][] y0, double[][] y1) {
        double[][] arr = new double[y0.length][1];
        for (int i = 0; i < y0.length; i++) {
            arr[i][0] = (int)((y0[i][0] * 10) + y1[i][0]);
        }
        printMatrix(arr);
        return arr;

    }

   private static double[][] combineIntoAugmented(double[][] a, double[][] y) {
       double[][] aug = new double[a.length][a[0].length + 1];

       for (int i = 0; i < a.length; i++) {
           for (int j = 0; j < a[0].length; j++) {
               aug[i][j] = a[i][j];
           }
       }

       for (int k = 0; k < y.length; k++) {
           aug[k][aug[0].length -1 ] = y[k][0];
       }
       printMatrix(aug);
       return aug;
   }

    //decompose augmented matrix into A
    private static double[][] decomposeIntoA(double[][] matrix) {
        double[][] a = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                a[i][j] = matrix[i][j];
            }
        }
        printMatrix(a);
        return a;
    }

    //decompose augmented matrix into Bd
    private static double[][] decomposeIntoB(double[][] matrix) {
        double[][] a = new double[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                a[i][j] = matrix[i][j];
            }
        }
        printMatrix(a);
        return a;
    }



    // this is the method that does the entire decoding
    public static void decode(double[][] matrix, double[][] guess, double tol) {
        double[][] a = decomposeIntoA(matrix);


        if (checkifA0(a)) {
            System.out.println("Matrix is A0");

            // need to combine A0 with Y0 into a single matrix



//            double[][] y = findB(matrix);
//            double[][] y0 = findY0(convertIntoStringArray(y));
//
//
//            double[][] m = combineIntoAugmented(a,y0);
//            double[][] result = returnNewX(m, guess, tol);
//            double[][] finalAnswer = reduce3spots(result);
//            return finalAnswer;
        } else if (checkifA1(a)) {
            System.out.println("Matrix is A1");
            // need to combine A1 with Y1 into a single matrix
//            double[][] y = findB(matrix);
//            double[][] y1 = findY0(convertIntoStringArray(y));
//
//            double[][] m = combineIntoAugmented(a, y1);
//            double[][] result = returnNewX(m, guess, tol);
//            double[][] finalAnswer = reduce3spots(result);
//            return finalAnswer;
        } else {
            throw new IllegalArgumentException("Matrix needs to be A0 or A1. It is not in the right format");
        }

    }






    //checks if input matrix "A" is A0
    private static boolean checkifA0(double[][] matrix) {
        double[][] a0 = generateA0(matrix.length);
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum += a0[i][j] - matrix[i][j];
            }
        }
        return (sum == 0);
    }

    //checks if input matrix "A" is A1
    private static boolean checkifA1(double[][] matrix) {
        double[][] a0 = generateA1(matrix.length);
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sum += a0[i][j] - matrix[i][j];
            }
        }
        return (sum == 0);
    }


    // returns (n - 3) x 1
    private static double[][] reduce3spots(double[][] matrix) {
        double [][] result = new double[matrix.length - 3][1];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = matrix[i][0];
        }
        return result;
    }











    public static void main(String[] args) {
        double[][] y0 = generateY0(3);
        System.out.println("");

        double[][] y1 = generateY1(3);
        System.out.println("");

        double[][] y = createY(y0, y1);

        double[][] a = generateA0(3);
        System.out.println("");

        System.out.println("This is augmented matrix");
        double[][] augmented = combineIntoAugmented(a, y);

        double[][] initialX = new double[3][1];
        initialX[0][0] = 0;
        initialX[1][0] = 0;
        initialX[2][0] = 0;

        System.out.println("");
        System.out.println("Begin Decoding");


        decode(augmented, initialX, 0.0001);

    }


}
