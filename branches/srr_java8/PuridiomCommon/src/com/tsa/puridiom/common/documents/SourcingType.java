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
public class SourcingType extends GeneralType
{
	public static final String DIRECT_PURCHASE = "D";

	public static final String INVENTORY = "I";

	public static final String STORE_CATALOG = "P";

	public static final String INTERNAL_CATALOG = "R";

	public static final String PURCHASE_FOR_SOURCING = "S";


	public static String toString(String type)
	{
		return RequisitionType.toString(type, "");
	}

	public static String toString(String type, String organizationId)
	{
		if (type == null || type.equals("null"))
		{
			return "";
		} else
		{
			return DictionaryManager.getInstance("requisition-type", organizationId).getProperty(type, type);
		}
	}

	public static List getAllValues(String organizationId)
	{
		return getAllValues("sourcing-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
	return getPropertyMapOrderedByValue("sourcing-type", organizationId);
	}
}
