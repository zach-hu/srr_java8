/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import java.util.List;
import java.util.Map;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class DisbursementType extends GeneralType
{
	public static final String SUPPLY_REQUEST = "S" ;
	public static final String TRANSFER_REQUEST = "T";
	public static final String OVER_THE_COUNTER = "O";
	
	public static String toString(String type)
	{
	    return DisbursementType.toString(type, "");
	    
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
	        return DictionaryManager.getInstance("disbursement-type", organizationId).getProperty(type, type);
	    }
    }
    
    public static List getAllValues(String organizationId)
	{
		return getAllValues("disbursement-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("disbursement-type", organizationId);
	}
}
