/**
 *
 */
package com.tsa.puridiom.budget.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class BudgetSetupType extends Task
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
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			String documentType = (String) incomingRequest.get("formtype");
			Object header = incomingRequest.get("header");
			String budgetType = "";

			if (documentType.equalsIgnoreCase("REQ"))
			{
				budgetType = "REQ";

			} else if (documentType.equalsIgnoreCase("PO"))
			{
				PoHeader poHeader = (PoHeader) header;
				BigDecimal IcPoHeader = poHeader.getIcPoHeader();

				String queryString = "from PoHeader PoHeader where PoHeader.icPoHeader = ? and PoHeader.icReqHeader in " +
									"(select RequisitionHeader.icReqHeader from RequisitionHeader RequisitionHeader " +
									"where RequisitionHeader.requisitionType = 'C' )";
				List resultList = dbs.query(queryString, new Object[] {IcPoHeader, } , new Type[] { Hibernate.BIG_DECIMAL}) ;

				if (poHeader.getPoType().equalsIgnoreCase("BO") && poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) == 0)
				{
					budgetType = "POBO";
				}
				else if (poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0)
				{
					if (resultList != null && resultList.size() > 0)
					{
						budgetType = "POCREV";
					}
					else
					{
						budgetType = "POREV";
					}
				}
				else
				{
					budgetType = "PO";
				}

			} else if (documentType.equalsIgnoreCase("IVC"))
			{
				budgetType = "IVC";
			}

			result = budgetType;

			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "BudgetSetupType error " + e.getMessage());

			throw e;
		}

		return result;
	}

}
