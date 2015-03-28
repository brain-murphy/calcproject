package ConvolutionalCodes;

import General.Ops;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
* Created by yenhuang on 3/27/15.
*/

// goes from Y to X
public class decode {



    private double[][] initialX;

    private int lengthOfY;

    public decode(int lengthOfY, double[][] initialX) {
        this.lengthOfY = lengthOfY;
        this.initialX = initialX;

        double[][] a = generateA0(lengthOfY);
        double[][] y0 = generateY0(lengthOfY);
        double[][] y1 = generateY1(lengthOfY);
        double[][] y = createY(y0, y1);


        double[][] augmented = combineIntoAugmented(a, y);

        System.out.println("");
        System.out.println("Begin Decoding Jacobi");
        decodeJacobi(augmented, initialX, 0.00000001);


        System.out.println("");
        System.out.println("Begin Decoding Gauss-Seidel");
        decodeGauss(augmented, initialX, 0.00000001);



    }





    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
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


        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < length; i++) {
            System.out.println("What do you want the values of Y0 to be at index " + i +  "?");
            int num = scanner.nextInt();
            if (num == 0 || num == 1) {
                y0[i][0] = num;
            } else {
                throw new IllegalArgumentException("Input needs to be 1 or 0");
            }




        }

        return y0;

    }

    private static double[][] generateY1(int length) {
        double[][] y1 = new double[length][1];
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < length; i++) {
            System.out.println("What do you want the values of Y1 to be at index " + i +  "?");
            int num = scanner.nextInt();
            if (num == 0 || num == 1) {
                y1[i][0] = num;
            } else {
                throw new IllegalArgumentException("Input needs to be 1 or 0");
            }

        }
        return y1;

    }


    private static double[][] createY(double[][] y0, double[][] y1) {
        double[][] arr = new double[y0.length][1];
        for (int i = 0; i < y0.length; i++) {
            arr[i][0] = (int)((y0[i][0] * 10) + y1[i][0]);
        }
//        printMatrix(arr);
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
//       printMatrix(aug);
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
//        printMatrix(a);
        return a;
    }

    //decompose augmented matrix into B
    private static double[][] decomposeIntoB(double[][] matrix) {
        double[][] b = new double[matrix.length][1];
        for (int i = 0; i < matrix.length; i++) {
            b[i][0] = matrix[i][matrix[0].length - 1];
        }
        return b;
    }

    //decompose Y into Y0
    private static double[][] decomposeIntoY0(double[][] y) {
        double[][] y0 = new double[y.length][1];
        for (int i = 0; i < y.length; i++) {
            y0[i][0] = Math.floor(y[i][0] / 10);
        }
        System.out.println("This is Y0");
        printMatrix(y0);
        return y0;

    }

    //decompose Y into Y1
    private static double[][] decomposeIntoY1(double[][] y) {
        double[][] y1 = new double[y.length][1];
        for (int i = 0; i < y.length; i++) {
            y1[i][0] = y[i][0] % 10;
        }
        System.out.println("This is Y1");
        printMatrix(y1);
        return y1;

    }


    // this is the method that does the entire decoding in Jacobi iteration
    public static double[][] decodeJacobi(double[][] matrix, double[][] guess, double tol) {
        double[][] a = decomposeIntoA(matrix);
        double[][] y = decomposeIntoB(matrix);


        if (checkifA0(a)) {

            double[][] y0 = decomposeIntoY0(y);


            double[][] combinedAandY0 = combineIntoAugmented(a, y0);


            jacobi jac = new jacobi(combinedAandY0,guess,tol);


            double[][] finalXJacobi = jac.getAnswer();


            double[][] jacobi = reduce3spots(finalXJacobi);


            double[][] answer = convertIntoBinary(jacobi);

            System.out.println("This is final answer for Jacobi");
            printMatrix(answer);
            System.out.println("Number of iteration for Jacobi: " + jac.getIteration());

            return answer;
        } else if (checkifA1(a)) {

            double[][] y1 = decomposeIntoY1(y);


            double[][] combinedAandY1 = combineIntoAugmented(a, y1);


            jacobi jac = new jacobi(combinedAandY1,guess,tol);



            double[][] finalXJacobi = jac.getAnswer();
            double[][] jacobi = reduce3spots(finalXJacobi);


            double[][] answer = convertIntoBinary(jacobi);

            System.out.println("This is final answer for Jacobi");
            printMatrix(answer);
            System.out.println("Number of iteration for Jacobi: " + jac.getIteration());

            return answer;

        } else {
            throw new IllegalArgumentException("Matrix needs to be A0 or A1. It is not in the right format");
        }

    }

    // this is the method that does the entire decoding in Gauss-Seidel
    public static double[][] decodeGauss(double[][] matrix, double[][] guess, double tol) {
        double[][] a = decomposeIntoA(matrix);
        double[][] y = decomposeIntoB(matrix);

        if (checkifA0(a)) {

            double[][] y0 = decomposeIntoY0(y);


            double[][] combinedAandY0 = combineIntoAugmented(a, y0);

            gauss_seidel gs = new gauss_seidel(combinedAandY0, guess, tol);

            double[][] finalXGauss = gs.getAnswer();

            double[][] gauss = reduce3spots(finalXGauss);


            double[][] answer = convertIntoBinary(gauss);

            System.out.println("This is final answer for Gauss-Seidel");
            printMatrix(answer);
            System.out.println("Number of iteration for Gauss-Seidel: " + gs.getIteration());

            return answer;

        } else if (checkifA1(a)) {

            double[][] y1 = decomposeIntoY1(y);


            double[][] combinedAandY1 = combineIntoAugmented(a, y1);

            gauss_seidel gs = new gauss_seidel(combinedAandY1, guess, tol);


            double[][] finalXGauss = gs.getAnswer();
            double[][] gauss = reduce3spots(finalXGauss);


            double[][] answer = convertIntoBinary(gauss);

            System.out.println("This is final answer for Gauss-Seidel");
            printMatrix(answer);
            System.out.println("Number of iteration for Gauss-Seidel: " + gs.getIteration());

            return answer;


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

    private static double[][] convertIntoBinary(double[][] matrix) {
        double[][] a = new double[matrix.length][1];
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] % 2 != 0) {
                a[i][0] = 1;
            } else {
                a[i][0] = 0;
            }
        }
        return a;
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


        double[][] initialGuess = new double[17][1];
        initialGuess[0][0] = 0;
        initialGuess[1][0] = 0;
        initialGuess[2][0] = 0;
        initialGuess[3][0] = 0;
        initialGuess[4][0] = 0;
        initialGuess[5][0] = 0;
        initialGuess[6][0] = 0;
        initialGuess[7][0] = 0;
        initialGuess[8][0] = 0;
        initialGuess[9][0] = 0;
        initialGuess[10][0] = 1;
        initialGuess[11][0] = 1;
        initialGuess[12][0] = 1;
        initialGuess[13][0] = 1;
        initialGuess[14][0] = 1;
        initialGuess[15][0] = 1;
        initialGuess[16][0] = 1;


        decode dec = new decode(17, initialGuess);



    }


}
