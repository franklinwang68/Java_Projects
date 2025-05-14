/**
 * A gaussian integer with a real and imaginary part that are integers
 * Franklin Wang
 */
public class GaussianInteger extends ComplexNumber{
  /** Real part */
  private Whole real;
  
  /** Imaginary part */
  private Whole imaginary;
  
  /**
   * Creates a gaussian integer with a real part and an imaginary part.
   * @param real the real part of the number
   * @param imaginary the imaginary part of the number
   */ 
  public GaussianInteger(Whole real, Whole imaginary){
    super(real, imaginary);
    this.real = real;
    this.imaginary = imaginary;
  }
  
  /**
   * Returns the real part of the gaussian integer.
   * @return the real part
   */ 
  public Whole getRealPart(){
    return this.real;
  }
  
  /**
   * Returns the imaginary part of the gaussian integer.
   * @return the imaginary part
   */ 
  public Whole getImaginaryPart(){
    return this.imaginary;
  }
  
  /**
   * Returns the sum of two compatiable numbers.
   * @param n the number to add to this
   * @return the sum of two numbers as a GaussianInteger
   * @throws IllegalArgumentException if the input is a not an instance of GaussianInteger or IntegerNumber 
   */ 
  @Override
  public GaussianInteger add(Number n){
    //addition if n is an IntegerNumber or NaturalNumber
    if(n instanceof IntegerNumber){
      return new GaussianInteger((Whole)this.getRealPart().add(((IntegerNumber)n).getRealPart()), 
                                 this.getImaginaryPart());
    }
    
    //addition with GaussianIntegers
    if(n instanceof GaussianInteger){
      return new GaussianInteger((Whole)this.getRealPart().add(((GaussianInteger)n).getRealPart()), 
                                 (Whole)this.getImaginaryPart().add(((GaussianInteger)n).getImaginaryPart()));
    }
    else
      throw new IllegalArgumentException("Input is not an instance of GaussianInteger or IntegerNumber");
  }
    
  /**
   * Compares the value of two gaussian integers.
   * @param n the number to compare
   * @return whether the two numbers represent the same value
   */
  @Override
  public boolean equals(Number n){
    return super.equals(n);
  }
  
  /**
   * Returns the String representation of the number in the form of a +/- bi.
   * @return the String representation of the number
   */
  @Override
  public String toString(){
    return super.toString();
  }
  
}