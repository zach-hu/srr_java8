/*
 * Created on Sep 25, 2003 
 */
package com.tsagate.foundation.utility;

import java.text.*;
import java.util.*;

/**
 * @author Administrator 
 */
public class LocaleBasedComparator {
	public static int compareTwoObjects(Object source, Object target, Locale locale) throws Exception{
		int result = 0;
		try{
			RuleBasedCollator rbc = (RuleBasedCollator)Collator.getInstance(locale);
			result = rbc.compare(source, target);
		}
		catch(Exception exception){
			throw exception;
		}
		finally{
			return result;
		}
	}	
	
	public static int compareTwoStrings(String source, String target, Locale locale) throws Exception{
		int result = 0;
		try{
			RuleBasedCollator rbc = (RuleBasedCollator)Collator.getInstance(locale);
			result = rbc.compare(source, target);
		}
		catch(Exception exception){
			throw exception;
		}
		finally{
			return result;
		}
	}
	
	/**
	 * compareTwoStrings
	 * <p>Gets the current value of the default locale for this instance of the Java Virtual Machine
	 * @param source
	 * @param target
	 * @return
	 * @throws Exception
	 */
	public static int compareTwoStrings(String source, String target) throws Exception
	{
		return LocaleBasedComparator.compareTwoStrings(source, target, Locale.getDefault());
	}
	

}
