package implementation;

import java.util.ArrayList;
import java.util.List;

 public class BstMultiset extends RmitMultiset{
	private Node root;
    protected String pRange;
    protected String print;
    public BstMultiset()
    {root = null;}
    public void add(String element) {root = insert(root, element);}
    private Node insert(Node n, String element){
       if (n == null) {n = new Node(element);}
       else{
         if(element.compareTo(n.getElement())==0){
        		 n.setInstance(n.getInstance()+1);
        		 return n;}
        else{
             if (element.compareTo(n.getElement())<=0) {
                 n.left = insert(n.left, element);}
             else {
                 n.right = insert(n.right, element);}}}
         return n;}
    
    @Override
	public int search(String element) {
       return search(root, element);} 
    public int search(Node n, String elem) {
        if(n.getElement().compareTo(elem)==0){
        return n.getInstance();}
        else if (elem.compareTo(n.getElement())>0){
    	return	search(n.getRightchild_element(), elem);}
        return search(n.getLeftchild_element(), elem);}
   
    @Override
	public List<String> searchByInstance(int instanceCount) {
        List<String> search=new ArrayList<String>();
        searchByInstance(root,instanceCount, search);
        return search;} 
    private void searchByInstance(Node n,int instance_count, List<String> s){
        if (n != null){
        	searchByInstance(n.getLeftchild_element(), instance_count,s);
            if(n.getInstance()==instance_count) {
            s.add(n.getElement());  }
            searchByInstance(n.getRightchild_element(),instance_count,s);}}

    @Override
	public boolean contains(String element) {
        return check(root, element);}
    private boolean check(Node r, String element){
        boolean found = false;
        while ((r != null) && !found){
            String elem = r.getElement();
            if (element.compareTo(elem)<0)
                r = r.getLeftchild_element();
            else if (element.compareTo(elem)>0)
                r = r.getRightchild_element();
            else{found = true;}
            found = check(r, element);}
         return found;}  

@Override
	public void removeOne(String item) {
        if (root!=null){
    	   root = delete(root, item);}}
    
private Node delete(Node root, String item){
        Node c1, c2, n;
        if (root.getElement().compareTo(item)==0){
        if(root.getInstance()>1){
        		root.setInstance(root.getInstance()-1);
        		return root;}
        else{
            Node left, right;
            left = root.getLeftchild_element();
            right = root.getRightchild_element();
            if (left == null && right == null) {
                return null;}
            else if (left == null){
                c1 = right;return c1;}
            else if (right == null){
                c1 = left;return c1;}
            else{
                c2 = right;c1 = right;
                while (c1.getLeftchild_element() != null){
                    c1 = c1.getLeftchild_element();}
                c1.setLeft_node(left);return c2;}}}
        if (item.compareTo(root.getElement())<0){
            n = delete(root.getLeftchild_element(), item);
            root.setLeft_node(n);}
        else{
            n = delete(root.getRightchild_element(), item);
            root.setRight_node(n);}
        return root;}
    
@Override
	public String print() {
	      print="";
          return print(root);}     
private String print(Node r){
      if (r != null){
            r.getLeftchild_element();
            print=print+r.getElement() +" :"+r.getInstance()+"\n";
            r.getRightchild_element();}
        return print;}

@Override
	public String printRange(String lower, String upper) {
           pRange="";
           return range(root, lower, upper);}
    private String range(Node r,String lower, String upper){
             if (r != null){
            range(r.getLeftchild_element(),lower,upper);
            if(r.getElement().compareToIgnoreCase(lower)>=0 & r.getElement().compareToIgnoreCase(upper)<=0){
            pRange=pRange+r.getElement() +" :"+r.getInstance()+"\n";}
            range(r.getRightchild_element(),lower, upper);}
    return pRange;}

    @Override
	public RmitMultiset union(RmitMultiset other) {
         Node SM_Root =((BstMultiset) other).getRoot();
    	 RmitMultiset union= new BstMultiset();
    	 union(root, union);
         union(SM_Root,union);
     Node N= ((BstMultiset) union).getRoot();
     union(N,union);return union;}
    public void union(Node N, RmitMultiset M) {
    	if(N!=null){
    	union(N.getLeftchild_element(), M); 
    	M.add(N.getElement());
    	union(N.getRightchild_element(),M);}}
    
@Override
	public RmitMultiset intersect(RmitMultiset other) {
       Node SM_Root =((BstMultiset) other).getRoot();
	   RmitMultiset intersect= new BstMultiset();
       intersect(root, intersect);
       intersect(SM_Root,intersect);
       Node N= ((BstMultiset) intersect).getRoot();
       intersect(N,intersect);return intersect;}
   public void intersect(Node N, RmitMultiset M) {
		if(N!=null){
          intersect(N.getLeftchild_element(),M);
    	  if(N.getInstance()==1){
    	     M.removeOne(N.getElement());}
    	     intersect(N.getRightchild_element(),M);}}

  @Override
	public RmitMultiset difference(RmitMultiset other) {
       Node SM_Root =((BstMultiset) other).getRoot();
       RmitMultiset difference= new BstMultiset();
       difference(root, difference);
       difference(SM_Root,difference);
    Node N= ((BstMultiset) difference).getRoot();
      difference(N,difference);return difference;}
   public void difference(Node N, RmitMultiset M) {
    	if(N!=null){
           difference(N.getLeftchild_element(),M);
    	if(N.getInstance()==2){
    	 M.removeOne(N.getElement());
    	 M.removeOne(N.getElement());}
    	difference(N.getRightchild_element(),M);}}

public Node getRoot() {return root;}

   private class Node
   {
	   Node left, right;String item;int inst;
	   public Node(String n){left = null;right = null;item = n;inst=1;}
	   public void setLeft_node(Node n){left = n;}
	   public void setRight_node(Node n){right = n;}
	   public Node getLeftchild_element(){return left;}
	   public Node getRightchild_element(){return right;}
	   public void setInstance(int d){inst = d;}
       public String getElement(){return item;}
	   public int getInstance() {return inst;}}}



