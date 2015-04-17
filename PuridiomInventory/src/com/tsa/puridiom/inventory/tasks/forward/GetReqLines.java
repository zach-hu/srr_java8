/*
 * Created on Dec 3, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.inventory.forwardGetReqLines.java
 */
 
package com.tsa.puridiom.inventory.tasks.forward;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.Log;
import java.math.BigDecimal;
import java.util.*;

public class GetReqLines extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Map icReqLines = (Map)incomingRequest.get("icReqLines");
		Set keys = icReqLines.keySet();
		List reqLines = new ArrayList();
		PuridiomProcessLoader processLoader = new PuridiomProcessLoader((String)incomingRequest.get("organizationId"));
		PuridiomProcess process = processLoader.loadProcess("requisitionline-retrieve-by-id.xml");
		Map	retrieveParameters = new HashMap();
		
		retrieveParameters.put("dbsession", incomingRequest.get("dbsession"));
		retrieveParameters.put("userId", incomingRequest.get("userId"));
		retrieveParameters.put("userTimeZone", incomingRequest.get("userTimeZone"));
		retrieveParameters.put("organizationId", incomingRequest.get("organizationId"));
		retrieveParameters.put("dbsession", incomingRequest.get("dbsession"));
		
		for (Iterator iter = keys.iterator(); iter.hasNext();)
		{
			BigDecimal icReqLine = (BigDecimal) iter.next();
			retrieveParameters.put("RequisitionLine_icReqLine", icReqLine.toString());
			
			process.executeProcess(retrieveParameters);
			
			if(process.getStatus() != Status.SUCCEEDED)
			{
				Log.error(this, "error retrieving req line: " + icReqLine.toString());
				return null;
			}
			else if ((RequisitionLine)retrieveParameters.get("requisitionLine") != null)
			{
				RequisitionLine reqLine = (RequisitionLine)retrieveParameters.get("requisitionLine");
				BigDecimal qtyDisbursed = (BigDecimal)icReqLines.get(icReqLine);
				Log.debug(this, "disbForward-qty to disburse: " + qtyDisbursed.toString() + " -item: " + reqLine.getItemNumber() + " -line " + reqLine.getLineNumber());
				if(reqLine.getBackordered().compareTo(new BigDecimal(0)) == 0 || reqLine.getQuantity().compareTo(qtyDisbursed) > 0)
				{
				    BigDecimal temp = null;
				    if(reqLine.getBackordered().compareTo(new BigDecimal(0)) < 1)
				    {
				        temp = reqLine.getQuantity().subtract(qtyDisbursed);
				    }
				    else
				    {
				        temp = reqLine.getBackordered().subtract(qtyDisbursed);
				    }
				    
				    if(temp.compareTo(new BigDecimal(0)) < 1)
				    {
				        temp = new BigDecimal(0);
				    }
				    else
				    {
				        incomingRequest.put("historyReqBackorder", temp);
				    }
				    
				    reqLine.setBackordered(temp);
				}
				else
				{
				    reqLine.setBackordered(new BigDecimal(0));
				}
				
				if(reqLine.getStatus().compareTo(DocumentStatus.REQ_APPROVED) == 0)
				{
			        reqLine.setStatus(DocumentStatus.INV_INPROGRESS);
				}
				else
				{
				    if(reqLine.getBackordered().compareTo(reqLine.getQuantity()) == 0)
				    {
				        reqLine.setStatus(DocumentStatus.INV_BACKORDERED);
				    }
				    else if(reqLine.getBackordered().compareTo(new BigDecimal(0)) > 0)
				    {
				        reqLine.setStatus(DocumentStatus.INV_PARTIAL);
				    }
				    else if(reqLine.getBackordered().compareTo(new BigDecimal(0)) == 0)
				    {
				        reqLine.setStatus(DocumentStatus.INV_DISBURSED);
				    }
				}
				/*requisition line quantity should not be modified!*/
				reqLines.add(reqLine);
			}
		}
		
		this.setStatus(Status.SUCCEEDED);
		return reqLines;
	}
}