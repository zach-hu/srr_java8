package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RequisitionLineRenumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List requisitionLineList = (List) incomingRequest.get("requisitionLineList");
			List updateList = new ArrayList();

			if (requisitionLineList != null)
			{
				//First sort the RequisitionLine entities by the original requisition line values.
				Map requisitionLineMap = new HashMap();
				for (int i = 0; i < requisitionLineList.size(); i++)
				{
					RequisitionLine requisitionLine = (RequisitionLine) requisitionLineList.get(i);
					BigDecimal bdKey = requisitionLine.getLineNumber();

					while (requisitionLineMap.containsKey(bdKey))
					{
						// Must be a duplicate
						bdKey = bdKey.add(new BigDecimal("0.00001"));
					}
					requisitionLineMap.put(bdKey, requisitionLine);
				}

				TreeSet keySet = new TreeSet(requisitionLineMap.keySet());
				Iterator iterator = keySet.iterator();
				int	i = 1;
	
				while (iterator.hasNext())
				{
					BigDecimal key = (BigDecimal) iterator.next();
					RequisitionLine requisitionLine = (RequisitionLine) requisitionLineMap.get(key);
					BigDecimal lineNumber = new BigDecimal(i);
					
					if (requisitionLine.getLineNumber().compareTo(lineNumber) != 0)
					{
						requisitionLine.setLineNumber(new BigDecimal(i));
						updateList.add(requisitionLine);
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