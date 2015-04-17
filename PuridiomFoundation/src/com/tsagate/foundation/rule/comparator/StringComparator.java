/*
 * Created on Jul 18, 2003
 */
package com.tsagate.foundation.rule.comparator;

import com.tsagate.foundation.utility.Log;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;

/**
 * @author Administrator
 */
public class StringComparator implements Comparator 
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Object objectOne, Object objectTwo) 
	{
		String stringOne = (String)objectOne;
		String stringTwo = (String)objectTwo;
		if (stringOne == null && stringTwo == null)
		{
			return 0;
		}
		else if (stringOne == null || objectTwo == null)
		{
			return -1;
		}
		else
		{
		    try
		    {
		        byte asciiBytes1[] = stringOne.getBytes("ASCII");
                byte asciiBytes2[] = stringTwo.getBytes("ASCII");
                
            	int len1 = asciiBytes1.length;
            	int len2 = asciiBytes2.length;

            	if (len1 == len2) {
            	    int k = 0;
            	    
            	    while (k < len1) {
            	        byte b1 = asciiBytes1[k];
            	        byte b2 = asciiBytes2[k];
            	        
						if (b1 != b2) {
						    return b1 - b2;
						}
						k++;
            	    }
            	    return 0;
				} else {
				    return len1 - len2;
            	}
		    } catch (UnsupportedEncodingException e) {
                Log.error(this, e.getMessage());
            }
		    
			return stringOne.compareTo(stringTwo);
		}
	}

}
