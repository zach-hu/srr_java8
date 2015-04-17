package com.tsa.puridiom.poline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.PoHeader;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class PoLineListRetrieveBuyerRemarksList extends Task {
	public Object  executeTask (Object object) throws Exception {
		Map incomingRequest = (Map)object;
		Object result = null;

		try {
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			List	icReqHeaderList = (List) incomingRequest.get("icReqHeaderList");
			List	buyerRemarksList = new ArrayList();
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			for (int i = 0; i < icReqHeaderList.size(); i++)
			{
				String icHeader = (String) icReqHeaderList.get(i);
				if (icHeader == null)
				{
					icHeader = "0";
				}
				BigDecimal bdHeader = new BigDecimal(icHeader);
				String status = DocumentStatus.BUYER_REMARKS;
				String queryString ="";
				if(poHeader.getRevisionNumber().compareTo(new BigDecimal(0)) > 0 )
				{
					queryString = "from HistoryLog as hst where hst.icLine = ? and hst.status = ? order by hst.logDate ASC, hst.logTime ASC";
					bdHeader = poHeader.getIcPoHeader();
				}
				else
				{
					queryString = "from HistoryLog as hst where hst.icHeader = ? and hst.status = ? order by hst.logDate ASC, hst.logTime ASC";
				}

				result = dbs.query(queryString, new Object[] {bdHeader, status} , new Type[] { Hibernate.BIG_DECIMAL, Hibernate.STRING});

				buyerRemarksList.add(result);
			}

			result = buyerRemarksList;
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e) {
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}