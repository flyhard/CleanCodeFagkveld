package com.visma.tdd;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class StringCalculatorTest
{
  StringCalculator cut;

  @Before
  public void setUp() throws Exception
  {
    cut = new StringCalculator();
  }

  @Test
  public void testParameters() throws Exception
  {
    assertThat(cut.add(""), is(equalTo(0)));
    assertThat(cut.add("1"), is(equalTo(1)));
    assertThat(cut.add("1,2"), is(equalTo(3)));
    assertThat(cut.add("1,2,3,4,5"), is(equalTo(15)));
    assertThat(cut.add("1,2,3,4\n5"), is(equalTo(15)));
  }

  @Test
  public void testParametersInvalid() throws Exception
  {
    try
    {
      cut.add("2,x");
      fail();
    }
    catch (IllegalArgumentException e)
    {

    }
    catch (Exception e)
    {
      fail();
    }

    try
    {
      cut.add("1,\n");
      fail();
    }
    catch (NumberFormatException e)
    {

    }

    assertThat(cut.add("//;\n1;2"), is(equalTo(3)));

    try
    {
      cut.add("//;\n1,2");
      fail();
    }
    catch (NumberFormatException e)
    {

    }
  }

  @Test
  public void testNegatives()
  {
    try
    {
      cut.add("1,-2");
      fail("Needs to fail.");
    }
    catch (RuntimeException e)
    {
      fail("Failure cannot be runtime exception");
    }
    catch (Exception e)
    {
      assertThat(e.getMessage(), containsString("negatives not allowed"));
      assertThat(e.getMessage(), containsString("-2"));
    }
  }

  @Test
  public void testIgnoredNumbers() throws Exception
  {
    assertThat(cut.add("1,2,3000"), is(equalTo(3)));
    assertThat(cut.add("1,2,1001"), is(equalTo(3)));
    assertThat(cut.add("1,2,1000"), is(equalTo(1003)));
  }

  @Test
  public void testLargeDelimiters() throws Exception
  {
    assertThat(cut.add("//[xyz]\n1xyz2xyz3000"), is(equalTo(3)));
  }

  @Test
  public void testMultipleCustomDelimiters() throws Exception
  {
    assertThat(cut.add("//[x][z]\n1z2x3000"), is(equalTo(3)));
  }
}
