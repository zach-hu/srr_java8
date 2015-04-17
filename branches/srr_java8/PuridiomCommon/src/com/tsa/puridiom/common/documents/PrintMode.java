
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;


public class PrintMode {
	public static final String DRAFT_ONLY_NOT_AN_ORDER  = "N" ;
	public static final String ORIGINAL  = "O";
	public static final String DUPLICATE_ORDER = "D";
	
	public static String toString(String type)	{
	    return DisbursementType.toString(type, "");
	}

    public static String toString(String type, String organizationId) {
        if (type == null || type.equals("null")) {
			return "";
		}
	    else {
	        return DictionaryManager.getInstance("print-mode", organizationId).getProperty(type, type);
	    }
    }
}