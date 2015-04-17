package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class PoLineListRetrieveBuyerRemarksFromNothingList extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List	buyerPoRemarksList = new ArrayList();
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");


			BigDecimal bdHeader = poHeader.getIcPoHeader();
			String status = DocumentStatus.BUYER_REMARKS;

			String queryString = "from HistoryLog as hst where hst.icHeader = ? and hst.status = ? order by hst.logDate ASC, hst.logTime ASC";

			result = dbs.query(queryString, new Object[] {bdHeader, status} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING});

			buyerPoRemarksList.add(result);

			result = buyerPoRemarksList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}