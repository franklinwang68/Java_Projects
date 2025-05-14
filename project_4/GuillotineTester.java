import org.junit.*;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

/** 
 * A tester for the game
 * @author <em> Franklin Wang </em>
 */ 
public class GuillotineTester{
    /**
     * Tests Card
     */ 
    @Test
    public void testCard(){
        Card card = new Card("Tragic Figure", "Commoner", -1);
        int[] array = new int[40];
        
        assertEquals("Test getPerson", "Tragic Figure", card.getPerson());
        assertEquals("Test getGroup", "Commoner", card.getGroup());
        assertEquals("Test getPoints", -1, card.getPoints());
        
        //test generateRandomUnusedNumber. This test may be problematic
        for(int i = 0; i < 40; i++){ //i cannot be greater than 40 as the main method restricts this
            array[i] = card.generateRandomUnusedNumber();
            if(array[i] > 39 || array[i] < 0)
                fail("random number less than 0 or greater than 40");
        }
        
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array.length; j++){
                if(array[j] == array[i] && i != j)
                    fail("Duplicate number found at index " + i + ": " + array[i] + " and " + j + ": " + array[j]);
            }
        }
        
        //test toString
        Card c1 = new Card("Tragic Figure", "Commoner", -1);
        Card c2 = new Card("Palace Guard", "Military", 1);
        Card c3 = new Card("Tax Collector", "Civic", 1);
        Card c4 = new Card("Heretic", "Church", 1);
        Card c5 = new Card("Lord", "Royal", 2);
        Card c6 = new Card("Lady", "Royal", 2);
        Card c7 = new Card("Count", "Royal", 2);
        Card c8 = new Card("Countess", "Royal", 2);
        Card c9 = new Card("Duke", "Royal", 4);
        
        assertTrue("test toString special points", c1.toString().equals("Tragic Figure Commoner *"));
        assertTrue("test toString special points", c2.toString().equals("Palace Guard Military *"));
        assertTrue("test toString special points", c3.toString().equals("Tax Collector Civic *"));
        assertTrue("test toString special points", c4.toString().equals("Heretic Church *"));
        assertTrue("test toString special points", c5.toString().equals("Lord Royal *"));
        assertTrue("test toString special points", c6.toString().equals("Lady Royal *"));
        assertTrue("test toString special points", c7.toString().equals("Count Royal *"));
        assertTrue("test toString special points", c8.toString().equals("Countess Royal *"));
        assertTrue("test toString regular", c9.toString().equals("Duke Royal 4"));
    }
    
    /**
     * Tests Guillotine
     */ 
    @Test
    public void testGuillotine(){
        //calculate points
        LinkedList<Card> cardList = new LinkedList<Card>();
        
        cardList.addToFront(new Card("Duke", "Royal", 4));
        assertEquals("test 0 special cards", 4, Guillotine.calculatePoints(cardList));
        
        //1, first
        cardList.removeFromFront();
        cardList.addToFront(new Card("Sheriff", "Civic", 1));
        cardList.addToFront(new Card("Tax Collector", "Civic", 1));
        assertEquals("test many special cards at last", 3, Guillotine.calculatePoints(cardList));
        
        
        //many, middle
        while(!cardList.isEmpty()){
            cardList.removeFromFront();
        }
        cardList.addToFront(new Card("Duke", "Royal", 4)); // worth 4 points
        cardList.addToFront(new Card("Lord", "Royal", 2)); // worth 4
        cardList.addToFront(new Card("Lady", "Royal", 2)); // 4
        cardList.addToFront(new Card("Count", "Royal", 2)); // 4
        cardList.addToFront(new Card("Countess", "Royal", 2)); // 4
        cardList.addToFront(new Card("Priest", "Church", 1)); // 1
        cardList.addToFront(new Card("Priest", "Church", 1)); // 1
        // 4 + 4 + 4 + 4 + 4 + 1 + 1 = 22
        assertEquals("test many special cards in middle", 22, Guillotine.calculatePoints(cardList));
        
        //many, last
        while(!cardList.isEmpty()){
            cardList.removeFromFront();
        }
        cardList.addToFront(new Card("Heretic", "Church", 1));
        cardList.addToFront(new Card("Priest", "Church", 1));
        cardList.addToFront(new Card("Priest", "Church", 1));
        cardList.addToFront(new Card("Priest", "Church", 1));
        assertEquals("test 1 special card at first", 7, Guillotine.calculatePoints(cardList));
    }
    
    /**
     * Tests Linkedist
     */ 
    @Test
    public void testLinkedList(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        
        //isEmpty true
        assertTrue("Test isEmpty", list.isEmpty());
        
        //set and get first node,
        assertTrue("Test get first node", list.getFirstNode() == null);
        list.setFirstNode(new LLNode<Integer>(1, null));
        assertTrue("Test get first node", list.getFirstNode().getElement().equals(1));
        
        //add to front
        list.addToFront(2);
        assertTrue("Test addToFront", list.getFirstNode().getElement().equals(2));
        assertTrue("Test addToFront", list.getFirstNode().getNext().getElement().equals(1));
        
        //isEmpty false
        assertFalse("Test isEmpty", list.isEmpty());
        
        //remove from front
        list.removeFromFront();
        assertTrue("Test removeFromFront 1", list.getFirstNode().getElement().equals(1));
        list.removeFromFront();
        assertTrue("Test removeFromFront 0", list.getFirstNode() == null);
        
        //length
        assertEquals("Test length 0", 0, list.length());
        list.addToFront(1);
        assertEquals("Test length 1", 1, list.length());
        list.addToFront(2);
        assertEquals("Test length many", 2, list.length());
        
        //move back n
        list.moveBack(2); //should do nothing
        assertTrue("Test moveBack n > length", list.getFirstNode().getElement().equals(2));
        assertTrue("Test moveBack", list.getFirstNode().getNext().getElement().equals(1));
        list.moveBack(0); //should do nothing
        assertTrue("Test moveBack n < 0", list.getFirstNode().getElement().equals(2));
        assertTrue("Test moveBack", list.getFirstNode().getNext().getElement().equals(1));
        list.moveBack(1);
        assertTrue("Test moveBack", list.getFirstNode().getElement().equals(1));
        assertTrue("Test moveBack", list.getFirstNode().getNext().getElement().equals(2));
        
        //move first to last
        list.addToFront(3); //the list is now 3, 1, 2
        
        list.moveFirstToLast();
        assertTrue("Test moveFirstToLast many", list.getFirstNode().getElement().equals(1));
        assertTrue("Test moveFirstToLast many", list.getFirstNode().getNext().getElement().equals(2));
        assertTrue("Test moveFirstToLast many", list.getFirstNode().getNext().getNext().getElement().equals(3));
        
        list.removeFromFront();
        list.removeFromFront();
        list.moveFirstToLast();
        assertTrue("Test moveFirstToLast 1", list.getFirstNode().getElement().equals(3));
        
        list.removeFromFront();
        list.moveFirstToLast();
        assertTrue("Test moveFirstToLast 0", list.getFirstNode() == null);
        
        //move last to first
        list.moveLastToFirst();
        assertTrue("Test moveFirstToLast 0", list.getFirstNode() == null);
        
        list.addToFront(3); //list is now 3
        list.moveLastToFirst();
        assertTrue("Test moveLastToFirst 1", list.getFirstNode().getElement().equals(3));
        
        list.addToFront(2);
        list.addToFront(1);
        list.moveLastToFirst();
        assertTrue("Test moveLastToFirst many", list.getFirstNode().getElement().equals(3));
        assertTrue("Test moveLastToFirst many", list.getFirstNode().getNext().getElement().equals(1));
        assertTrue("Test moveLastToFirst many", list.getFirstNode().getNext().getNext().getElement().equals(2));
        
        //reverse list
        list.reverseList();
        assertTrue("Test reverseList many", list.getFirstNode().getElement().equals(2));
        assertTrue("Test reverseList many", list.getFirstNode().getNext().getElement().equals(1));
        assertTrue("Test reverseList many", list.getFirstNode().getNext().getNext().getElement().equals(3));
        
        list.removeFromFront();
        list.removeFromFront();
        list.reverseList();
        assertTrue("Test reverseList 1", list.getFirstNode().getElement().equals(3));
        
        list.removeFromFront();
        list.reverseList();
        assertTrue("Test reverseList 0", list.getFirstNode() == null);
        
        //reverse first K
        list.reverseFirstK(0);
        assertTrue("Test reverseFirstK 0", list.getFirstNode() == null);
        
        list.reverseFirstK(2);
        assertTrue("Test reverseFirstK empty list", list.getFirstNode() == null);
        
        list.addToFront(3); //list is now 3
        list.reverseFirstK(1);
        assertTrue("Test reverseFirstK 1", list.getFirstNode().getElement().equals(3));
        
        list.reverseFirstK(1);
        assertTrue("Test reverseFirstK one element list", list.getFirstNode().getElement().equals(3));
        
        list.addToFront(2);
        list.addToFront(1);
        list.reverseFirstK(2);
        assertTrue("Test reverseFirstK many", list.getFirstNode().getElement().equals(2));
        assertTrue("Test reverseFirstK many", list.getFirstNode().getNext().getElement().equals(1));
        assertTrue("Test reverseFirstK many", list.getFirstNode().getNext().getNext().getElement().equals(3));
        
        list.reverseFirstK(3);
        assertTrue("Test reverseFirstK many", list.getFirstNode().getElement().equals(3));
        assertTrue("Test reverseFirstK many", list.getFirstNode().getNext().getElement().equals(1));
        assertTrue("Test reverseFirstK many", list.getFirstNode().getNext().getNext().getElement().equals(2));
        
        list.reverseFirstK(4); //do nothing
        assertTrue("Test reverseFirstK many", list.getFirstNode().getElement().equals(3));
        assertTrue("Test reverseFirstK many", list.getFirstNode().getNext().getElement().equals(1));
        assertTrue("Test reverseFirstK many", list.getFirstNode().getNext().getNext().getElement().equals(2));
        
        //toString
        assertTrue("Test toString many", list.toString().equals("3\n1\n2\n"));
        list.removeFromFront();
        assertTrue("Test toString many", list.toString().equals("1\n2\n"));
        list.removeFromFront();
        assertTrue("Test toString 1", list.toString().equals("2\n"));
        list.removeFromFront();
        assertTrue("Test toString 0", list.toString().equals(""));
    }
    
    /**
     * Tests LinkedList Iterator
     */ 
    @Test
    public void testLinkedListIterator(){
        LinkedList<Integer> list = new LinkedList<Integer>();
        LinkedListIterator it = list.iterator();
        
        //has next and next
        assertFalse("test hasNext 0", it.hasNext());
        try{
            it.next();
        }
        catch(NoSuchElementException e){
            //good
        }
        
        list.addToFront(1);
        it = list.iterator();
        assertTrue("test hasNext 1", it.hasNext());
        try{
            assertTrue("test next 1", it.next().equals(1));
        }
        catch(NoSuchElementException e){
            fail("Exception thrown when element exists");
        }
        
        list.addToFront(2);
        it = list.iterator();
        assertTrue("test hasNext many", it.hasNext());
        try{
            assertTrue("test next many", it.next().equals(2));
            assertTrue("test next many", it.next().equals(1));
        }
        catch(NoSuchElementException e){
            fail("Exception thrown when element exists");
        }
    }
    
    /**
     * Tests LLNode
     */ 
    @Test
    public void testLLNode(){
        LLNode<Integer> node = new LLNode<Integer>(new Integer(1), null);
        LLNode<Integer> node2 = new LLNode<Integer>(new Integer(3), null);
        
        //set and get element
        assertTrue("Test get element", node.getElement().equals(1));
        node.setElement(new Integer(2));
        assertTrue("Test get element", node.getElement().equals(2));
        
        //set and get next
        assertTrue("Test get next", node.getNext() == null);
        node.setNext(node2);
        assertTrue("Test get next", node.getNext().equals(node2));
    }
}