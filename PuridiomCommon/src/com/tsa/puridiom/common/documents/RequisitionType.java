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
public class RequisitionType extends GeneralType
{
	public static final String PURCHASE_REQUEST = "P";

	public static final String MSR_REQUEST = "M";

	public static final String SUPPLY_REQUEST = "S";

	public static final String IMPRINT_REQUEST = "Z";

	public static final String TRANSFER_REQUEST = "T";

	public static final String REPLENISH_REQUEST = "R";

	public static final String INVENTORY_RETURN = "I";

	public static final String PURCHASE_RETURN = "U";

	public static final String HISTORY_REQUEST = "Q";

	public static final String CHANGE_REQUEST = "C";

	public static final String RELEASE_REQUEST = "E";

	public static final String PRICING_REQUEST = "N";

	public static final String CAPITAL_REQUEST = "A";

	public static final String ADMIN_CHECK_REQUEST = "K";

	public static final String PROJECT_REQUEST = "O";

	public static final String REVISION_REQUEST = "V";

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
		return getAllValues("requisition-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("requisition-type", organizationId);
	}
}
