package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqLineRenumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqLineList = (List) incomingRequest.get("rfqLineList");
			List updateList = new ArrayList();

			if (rfqLineList != null)
			{
				//First sort the RfqLine entities by the original rfq line values.
				Map rfqLineMap = new HashMap();
				for (int i = 0; i < rfqLineList.size(); i++)
				{
					RfqLine rfqLine = (RfqLine) rfqLineList.get(i);
					BigDecimal bdKey = rfqLine.getRfqLine();

					while (rfqLineMap.containsKey(bdKey))
					{
						// Must be a duplicate
						bdKey = bdKey.add(new BigDecimal("0.00001"));
					}
					rfqLineMap.put(bdKey, rfqLine);
				}

				TreeSet keySet = new TreeSet(rfqLineMap.keySet());
				Iterator iterator = keySet.iterator();
				int	i = 1;
	
				while (iterator.hasNext())
				{
					BigDecimal key = (BigDecimal) iterator.next();
					RfqLine rfqLine = (RfqLine) rfqLineMap.get(key);
					BigDecimal lineNumber = new BigDecimal(i);
					
					if (rfqLine.getRfqLine().compareTo(lineNumber) != 0)
					{
						rfqLine.setRfqLine(new BigDecimal(i));
						updateList.add(rfqLine);
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