/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

public class AutoGenType 
{
	public static final String REQUISITION = "REQ";
	public static final String SOLICITATION = "RFQ";
	public static final String ORDER = "PO";
	public static final String DISBURSEMENT = "DSB";
	public static final String SUPPLIER_CONTRACT_INSURANCE = "SCI";
	public static final String VOUCHER_CONTROL_NUMBER = "CTL";
	public static final String INVOICE_VOUCHER_NUMBER = "VCH";
	
	public static String toString(String type)
	{
		return AutoGenType.toString(type, "");
	}
	
	public static String toString(String type, String organizationId)
	{
		if (type == null || type.equals("null")) 
		{
			return "";
		}
		else 
		{
		    return DictionaryManager.getInstance("autogen-type", organizationId).getProperty(type, type);
		}
	}
}
