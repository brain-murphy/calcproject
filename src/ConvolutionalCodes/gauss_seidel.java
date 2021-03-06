package ConvolutionalCodes;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import java.util.ArrayList;


public class gauss_seidel {

    private static int iteration;
    private double[][] firstX;
    private double tolerance;
    private double[][] gaussMatrix;
    private static double[][] answer;





    public gauss_seidel(double[][] gaussMatrix, double[][] firstX, double tolerance) {
        this.gaussMatrix = gaussMatrix;
        this.firstX = firstX;
        this.tolerance = tolerance;
        iteration = 0;

        answer = returnNewX(gaussMatrix, firstX, tolerance);



    }

    public double[][] getAnswer() {
        return answer;
    }



    // finds b matrix
    // input is a nx(n+1) matrix
    private static double[][] findB(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length;
        double[][] b = new double[length][1];
        for (int i = 0; i < length; i++) {
            for (int j = width - 1; j < width; j++) {
                b[i][0] = matrix[i][j];
            }
        }
//        System.out.println("This is b matrix");
//        printMatrix(b);
//        System.out.println("");
        return b;
    }

    // find "A" matrix from the augmented matrix [A|B]
    private static double[][] findA(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] mat = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                mat[i][j] = matrix[i][j];
            }
        }
//        System.out.println("This is A matrix");
//        printMatrix(mat);
//        System.out.println("");
        return mat;
    }


    // finds upper triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findUpper(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] upper = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < width; j++) {
                upper[i][j] = matrix[i][j];
            }
        }
//        System.out.println("This is Upper matrix");
//        printMatrix(upper);
//        System.out.println("");
        return upper;
    }

    // finds lower triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findLower(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] lower = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < i; j++) {
                lower[i][j] = matrix[i][j];
            }
        }
//        System.out.println("This is lower matrix");
//        printMatrix(lower);
//        System.out.println("");
        return lower;
    }

    // finds diagonal triangular matrix
    // input is a nx(n+1) matrix
    private static double[][] findDiagonal(double[][] matrix) {
        int length = matrix.length;
        int width = matrix[0].length - 1;
        double[][] diag = new double[length][width];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (i == j) {
                    diag[i][j] = matrix[i][j];
                }

            }
        }
