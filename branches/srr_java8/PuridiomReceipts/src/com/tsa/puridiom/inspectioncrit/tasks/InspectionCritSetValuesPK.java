package com.tsa.puridiom.inspectioncrit.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionCritSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionCrit inspectioncrit ) throws Exception
	{
		try
		{
			String inspectCode = (String ) incomingRequest.get("InspectionCrit_inspectCode");
			String critNo = (String ) incomingRequest.get("InspectionCrit_critNo");
			InspectionCritPK comp_id = new InspectionCritPK();
			comp_id.setCritNo(critNo);
			comp_id.setInspectCode(inspectCode);
			inspectioncrit.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}