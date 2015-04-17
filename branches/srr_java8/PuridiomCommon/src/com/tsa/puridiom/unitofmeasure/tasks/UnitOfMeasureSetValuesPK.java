package com.tsa.puridiom.unitofmeasure.tasks;

import com.tsa.puridiom.entity.UnitOfMeasure;
import java.util.Map;

public class UnitOfMeasureSetValuesPK
{
	public void setValues(Map incomingRequest, UnitOfMeasure unitofmeasure ) throws Exception
	{
		try
		{
			String umCode = (String ) incomingRequest.get("UnitOfMeasure_umCode");
			unitofmeasure.setUmCode(umCode);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
}