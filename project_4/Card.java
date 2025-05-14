/** 
 * A person card used in the game
 * @author <em> Franklin Wang </em>
 */ 
public class Card{
    /** The name or occupation */
    private String person;
    
    /** the group the person belongs to */
    private String group;
    
    /** the person's point worth */
    private int points;
    
    /** tracks of which cards are unavailable */
    private static int[] usedCards= new int[40];
    
    /** tracks the number of cards */
    private static int count = 0;
    
    /**
     * Creates a random card
     */ 
    public Card() { //is there a better way to create these?
        //a random number that represents a card
        int cardNum = generateRandomUnusedNumber();
        
        //create a card with the person, group, and point value as directed by the random number
        switch(cardNum){
            case 0: {
                person = "Student";
                group = "Commoner";
                points = -1;
                break;
            }
            case 1: {
                person = "King Louis XVI";
                group = "Royal";
                points = 5;
                break;
            }
            case 2: {
                person = "Marie Antoinette";
                group = "Royal";
                points = 5;
                break;
            }
            case 3: {
                person = "Regent";
                group = "Royal";
                points = 4;
                break;
            }
            case 4: {
                person = "Duke";
                group = "Royal";
                points = 3;
                break;
            }
            case 5: {
                person = "Baron";
                group = "Royal";
                points = 3;
                break;
            }
            case 6: {
                person = "Count";
                group = "Royal";
                points = 2;
                break;
            }
            case 7: {
                person = "Countess";
                group = "Royal";
                points = 2;
                break;
            }
            case 8: {
                person = "Lord";
                group = "Royal";
                points = 2;
                break;
            }
            case 9: {
                person = "Lady";
                group = "Royal";
                points = 2;
                break;
            }
            case 10: {
                person = "Cardinal";
                group = "Church";
                points = 5;
                break;
            }case 11: {
                person = "Archbishop";
                group = "Church";
                points = 4;
                break;
            }
            case 12: {
                person = "Nun";
                group = "Church";
                points = 3;
                break;
            }
            case 13: {
                person = "Bishop";
                group = "Church";
                points = 2;
                break;
            }
            case 14: {
                person = "Priest";
                group = "Church";
                points = 1;
                break;
            }
            case 15: {
                person = "Priest";
                group = "Church";
                points = 1;
                break;
            }
            case 16: {
                person = "Heretic";
                group = "Church";
                points = 1;
                break;
            }
            case 17: {
                person = "Governor";
                group = "Civic";
                points = 4;
                break;
            }
            case 18: {
                person = "Mayor";
                group = "Civic";
                points = 3;
                break;
            }
            case 19: {
                person = "Councilman";
                group = "Civic";
                points = 3;
                break;
            }
            case 20: {
                person = "Judge";
                group = "Civic";
                points = 2;
                break;
            }case 21: {
                person = "Judge";
                group = "Civic";
                points = 2;
                break;
            }
            case 22: {
                person = "Tax Collector";
                group = "Civic";
                points = 1;
                break;
            }
            case 23: {
                person = "Sheriff";
                group = "Civic";
                points = 1;
                break;
            }
            case 24: {
                person = "Sheriff";
                group = "Civic";
                points = 1;
                break;
            }
            case 25: {
                person = "Palace Guard";
                group = "Military";
                points = 1;
                break;
            }case 26: {
                person = "Palace Guard";
                group = "Military";
                points = 1;
                break;
            }
            case 27: {
                person = "Palace Guard";
                group = "Military";
                points = 1;
                break;
            }
            case 28: {
                person = "Palace Guard";
                group = "Military";
                points = 1;
                break;
            }
            case 29: {
                person = "Palace Guard";
                group = "Military";
                points = 1;
                break;
            }
            case 30: {
                person = "General";
                group = "Military";
                points = 4;
                break;
            }case 31: {
                person = "Colonel";
                group = "Military";
                points = 3;
                break;
            }
            case 32: {
                person = "Captain";
                group = "Military";
                points = 2;
                break;
            }
            case 33: {
                person = "Lieutenant";
                group = "Military";
                points = 1;
                break;
            }
            case 34: {
                person = "Lieutenant";
                group = "Military";
                points = 1;
                break;
            }
            case 35: {
                person = "Tragic Figure";
                group = "Commoner";
                points = -1;
                break;
            }case 36: {
                person = "Heroic Figure";
                group = "Commoner";
                points = -3;
                break;
            }
            case 37: {
                person = "Student";
                group = "Commoner";
                points = -1;
                break;
            }
            case 38: {
                person = "Student";
                group = "Commoner";
                points = -1;
                break;
            }
            case 39: {
                person = "Student";
                group = "Commoner";
                points = -1;
                break;
            }
        }//end switch
    }
    
    /**
     * Creates a arbituary card for testing
     * @param person the name
     * @param group the group
     * @param points the point worth
     */ 
    public Card(String person, String group, int points) {
        this.person = person;
        this.group = group;
        this.points = points;
    }
    
    /**
     * Returns a random unused number between 0 and 39 both inclusive
     * @return the random unused number
     */ 
    public int generateRandomUnusedNumber() { //method should be private, made public for testing and reflection didn't work
        int rand = (int)(Math.random() * 40);      //a random number that represents a card
        int i = 0;                                 //loop iterator
        
        //makes a random number that is not used
        while(i < count){
            if(rand == usedCards[i]){
                i = 0;
                rand = (int)(Math.random() * 40);
            }
            else
                i++;
        }
        
        //add the number to used 'pile' and increase the number of cards
        usedCards[count] = rand; 
        count++;
        
        return rand;
    }
    
    /**
     * Get the person.
     * @return the person part of the card
     */ 
    public String getPerson(){
        return person;
    }
    
    /**
     * Get the group
     * @return the group part of the card
     */ 
    public String getGroup(){
        return group;
    }
    
    /**
     * Get the point value of a card.
     * @return the point value of the card
     */ 
    public int getPoints(){
        return points;
    }
    
    /**
     * Returns the string representation of a card
     * @return the string representation of a card
     */
    @Override
    public String toString(){
        if(person.equals("Count") || 
           person.equals("Countess") ||
           person.equals("Lord") ||
           person.equals("Lady") ||
           person.equals("Heretic") ||
           person.equals("Tax Collector") ||
           person.equals("Palace Guard") ||
           person.equals("Tragic Figure") ) {
            return person + " " + group + " *";
        }
        else
            return person + " " + group + " " + points;
    }
}