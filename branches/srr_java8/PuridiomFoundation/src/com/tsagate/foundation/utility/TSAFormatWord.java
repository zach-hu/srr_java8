/*
 * Created on Apr 4, 2003
 */
package com.tsagate.foundation.utility;

/**
 * @author renzo
 */
public class TSAFormatWord implements FormatWord
{
	private StringBuffer sbWord;

	public String formatToClassName(String asWord) {
		this.sbWord = new StringBuffer(asWord.toLowerCase());

		//	UpperCase first char
		String stemp = this.sbWord.substring(0, 1).toUpperCase();

		this.sbWord.replace(0, 1, stemp);

		this.replaceChar("_");
		this.replaceChar("-") ;
		return this.sbWord.toString();
	}

	public String formatToVariableName(String asWord) {
		asWord = this.formatToClassName(asWord);
		asWord = asWord.substring(0,1).toLowerCase() + asWord.substring(1);
		return asWord;
	}

	public String formatToPropertyName(String asWord) {
		this.sbWord = new StringBuffer(asWord.toLowerCase());

		this.replaceChar("_");
		return this.sbWord.toString();
	}

	public String formatToText(String asWord) {
		this.sbWord = new StringBuffer(asWord.toLowerCase());

		//	UpperCase first char
		String stemp = this.sbWord.substring(0, 1).toUpperCase();

		this.sbWord.replace(0, 1, stemp);

		this.replaceWithChar("_", " ");
		this.replaceChar("-") ;
		return this.sbWord.toString();
	}

	public String formatWithPrefix(String asWord, String prefix) {
// 		String temp = this.formatToClassName(asWord);
//	   	return prefix + this.sbWord.toString();

		return prefix + this.formatToClassName(asWord);
  	}

	public String formatWithSufix(String asWord, String sufix) {
//		String temp = this.formatToClassName(asWord);
//		return this.sbWord.toString() + sufix;

		return this.formatToClassName(asWord) + sufix;
	}

	/**
	 * Method replaceChar
	 * uppercase next car after str (_) and delete (str)
	 * replaceChar
	 * @param str
	 */
	public void replaceWithChar(String str, String replaceWith) {
		int index = this.sbWord.indexOf(str);

		while (index >= 0 && (sbWord.length() > (index + 1))) {
			String stemp = this.sbWord.substring(index + 1, index + 2).toUpperCase();
			this.sbWord.replace(index + 1, index + 2, stemp);

			//	replace "_"
			this.sbWord.replace(index, index + 1, replaceWith);
			index = this.sbWord.indexOf(str, index + 1);
		}
	}

	/**
	 * Method replaceChar
	 * uppercase next car after str (_) and delete (str)
	 * replaceChar
	 * @param str
	 */
	public void replaceChar(String str) {
		int index = this.sbWord.indexOf(str);

		while (index >= 0 && (sbWord.length() > (index + 1))) {
			String stemp = this.sbWord.substring(index + 1, index + 2).toUpperCase();
			this.sbWord.replace(index + 1, index + 2, stemp);

			//	replace "_"
			this.sbWord.deleteCharAt(index);
			index = this.sbWord.indexOf(str, index + 1);
		}
	}

	public String formatToDatabaseName(String asWord) {
	    String	tableName = asWord;
	    if (asWord != null) {
		    String	upperCaseCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		    String	lowerCaseCharacters = upperCaseCharacters.toLowerCase();
		    StringBuffer sb = new StringBuffer();
		    int	len = asWord.length();

		    sb.append(asWord.charAt(0));
		    int countDigits = 0;
		    for (int i = 1; i < len; i++)
		    {
		        char c = asWord.charAt(i);
		        boolean digitMe = false;
		        if(i > 2 && i < len)
		        {
		        	char prevC = asWord.charAt(i - 1);

		        	if(Character.isDigit(prevC) && Character.isLetter(c))
		        	{
		        		digitMe = true;
		        	}
		        	else if(Character.isDigit(c) && Character.isLetter(prevC))
		        	{
		        		digitMe = true;
		        	}
		        	else if(Character.isDigit(c) && Character.isDigit(prevC))
		        	{
		        		digitMe = false;
		        	}
		        }
		        if ((upperCaseCharacters.indexOf(c) >= 0 && (i < len)) || (digitMe))
		        {
		            sb.append("_" + asWord.charAt(i));
		        }
		        else
		        {
		            sb.append(asWord.charAt(i));
		        }
		    }

		    tableName = sb.toString().toUpperCase();
	    }
	    return tableName;
	}

	public static void main(String[] args)
	{
		TSAFormatWord format = new TSAFormatWord();
		System.out.println(format.formatToPropertyName("asset_service"));
		System.out.println(format.formatToVariableName("asset_service"));
		System.out.println(format.formatToClassName("asset_service"));
		System.out.println(format.formatToText("asset_service"));
		System.out.println(format.formatToDatabaseName("asset_service"));
	}
}
