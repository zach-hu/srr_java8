package com.tsa.puridiom.bomreference.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomReferenceSetValuesPK
{
	public void setValues(Map incomingRequest, BomReference bomreference ) throws Exception
	{
		try
		{
			String icReferenceString = (String) incomingRequest.get("BomReference_icReference");
			BigDecimal icReference = new BigDecimal ( icReferenceString );
			bomreference.setIcReference(icReference);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}