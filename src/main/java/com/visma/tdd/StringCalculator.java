package com.visma.tdd;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;

public class StringCalculator
{

  private static final String DEFAULT_PATTERN = "[\\s,]";

  public int add(final String inputString) throws NegativeNumberException
  {

    String string = inputString;
    if (string.isEmpty())
    {
      return 0;
    }
    Pattern pattern = Pattern.compile("^(//\\[(.+)\\]|(.+))\n(.*)");
    Matcher matcher = pattern.matcher(string);
    String delimiter = DEFAULT_PATTERN;

    if (matcher.matches())
    {
      delimiter = matcher.group(2);
      string = matcher.group(4);
    }
    if (string.matches(".*" + delimiter + "{2,}.*"))
    {
      throw new NumberFormatException();
    }
    String[] numbers = string.split(delimiter);
    List<Integer> negs = Lists.newArrayList();
    int sum = 0;
    for (String s : numbers)
    {
      int number = Integer.parseInt(s);
      if (number < 0)
      {
        negs.add(number);
      }
      else
      {
        if (number < 1001)
        {
          sum += number;
        }
      }
    }
    if (negs.isEmpty())
    {
      return sum;
    }
    else
    {
      throw new NegativeNumberException("negatives not allowed", negs);
    }
  }

}
