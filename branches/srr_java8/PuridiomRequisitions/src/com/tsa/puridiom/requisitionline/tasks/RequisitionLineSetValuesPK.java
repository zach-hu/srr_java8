package com.tsa.puridiom.requisitionline.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class RequisitionLineSetValuesPK
{
	public void setValues(Map incomingRequest, RequisitionLine requisitionline ) throws Exception
	{
		try
		{
			String icReqLineString = (String) incomingRequest.get("RequisitionLine_icReqLine");
			BigDecimal icReqLine = new BigDecimal ( icReqLineString );
			requisitionline.setIcReqLine(icReqLine);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}