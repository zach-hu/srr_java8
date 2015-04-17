package com.tsa.puridiom.requisitionheader.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class RequisitionHeaderSetValuesPK
{
	public void setValues(Map incomingRequest, RequisitionHeader requisitionheader ) throws Exception
	{
		try
		{
			String icReqHeaderString = (String) incomingRequest.get("RequisitionHeader_icReqHeader");
			BigDecimal icReqHeader = new BigDecimal ( icReqHeaderString );
			requisitionheader.setIcReqHeader(icReqHeader);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}