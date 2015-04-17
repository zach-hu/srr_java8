package com.tsa.puridiom.rfqquestion.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.*;

public class RfqQuestionRenumber extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List rfqQuestionList = (List) incomingRequest.get("rfqQuestionList");
			List updateList = new ArrayList();

			if (rfqQuestionList != null)
			{
				//First sort the RfqQuestion entities by the original sequence values.
				Map rfqQuestionMap = new HashMap();
				for (int i = 0; i < rfqQuestionList.size(); i++)
				{
					RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionList.get(i);
					BigDecimal bdKey = rfqQuestion.getSequence();

					while (rfqQuestionMap.containsKey(bdKey))
					{
						// Must be a duplicate
						bdKey = bdKey.add(new BigDecimal("0.00001"));
					}
					rfqQuestionMap.put(bdKey, rfqQuestion);
				}

				TreeSet keySet = new TreeSet(rfqQuestionMap.keySet());
				Iterator iterator = keySet.iterator();
				int	i = 1;
	
				while (iterator.hasNext())
				{
					BigDecimal key = (BigDecimal) iterator.next();
					RfqQuestion rfqQuestion = (RfqQuestion) rfqQuestionMap.get(key);
					BigDecimal lineNumber = new BigDecimal(i);
					
					if (rfqQuestion.getSequence().compareTo(lineNumber) != 0)
					{
						rfqQuestion.setSequence(new BigDecimal(i));
						updateList.add(rfqQuestion);
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