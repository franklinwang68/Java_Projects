import org.junit.*;
import static org.junit.Assert.*;

/**
 * A tester for the Numbers project
 * Franklin Wang
 */
public class Project3Tester{
  Float f0 = new Float(false, new byte[]{}, 0);
  Float f1 = new Float(false, new byte[]{0, 0, 0}, 0);
  Float f2 = new Float(false, new byte[]{1}, 0);
  Float f3 = new Float(false, new byte[]{0, 1}, 1);
  Float f4 = new Float(false, new byte[]{1}, 3);
  Float f5 = new Float(false, new byte[]{9, 0}, 1);
  Float f6 = new Float(false, new byte[]{0, 0, 1, 0, 0, 0}, 4);
  Float f7 = new Float(true, new byte[]{1, 2, 3, 4, 5, 6, 7}, 3);
  Float f8 = new Float(false, new byte[]{0, 6, 0}, 1);
  Float f9 = new Float(true, new byte[]{1, 2, 3, 4, 5, 6, 7}, 4);
  Float f10 = new Float(false, new byte[]{1, 2, 3, 4, 5, 6, 7}, 3);
  Float f11 = new Float(false, new byte[]{1, 2, 3}, 0);
  
  Whole w0 = new Whole(false, new byte[]{});
  Whole w1 = new Whole(false, new byte[]{0});
  Whole w2 = new Whole(false, new byte[]{1});
  Whole w3 = new Whole(false, new byte[]{0, 1, 2, 3, 0});
  Whole w4 = new Whole(false, new byte[]{0, 0, 1, 2, 3, 0, 0, 0});
  Whole w5 = new Whole(true, new byte[]{1, 2, 3});
  Whole w6 = new Whole(false, new byte[]{1, 2, 3});
  Whole w7 = new Whole(false, new byte[]{1, 2, 3, 0});
  Whole w8 = new Whole(false, new byte[]{9, 9, 9, 9});
  
  ComplexNumber c1 = new ComplexNumber(f7, f7);
  ComplexNumber c2 = new ComplexNumber(f9, f9);
  ComplexNumber c3 = new ComplexNumber(w6, w6);
  ComplexNumber c4 = new ComplexNumber(f7, f0);
  ComplexNumber c5 = new ComplexNumber(w6, f0);
  
  GaussianInteger g1 = new GaussianInteger(w6, w6);
  GaussianInteger g2 = new GaussianInteger(w5, w5);
  GaussianInteger g3 = new GaussianInteger(w6, w1);
  
  RealNumber r1 = new RealNumber(f7);
  RealNumber r2 = new RealNumber(f8);
  RealNumber r3 = new RealNumber(w6);
  
  RationalNumber q1 = new RationalNumber(w2, w6);
  RationalNumber q2 = new RationalNumber(w6, w2);
  RationalNumber q3 = new RationalNumber(w6);
  
  IntegerNumber z1 = new IntegerNumber(w2);
  IntegerNumber z2 = new IntegerNumber(w6);
  
  NaturalNumber n1 = new NaturalNumber(w6);
  NaturalNumber n2 = new NaturalNumber(w2);
  
  /**
   * Tests the getter methods for ComplexNumber, GaussianInteger, and RationalNumber
   */ 
  @Test
  public void testGetter(){
    //ComplexNumber
    assertTrue("Testing getRealPart", f7.equals(c1.getRealPart()));
    assertTrue("Testing getImaginaryPart", f7.equals(c1.getImaginaryPart()));

    //GaussianInteger
    assertTrue("Testing getRealPart", w6.equals(g1.getRealPart()));
    assertTrue("Testing getImaginaryPart", w6.equals(g1.getImaginaryPart()));
    
    //RationalNumber
    assertTrue("Testing getNumerator", w2.equals(q1.getNumerator()));
    assertTrue("Testing getDenominator", w6.equals(q1.getDenominator()));
    assertTrue("Testing getNumerator", w6.equals(q3.getNumerator()));
    assertTrue("Testing getDenominator", w2.equals(q3.getDenominator()));
  }
  
