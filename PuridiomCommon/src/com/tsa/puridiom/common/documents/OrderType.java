/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class OrderType extends GeneralType
{
	public static final String PURCHASE_ORDER = "PO";

	public static final String BLANKET_ORDER = "BO";

	public static final String RELEASE_ORDER = "RO";

	public static final String DELIVERY_ORDER = "DO";

	public static final String DELIVERY_RELEASE = "DR";

	public static final String SERVICE_ORDER = "SO";

	public static final String SERVICE_BLANKET = "SB";

	public static final String SERVICE_RELEASE = "SR";

	public static final String CONTRACT = "CT";

	public static final String ALASKA_PURCHASE_REQUISITION = "AK";

	public static final String PURCHASE_RELEASE = "PR";

	public static String toString(String type)
	{
		return OrderType.toString(type, "PURIDIOM");
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
		} else
		{
			String typeFound = DictionaryManager.getInstance("order-type", organizationId).getProperty(type, "");
			if (HiltonUtility.isEmpty(type))
			{
				typeFound = "PO";
			}
			return typeFound;
		}
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
				Map orderTypeMap = DictionaryManager.getInstance("order-type", organizationId).getPropertyMap();
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
			Log.error("OrderType", "Error getting value for status: " + typeText);
			Log.error("OrderType", e.toString());
		}
		return orderType;
	}

	public static List getAllValues(String organizationId)
	{
		return getAllValues("order-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("order-type", organizationId);
	}

}
