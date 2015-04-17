package com.tsa.puridiom.invoice.approvals.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class ApprovalLogSetValuesPK
{
	public void setValues(Map incomingRequest, ApprovalLog approvallog ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("ApprovalLog_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("ApprovalLog_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String sequenceString = (String) incomingRequest.get("ApprovalLog_sequence");
			BigDecimal sequence = new BigDecimal ( sequenceString );
			String userId = (String ) incomingRequest.get("ApprovalLog_userId");
			ApprovalLogPK comp_id = new ApprovalLogPK();
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(icLine);
			comp_id.setSequence(sequence);
			comp_id.setUserId(userId);
			approvallog.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}