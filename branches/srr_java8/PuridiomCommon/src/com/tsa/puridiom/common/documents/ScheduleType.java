/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Kelli
 */
public class ScheduleType
{
	public static final String PERFORMANCE_SCHEDULE = "R" ;
	public static final String DELIVERY_SCHEDULE = "D";
	public static final String MILESTONE_SCHEDULE = "M";
	public static final String PAYMENT_SCHEDULE = "P";
	public static final String OTHER_SCHEDULE = "O";

	public static String toString(String type)
	{
		return ScheduleType.toString(type, "");
	}

	public static String toString(String type, String organizationId)
	{
		if (type == null || type.equals("null"))
		{
			return "";
		}
		else
		{
		    return DictionaryManager.getInstance("schedule-type", organizationId).getProperty(type, type);
		}
	}
}
