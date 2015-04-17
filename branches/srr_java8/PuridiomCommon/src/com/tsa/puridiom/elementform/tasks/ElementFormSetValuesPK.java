package com.tsa.puridiom.elementform.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class ElementFormSetValuesPK
{
	public void setValues(Map incomingRequest, ElementForm elementform ) throws Exception
	{
		try
		{
			String formId = (String ) incomingRequest.get("ElementForm_formId");
			String elementIndexString = (String) incomingRequest.get("ElementForm_elementIndex");
			BigDecimal elementIndex = new BigDecimal ( elementIndexString );
			ElementFormPK comp_id = new ElementFormPK();
			comp_id.setElementIndex(elementIndex);
			comp_id.setFormId(formId);
			elementform.setComp_id(comp_id);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}