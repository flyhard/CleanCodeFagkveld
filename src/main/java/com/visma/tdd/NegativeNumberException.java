package com.visma.tdd;

import java.util.List;

import com.google.common.base.Joiner;

public class NegativeNumberException extends Exception
{

  private final List<Integer> negs;
  public NegativeNumberException(final String string, final List<Integer> negs)
  {
    super(string);
    this.negs = negs;
  }

  @Override
  public String getMessage()
  {
    return super.getMessage() + Joiner.on(" ").join(negs);
  }
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
