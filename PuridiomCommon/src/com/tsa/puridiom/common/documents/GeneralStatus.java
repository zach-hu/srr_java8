/*
 * Created on Aug 26, 2003
 */
package com.tsa.puridiom.common.documents;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Administrator
 */
public class GeneralStatus extends GeneralType
{

    public static final String STATUS_TEMPORARY = "01" ;
    public static final String STATUS_PERMANENT = "02";
    public static final String STATUS_INACTIVE = "03" ;
    public static final String STATUS_ON_HOLD = "04" ;


    /*  used for HISTORY ONLY  */

    public static String toString(String status)
    {
        return GeneralStatus.toString(status, "PURIDIOM");
    }
    public static String toString(String status, String organizationId)
    {
        String docStatus = "";
        try
        {
            if (status == null || status.equals("null"))
            {
                return "";
            }
            else
            {
                docStatus = DictionaryManager.getInstance("general-status", organizationId).getProperty(status, status);
            }
        }
        catch (Exception e)
        {
            Log.error("DocumentStatus", "Error loading status: " + status);
            Log.error("DocumentStatus", e.toString());
        }
        return docStatus;
    }
    public static String toValue(String statusString, String organizationId)
    {
        String docStatus = "";
        try
        {
            if (Utility.isEmpty(statusString) || statusString.equals("null"))
            {
                return "";
            }
            else
            {
                Map statusMap = DictionaryManager.getInstance("general-status", organizationId).getPropertyMap();
                Iterator statusKeys = statusMap.keySet().iterator();

                while (statusKeys.hasNext()) {
                    String	key = (String) statusKeys.next();
                    String	value = (String) statusMap.get(key);

                    if (value.equalsIgnoreCase(statusString)) {
                        docStatus = key;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.error("GeneralStatus", "Error getting value for status: " + statusString);
            Log.error("GeneralStatus", e.toString());
        }
        return docStatus;
    }

    public static List getAllValues(String organizationId)
	{
    	return getAllValues("general-status", organizationId);
	}

	public static Map getPropertyMap(String organizationId, String startCode)
	{
		return getPropertyMapOrderedByIntKey("general-status", organizationId, startCode);
	}

	public static Map getPropertyMap(String organizationId, String startCode, String lastCode)
	{
		return getPropertyMapOrderedByIntKey("general-status", organizationId, startCode, lastCode);
	}
}