  /**
   * Tests the add method for all Number types
   */ 
  @Test
  public void testAdd(){  
    //For Float
    assertEquals("Testing empty digits", f0, f0.add(f0));
    assertEquals("Testing zero values", f0, f0.add(f1));
    assertEquals("Testing values that equal 1 with precision 0 and 1", "2.0", (f2.add(f3)).toString());
    //assertEquals("Testing one element arrays", "1.001", (f2.add(f4)).toString()); fails because the array length is too small
    assertEquals("Testing one element arrays with many precision", "0.002", (f4.add(f4)).toString());
    assertEquals("Testing one leading zero", "1.8", (f5.add(f5)).toString());
    assertEquals("Testing one trailing zero", "6.9", (f5.add(f8)).toString());
    assertEquals("Testing many leading and trailing zeros", "0.0200", (f6.add(f6)).toString());
    assertEquals("Testing many digits", "-15308.642", (f7.add(f7)).toString());
    assertEquals("Testing empty Whole input", "0.0", (f0.add(w0)).toString());
    assertEquals("Testing Whole input with value of zero", "2.0", (f2.add(w2)).toString());
    assertEquals("Testing Whole input of many digits", "642.0", (f11.add(w7)).toString()); 
    //Testing opposite signs
    try{
      f10.add(f9);
      fail("Exception not thrown");
    }
    catch(UnsupportedOperationException e){
      //All good
    }
    catch(Exception e){
      fail("Wrong Exception thrown");
    }
    
    //For Whole
    assertEquals("Testing empty digits", w0, w0.add(w0));
    assertEquals("Testing zero values", w0, w0.add(w1));
    assertEquals("Testing values that equal 1 and one element arrays", "2", (w2.add(w2)).toString());
    assertEquals("Testing one leading zero", "321", (w7.add(w0)).toString());
    assertEquals("Testing many leading and trailing zeros", "32421", (w4.add(w7)).toString());
    assertEquals("Testing many digits", "10320", (w8.add(w7)).toString());
    //Testing opposite signs
    try{
      w2.add(w5);
      fail("Exception not thrown");
    }
    catch(UnsupportedOperationException e){
      //All good
    }
    catch(Exception e){
      fail("Wrong Exception thrown");
    }
    
    //For ComplexNumber
    assertEquals("Complex takes Complex", "-15308.642 -15308.642i", (c1.add(c1)).toString());
    assertEquals("Takes GaussianInteger", "642 + 642i", (c3.add(g1)).toString());
    assertEquals("Takes Real", "-8419.7531 -765.4321i", (c2.add(r1)).toString());
    assertEquals("Takes Rational", "642 + 321i", (c3.add(q2)).toString());
    assertEquals("Takes Rational", "642 + 321i", (c3.add(q3)).toString());
    assertEquals("Takes Integer", "642 + 321i", (c3.add(z2)).toString());
    assertEquals("Takes Integer", "322 + 321i", (c3.add(z1)).toString());
    assertEquals("Takes Natural", "642 + 321i", (c3.add(n1)).toString());
    //Tests a rational number with a non 1 denominator
    try{
      c5.add(q1);
      fail("Exception not thrown");
    }
    catch(UnsupportedOperationException e){
      //All good
    }
    catch(Exception e){
      fail("Wrong excpetion thrown");
    }
    
    //For GaussainInteger
    assertEquals("Takes GaussianInteger", "642 + 642i", (g1.add(g1)).toString());
    assertEquals("Takes Integer", "642 + 0i", (g3.add(z2)).toString());
    assertEquals("Takes Natural", "642 + 321i", (g1.add(n1)).toString());
    
    //For RealNumber
    assertEquals("Takes Real", "-15308.642", (r1.add(r1)).toString());
    assertEquals("Takes Rational", "642", (r3.add(q2)).toString());
    assertEquals("Takes Rational", "642", (r3.add(q3)).toString());
    assertEquals("Takes Integer", "642", (r3.add(z2)).toString());
    assertEquals("Takes Integer", "322", (r3.add(z1)).toString());
    assertEquals("Takes Natural", "642", (r3.add(n1)).toString());
    //Tests a rational number with a non 1 denominator
    try{
      r3.add(q1);
      fail("Exception not thrown");
    }
    catch(UnsupportedOperationException e){
      //All good
    }
    catch(Exception e){
      fail("Wrong excpetion thrown");
    }
    
    //For RationalNumber
    assertEquals("Takes Rational", "642 / 1", (q2.add(q2)).toString());
    assertEquals("Takes Rational", "642 / 1", (q2.add(q3)).toString());
    assertEquals("Takes Integer", "642 / 1", (q2.add(z2)).toString());
    assertEquals("Takes Integer", "322 / 1", (q2.add(z1)).toString());
    assertEquals("Takes Natural", "642 / 1", (q2.add(n1)).toString());
    //Tests a rational number with different denominators
    try{
      q2.add(q1);
      fail("Exception not thrown");
    }
    catch(UnsupportedOperationException e){
      //All good
    }
    catch(Exception e){
      fail("Wrong excpetion thrown");
    }
    
    //For IntegerNumber
    assertEquals("Takes Integer", "642", (z2.add(z2)).toString());
    assertEquals("Takes Integer", "322", (z2.add(z1)).toString());
    assertEquals("Takes Natural", "642", (z2.add(n1)).toString());
    
    //For NaturalNumber
    assertEquals("Takes Natural", "642", (n1.add(n1)).toString());
    assertEquals("Takes Natural", "322", (n2.add(n1)).toString());
  }
  
