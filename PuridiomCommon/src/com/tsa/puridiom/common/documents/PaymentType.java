/*
 * Created on November 14, 2006
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Kathleen
 */
public class PaymentType
{
	public static final String  AMERICAN_EXPRESS= "AMEX" ;
	public static final String DISCOVER = "DISC";
	public static final String VISA = "VISA";
	public static final String MASTERCARD = "MC";
	public static final String PCARD = "PCARD";
	public static final String CREDIT_CARD = "CC";
	public static final String ON_ACCOUNT = "OA";

	public static String toString(String type)
	{
		return PaymentType.toString(type, "PURIDIOM");
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
		}
		else
		{
		    return DictionaryManager.getInstance("payment-type", organizationId).getProperty(type, type);
		}
    }

    public static String toValue(String typeText, String organizationId)
    {
        String paymentType = "";
        try
        {
            if (Utility.isEmpty(typeText) || typeText.equals("null"))
            {
                return "";
            }
            else
            {
                Map paymentTypeMap = DictionaryManager.getInstance("payment-type", organizationId).getPropertyMap();
                Iterator paymentTypeKeys = paymentTypeMap.keySet().iterator();

                while (paymentTypeKeys.hasNext()) {
                    String	key = (String) paymentTypeKeys.next();
                    String	value = (String) paymentTypeMap.get(key);

                    if (value.equalsIgnoreCase(typeText)) {
                        paymentType = key;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.error("PaymentType", "Error getting value for status: " + typeText);
            Log.error("PaymentType", e.toString());
        }
        return paymentType;
    }
}
