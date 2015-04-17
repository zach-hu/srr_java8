/*
 * Created on Aug 31, 2006
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class ChecklistEntryResponseType 
{
	public static final String YES_NA = "YN" ;
	public static final String OPEN_TEXT = "TEXT" ;
	public static final String YES_NO_AND_OPEN_TEXT = "BOTH" ;
	public static final String MULTIPLE_CHOICE = "MC" ;
	
	
	public static String toString(String type)
	{
		return ChecklistEntryResponseType.toString(type, "");
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
		    return DictionaryManager.getInstance("checklistentry-response-type", organizationId).getProperty(type, type);
		}
    }
}