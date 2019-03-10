import java.util.Random;


public class matrix_factorization {
	 static double P[][]=new double[4][2];
	  static double Q[][]=new double[4][2];
	  static double QT[][]=new double[2][4];
	public static void main(String args[])
	{
		double a[][]={
			       {4,0,0,4},
			       {0,0,0,0},
	               {4,0,0,0},
	               {4,4,4,4}
		          };
		double alpha=0.0002;
		double beta=0.02;
		matrix_factorization(a,5000,alpha,beta);
		//Q=Transpose(QT,2,4);
//		for(int i=0;i<4;i++)
//		{
//			for(int j=0;j<4;j++)
//			   System.out.print(P[i][j]+" ");
//			System.out.println();
//		}
		double aa[][]=new double[4][4];
		aa=matrixmutiple(P,QT);
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<4;j++)
			   System.out.print(aa[i][j]+" ");
			System.out.println();
		}
		
		
	}
	  public static void matrix_factorization(double a[][],int steps,double alpha, double beta)
	  {
		 
		  int k=2;
		  Random r = new Random();
		  for(int l=0;l<4;l++)
		  {
			  for(int z=0;z<2;z++)
			  { 
				  P[l][z]= r.nextGaussian();
				  System.out.println("P[L][Z] "+P[l][z]+"l "+l+" z "+z);
			  }
		  }
		  Random r2 = new Random();
		  for(int l=0;l<4;l++)
		  {
			  for(int z=0;z<2;z++)
			  {
				  Q[l][z]=r.nextGaussian();
				  System.out.println("Q[L][Z] "+Q[l][z]+"l "+l+" z "+z);
			  }
		  }
		  QT=Transpose(Q,4,2);
		  double eij;
		  double e;
		  for (int step=0;step<steps;step++)
		  {
			      for (int z=0;z<a.length ;z++)
			      {
			    	  for (int j=0;j<a[0].length;j++) 
			    	  {
			    		  if (a[z][j]>0)
			    		  {
			    			   eij=a[z][j]-mutiple(P[z],QT,j);  // .dot(P,Q) 表示矩阵内积
			                    for (int h=0;h<k;h++)
			                    {
			                    	P[z][h]=P[z][h]+alpha*(2*eij*QT[h][j]-beta*P[z][h]);
			                    	QT[h][j]=QT[h][j]+alpha*(2*eij*P[z][h]-beta*QT[h][j]);
			                    }
			    		  }
			    	  }
			      }
			                        
			      //eR=numpy.dot(P,Q)
			      e=0;
			      for (int z=0;z<a.length ;z++)
			      {
			    	  for (int j=0;j<a[0].length;j++)
			    	  {
			    		  if (a[z][j]>0)
			    		  {
			    			  e=e+Math.pow((a[z][j]-mutiple(P[z],QT,j)),2);
			                  for (int h=0;h<k;h++)
			                  {
			                	  e=e+(beta/2)*(Math.pow(P[z][h],2)+Math.pow(QT[h][j],2));
			                  }
			    		  }           
			        	}
			        }
			    if(e<0.001)
			       break;
		  }
				            
	  }
	  public static double [][] Transpose(double [][] Matrix, int Line, int List) 
	  {
		  double [][] MatrixC = new double [List] [Line] ;
		  System.out.println("QT");
		  for (int i = 0; i < Line; i++) 
		  {
			  for (int j = 0; j < List; j++) 
			  {
				  MatrixC[j][i] = Matrix[i][j] ;
				  System.out.print( MatrixC[j][i]);
	          }
			  System.out.println();
		  }
		  return MatrixC ;
	   }
	  public static double mutiple(double a[], double b[][],int j) 
	  {
	      //当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
	      if (a.length != b.length)
	          return 0.0;
	      //c矩阵的行数y，与列数x
	      int y = a.length;
	      int x = b.length;
	     double sum=0;
	      for (int i = 0; i < y; i++)
	      {
	    	  sum=sum+a[i]*b[i][j];
	      }
	                  
	      return sum;
	  }

	    public static double[][] matrixmutiple(double a[][], double b[][]) {
	        //当a的列数与矩阵b的行数不相等时，不能进行点乘，返回null
	        if (a[0].length != b.length)
	            return null;
	        //c矩阵的行数y，与列数x
	        int y = a.length;
	        int x = b[0].length;
	        double c[][] = new double[y][x];
	        for (int i = 0; i < y; i++)
	            for (int j = 0; j < x; j++)
	                //c矩阵的第i行第j列所对应的数值，等于a矩阵的第i行分别乘以b矩阵的第j列之和
	                for (int k = 0; k < b.length; k++)
	                    c[i][j] += a[i][k] * b[k][j];
	        return c;
	    }

}
