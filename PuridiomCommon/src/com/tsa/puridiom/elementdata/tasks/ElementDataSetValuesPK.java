package com.tsa.puridiom.elementdata.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ElementDataSetValuesPK
{
	public void setValues(Map incomingRequest, ElementData elementdata ) throws Exception
	{
		try
		{
			String formId = (String ) incomingRequest.get("ElementData_formId");
			String icHeaderString = (String) incomingRequest.get("ElementData_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			String icLineString = (String) incomingRequest.get("ElementData_icLine");
			BigDecimal icLine = new BigDecimal ( icLineString );
			String icSequenceString = (String) incomingRequest.get("ElementData_icSequence") ;
			if (icSequenceString == null) icSequenceString = "0";
			BigDecimal icSequence = new BigDecimal ( icSequenceString );

			String elementId = (String ) incomingRequest.get("ElementData_elementId");
			ElementDataPK comp_id = new ElementDataPK();
			comp_id.setElementId(elementId);
			comp_id.setFormId(formId);
			comp_id.setIcHeader(icHeader);
			comp_id.setIcLine(icLine);
			comp_id.setIcSequence(icSequence) ;
			elementdata.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}