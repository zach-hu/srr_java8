/**
 *
 */
package com.tsa.puridiom.common.documents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.tsagate.properties.DictionaryManager;

/**
 * @author Johnny Zapana
 */
public abstract class GeneralType
{
	protected static String toString(String resource, String type)
	{
		return "";
	}

	protected static String toString(String resource, String type, String organizationId)
	{
		return "";
	}

	protected static String toValue(String resource, String typeText, String organizationId)
	{
		return "";
	}

	protected static List getAllValues(String resource, String organizationId)
	{
		Map typeMap = DictionaryManager.getInstance(resource, organizationId).getPropertyMap();
		return new ArrayList(typeMap.values());
	}

	protected static Map getPropertyMapOrderedByValue(String resource, String organizationId)
	{
		Map orderedPropertyMap = new LinkedHashMap();

		Map propertyMap = DictionaryManager.getInstance(resource, organizationId).getPropertyMap();

		List keys = new ArrayList(propertyMap.keySet());

		List values = new ArrayList(propertyMap.values());

		Object[] arrayValues = values.toArray();

		Arrays.sort(arrayValues);

		for (int i = 0; i < arrayValues.length; i++)
		{
			orderedPropertyMap.put(keys.get(values.indexOf(arrayValues[i])), arrayValues[i]);
		}

		return orderedPropertyMap;
	}

	protected static Map getPropertyMapOrderedByKey(String resource, String organizationId)
	{
		Map orderedPropertyMap = new LinkedHashMap();

		Map propertyMap = DictionaryManager.getInstance(resource, organizationId).getPropertyMap();

		List keys = new ArrayList(propertyMap.keySet());

		List values = new ArrayList(propertyMap.values());

		Object[] arrayKeys = keys.toArray();

		Arrays.sort(arrayKeys);

		String key;

		for (int i = 0; i < arrayKeys.length; i++)
		{
			key = String.valueOf(arrayKeys[i]);

			if (key.indexOf('_') > 0)
			{
				key = key.replaceFirst("[a-z]_", "");
			}

			orderedPropertyMap.put(key, values.get(keys.indexOf(arrayKeys[i])));
		}

		return orderedPropertyMap;
	}

	protected static Map getPropertyMapOrderedByIntKey(String resource, String organizationId, String startCode)
	{
		Map orderedPropertyMap = new LinkedHashMap();

		Map propertyMap = DictionaryManager.getInstance(resource, organizationId).getPropertyMap();

		List keys = new ArrayList(propertyMap.keySet());

		List values = new ArrayList(propertyMap.values());

		Object[] arrayKeys = keys.toArray();

		Arrays.sort(arrayKeys, GeneralType.IntegerComparator);

		for (int i = 0; i < arrayKeys.length; i++)
		{
			if (Integer.parseInt((String) arrayKeys[i]) >= Integer.parseInt(startCode))
			{
				orderedPropertyMap.put(arrayKeys[i], values.get(keys.indexOf(arrayKeys[i])));
			}
		}

		return orderedPropertyMap;
	}

	protected static Map getPropertyMapOrderedByIntKey(String resource, String organizationId, String startCode, String lastCode)
	{
		Map orderedPropertyMap = new LinkedHashMap();

		Map propertyMap = DictionaryManager.getInstance(resource, organizationId).getPropertyMap();

		List keys = new ArrayList(propertyMap.keySet());

		List values = new ArrayList(propertyMap.values());

		Object[] arrayKeys = keys.toArray();

		Arrays.sort(arrayKeys, GeneralType.IntegerComparator);

		for (int i = 0; i < arrayKeys.length; i++)
		{
			if (Integer.parseInt((String) arrayKeys[i]) >= Integer.parseInt(startCode) && Integer.parseInt((String) arrayKeys[i]) <= Integer.parseInt(lastCode))
			{
				orderedPropertyMap.put(arrayKeys[i], values.get(keys.indexOf(arrayKeys[i])));
			}
		}

		return orderedPropertyMap;
	}

	private static Comparator IntegerComparator = new Comparator()
	{
		public int compare(Object key_1, Object key_2)
		{
			return Integer.parseInt((String) key_1) - Integer.parseInt((String) key_2);
		}
	};

}
