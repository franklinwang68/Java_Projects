/**
 * Any Number 
 * Franklin Wang
 */
public abstract class Number{
  
  /**
   * Adds two Numbers.
   * @param n the Number to add to this
   * @return the sum of n and this
   */
  public abstract Number add(Number n);
  
  /**
   * Compares two Numbers.
   * @param n the Number to compare
   * @return whether the values are equal
   */
  public abstract boolean equals(Number n);
  
  /**
   * Returns the String represention of the Number
   * @return the String representation
   */
  public abstract String toString();
}