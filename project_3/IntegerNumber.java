/**
 * An integer
 * Franklin Wang
 */
public class IntegerNumber extends RationalNumber{
  
  /**
   * Creates an integer number
   * @param real the value of the integer
   */ 
  public IntegerNumber(Whole value){
    super(value);
  }
  
  /**
   * Returns the sum of two compatiable numbers.
   * @param n the number to add to this
   * @return the sum of two numbers as a IntegerNumber
   * @throws IllegalArgumentException Input must be an instanceof IntegeNumber
   */ 
  @Override
  public IntegerNumber add(Number n){
    if(n instanceof IntegerNumber){
      return new IntegerNumber((Whole)this.getNumerator().add(((IntegerNumber)n).getNumerator()));
    }
    else
      throw new IllegalArgumentException("Input not an instance of IntegerNumber");
  }

  /**
   * Compares two Numbers.
   * @param n the Number to compare
   * @return whether the values are equal
   */ 
  @Override
  public boolean equals(Number n){
    return super.equals(n);
  }
  
  /**
   * Returns the String representation of an IntegerNumber.
   * @return the String representation of the value
   */
  @Override 
  public String toString(){
    return getNumerator().toString();
  }
  
}