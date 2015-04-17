package com.tsa.puridiom.inspectionhistory.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionHistorySetValuesPK
{
	public void setValues(Map incomingRequest, InspectionHistory inspectionhistory ) throws Exception
	{
		try
		{
			String icHistoryString = (String) incomingRequest.get("InspectionHistory_icHistory");
			BigDecimal icHistory = new BigDecimal ( icHistoryString );
			inspectionhistory.setIcHistory(icHistory);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}