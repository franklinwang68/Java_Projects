import java.util.NoSuchElementException;

/**
 * A class to represent a linked list.
 * @author <em> CSDS 132 </em>
 * @author <em> Franklin Wang </em>
 */
public class LinkedList<T> implements Iterable {
    /** the first node of the list */
    private LLNode<T> firstNode;
    
    /**
     * Creates an initially empty linked list
     */
    public LinkedList() {
        firstNode = null;
    }
    
    /**
     * Returns the first node.
     */
    protected LLNode<T> getFirstNode() {
        return firstNode;
    }
    
    /**
     * Changes the front node.
     * @param node the node that will be the first node of the new linked list
     */
    protected void setFirstNode(LLNode<T> node) {
        this.firstNode = node;
    }
    
    /**
     * Return whether the list is empty
     * @return true if the list is empty
     */
    public boolean isEmpty() {
        return (getFirstNode() == null);
    }
    
    /**
     * Add an element to the front of the linked list
     */
    public void addToFront(T element) {
        setFirstNode(new LLNode<T>(element, getFirstNode()));
    }
    
    /**
     * Removes and returns the element at the front of the linked list
     * @return the element removed from the front of the linked list
     * @throws NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty())
            throw new NoSuchElementException();
        else {
            T save = getFirstNode().getElement();
            setFirstNode(getFirstNode().getNext());
            return save;
        }
    }
    
    /**
     * Returns the length of the linked list
     * @return the number of nodes in the list
     */
    public int length() {
        int lengthSoFar = 0;
        LLNode<T> nodeptr = getFirstNode();
        
        //increment the length by one as long as there is a node
        while (nodeptr != null) {
            lengthSoFar++;
            nodeptr = nodeptr.getNext();
        }
        return lengthSoFar;
    }
    
    /** 
     * Moves the first node of the list back n places.
     * @param n the number of nodes back the first node moves
     */ 
    public void moveBack(int n){
        if(n < length() && n > 0){
            LLNode<T> nodeptr = getFirstNode();
            LLNode<T> save = getFirstNode();
            
            //determines which node the first node should go after 
            for(int i = 0; i < n; i++){
                nodeptr = nodeptr.getNext();
            }
            
            //set the second node to be the first node
            setFirstNode(getFirstNode().getNext());
            
            //link the next node to the moved node
            save.setNext(nodeptr.getNext());
            
            //link the previous node to the first node
            nodeptr.setNext(save);
        }
    }
    
    /** 
     * Moves the first node to become the last node of the list
     */ 
    public void moveFirstToLast(){ 
        LLNode<T> nodeptr = getFirstNode();
        LLNode<T> save = getFirstNode();
        
        if(length() > 1){
            //finds the last node
            while(nodeptr.getNext() != null){
                nodeptr = nodeptr.getNext();
            }
            
            //set the second node to be the first node
            setFirstNode(save.getNext());
            
            //cuts off the link of the first node
            save.setNext(null);
            
            //set the last node to be the first node
            nodeptr.setNext(save);    
        }
    }
    
    /** 
     * Moves the last node to the front
     */ 
    public void moveLastToFirst(){
        LLNode<T> nodeptr = getFirstNode();
        LLNode<T> save;
        
        if(length() > 1){
            //finds the second to last node
            while(nodeptr.getNext().getNext() != null){
                nodeptr = nodeptr.getNext();
            }
            
            //saves the last node
            save = nodeptr.getNext();
            
            //cuts off the link of the second to last node
            nodeptr.setNext(null);
            
            //link the saved node to the first node
            save.setNext(getFirstNode());
            
            //set the saved node to be first node
            setFirstNode(save);
        }
    }
    
    /** 
     * Reverse the node list
     */ 
    public void reverseList(){
        LLNode<T> prev = null;
        LLNode<T> current = getFirstNode();
        LLNode<T> next;
        
        if(!isEmpty()){
            next = getFirstNode().getNext();
            
            //reverse the linked list
            while (current != null) {
                //stores the next node of the current
                next = current.getNext();
                
                //reverse the current node to point to the previous
                current.setNext(prev);
                
                //sets the previous to be the current node
                prev = current;
                
                //sets the current to be the next node
                current = next;
            }
            
            //sets the new first node to be the last node 
            setFirstNode(prev);
        }
    }
    
    /** 
     * Reverses the first k nodes of the linked list
     */ 
    public void reverseFirstK(int k){
        if(length() >= k && k > 1){
            LLNode<T> prev = getFirstNode(); 
            LLNode<T> current = getFirstNode();
            LLNode<T> next = getFirstNode().getNext();
            
            //tracks how many nodes was reversed
            int count = 0; 
            
            //finds the kth node which would become the first node later
            for(int i = 0; i < k; i++){
                prev = prev.getNext();
            }
            
            //reverse k number of nodes
            while (count < k) {
                //stores the next node of the current
                next = current.getNext();
                
                //reverse the current node so it points to the prev
                current.setNext(prev);
                
                //set the previous node to be the current
                prev = current;
                
                //set the current node to be the next
                current = next;
                
                //increment how many nodes have been reversed
                count++;
            }
            
            //sets the kth node to be the first node
            setFirstNode(prev);
        }
    }
    
    /**
     * Returns a String representation of the list
     * @return a String representing the list
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        LLNode<T> nodeptr = getFirstNode();
        
        //appends each element of the list on a new line in the builder
        while(nodeptr != null){
            builder.append(nodeptr.getElement());
            builder.append('\n');
            nodeptr = nodeptr.getNext();
        }
        return builder.toString();
    }
    
    /**
     * Determines whether an element is stored in the list
     * @param element the element to search for in the list
     * @return true if and only if the parameter element is in the list
     */
    public boolean contains(T element) {
        LLNode<T> nodeptr = getFirstNode();
        
        //runs through the list and returns true when the element in the list is found
        while(nodeptr != null){
            if(nodeptr.getElement().equals(element))
                return true;
            nodeptr = nodeptr.getNext();
        }
        return false;
    }
    
    /**
     * Returns a new LinkedListIterator
     * @return a new LinkedListIterator
     */
    @Override
    public LinkedListIterator<T> iterator(){
        return new LinkedListIterator<T>(getFirstNode());
    }
}
