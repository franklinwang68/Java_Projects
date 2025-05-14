/**
 * A natural number (includes 0)
 * Franklin Wang
 */
public class NaturalNumber extends IntegerNumber{
  /**
   * Creates a NaturalNumber.
   * @param real the value of the naturalNumber
   * @throws IllegalArgumentException NaturalNumbers only accpet positve values
   */ 
  public NaturalNumber(Whole real){
    super(real);
    if(getRealPart().isNegative){
      throw new IllegalArgumentException("Cannot take negative values");
    }
  }
  
  /**
   * Adds two NaturalNumbers.
   * @param n the Number to add
   * @return the sum of two numbers
   * @throws IllegalArgumentException Input must be an instanceof NaturalNumber
   */ 
  @Override
  public NaturalNumber add(Number n){
    if(n instanceof NaturalNumber){
      return new NaturalNumber((Whole)this.getRealPart().add(((NaturalNumber)n).getRealPart()));
    }
    else
      throw new IllegalArgumentException("Input not an instance of NaturalNumber");
  }
  
  /**
   * Compares two values.
   * @param n the Number to compare
   * @return whether the values are equal
   */
  @Override
  public boolean equals(Number n){
    return super.equals(n);
  }
  
  /**
   * Returns the String representation of the NatualNumber
   * @return the String representation of the value
   */
  @Override 
  public String toString(){
    return getRealPart().toString();
  }
  
}