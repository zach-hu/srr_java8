package com.tsa.puridiom.common.documents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;

public class DateRanges extends GeneralType
{
	public static String AS_DATE = ":as_date_";

	public static String TODAY = ":today";

	public static String THISWEEK = ":thisweek";

	public static String THISMONTH = ":thismonth";

	public static String THISQUARTER = ":thisquarter";

	public static String THISYEAR = ":thisyear";

	public static String LASTWEEK = ":lastweek";

	public static String LASTMONTH = ":lastmonth";

	public static String LASTQUARTER = ":lastquarter";

	public static String LASTYEAR = ":lastyear";

	public static String toString(String type, String organizationId)
	{
		String typeString = new String(type);
		Map propertyMap;
		List keys;
		int index;

		if (!HiltonUtility.isEmpty(type))
		{
			propertyMap = getPropertyMapOrderedByKey("date-range", organizationId);
			keys = new ArrayList(propertyMap.keySet());
			index = keys.indexOf(type);

			if (index >= 0)
			{
				typeString = (String) propertyMap.get(keys.get(index));
			} else if (type.startsWith(AS_DATE))
			{
				typeString = type.substring(AS_DATE.length());
			}
		}

		return typeString;
	}

	public static Map getPropertyMap(String organizationId)
	{
		return getPropertyMapOrderedByKey("date-range", organizationId);
	}
}
