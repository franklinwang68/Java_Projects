//Franklin Wang
//the Bid class represents the bid a contractor makes for a contract
public class Bid{
  
  //stores a Contract
  private Contract contract;
  
  //stores a Contracotr
  private Contractor contractor;
  
  //stores the bid price
  private double value;
  
  //Constructor creates a Bid with the contract, contractor, and price
  public Bid(Contract contract, Contractor contractor, double value){
    this.contract = contract;
    this.contractor = contractor;
    this.value = value;
  }
      
  //returns the contract
  public Contract contract(){//why not getContract?
    return this.contract;
  }
  
  //returns the contractor
  public Contractor getContractor(){
    return this.contractor;
  }
  
  //returns the bid price
  public double value(){//why not getValue?
    return this.value;
  }
}