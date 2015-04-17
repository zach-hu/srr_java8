/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import com.tsagate.properties.DictionaryManager;

import java.util.Iterator;
import java.util.Map;

/**
 * @author Kelli
 */
public class ReportQueueFrequency
{
	public static final String ONCE = "O" ;
	public static final String DAILY = "D";
	public static final String WEEKLY = "W";
	public static final String MONTHLY = "M";
	public static final String QUARTERLY = "Q";
	public static final String ANNUALY = "A";

	public static String toString(String frequency)
	{
		return ReportQueueFrequency.toString(frequency, "PURIDIOM");
	}

    /**
     * @param string
     * @param organizationId
     * @return
     */
    public static String toString(String frequency, String organizationId)
    {
		if (frequency == null || frequency.equals("null"))
		{
			return "";
		}
		else
		{
		    return DictionaryManager.getInstance("reportqueue-frequency", organizationId).getProperty(frequency, frequency);
		}
    }

    public static String toValue(String frequencyText, String organizationId)
    {
        String reportQueueFrequency = "";
        try
        {
            if (Utility.isEmpty(frequencyText) || frequencyText.equals("null"))
            {
                return "";
            }
            else
            {
                Map reportQueueFrequencyMap = DictionaryManager.getInstance("reportqueue-frequency", organizationId).getPropertyMap();
                Iterator reportQueueFrequencyKeys = reportQueueFrequencyMap.keySet().iterator();

                while (reportQueueFrequencyKeys.hasNext()) {
                    String	key = (String) reportQueueFrequencyKeys.next();
                    String	value = (String) reportQueueFrequencyMap.get(key);

                    if (value.equalsIgnoreCase(frequencyText)) {
                    	reportQueueFrequency = key;
                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.error("OrderType", "Error getting value for status: " + frequencyText);
            Log.error("OrderType", e.toString());
        }
        return reportQueueFrequency;
    }
}
