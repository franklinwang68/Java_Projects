/**
 * An arbituary floating point value
 * Franklin Wang
 */

public class Float extends Number{
  /** The sign of the float */
  protected boolean isNegative;
  
  /** The specific digits in reverse order */
  protected byte[] digits;
  
  /** The decimal position/the number of digits after the decimal point */
  private int precision;
  
  /**
   * Creates an arbituary floating point value.
   * @param isNegative the sign
   * @param digits the specific digits in reverse order
   * @param precision the decimal location 
   * @throws IllegalArgumentException if there are no digits
   */ 
  public Float(boolean isNegative, byte[] digits, int precision){
    this.isNegative = isNegative;
    this.digits = digits;
    this.precision = precision;
    if(digits == null)
      throw new IllegalArgumentException("digits cannot be null");
  }
  
  /**
   * Returns the sum of two arbituary floats. Assumes that the digits length is greater than the precision
   * @param n the value to add to this
   * @return the sum of two numbers as a Float
   * @throws IllegalArgumentException if input is not an instance of Float
   * @throws UnsupportedOperationException if the signs don't match
   */
  @Override
  public Float add(Number n){
    byte[] sum; //the new array of digits
    int carryOver = 0; //the value to carry over in the addition
    
    if(!(n instanceof Float)){
      throw new IllegalArgumentException("input not instance of Float");
    }
    
    //returns the other value if one is zero
    if(((Float)n).equals(new Float(false, new byte[]{0}, 0)))
      return this;
    if(this.equals(new Float(false, new byte[]{0}, 0)))
      return (Float)n;
    
    //checks signs
    if(((Float)n).isNegative != this.isNegative)
      throw new UnsupportedOperationException("Cannot add values of opposite signs");
    
    /* creates the byte[] for the sum of digits
     * If the length of digits is less than precision, errors will occur 
     * because the new array length can't contain the all the digits. See testAdd in Project3Tester.
     */ 
    if(((Float)n).digits.length < this.digits.length){
      sum = new byte[this.digits.length + 1];
    }else 
      sum = new byte[((Float)n).digits.length + 1];
    
    //lines up the decimal 
    if(((Float)n).precision <= this.precision){
      //do the addition with carry over
      for(int i = 0, j = ((Float)n).precision - this.precision, k = 0; k < sum.length; i++, j++, k++){
        try{
          if(this.digits[i] + ((Float)n).digits[j] + carryOver >= 10){
            sum[k] = (byte)(this.digits[i] + ((Float)n).digits[j] + carryOver - 10);
            carryOver = 1;
          }
          else{
            sum[k] = (byte)(this.digits[i] + ((Float)n).digits[j] + carryOver);
            carryOver = 0;
          }
        }
        //puts in 0 or the appropriate value if the array lengths don't match
        catch(ArrayIndexOutOfBoundsException e){
          if(i < this.digits.length){
            sum[k] = (byte)(this.digits[i] + carryOver);
          }
          else{
            if(j < ((Float)n).digits.length && j >= 0){
              sum[k] = (byte)(((Float)n).digits[j] + carryOver);
            }
            else  
              sum[k] = (byte)(carryOver);
          }
          carryOver = 0;
        }
      }
      return new Float(this.isNegative, sum, this.precision);
    }
    //lines up the decimal 
    else{
      //does the addition
      for(int i = this.precision - ((Float)n).precision, j = 0, k = 0; k < sum.length; i++, j++, k++){
        try{
          if(this.digits[i] + ((Float)n).digits[j] + carryOver >= 10){
            sum[k] = (byte)(this.digits[i] + ((Float)n).digits[j] + carryOver - 10);
            carryOver = 1;
          }
          else{
            sum[k] = (byte)(this.digits[i] + ((Float)n).digits[j] + carryOver);
            carryOver = 0;
          }
        }
        //puts a 0 or the appropriate value if the array lengths don't match
        catch(ArrayIndexOutOfBoundsException e){
          if(j < this.digits.length){
            sum[k] = (byte)(((Float)n).digits[j] + carryOver);
          }
          else{
            if(i < this.digits.length && i >= 0){
              sum[k] = (byte)(this.digits[i] + carryOver);
            }
            else  
              sum[k] = (byte)(carryOver);
          }
          carryOver = 0;
        }
      }
      return new Float(this.isNegative, sum, ((Float)n).precision);
    }
  }
  
