package com.tsa.puridiom.rfqheader.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.processengine.*;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;
import java.util.*;

public class RfqHeaderAmendment extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			/* Expects incoming request to contain rfqHeader and userId */
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			RfqHeader	originalRfqHeader = (RfqHeader) incomingRequest.get("originalRfqHeader");
			RfqHeader	rfqHeader = (RfqHeader) incomingRequest.get("rfqHeader");
			
			String	rfqAmendment = originalRfqHeader.getRfqAmendment();
			
			if (!Utility.isEmpty(rfqAmendment))
			{
				try
				{
					int iamendment = Integer.valueOf(rfqAmendment).intValue();
					iamendment = iamendment + 1;
					rfqAmendment = Utility.formatString("0000", String.valueOf(iamendment));
				}
				catch (Exception e1)
				{
					rfqAmendment = "0001";
				} 
			}
			else
			{
				rfqAmendment = "0001";
			}

			rfqHeader.setAuthorizationCode(originalRfqHeader.getAuthorizationCode());
			rfqHeader.setBidDueTime(originalRfqHeader.getBidDueTime());
			rfqHeader.setDepartmentCode(originalRfqHeader.getDepartmentCode());
			rfqHeader.setDueDate(originalRfqHeader.getDueDate());
			rfqHeader.setIcHeaderHistory(originalRfqHeader.getIcHeaderHistory());
			rfqHeader.setIcReqHeader(originalRfqHeader.getIcReqHeader());
			rfqHeader.setRequisitionerCode(originalRfqHeader.getRequisitionerCode());
			rfqHeader.setRequisitionNumber(originalRfqHeader.getRequisitionNumber());
			rfqHeader.setRfqAmendment(rfqAmendment);
			rfqHeader.setRfqNumber((String) incomingRequest.get("newRfqHeader_rfqNumber"));
			rfqHeader.setStatus(DocumentStatus.RFQ_OPENAMENDMENT);

			incomingRequest.put("rfqHeader", rfqHeader) ;
/*	
			RfqHeaderAdd addTask = new RfqHeaderAdd() ;
			rfqHeader = (RfqHeader) addTask.executeTask(incomingRequest);
			this.setStatus(addTask.getStatus()) ;
*/		
			result = rfqHeader;
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