package com.tsa.puridiom.inspectiondispos.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class InspectionDisposSetValuesPK
{
	public void setValues(Map incomingRequest, InspectionDispos inspectiondispos ) throws Exception
	{
		try
		{
			String icRecHeaderString = (String) incomingRequest.get("InspectionDispos_icRecHeader");
			BigDecimal icRecHeader = new BigDecimal ( icRecHeaderString );
			String icRecLineString = (String) incomingRequest.get("InspectionDispos_icRecLine");
			BigDecimal icRecLine = new BigDecimal ( icRecLineString );
			String icInspDiscrepString = (String) incomingRequest.get("InspectionDispos_icInspDiscrep");
			BigDecimal icInspDiscrep = new BigDecimal ( icInspDiscrepString );
			String keySequenceString = (String) incomingRequest.get("InspectionDispos_keySequence");
			BigDecimal keySequence = new BigDecimal ( keySequenceString );
			InspectionDisposPK comp_id = new InspectionDisposPK();
			comp_id.setIcRecHeader(icRecHeader);
			comp_id.setIcRecLine(icRecLine);
			comp_id.setIcInspDiscrep(icInspDiscrep) ;
			comp_id.setKeySequence(keySequence);
			inspectiondispos.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}