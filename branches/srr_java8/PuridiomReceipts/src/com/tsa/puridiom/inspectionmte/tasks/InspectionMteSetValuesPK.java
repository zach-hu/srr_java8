package com.tsa.puridiom.inspectionmte.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionMteSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionMte inspectionmte ) throws Exception
	{
		try
		{
			String icRecHeaderString = (String) incomingRequest.get("InspectionMte_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			String icRecLineString = (String) incomingRequest.get("InspectionMte_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );
			String keySequenceString = (String) incomingRequest.get("InspectionMte_keySequence");
			BigDecimal keySequence = new BigDecimal ( keySequenceString );
			String icInspNoString = (String) incomingRequest.get("InspectionMte_icInspNo");
			BigDecimal icInspNo = new BigDecimal ( icInspNoString );
			InspectionMtePK comp_id = new InspectionMtePK();
			comp_id.setIcRecHeader(icRecHeader);
			comp_id.setIcRecLine(icRecLine);
			comp_id.setKeySequence(keySequence);
			comp_id.setIcInspNo(icInspNo);
			inspectionmte.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}