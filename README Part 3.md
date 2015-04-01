Readme

First of all, be sure that everything has been compiled by following the instructions from the README for part 1.

To run the power method, open the RunPowerMethod java class and initialize the initial
    vector approximation as well as the tolerance.  Then uncomment the line 
        pm.power_method(matrix, vecApprox, tolerance);
    
Then, run the java class
    \src> java PopDynamics.RunPowerMethod < data.dat

The method will print out the eigenvalue, eigenvector, and the number of iterations needed to reach the tolerance.
If it does not converge within a tolerance within 100 iterations, it will tell you.
