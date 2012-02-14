/**
 *
 * Joseph Romanowski
 * Instructor: Massimo Dipierro
 * Final Project
 *
 * This code is distributed under the BSD license and it is a rewrite
 * of code shared in class CSC431 at DePaul University by Massimo Di Pierro.
 *
 */

import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class Matrix
{
    int rows;
    int cols;
    float [][] data;

	public Matrix(int rows, int cols)
    {

 		this.rows = rows;
		this.cols = cols;
		this.data = new float [rows][cols];
			for(int r=0; r <data.length; r++)
      			for(int c=0; c <data[r].length; c++)
      				data[r][c] = 0;

	} // end public Matrix


	//method to add matrices
    public float[][] add(Matrix a, Matrix b)
    {
		try {
		float[][] sum = new float[a.data.length][a.data[0].length];
   			for (int r = 0; r < a.data.length; r++)
            {
                for (int c = 0; c < a.data[r].length; c++)
                {
                    sum[r][c] = a.data[r][c] + b.data[r][c];
                }
 			}
			return sum;
			} // end try block

		catch (ArrayIndexOutOfBoundsException imeRef)
		{
			System.out.println("Cannot add matrices because they are not the same size!");
			return null;
		}
	} // end add method



	//method to subtract matrices
    public float[][] subtract(Matrix a, Matrix b)
    {
		try {
		float[][] diff = new float[a.data.length][a.data[0].length];
   			for (int r = 0; r < a.data.length; r++)
            {
                for (int c = 0; c < a.data[r].length; c++)
                {
                    diff[r][c] = a.data[r][c] - b.data[r][c];
                }
 			}
			return diff;
			} //end try block

		catch (ArrayIndexOutOfBoundsException imeRef)
		{
			System.out.println("Cannot subtract matrices because they are not the same size!");
			return null;
		}
    } // end subtract method


	//method to multiply matrices
    public static float[][] multiply(Matrix a, Matrix b) {
		try {
	    float[][] product = new float[a.data.length][b.data[0].length];
		    for (int r=0; r<a.data.length; r++)
				for (int c=0; c<b.data[0].length; c++)
					for (int k=0; k<a.data[0].length; k++)
					product[r][c] += a.data[r][k] * b.data[k][c];
				    return product;
			} // end try block
		catch (ArrayIndexOutOfBoundsException imeRef)
		{
			System.out.println("Column length of first matrix does not equal row length second matrix!");
			return null;
		}
	} // end multiply method


	// method to find inverse of a matrix
	public float[][] inverse(Matrix a) {
		try {
		float[][] invB = new float[a.rows][a.cols];
		float p;
		float q;
		int m;

			for(int r=0; r<invB[0].length;r++)
			invB[r][r] = 1;
				for(int c=0; c<a.cols;c++) {
				m = c;
				p = a.data[c][c];

			for (int i=c+1; i<a.rows; i++){
				if(abs(a.data[i][c]) > abs(p)) {m=i; p=a.data[i][c];}}

			for(int i=0; i<a.cols; i++)
			{
				float xa = a.data[m][i];
				float ya = a.data[c][i];
				a.data[m][i] = ya;
				a.data[c][i] = xa;

				float xb = invB[m][i];
				float yb = invB[c][i];
				invB[m][i] = yb;
				invB[c][i] = xb;
			}

			for(int i=0; i<a.cols; i++)
			{
				a.data[c][i] /= p;
				invB[c][i] /= p;
			}

			for(int r=0; r<a.rows; r++)
				if(r!=c) {
					q = a.data[r][c];
						for(int i=0; i<a.cols; i++) {
						a.data[r][i] -= q * a.data[c][i];
						invB[r][i] -= q * invB[c][i];
						}
				}
			}
  			return invB;
		} // end try block
		catch (ArrayIndexOutOfBoundsException imeRef)
		{
			System.out.println("Column length does not equal row length!");
			return null;
		}
} // end inverse method


	// method to print matrix
	public static void printMatrix (float[][] z)
	{
		try{
	    for (int r = 0; r < z.length; r++)
	    {
			for (int c = 0; c < z[r].length; c++)
				System.out.printf("%f  ", z[r][c]);
	 		System.out.println();
		}
	 		System.out.println();
			} //end try block
	 	catch (NullPointerException imeRef)
		{
		System.out.println("Error!");
		}
	} //end printMatrix method



 	public static void main(String[] args)
    {

    Matrix a = new Matrix(3,3);
    a.data[0][0]=4; a.data[0][1]=5; a.data[0][2]=9;
    a.data[1][0]=-1; a.data[1][1]=4; a.data[1][2]=7;
    a.data[2][0]=3; a.data[2][1]=0; a.data[2][2]=5;

    Matrix b = new Matrix(3,3);
    b.data[0][0]=2; b.data[0][1]=0; b.data[0][2]=0;
    b.data[1][0]=3; b.data[1][1]=-2; b.data[1][2]=3;
    b.data[2][0]=4; b.data[2][1]=-4; b.data[2][2]=7;

    float [][] zAdd = a.add(a,b);
    float [][] zSub = a.subtract(a,b);
    float [][] zMult = a.multiply(a,b);
    float[][] zInv = a.inverse(a);

	printMatrix(zAdd);
	printMatrix(zSub);
	printMatrix(zMult);
	printMatrix(zInv);

    } //end Main method


}// end class Matrix


