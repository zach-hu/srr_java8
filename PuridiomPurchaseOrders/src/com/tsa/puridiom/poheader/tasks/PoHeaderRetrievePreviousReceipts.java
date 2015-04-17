package com.tsa.puridiom.poheader.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;

public class PoHeaderRetrievePreviousReceipts extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			if (poHeader == null) {
				throw new Exception("PoHeader was not found");
			}

			String poNumber = poHeader.getPoNumber();
			BigDecimal releaseNumber = poHeader.getReleaseNumber();
			BigDecimal revisionNumber = poHeader.getRevisionNumber();

			String queryString = "FROM ReceiptHeader as rh WHERE rh.icPoHeader in (SELECT ph.id.icPoHeader FROM " +
					"PoHeader as ph WHERE ph.poNumber = ? AND ph.releaseNumber = ? " +
					"GROUP BY ph.id.icPoHeader " +
					"HAVING MAX(ph.revisionNumber) < ?)";

			List resultList = dbs.query(queryString, new Object[] {poNumber, releaseNumber, revisionNumber},
					new Type[] {Hibernate.STRING, Hibernate.BIG_DECIMAL, Hibernate.BIG_DECIMAL});

			if (resultList != null && resultList.size() > 0)
			{
				result = resultList;
			}

			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}
}
