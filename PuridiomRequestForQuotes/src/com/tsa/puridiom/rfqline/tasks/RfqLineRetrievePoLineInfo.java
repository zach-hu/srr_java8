/*
 * Created on November 1, 2006
 */
package com.tsa.puridiom.rfqline.tasks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.ReferenceInfo;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

import org.hibernate.*;
import org.hibernate.type.Type;

/**
 * @author Kelli
 */
public class RfqLineRetrievePoLineInfo extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		List result = new ArrayList();
		try
		{
	        DBSession dbs = (DBSession) incomingRequest.get("dbsession");
	        String icRfqLine = (String) incomingRequest.get("RfqLine_icRfqLine");
	        List referenceInfoList = new ArrayList();
	        
			if (!Utility.isEmpty(icRfqLine) && !icRfqLine.equals("0")) {
		        String queryString = "select poLine.icPoHeader, poLine.icPoLine, poLine.poNumber, poHeader.releaseNumber from PoLine as poLine, PoHeader as poHeader " +
		        "where poHeader.icPoHeader = poLine.icPoHeader AND poHeader.lastRevision = 'C' AND poLine.icRfqLine = ? AND poLine.poNumber <> 'N/A'" ;
	
				BigDecimal bdLine = new BigDecimal(icRfqLine);
	
				List list = dbs.query(queryString,	new Object[] {bdLine}, new Type[] {Hibernate.BIG_DECIMAL});
				
				if (list != null) {
				    for (int ix=0; ix < list.size(); ix++) {
				        Object infoObj[] = (Object[]) list.get(ix);
				        BigDecimal icHeader = (BigDecimal) infoObj[0];
				        BigDecimal icLine = (BigDecimal) infoObj[1];
				        String formNumber = (String) infoObj[2];
				        BigDecimal releaseNumber = (BigDecimal) infoObj[3];
				        
				        ReferenceInfo referenceInfo = new ReferenceInfo();
				        referenceInfo.setFormType("POL");
				        referenceInfo.setIcHeader(icHeader);
				        referenceInfo.setIcLine(icLine);
				        referenceInfo.setFormNumber(formNumber);
				        referenceInfo.setReleaseNumber(releaseNumber);
				        
				        referenceInfoList.add(referenceInfo);
				    }
				}
			}
			
			result = referenceInfoList;
			
			this.setStatus(dbs.getStatus());
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "RfqLineRetrievePoLineInfo Failed: " + e.getMessage());
			throw e;
		}
		return result  ;
	}
}