//        System.out.println("This is the diagonal matrix");
//        printMatrix(diag);
//        System.out.println("");
        return diag;
    }

    //a method for adding two matrices
    private static double[][] matrixAdd(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }


    // a method for multiplying matrices
    private static double[][] multiplyTwoX(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {

                    c[i][j] += a[i][k] * b[k][j];

                }
            }
        }
        return c;
    }

    // a method for negating a matrix
    private static double[][] negateMatrix(double[][] matrix) {
        double[][] neg = new double[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                neg[i][j] = -matrix[i][j];
            }
        }
        return neg;
    }

    // This is -(Ux) + b
    private static double[][] combineUpperXandB(double[][] matrix, double[][] initialX) {
        double[][] upper = findUpper(matrix);

        double[][] b = findB(matrix);

        double[][] mult = multiplyTwoX(upper, initialX);
        double[][] negatedMult = negateMatrix(mult);


        double[][] result = matrixAdd(negatedMult, b);
//        System.out.println("This is -Ux + b");
//        printMatrix(result);
        return result;
    }

    //L+D
    private static double[][] combineLowerAndDiagonal(double[][] matrix) {
        double[][] lower = findLower(matrix);
        double [][] diag = findDiagonal(matrix);
        double[][] result = matrixAdd(lower, diag);
//        System.out.println("This is L + D");
//        printMatrix(result);
        return result;
    }

    // row reducing an augmented matrix (L+D)|(-Ux + b)
    private static double[][] gaussElimination(double[][] a, double[][] b) {
        double[][] combined = new double[a.length][a[0].length + 1];
        for (int c = 0; c < combined.length; c++) {
            for (int d = 0; d < combined[0].length - 1; d++) {
                combined[c][d] = a[c][d];
            }
        }

        for (int e = 0; e < combined.length; e++) {
            for (int f = combined[0].length - 1; f < combined[0].length; f++) {
                combined[e][f] = b[e][0];
            }
        }

        //row-reducing starts now
        for (int col = 0; col < combined.length; col++) {
            int topRow = col;
            for (int row = col + 1; row < combined.length; row++) {
                if (Math.abs(combined[row][col]) > Math.abs(combined[topRow][col])) {
                    topRow = row;
                }
            }

            // swapping for partial pivoting
            double[] swapped = combined[col];
            combined[col] = combined[topRow];
            combined[topRow] = swapped;


            // making it in echelon form
            for (int i = col + 1; i < combined.length; i++) {
                double multFactor = combined[i][col] / combined[col][col];
                for (int j = col; j < combined.length + 1; j++) {
                    combined[i][j] = combined[i][j] - multFactor * combined[col][j];
                }

            }

            double divisionFactor = 0;
            for (int i = 0; i < combined.length; i++) {
                for (int j = 0; j < combined[0].length; j++) {

                    if (i == j) {
                        divisionFactor = combined[i][j];
                    }

                    combined[i][j] = combined[i][j] / divisionFactor;
                }

            }


        }

        double[][] second = findB(combined);
        double[][] first = findA(combined);


        //backward substitution
        double[][] result = backSubstitution_up(first, second);

//        printMatrix(result);

        return result;
    }

    private static double[][] backSubstitution_up(double[][] matrix, double[][] b_) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        if (b_.length != numCols) {
            throw new IllegalArgumentException("wrong dimensions for solving");
        }
        double[][] x_ = new double[b_.length][1];
        for (int row = numRows - 1; row >=0; row--) {
            //use previous vals//
            double known = 0.0;
            for (int col = numCols - 1; col > row; col--) {
                known += matrix[row][col] * x_[col][0];
            }
            x_[row][0] = (b_[row][0] - known) / matrix[row][row];
        }
        return x_;
    }

    // returns X of k+1 when you do (L+D)X = (-U)(X) + b
    public static double[][] returnNewX(double[][] matrix, double[][] x, double tol) {
        double error = 100;
        double[][] finalX = new double[x.length][1];
        while(error >= tol && iteration < 100) {
            double[][] temp = x;
            double[][] lAndD = combineLowerAndDiagonal(matrix);
            double[][] r = combineUpperXandB(matrix, x);
            finalX = gaussElimination(lAndD, r);

            double[][] result = matrixSubtract(finalX, temp);
            double mag = findMagnitude(result);

            error = mag;
            iteration++;

            x = finalX;
        }
        if (iteration >= 100) {
            throw new IllegalArgumentException("Does not converge to tolerance");
        }

        return finalX;
    }


    private static double findMagnitude(double[][] a) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sum += (a[i][j] * a[i][j]);
            }
        }
        return Math.sqrt(sum);
    }


    public static double[][] matrixSubtract(double[][] A, double[][] B) {
        double[][] C = new double[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }






    //returns the number of iterations needed for gauss seidel
    public static int getIteration() {
        return iteration;
    }







    public static void printMatrix(double[][] matrix) {
        for (double[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

    public static void printStringMatrix(String[][] matrix) {
        for (String[] v : matrix) {
            System.out.println(Arrays.toString(v));
        }
    }

      public static double[][] parseMatrix() {
       Scanner in  = new Scanner(System.in);

        ArrayList<String[]> lines = new ArrayList<>();

        while (in.hasNextLine()) {
            lines.add(in.nextLine().trim().split(" "));
        }

        double[][] matrix = new double[lines.size()][lines.get(0).length - 1];
        double[][] vector = new double[lines.size()][1];

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length; j++) {
                if (j < matrix[0].length) {
                    matrix[i][j] = Double.parseDouble(lines.get(i)[j]);
                } else {
                    vector[i][0] = Double.parseDouble(lines.get(i)[j]);
                }
            }
        }
        return combineIntoAugmented(matrix, vector);

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




    public static void main(String[] args) {

        double tol = 0.00000001;


        double[][] initialX = new double[4][1];
        initialX[0][0] = 0;
        initialX[1][0] = 0;
        initialX[2][0] = 0;
        initialX[3][0] = 0;


        double[][] test = parseMatrix();
//        test[0][0] = 3;
//        test[0][1] = -1;
//        test[0][2] = 1;
//        test[0][3] = 1;
//
//        test[1][0] = 3;
//        test[1][1] = 6;
//        test[1][2] = 2;
//        test[1][3] = 0;
//
//        test[2][0] = 3;
//        test[2][1] = 3;
//        test[2][2] = 7;
//        test[2][3] = 4;

        gauss_seidel g = new gauss_seidel(test,initialX,0.00000001);

        System.out.println("Iteration: " + getIteration());

    }
}

