/*
 * Created on Feb 28, 2007
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Kathleen
 */
public class InspectionLevel
{
	public static final String	VISUAL= "1" ;
	public static final String	PHYSICAL = "2";
	public static final String	SPECIAL = "3";
	public static final String	NONE_REQUIRED = "4";

	public static String toString(String type)
	{
		return InspectionLevel.toString(type, "PURIDIOM");
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
		    return DictionaryManager.getInstance("inspection-level", organizationId).getProperty(type, type);
		}
    }

    public static String toValue(String typeText, String organizationId)
    {
        String inspectionLevel = "";
        try
        {
            if (Utility.isEmpty(typeText) || typeText.equals("null"))
            {
                return "";
            }
            else
            {
                Map inspectionLevelMap = DictionaryManager.getInstance("inspection-level", organizationId).getPropertyMap();
                Iterator inspectionLevelKeys = inspectionLevelMap.keySet().iterator();

                while (inspectionLevelKeys.hasNext()) {
                    String	key = (String) inspectionLevelKeys.next();
                    String	value = (String) inspectionLevelMap.get(key);

                    if (value.equalsIgnoreCase(typeText)) {
                    	inspectionLevel = key;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.error("InspectionLevel", "Error getting value for status: " + typeText);
            Log.error("InspectionLevel", e.toString());
        }
        return inspectionLevel;
    }
}
