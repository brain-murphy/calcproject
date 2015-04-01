Readme

First of all, be sure that everything has been compiled by following the instructions from the README for part 1.


To encode an input stream x to y, run encode class:
    \src> java ConvolutionalCodes.encode


To decode an input stream y to x, open decode.java and set your size of your initial vector x in the main method. Also insert in the values for that vector. Run decode class: 
    \src> java ConvolutionalCodes.decode

    This file will require the user to manually insert in the y0 values and y1 values. It will output the number of iterations for Gauss Seidel and Jacobi and also will output the decoded stream. If it requires more than 100 iterations, the code will output "Does not converge." 


To run Jacobi iteraton, open up jacobi.java and initialize the initial vector nx1 (it's called initialX). Make sure that the length of the vector is the same as the augmented matrix's length. Run jacobi class:

    \src> java ConvolutionalCodes.jacobi < b.dat


To run Gauss-Seidel iteration, open up gauss_seidel.java and initialize the initial vector nx1 (it's called initialX). Make sure that the length of the vector is the same as the augmented matrix's length. Run gauss_seidel class:

    \src> java ConvolutionalCodes.gauss_seidel < b.dat
