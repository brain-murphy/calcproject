package ConvolutionalCodes;

import java.util.Arrays;

/**
 * Created by yenhuang on 3/27/15.
 */
public class decode {





    // finds y0
    //takes in a string 2d array
    public static double[][] findY0(String[][] y) {

        double[][] y0 = new double[y.length][1];
        for (int i = 0; i < y.length; i++) {
            y0[i][0] = Double.parseDouble(y[i][0].substring(0,1));
        }
        System.out.println("This is y0");
        printMatrix(y0);
        return y0;
    }

    // finds y1
    //takes in a string 2d array
    public static double[][] findY1(String[][] y) {

        double[][] y1 = new double[y.length][1];
        for (int i = 0; i < y.length; i++) {
            y1[i][0] = Double.parseDouble(y[i][0].substring(1,2));
        }
        System.out.println("This is y1");
        printMatrix(y1);
        return y1;
    }

    //printing out a double matrix
    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    //printing out a String matrix
    public static void printStringMatrix(String[][] matrix) {
        for (String[] v : matrix) {
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


    // make it into augmented matrix
    private static double[][] combineIntoAugmented(double[][] a, double[][]b) {
        double[][] c = new double[a.length][a[0].length + 1];
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                c[i][j] = a[i][j];
            }
        }

        for (int i = 0; i < c.length; i++) {
            c[i][c.length - 1] = b[i][0];

        }


        return c;
    }

    // this is the method that does the entire decoding
//    public static double[][] decode(double[][] matrix, double[][] guess, int tol) {
//        double[][] a = findA(matrix);
//
//        if (checkifA0(a)) {
//            System.out.println("Matrix is A0");
//
//            // need to combine A0 with Y0 into a single matrix
//            double[][] y = findB(matrix);
//            double[][] y0 = findY0(convertIntoStringArray(y));
//
//
//            double[][] m = combineIntoAugmented(a,y0);
//            double[][] result = returnNewX(m, guess, tol);
//            double[][] finalAnswer = reduce3spots(result);
//            return finalAnswer;
//        } else if (checkifA1(a)) {
//            System.out.println("Matrix is A1");
//            // need to combine A1 with Y1 into a single matrix
//            double[][] y = findB(matrix);
//            double[][] y1 = findY0(convertIntoStringArray(y));
//
//            double[][] m = combineIntoAugmented(a, y1);
//            double[][] result = returnNewX(m, guess, tol);
//            double[][] finalAnswer = reduce3spots(result);
//            return finalAnswer;
//        } else {
//            throw new IllegalArgumentException("Matrix needs to be A0 or A1. It is not in the right format");
//        }
//
//    }


}
