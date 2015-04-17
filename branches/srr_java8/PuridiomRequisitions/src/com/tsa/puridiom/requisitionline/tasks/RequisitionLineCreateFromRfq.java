package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.util.Map;

public class RequisitionLineCreateFromRfq extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			RequisitionHeader reqHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
			RequisitionLine reqLine = (RequisitionLine) incomingRequest.get("requisitionLine");
			if (reqLine == null)
			{
				reqLine = new RequisitionLine();
			}
			
			//generate new line ic
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			reqLine.setIcReqLine(new BigDecimal(ukg.getUniqueKey().toString()));
			
			reqLine.setIcReqHeader(reqHeader.getIcReqHeader());
			reqLine.setRequisitionNumber(reqHeader.getRequisitionNumber());
			
			reqLine.setStatus(DocumentStatus.RFQ_PRICED);
			
			incomingRequest.put("requisitionLine", reqLine);
			
			result = reqLine;
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