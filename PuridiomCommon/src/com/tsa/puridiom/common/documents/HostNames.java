/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class HostNames extends GeneralType
{
	public static String toString(String type)
	{
		return HostNames.toString(type, "PURIDIOM");
	}

	/**
	 * @param string
	 * @param organizationId
	 * @return
	 */
	public static String toString(String type, String organizationId)
	{	
		String valueProperty = "";
		if (!(type == null || type.equals("null")))
		{
			PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);
			valueProperty = propertiesManager.getProperty("HOST NAMES", type, "");
			
			if (HiltonUtility.isEmpty(valueProperty)) {
				valueProperty = DictionaryManager.getInstance("host-names", organizationId).getProperty(type, "");					
			}
		}
		
		if (HiltonUtility.isEmpty(valueProperty))
		{
			valueProperty = "N";
		}
		return valueProperty;
	}

	public static String toValue(String typeText, String organizationId)
	{
		String orderType = "";
		try
		{
			if (Utility.isEmpty(typeText) || typeText.equals("null"))
			{
				return "";
			} else
			{
				Map orderTypeMap = DictionaryManager.getInstance("host-names", organizationId).getPropertyMap();
				Iterator orderTypeKeys = orderTypeMap.keySet().iterator();

				while (orderTypeKeys.hasNext())
				{
					String key = (String) orderTypeKeys.next();
					String value = (String) orderTypeMap.get(key);

					if (value.equalsIgnoreCase(typeText))
					{
						orderType = key;
						break;
					}
				}
			}
		} catch (Exception e)
		{
			Log.error("HostNames", "Error getting value for status: " + typeText);
			Log.error("HostNames", e.toString());
		}
		return orderType;
	}

	public static List getAllValues(String organizationId)
	{
		return getAllValues("host-names", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("host-names", organizationId);
	}

}
