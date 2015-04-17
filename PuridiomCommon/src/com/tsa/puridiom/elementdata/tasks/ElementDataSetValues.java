package com.tsa.puridiom.elementdata.tasks;

import com.tsa.puridiom.entity.*;
import com.tsagate.foundation.utility.*;
import com.tsagate.foundation.processengine.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ElementDataSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			ElementDataPK comp_id = new ElementDataPK();
			ElementData elementData = (ElementData) incomingRequest.get("elementData");
			if (elementData == null)
			{
				elementData = new ElementData();
			}

			if (incomingRequest.containsKey("ElementData_formId"))
			{
				String formId = (String) incomingRequest.get("ElementData_formId");
				comp_id.setFormId(formId);
			}
			if (incomingRequest.containsKey("ElementData_icHeader"))
			{
				String icHeaderString = (String) incomingRequest.get("ElementData_icHeader");
				if (Utility.isEmpty(icHeaderString))
				{
					icHeaderString = "0";
				}
				BigDecimal icHeader = new BigDecimal ( icHeaderString );
				comp_id.setIcHeader(icHeader);
			}
			if (incomingRequest.containsKey("ElementData_icLine"))
			{
				String icLineString = (String) incomingRequest.get("ElementData_icLine");
				if (Utility.isEmpty(icLineString))
				{
					icLineString = "0";
				}
				BigDecimal icLine = new BigDecimal ( icLineString );
				comp_id.setIcLine(icLine);
			}
			if (incomingRequest.containsKey("ElementData_icSequence"))
			{
				String icSequenceString = (String) incomingRequest.get("ElementData_icSequence");
				if (Utility.isEmpty(icSequenceString))
				{
					icSequenceString = "0";
				}
				BigDecimal icSequence = new BigDecimal ( icSequenceString );
				comp_id.setIcSequence(icSequence);
			}
			if (incomingRequest.containsKey("ElementData_elementId"))
			{
				String elementId = (String) incomingRequest.get("ElementData_elementId");
				comp_id.setElementId(elementId);
			}
			if (incomingRequest.containsKey("ElementData_elementValue"))
			{
				String elementValue = (String) incomingRequest.get("ElementData_elementValue");
				elementData.setElementValue(elementValue);
			}
			elementData.setComp_id(comp_id);

			result = elementData;
			this.status = Status.SUCCEEDED;
		}
		catch (Exception e)
		{
			this.status = Status.FAILED;
			throw e;
		}
		return result;
	}
}