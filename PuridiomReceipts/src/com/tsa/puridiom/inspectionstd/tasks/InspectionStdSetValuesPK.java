package com.tsa.puridiom.inspectionstd.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionStdSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionStd inspectionstd ) throws Exception
	{
		try
		{
			String standardCode = (String ) incomingRequest.get("InspectionStd_standardCode");
			String inspectType = (String ) incomingRequest.get("InspectionStd_inspectType");
			String inspectCode = (String ) incomingRequest.get("InspectionStd_inspectCode");
			String critNo = (String ) incomingRequest.get("InspectionStd_critNo");
			InspectionStdPK comp_id = new InspectionStdPK();
			comp_id.setCritNo(critNo);
			comp_id.setInspectCode(inspectCode);
			comp_id.setInspectType(inspectType);
			comp_id.setStandardCode(standardCode);
//			inspectionstd.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}