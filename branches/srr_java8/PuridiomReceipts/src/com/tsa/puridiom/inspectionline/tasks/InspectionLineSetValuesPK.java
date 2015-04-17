package com.tsa.puridiom.inspectionline.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionLineSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionLine inspectionline ) throws Exception
	{
		try
		{
			String icInspLineString = (String) incomingRequest.get("InspectionLine_icInspLine");
			BigDecimal icInspLine = new BigDecimal ( icInspLineString );
			inspectionline.setIcInspLine(icInspLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}