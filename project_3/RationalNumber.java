/**
 * A rational number represented as a fraction
 * Franklin Wang
 */
public class RationalNumber extends RealNumber{
  /** The numerator */
  private Whole numerator;
  
  /** The denominator */
  private Whole denominator;
  
  /**
   * Creates a rational number with a numerator and denominator.
   * @param numer the numerator 
   * @param denom the denominator
   * @throws IllegalArgumentException if the denominator is zero
   */ 
  public RationalNumber(Whole numer, Whole denom){
    super(null);
    numerator = numer;
    denominator = denom;
    if(denominator.equals(new Whole(false, new byte[]{0}))){
      throw new IllegalArgumentException("Denominator cannot be zero");
    }
  }
  
  /**
   * Creates a rational number with a numerator and a denominator of 1.
   * @param numer the numerator
   */ 
  public RationalNumber(Whole numer){
    super(numer);
    numerator = numer;
    denominator = new Whole(false, new byte[]{1});
  }
  
  /**
   * Returns the numerator.
   * @return the numerator
   */ 
  public Whole getNumerator(){
    return this.numerator;
  }
  
  /**
   * Returns the denominator.
   * @return the denominator
   */ 
  public Whole getDenominator(){
    return this.denominator;
  }
  
  /**
   * Returns the sum of two compatiable numbers
   * @param n the number to add
   * @return the sum of the two numbers
   * @throws UnsupportedOperationException Only numbers of the same denominator are accepted
   * @throws IllegalArgumentException Input must be an instance of RationalNumber
   */
  @Override
  public RationalNumber add(Number n){
    if (n instanceof RationalNumber){
      if(!(getDenominator().equals(((RationalNumber)n).getDenominator()))){
        throw new UnsupportedOperationException("Cannot take different denominators");
      }
      return new RationalNumber((Whole)this.getNumerator().add(((RationalNumber)n).getNumerator()), getDenominator());
    }
    else
      throw new IllegalArgumentException();
  }
  
  /**
   * Returns true if the numerators equal and the denominators equal.
   * @param n the Number to compare
   * @return whether the values are equal
   */
  @Override
  public boolean equals(Number n){
    return this.getNumerator().equals(((RationalNumber)n).getNumerator()) &&
      this.getDenominator().equals(((RationalNumber)n).getDenominator());
  }
  
  /**
   * Returns the String representation of the RationalNumber.
   * @return th String representation
   */
  @Override
  public String toString(){
    return getNumerator() + " / " + getDenominator();
  }
  
}