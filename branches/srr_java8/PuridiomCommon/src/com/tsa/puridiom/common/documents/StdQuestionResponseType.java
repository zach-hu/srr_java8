/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class StdQuestionResponseType 
{
	public static final String YES_NO = "YN" ;
	public static final String OPEN_TEXT = "TEXT" ;
	public static final String YES_NO_AND_OPEN_TEXT = "BOTH" ;
	public static final String MULTIPLE_CHOICE = "MC" ;
	
	public static String toString(String type)
	{
		return StdQuestionResponseType.toString(type, "");
	}

    /**
     * @param string
     * @param organizationId
     * @return
     */
    public static String toString(String type, String organizationId)
    {
        if (type == null || type.equals("null")) 
		{
			return "";
		}
		else 
		{
		    return DictionaryManager.getInstance("stdquestion-response-type", organizationId).getProperty(type, type);
		}
    }
}