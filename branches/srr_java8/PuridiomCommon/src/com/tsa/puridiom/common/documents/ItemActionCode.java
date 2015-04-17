/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class ItemActionCode 
{
	public static final String BUY = "B" ;
	public static final String FILL = "F" ;
	public static final String MAKE = "M" ;
	
	public static String toString(String type) {
		return ItemActionCode.toString(type, "");
	}

    /**
     * @param string
     * @param organizationId
     * @return
     */
    public static String toString(String type, String organizationId) {
        if (type == null || type.equals("null")) {
			return "";
		}
		else {
		    return DictionaryManager.getInstance("item-action-code", organizationId).getProperty(type, type);
		}
    }
}
