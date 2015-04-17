package com.tsa.puridiom.bommethod.tasks;

import com.tsa.puridiom.entity.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class BomMethodSetValuesPK
{
	public void setValues(Map incomingRequest, BomMethod bommethod ) throws Exception
	{
		try
		{
			String icMethodString = (String) incomingRequest.get("BomMethod_icMethod");
			BigDecimal icMethod = new BigDecimal ( icMethodString );
			bommethod.setIcMethod(icMethod);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}