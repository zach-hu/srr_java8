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
public class InvoiceType extends GeneralType
{
	public static final String PURCHASING = "PU" ;
	public static final String PRICING = "PR";
	public static final String REQUEST_FOR_QUOTATION = "RQ";
	public static final String REQUEST_FOR_INFORMATION = "RI";
	public static final String REQUEST_FOR_PROPOSAL = "RP";
	public static final String INVITATION_TO_BID = "IB";
	public static final String QUOTE_FOR_CONSTRUCTION = "QC";
	public static final String INVITATION_FOR_QUOTE = "IQ";

	public static String toString(String type)
	{
		return InvoiceType.toString(type, "");
	}

	public static String toString(String type, String organizationId)
	{
	    if (type == null || type.equals("null"))
		{
			return "";
		}
		else
		{
		    return DictionaryManager.getInstance("invoice-type", organizationId).getProperty(type, type);
		}
	}

	public static List getAllValues(String organizationId)
	{
		return getAllValues("invoice-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("invoice-type", organizationId);
	}

}
