package com.tsa.puridiom.inspectionmfr.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionMfrSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionMfr inspectionmfr ) throws Exception
	{
		try
		{
			String icMfrNoString = (String) incomingRequest.get("InspectionMfr_icMfrNo");
			BigDecimal icMfrNo = new BigDecimal ( icMfrNoString );
			inspectionmfr.setIcMfrNo(icMfrNo);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}