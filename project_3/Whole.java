/**
 * An arbituray "whole" number that can have negative values
 * Franklin Wang
 */
public class Whole extends Float{
  /**
   * Creates and arbiturary whole number.
   * @param isNegative the sign of the number
   * @param digits the digits of the number
   */ 
  public Whole(boolean isNegative, byte[] digits){
    super(isNegative, digits, 0);
  }
  
  /**
   * Adds to Numbers
   * @param n the numeber to add to this
   * @return the sum of the two values
   * @throws IllegalArgumentException if input is not instance of Whole
   * @throws UnsupportedOperationException if signs don't match
   */
  @Override
  public Whole add(Number n){    
    byte[] sum; // the new array of digits
    int carryOver = 0; // The carry over value
    
    if(!(n instanceof Whole))
      throw new IllegalArgumentException("Input not instance of Whole");
    
    //returns the other value if one is zero
    if(((Whole)n).equals(new Whole(false, new byte[]{0})))
      return this;
    if(this.equals(new Whole(false, new byte[]{0})))
      return (Whole)n;
    
    //checks sign
    if(((Whole)n).isNegative != this.isNegative)
      throw new UnsupportedOperationException("Cannot add values of opposite signs");
    
    //creates the new array of digits
    if(((Whole)n).digits.length < this.digits.length){
      sum = new byte[this.digits.length + 1];
    }else 
      sum = new byte[((Whole)n).digits.length + 1];
    
    //adds each digit line by line and carries over
    for(int i = 0, k = 0; k < sum.length; i++, k++){
      try{
        if(this.digits[i] + ((Whole)n).digits[i] + carryOver >= 10){
          sum[k] = (byte)(this.digits[i] + ((Whole)n).digits[i] + carryOver - 10);
          carryOver = 1;
        }
        else{
          sum[k] = (byte)(this.digits[i] + ((Whole)n).digits[i] + carryOver);
          carryOver = 0;
        }
      }
      //puts in 0 of the appropriate value if the array indexes don't match
      catch(ArrayIndexOutOfBoundsException e){
        if(i < this.digits.length){
          sum[k] = (byte)(this.digits[i] + carryOver);
        }
        else{
          if(i < ((Float)n).digits.length){
            sum[k] = (byte)(((Whole)n).digits[i] + carryOver);
          }
          else  
            sum[k] = (byte)(carryOver);
        }
        carryOver = 0;
      }
    }
    return new Whole(this.isNegative, sum);
  }
  
  /**
   * Compares two numbers.
   * @param n the number to copmare
   * @return whether the values are equal
   */
  @Override
  public boolean equals (Number n){
    boolean isValueZero_this = true; //checks if this value is zero
    boolean isValueZero_n = true; //checks if n is zero
    int leadingZeros_this = 0; //counts the number of leading zeros in this
    int leadingZeros_n = 0; //counts the number of leading zeros in n
    
    //checks if the values are zero
    for(int i = 0; i < digits.length && isValueZero_this; i++){
      if(digits[i] != 0)
        isValueZero_this = false;
    } 
    for(int i = 0; i < ((Float)n).digits.length && isValueZero_n; i++){
      if(((Float)n).digits[i] != 0)
        isValueZero_n = false;
    }
    
    //return true if both values are zero, return false if one is zero and the other is not
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
    for(int i = ((Whole)n).digits.length - 1; ((Whole)n).digits[i] == 0; i--)
      leadingZeros_n++;
    
    //checks digits
    for(int i = 0, j = 0; i < digits.length - leadingZeros_this || j < ((Whole)n).digits.length - leadingZeros_n; i++, j++){
      try{
        if(this.digits[i] != ((Float)n).digits[j])
          return false;
      }
      catch(ArrayIndexOutOfBoundsException e){
        return false;
      }
    }
    return true;
  }
  
  /**
   * Returns the String representation of the Number
   * @return the value of the number
   */
  @Override
  public String toString(){
    StringBuilder builder = new StringBuilder(); //the String representation
    int leadingZeros = 0; //the number leading zeros
    
    //return 0 if the value is equal to zero
    if(this.equals(new Whole(false, new byte[]{0})))
      return "0";
    
    //counts the number of leading zeros, returns "0" if all digits are zero
    for(int i = this.digits.length - 1; this.digits[i] == 0; i--)
      leadingZeros++;
    
    if(isNegative)
      builder.append('-');
    
    //appends the digits
    for(int i = digits.length - 1 - leadingZeros; i >= 0; i--){
      builder.append(digits[i]);
    }
    return builder.toString();
  }
}