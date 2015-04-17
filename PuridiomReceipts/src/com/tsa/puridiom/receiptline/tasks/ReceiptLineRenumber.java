package com.tsa.puridiom.receiptline.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class ReceiptLineRenumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List receiptLineList = (List) incomingRequest.get("receiptLineList");
			List updateList = new ArrayList();

			if (receiptLineList != null)
			{
				//First sort the ReceiptLine entities by the original receipt line values.
				Map receiptLineMap = new HashMap();
				for (int i = 0; i < receiptLineList.size(); i++)
				{
					ReceiptLine receiptLine = (ReceiptLine) receiptLineList.get(i);
					BigDecimal bdKey = receiptLine.getReceiptLine();

					while (receiptLineMap.containsKey(bdKey))
					{
						// Must be a duplicate
						bdKey = bdKey.add(new BigDecimal("0.00001"));
					}
					receiptLineMap.put(bdKey, receiptLine);
				}

				TreeSet keySet = new TreeSet(receiptLineMap.keySet());
				Iterator iterator = keySet.iterator();
				int	i = 1;
	
				while (iterator.hasNext())
				{
					BigDecimal key = (BigDecimal) iterator.next();
					ReceiptLine receiptLine = (ReceiptLine) receiptLineMap.get(key);
					BigDecimal lineNumber = new BigDecimal(i);
					
					if (receiptLine.getReceiptLine().compareTo(lineNumber) != 0)
					{
						receiptLine.setReceiptLine(new BigDecimal(i));
						updateList.add(receiptLine);
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