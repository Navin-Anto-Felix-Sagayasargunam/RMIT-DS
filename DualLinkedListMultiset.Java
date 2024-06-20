package implementation;
import java.util.ArrayList;
import java.util.List;
public class DualLinkedListMultiset extends RmitMultiset
{
    protected Node dllHead;
	protected int dllLength; 
    
	public DualLinkedListMultiset() {
    	 dllHead = null;
         dllLength = 0;}
    
	public void add(String item) {
          Node nNode = new Node(item);
          if (dllHead == null){
	            dllHead = nNode;
	            dllLength++;}
	      else {
	        	int test=0;	 		       
	        	Node currentNode = dllHead;     	
	            while(currentNode!=null){
	            if(item.compareTo(currentNode.getElement())==0){
	        	   currentNode.setInstance(1);	
	               test=1;}	        	
	        	currentNode= currentNode.getNext();}
	        	if(test==0){Node cNode=dllHead;
	        	if(item.compareTo(cNode.getElement())<=0){
	        	nNode.setNext(dllHead);
	        	dllHead=nNode;
	        	dllLength++;}
	        else if(item.compareTo(cNode.getElement())>0){
	        		int elem=0;
	                while( cNode.getNext()!=null){
	        	    if(item.compareTo(cNode.getElement())>0 && item.compareToIgnoreCase(cNode.getNext().getElement())<=0){
	        		nNode.setNext(cNode.getNext());
	        		cNode.setNext(nNode);
	        		dllLength++;
	        		elem=1;}
	        	    cNode=cNode.getNext();}
	        if(elem==0){
	        	cNode.setNext(nNode);
	        	dllLength++;}}}}} 
 @Override
	public int search(String item) {
         Node currentNode=dllHead;
         while(currentNode!=null){
             if(item.compareTo(currentNode.getElement())==0){
                 return currentNode.getInstance();}
             currentNode=currentNode.getNext();}
  	      return searchFailed;}

@Override
	public List<String> searchByInstance(int instanceCount) {
         Node cNode=dllHead;
         List<String> search= new ArrayList<String>();
         while(cNode!=null){
            if(instanceCount==cNode.getInstance()){
                 search.add(cNode.getElement());}
            cNode=cNode.getNext();}
 	     return search;}   

@Override
	public boolean contains(String item) {
        Node currentNode =dllHead;
        while(currentNode!=null){      
    		if(item.compareTo(currentNode.getElement())==0){          		      
    			return true;}     
    		currentNode=currentNode.getNext();}
        return false;}

@Override
	public void removeOne(String item) {
        Node cNode=dllHead;
    	if(cNode.getElement().compareTo(item)==0){
    		cNode.setInstance(-1);
    		if(cNode.getInstance()==0){
    			cNode=cNode.getNext();	
    			dllHead=cNode;
    			dllLength--;}}
    	else{
    		for(int i=0; i<dllLength; i++){
    		if(cNode.getNext().getElement().compareTo(item)==0){
    			cNode.getNext().setInstance(-1);
    		if(cNode.getNext().getInstance()==0){
    			cNode.setNext(cNode.getNext().getNext());
    				dllLength--;}}
    		 cNode= cNode.getNext();
    		 }}}

@Override
	public String print() {
        String print ="";
        if(dllHead!=null){   
    	Node cNode = dllHead;
        while (cNode != null){     
    	  print = print+cNode.getElement() + " :"+cNode.getInstance()+"\n";      
    	  cNode = cNode.getNext();}}
        return print;} 

@Override
	public String printRange(String lower, String upper) {
        Node cNode=dllHead;
        String p ="";
        while(cNode!=null){
            if(cNode.getElement().compareTo(lower)>=0 & cNode.getElement().compareTo(upper)<=0){     	
                   p=p+cNode.getElement()+" :"+cNode.getInstance()+"\n";}
            cNode=cNode.getNext();}
         return p;} 

    @Override
	public RmitMultiset union(RmitMultiset other) {
         RmitMultiset union=new DualLinkedListMultiset();
   	     Node nNode = ((DualLinkedListMultiset) other).find();
         Node cNode=dllHead;
         while(nNode!=null){	    
    		union.add(nNode.getElement());
            nNode=nNode.getNext();}
         while(cNode!=null) {
            union.add(cNode.getElement());
            cNode=cNode.getNext();}
    	Node result=((DualLinkedListMultiset) union).find();
    	  while(result!=null){
	          result.changeInstance(1); 	    				   	
    		  result=result.getNext();}		    	
    	 return union;}

@Override
	public RmitMultiset intersect(RmitMultiset other) {
        RmitMultiset intersect=new DualLinkedListMultiset();
        Node n = ((DualLinkedListMultiset) other).find();
        Node cNode=dllHead;
    	while(n!=null){	    
    		intersect.add(n.getElement());
            n=n.getNext();}
        while(cNode!=null){
             intersect.add(cNode.getElement());
             cNode=cNode.getNext();}
       Node result=((DualLinkedListMultiset) intersect).find();
       while(result!=null){
	       if(result.getInstance()==1){
    			 intersect.removeOne(result.getElement());}
    	   else{
    			  result.changeInstance(1);}	    		  	    				   	
       result=result.getNext();}		        
       return intersect;} 

 @Override
	public RmitMultiset difference(RmitMultiset other) {
           RmitMultiset difference=new DualLinkedListMultiset();
           Node n = ((DualLinkedListMultiset) other).find();
           Node cNode=dllHead;
    while(n!=null){	    
    		difference.add(n.getElement());
            n=n.getNext();}
    while(cNode!=null){
        difference.add(cNode.getElement());
        cNode=cNode.getNext();}
    Node result=((DualLinkedListMultiset) difference).find();
    while(result!=null){
	    if(result.getInstance()==2){
          difference.removeOne(result.getElement());
    	  difference.removeOne(result.getElement());}
    	  result=result.getNext();}		
    return difference;}
    public Node find() {return dllHead;}
    private class Node{
        protected int Ins;
        protected String Element;
        protected Node dllNext;
        public Node(String element) {
            Element = element;Ins=1;dllNext = null;}
        public int getInstance() {return Ins;}
        public String getElement() {return Element;}
        public Node getNext() {return dllNext;}
        public void setInstance(int Inst) {Ins = Ins +Inst;}
        public void changeInstance(int Inst) {Ins=Inst;}
        public void setNext(Node next) {dllNext = next; }}}