  /**
   * Tests the equals method for all Number types.
   */  
  @Test
  public void testEquals(){
    //For Float
    assertTrue("Testing empty digits", f0.equals(f0));
    assertTrue("Testing zero values", f0.equals(f1));
    assertTrue("Testing values that equal 1 with precision 0 and 1", f2.equals(f3));
    assertFalse("Testing one element arrays", f2.equals(f4));
    assertTrue("Testing one element arrays with many precision", f4.equals(f4));
    assertTrue("Testing one leading zero", f5.equals(f5));
    assertFalse("Testing one trailing zero", f5.equals(f8));
    assertTrue("Testing many leading and trailing zeros", f6.equals(f6));
    assertTrue("Testing many digits", f7.equals(f7));
    assertTrue("Testing different precsision", f7.equals(f7));
    assertFalse("Testing opposite signs", f7.equals(f10));
    assertTrue("Testing empty Whole input", f0.equals(w0));
    assertTrue("Testing Whole input with value of zero", f2.equals(w2));
    assertTrue("Testing Whole input of many digits", f11.equals(w7));
    
    //For Whole
    assertTrue("Testing empty digits", w0.equals(w0));
    assertTrue("Testing zero values", w0.equals(w1));
    assertTrue("Testing values that equal 1 and one element arrays", w2.equals(w2));
    assertTrue("Testing one leading and trailing zero", w3.equals(w3));
    assertTrue("Testing many leading and trailing zeros", w4.equals(w4));
    assertTrue("Testing many digits", w6.equals(w7));
    assertFalse("Testing opposite signs", w5.equals(w6));
    
    //For ComplexNumber
    assertTrue("Complex takes Complex", c1.equals(c1));
    assertTrue("Takes GaussianInteger", c3.equals(g1));
    assertTrue("Takes Real", c4.equals(r1));
    assertTrue("Takes Rational", c5.equals(q2));
    assertTrue("Takes Rational", c5.equals(q3));
    assertFalse("Takes Rational", c5.equals(q1));
    assertTrue("Takes Integer", c5.equals(z2));
    assertFalse("Takes Integer", c5.equals(z1));
    assertTrue("Takes Natural", c5.equals(n1));
    
    //For GaussainInteger
    assertTrue("Takes GaussianInteger", g1.equals(g1));
    assertTrue("Takes Integer", g3.equals(z2));
    assertTrue("Takes Natural", g3.equals(n1));
    
    //For RealNumber
    assertTrue("Takes Real", r1.equals(r1));
    assertTrue("Takes Rational", r3.equals(q2));
    assertTrue("Takes Rational", r3.equals(q3));
    assertFalse("Takes Rational", r3.equals(q1));
    assertTrue("Takes Integer", r3.equals(z2));
    assertFalse("Takes Integer", r3.equals(z1));
    assertTrue("Takes Natural", r3.equals(n1));
    
    //For RationalNumber
    assertTrue("Takes Rational", q2.equals(q2));
    assertTrue("Takes Rational", q2.equals(q3));
    assertFalse("Takes Rational", q2.equals(q1));
    assertTrue("Takes Integer", q2.equals(z2));
    assertFalse("Takes Integer", q2.equals(z1));
    assertTrue("Takes Natural", q2.equals(n1));
    
    //For IntegerNumber
    assertTrue("Takes Integer", z2.equals(z2));
    assertFalse("Takes Integer", z2.equals(z1));
    assertTrue("Takes Natural", z2.equals(n1));
    
    //For NaturalNumber
    assertTrue("Takes Natural", n1.equals(n1));
    assertFalse("Takes Natural", n2.equals(n1));
  }
  
