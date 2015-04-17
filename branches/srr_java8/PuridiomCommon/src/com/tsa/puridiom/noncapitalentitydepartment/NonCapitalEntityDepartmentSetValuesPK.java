package com.tsa.puridiom.noncapitalentitydepartment;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.util.Map;

public class NonCapitalEntityDepartmentSetValuesPK
{
	public void setValues(Map incomingRequest, NonCapitalEntityDepartment nonCapitalEntityDepartment ) throws Exception
	{
		try
		{
			String icHeaderString = (String) incomingRequest.get("NonCapitalEntityDepartment_icHeader");
			BigDecimal icHeader = new BigDecimal ( icHeaderString );
			nonCapitalEntityDepartment.setIcHeader(icHeader);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}