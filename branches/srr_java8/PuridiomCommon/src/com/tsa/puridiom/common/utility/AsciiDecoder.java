/**
 *
 */
package com.tsa.puridiom.common.utility;


/**
 * @author Johnny Zapana
 */
public class AsciiDecoder
{
	public static String getLimitedCharString(String token, char limitChar)
	{
		char tokenChars[] = token.toCharArray();
		char tokenCharsLimited[] = new char[tokenChars.length];
		int index = 0;


		for (int i = 0; i < tokenChars.length; i++)
		{
			if (tokenChars[i] <= limitChar)
			{
				tokenCharsLimited[index++] = tokenChars[i];
			}
		}

		return new String(tokenCharsLimited);
	}
}
