package com.tsa.puridiom.budgetcenter.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.puridiom.service.budget.exceptions.BudgetDoesNotExist;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class BudgetCenterRetrieveFromArray extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			Object udfArray[] = (Object[])incomingRequest.get("budgetString");

			String queryString = "from BudgetCenter budgetCenter where 1 = 1 ";
			StringBuffer wheresb = new StringBuffer();
			boolean isBudgetKeyCorrect = false;

			List resultList = new ArrayList();
			for (int i = 0; i < udfArray.length; i++)
			{
				if(!HiltonUtility.isEmpty(udfArray[i].toString()))
				{
					if (i == 0)
					{
						wheresb.append("and budgetCenter.budgetPeriod = '" + udfArray[i].toString().trim() + "' ");
					} else
					{
						isBudgetKeyCorrect = true;
						wheresb.append(" and budgetCenter.budget" + i + " = '" + udfArray[i].toString().trim() + "' ");
					}
				}
			}

			queryString = queryString + wheresb.toString();

			if(isBudgetKeyCorrect)
			{
				resultList = dbs.query(queryString);

				if (resultList != null && resultList.size() > 0)
				{
					result = resultList.get(0);
				}
				else
				{
					this.setStatus(Status.FAILED);
					throw new BudgetDoesNotExist(udfArray);
				}
			}
			else
			{
				result = null;
			}
			this.setStatus(dbs.getStatus()) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
		}
		return result;
	}

}