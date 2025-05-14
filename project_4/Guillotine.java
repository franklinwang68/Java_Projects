import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

/** 
 * The Guillotine game
 * @author <em> Franklin Wang </em>
 */
public class Guillotine extends Application {
    /** The cards available to draw */
    private static LinkedList<Card> cardList = new LinkedList<Card>(); //is it a bad to make this static? Should I have used Application.getParameters instead?
    
    /** The cards collected by player 1 */
    private LinkedList<Card> p1Cards = new LinkedList<Card>();
    
    /** The cards collected by player 2 */
    private LinkedList<Card> p2Cards = new LinkedList<Card>();
    
    /** Score of player 1 */
    private int p1Score = 0;
    
    /** Score of player 2 */
    private int p2Score = 0;
    
    /** The display for the list of cards */
    private Text cardListDisplay; //Did each individual card need its own gadget?
    
    /** The display for player 1's cards */
    private TextArea p1CardDisplay;
    
    /** The display for player 2's cards */
    private TextArea p2CardDisplay;
    
    /** The display for the score, who's turn it is, and the winner at the end */
    private TextArea playerScores;
    
    /** Determines who's turn it is */
    private boolean p1Turn = true;
    
    /** 
     * Overrides the start method of Application to create the Guillotine Display.
     * @param primaryStage the JavaFX main window
     */
    @Override
    public void start(Stage primaryStage) {
        //Components
        //buttons for player 1
        Button p1moveLeadCardBack4 = new Button("Move Lead Card Back 4 Places");
        Button p1moveLeadCardBack3 = new Button("Move Lead Card Back 3 Places");
        Button p1moveLeadCardBack2 = new Button("Move Lead Card Back 2 Places");
        Button p1moveLeadCardBack1 = new Button("Move Lead Card Back 1 Place");
        Button p1moveLeadCardToEnd = new Button("Move Lead Card To End");
        Button p1moveLastCardToFront = new Button("Move Last Card To Front");
        Button p1reverseCardLine = new Button("Reverse Card Line");
        Button p1reverseFirst5Cards = new Button("Reverse First 5 Cards");
        Button p1skipTurn = new Button("Skip Turn");
        Button p1drawCard = new Button("Draw Card");
        
        //butons for player 2
        Button p2moveLeadCardBack4 = new Button("Move Lead Card Back 4 Places");
        Button p2moveLeadCardBack3 = new Button("Move Lead Card Back 3 Places");
        Button p2moveLeadCardBack2 = new Button("Move Lead Card Back 2 Places");
        Button p2moveLeadCardBack1 = new Button("Move Lead Card Back 1 Place");
        Button p2moveLeadCardToEnd = new Button("Move Lead Card To End");
        Button p2moveLastCardToFront = new Button("Move Last Card To Front");
        Button p2reverseCardLine = new Button("Reverse Card Line");
        Button p2reverseFirst5Cards = new Button("Reverse First 5 Cards");
        Button p2skipTurn = new Button("Skip Turn");
        Button p2drawCard = new Button("Draw Card");
        
        //text for cardList
        cardListDisplay = new Text(cardList.toString());
        cardListDisplay.setWrappingWidth(200);
        cardListDisplay.setLineSpacing(2);
        cardListDisplay.setTextAlignment(TextAlignment.CENTER);
        
        //text area for Player 1 collected card
        p1CardDisplay = new TextArea(p1Cards.toString());
        p1CardDisplay.setPrefWidth(200);
        
        //text area for Player 2 collected card
        p2CardDisplay = new TextArea(p2Cards.toString());
        p2CardDisplay.setPrefWidth(200);
        
        //text area for player scores
        playerScores = new TextArea("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 1's Turn");
        playerScores.setPrefHeight(80);
        
        //Registering Button Actions
        //player 1 actions
        //move back 4
        p1moveLeadCardBack4.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    cardList.moveBack(4);
                    cardListDisplay.setText(cardList.toString());
                    p1moveLeadCardBack4.setDisable(true);
                }
            }
        });
        
        //move back 3
        p1moveLeadCardBack3.setOnAction(e -> {
            if(p1Turn){
                cardList.moveBack(3);
                cardListDisplay.setText(cardList.toString());
                p1moveLeadCardBack3.setDisable(true);
            }
        });
        
        //move back 2
        p1moveLeadCardBack2.setOnAction(e -> {
            if(p1Turn){
                cardList.moveBack(2);
                cardListDisplay.setText(cardList.toString());
                p1moveLeadCardBack2.setDisable(true);
            }
        });
        
        //move back 1
        p1moveLeadCardBack1.setOnAction(e -> {
            if(p1Turn){
                cardList.moveBack(1);
                cardListDisplay.setText(cardList.toString());
                p1moveLeadCardBack1.setDisable(true);
            }
        });
        
        //move first to last
        p1moveLeadCardToEnd.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    cardList.moveFirstToLast();
                    cardListDisplay.setText(cardList.toString());
                    p1moveLeadCardToEnd.setDisable(true);
                }
            }
        });
        
        //move last to first    
        p1moveLastCardToFront.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    cardList.moveLastToFirst();
                    cardListDisplay.setText(cardList.toString());
                    p1moveLastCardToFront.setDisable(true);
                }
            }
        });
        
        //reverse all
        p1reverseCardLine.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    cardList.reverseList();
                    cardListDisplay.setText(cardList.toString());
                    p1reverseCardLine.setDisable(true);
                }
            }
        });
        
        //reverse first 5
        p1reverseFirst5Cards.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    cardList.reverseFirstK(5);
                    cardListDisplay.setText(cardList.toString());
                    p1reverseFirst5Cards.setDisable(true);
                }
            }
        });
        
        //skip
        p1skipTurn.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    p1Turn = false;
                    playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 2's Turn");
                    p1skipTurn.setDisable(true);
                }
            }
        });
        
        //draw
        p1drawCard.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                if(p1Turn){
                    p1Cards.addToFront(cardList.removeFromFront());
                    p1CardDisplay.setText(p1Cards.toString());
                    cardListDisplay.setText(cardList.toString());
                    p1Turn = false;
                    p1Score = calculatePoints(p1Cards);
                    p2Score = calculatePoints(p2Cards);
                    playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 2's Turn");
                    if(cardList.isEmpty()){
                        if(p1Score == p2Score)
                            playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nTie Game");
                        else if(p1Score > p2Score)
                            playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 1 Wins");
                        else
                            playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 2 Wins");
                        p2drawCard.setDisable(true);
                    }
                }
            }
        });
        
        //player 2 actions
        //move back 4
        p2moveLeadCardBack4.setOnAction(e -> {
            if(!p1Turn){
                cardList.moveBack(4);
                cardListDisplay.setText(cardList.toString());
                p2moveLeadCardBack4.setDisable(true);
            }
        });
        
        //move back 3
        p2moveLeadCardBack3.setOnAction(e -> {
            if(!p1Turn){
                cardList.moveBack(3);
                cardListDisplay.setText(cardList.toString());
                p2moveLeadCardBack3.setDisable(true);
            }
        });
        
        //move back 2
        p2moveLeadCardBack2.setOnAction(e -> {
            if(!p1Turn){
                cardList.moveBack(2);
                cardListDisplay.setText(cardList.toString());
                p2moveLeadCardBack2.setDisable(true);
            }
        });
        
        //move back 1
        p2moveLeadCardBack1.setOnAction(e -> {
            if(!p1Turn){
                cardList.moveBack(1);
                cardListDisplay.setText(cardList.toString());
                p2moveLeadCardBack1.setDisable(true);
            }
        });
        
        //move first to last
        p2moveLeadCardToEnd.setOnAction(e ->{
            if(!p1Turn){
                cardList.moveFirstToLast();
                cardListDisplay.setText(cardList.toString());
                p2moveLeadCardToEnd.setDisable(true);
            }
        });
        
        //move last to first    
        p2moveLastCardToFront.setOnAction(e ->{
            if(!p1Turn){
                cardList.moveLastToFirst();
                cardListDisplay.setText(cardList.toString());
                p2moveLastCardToFront.setDisable(true);
            }
        });
        
        //reverse all
        p2reverseCardLine.setOnAction(e -> {
            if(!p1Turn){
                cardList.reverseList();
                cardListDisplay.setText(cardList.toString());
                p2reverseCardLine.setDisable(true);
            }
        });
        
        //reverse first 5
        p2reverseFirst5Cards.setOnAction(e -> {
            if(!p1Turn){
                cardList.reverseFirstK(5);
                cardListDisplay.setText(cardList.toString());
                p2reverseFirst5Cards.setDisable(true);
            }
        });
        
        //skip
        p2skipTurn.setOnAction(e -> {
            if(!p1Turn){
                p1Turn = true;
                playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 1's Turn");
                p2skipTurn.setDisable(true);
            }
        });
        
        //draw
        p2drawCard.setOnAction(e -> {
            if(!p1Turn){
                p2Cards.addToFront(cardList.removeFromFront());
                p2CardDisplay.setText(p2Cards.toString());
                cardListDisplay.setText(cardList.toString());
                p1Turn = true;
                p1Score = calculatePoints(p1Cards);
                p2Score = calculatePoints(p2Cards);
                playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 1's Turn");
                if(cardList.isEmpty()){
                    if(p1Score == p2Score)
                        playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nTie Game");
                    else if(p1Score > p2Score)
                        playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 1 Wins");
                    else
                        playerScores.setText("Score \nPlayer 1: " + p1Score + "\nPlayer 2: " + p2Score + "\nPlayer 2 Wins");
                    p1drawCard.setDisable(true);
                }
            }
        });
        
        //Layouts
        //General Game layout
        BorderPane generalLayout = new BorderPane(); 
        
        //player 1 button layout
        VBox p1buttonLayout = new VBox(new Text("Player 1"),
                                       p1moveLeadCardBack4, p1moveLeadCardBack3, p1moveLeadCardBack2, 
                                       p1moveLeadCardBack1, p1moveLeadCardToEnd, p1moveLastCardToFront, 
                                       p1reverseCardLine, p1reverseFirst5Cards,
                                       p1skipTurn, p1drawCard);
        
        //plyaer 2 button layout
        VBox p2buttonLayout = new VBox(new Text("Player 2"),
                                       p2moveLeadCardBack4, p2moveLeadCardBack3, p2moveLeadCardBack2, 
                                       p2moveLeadCardBack1, p2moveLeadCardToEnd, p2moveLastCardToFront, 
                                       p2reverseCardLine, p2reverseFirst5Cards,
                                       p2skipTurn, p2drawCard);
        
        //player 1 display
        HBox p1Display = new HBox(p1buttonLayout, p1CardDisplay);
        
        //player 2 display
        HBox p2Display = new HBox(p2CardDisplay, p2buttonLayout);
        
        generalLayout.setTop(playerScores);
        generalLayout.setCenter(cardListDisplay);
        generalLayout.setLeft(p1Display);
        generalLayout.setRight(p2Display);
        
        //Scene
        Scene scene = new Scene(generalLayout);          
        
        //Stage
        primaryStage.setTitle("Guillotine");
        primaryStage.setScene(scene);            
        primaryStage.show();                     
    }
    
    /** 
     * Calculates the number of points of a player
     * @param cards the linked list of cards the player has
     * @return the total number of points a player has
     */
    public static int calculatePoints(LinkedList<?> cards){ // Confused about the generics, why can't I just use Card as a generic in the parameter? 
                                                            // Method should also be private, but made public for testing. It is static for testing purposes.
        //the total number of points the cards add up to
        int totalPoints = 0;
        
        //determines whether the cards has a count
        boolean hasCount = false;
        
        //determines whether the cards has a countess
        boolean hasCountess = false;
        
        //determines whether the cards has a lord
        boolean hasLord = false;
        
        //determines whether the cards has a lady
        boolean hasLady = false;
        
        //determines whether the cards has a heretic
        boolean hasHeretic = false;
        
        //determines whether the cards has a tax collector
        boolean hasTaxCollector = false;
        
        //determines whether the cards has a tragic figure
        boolean hasTragicFigure = false;
        
        //the number of church members
        int numOfChurch = 0;
        
        //the number of civic memebers
        int numOfCivic = 0;
        
        //the number of commoners
        int numOfCommoner = 0;
        
        //the number of palace guards
        int numOfPalaceGuard = 0;
        
        //check for special cards and tracks the number of cards used in the point calculation
        for(Object card : cards){
            if(((Card)card).getPerson().equals("Palace Guard"))
                numOfPalaceGuard += 1;
            else if(((Card)card).getPerson().equals("Count"))
                hasCount = true;
            else if(((Card)card).getPerson().equals("Countess"))
                hasCountess = true;
            else if(((Card)card).getPerson().equals("Lord"))
                hasLord = true;
            else if(((Card)card).getPerson().equals("Lady"))
                hasLady = true;
            else if(((Card)card).getPerson().equals("Heretic"))
                hasHeretic = true;
            else if(((Card)card).getPerson().equals("Tax Collector"))
                hasTaxCollector = true;
            else if(((Card)card).getPerson().equals("Tragic Figure"))
                hasTragicFigure = true;
            if(((Card)card).getGroup().equals("Church"))
                numOfChurch += 1;
            else if(((Card)card).getGroup().equals("Civic"))
                numOfCivic += 1;
            else if(((Card)card).getGroup().equals("Commoner"))
                numOfCommoner += 1;
        }
        
        //adds the additional points from special cards
        if(hasCount && hasCountess)
            totalPoints += 4;
        if(hasLord && hasLady)
            totalPoints += 4;
        if(hasHeretic)
            totalPoints += numOfChurch - 1;
        if(hasTaxCollector)
            totalPoints += numOfCivic - 1;
        if(hasTragicFigure)
            totalPoints += -1 * (numOfCommoner - 1);
        totalPoints += numOfPalaceGuard * numOfPalaceGuard - numOfPalaceGuard;
        
        //add the innate point worth of each card
        for(Object card : cards){ 
            totalPoints += ((Card)card).getPoints();
        }
        
        return totalPoints;
    }
    
    /**
     * Launches the GUI
     * @param args the number of cards to create
     */ 
    private static void launchGame(String[] args){
        Application.launch(args);
    }
    
    /**
     * Creates the cards and launches the game on a new thread
     * @param args the number of cards to create
     */
    public static void main(String[] args) {
        //a thread to run the game
        Thread thread;
        
        //the number of cards to create. The default is 20
        int numOfCards = 20;
        
        try {
            Integer.parseInt(args[0]);
            if(Integer.parseInt(args[0]) <= 40 && Integer.parseInt(args[0]) >= 2)
                numOfCards = (int)Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e){
            //parameters cannot be parsed into int
        }
        catch(ArrayIndexOutOfBoundsException e){
            //parameteres are empty
        }
        finally{
            System.out.println("Creating a card list with " + numOfCards + " cards");
            //create the cardList
            for(int i = 0; i < numOfCards; i++){
                cardList.addToFront(new Card());
            }
        }
        
        thread = new Thread(){
            public void run(){
                Guillotine.launchGame(args);
            }
        };
        thread.start();
    }
}

