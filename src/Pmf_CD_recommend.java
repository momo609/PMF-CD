import java.util.ArrayList;
import java.util.HashMap;


public class Pmf_CD_recommend {

	public HashMap<Integer,HashMap<Integer,Double>> recommend(double cp[][],int q[][])
	{
		double average[]=new double[12];
	    HashMap<Integer,ArrayList<Integer>>lowpro=new  HashMap<Integer,ArrayList<Integer>>();
	    HashMap<Integer,HashMap<Integer,Double>>s_umkg=new HashMap<Integer,HashMap<Integer,Double>>();
	    HashMap<Integer,Integer>pro_kg=new HashMap<Integer,Integer>();
	    int umkgnum[]=new int[12];
	    double sum;
	    for(int i=0;i<12;i++)
	    {
	    	sum=0;
	    	for(int j=0;j<56;j++)
	    	{
	    		sum=sum+cp[i][j];
	    	}
	    	average[i]=sum/56;
	    }
//	    for(int i=0;i<12;i++)
//	    {
//	    	System.out.print(average[i]+" ");
//	    }
//	    System.out.println();
	    for(int i=0;i<12;i++)
	    {
	    	ArrayList<Integer>lowproid=new ArrayList<Integer>();
	    	for(int j=0;j<56;j++)
	    	{
	    		//if(cp[i][j]<average[i])
	    		if(cp[i][j]<1000)
	    		{
	    			lowproid.add(j);
	    		}
	    	}
	    	lowpro.put(i,lowproid); 
	    }
	   // System.out.println(lowpro);
	    for(int i=0;i<56;i++)
	    {
	    	for(int j=0;j<11;j++)
	    	{
	    		if(q[i][j]==1)
	    			pro_kg.put(i, j+1);   //key题号，value知识点id
	    	}
	    }
	    for(int i=0;i<12;i++)
	    {
	    	 HashMap<Integer,Double>sp_umkg=new HashMap<Integer,Double>();  //key知识点id，value概率
	    	ArrayList<Integer>lowproid=lowpro.get(i);
	    	for(int j=0;j<lowproid.size();j++)
	    	{
	    		int kgid=pro_kg.get(lowproid.get(j));
	    		if(sp_umkg.get(kgid)==null)
	    		 {
	    			sp_umkg.put(kgid, cp[i][lowproid.get(j)]);
	    			umkgnum[kgid]=1;
	    		 }
	    		else
	    		{
	    			double degree=sp_umkg.get(kgid);
	    			degree=degree+cp[i][lowproid.get(j)];
	    			sp_umkg.put(kgid, degree);
	    			umkgnum[kgid]++;
	    		}
	    	}
	    	for(int j=1;j<=11;j++)
	    	{
	    		if(sp_umkg.get(j)!=null)
	    		{
	    			double degree=sp_umkg.get(j)/umkgnum[j];
		    		sp_umkg.put(j, degree);
	    		}
	    		
	    	}
	    	s_umkg.put(i, sp_umkg);
	    }
	    return s_umkg;
	}
	
}
