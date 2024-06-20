package implementation;

import java.util.ArrayList;
import java.util.List;

public class OrderedLinkedListMultiset extends RmitMultiset{
	protected Node Linkedlist_head;
	protected int Linkedlist_length;
	public OrderedLinkedListMultiset() {
		   Linkedlist_head = null;
		   Linkedlist_length = 0;}
	public void add(String item) {
        Node n = new Node(item);
		if (Linkedlist_head == null) {
		    Linkedlist_head = n;}
		else{
			int check=0;		 		       		
			Node currentNode = Linkedlist_head;     			        		
			while(currentNode!=null){	
               if(item.compareTo(currentNode.getElement())==0){		 
					currentNode.instance(1);check=1;}
		       currentNode= currentNode.getNext();}
               if(check==0){		        	  
				Node cNode=Linkedlist_head;
		       if(item.compareTo(cNode.getElement())<=0)			
				{	n.setNext(Linkedlist_head);Linkedlist_head=n;Linkedlist_length++;}
               else if(item.compareTo(cNode.getElement())>0) {
		            int c=0;		        		  
					while( cNode.getNext()!=null){		 
						if(item.compareTo(cNode.getElement())>0 && item.compareTo(cNode.getElement())<=0){
		                    n.setNext(cNode.getNext());
		                    cNode.setNext(n);Linkedlist_length++;c=1;}
		                    cNode=cNode.getNext();}
               if(c==0){		  					
						cNode.setNext(n);Linkedlist_length++;}}}}}

@Override
		public int search(String item) {
	        Node cNode=Linkedlist_head;
            while(cNode!=null) {
              if(item.compareTo(cNode.getElement())==0) {return cNode.getInstance();}
                cNode=cNode.getNext();}
            return searchFailed;}

@Override
		public List<String> searchByInstance(int instanceCount) {
            Node cNode=Linkedlist_head;
            List<String> search= new ArrayList<String>();
            while(cNode!=null) {
                if(instanceCount==cNode.getInstance()) {
        	         search.add(cNode.getElement());}
              cNode=cNode.getNext();}
            return search;}

@Override
		public boolean contains(String item) {
	        Node cNode =Linkedlist_head;
           while(cNode!=null){
               if(item.compareTo(cNode.getElement())==0){
        		return true;}
            cNode=cNode.getNext();}
            return false;}

@Override
		public void removeOne(String item) {
           Node cNode=Linkedlist_head;
         if(cNode.getElement().compareTo(item)==0) 	   	   
	    	 {cNode.instance(-1);
		if(cNode.getInstance()==0) 	    	    
	    		{	cNode=cNode.getNext();Linkedlist_head=cNode;Linkedlist_length--;}}
	    	else{	  		    			    
	    		for(int i=0; i<Linkedlist_length; i++){	    	    			    	
	    			if(cNode.getNext().getElement().compareTo(item)==0){
	    				cNode.getNext().instance(-1);
	    				if(cNode.getNext().getInstance()==0) 
	    				{	cNode.setNext(cNode.getNext().getNext());Linkedlist_length--;}}   	    	
	    			cNode= cNode.getNext();}}}

 @Override
		public String print() {
            Node cNode = Linkedlist_head;
	           String p = new String();
           while (cNode != null){	        
	        	   p= p+cNode.getElement() + " :"+cNode.getInstance()+"\n";	       
	        	   cNode = cNode.getNext();}
           return p;}

@Override
		public String printRange(String lower, String upper) {
       Node cNode=Linkedlist_head;
	   String pRange="";
    while(cNode!=null){	  
	    	if(cNode.getElement().compareTo(lower)>=0 & cNode.getElement().compareTo(upper)<=0) 	  
	    		{	pRange=pRange+cNode.getElement()+" :"+cNode.getInstance()+"\n";}	        
	    		cNode=cNode.getNext();}
	         return pRange;} 

@Override
	   public RmitMultiset union(RmitMultiset other) {
	   RmitMultiset union=new OrderedLinkedListMultiset();
	   Node n = ((OrderedLinkedListMultiset) other).find();
	   Node cNode=Linkedlist_head;
       while(n!=null){	    
	      union.add(n.getElement());n=n.getNext();}
       while(cNode!=null){
	      union.add(cNode.getElement());
	      cNode=cNode.getNext();}
	  Node result=((OrderedLinkedListMultiset) union).find();
	    	  while(result!=null) 
		    	{ result.setInstance(1); 	    				   	
	    		  result=result.getNext();}		    	
	          return union;} 

 @Override
		public RmitMultiset intersect(RmitMultiset other) {
          RmitMultiset intersect=new OrderedLinkedListMultiset();
          Node n = ((OrderedLinkedListMultiset) other).find();
	      Node cNode=Linkedlist_head;
         while(n!=null){	    
	    		intersect.add(n.getElement());n=n.getNext();}
        while(cNode!=null){
	            intersect.add(cNode.getElement());cNode=cNode.getNext();}
	    Node result=((OrderedLinkedListMultiset) intersect).find();
	    while(result!=null){
            if(result.getInstance()==1){
	    			 intersect.removeOne(result.getElement());}
	        else{
	           result.setInstance(1);}	    		  	    				   	
	    	   result=result.getNext();}		
        return intersect;} 


	    @Override
		public RmitMultiset difference(RmitMultiset other) {
            RmitMultiset difference=new OrderedLinkedListMultiset();
            Node n = ((OrderedLinkedListMultiset) other).find();
	        Node cNode=Linkedlist_head;
	        while(n!=null){	    
	    		difference.add(n.getElement());
	            n=n.getNext();}
           while(cNode!=null){
	            difference.add(cNode.getElement());
	            cNode=cNode.getNext();}
	      Node result=((OrderedLinkedListMultiset) difference).find();
          while(result!=null){
		    if(result.getInstance()==2){
	    			 difference.removeOne(result.getElement());
	    			 difference.removeOne(result.getElement());}
	        result=result.getNext();}		
            return difference;}
	    
	    public Node find() {return Linkedlist_head;}
   
	    private class Node
	    {
	        protected int Ins;
	        protected String element;
            protected Node Linkedlist_Next;
            public Node(String elem) {
	            element = elem;Ins=1;
	            Linkedlist_Next = null;}
            public int getInstance() {return Ins;}
	        public String getElement() {return element;}
            public Node getNext() {return Linkedlist_Next; }
            public void instance(int Inst) {Ins = Ins +Inst; }
            public void setInstance(int inst) {Ins=inst;}
	        public void setNext(Node next) {Linkedlist_Next = next;}}}



