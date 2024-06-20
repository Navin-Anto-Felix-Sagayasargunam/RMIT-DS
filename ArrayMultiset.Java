package implementation;

import java.util.ArrayList;
import java.util.List;
public class ArrayMultiset extends RmitMultiset{
    
	protected String s[];
	protected int I[];
    public void add(String element) {
        if (s == null){
          s = new String[1];
          I= new int[1];
          s[0] = element;
          I[0] =1;} 
        else{
    	    int c=0;
            for(int i=0; i<s.length; i++){
    			  if(element.compareTo(s[i])==0){
    				  I[i]++;c++;}}
            if(c==0){	
    	        String S[] = new String[s.length + 1];
                int n[] =new int[I.length + 1];
                for (int i = 0; i < s.length; i++){
        	         S[i] = s[i];
        	         n[i]= I[i];}
                     S[s.length] = element;
                     n[I.length]= 1;
                 s = S;
                 I = n;}}}
 @Override
		public int search(String element) {
	        if(s!=null){
                 for(int i=0; i<s.length; i++){
                 if(element.compareTo((s[i]))==0){
        	          return I[i];}}}
	      return searchFailed;}

 @Override
	    public List<String> searchByInstance(int instanceCount) {
	    List<String> search = new ArrayList<String>();
        if(s!=null){
            for(int i=0; i<s.length; i++){
                 if(instanceCount==I[i]){       	
            			search.add(s[i]);}}}
        return search;}
        public boolean contains(String element) {
	       if(s!=null){
              for(int i=0; i<s.length; i++){
	          if(element.compareTo(s[i])==0){
	    		  return true;}}}
         return false;}

@Override
	    public void removeOne(String element) {
            int ct=0;	  
	    	int ind=-1;
            if(s!=null){
               for(int i=0; i<s.length;i++){	  
	    			if(element.compareTo(s[i])==0){	    
	    				I[i]--;ct=I[i];ind=i;}}
            if(ct==0){	
	    			String[] S= new String[s.length-1];	 
	    			int[] n= new int[I.length-1];
                    if(ind==S.length){
	    			for(int i=0; i< S.length; i++){	    				
	    				  S[i]=s[i];n[i]=I[i];}}
            else if(ind==0){	
	    		    for(int i=0; i<S.length; i++){	
	    				S[i]=s[i+1];n[i]=I[i+1];}}
            else {
	    			for(int i=0; i<ind; i++){	
	    				S[i]=s[i];n[i]=I[i];}
	                for(int j=ind; j<s.length-1; j++){	
	    			    S[j]=s[j+1];n[j]=I[j+1];}}	
	    			    s=S;I=n;}}} 

@Override
	     public String print() {
	           String print=" ";
               if(s!=null){
                   for(int i=0; i<s.length-2; i++){
                     int min=i;
                     for(int j=i+1; j<s.length-1; j++){
        	            if(I[min]<I[j]){
                            min=j;}}
                        String Str=s[i];s[i]=s[min];s[min]=Str;
        				int Int=I[i];I[i]=I[min];I[min]=Int;}
                     for(int i=0; i<s.length; i++){
	                     print=print+"\n"+s[i]+": "+I[i];}}
 	          return print;} 

 @Override
		public String printRange(String lower, String upper) {
            String pRange="";
            if(s!=null){
                for(int i=0;i<s.length; i++){
	                if(s[i].compareTo(lower)>=0 && s[i].compareTo(upper)<=0){
	                       pRange=pRange+s[i]+" :"+I[i];}}}
            return pRange;}
	    
  @Override
		public RmitMultiset union(RmitMultiset other) {
        String[] S= ((ArrayMultiset) other).StringArray();   
	    RmitMultiset union= new ArrayMultiset();
	    if(S!=null || s!=null){	    			    	
 	         for(int i=0; i<S.length; i++){	   
	    			union.add(S[i]);}
             for(int i=0; i<s.length; i++){	   
	    			union.add(s[i]);}
             String [] string=((ArrayMultiset) union).StringArray();
	    	 int [] n=((ArrayMultiset) union).I;
             for(int i=0; i<string.length; i++){
	    		 n[i]=1;}}
	   return union;}

  @Override
		public RmitMultiset intersect(RmitMultiset other) {
        String[] S= ((ArrayMultiset) other).StringArray();   
        RmitMultiset intersect= new ArrayMultiset();
        if(S!=null || s!=null){	    			    	
  	        for(int i=0; i<S.length; i++){	   
	    		intersect.add(S[i]);}
            for(int i=0; i<s.length; i++){	   
	    	    intersect.add(s[i]);}
	    String [] string=((ArrayMultiset) intersect).StringArray();
	    int [] n=((ArrayMultiset) intersect).I;
        for(int i=0; i<string.length; i++){
	    		 if(n[i]==1){
	    			 intersect.removeOne(string[i]);}
	    		 else if(n[i]==2)
	    		 {n[i]=1;}}}
	    return intersect;}

  @Override
		public RmitMultiset difference(RmitMultiset other) {
        String[] S= ((ArrayMultiset) other).StringArray();   
        RmitMultiset difference= new ArrayMultiset();
        if(S!=null || s!=null){	    			    	
	         for(int i=0; i<S.length; i++){	   
	    	     difference.add(S[i]);}
             for(int i=0; i<s.length; i++){	   
	    		 difference.add(s[i]);}
             String [] string=((ArrayMultiset) difference).StringArray();
	    	 int [] n=((ArrayMultiset) difference).I;
             for(int i=0; i<string.length; i++){
	    		 if(n[i]==2){
	    			 difference.removeOne(string[i]);
	    			 difference.removeOne(string[i]);}}}	   	    
	    	 return difference;} 
    public String[] StringArray() {return s;}}  
  


