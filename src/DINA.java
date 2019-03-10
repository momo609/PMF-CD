import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;


public class DINA {
	double[][] studentdata=new double[4209][20];
	int[][] knowledgerelation=new int[20][11];

	static double[] s=new double[15];
	static double[] g=new double[15]; 
	int[] finalh=new int[4209];
	int[][] h=new int[4209][15];
	int[][] pxal=new int[15][];
	int [][] Il=new int[15][];
	int [][] rl=new int[15][];
    ArrayList<Integer> pal=new ArrayList<Integer>();
public void em() throws IOException
{
//	for(int i=0;i<4209;i++)
//	{
		for(int j=0;j<15;j++)
		{
			s[j]=0.6;
			g[j]=0.5;
		}
		
//	}
	FileInputStream fis = null;
	InputStreamReader isr = null;
	int scount=0;
	int kcount=0;
	BufferedReader br = null;
	String[][] scoredata=new String[4209][];
	String[][] knowledgedata=new String[20][11];
	try {
		String str="";
	   fis = new FileInputStream("C:/Users/admin/Desktop/大四重启动/math2015/math2015/Math1/data.txt");
	    isr = new InputStreamReader(fis);
	    br = new BufferedReader(isr);
	    while ((str= br.readLine()) != null) {
	    	scoredata[scount]=str.split("\t"); 
	    	     //System.out.println(data[count].length+" "+count);
	             scount++;
	            
	    }
	    fis = new FileInputStream("C:/Users/admin/Desktop/大四重启动/math2015/math2015/Math1/q.txt");
	    isr = new InputStreamReader(fis);
	    br = new BufferedReader(isr);
	    while ((str= br.readLine()) != null) {
	    	knowledgedata[kcount]=str.split("\t"); 
	    	     //System.out.println(data[count].length+" "+count);
	         kcount++;
	            
	    }
	    
	  } catch (FileNotFoundException e) {
	   System.out.println("找不到指定文件");
	  } catch (IOException e) {
	   System.out.println("读取文件失败");
	  } finally {
	      try {
	           br.close();
	           isr.close();
	           fis.close();
	          } catch (IOException e) {
	               e.printStackTrace();
	          }
	  }
	for(int i=0;i<scount;i++)
	{
		for(int j=0;j<scoredata[i].length;j++)
		{
			studentdata[i][j]=Double.parseDouble(scoredata[i][j]);
			//System.out.print(studentdata[i][j]+" ");
		}
		//System.out.println("\n");
	}
	for(int i=0;i<kcount;i++)
	{
		for(int j=0;j<knowledgedata[i].length;j++)
		{
			knowledgerelation[i][j]=Integer.parseInt(knowledgedata[i][j]);
			//System.out.print(knowledgerelation[i][j]+" ");
		}
		//System.out.println("\n");
	}
	double ll=0;
	double h;
	for(int i=0;i<1000;i++)
	{
		//System.out.println("likelihold="+e_step()+"  "+ll);
		h=e_step();
		if(ll>=h)
			break;
	}
	File file = new File("C:/Users/admin/Desktop/大四重启动/param.txt");
	   // if file doesnt exists, then create it
	   if (!file.exists()) {
	       file.createNewFile();
	   }
	   FileWriter fw = new FileWriter(file.getAbsoluteFile());
	   BufferedWriter bw = new BufferedWriter(fw);
	   String params;
	for(int z=0;z<15;z++)
	{
	   //System.out.println(z+" "+s[z]+" "+g[z]);
	   params=s[z]+" "+g[z]+"\r\n";
	   bw.write(params);
	}
	   bw.close();
}
public double e_step()
{
	double[] result=new double[3];
	double likelihold=1;
	double finallikelihold=0;
	
	 double a0all=1;
	double a1all=1;
	int countyes=0;
	double pa = 0;
	double ll;
	for(int i=0;i<4209;i++)
	{
		for(int j=0;j<15;j++)
		{
			if(studentdata[i][j]==1)
				countyes++;
		}
	}
//	for(int z=0;z<4209;z++)
//	{
//		if(finallikelihold<likelihold)
//			{
//			  finallikelihold=likelihold;
//			     for(int i=0;i<4209;i++)
//			     {
//			    	 finalh[i]=h[i];
//			     }
//			}
	    //System.out.println(finallikehold);
//		likehold=1;
//		for(int i=0;i<4209;i++)
//		{
//			for(int j=0;j<15;j++)
//			{
//				
//				//System.out.println(i+" "+j+" "+a1all+" "+a0all+" "+s[i]);
//				double a0=Math.pow(g[i],studentdata[i][j])*Math.pow((1-g[i]), (1-studentdata[i][j]));
//				double a1=Math.pow((1-s[i]),studentdata[i][j])*Math.pow(s[i], (1-studentdata[i][j]));
//				a0all=a0all*a0;
//				a1all=a1all*a1;
//				if(Double.isNaN(a1all))
//				{
//					System.out.println(i+" "+j+" "+a1all+" "+a0all+" "+a1+" "+s[z]);
//					return 1.0;
//				}
//				pa=pa+(((double)countyes/(double)4209)*a1all)*(((double)countyes/(double)4209)*a0all);
//				
//			}
//			//System.out.println(a0all+" "+a1all);
//			
//			//System.out.println(i+" "+pa);
//			if(a0all<a1all)
//			{
//			     h[i]=1;
//			    // pa=((double)countyes/(double)4029)*a1all;
//			     likehold=likehold*a1all*pa;
//			}
//		    else
//		    {
//			     h[i]=0;
//			     //pa=((double)countyes/(double)4029)*a0all;
//			     likehold=likehold*a0all*pa;
//		    }
//			//System.out.println( h[i]);
//			//likehold=likehold*h[z]*pa;
//			//System.out.println(a0all+" "+a1all);
//			
//		}
		likelihold=e_single(countyes);
//		    m_step(z,h);
//		
//		
//	}
	
	
	return likelihold;
}
public double e_single(int countyes)
{
	double a0all=1;
	double a1all=1;
	double pa=1;
	double lxa = 1;
	double lxi=0;
	double lx=1;
	double likelihold=1;
	int binary[] = new int[15];
	for(int i=0;i<4209;i++)
	{
		int flag=0;
		for(int l=0;l<Math.pow(2, 11);l++)
		{
			int a=l;
			int h=0;
			if(a==0)
			{
				for(int k=0;k<11;k++)
					binary[k] = 0;
			}
			while(a>=1)
			{
		        if (a % 2 == 0) {
		        	binary[h] = 0;
		        } else {
		        	binary[h] = 1;
		        }
		        a=a/2;
		        h++;
		        //System.out.println(binary[h]+" "+h);
		    }
			for(int j=0;j<15;j++)
			{
				for(int k=0;k<11;k++)
				{
					//System.out.println(knowledgerelation[j][k]);
					if(knowledgerelation[j][k]==binary[k])
					{
						flag=1;
						break;
					}
				}
				if(flag==1)
				{
					BigDecimal a00 = new BigDecimal(Double.toString(Math.pow(g[j],studentdata[i][j])));  
				     BigDecimal a01 = new BigDecimal(Double.toString(Math.pow((1-g[j]), (1-studentdata[i][j]))));   
				     BigDecimal f_a0 = a00.multiply(a01);// 相乘结果
				     BigDecimal three = new BigDecimal("3");
				     double a0 = f_a0.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数  p(x|al)
				     lxa=lxa*a0; //l(x|al)
				     pa=pa*g[j];
				}
				else if(flag==0)
				{
					 BigDecimal a10 = new BigDecimal(Double.toString(Math.pow((1-s[j]),studentdata[i][j])));  
				     BigDecimal a11 = new BigDecimal(Double.toString(Math.pow(s[j], (1-studentdata[i][j]))));   
				     BigDecimal f_a1 = a10.multiply(a11);// 相乘结果
				     BigDecimal three = new BigDecimal("3");  
				     double a1=f_a1.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数;  p(x|al)
				     lxa=lxa*a1; //l(x|al)
				     pa=pa*(1-s[j]);
				}
			}
			 BigDecimal lx0 = new BigDecimal(Double.toString(lxa));  
		     BigDecimal lx1 = new BigDecimal(Double.toString(pa));   
		     BigDecimal f_lx = lx0.multiply(lx1);// 相乘结果
		     BigDecimal three = new BigDecimal("3");  
		     double lxl =f_lx.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数;  
		     lxi=lxi+lxl;
		}
		lx=lx*lxi;
	}
	m_step();
//	m_step(h);
	return lx;
}
public void m_step()
{
	System.out.println("111");
	double ijl0;
	double ijl1 = 0;
	double rjl0 = 0;
	double rjl1 = 0;
	double countyes=0;
	for(int j=0;j<15;j++)
	{
		ijl0=Math.pow(g[j],2)*4209;
		for(int i=0;i<4209;i++)
		{
			if(studentdata[i][j]==1)
			{
				countyes++;
				rjl0=rjl0+(double)Math.pow(1-s[j], 2);
			}
			ijl1=ijl1+(double)Math.pow(g[j], 2)/((double)countyes/4209);
			rjl1=rjl1+(double)Math.pow(1-s[j], 2)/((double)countyes/4209);
		}
		s[j]=(ijl1-rjl1)/ijl1;
		g[j]=rjl0/ijl0;
		System.out.println(s[j]+" "+g[j]);
	}
}
public static void main(String args[]) throws IOException
{
	
	   new DINA().em();
}
}
//public int[] intrevbinary(int a)
//{
//	int result[] = null;
//	int i=0;
//	while(a>=1)
//	{
//      if (a % 2 == 0) {
//      	result[i] = 0;
//      } else {
//      	result[i] = 1;
//      }
//      a=a/2;
//      i++;
//  }
//	return result;
//}
//public void m_step(int[][]h)
//{
//	
//	int count0=0;
//	int count1=0;
//	int count0yes=0;
//	int count1yes=0;
//	double updates;
//	double updateg;
//	for(int j=0;j<15;j++)
//	{
//		count0=0;
//		count1=0;
//		count0yes=0;
//		count1yes=0;
//		for(int i=0;i<4209;i++)
//		{
//			if(h[i][j]==0)
//				count0++;
//			else if(h[i][j]==1)
//				count1++;
//			if(h[i][j]==0&&studentdata[i][j]==1)
//				count0yes++;
//			if(h[i][j]==1&&studentdata[i][j]==1)
//				count1yes++;
//		}
//		System.out.println(count1+" "+count1yes+" "+count0+" "+count0yes);
//		updates=((double)count1/(double)4209-(double)count1yes/(double)4209)/((double)count1/(double)4209);
//		updateg=((double)count0yes/(double)4209)/((double)count0/(double)4209);
//		//System.out.println("updates="+updates+"  updateg="+updateg+" "+(double)count1/(double)4209+" "+(double)count1yes/(double)4209+" "+((double)count1/(double)4209));
//		s[j]=updates;
//		g[j]=updateg;
//		System.out.println("s[j]="+s[j]+"  g[j]="+g[j]);
//	}
//	
//	
////	for(int i=0;i<4209;i++)
////	{
////		System.out.println(s[i]+" "+g[i]);
////	}
//	//System.out.println(row+" "+count0+" "+count1+" "+count0yes+" "+count1yes+" "+updates+" "+updateg);
//}
//for(int j=0;j<15;j++)
//{
//	
//	//System.out.print(i+" "+j+" "+a1all+" "+a0all+" ");
//	BigDecimal a00 = new BigDecimal(Double.toString(Math.pow(g[j],studentdata[i][j])));  
//     BigDecimal a01 = new BigDecimal(Double.toString(Math.pow((1-g[j]), (1-studentdata[i][j]))));   
//     BigDecimal f_a0 = a00.multiply(a01);// 相乘结果
//     BigDecimal a10 = new BigDecimal(Double.toString(Math.pow((1-s[j]),studentdata[i][j])));  
//     BigDecimal a11 = new BigDecimal(Double.toString(Math.pow(s[j], (1-studentdata[i][j]))));   
//     BigDecimal f_a1 = a10.multiply(a11);// 相乘结果
//     BigDecimal three = new BigDecimal("3");  
//     double a0 = f_a0.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数
//	double a1=f_a1.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数;
//	//System.out.println(a0+" "+a1);
////	if(a0==0||a1==0)
////	{
////		System.out.println(i+" "+j+" "+a0+" "+a1);
////		return 1.0;
////	}
//	
////	 BigDecimal a0all0 = new BigDecimal(Double.toString(a0all));  
////     BigDecimal a0all1 = new BigDecimal(Double.toString(a0));   
////     BigDecimal f_aall0 = a0all0.multiply(a0all1);// 相乘结果
////     BigDecimal a1all0 = new BigDecimal(Double.toString(a1all));  
////     BigDecimal a1all1 = new BigDecimal(Double.toString(a1));   
////     BigDecimal f_a1all = a1all0.multiply(a1all1);// 相乘结果
////     a0all = f_aall0.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数
////     a1all=f_a1all.divide(three,2,BigDecimal.ROUND_HALF_UP).doubleValue();//保留3位数;
//	a0all=a0all*a0;
//	a1all=a1all*a1;
////     System.out.println(i+" "+j+" "+a1all+" "+a0all+" ");
////     if(a0all==0||a1all==0)
////		{
////			//System.out.println(i+" "+j+" "+a0all+" "+a1all+" "+a0+" "+a1);
////			return 1.0;
////		}
//     
//     
//    // System.out.println(i+" "+j+" "+a0all+" "+a1all);
//	//System.out.println(z+" "+a0all+" "+a1all);
////	if(Double.isNaN(a1all))
////	{
////		
////		System.out.println(i+" "+j+" "+a1all+" "+a0all+" "+a1+" "+s[i]);
////		return 1.0;
////	}
//	llxapa=llxapa+(((double)countyes/(double)4209)*a1all)*(((double)countyes/(double)4209)*a0all);  //P（afal）要改怎么求？？？
//	
//	if(a0all<a1all)
//	{
//	     h[i][j]=1;
//	    // pa=((double)countyes/(double)4029)*a1all;
//	    
//	}
//    else
//    {
//	     h[i][j]=0;
//	    //pa=((double)countyes/(double)4029)*a0all;
//     }
//}
////System.out.println(a0all+" "+a1all);
//
////System.out.println(i+" "+pa);
//
// likelihold=likelihold*llxapa;
////System.out.println( h[i]);
//System.out.println(likelihold);