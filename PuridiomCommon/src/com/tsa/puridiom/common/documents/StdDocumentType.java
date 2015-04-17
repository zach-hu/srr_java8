/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class StdDocumentType 
{
	public static final String STANDARD_DOCUMENT = "DS" ;
	public static final String OTHER_DOCUMENT = "DO" ;
	
	
	public static String toString(String type)
	{
		return StdDocumentType.toString(type, "");
	}


    /**
     * @param string
     * @param organizationId
     * @return
     */
    public static String toString(String type, String organizationId)
    {
        if (type == null || type.equals("null")) {
			return "";
		}
		else 
		{
		    return DictionaryManager.getInstance("stddocument-type", organizationId).getProperty(type, type);
		}
    }
}