  /**
   * Returns whether two Numbers are equal.
   * @param n the Number to compare
   * @return whether the values are equal
   */
  @Override
  public boolean equals(Number n){
    boolean isValueZero_this = true; //determines whether the value of this is zero
    boolean isValueZero_n = true; //determines whether the value is n is zero
    int leadingZeros_this = 0; //the nmumber of leading zeros in this
    int leadingZeros_n = 0; //the number of leading zeros in n
    int trailingZeros_this = 0; //the number of trailing zeros in this
    int trailingZeros_n = 0; //the number of trailing zeros in n
    
    //checks if the values are zero
    for(int i = 0; i < digits.length && isValueZero_this; i++){
      if(digits[i] != 0)
        isValueZero_this = false;
    } 
    for(int i = 0; i < ((Float)n).digits.length && isValueZero_n; i++){
      if(((Float)n).digits[i] != 0)
        isValueZero_n = false;
    }
    //returns true if both values represent zero, returns false if one is zero and the other is not
    if(isValueZero_this && isValueZero_n){
      return true;
    }
    else if(isValueZero_this != isValueZero_n)
      return false;
    
    //checks sign
    if(isNegative != ((Float)n).isNegative)
      return false;
    
    //determines the number of leading zeros
    for(int i = this.digits.length - 1; this.digits[i] == 0; i--)
      leadingZeros_this++;
    for(int i = ((Float)n).digits.length - 1; ((Float)n).digits[i] == 0; i--)
      leadingZeros_n++;
    
    //determines the number of trailing zeros
    for(trailingZeros_this = 0; this.digits[trailingZeros_this] == 0; trailingZeros_this++)
      ;
    for(trailingZeros_n = 0; ((Float)n).digits[trailingZeros_n] == 0; trailingZeros_n++)
      ;
    
    //checks decimal placement
    if(this.precision - trailingZeros_this != ((Float)n).precision - trailingZeros_n){
      return false;
    }
    
    //checks digits
    for(int i = trailingZeros_this, j = trailingZeros_n; i < digits.length - leadingZeros_this || j < ((Float)n).digits.length - leadingZeros_n; i++, j++){
      if(this.digits[i] != ((Float)n).digits[j])
        return false;
    }
    return true;
  }
  
  
  /**
   * Returns the String representation of the Float.
   * @return the String representation
   */
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder(); //holds the String representation
    boolean noLeadingDigits = true; //checks leading digits
    boolean leadingZero = true; //checks for leading zeros
    
    if(isNegative)
      builder.append('-');
    
    //appends the digits, ignoring the leading zeros
    for(int i = digits.length - 1; i >= precision; i--){
      if(leadingZero){
        if(digits[i] != 0){
          leadingZero = false;
          builder.append(digits[i]);
          noLeadingDigits = false;
        }
      }
      else 
        builder.append(digits[i]);
    }
    
    //appends 0. if the value is less than one
    if(noLeadingDigits)
      builder.append('0');
    builder.append('.');
    
    //appends the digits after the decimal if they exist, otherwise append 0
    if(precision - 1 < 0){
      builder.append('0');
    }
    else{
      for(int i = precision - 1; i >= 0; i--){
        try{
          builder.append(digits[i]);
        }
        catch(ArrayIndexOutOfBoundsException e){
          builder.append('0');
        }
      }
    }
    
    return builder.toString();
  }
}