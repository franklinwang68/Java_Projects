/**
 * Any real number
 * Franklin Wang
 */
public class RealNumber extends ComplexNumber{
  /**
   * Creates a RealNumber.
   * @param real the value of the RealNumber
   */ 
  public RealNumber(Float real){
    super(real, new Float(false, new byte[]{0}, 0));
  }
  
  /**
   * Adds to Numbers of compatiable types.
   * @param n the Number to add to this
   * @return the sum of the two values
   * @throws IllegalArgumentException Input must be an instance of a RealNumber
   * @throws UnsupportedOperationException if the input is a RationalNumber with a non 1 denominator
   */
  @Override
  public RealNumber add(Number n){
    if (n instanceof RealNumber){
      if(n instanceof RationalNumber){
        if(((RationalNumber)n).getDenominator().equals(new Whole(false, new byte[]{1}))){
          return new RealNumber((Float)getRealPart().add(((RationalNumber)n).getNumerator()));
        }
        else 
          throw new UnsupportedOperationException("Cannot add rational numbers with non 1 denominators");
      }
      return new RealNumber((Float)this.getRealPart().add(((RealNumber)n).getRealPart()));
    }
    else
      throw new IllegalArgumentException("Input not an instance of RealNumber");
  }
  
  /**
   * Compares to Numbers.
   * @param n the Number to compare
   * @return whether the values are equal
   */
  @Override
  public boolean equals(Number n){
    return super.equals(n);
  }
  
  /**
   * Returns the String representation of the RealNumber
   * @return the String representation of the value
   */
  @Override
  public String toString(){
    return getRealPart().toString();
  }
  
}