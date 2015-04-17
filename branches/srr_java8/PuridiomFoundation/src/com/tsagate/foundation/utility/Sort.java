package com.tsagate.foundation.utility;

import java.math.BigDecimal;
import java.util.*;

public class Sort
{
	public static Object sortIt(List listToSort, String columnName)
	{
		Object result = null;

		try
		{
			List updateList = new ArrayList();

			if (listToSort != null)
			{
				//First sort the RfqLine entities by the original rfq line values.
				Map keysMap = new HashMap();
				for (int i = 0; i < listToSort.size(); i++)
				{
					Object element = listToSort.get(i);
					
					Object key = EntityUtility.invokeGet(columnName, element);

					while (keysMap.containsKey(key))
					{
						// Must be a duplicate
						 key = Sort.makeUnique(key);
					}
					keysMap.put(key, element);
				}

				TreeSet keySet = new TreeSet(keysMap.keySet());
				Iterator iterator = keySet.iterator();
	
				while (iterator.hasNext())
				{
					Object key = iterator.next();
					Object entity = keysMap.get(key);
					
					updateList.add(entity);
				}
			}
			result = updateList;
			
		}
		catch (Exception e)
		{
			Log.error(new Sort(), e.toString());
		}
		return result;
	}

	/**
	 * makeUnique
	 * @param key
	 * @return
	 */
	public static Object makeUnique(Object key)
	{
		Object newKey = null;
		
		if(key instanceof String)
		{
			newKey = (String)key + "1";
		}
		else if(key instanceof BigDecimal)
		{
			newKey = ((BigDecimal)key).add(new BigDecimal(0.0001));
		}
		else if(key instanceof Date)
		{
			long date = ((Date)key).getTime();
			newKey = new Date(date ++);
		}
		return newKey;
	}
}