package com.tsa.puridiom.common.utility;

import java.math.BigDecimal;

/**
 * @author Néstor
 */
public class NumberToWordsConverter
{
	  private static final String[] majorNames = {
	    "",
	    " Thousand",
	    " Million",
	    " Billion",
	    " Trillion",
	    " Quadrillion",
	    " Quintillion"
	    };

	  private static final String[] tensNames = {
	    "",
	    " Ten",
	    " Twenty",
	    " Thirty",
	    " Fourty",
	    " Fifty",
	    " Sixty",
	    " Seventy",
	    " Eighty",
	    " Ninety"
	    };

	  private static final String[] numNames = {
	    "",
	    " One",
	    " Two",
	    " Three",
	    " Four",
	    " Five",
	    " Six",
	    " Seven",
	    " Eight",
	    " Nine",
	    " Ten",
	    " Eleven",
	    " Twelve",
	    " Thirteen",
	    " Fourteen",
	    " Fifteen",
	    " Sixteen",
	    " Seventeen",
	    " Eighteen",
	    " Nineteen"
	    };

	 private static String convertLessThanOneThousand(int number)
	 {
	    String soFar;

	    if (number % 100 < 20){
	        soFar = numNames[number % 100];
	        number /= 100;
	       }
	    else {
	        soFar = numNames[number % 10];
	        number /= 10;

	        soFar = tensNames[number % 10] + "-" + soFar.trim(); 	// changed this line
	        number /= 10;
	       }
	    if (number == 0) return soFar;
	    return numNames[number] + " Hundred" + soFar;
	}

	public static String convert(int number)
	{
	    /* special case */
	    if (number == 0) { return "zero"; }

	    String prefix = "";

	    if (number < 0) {
	        number = -number;
	        prefix = "negative";
	      }

	    String soFar = "";
	    int place = 0;

	    do {
	      int n = number % 1000;
	      if (n != 0){
	         String s = convertLessThanOneThousand(n);
	         soFar = s + majorNames[place] + soFar;
	        }
	      place++;
	      number /= 1000;
	      } while (number > 0);

	    return (prefix + soFar).trim();
	}

	public static String convertToDollarText(BigDecimal number)
	{
		String numberString = number.toString();
		String centsPart = (numberString.substring(numberString.lastIndexOf('.') + 1));
		if (centsPart.length()== 1)
		{
			centsPart = centsPart + "0";
		}
		else if (centsPart.length() > 2)
		{
			centsPart = "00";
		}

	    return convert(number.intValue()) + " Dollars And " + centsPart + " Cents";
	}

	public static void main(String[] args)
	{
		NumberToWordsConverter f = new NumberToWordsConverter();
	    System.out.println("*** " + f.convertToDollarText(new BigDecimal("153.61")));
	    System.out.println("*** " + f.convertToDollarText(new BigDecimal("7736.50")));
	    System.out.println("*** " + f.convertToDollarText(new BigDecimal("45108.69")));
	    System.out.println("*** " + f.convertToDollarText(new BigDecimal("116.00")));
	    System.out.println("*** " + f.convertToDollarText(new BigDecimal("200")));
    }
}
