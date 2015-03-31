# calcproject
Georgia Tech Calculus 3 project, by Brian Murphy, Justin Joe, and Yen Huang

## Compilation

In order to compile the project, navigate into the 'src' directory and run 

```
\src>     javac -d . ConvolutionalCodes\*.java General\*.java HilbertMatrix\*.java PopDynamics\*.java

```

This will compile the entire project; no additional compilation will be necessary in order to run the individual parts of the project. 


## Running

Everything in this part of the project should be run from the src directory

Input should be piped to file to ensure correct parsing. The conventions found in the example files a.dat and b.dat were followed
In order to pipe input input in this manner, use the following commands:

in order to use lu_fact:

```
\src>     java HilbertMatrix.lu_fact < example.dat

```

in order to use qr_fact_househ:

```
\src>     java HilbertMatrix.qr_fact_househ < example.dat

```

in order to use qr_fact_givens:

```
\src>     java HilbertMatrix.qr_fact_givens < example.dat

```

in order to use solve_lu_b:

```
\src>     java HilbertMatrix.solve_lu_b < example.dat

```

in order to use solve_qr_b:

```
\src>     java HilbertMatrix.solve_qr_b < example.dat

```



In order to run Hilbert matrix solving code for this portion of the project, navigate to the "src" directory and run 

```
\src>     java HilbertMatrix.HilbertOps

```

This will solve solve the equation Hx = b for Hilbert matrices of size two to twenty, inclusive, for  b = .1^(n/3) (1,1,...1)t. The solution will be determined and printed for the LU, Givens, and Householder methods, and printed under corresponding headings. 

