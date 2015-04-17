/**
 * Created on May 20, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.poheader.tasks.PoHeaderRetrievelastByNumber.java
 *
 */
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

public class PoSetupLastRevision extends Task
{
	/*
	 * @see com.tsagate.foundation.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			PoHeader poHeader = (PoHeader)incomingRequest.get("poHeader");
			String poNumber = poHeader.getPoNumber();
			BigDecimal releaseNumber = poHeader.getReleaseNumber();
			BigDecimal revisionNumber = poHeader.getRevisionNumber();
			String revisionDelete = revisionNumber.toString();
			revisionNumber = revisionNumber.subtract(new BigDecimal(1));
//******************************* Get History **********************************************//
			String icHeaderHistory = poHeader.getIcHeaderHistory().toString();
			String icLineHistory = "0";
			String icHeader = poHeader.getIcPoHeader().toString();
			String icLine = "0";
			String doctype = "POH";
			String description = "Delete Revision: "+revisionDelete;
//*****************************************************************************************//
			incomingRequest.put("PoHeader_poNumber", poNumber);
			incomingRequest.put("PoHeader_releaseNumber", releaseNumber);
			incomingRequest.put("PoHeader_revisionNumber", revisionNumber);
//*********************************** Set History *********************************************//
			incomingRequest.put("HistoryLog_icHeaderHistory", icHeaderHistory);
			incomingRequest.put("HistoryLog_icLineHistory", icLineHistory);
			incomingRequest.put("HistoryLog_icHeader", icHeader);
			incomingRequest.put("HistoryLog_icLine", icLine);
			incomingRequest.put("HistoryLog_doctype", doctype);
			incomingRequest.put("HistoryLog_description", description);
//*********************************************************************************************//
			incomingRequest.put("revisionDelete", revisionDelete);
			incomingRequest.put("checkRestoreRevision", "Y");
//*********************************************************************************************//
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

}
