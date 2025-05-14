//Franklin Wang
//the Contractor class represents a contractor 
public class Contractor{
  
  //contractor name
  private String name;
  
  //contractor address
  private String address;
  
  //contractor contact person
  private String contact;
  
  //contractor payment
  private double balance = 0.0;
  
  //constructor with inputs
  public Contractor(String name, String address, String contact){
    this.name = name;
    this.address = address;
    this.contact = contact;
  }
  
  //returns the name of the contractor
  public String getName(){
    return this.name;
  }
  
  //changes the name of the contractor
  public void setName(String name){
    this.name = name;
  }
  
  //returns the address of the contractor
  public String getAddress(){
    return this.address;
  }
  
  //changes the address of the contractor
  public void setAddress(String address){
    this.address = address;
  }
  
  //returns the contact person of the contractor
  public String getContact(){
    return this.contact;
  }
  
  //changes the contact person
  public void setContact(String name){
    this.contact = name;
  }
  
  //returns the amount the contractor has been paid
  public double getAmountPaid(){
    return this.balance;
  }
  
  //pays the contractor
  public void pay(double amount){
    this.balance = this.balance + amount;//+=amount?
  }
  
  //overrides toStirng() so it gives the name, address, and contact person instead of the address
  @Override
  public String toString(){
    return this.getName() + " " + this.getAddress() + " " + this.getContact();
  }
  
  //overrides equals() so if the contractor name matches, they are the same
  @Override
  public boolean equals(Object o){
    if (o instanceof Contractor){
      Contractor c = (Contractor)o; //type cast so it compares the same class type
      return this.name.equals(c.name);
    }
    else
      return false;
  }
}