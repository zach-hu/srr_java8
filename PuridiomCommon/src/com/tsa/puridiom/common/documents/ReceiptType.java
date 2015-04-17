/*
 * Created on June 29, 2005
 */
package com.tsa.puridiom.common.documents;

import java.util.List;
import java.util.Map;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class ReceiptType extends GeneralType
{
	public static final String ORIGINAL = "O" ;
	public static final String ADJUSTMENT = "A";
	public static final String RETURN = "R";
	public static final String FULL_RECEIPT_FROM_PO = "P";
	public static final String TRANSFER = "T";

	public static String toString(String type)
	{
		return ReceiptType.toString(type, "");
	}

	public static String toString(String type, String organizationId)
	{
	    if (type == null || type.equals("null"))
		{
			return "";
		}
		else
		{
		    return DictionaryManager.getInstance("receipt-type", organizationId).getProperty(type, type);
		}
	}

	public static List getAllValues(String organizationId)
	{
		return getAllValues("receipt-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("receipt-type", organizationId);
	}

}
