package com.tsa.puridiom.inspectiondiscrep.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionDiscrepSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionDiscrep inspectiondiscrep ) throws Exception
	{
		try
		{
			String icRecHeaderString = (String) incomingRequest.get("InspectionDiscrep_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			String icRecLineString = (String) incomingRequest.get("InspectionDiscrep_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );
			String keySequenceString = (String) incomingRequest.get("InspectionDiscrep_keySequence");
			BigDecimal keySequence = new BigDecimal ( keySequenceString );
			InspectionDiscrepPK comp_id = new InspectionDiscrepPK();
			comp_id.setIcRecHeader(icRecHeader);
			comp_id.setIcRecLine(icRecLine);
			comp_id.setKeySequence(keySequence);
			inspectiondiscrep.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}