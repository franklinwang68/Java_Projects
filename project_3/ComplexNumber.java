/**
 * A complex number with a real and imaginary part.
 * Franklin Wang
 */
public class ComplexNumber extends Number {
  /** Real part */
  private Float real;
  
  /** Imaginary part */
  private Float imaginary;
  
  /**
   * Creates a complex number with a real part and an imaginary part.
   * @param real the real part of the number
   * @param imaginary the imaginary part of the number
   */ 
  public ComplexNumber(Float real, Float imaginary){
    this.real = real;
    this.imaginary = imaginary;
  }
  
  /**
   * Returns the real part of the complex number.
   * @return the real part
   */ 
  public Float getRealPart(){
    return this.real;
  }
  
  /**
   * Returns the imaginary part of the complex number.
   * @return the imaginary part
   */
  public Float getImaginaryPart(){
    return this.imaginary;
  }
  
  /**
   * Returns the sum of two compatiable numbers.
   * @param n the number to add to this
   * @return the sum of two numbers as a ComplexNumber
   * @throws IllegalArgumentException the input must be an instanceof ComplexNumber
   * @throws UnsupportedOperationException if the input is a RationalNumber with a non 1 denominator
   */ 
  @Override
  public ComplexNumber add(Number n){
    if (n instanceof ComplexNumber){
      if(n instanceof RationalNumber){
        if(((RationalNumber)n).getDenominator().equals(new Whole(false, new byte[]{1}))){
          return new ComplexNumber(getRealPart().add(((RationalNumber)n).getNumerator()), 
                                   getImaginaryPart());
        }
        else 
          throw new UnsupportedOperationException("Cannot add rational numbers with non 1 denominators");
      }
      return new ComplexNumber((Float)this.getRealPart().add(((ComplexNumber)n).getRealPart()), 
                               (Float)this.getImaginaryPart().add(((ComplexNumber)n).getImaginaryPart()));
    }
    else
      throw new IllegalArgumentException("input is not an instance of ComplexNumber");
  }
  
  
  /**
   * Compares the value of two complex numebers.
   * @param n the number to compare
   * @return whether the two numbers represent the same value
   */
  @Override
  public boolean equals(Number n){
    if(n instanceof ComplexNumber){    
      if(n instanceof RationalNumber){
        if(((RationalNumber)n).getDenominator().equals(new Whole(false, new byte[]{1}))){
          return getRealPart().equals(((RationalNumber)n).getNumerator()) && 
          getImaginaryPart().equals(new Whole(false, new byte[]{0}));
        }
        else 
          return false;
      }
      return getRealPart().equals(((ComplexNumber)n).getRealPart()) && 
        getImaginaryPart().equals(((ComplexNumber)n).getImaginaryPart());
    }
    else
      return false;
  }
  
  /**
   * Returns the String representation of the number in the form of a +/- bi.
   * @return the String representation of the number
   */
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder();
    
    builder.append(getRealPart().toString());
    if(!getImaginaryPart().isNegative)
      builder.append(" +");
    builder.append(' ' + getImaginaryPart().toString() + 'i');
    
    return builder.toString();
  }
  
  
}