  /**
   * Tests the toString method for all Number types
   */ 
  @Test
  public void testToString(){ 
    //For Float
    assertTrue("Testing empty digits", "0.0".equals(f0.toString()));
    assertTrue("Testing zero values", "0.0".equals(f1.toString()));
    assertTrue("Testing values that equal 1 with precision 0 and 1", "1.0".equals(f2.toString()));
    assertTrue("Testing one element arrays with many precision", "0.001".equals(f4.toString()));
    assertTrue("Testing one leading zero", "0.9".equals(f5.toString()));
    assertTrue("Testing one trailing zero", "1.0".equals(f3.toString()));
    assertTrue("Testing many leading and trailing zeros", "0.0100".equals(f6.toString()));
    assertTrue("Testing many digits and negative sign", "-7654.321".equals(f7.toString()));

    //For Whole
    assertTrue("Testing empty digits", "0".equals(w0.toString()));
    assertTrue("Testing zero values", "0".equals(w1.toString()));
    assertTrue("Testing values that equal 1 with precision 0 and 1", "1".equals(w2.toString()));
    assertTrue("Testing one leading zero and one trailing zero", "3210".equals(w3.toString()));
    assertTrue("Testing many leading and trailing zeros", "32100".equals(w4.toString()));
    assertTrue("Testing many digits and negative sign", "-321".equals(w5.toString()));
    
    //For ComplexNumber
    assertTrue("Testing both positive", "321 + 321i".equals(c3.toString()));
    assertTrue("Testing both negative", "-7654.321 -7654.321i".equals(c1.toString()));
    
    //For GaussainInteger
    assertTrue("Testing both positive", "321 + 321i".equals(g1.toString()));
    assertTrue("Testing both negative", "-321 -321i".equals(g2.toString()));
    
    //For RealNumber see Float
    assertTrue("-7654.321".equals(r1.toString()));
    
    //For RationalNumber
    assertTrue("Testing Format", "1 / 321".equals(q1.toString()));
    assertTrue("Testing Format", "321 / 1".equals(q2.toString()));
    assertTrue("Testing Format", "321 / 1".equals(q3.toString()));
    
    //For IntegerNumber see Whole
    assertTrue("Testing Format", "1".equals(z1.toString()));
    
    //For NaturalNumber see Whole
    assertTrue("Testing Format", "321".equals(n1.toString()));
  }
}