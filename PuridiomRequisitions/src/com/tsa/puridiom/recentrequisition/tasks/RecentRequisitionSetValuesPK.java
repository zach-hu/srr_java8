package com.tsa.puridiom.recentrequisition.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.Utility;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RecentRequisitionSetValuesPK
{
	public void setValues(Map incomingRequest, RecentRequisition recentrequisition ) throws Exception
	{
		try
		{
			String	requisitionerCode = (String) incomingRequest.get("RecentRequisition_requisitionerCode");
			String icReqHeaderString = (String) incomingRequest.get("RecentRequisition_icReqHeader");
			if (Utility.isEmpty(icReqHeaderString))
			{
				icReqHeaderString = "0";
			}
			BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
			
			RecentRequisitionPK comp_id = new RecentRequisitionPK();
			comp_id.setRequisitionerCode(requisitionerCode);
			comp_id.setIcReqHeader(icReqHeader);
			recentrequisition.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}