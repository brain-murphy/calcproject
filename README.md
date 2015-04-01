# GT Calc 3 Linear Algebra project
Georgia Tech Calculus 3 project, by Brian Murphy, Justin Joe, and Yen Huang


This project comprises the submission for a Calculus assignment, and I suppose I'll leave it up as a demo of sorts, or a reference, should future students want an example to view. 

## Part one : Matrix factorization with the Hilbert matrix. 
### Brian Murphy

In the package ``` HilbertMatrix ```  are several procedures that factorize matrices as A = LU and A = QR. The QR factorization procedures for both the Householder algorithm and the Givens algorithm were implemented. These procedures can be run from the command line (see the Readme file for part one), or can be called by other java code. 

In order to use the factorization procedures from other java code, parse your matrix into a two dimensional array of doubles, where each array represents a row, and call LUFactorization.lu_fact() or the equivalents for the QRFactorization class on that matrix. This will return a corresponding LUFactorization or QRFactorization Object, which can be passed to either solve_lu_b() or solve_qr_b() in the HilbertOps class, respectively. When you call either of these methods, submit with your matrix a vector b, in the form of an array of doubles. These solving methods will return a vector x, where LUx = b, or QRx = b.
