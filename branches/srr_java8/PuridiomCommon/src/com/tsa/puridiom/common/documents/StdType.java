/*
 * Created on June 29, 2005
 */
package com.tsa.puridiom.common.documents;

import java.util.List;
import java.util.Map;

import com.tsagate.properties.DictionaryManager;

public class StdType extends GeneralType
{
	public static final String AC01 = "Entity";
	public static final String AC02 = "Dept";
	public static final String AC03 = "Account";
	public static final String AC04 = "Event";
	public static final String AC05 = "Performance";
	public static final String AC06 = "Program";
	public static final String BIDW = "Bid Waiver Codes";
	public static final String CARR = "Carriers";
	public static final String COMM = "COMM";
	public static final String CPTL = "CPTL";
	public static final String DEPT = "DEPT";
	public static final String DISP = "Disposition Codes";
	public static final String DIVS = "DIVS";
	public static final String ENDP = "ENDP";
	public static final String FOB = "FOB Codes";
	public static final String INSP = "Inspection Codes";
	public static final String IV01 = "Pay Group";
	public static final String PO01 = "Order UDF 1";
	public static final String PO04 = "Contract Required";
	public static final String PO06 = "Order UDF 6";
	public static final String PO08 = "Order UDF 8";
	public static final String PV01 = "PV01";
	public static final String RCOD = "Voucher Reason Codes";
	public static final String REJ = "Rejection Codes";
	public static final String RESP = "RESP";
	public static final String RQ01 = "Capital";
	public static final String RQ04 = "Contract Required";
	public static final String RQ05 = "Establish Blanket";
	public static final String RQ06 = "In Budget";
	public static final String RQ08 = "Deposit Required";
	public static final String SCR2 = "UDF 2";
	public static final String SHP = "Ship Via Codes";
	public static final String SLC1 = "SLC1";
	public static final String SLC2 = "SLC2";
	public static final String SLC3 = "SLC3";
	public static final String STAT = "State/Province Codes";
	public static final String TAX = "TAX";
	public static final String UOM = "UOM";
	public static final String USRT = "User Types";
	public static final String VCLS = "VCLS";
	public static final String VN01 = "Qualified";
	public static final String VN03 = "Supplier Type";
	public static final String VN04 = "Organization Type";

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
		    return DictionaryManager.getInstance("std-type", organizationId).getProperty(type, type);
		}
	}

	public static List getAllValues(String organizationId)
	{
		return getAllValues("std-type", organizationId);
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByValue("std-type", organizationId);
	}
}