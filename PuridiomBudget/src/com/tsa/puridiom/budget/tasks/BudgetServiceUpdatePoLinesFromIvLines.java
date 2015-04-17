/**
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.poline.tasks.PoLineRetrieveById;
import com.puridiom.service.budget.BudgetEntity;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author Johnny
 */
public class BudgetServiceUpdatePoLinesFromIvLines extends Task
{
	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			List budgetEntityList = (List) incomingRequest.get("budgetEntityList");
			String organizationId = (String) incomingRequest.get("organizationId");
			String userId = (String) incomingRequest.get("userId");
			DBSession dbsession = (DBSession) incomingRequest.get("dbsession");
			Map newIncomingRequest = new HashMap();

			newIncomingRequest.put("organizationId", organizationId);
			newIncomingRequest.put("userId", userId);
			newIncomingRequest.put("dbsession", dbsession);

			for (Iterator it = budgetEntityList.iterator(); it.hasNext();)
			{
				BudgetEntity budgetEntity = (BudgetEntity) it.next();

				if (budgetEntity.getBudgetAction().getType().equalsIgnoreCase("IVC"))
				{
					if (budgetEntity.getIcReferenceLine() != null &&
						budgetEntity.getIcReferenceLine().compareTo(new BigDecimal(0)) > 0)
					{
						PoLine poLine;

						PoLineRetrieveById poLineRetrieveById = new PoLineRetrieveById();

						newIncomingRequest.put("PoLine_icPoLine", budgetEntity.getIcReferenceLine().toString());

						poLine = (PoLine) poLineRetrieveById.executeTask(newIncomingRequest);

						if (poLineRetrieveById.getStatus() == Status.FAILED)
						{
							throw new TsaException("Error loading Po Line from Invoice Line " + budgetEntity.getIcReferenceLine());
						}

						poLine.setExpensed(poLine.getExpensed().add(budgetEntity.getLineTotal()).setScale(2, BigDecimal.ROUND_HALF_UP));
					}
				}
			}

			this.setStatus(Status.SUCCEEDED);
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetServiceUpdatePoLinesFromIvLines error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
