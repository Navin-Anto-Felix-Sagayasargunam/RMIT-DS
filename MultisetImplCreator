import implementation.*;

public class MultisetImplCreator
{
    
    protected String mImplementationType;

    public MultisetImplCreator(String implementationType)
        throws IllegalArgumentException
    {
        if (implementationType.compareTo("array") != 0 &&
            implementationType.compareTo("orderedlinkedlist") != 0 &&
            implementationType.compareTo("bst") != 0 &&
            implementationType.compareTo("duallinkedlist") != 0
            ) {
                throw new IllegalArgumentException("Unknown implementation type.");
        }
        mImplementationType = implementationType;
    }
    public RmitMultiset createImplementation() {
        RmitMultiset multiset = null;
        switch (mImplementationType) {
            case "array":
                multiset = new ArrayMultiset();
                break;
 			case "orderedlinkedlist":
     		    multiset = new OrderedLinkedListMultiset();
     			break;
     		case "bst":
     		    multiset = new BstMultiset();
     		    break;
            case "duallinkedlist":
              	multiset = new DualLinkedListMultiset();
               	break;
         }
         return multiset;}}
