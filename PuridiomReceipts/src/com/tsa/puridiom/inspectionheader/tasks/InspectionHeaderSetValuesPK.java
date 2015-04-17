package com.tsa.puridiom.inspectionheader.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionHeaderSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionHeader inspectionheader ) throws Exception
	{
		try
		{
			String icInspNoString = (String) incomingRequest.get("InspectionHeader_icInspNo");
			BigDecimal icInspNo = new BigDecimal ( icInspNoString );
			String icMsrLineString = (String) incomingRequest.get("InspectionHeader_icMsrLine");
			BigDecimal icMsrLine = new BigDecimal ( icMsrLineString );
			InspectionHeaderPK comp_id = new InspectionHeaderPK();
			comp_id.setIcInspNo(icInspNo);
			comp_id.setIcMsrLine(icMsrLine);
			inspectionheader.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}