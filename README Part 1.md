# calcproject
Georgia Tech Calculus 3 project, by Brian Murphy, Justin Joe, and Yen Huang

## Compilation

In order to compile the project, navigate into the 'src' directory and run 

```
\src>     javac -d . ConvolutionalCodes\*.java General\*.java HilbertMatrix\*.java PopDynamics\*.java

```

This will compile the entire project; no additional compilation will be necessary in order to run the individual parts of the project. 


## Running

In order to run Hilbert matrix solving code for this portion of the project, navigate to the "src" directory and run 

```
\src>     java HilbertMatrix.HilbertOps

```

This will solve solve the equation Hx = b for Hilbert matrices of size two to twenty, inclusive, for  b = .1^(n/3) (1,1,...1)t. The solution will be determined and printed for the LU, Givens, and Householder methods, and printed under corresponding headings. 

