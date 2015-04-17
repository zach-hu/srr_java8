package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class PoLineRenumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List poLineList = (List) incomingRequest.get("poLineList");
			List updateList = new ArrayList();

			if (poLineList != null)
			{
				//First sort the RfqLine entities by the original rfq line values.
				Map poLineMap = new HashMap();
				for (int i = 0; i < poLineList.size(); i++)
				{
					PoLine poLine = (PoLine) poLineList.get(i);
					BigDecimal bdKey = poLine.getLineNumber();

					while (poLineMap.containsKey(bdKey))
					{
						// Must be a duplicate
						bdKey = bdKey.add(new BigDecimal("0.00001"));
					}
					poLineMap.put(bdKey, poLine);
				}

				TreeSet keySet = new TreeSet(poLineMap.keySet());
				Iterator iterator = keySet.iterator();
				int	i = 1;
	
				while (iterator.hasNext())
				{
					BigDecimal key = (BigDecimal) iterator.next();
					PoLine poLine = (PoLine) poLineMap.get(key);
					BigDecimal lineNumber = new BigDecimal(i);
					
					if (poLine.getLineNumber().compareTo(lineNumber) != 0)
					{
						poLine.setLineNumber(new BigDecimal(i));
						updateList.add(poLine);
					}
					i++;
				}
			}

			result = updateList;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}