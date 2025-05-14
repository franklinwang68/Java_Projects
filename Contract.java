//Franklin Wang
//the Contract class represents a contract
public class Contract{
  
  //contract ID
  private String contractID;
  
  //minimum contract price
  private double minValue;
  
  //maximum contract price
  private double maxValue;
  
  //whether the contract open for bids
  private boolean acceptingBids = true;
  
  //bonus money for contractor
  private double bonus;
  
  //penalty for contractor
  private double penalty;
  
  //deadline for Contract
  private Date deadline;
  
  //highest value bid
  private Bid bestBid = null;
  
  //whether the contract is complete
  private boolean complete = false;
  
  /*the date the contract is completed. Field not mentioned in instructions, 
   * but really confused where else to store it
   */
  private Date completedDate;
  
  //constructor creates Contract with the inputs
  public Contract(String contractID, double minValue, double maxValue, 
                  double bonus, double penalty, Date deadline){
    this.contractID = contractID;
    this.minValue = minValue;
    this.maxValue = maxValue;
    this.bonus = bonus;
    this.penalty = penalty;
    this.deadline = deadline;  
  }
  
  //returns the contractID
  public String getID(){
    return this.contractID;
  }
  
  //returns the minimum contract price
  public double getMinValue(){
    return this.minValue;
  }
  
  //changes the minimum price
  public void setMinValue(double value){
    this.minValue = value;
  }
  
  //returns the maximum price of contract
  public double getMaxValue(){
    return this.maxValue;
  }
  
  //changes the maximum price of the contract
  public void setMaxValue(double value){
    this.maxValue = value;
  }
  
  //returns the bonus for the Contractor 
  public double getBonus(){
    return this.bonus;
  }
  
  /*changes the bonus depending on when the contract 
  is completed relative to the deadline*/
  public void setBonus(double bonus){
    this.bonus = bonus;
  }
  
  //returns the penatlty for the Contractor
  public double getPenalty(){
    return this.penalty;
  }
  
  /*changes the penalty depending on when the contract 
  is completed relative to the deadline*/
  public void setPenalty(double penalty){
    this.penalty = penalty;
  }
  
  //returns the deadline for the contract
  public Date getDeadline(){
    return this.deadline;
  }
  
  //changes the deadline for the contract
  public void setDeadline(Date deadline){
    this.deadline = deadline;
  }
  
  //tells whether or not the contract is open for bids
  public boolean isAcceptingBids(){
    return acceptingBids;
  }
  
  //returns the bid with the highest value
  public Bid getBestBid(){
    return this.bestBid;
  }
  
  //make a bid and store it if it is the bestBid 
  public boolean makeBid(Bid bid){
    if( acceptingBids && 
       bid.contract().equals(this) &&
       bid.value() <= this.getMaxValue() &&
       bid.value() >= this.getMinValue() ){
      if(this.bestBid == null){//sets bid as bestBid if there is no bid
        this.bestBid = bid;
        return true;
      }
      else
        if (bid.value() < this.bestBid.value() ){//compares the input bid to the current best bid
        this.bestBid = bid;
        return true;
      }
    }
    return false;
  }
  
  //gives the contract to a contractor so it no longer accepts bids
  public void awardContract(){
    this.acceptingBids = false;
  }
  
  //tells whether of not the contract is completed
  public boolean isComplete(){
    return this.complete;
  }
  
  //returns the date of completion
  public Date completeDate(){
    if(isComplete())
      return completedDate;
    else 
      return null;
  }
  
  //sets the contract as complete, sets the completed date, pays the contractor
  public void setComplete(Date completedDate){//spelling error in instrucitons. I changed competedDate to completedDate
    this.complete = true;
    this.completedDate = completedDate;
    if(Date.difference(completedDate, deadline) > 0){ /* pays the contractor with penalty when the contract 
                                                       * is completed after deadline */
      if( (this.bestBid.value() - (this.getPenalty() * Date.difference(completedDate, deadline))) < 0)/*pays 0 if 
        * penalty leads to negative payment*/
        this.bestBid.getContractor().pay(0.0);
      else 
        this.bestBid.getContractor().pay(this.bestBid.value() - 
                                       (this.getPenalty() * Date.difference(completedDate, deadline)));
    }
    else
      if( (this.bestBid.value() - (this.getBonus() * Date.difference(completedDate, deadline))) > this.getMaxValue())/*
       * pays the max value if bonus is too much */ 
        this.bestBid.getContractor().pay(this.getMaxValue());
      else 
        this.bestBid.getContractor().pay(this.bestBid.value() +
                                        (this.getBonus() * Date.difference(deadline, completedDate)));
  }  
}